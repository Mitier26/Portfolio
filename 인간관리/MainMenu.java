package SWING2;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainMenu extends JFrame
{
	JPanel mainPanel;	// �гε��� �����ϴ� �θ� �г�
	JPanel titlePanel;	// Ÿ��Ʋ�� ������ �ִ� �г�
	JPanel inputPanel;	// �Է� �޴� �κ��� �г�
	JPanel checkPanel;
	JPanel buttonPanel;	// ��ư �׷��� �ִ� �г�
	JLabel title;		// Ÿ��Ʋ
	JLabel caution;		// ���� ����
	JPanel idPanel;
	JPanel pwPanel;
	JTextField id;		// id �Է� �κ�
	JPasswordField pw;		// pw �Է� �κ�
	JLabel idText;		
	JLabel pwText;
	JLabel checkText;
	JButton loginBtn;	// �α��� ��ư
	JButton acountBtn;	// ������ ���� ��ư
	JButton personCollectBtn;
	
	boolean isManager = false;
	
	SaveAdmin saveAdmin;
	
	MainMenu()
	{
		
		setTitle("������ �α���");
		
		mainPanel = new JPanel();
		titlePanel = new JPanel();
		inputPanel = new JPanel();
		buttonPanel = new JPanel();
		
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
		inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 30));
		
		
		title = new JLabel("������ �α���");
		title.setFont(new Font("����", Font.BOLD, 54));
		title.setForeground(Color.blue);
		
		idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10 ,0));
		pwPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10 ,0));
		checkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,1000 ,0));
		idText = new JLabel("������     ID : ");
		pwText = new JLabel("������ PW : ");
		id = new JTextField(10);
		pw = new JPasswordField(10);
		loginBtn = new JButton("�α���");
		acountBtn = new JButton("������ ����");
		checkText = new JLabel("�α��� ��� Ȯ��");
		personCollectBtn = new JButton("�ΰ� ����");
		
		
		loginBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// text ������ �ͼ� ���ؾ� �Ѵ�.
				// ����� �ʵ� : checkText,id, pw, 
				
				loginCheck(id.getText(), new String(pw.getPassword()));
			}
		});
		
		acountBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(isManager)
				{
					dispose();
					
					new AdminMenu();
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "�����ڸ� ������ �� �ֽ��ϴ�.", "������ Ȯ��", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		personCollectBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(isManager)
				{
					// �ΰ� ���� â����
					dispose();
					new PersonManager();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "�����ڸ� ������ �� �ֽ��ϴ�.", "������ Ȯ��", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		idPanel.add(idText); idPanel.add(id);
		pwPanel.add(pwText); pwPanel.add(pw);
		checkPanel.add(checkText);
		inputPanel.add(idPanel); inputPanel.add(pwPanel);inputPanel.add(checkPanel);
		
		buttonPanel.add(loginBtn); 
		buttonPanel.add(personCollectBtn);
		buttonPanel.add(acountBtn);
		
		titlePanel.add(title);
		
		mainPanel.add(titlePanel);mainPanel.add(inputPanel); mainPanel.add(buttonPanel);
		getContentPane().add(mainPanel);
		
		setSize(600, 600);
		setResizable(false);
		setVisible(true);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing (WindowEvent e)
			{
				new EndScreen();
			}
		});
	}
	
	// �ؽ�Ʈ �ʵ忡�� ���� String ���� �̿��Ͽ� �α����� ���� ���� �Ǵ��Ѵ�.
	// ������ �ִ� ������ ������ �̿��Ͽ� �α��� �Ѵ�.
	public void loginCheck(String getId, String getPw)
	{
		if(getId.length() > 3)
		{
			if(new Acount().idCheck(getId))
			{
				if(new Acount().pwCheck(getPw))
				{
					// �α��� ����
					checkText.setText("�α��� ����");
					//saveAdmin.save(true, getId , getPw);
					isManager = true;
				}
				else
				{
					checkText.setText("��� ��ȣ�� ��� Ȯ���ϼ���");
				}
			}
			else
			{
				checkText.setText("�ش��ϴ� ID�� ���� ���� �ʽ��ϴ�.");
			}
		}
		else
		{
			checkText.setText("�Է��Ͻ� ID�� �ʹ� ª���ϴ�.");
		}
	}
}
