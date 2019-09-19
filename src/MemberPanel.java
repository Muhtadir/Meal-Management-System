import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class MemberPanel extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldPhone;
	private DefaultTableModel memberTableModel;
	
	private MemberFileOperations memberFileOperations;
	private ProcessFile processfile;
	
	private ArrayList<Member> allMembers = new ArrayList<>();
	private JTable table;
	

	/**
	 * Create the panel.
	 */
	public MemberPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		add(panelTitle, BorderLayout.NORTH);
		
		JLabel lblOperations = new JLabel("Operations");
		lblOperations.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelTitle.add(lblOperations);
		
		JPanel panelButton = new JPanel();
		add(panelButton, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String name = textFieldName.getText().toString();
				String Phone = textFieldPhone.getText().toString();
				
				textFieldName.setText("");
				textFieldPhone.setText("");
				
				saveMembersData(name, Phone);
				
			}
		});
		panelButton.add(btnSave);
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int index = table.getSelectedRow();
				allMembers.remove(index);
				loadTable();
				writeMembersFile();
			}
		});
		panelButton.add(btnDelete);
		
		JPanel panelContet = new JPanel();
		add(panelContet, BorderLayout.CENTER);
		panelContet.setLayout(new GridLayout(1, 2, 10, 0));
		
		JPanel panelLeft = new JPanel();
		panelContet.add(panelLeft);
		panelLeft.setLayout(null);
		 
		JLabel lblEmployeeName = new JLabel("Name");
		lblEmployeeName.setBounds(20, 0, 120, 27);
		panelLeft.add(lblEmployeeName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(20, 31, 200, 33);
		textFieldName.setToolTipText("Enter your name");
		panelLeft.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblEmployeeId = new JLabel("Phone");
		lblEmployeeId.setBounds(20, 69, 128, 27);
		panelLeft.add(lblEmployeeId);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(20, 107, 200, 33);
		textFieldPhone.setToolTipText("Enter Your phone number");
		panelLeft.add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		JPanel panelRight = new JPanel();
		panelContet.add(panelRight);
		panelRight.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelRight.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		//table2=new JTable();
		/*table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = table.getSelectedRow();
				
			}
		});*/
		scrollPane.setViewportView(table);
	//	scrollPane.setViewportView(table2);
		
		memberFileOperations = new MemberFileOperations("Member.txt");
	//	processfile=new ProcessFile("Meal.txt");
		//empFileOperations.openFormatter();
		allMembers = memberFileOperations.getMembers();
		//allEmployees=processfile.getEmployees();
		loadTable();
	}
	
	
	private void loadTable() {
		
		String heading[] = {"Name", "Phone Number"};
		
		memberTableModel = new DefaultTableModel(heading, 0);
		
		for (Member employee : allMembers) {
			
			Object[] empData = {employee.getName(), employee.getPhone()};
			memberTableModel.addRow(empData);
			
		}
		
		table.setModel(memberTableModel);
	}
		
	private void writeMembersFile(){
		memberFileOperations.writeMembers(allMembers);
				
	}

	private void saveMembersData(String name, String phone)
	{
		
		Member member = new Member(name, phone);
		allMembers.add(member);
		memberFileOperations.addMembers(member);
		loadTable();
	}
}

