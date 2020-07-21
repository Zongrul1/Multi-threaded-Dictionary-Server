package DictionaryServer;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.*;

public class Text extends JFrame{
	private JPanel jp1;
	private JTextArea jta1;
	
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
        this.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
	}
	public void refresh(String content) {
		jta1.append(content+"\r\n");
		jta1.paintImmediately(jta1.getBounds());
	}
}
