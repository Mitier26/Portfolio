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
		
		JLabel joinTitle = new JLabel("�� ��");
		joinTitle.setForeground(Color.ORANGE);
		joinTitle.setFont(new Font("����", Font.BOLD, 41));
		joinTitlePanel.add(joinTitle);
		
		JPanel joinNamePanel = new JPanel();
		getContentPane().add(joinNamePanel);
		joinNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel joinNameLabel = new JLabel("�� �� : ");
		joinNameLabel.setFont(new Font("����", Font.PLAIN, 18));
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
		joinNameInputField.setFont(new Font("����", Font.PLAIN, 18));
		joinNamePanel.add(joinNameInputField);
		joinNameInputField.setColumns(10);
		
		JPanel joinGenderPanel = new JPanel();
		getContentPane().add(joinGenderPanel);
		
		JLabel joinGenderLabel = new JLabel("�� �� : ");
		joinGenderLabel.setFont(new Font("����", Font.PLAIN, 18));
		joinGenderPanel.add(joinGenderLabel);
		
		bg = new ButtonGroup();
		
		JRadioButton manRadioButton = new JRadioButton("����");
		manRadioButton.setFont(new Font("����", Font.PLAIN, 18));
		joinGenderPanel.add(manRadioButton);
		
		JLabel lblNewLabel = new JLabel("      ");
		joinGenderPanel.add(lblNewLabel);
		
		JRadioButton womanRadioButton = new JRadioButton("����");
		womanRadioButton.setFont(new Font("����", Font.PLAIN, 18));
		joinGenderPanel.add(womanRadioButton);
		 bg.add(manRadioButton); bg.add(manRadioButton);
		
		JPanel joinAgePanel = new JPanel();
		getContentPane().add(joinAgePanel);
		
		JLabel joinAgeLabel = new JLabel("�� �� : ");
		joinAgeLabel.setFont(new Font("����", Font.PLAIN, 18));
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
		joinAgeInputField.setFont(new Font("����", Font.PLAIN, 18));
		joinAgePanel.add(joinAgeInputField);
		joinAgeInputField.setColumns(10);
		
		JPanel joinButtonPanel = new JPanel();
		getContentPane().add(joinButtonPanel);
		
		JButton joinButton = new JButton("�� ��");
		joinButton.setFont(new Font("����", Font.PLAIN, 18));
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
		
		// �̸� 8��, ����  999 ������ �־�� �Ѵ�.
		joinButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// ����ó���� �� �־�� �Ѵ�.
				// age ���ڰ� ���� ���
				String name = joinNameInputField.getText();
				String gender;
				int age = 0;
				if(joinAgeInputField.getText().length() > 1)
				{
					age = Integer.parseInt(joinAgeInputField.getText()); 
				}
				
				
				
				if(name.length() < 1)
				{
					name = "�̸�����";
				}
				
				if(manRadioButton.isSelected())
				{
					gender = "����";
				}
				else if (womanRadioButton.isSelected())
				{
					gender = "����";
				}
				else
				{
					gender = "Ư��";
				}
				
				new PersonServerManager().putPerson(name, gender, age);
				
				//call.check(true);
				dispose();
				new PersonManager();
			}
		});
	}
	
}
