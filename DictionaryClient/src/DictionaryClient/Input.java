package DictionaryClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Input extends JFrame implements ActionListener{
	//variables
	private JTextField inputWord;
	private JTextArea inputMeaning;
	//String
	private String W;
	private String M;
	private String str;
	//socket
	private DictionaryClient clientSocket;
	public Input(DictionaryClient clientSocket,String str) {
		this.str = str;
		this.clientSocket = clientSocket;
		JLabel Word = new JLabel("Word");
		JLabel Meaning = new JLabel("Meaning");
		inputWord = new JTextField();
		inputMeaning = new JTextArea();
		inputMeaning.setLineWrap(true);
		JButton Enter = new JButton("Enter");
		Enter.addActionListener(this);
		Box wordBox = Box.createHorizontalBox();
		Box meaningBox = Box.createHorizontalBox();
		Box buttonBox = Box.createHorizontalBox();
		//word
		wordBox.add(Word);
		wordBox.add(Box.createHorizontalStrut(30));
		wordBox.add(inputWord);
		//meaning
		meaningBox.add(Meaning);
		meaningBox.add(Box.createHorizontalStrut(12));
		meaningBox.add(inputMeaning);
		//button
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(Enter);
		setLayout(null);
        setBounds(855,470,480,250);
        add(wordBox);
		wordBox.setBounds(20,20,200,20);
		add(meaningBox);
		meaningBox.setBounds(20,60,400,80);
        add(buttonBox);
        buttonBox.setBounds(20,150,200,50);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        validate();
	}
	public void actionPerformed(ActionEvent e) {
		W = inputWord.getText();
		M = inputMeaning.getText();
		System.out.println(W);
		if(W != null && M != null) {
			str = str.concat(" " + W + " " + M);
			System.out.println(str);
			clientSocket.Send(clientSocket.getSocket(),str);
			setVisible(false);
			clientSocket.Receive(clientSocket.getSocket(),str);
		}
	}
}
