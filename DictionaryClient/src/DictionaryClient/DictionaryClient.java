package DictionaryClient;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class DictionaryClient {
	private Socket clientSocket;
	private boolean flag;
	public static void main(String[] args) {
		DictionaryClient DC = new DictionaryClient();
		DC.Connection(args[0],Integer.parseInt(args[1]));		
		if(DC.flag == true) {
			Thread t = new Thread(()->new Main("Dictionary",DC));        //create a window
			t.start();
		}
		else {
			Thread t = new Thread(()->JOptionPane.showMessageDialog(null, "connection failed", "error", JOptionPane.ERROR_MESSAGE));        //if failed
			t.start();
		}

	}
	//get socket
	public Socket getSocket() {
		return clientSocket;
	}
	//socket connection
	public void Connection(String host,int port) {
		flag = true;
		try {
			clientSocket = new Socket(host, port);
			System.out.println("Connection established");
			flag = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed");
			flag = false;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection failed");	
			flag = false;
			e.printStackTrace();
		}
		
	}
	//Send
	public void Send(Socket clientSocket,String inputStr) {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
			Scanner scanner = new Scanner(System.in);
			out.write(inputStr + "\n");
			out.flush();
			System.out.println("Message sent");
			scanner.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "connection failed", "error", JOptionPane.ERROR_MESSAGE);  
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "connection failed", "error", JOptionPane.ERROR_MESSAGE);  
			e.printStackTrace();
			System.exit(0);
		}

	}
	//Receive for search
	public void Receive_Search(Socket clientSocket,String Content) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
			String received = in.readLine(); // This method blocks until there  is something to read from the
			// input stream
			received = received.trim();
			if(received.equals("null")) {
				JOptionPane.showMessageDialog(null, "Input field cannot be empty", "error", JOptionPane.ERROR_MESSAGE); 
			}
			else {
				new Text(Content,received);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Receive for normal operations
	public void Receive(Socket clientSocket,String Content) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
			String received = in.readLine(); // This method blocks until there  is something to read from the
			// input stream
			received = received.trim();
			if(received.equals("null")) {
				JOptionPane.showMessageDialog(null, "Input field cannot be empty", "error", JOptionPane.ERROR_MESSAGE); 
			}
			else {
				JOptionPane.showMessageDialog(null, received,Content, JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
