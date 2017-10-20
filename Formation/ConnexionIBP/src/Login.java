import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField Username;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(66, 68, 92, 27);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(67, 137, 92, 27);
		frame.getContentPane().add(lblPassword);
		
		Username = new JTextField();
		Username.setBounds(222, 68, 130, 26);
		frame.getContentPane().add(Username);
		Username.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(222, 137, 130, 26);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String uname=Username.getText();
				String psd=passwordField.getText();
				
			if(uname.equals("name") && psd.equals("password"))
				
			{
				JOptionPane.showMessageDialog(frame, "Vous etes bien connecté sur la plateforme recette IBP");
			}
			else 
			{
				JOptionPane.showMessageDialog(frame, "username ou mot de passe invalide");
			}
			}
		});
		btnLogin.setBounds(163, 193, 117, 29);
		frame.getContentPane().add(btnLogin);
	}
}
