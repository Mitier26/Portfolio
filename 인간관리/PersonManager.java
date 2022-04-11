package SWING2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.text.NumberFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;



public class PersonManager extends JFrame
{
	JTable table;
	JPanel tablePanel;
	//JoinPerson join = new JoinPerson();

	PersonManager()
	{
		showPanel();
	}
	
	public void refresh()
	{
		dispose();
		showPanel();
	}
	
	public void showPanel()
	{
		
		setSize(600, 650);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{580, 0};
		gridBagLayout.rowHeights = new int[]{66, 81, 305, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		JPanel Titlepanel = new JPanel();

		JLabel titleLabel = new JLabel("인간 관리");
		titleLabel.setFont(new Font("굴림", Font.BOLD, 52));
		Titlepanel.add(titleLabel);
		GridBagConstraints gbc_Titlepanel = new GridBagConstraints();
		gbc_Titlepanel.fill = GridBagConstraints.BOTH;
		gbc_Titlepanel.insets = new Insets(0, 0, 5, 0);
		gbc_Titlepanel.gridx = 0;
		gbc_Titlepanel.gridy = 0;
		getContentPane().add(Titlepanel, gbc_Titlepanel);

		JPanel bittonPanel = new JPanel();

		JButton searchButton = new JButton("검색");
		searchButton.setFont(new Font("굴림", Font.PLAIN, 24));
		searchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		bittonPanel.add(searchButton);

		JLabel lblNewLabel = new JLabel("                  ");
		bittonPanel.add(lblNewLabel);

		JButton inputButton = new JButton("등록");
		inputButton.setFont(new Font("굴림", Font.PLAIN, 24));

		bittonPanel.add(inputButton);
		GridBagConstraints gbc_bittonPanel = new GridBagConstraints();
		gbc_bittonPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_bittonPanel.insets = new Insets(0, 0, 5, 0);
		gbc_bittonPanel.gridx = 0;
		gbc_bittonPanel.gridy = 1;
		getContentPane().add(bittonPanel, gbc_bittonPanel);

		tablePanel = new JPanel();
		GridBagConstraints gbc_tablePanel = new GridBagConstraints();
		gbc_tablePanel.fill = GridBagConstraints.VERTICAL;
		gbc_tablePanel.gridx = 0;
		gbc_tablePanel.gridy = 2;
		getContentPane().add(tablePanel, gbc_tablePanel);

		inputButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				new JoinPerson();
			}
			
		});

		makeTable();

		showPerson();

		setTable();


		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				// 창을 닫고 이전 창 main으로 돌아 간다.
				dispose();
				new MainMenu();
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(550, 400));
		tablePanel.add(scrollPane);

		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public void makeTable()
	{
		// 테이블
		String header[] = {"번호", "이름", "성별", "나이", "등록일", "삭제"};

		DefaultTableModel model = new DefaultTableModel(header, 0)
		{
			public Class<?> getColumnClass(int column)
			{
				return getValueAt(0, column).getClass();
			}
		};



		// 테이블 뷰 내부 설정
		table = new JTable(model)
		{
			//오버라이드 하는 UI를 업데이트 하는 것
			public void updateUI()
			{
				super.updateUI();
				setRowHeight(36);	// 행, 가로 칸의 높이 설정
				setAutoCreateRowSorter(true);	//열의 자동 정렬 기능
				//setAlignmentX(CENTER_ALIGNMENT);

				// 테이블 뷰 셀 안에 버튼 그룹을 넣는 것
				// 버튼을 넣을 셀의 위치 5
				TableColumn column = getColumnModel().getColumn(5);
				//column.setPreferredWidth(100);

				column.setCellRenderer(new DeleteButtonsRenderer());
				column.setCellEditor(new PersonButtonsEditor(this));
			}
		};
	}

	public void setTable()
	{
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

				if (preferredWidth >= maxWidth)
				{
					preferredWidth = maxWidth;
					break;
				}
			}

			tableColumn.setPreferredWidth( preferredWidth + 20 );
		}
	}

	public void showPerson()
	{
		String empty = "";
		ResultSet rs = new PersonServerManager().getAllPerson();
		// 테이블에 추가하는 방법
		DefaultTableModel model = (DefaultTableModel)table.getModel();

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
			System.out.println("데이터가 없다 " + e );
		}

	}

}

class DeleteButtonsPanel extends JPanel
{
	List<JButton> buttons = Arrays.asList(new JButton("삭제"));

	DeleteButtonsPanel()
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

class DeleteButtonsRenderer implements TableCellRenderer 
{
	DeleteButtonsPanel panel = new DeleteButtonsPanel()
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

class DeletePesonAction extends AbstractAction
{
	JTable table;

	DeletePesonAction(JTable table)
	{
		super("삭제");
		this.table = table;
	}

	public void actionPerformed(ActionEvent e)
	{
		int row = table.convertRowIndexToModel(table.getEditingRow());
		Object o = table.getModel().getValueAt(row, 1);
		// 같은 이름이 가능 하기 때문에 번호로 삭제 해야 한다.
		Object o1 = table.getModel().getValueAt(row,0);
		// 같은 이름이 있는 것을 삭제 할 때 문제가 있을 것이다.
		// 관리자는 id가 겹치치 않게 되어 있지만 인간은 이름이 겹친다.
		// 인간에서 겹치치 않는 것은 primary key 로 설정한 number 뿐이다.
		// 테이블뷰에서 걱정되는 것이 선택한 것의 번호 였다.
		// 하지만 테이블 뷰에 있는 번호는 새로 만든 번호가 아니고
		// data에 있는 번호이다.
		int result = JOptionPane.showConfirmDialog(null, o + " 인간을 처리하겠습니까?","처리확인", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION)
		{
			// 인간 처리
			new PersonServerManager().excutionPerson(Integer.parseInt(""+o1));
			JOptionPane.showMessageDialog(null,  o + " 인간을 처리 했습니다.", "처리", JOptionPane.WARNING_MESSAGE);
		}
	}
}

class PersonButtonsEditor extends AbstractCellEditor implements TableCellEditor
{
	DeleteButtonsPanel panel = new DeleteButtonsPanel();
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
			EventQueue.invokeLater(PersonButtonsEditor.this::fireEditingStopped);
		}
	}

	PersonButtonsEditor(JTable table)
	{
		super();
		this.table = table;
		List<JButton> list = panel.getButtons();
		list.get(0).setAction(new DeletePesonAction(table));

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
}