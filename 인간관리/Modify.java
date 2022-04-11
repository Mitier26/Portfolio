package SWING2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

public final class Modify extends JFrame 
{
	Acount acount;
	boolean isUsedId = true;
	boolean isPwCheck = false;
	private JTextField currentId;
	private JTextField inputNewId;
	private JPasswordField currentPwField;
	private JPasswordField newPasswordField1;
	private JPasswordField newPasswordField2;
	private JTextField newEmailField;
	private JTextField newPhoneField;
	
	static String currentAdmin;
	
	Modify(Object o) 
	{
		acount = new Acount();
		currentAdmin = (String)o;
		
		setTitle("@title@");
		
		
		//super(new BorderLayout());

		//setBorder(new TitledBorder(null, "������ ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setPreferredSize(new Dimension(500, 500));
    
		Font textFont = new Font("����", Font.PLAIN, 12);
		
		JLabel titleLable = new JLabel("������ ����");
		titleLable.setFont(new Font("����", Font.BOLD, 24));
		titleLable.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(titleLable, BorderLayout.NORTH);
    
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{488, 0};
		gbl_mainPanel.rowHeights = new int[] {89, 141, 48, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0};
		mainPanel.setLayout(gbl_mainPanel);
    
		JPanel idPanel = new JPanel();
		idPanel.setBorder(new TitledBorder(null, "ID", TitledBorder.LEADING, TitledBorder.TOP, textFont, null));
		GridBagConstraints gbc_idPanel = new GridBagConstraints();
		gbc_idPanel.fill = GridBagConstraints.BOTH;
		gbc_idPanel.insets = new Insets(0, 0, 5, 0);
		gbc_idPanel.gridx = 0;
    	gbc_idPanel.gridy = 0;
    	mainPanel.add(idPanel, gbc_idPanel);
    
    	GridBagLayout gbl_idPanel = new GridBagLayout();
    	gbl_idPanel.columnWidths = new int[] {470, 0};
    	gbl_idPanel.rowHeights = new int[] {25, 25, 0};
    	gbl_idPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
    	gbl_idPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    	idPanel.setLayout(gbl_idPanel);
    
    	JPanel idCheckPanel = new JPanel();
    	GridBagConstraints gbc_idCheckPanel = new GridBagConstraints();
    	gbc_idCheckPanel.anchor = GridBagConstraints.NORTH;
    	gbc_idCheckPanel.fill = GridBagConstraints.HORIZONTAL;
    	gbc_idCheckPanel.insets = new Insets(0, 0, 5, 0);
    	gbc_idCheckPanel.gridx = 0;
    	gbc_idCheckPanel.gridy = 0;
    	idPanel.add(idCheckPanel, gbc_idCheckPanel);
    
    	JLabel idCheckLabel = new JLabel("���̵� �ߺ� Ȯ��");
    	idCheckLabel.setFont(new Font("����", Font.PLAIN, 12));
    	idCheckPanel.add(idCheckLabel);
    
	    JPanel idInputPanel = new JPanel();
	    GridBagConstraints gbc_idInputPanel = new GridBagConstraints();
	    gbc_idInputPanel.fill = GridBagConstraints.BOTH;
	    gbc_idInputPanel.gridx = 0;
	    gbc_idInputPanel.gridy = 1;
	    idPanel.add(idInputPanel, gbc_idInputPanel);
	    GridBagLayout gbl_idInputPanel = new GridBagLayout();
	    gbl_idInputPanel.columnWidths = new int[] {160, 25, 160, 30, 100};
	    gbl_idInputPanel.rowHeights = new int[] {25, 0};
	    gbl_idInputPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
	    gbl_idInputPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	    idInputPanel.setLayout(gbl_idInputPanel);
    
	    currentId = new JTextField();
	    currentId.setEditable(false);
	    currentId.setColumns(10);
	    currentId.setText((String)currentAdmin);
	    GridBagConstraints gbc_currentId = new GridBagConstraints();
	    gbc_currentId.insets = new Insets(0, 0, 0, 5);
	    gbc_currentId.fill = GridBagConstraints.HORIZONTAL;
	    gbc_currentId.gridx = 0;
	    gbc_currentId.gridy = 0;
	    idInputPanel.add(currentId, gbc_currentId);
	    
	    JLabel nextLabel = new JLabel("->");
	    GridBagConstraints gbc_nextLabel = new GridBagConstraints();
	    gbc_nextLabel.insets = new Insets(0, 0, 0, 5);
	    gbc_nextLabel.gridx = 1;
	    gbc_nextLabel.gridy = 0;
	    idInputPanel.add(nextLabel, gbc_nextLabel);
	    
	    inputNewId = new JTextField();
	    inputNewId.setColumns(10);
	    GridBagConstraints gbc_inputNewId = new GridBagConstraints();
	    gbc_inputNewId.fill = GridBagConstraints.HORIZONTAL;
	    gbc_inputNewId.insets = new Insets(0, 0, 0, 5);
	    gbc_inputNewId.gridx = 2;
	    gbc_inputNewId.gridy = 0;
	    idInputPanel.add(inputNewId, gbc_inputNewId);
	    
	    JLabel confirmIdLabel = new JLabel(" : ");
	    GridBagConstraints gbc_confirmIdLabel = new GridBagConstraints();
	    gbc_confirmIdLabel.insets = new Insets(0, 0, 0, 5);
	    gbc_confirmIdLabel.gridx = 3;
	    gbc_confirmIdLabel.gridy = 0;
	    idInputPanel.add(confirmIdLabel, gbc_confirmIdLabel);
	    
	    JButton confirmIdButton = new JButton("ID Ȯ��");
	    GridBagConstraints gbc_confirmButton = new GridBagConstraints();
	    gbc_confirmButton.fill = GridBagConstraints.HORIZONTAL;
	    gbc_confirmButton.anchor = GridBagConstraints.NORTH;
	    gbc_confirmButton.gridx = 4;
	    gbc_confirmButton.gridy = 0;
	    idInputPanel.add(confirmIdButton, gbc_confirmButton);
	    
	    JPanel pwPanel = new JPanel();
	    pwPanel.setBorder(new TitledBorder(null, "PW", TitledBorder.LEADING, TitledBorder.TOP, textFont, null));
	    GridBagConstraints gbc_pwPanel = new GridBagConstraints();
	    gbc_pwPanel.insets = new Insets(0, 0, 5, 0);
	    gbc_pwPanel.fill = GridBagConstraints.BOTH;
	    gbc_pwPanel.gridx = 0;
	    gbc_pwPanel.gridy = 1;
	    mainPanel.add(pwPanel, gbc_pwPanel);
	    GridBagLayout gbl_pwPanel = new GridBagLayout();
	    gbl_pwPanel.columnWidths = new int[]{470, 0};
	    gbl_pwPanel.rowHeights = new int[] {25, 60, 0};
	    gbl_pwPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
	    gbl_pwPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	    pwPanel.setLayout(gbl_pwPanel);
	    
	    JPanel confirmCurrentPwPanel = new JPanel();
	    confirmCurrentPwPanel.setBorder(new TitledBorder(null, "���� PW Ȯ��", TitledBorder.LEADING, TitledBorder.TOP, textFont, null));
	    GridBagConstraints gbc_confirmCurrentPwPanel = new GridBagConstraints();
	    gbc_confirmCurrentPwPanel.anchor = GridBagConstraints.WEST;
	    gbc_confirmCurrentPwPanel.fill = GridBagConstraints.VERTICAL;
	    gbc_confirmCurrentPwPanel.insets = new Insets(0, 0, 5, 0);
	    gbc_confirmCurrentPwPanel.gridx = 0;
	    gbc_confirmCurrentPwPanel.gridy = 0;
	    pwPanel.add(confirmCurrentPwPanel, gbc_confirmCurrentPwPanel);
	    GridBagLayout gbl_confirmCurrentPwPanel = new GridBagLayout();
	    gbl_confirmCurrentPwPanel.columnWidths = new int[] {232, 153, 0};
	    gbl_confirmCurrentPwPanel.rowHeights = new int[] {25, 0};
	    gbl_confirmCurrentPwPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
	    gbl_confirmCurrentPwPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	    confirmCurrentPwPanel.setLayout(gbl_confirmCurrentPwPanel);
	    
	    currentPwField = new JPasswordField();
	    GridBagConstraints gbc_currentPwField = new GridBagConstraints();
	    gbc_currentPwField.fill = GridBagConstraints.BOTH;
	    gbc_currentPwField.insets = new Insets(0, 0, 0, 5);
	    gbc_currentPwField.gridx = 0;
	    gbc_currentPwField.gridy = 0;
	    confirmCurrentPwPanel.add(currentPwField, gbc_currentPwField);
	    
	    JCheckBox pwCheckBox = new JCheckBox("");
	    pwCheckBox.setEnabled(false);
	    GridBagConstraints gbc_pwCheckBox = new GridBagConstraints();
	    gbc_pwCheckBox.insets = new Insets(0, 0, 0, 5);
	    gbc_pwCheckBox.gridx = 1;
	    gbc_pwCheckBox.gridy = 0;
	    confirmCurrentPwPanel.add(pwCheckBox, gbc_pwCheckBox);
	    
	    JButton currentPwCheckButton = new JButton("PW Ȯ��");
	    GridBagConstraints gbc_currentPwCheckButton = new GridBagConstraints();
	    gbc_currentPwCheckButton.fill = GridBagConstraints.BOTH;
	    gbc_currentPwCheckButton.gridx = 2;
	    gbc_currentPwCheckButton.gridy = 0;
	    confirmCurrentPwPanel.add(currentPwCheckButton, gbc_currentPwCheckButton);
	    
	    JPanel newPwPanel = new JPanel();
	    newPwPanel.setBorder(new TitledBorder(null, "PW ����", TitledBorder.LEADING, TitledBorder.TOP, textFont, null));
	    GridBagConstraints gbc_newPwPanel = new GridBagConstraints();
	    gbc_newPwPanel.fill = GridBagConstraints.BOTH;
	    gbc_newPwPanel.gridx = 0;
	    gbc_newPwPanel.gridy = 1;
	    pwPanel.add(newPwPanel, gbc_newPwPanel);
	    GridBagLayout gbl_newPwPanel = new GridBagLayout();
	    gbl_newPwPanel.columnWidths = new int[]{232, 232, 0};
	    gbl_newPwPanel.rowHeights = new int[]{21, 21, 0};
	    gbl_newPwPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	    gbl_newPwPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	    newPwPanel.setLayout(gbl_newPwPanel);
	    
	    newPasswordField1 = new JPasswordField();
	    GridBagConstraints gbc_newPasswordField1 = new GridBagConstraints();
	    gbc_newPasswordField1.fill = GridBagConstraints.BOTH;
	    gbc_newPasswordField1.insets = new Insets(0, 0, 5, 5);
	    gbc_newPasswordField1.gridx = 0;
	    gbc_newPasswordField1.gridy = 0;
	    newPwPanel.add(newPasswordField1, gbc_newPasswordField1);
	    
	    newPasswordField2 = new JPasswordField();
	    GridBagConstraints gbc_newPasswordField2 = new GridBagConstraints();
	    gbc_newPasswordField2.fill = GridBagConstraints.BOTH;
	    gbc_newPasswordField2.insets = new Insets(0, 0, 0, 5);
	    gbc_newPasswordField2.gridx = 0;
	    gbc_newPasswordField2.gridy = 1;
	    newPwPanel.add(newPasswordField2, gbc_newPasswordField2);
	    
	    JPanel emailPanel = new JPanel();
	    emailPanel.setBorder(new TitledBorder(null, "E-mail", TitledBorder.LEADING, TitledBorder.TOP, textFont, null));
	    GridBagConstraints gbc_emailPanel = new GridBagConstraints();
	    gbc_emailPanel.insets = new Insets(0, 0, 5, 0);
	    gbc_emailPanel.fill = GridBagConstraints.BOTH;
	    gbc_emailPanel.gridx = 0;
	    gbc_emailPanel.gridy = 2;
	    mainPanel.add(emailPanel, gbc_emailPanel);
	    GridBagLayout gbl_emailPanel = new GridBagLayout();
	    gbl_emailPanel.columnWidths = new int[] {232, 7, 0};
	    gbl_emailPanel.rowHeights = new int[] {25};
	    gbl_emailPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	    gbl_emailPanel.rowWeights = new double[]{0.0};
	    emailPanel.setLayout(gbl_emailPanel);
	    
	    newEmailField = new JTextField();
	    GridBagConstraints gbc_newEmailField = new GridBagConstraints();
	    gbc_newEmailField.fill = GridBagConstraints.BOTH;
	    gbc_newEmailField.insets = new Insets(0, 0, 0, 5);
	    gbc_newEmailField.gridx = 0;
	    gbc_newEmailField.gridy = 0;
	    emailPanel.add(newEmailField, gbc_newEmailField);
	    newEmailField.setColumns(10);
    
	    JPanel phonePanel = new JPanel();
	    phonePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Phone", TitledBorder.LEADING, TitledBorder.TOP, textFont, new Color(0, 0, 0)));
	    GridBagConstraints gbc_phonePanel = new GridBagConstraints();
	    gbc_phonePanel.insets = new Insets(0, 0, 5, 0);
	    gbc_phonePanel.fill = GridBagConstraints.BOTH;
	    gbc_phonePanel.gridx = 0;
	    gbc_phonePanel.gridy = 3;
	    mainPanel.add(phonePanel, gbc_phonePanel);
	    GridBagLayout gbl_phonePanel = new GridBagLayout();
	    gbl_phonePanel.columnWidths = new int[] {232, 57, 0};
	    gbl_phonePanel.rowHeights = new int[] {25};
	    gbl_phonePanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	    gbl_phonePanel.rowWeights = new double[]{0.0};
	    phonePanel.setLayout(gbl_phonePanel);
	    
	    newPhoneField = new JTextField();
	    GridBagConstraints gbc_newPhoneField = new GridBagConstraints();
	    gbc_newPhoneField.fill = GridBagConstraints.BOTH;
	    gbc_newPhoneField.insets = new Insets(0, 0, 0, 5);
	    gbc_newPhoneField.gridx = 0;
	    gbc_newPhoneField.gridy = 0;
	    phonePanel.add(newPhoneField, gbc_newPhoneField);
	    newPhoneField.setColumns(10);
	    
	    JPanel comfirmPanel = new JPanel();
	    GridBagConstraints gbc_comfirmPanel = new GridBagConstraints();
	    gbc_comfirmPanel.insets = new Insets(0, 0, 5, 0);
	    gbc_comfirmPanel.fill = GridBagConstraints.VERTICAL;
	    gbc_comfirmPanel.gridx = 0;
	    gbc_comfirmPanel.gridy = 4;
	    mainPanel.add(comfirmPanel, gbc_comfirmPanel);
	    GridBagLayout gbl_comfirmPanel = new GridBagLayout();
	    gbl_comfirmPanel.columnWidths = new int[]{122, 122, 122, 0};
	    gbl_comfirmPanel.rowHeights = new int[] {50};
	    gbl_comfirmPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	    gbl_comfirmPanel.rowWeights = new double[]{0.0};
	    comfirmPanel.setLayout(gbl_comfirmPanel);
	    
	    JButton cancelButton = new JButton("�� ��");
	    GridBagConstraints gbc_cancelButton = new GridBagConstraints();
	    gbc_cancelButton.fill = GridBagConstraints.BOTH;
	    gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
	    gbc_cancelButton.gridx = 0;
	    gbc_cancelButton.gridy = 0;
	    comfirmPanel.add(cancelButton, gbc_cancelButton);
	    
	    JLabel lblNewLabel_6 = new JLabel("");
	    GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
	    gbc_lblNewLabel_6.fill = GridBagConstraints.BOTH;
	    gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
	    gbc_lblNewLabel_6.gridx = 1;
	    gbc_lblNewLabel_6.gridy = 0;
	    comfirmPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
	    
	    JButton changeButton = new JButton("�� ��");
	    GridBagConstraints gbc_changeButton = new GridBagConstraints();
	    gbc_changeButton.fill = GridBagConstraints.BOTH;
	    gbc_changeButton.gridx = 2;
	    gbc_changeButton.gridy = 0;
	    comfirmPanel.add(changeButton, gbc_changeButton);
	    
	    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	    
	    ////////////////////////////////////////////////////////
	    // Buttons Listener
	    confirmIdButton.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		// �������� ������ ������ ���̵� �ִٸ� "���� ID �� ���� �Ѵ�"
	    		// �Է��� ���ϰų� ���� ID�̸� �������� �ʴ´�.
	    		if(inputNewId.getText().length() >= 3)
	    		{
	    			if(acount.idCheck(inputNewId.getText()))
		    		{
		    			// ���� ID ����
		    			//JOptionPane.showMessageDialog(mainPanel, "���Ͼ� ID �� �����մϴ�.","ID ����", JOptionPane.WARNING_MESSAGE);
		    			idCheckLabel.setText("������ ID�� ���� �մϴ�.");
		    			idCheckLabel.setForeground(Color.red);
		    			isUsedId = true;
		    		}
		    		else
		    		{
		    			idCheckLabel.setText("��� ������ ID �Դϴ�.");
		    			idCheckLabel.setForeground(Color.green);
		    			isUsedId = false;
		    		}
	    		}
	    		else
	    		{
	    			idCheckLabel.setText("ID�� �ʹ� ª���ϴ�.");
	    			idCheckLabel.setForeground(Color.orange);
	    			isUsedId = true;
	    		}
	    		
	    	}
	    });
	    
	    currentPwCheckButton.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent e) 
			{
				// ��� ��ȣ Ȯ���� ���� ��
				// üũ �ڽ��� ����
				
				if(acount.pwCheck(new String(currentPwField.getPassword())))
				{
					// ����� ���� , ����
					isPwCheck = true;
				}
				else
				{
					isPwCheck = false;
					JOptionPane.showMessageDialog(mainPanel, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "��й�ȣ ����", JOptionPane.WARNING_MESSAGE);
				}
				
				pwCheckBox.setSelected(isPwCheck);
			}
		});
	    
	    cancelButton.addActionListener(new ActionListener() 
	    {
			public void actionPerformed(ActionEvent e) 
			{
				// ��� ��ư
				dispose();
			}
		});
	    
	    changeButton.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		String inputId = inputNewId.getText();
		    	String inputPw1 = new String(newPasswordField1.getPassword());
		    	String inputPw2 = new String(newPasswordField2.getPassword());
		    	String inputEmail = newEmailField.getText();
		    	String inputPhone = newPhoneField.getText();
		    	
	    		// ���� ��ư
	    		if(!isUsedId)
	    		{
	    			// ���̵� ��� ����
	    			if(isPwCheck)
	    			{
	    				// ���� ��й�ȣ ��ġ
	    				if(inputPw1.equals(inputPw2))
	    				{
	    					// ���ο� ��й�ȣ�� ���� ��ġ
	    					// �����͸� �����Ѵ�.
	    					// �̸��ϰ� ��ȭ��ȣ�� �Է����� ������ ������ ������ �Ѵ�.
	    					// ID�� ���� �����ϸ� �ȵ�.
	    					// ������ ������
	    					if(inputPhone.length() < 1)
	    					{
	    						// ��ȭ��ȣ �Է��� ���� ��
	    						try 
	    						{
									inputPhone = new Acount().getPhone(currentAdmin);
	    							
								} 
	    						catch (Exception e1) 
	    						{
									System.out.println("��ȭ��ȣ ���� �� ���� �߻� : " + e1);
								}
	    					}
	    					if(inputEmail.length() < 1)
	    					{
	    						// �̸��� �Է��� ���Ѱ�
	    						// ������ ID�� ��ȭ ��ȣ�� �Է��Ѵ�.
	    						//String a = currentAdmin;
	    						try 
	    						{
									inputEmail = new Acount().getEmail(currentAdmin);
								} 
	    						catch (Exception e1) 
	    						{
									System.out.println("�̸��� ���� �� ���� �߻� : " + e1);
								}
	    					
	    					}
	    					
	    					// ���� ������ ������ �Ѵ�.
	    					acount.updateData(currentAdmin, inputId, inputPw1, inputEmail, inputPhone);
	    					JOptionPane.showMessageDialog(mainPanel, "�����͸� �����߽��ϴ�.", "������ ����", JOptionPane.WARNING_MESSAGE);
	    					dispose();
	    					
	    				}
	    				else
	    				{
	    					// ���ο� ��й�ȣ�� ���� ����ġ
	    					JOptionPane.showMessageDialog(mainPanel, "��й�ȣ�� ���� ���� �ʽ��ϴ�..", "��й�ȣ ����", JOptionPane.WARNING_MESSAGE);
	    				}
	    			}
	    			else
	    			{
	    				// ���� ��й�ȣ ����ġ
	    				JOptionPane.showMessageDialog(mainPanel, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "��й�ȣ ����", JOptionPane.WARNING_MESSAGE);
	    			}
	    		}
	    		else
	    		{
	    			// ���̵� ��� �Ұ���
	    			JOptionPane.showMessageDialog(mainPanel, "�ش� ID�� ������Դϴ�..", "ID ����", JOptionPane.WARNING_MESSAGE);
	    		}
	    	}
	    });// ������ ���� ��ư
	    
	    
	    
	    
	} // ������

}