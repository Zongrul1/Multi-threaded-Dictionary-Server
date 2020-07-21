package DictionaryClient;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
 

public class Main extends JFrame implements ActionListener{
	//variables
	private DictionaryClient clientSocket;
	//methods

	// window	
	public Main(String title,DictionaryClient clientSocket)
	{
		this.clientSocket = clientSocket;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 300, 700, 417);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 318, 368);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_4 = new JButton("Query");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_4.addActionListener(this);
		btnNewButton_4.setFocusPainted(false);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("Add");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setFocusPainted(false);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("Modify");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setFocusPainted(false);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.addActionListener(this);		
		btnNewButton.setFocusPainted(false);
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setFocusPainted(false);
		panel.add(btnNewButton_2);

		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.WHITE);
		panel_2.setBounds(343, 5, 334, 368);
		panel_2.setBackground(Color.WHITE);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel txtrDictionary = new JLabel();
		txtrDictionary.setFont(new Font("Monospaced", Font.BOLD, 36));
		txtrDictionary.setText("  Dictionary");
		txtrDictionary.setBounds(10, 77, 326, 45);
		panel_2.add(txtrDictionary);
		
		JTextArea txtrDitionaryqueryFor = new JTextArea();
		txtrDitionaryqueryFor.setEditable(false);
		txtrDitionaryqueryFor.setForeground(Color.DARK_GRAY);
		txtrDitionaryqueryFor.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtrDitionaryqueryFor.setBounds(10, 143, 336, 178);
		txtrDitionaryqueryFor.setLineWrap(true);
		txtrDitionaryqueryFor.setText("1.Query for searching words\r\n\r\n2.Add for add new words to dictionary\r\n\r\n3.Remove for remove words from dictionary\r\n\r\n4.Exit for leaving");
		panel_2.add(txtrDitionaryqueryFor);
		this.setTitle(title);
		this.setVisible(true);
		this.setResizable(false);
	}
	//actionPerformed
	public void actionPerformed(ActionEvent e) {
			String str = e.getActionCommand();
			//Query
			if(str.equals("Query")) {
				// TODO Auto-generated method stub
				String inputContent = JOptionPane.showInputDialog("words","default");
				if(inputContent != null) {
					str = str.concat(" " + inputContent);
					System.out.println(str);
					clientSocket.Send(clientSocket.getSocket(),str);
					clientSocket.Receive_Search(clientSocket.getSocket(),inputContent);
				}
			}							
			//Add
			else if(str.equals("Add")) {
				// TODO Auto-generated method stub
				//String inputContent = JOptionPane.showInputDialog("words","default");
				//str = str.concat(" " + inputContent);
				new Input(clientSocket,str);
			}
			//Modify
			else if(str.equals("Modify")) {
				// TODO Auto-generated method stub
				//String inputContent = JOptionPane.showInputDialog("words","default");
				//str = str.concat(" " + inputContent);
				new Input(clientSocket,str);
			}			
			//Remove
			else if(str.equals("Remove")) {
				// TODO Auto-generated method stub
				String inputContent = JOptionPane.showInputDialog("words","default");
				if(inputContent != null) {
					str = str.concat(" " + inputContent);
					System.out.println(str);
					clientSocket.Send(clientSocket.getSocket(),str);
					clientSocket.Receive(clientSocket.getSocket(),inputContent);
				}
			}
			
			//exit
			else if(str.equals("Exit")){
				System.exit(0);
			}
			
			else {
				
			}
	}
}	

