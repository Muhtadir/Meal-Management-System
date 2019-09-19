import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JMenu;

public class Main_frame {

	private JFrame frame;
	static MemberPanel memberPanel = new MemberPanel();
	static Process p = new Process();
	CardLayout appLayout;

	/**
	 * Launch the application.
	 */

	static String USERNAME = "user";
	static String PASS = "123";

	public static void main(String[] args) {

		if (login(null)) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Main_frame window = new Main_frame();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else {
			JOptionPane.showMessageDialog(null, "Username or Password don't matches!");
			
		}
	}

	public static boolean login(JFrame frame) {
		
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("Name", SwingConstants.RIGHT));
		label.add(new JLabel("Password", SwingConstants.RIGHT));
		panel.add(label, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField username = new JTextField();
		controls.add(username);
		JPasswordField password = new JPasswordField();
		controls.add(password);
		panel.add(controls, BorderLayout.CENTER);

		JOptionPane.showMessageDialog(frame, panel, "login", JOptionPane.QUESTION_MESSAGE);

		return username.getText().toString().equals(USERNAME) && new String(password.getPassword()).equals(PASS);

	}

	/**
	 * Create the application.
	 */
	public Main_frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Meal managment system");
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnInformation = new JMenu("Information");
		mnInformation.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				appLayout = (CardLayout) frame.getContentPane().getLayout();
				appLayout.show(frame.getContentPane(), "memberPanel");

			}
		});
		menuBar.add(mnInformation);

		JMenu mnProcess = new JMenu("Process");
		mnProcess.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				appLayout = (CardLayout) frame.getContentPane().getLayout();
				appLayout.show(frame.getContentPane(), "processPanel");

			}
		});
		menuBar.add(mnProcess);
		frame.getContentPane().add(memberPanel, "memberPanel");
		frame.getContentPane().add(p, "processPanel");
	}

}
