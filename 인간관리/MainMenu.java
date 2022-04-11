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
	JPanel mainPanel;	// 패널들을 관리하는 부모 패널
	JPanel titlePanel;	// 타이틀을 가지고 있는 패널
	JPanel inputPanel;	// 입력 받는 부분의 패널
	JPanel checkPanel;
	JPanel buttonPanel;	// 버튼 그룹이 있는 패널
	JLabel title;		// 타이틀
	JLabel caution;		// 주의 사항
	JPanel idPanel;
	JPanel pwPanel;
	JTextField id;		// id 입력 부분
	JPasswordField pw;		// pw 입력 부분
	JLabel idText;		
	JLabel pwText;
	JLabel checkText;
	JButton loginBtn;	// 로그인 버튼
	JButton acountBtn;	// 관리자 관리 버튼
	JButton personCollectBtn;
	
	boolean isManager = false;
	
	SaveAdmin saveAdmin;
	
	MainMenu()
	{
		
		setTitle("관리자 로그인");
		
		mainPanel = new JPanel();
		titlePanel = new JPanel();
		inputPanel = new JPanel();
		buttonPanel = new JPanel();
		
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
		inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 30));
		
		
		title = new JLabel("관리자 로그인");
		title.setFont(new Font("돋움", Font.BOLD, 54));
		title.setForeground(Color.blue);
		
		idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10 ,0));
		pwPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10 ,0));
		checkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,1000 ,0));
		idText = new JLabel("관리자     ID : ");
		pwText = new JLabel("관리자 PW : ");
		id = new JTextField(10);
		pw = new JPasswordField(10);
		loginBtn = new JButton("로그인");
		acountBtn = new JButton("관리자 설정");
		checkText = new JLabel("로그인 결과 확인");
		personCollectBtn = new JButton("인간 관리");
		
		
		loginBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				// text 가지고 와서 비교해야 한다.
				// 사용할 필드 : checkText,id, pw, 
				
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
					JOptionPane.showMessageDialog(null, "관리자만 접근할 수 있습니다.", "관리자 확인", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		personCollectBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(isManager)
				{
					// 인간 관리 창으로
					dispose();
					new PersonManager();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "관리자만 접근할 수 있습니다.", "관리자 확인", JOptionPane.WARNING_MESSAGE);
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
	
	// 텍스트 필드에서 받은 String 값을 이용하여 로그이이 가능 한지 판단한다.
	// 서버에 있는 관리자 정보를 이용하여 로그인 한다.
	public void loginCheck(String getId, String getPw)
	{
		if(getId.length() > 3)
		{
			if(new Acount().idCheck(getId))
			{
				if(new Acount().pwCheck(getPw))
				{
					// 로그인 성공
					checkText.setText("로그인 성공");
					//saveAdmin.save(true, getId , getPw);
					isManager = true;
				}
				else
				{
					checkText.setText("비밀 번호를 대시 확인하세요");
				}
			}
			else
			{
				checkText.setText("해당하는 ID는 존재 하지 않습니다.");
			}
		}
		else
		{
			checkText.setText("입력하신 ID는 너무 짧습니다.");
		}
	}
}
