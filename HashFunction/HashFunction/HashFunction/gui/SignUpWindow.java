package gui;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.MD5;
import logic.database.MemberDatabase;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.util.Map;

public class SignUpWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtFieldUsername;
	private JPasswordField passwordField;
	private static final int PASSWORD_LENGTH = 8;
	private static Character [] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X' ,'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', '_', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpWindow frame = new SignUpWindow();
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
	public SignUpWindow() {
		setResizable(false);
		setTitle("Sign up system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SignUpWindow.class.getResource("/img/lock.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 467);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		getUserTextField();
		getPasswordField();
		getLblUsername();
		getLblPassword();
		getButtonSignUp();
		getLblLogIn();
		
	}
	
	private JTextField getUserTextField() {
		txtFieldUsername = new JTextField();
		txtFieldUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letter = e.getKeyChar();
				boolean inList = false;
				for (char c: characters) {
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

	private JButton getButtonSignUp() {
		JButton buttonSignUp = new JButton("Sign up");
		buttonSignUp.setFont(new Font("Tahoma", Font.BOLD, 13));
		buttonSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtFieldUsername.getText().length() == 0) {
					JOptionPane.showMessageDialog(SignUpWindow.this, "Please, enter an username.", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
				else if (passwordField.getPassword().length == 0) {
					JOptionPane.showMessageDialog(SignUpWindow.this, "Please, enter a password.", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
				else if (passwordField.getPassword().length > PASSWORD_LENGTH) {
					JOptionPane.showMessageDialog(SignUpWindow.this, "Please, the password's length must be 8 characters or less.", "Warning",
							JOptionPane.WARNING_MESSAGE);
					passwordField.setText("");
					passwordField.grabFocus();
				}
				else {
					String pass = String.valueOf(passwordField.getPassword());
					String hashCode = MD5.hashPassword(pass);
					JOptionPane.showMessageDialog(SignUpWindow.this, hashCode, "Hash function of the password.", JOptionPane.INFORMATION_MESSAGE);
					
					try {
						MemberDatabase.insert(txtFieldUsername.getText(), hashCode);
						JOptionPane.showMessageDialog(SignUpWindow.this, "Accound added correctly!", "Information", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(SignUpWindow.this, "You already have an account!", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					
					txtFieldUsername.setText("");
					passwordField.setText("");
					getUserTextField().grabFocus();
				}
					
			}});
		buttonSignUp.setBounds(61, 261, 97, 25);
		contentPane.add(buttonSignUp);

		return buttonSignUp;
	}
	
	private JLabel getLblLogIn() {
		JLabel lblLogIn = new JLabel("You already have an account?");
		lblLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				underlineText(lblLogIn);
				lblLogIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblLogIn.setFont(new Font("Tahoma", Font.ITALIC, 13));
				lblLogIn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				showLogInWindow();
			}
		});
		lblLogIn.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblLogIn.setBounds(61, 319, 235, 31);
		contentPane.add(lblLogIn);
		
		return lblLogIn;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void underlineText(JLabel lbl) {
		Font font = lbl.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lbl.setFont(font.deriveFont(attributes));
	}
	
	private void showLogInWindow() {
		LogInWindow lw = new LogInWindow();
		lw.setVisible(true);
		this.setVisible(false);
	}

}
