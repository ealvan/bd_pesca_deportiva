import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Lab_View_3 {

	private JFrame frame;
	private Conexion conection = new Conexion();
	private Connection con = conection.getConn();
	private JTable tableData;
	private DefaultTableModel dtm;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab_View_3 window = new Lab_View_3();
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
	public Lab_View_3() {
		create_Tablas("estado_registro");
		initialize();
	}
	public void create_Tablas(String tabla) {
		dtm = new DefaultTableModel();
		
		
		dtm.addColumn("Codigo del Lugar");
		dtm.addColumn("Nombre del Lugar");
		dtm.addColumn("Comunidad del Lugar");
		dtm.addColumn("Cauce del Lugar");
		dtm.addColumn("Tipo de Lugar");
		dtm.addColumn("Acotado");
		dtm.addColumn("Informacion de la veda");
		dtm.addColumn("Pez de la veda");
		dtm.addColumn("Estado");
		
		String[] datos = new String [10];
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM luguares_referenciales;");
			
			while(rs.next()) {
				datos[0] = rs.getString(1);
				datos[1] = rs.getString(2);
				datos[2] = rs.getString(3);
				datos[3] = rs.getString(4);
				datos[4] = rs.getString(5);
				datos[5] = rs.getString(6);
				datos[6] = rs.getString(7);
				datos[7] = rs.getString(8);
				datos[8] = rs.getString(9);
				dtm.addRow(datos);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e + "Error en la consulta");
			// TODO: handle exception
		}
		
	}
	
	static ArrayList<String> getColumns(String tableName, Connection con) {
		if(tableName.isEmpty()) {
			tableName="peces";
		}
		ArrayList<String> columns = new ArrayList<String>();
		
		String query = "SHOW COLUMNS FROM " + tableName+";";
		
		try {
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				columns.add(rs.getString(1));
			}
			//System.out.println(columns.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return columns;
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 651, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vista Lugares con Referenciales");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 15));
		lblNewLabel.setBounds(24, 35, 262, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(24, 279, 572, 163);
		frame.getContentPane().add(panel);
		tableData = new JTable(dtm);
		JScrollPane scrollPane = new JScrollPane(tableData);
		scrollPane.setBounds(0, 0, 572, 163);
		panel.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo del Lugar");
		lblNewLabel_1.setBounds(24, 81, 93, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre del Lugar");
		lblNewLabel_1_1.setBounds(24, 117, 103, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Comunidad del Lugar");
		lblNewLabel_1_1_1.setBounds(24, 151, 103, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Cauce del Lugar");
		lblNewLabel_1_1_1_1.setBounds(24, 186, 103, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Tipo de Lugar");
		lblNewLabel_1_1_1_1_1.setBounds(273, 81, 103, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		textField = new JTextField();
		textField.setBounds(134, 78, 46, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(134, 114, 109, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(134, 148, 109, 20);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(134, 183, 109, 20);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(397, 78, 109, 20);
		frame.getContentPane().add(textField_4);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Acotado");
		lblNewLabel_1_1_1_1_1_1.setBounds(273, 117, 103, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Informacion de la veda");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(273, 151, 103, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1_1 = new JLabel("Pez de la veda");
		lblNewLabel_1_1_1_1_1_1_1_1.setBounds(273, 186, 103, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1_1_1 = new JLabel("Estado");
		lblNewLabel_1_1_1_1_1_1_1_1_1.setBounds(273, 221, 103, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(397, 114, 109, 20);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(397, 148, 109, 20);
		frame.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(397, 183, 109, 20);
		frame.getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(397, 218, 109, 20);
		frame.getContentPane().add(textField_8);
		eventos();
	}
	
	private void eventos() {
		tableData.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	int fila = tableData.getSelectedRow();
				
				if (fila != -1) {
					//Codigo Funcional
					
					
					String a = 	tableData.getValueAt(fila, 0).toString();
					String b = 	tableData.getValueAt(fila, 1).toString();
					String c = 	tableData.getValueAt(fila, 2).toString();
					String d = 	tableData.getValueAt(fila, 3).toString();
					String e	 = 	tableData.getValueAt(fila, 4).toString();
					String f	 = 	tableData.getValueAt(fila, 5).toString();
					String g	 = 	tableData.getValueAt(fila, 6).toString();
					String h	 = 	tableData.getValueAt(fila, 7).toString();
					String i	 = 	tableData.getValueAt(fila, 8).toString();
					
					textField.setText(String.valueOf(a));;
					textField_1.setText(String.valueOf(b));;
					textField_2.setText(String.valueOf(c));;
					textField_3.setText(String.valueOf(d));;
					textField_4.setText(String.valueOf(e));;
					textField_5.setText(String.valueOf(f));;
					textField_6.setText(String.valueOf(g));;
					textField_7.setText(String.valueOf(h));;
					textField_8.setText(String.valueOf(i));;
					
					
				} else {
					JOptionPane.showMessageDialog(null, "No selecciono ninguno");
				}
	        	
	        }
	    });
	}
}
