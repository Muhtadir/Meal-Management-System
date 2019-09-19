import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Process extends JPanel {
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	private DefaultTableModel memberpTableModel;
	private MemberFileOperations memberFileOperations;
	private ProcessFile processfile;

	private ArrayList<Meal> allmeals = new ArrayList<>();
	private JTable table;
	JScrollPane scrollPane;

	JLabel totalMeal;

	/**
	 * Create the panel.
	 * 
	 */

	public void showMainWidow() {

	}

	public Process() {
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		JLabel lblTaka = new JLabel("Total Expanditure");
		panel.add(lblTaka);

		totalMeal = new JLabel("totalMeal");
		panel.add(totalMeal);
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		JLabel lblProcessing = new JLabel("Processing");
		panel_1.add(lblProcessing);
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panelContet = new JPanel();
		add(panelContet, BorderLayout.CENTER);
		panelContet.setLayout(new GridLayout(1, 2, 10, 0));

		JPanel panelLeft = new JPanel();
		panelContet.add(panelLeft);
		panelLeft.setLayout(null);
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setBounds(21, 48, 46, 14);
		panelLeft.add(lblName_1);
		textField_5 = new JTextField();
		textField_5.setBounds(111, 45, 86, 20);
		panelLeft.add(textField_5);
		textField_5.setColumns(10);
		JLabel lblNoOfMeal_1 = new JLabel("No of meal");
		lblNoOfMeal_1.setBounds(21, 97, 80, 14);
		panelLeft.add(lblNoOfMeal_1);
		textField_6 = new JTextField();
		textField_6.setBounds(111, 94, 86, 20);
		panelLeft.add(textField_6);
		textField_6.setColumns(10);
		JLabel lblExpanditure_2 = new JLabel("Expenditure");
		lblExpanditure_2.setBounds(21, 139, 80, 14);
		panelLeft.add(lblExpanditure_2);
		textField_7 = new JTextField();
		textField_7.setBounds(111, 136, 86, 20);
		panelLeft.add(textField_7);
		textField_7.setColumns(10);
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(21, 204, 89, 23);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {

				String name = textField_5.getText().toString();
				String meal = textField_6.getText().toString();
				String expanditure = textField_7.getText().toString();

				// float ex = Float.parseFloat(expanditure);
				// String due=getText().toString();
			//	String r;

				textField_5.setText("");
				textField_6.setText("");
				textField_5.setText("");

				saveMembersData(name, meal, expanditure);

				loadTable();

			}
		});
		panelLeft.add(btnSave);
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(129, 204, 89, 23);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int index = table.getSelectedRow();
				allmeals.remove(index);
				writeMemberFile();
				loadTable();
			}
		});
		panelLeft.add(btnDelete);

		/*
		 * JPanel panel_4 = new JPanel(); panel_2.add(panel_4);
		 */

		JPanel panelRight = new JPanel();
		panelContet.add(panelRight);
		panelRight.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panelRight.add(scrollPane, BorderLayout.CENTER);

		/*
		 * table = new JTable();
		 * 
		 * scrollPane.setViewportView(table); processfile = new
		 * ProcessFile("Meal.txt"); allmeals = processfile.getMeal();
		 */
		loadTable();
	}

	private void loadTable() {

		table = new JTable();

		scrollPane.setViewportView(table);
		processfile = new ProcessFile("Meal.txt");
		allmeals = processfile.getMeal();

		String heading[] = { "Name", "No of Meal", "Expenditure", "Due", "Return" };

		memberpTableModel = new DefaultTableModel(heading, 0);

		float totalE = 0.0f;
		float totalmeal = 0.0f;

		int count = 0;
		try{
			
		for (Meal meals : allmeals) {
			totalmeal += Float.parseFloat(meals.getMeal());
			totalE += Float.parseFloat(meals.getExpanditure());
			count++;
		}

		totalMeal.setText(totalE + "");

		float mealRate = totalE / totalmeal;

		for (Meal meals : allmeals) {

			if (count == 1) {
				Object[] memberData = { meals.getName2(), meals.getMeal(), meals.getExpanditure(), "-", "-" };
				memberpTableModel.addRow(memberData);
			} else {
				float mdue = (mealRate * Float.parseFloat(meals.getMeal()) - Float.parseFloat(meals.getExpanditure()));

				Object[] memberData = { meals.getName2(), meals.getMeal(), meals.getExpanditure(),
						mdue > 0 ? (mdue + "") : "-", mdue < 0 ? (Math.abs(mdue) + "") : "-" };
				memberpTableModel.addRow(memberData);
			}

		}
		}catch(Exception e){
			System.out.println("Sorry");
		}

		table.setModel(memberpTableModel);

	}
        

	private void writeMemberFile() {
		processfile.writeMembers(allmeals);

	}

	private void saveMembersData(String name, String meal, String expanditure) {

		Meal meals = new Meal(name, meal, expanditure);
		processfile.addMeal(meals);
		loadTable();
	}

}
