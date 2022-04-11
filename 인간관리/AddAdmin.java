package SWING2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.Format;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AddAdmin extends JFrame {

	Acount acount;
	
	private JPanel contentPane;
	private HintTextField idtextField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField emailtextField;
	private JComboBox emailComboBox;
	private JComboBox phoneComboBox;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JFormattedTextField phonetextField1;
	private JFormattedTextField phonetextField2;
	private JFormattedTextField phonetextField3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public AddAdmin(JPanel parent) {
		
		acount = new Acount();
		
		setAlwaysOnTop(true);
		setTitle("관리자 추가");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 429);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{290, 0};
		gbl_contentPane.rowHeights = new int[] {65, 87, 87, 65, 65, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		idtextField = new HintTextField("3글자 이상 입력하세요");
		panel.add(idtextField);
		idtextField.setColumns(15);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Password", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{278, 0};
		gbl_panel_1.rowHeights = new int[] {0, 25, 25, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		lblNewLabel_3 = new JLabel("비밀번호는 4자 이상 입력하세요");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(15);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 1;
		panel_1.add(passwordField, gbc_passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(15);
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.gridx = 0;
		gbc_passwordField_1.gridy = 2;
		panel_1.add(passwordField_1, gbc_passwordField_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "E-mail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		contentPane.add(panel_2, gbc_panel_2);
		
		emailtextField = new JTextField();
		panel_2.add(emailtextField);
		emailtextField.setColumns(20);
		
		emailComboBox = new JComboBox();
		emailComboBox.setModel(new DefaultComboBoxModel(new String[] {"이메일 선택", "@naver.com", "@daum.net", "@google.com", "@yahoo.con", "직접입력"}));
		panel_2.add(emailComboBox);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Phone", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 3;
		contentPane.add(panel_3, gbc_panel_3);
		
		// 텍스트 필드의 포멧 지정하기
		DecimalFormat decimalFormat = new DecimalFormat("0000");
		//NumberFormatter numberFormat = new NumberFormatter();
		//numberFormat.setValueClass(Integer.class);
		//numberFormat.setMinimum(1000);
		//numberFormat.setMaximum(9999);
		
		phoneComboBox = new JComboBox();
		phoneComboBox.setModel(new DefaultComboBoxModel(new String[] {"선택", "010", "018", "기타"}));
		panel_3.add(phoneComboBox);
		
		lblNewLabel_2 = new JLabel(":");
		panel_3.add(lblNewLabel_2);
		phonetextField1 = new JFormattedTextField(new DecimalFormat("000"));
		panel_3.add(phonetextField1);
		phonetextField1.setColumns(3);
		
		lblNewLabel = new JLabel("-");
		panel_3.add(lblNewLabel);
		
		phonetextField2 = new JFormattedTextField(decimalFormat);
		phonetextField2.setColumns(3);
		panel_3.add(phonetextField2);
		
		lblNewLabel_1 = new JLabel("-");
		panel_3.add(lblNewLabel_1);
		
		phonetextField3 = new JFormattedTextField(decimalFormat);
		phonetextField3.setColumns(3);
		panel_3.add(phonetextField3);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 4;
		contentPane.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JButton addButton = new JButton("관리자 등록");
		panel_4.add(addButton);
		
		// 이메일 콤보 박스 설정
		emailComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String text = emailtextField.getText();
				if(text.contains("@"))	// @ 이 있다면
				{
					// @을 기준으로 앞뒤를 잘라 앞의 것만 남긴다.
					// @을 기준으로 자른고 배열에 넣는다.
					String input[] = text.split("@");
					// 앞부분만 남긴다.
					if(!emailComboBox.getSelectedItem().equals("직접입력")) 
					{
						emailtextField.setText(input[0]+emailComboBox.getSelectedItem());
					}
					else
					{
						emailtextField.setText(input[0]);
					}
				}
				else	// 입력된 것이 없으면.
				{
					if(emailComboBox.getSelectedItem().equals("직접입력")) 
					{
						emailtextField.setText(emailtextField.getText());
					}
					else
					{
						emailtextField.setText(emailtextField.getText()+emailComboBox.getSelectedItem());
					}
					
				}
			}
		});
		
		// 전화 번호 콤보 박스 설정
		phoneComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// 선택한 것이 "기타" 인지 확인
				if(phoneComboBox.getSelectedItem().equals("기타"))
				{
					phonetextField1.setText("");
				}
				else
				{
					phonetextField1.setText(phoneComboBox.getSelectedItem().toString());
				}
				
			}
		});
		
		// 관리자 추가 버튼
		addButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String id = idtextField.getText();
				String pw = new String(passwordField.getPassword());
				String pw1 = new String(passwordField_1.getPassword());
				String email = emailtextField.getText();
				String phone = phonetextField1.getText()+phonetextField2.getText()+phonetextField3.getText();
				
				// 쿼리 문을 보내기 전에 예외 처리를 해야 한다.
				
				
				// 이메일과 전화번호를 필수로 할 것인가?
				// null 을 허용할 것인가?
				if(id.length() >= 3)	// ID 길이 체크
				{
					if(!acount.idCheck(id))	// 서버에 같은 ID가 있는지 확인
					{
						if(pw.equals(pw1))	// 입력한 password 가 동일 한지 확인
						{
							if(pw.length()<=4)	// 입력한 password가 4자 이하이면
							{
								// 짧은 비밀번호는 보안에 취약합니다.
								JOptionPane.showMessageDialog(panel, "짧은 비밀번호는 보안에 취약합니다.","보안경고", JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								// 관리자 생성
								new Acount().InputData(id, pw1, email, phone);
								JOptionPane.showMessageDialog(panel, "관리자를 등록했습니다.","성공", JOptionPane.INFORMATION_MESSAGE);
								
								//////////////////////////////////////////////////////////////////////////
								// 리프레쉬가.........
								parent.getParent().setVisible(false);
								parent.getParent().revalidate();
								parent.getParent().repaint();
								parent.getParent().setVisible(true);
								
								dispose();
								
							}
						}
						else
						{							
							// 입력한 2개의 비밀번호가 다르다
							JOptionPane.showMessageDialog(panel, "입력한 비밀번호가 일치하지 않습니다.","비밀번호 오류", JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{						
						// 동일한 아이디가 있다.
						JOptionPane.showMessageDialog(panel, "동일안 ID 가 존재합니다.","ID 오류", JOptionPane.WARNING_MESSAGE);
					}
					
				}
				else
				{
					// 아이디가 너무 짧다.
					JOptionPane.showMessageDialog(panel, "ID가 너무 짧습니다.","ID 오류", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
}
