package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.MD5;
import logic.database.MemberDatabase;

import java.awt.Toolkit;
import java.awt.Color;

public class LogInWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtFieldUsername;
	private JPasswordField passwordField;
	private static Character[] characters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', '_', '-', '0', '1',
			'2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInWindow frame = new LogInWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogInWindow() {
		setTitle("Log in system");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogInWindow.class.getResource("/img/lock.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 467);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		getUserTextField();
		getPasswordField();
		getLblUsername();
		getLblPassword();
		getButtonLogIn();
		getButtonBack();

	}

	private JTextField getUserTextField() {
		txtFieldUsername = new JTextField();
		txtFieldUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letter = e.getKeyChar();
				boolean inList = false;
				for (char c : characters) {
					if (letter == c)
						inList = true;
				}

				if (!inList || txtFieldUsername.getText().length() == 10) {
					e.consume();
				}
			}
		});
		txtFieldUsername.setBounds(61, 98, 235, 31);
		contentPane.add(txtFieldUsername);
		txtFieldUsername.setColumns(10);

		return txtFieldUsername;
	}

	private JPasswordField getPasswordField() {
		passwordField = new JPasswordField();
		passwordField.setBounds(61, 198, 235, 31);
		contentPane.add(passwordField);

		return passwordField;
	}

	private JLabel getLblUsername() {
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUsername.setBounds(61, 66, 116, 24);
		contentPane.add(lblUsername);

		return lblUsername;
	}

	private JLabel getLblPassword() {
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPassword.setBounds(61, 161, 116, 24);
		contentPane.add(lblPassword);

		return lblPassword;
	}

	private JButton getButtonLogIn() {
		JButton buttonLogIn = new JButton("Log in");
		buttonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtFieldUsername.getText().length() == 0) {
					JOptionPane.showMessageDialog(LogInWindow.this, "Please, enter an username.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else if (passwordField.getPassword().length == 0) {
					JOptionPane.showMessageDialog(LogInWindow.this, "Please, enter a password.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String passEntered = String.valueOf(passwordField.getPassword());
					String hashCode = MD5.hashPassword(passEntered);

					try {
						if (MemberDatabase.select(txtFieldUsername.getText(), hashCode))
							JOptionPane.showMessageDialog(LogInWindow.this, "You can access to your account.",
									"Information", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(LogInWindow.this, "You don't have an account!", "Warning",
									JOptionPane.WARNING_MESSAGE);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		buttonLogIn.setBounds(61, 261, 97, 25);
		contentPane.add(buttonLogIn);

		return buttonLogIn;
	}

	private JButton getButtonBack() {
		JButton btnNewButton = new JButton("\u2ba8");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showSignUpWindow();
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(42, 352, 55, 39);
		contentPane.add(btnNewButton);
		return btnNewButton;
	}

	
	private void showSignUpWindow() {
		SignUpWindow sw = new SignUpWindow();
		sw.setVisible(true);
		this.setVisible(false);
	}
}
