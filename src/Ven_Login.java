import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

import java.util.*;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class Ven_Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private Conexion conection = new Conexion();
	private Connection con = conection.getConn();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ven_Login window = new Ven_Login();
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
	public Ven_Login() {
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
				char[] input = passwordField.getPassword();

				String query = "SELECT UsuCon FROM usuario where UsuUsu = '" + textField.getText() + "'";
				System.out.println(query);

				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					String k = null;
					if (rs.next()) {
						k = rs.getString(1);
					}

					char[] correctPassword = k.toCharArray();

					if (Arrays.equals(correctPassword, input)) {
						System.out.println("PUDO LOGEARSE");

						Ven_GenMenu tr = new Ven_GenMenu();
						frame.setVisible(false);
						tr.frame.setVisible(true);

					}

					con.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Ingreso un usuario o una contrase�a incorrecta");
				}

			}
		});
		btnNewButton.setBounds(32, 200, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
