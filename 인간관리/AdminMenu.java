package SWING2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMenu extends JFrame
{
	JLabel title;
	JPanel adminsPanel;
	private JTable table;
	JButton addAdminButton;

	AdminMenu()
	{
		setTitle("���� ����");

		title = new JLabel("������ ����");
		title.setFont(new Font("�ü�", Font.BOLD, 46));
		title.setForeground(Color.pink);
		title.setHorizontalAlignment(JLabel.CENTER);

		adminsPanel = new JPanel();
		adminsPanel.setBackground(Color.GRAY);

		setSize(600, 600);

		// Ÿ�̺� ��
		String header[] = {"No","ID", "E-mail", "Phone", "Date", "ETC" };

		// �� �� ���¸� �Էµ� ���·� �����Ѵ�.
		DefaultTableModel model = new DefaultTableModel(header, 0)
		{
			public Class<?> getColumnClass(int column)
			{
				return getValueAt(0, column).getClass();
			}
		};



		// ���̺�� ������ ���� ���� �Ӽ��� �����ϴ� ��
		table = new JTable(model)
		{

			//�������̵� �ϴ� UI�� ������Ʈ �ϴ� ��
			public void updateUI()
			{
				super.updateUI();
				setRowHeight(36);	// ��, ���� ĭ�� ���� ����
				setAutoCreateRowSorter(true);	//���� �ڵ� ���� ���

				// ���̺� �� �� �ȿ� ��ư �׷��� �ִ� ��
				// ��ư�� ���� ���� ��ġ 5
				TableColumn column = getColumnModel().getColumn(5);
				//column.setPreferredWidth(100);
				column.setCellRenderer(new ButtonsRenderer1());
				column.setCellEditor(new ButtonsEditor1(this));

			}
		};
		// �����͸� ���� ũ�Ⱑ �������� ���� �޾ƿ;� �Ѵ�.
		showAdmin();

		for (int i = 0; i < table.getColumnCount(); i++)
		{


			TableColumn tableColumn = table.getColumnModel().getColumn(i);
			int preferredWidth = tableColumn.getMinWidth();
			int maxWidth = tableColumn.getMaxWidth();

			for (int row = 0; row < table.getRowCount(); row++)
			{
				TableCellRenderer cellRenderer = table.getCellRenderer(row, i);
				Component c = table.prepareRenderer(cellRenderer, row, i);
				int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
				preferredWidth = Math.max(preferredWidth, width);

				//  We've exceeded the maximum width, no need to check other rows

				if (preferredWidth >= maxWidth)
				{
					preferredWidth = maxWidth;
					break;
				}
			}

			tableColumn.setPreferredWidth( preferredWidth );
		}

		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);	
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setFont(new Font("����", Font.PLAIN, 12));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(560, 450));
		scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
		adminsPanel.add(scrollPane, BorderLayout.CENTER);

		addAdminButton = new JButton("������ �߰�");
		addAdminButton.setFont(new Font("����", Font.BOLD, 22));
		addAdminButton.setPreferredSize(new Dimension(100, 50));

		addAdminButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new AddAdmin(adminsPanel);
			}
		});


		add(title, BorderLayout.NORTH);
		add(adminsPanel, BorderLayout.CENTER);
		add(addAdminButton, BorderLayout.SOUTH);

		//pack();
		setVisible(true);
		setLayout(new BorderLayout(0,0));
		setResizable(false);
		setLocationRelativeTo(null);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				new MainMenu();
				dispose();
			}
		});

	}

	public void showAdmin()
	{
		String empty = "";
		ResultSet rs = new Acount().getAllResult();
		// ���̺� �߰��ϴ� ���
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		//row �� ����
		//int rowCount = table.getRowCount();
		// select count(*) from admin;

		try 
		{
			int rowCount = 0;
			while(rs.next())
			{
				String[] data = {rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), empty};
				model.insertRow(rowCount, data);
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("�����Ͱ� ���� " + e );
		}

	}// show admin
}

/////////////////////////////////////////////////////
class ButtonsPanel1 extends JPanel
{
	List<JButton> buttons = Arrays.asList(new JButton("M"), new JButton("D"));

	ButtonsPanel1()
	{
		super();
		setOpaque(true);
		for(JButton b : buttons)
		{
			b.setFocusable(false);;
			b.setRolloverEnabled(false);
			add(b);
		}
	}

	List<JButton> getButtons()
	{
		return buttons;
	}

}

class ButtonsRenderer1 implements TableCellRenderer 
{
	ButtonsPanel1 panel = new ButtonsPanel1()
	{
		public void updateUI()
		{
			super.updateUI();

			setName("cellRenderer");
		}
	};

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasfocus, int row, int column)
	{
		panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
		return panel;
	}
}

///////////////////////////////////////////////////
class ModifyAction extends AbstractAction
{
	JTable table;

	ModifyAction(JTable table)
	{
		super("M");
		this.table = table;
	}

	public void actionPerformed(ActionEvent e)
	{
		// ���� ��Ʈ�� ������ ���
		//System.out.println("���� ��ư");
		//JOptionPane.showMessageDialog(table, "Viewing");
		int row = table.convertRowIndexToModel(table.getEditingRow());
		Object o = table.getModel().getValueAt(row, 1);
		new Modify(o);
	}
}

// ���� ��ư
// Ȯ�� �ϴ� ���� �־�� �Ѵ�.
class DeleteAction extends AbstractAction
{
	JTable table;

	DeleteAction(JTable table)
	{
		super("D");
		this.table = table;
	}

	public void actionPerformed(ActionEvent e)
	{

		int row = table.convertRowIndexToModel(table.getEditingRow());
		Object o = table.getModel().getValueAt(row, 1);
		int result = JOptionPane.showConfirmDialog(null, o + "�����ڸ� ���� �ϰڽ��ϱ�?","����Ȯ��", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION)
		{
			new Acount().deleteData((String)o);
			JOptionPane.showMessageDialog(null,  o + "�����ڸ� ���� �߽��ϴ�", "����", JOptionPane.WARNING_MESSAGE);
		}

	}
}

////////////////////////////////////////////////////////

class ButtonsEditor1 extends AbstractCellEditor implements TableCellEditor
{
	ButtonsPanel1 panel = new ButtonsPanel1();
	JTable table;

	class EditingStopHandler extends MouseAdapter implements ActionListener
	{
		public void mousePressed(MouseEvent e)
		{
			Object o = e.getSource();

			if(o instanceof TableCellEditor)
			{
				actionPerformed(new ActionEvent(o, ActionEvent.ACTION_PERFORMED, ""));
			}
			else if(o instanceof JButton)
			{
				ButtonModel m = ((JButton) e.getComponent()).getModel();
				if(m.isPressed() && table.isRowSelected(table.getEditingRow()) && e.isControlDown())
				{
					panel.setBackground(table.getBackground());
				}
			}
		}

		public void actionPerformed(ActionEvent e)
		{
			EventQueue.invokeLater(ButtonsEditor1.this::fireEditingStopped);
		}
	}

	ButtonsEditor1(JTable table)
	{
		super();
		this.table = table;
		List<JButton> list = panel.getButtons();
		list.get(0).setAction(new ModifyAction(table));
		list.get(1).setAction(new DeleteAction(table));

		EditingStopHandler handler = new EditingStopHandler();

		for(JButton b : list)
		{
			b.addMouseListener(handler);
			b.addActionListener(handler);
		}
		panel.addMouseListener(handler);
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		panel.setBackground(table.getSelectionBackground());
		return panel;
	}

	public Object getCellEditorValue()
	{
		return "";
	}

} // ButtonEditor1
