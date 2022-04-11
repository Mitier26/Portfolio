package SWING2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EndScreen extends JFrame
{
	JButton b1, b2;
	
	EndScreen()
	{
		setTitle("종료확인");
		
		b1 = new JButton("종료");
		b2 = new JButton("최소");
		
		b1.setBounds(20, 70, 100, 30);
		b2.setBounds(170, 70, 100, 30);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
		add(b1); add(b2);
		
		setSize(300, 200);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
