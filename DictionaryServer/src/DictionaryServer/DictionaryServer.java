package DictionaryServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.StringTokenizer;


import javax.net.ServerSocketFactory;
import javax.swing.JOptionPane;

public class DictionaryServer {
	private static DictionaryFile Hmap;
	private static int count = 0;
	private static int port;
	private static Text te;
	public static void main(String[] args){
		port = Integer.parseInt(args[0]);
		Hmap = new DictionaryFile(args[1]);
		ServerSocketFactory factory = ServerSocketFactory.getDefault();
		try(ServerSocket listeningSocket = factory.createServerSocket(port)){
			 //counter to keep track of the number of clients			
			te = new Text("client condition","");
			//Listen for incoming connections for ever 
			String str;
			while (true) 
			{	
				str = "Server listening on port " + port + " for a connection";
				System.out.println(str);
				te.refresh(str);
				//Accept an incoming client connection request 
				Socket clientSocket = listeningSocket.accept(); //This method will block until a connection request is received
				count++;
				str = "Client conection number " + count + " accepted:";
				System.out.println(str);
				te.refresh(str);
				str = "Remote Port: " + clientSocket.getPort();
				System.out.println(str);
				te.refresh(str);
				str = "Remote Hostname: " + clientSocket.getInetAddress().getHostName();
				System.out.println(str);
				te.refresh(str);
				str = "Local Port: " + clientSocket.getLocalPort();
				System.out.println(str);
				te.refresh(str);
				// Start a new thread for a connection
				Thread t = new Thread(() -> {
					synchronized(clientSocket) {//lock
						try {
							serveClient(clientSocket,count);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				t.start();
			}
		} 
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "Address already in use: NET_Bind", "error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		finally {

		}
	}
	//	
	public static void serveClient(Socket client,int number) throws UnsupportedEncodingException, IOException {
		//Get the input/output streams for reading/writing data from/to the socket
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));

		
		//Read the message from the client and reply
		//Notice that no other connection can be accepted and processed until the last line of 
		//code of this loop is executed, incoming connections have to wait until the current
		//one is processed unless...we use threads!
		String clientMsg = null;
		try 
		{
			while((clientMsg = in.readLine()) != null) 
			{
				String[] input = new String[3];
				int j = 0;
				StringTokenizer st = new StringTokenizer(clientMsg);
				while(st.hasMoreElements()) {
					String str = st.nextToken();
					if(str != null) {
						input[j] = str;
					}
					j++;
				}

				input[0] = input[0].toLowerCase();
				if(input[1] != null) {
					input[1] = input[1].toLowerCase();
				}
				System.out.println("Message from client " + number + ": " + clientMsg);	
				te.refresh("Message from client " + number + ": " + clientMsg);
				if(input[0].equals("query")) {
					if(input[1] == null) {
						out.write("null \n");
					}
					else {
						String str = Hmap.Query(input[1]);
						if(str != null) {
							out.write(str + "\n");
						}
						else {
							out.write(input[1] + " does not exist \n");
						}
					}
				}
				else if(input[0].equals("add")) {
					if(input[1] == null||input[2] == null) {
						out.write("null \n");
					}
					else {
						String str = Hmap.Add(input[1],input[2]);
						if(str == null) {
							out.write(input[1] + " has existed  \n");
						}
						else {
							out.write(input[1] + " has been added successfully\n");
						}
					}
				}
				else if(input[0].equals("modify")) {
					if(input[1] == null||input[2] == null) {
						out.write("null \n");
					}
					else {
						String str = Hmap.Modify(input[1],input[2]);
						if(str == null) {
							out.write(input[1] + " does not exist  \n");
						}
						else {
							out.write(input[1] + " has been modified successfully\n");
						}
					}
				}
				else if(input[0].equals("remove")) {
					if(input[1] == null) {
						out.write("null \n");
					}
					else {
						String str = Hmap.Remove(input[1]);
						//System.out.println(str);
						if(str != null) {
							out.write(input[1] + " has been removed successfully\n");
						}
						else {
							out.write(input[1] + " does not exist \n");
						}
					}
				}
				else {
					out.write("error " + "\n");
				}
				System.out.println("Response sent");
				out.flush();
			}
		}
		
		catch(SocketException e)
		{
			System.out.println("closed...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
