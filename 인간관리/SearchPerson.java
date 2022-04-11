package SWING2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchPerson extends JFrame
{
	SearchPerson()
	{
		setTitle("인간 검색");
		setSize(300, 300);
		
		
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
	}
	
}
