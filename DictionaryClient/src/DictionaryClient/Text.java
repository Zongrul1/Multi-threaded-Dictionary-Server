package DictionaryClient;

import javax.swing.*;

public class Text extends JFrame{
	JPanel jp1;
	JTextArea jta1;
	public Text(String title,String content) {
		jta1 = new JTextArea(content);
		jta1.setEditable(false);
		jta1.setLineWrap(true);
		this.add(jta1);
		this.setTitle(title);
		this.setBounds(700,400,800,400);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        validate();
	}
}
