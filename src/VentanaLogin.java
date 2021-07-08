import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

import java.util.*;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class VentanaLogin {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin();
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
	public VentanaLogin() {
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
		
		JTextPane txtpnLogin = new JTextPane();
		txtpnLogin.setEditable(false);
		txtpnLogin.setText("LOGIN");
		txtpnLogin.setBounds(32, 25, 132, 20);
		frame.getContentPane().add(txtpnLogin);
		
		textField = new JTextField();
		textField.setBounds(32, 86, 253, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(32, 151, 253, 20);
		frame.getContentPane().add(passwordField);
		
		JTextArea txtrUsername = new JTextArea();
		txtrUsername.setEditable(false);
		txtrUsername.setText("Username");
		txtrUsername.setBounds(42, 53, 82, 22);
		frame.getContentPane().add(txtrUsername);
		
		JTextArea txtrPassword = new JTextArea();
		txtrPassword.setEditable(false);
		txtrPassword.setText("Password");
		txtrPassword.setBounds(42, 117, 82, 22);
		frame.getContentPane().add(txtrPassword);
		
		JButton btnNewButton = new JButton("Logearte");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] correctPassword = { 'a', 's', 'd' };
				char[] input = passwordField.getPassword();
				System.out.println("Pass: "  + String.valueOf(input));
				System.out.println(textField.getText()+"\n" + Arrays.equals(correctPassword,input));
			}
		});
		btnNewButton.setBounds(32, 200, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
