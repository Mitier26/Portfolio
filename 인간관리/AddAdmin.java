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
		setTitle("������ �߰�");
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
		
		idtextField = new HintTextField("3���� �̻� �Է��ϼ���");
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
		
		lblNewLabel_3 = new JLabel("��й�ȣ�� 4�� �̻� �Է��ϼ���");
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
		emailComboBox.setModel(new DefaultComboBoxModel(new String[] {"�̸��� ����", "@naver.com", "@daum.net", "@google.com", "@yahoo.con", "�����Է�"}));
		panel_2.add(emailComboBox);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Phone", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 3;
		contentPane.add(panel_3, gbc_panel_3);
		
		// �ؽ�Ʈ �ʵ��� ���� �����ϱ�
		DecimalFormat decimalFormat = new DecimalFormat("0000");
		//NumberFormatter numberFormat = new NumberFormatter();
		//numberFormat.setValueClass(Integer.class);
		//numberFormat.setMinimum(1000);
		//numberFormat.setMaximum(9999);
		
		phoneComboBox = new JComboBox();
		phoneComboBox.setModel(new DefaultComboBoxModel(new String[] {"����", "010", "018", "��Ÿ"}));
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
		
		JButton addButton = new JButton("������ ���");
		panel_4.add(addButton);
		
		// �̸��� �޺� �ڽ� ����
		emailComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String text = emailtextField.getText();
				if(text.contains("@"))	// @ �� �ִٸ�
				{
					// @�� �������� �յڸ� �߶� ���� �͸� �����.
					// @�� �������� �ڸ��� �迭�� �ִ´�.
					String input[] = text.split("@");
					// �պκи� �����.
					if(!emailComboBox.getSelectedItem().equals("�����Է�")) 
					{
						emailtextField.setText(input[0]+emailComboBox.getSelectedItem());
					}
					else
					{
						emailtextField.setText(input[0]);
					}
				}
				else	// �Էµ� ���� ������.
				{
					if(emailComboBox.getSelectedItem().equals("�����Է�")) 
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
		
		// ��ȭ ��ȣ �޺� �ڽ� ����
		phoneComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// ������ ���� "��Ÿ" ���� Ȯ��
				if(phoneComboBox.getSelectedItem().equals("��Ÿ"))
				{
					phonetextField1.setText("");
				}
				else
				{
					phonetextField1.setText(phoneComboBox.getSelectedItem().toString());
				}
				
			}
		});
		
		// ������ �߰� ��ư
		addButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String id = idtextField.getText();
				String pw = new String(passwordField.getPassword());
				String pw1 = new String(passwordField_1.getPassword());
				String email = emailtextField.getText();
				String phone = phonetextField1.getText()+phonetextField2.getText()+phonetextField3.getText();
				
				// ���� ���� ������ ���� ���� ó���� �ؾ� �Ѵ�.
				
				
				// �̸��ϰ� ��ȭ��ȣ�� �ʼ��� �� ���ΰ�?
				// null �� ����� ���ΰ�?
				if(id.length() >= 3)	// ID ���� üũ
				{
					if(!acount.idCheck(id))	// ������ ���� ID�� �ִ��� Ȯ��
					{
						if(pw.equals(pw1))	// �Է��� password �� ���� ���� Ȯ��
						{
							if(pw.length()<=4)	// �Է��� password�� 4�� �����̸�
							{
								// ª�� ��й�ȣ�� ���ȿ� ����մϴ�.
								JOptionPane.showMessageDialog(panel, "ª�� ��й�ȣ�� ���ȿ� ����մϴ�.","���Ȱ��", JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								// ������ ����
								new Acount().InputData(id, pw1, email, phone);
								JOptionPane.showMessageDialog(panel, "�����ڸ� ����߽��ϴ�.","����", JOptionPane.INFORMATION_MESSAGE);
								
								//////////////////////////////////////////////////////////////////////////
								// ����������.........
								parent.getParent().setVisible(false);
								parent.getParent().revalidate();
								parent.getParent().repaint();
								parent.getParent().setVisible(true);
								
								dispose();
								
							}
						}
						else
						{							
							// �Է��� 2���� ��й�ȣ�� �ٸ���
							JOptionPane.showMessageDialog(panel, "�Է��� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.","��й�ȣ ����", JOptionPane.WARNING_MESSAGE);
						}
					}
					else
					{						
						// ������ ���̵� �ִ�.
						JOptionPane.showMessageDialog(panel, "���Ͼ� ID �� �����մϴ�.","ID ����", JOptionPane.WARNING_MESSAGE);
					}
					
				}
				else
				{
					// ���̵� �ʹ� ª��.
					JOptionPane.showMessageDialog(panel, "ID�� �ʹ� ª���ϴ�.","ID ����", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
}
