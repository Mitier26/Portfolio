package SWING2;

import javax.swing.*;
import javax.swing.text.NumberFormatter;


import java.awt.*;
import java.awt.event.*;

public class JoinPerson  extends JFrame
{
	private JFormattedTextField joinNameInputField;
	private JFormattedTextField joinAgeInputField;
	ButtonGroup bg;
	
//	Callback call;
	
	JoinPerson()
	{
		showJoinPanel();
	}
	
//	public void setCallback(Callback callback)
//	{
//		call = callback;
//		call.check(false);
//	}
	
	public void showJoinPanel()
	{
		setSize(300, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel joinTitlePanel = new JPanel();
		joinTitlePanel.setBackground(SystemColor.activeCaptionBorder);
		getContentPane().add(joinTitlePanel);
		
		JLabel joinTitle = new JLabel("등 록");
		joinTitle.setForeground(Color.ORANGE);
		joinTitle.setFont(new Font("돋움", Font.BOLD, 41));
		joinTitlePanel.add(joinTitle);
		
		JPanel joinNamePanel = new JPanel();
		getContentPane().add(joinNamePanel);
		joinNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel joinNameLabel = new JLabel("이 름 : ");
		joinNameLabel.setFont(new Font("돋움", Font.PLAIN, 18));
		joinNamePanel.add(joinNameLabel);
		
		joinNameInputField = new JFormattedTextField();
		joinNameInputField.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e)
			{
				if(((JFormattedTextField)e.getSource()).getText().length() > 8)
				{
					e.consume();
				}
			}
		});
		joinNameInputField.setFont(new Font("돋움", Font.PLAIN, 18));
		joinNamePanel.add(joinNameInputField);
		joinNameInputField.setColumns(10);
		
		JPanel joinGenderPanel = new JPanel();
		getContentPane().add(joinGenderPanel);
		
		JLabel joinGenderLabel = new JLabel("성 별 : ");
		joinGenderLabel.setFont(new Font("돋움", Font.PLAIN, 18));
		joinGenderPanel.add(joinGenderLabel);
		
		bg = new ButtonGroup();
		
		JRadioButton manRadioButton = new JRadioButton("남성");
		manRadioButton.setFont(new Font("돋움", Font.PLAIN, 18));
		joinGenderPanel.add(manRadioButton);
		
		JLabel lblNewLabel = new JLabel("      ");
		joinGenderPanel.add(lblNewLabel);
		
		JRadioButton womanRadioButton = new JRadioButton("여성");
		womanRadioButton.setFont(new Font("돋움", Font.PLAIN, 18));
		joinGenderPanel.add(womanRadioButton);
		 bg.add(manRadioButton); bg.add(manRadioButton);
		
		JPanel joinAgePanel = new JPanel();
		getContentPane().add(joinAgePanel);
		
		JLabel joinAgeLabel = new JLabel("나 이 : ");
		joinAgeLabel.setFont(new Font("돋움", Font.PLAIN, 18));
		joinAgePanel.add(joinAgeLabel);
		
		joinAgeInputField = new JFormattedTextField(new NumberFormatter());
		joinAgeInputField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e)
			{
				if(((JFormattedTextField)e.getSource()).getText().length() > 3)
				{
					e.consume();
				}
			}
		});
		joinAgeInputField.setFont(new Font("돋움", Font.PLAIN, 18));
		joinAgePanel.add(joinAgeInputField);
		joinAgeInputField.setColumns(10);
		
		JPanel joinButtonPanel = new JPanel();
		getContentPane().add(joinButtonPanel);
		
		JButton joinButton = new JButton("등 록");
		joinButton.setFont(new Font("돋움", Font.PLAIN, 18));
		joinButtonPanel.add(joinButton);
		
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing (WindowEvent e)
			{
				dispose();
				new PersonManager();
			}
		});
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(rootPaneCheckingEnabled);
		
		// 이름 8개, 나이  999 제한이 있어야 한다.
		joinButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// 예외처리를 해 주어야 한다.
				// age 숫자가 없을 경우
				String name = joinNameInputField.getText();
				String gender;
				int age = 0;
				if(joinAgeInputField.getText().length() > 1)
				{
					age = Integer.parseInt(joinAgeInputField.getText()); 
				}
				
				
				
				if(name.length() < 1)
				{
					name = "이름없음";
				}
				
				if(manRadioButton.isSelected())
				{
					gender = "남성";
				}
				else if (womanRadioButton.isSelected())
				{
					gender = "여성";
				}
				else
				{
					gender = "특이";
				}
				
				new PersonServerManager().putPerson(name, gender, age);
				
				//call.check(true);
				dispose();
				new PersonManager();
			}
		});
	}
	
}
