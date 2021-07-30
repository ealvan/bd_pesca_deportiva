import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Lab_Select_1 {

	private JFrame frame;
	private Conexion conection = new Conexion();
	private Connection con = conection.getConn();
	private JTable tableData;
	private DefaultTableModel dtm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab_Select_1 window = new Lab_Select_1();
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
	public Lab_Select_1() {
		create_Tablas("estado_registro");
		initialize();
	}
	public void create_Tablas(String tabla) {
		dtm = new DefaultTableModel();
		
		
		dtm.addColumn("AfiCod");
		dtm.addColumn("AfiNom");
		dtm.addColumn("AfiApePat");
		dtm.addColumn("AfiApeMat");
		dtm.addColumn("AfiLicCod");
		
		String[] datos = new String [10];
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT AfiCod, AfiNom, AfiApePat, AfiApeMat, AfiLicCod FROM afiliados WHERE AfiLicCod = 1;");
			
			while(rs.next()) {
				datos[0] = rs.getString(1);
				datos[1] = rs.getString(2);
				datos[2] = rs.getString(3);
				datos[3] = rs.getString(4);
				datos[4] = rs.getString(5);
				dtm.addRow(datos);
			}
			tableData = new JTable(dtm);
			
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
		frame.setBounds(100, 100, 581, 365);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SELECT AfiCod, AfiNom, AfiApePat, AfiApeMat, AfiLicCod");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 15));
		lblNewLabel.setBounds(24, 35, 510, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblFromAfiliados = new JLabel("FROM afiliados ");
		lblFromAfiliados.setHorizontalAlignment(SwingConstants.LEFT);
		lblFromAfiliados.setForeground(Color.BLACK);
		lblFromAfiliados.setFont(new Font("Georgia", Font.BOLD, 15));
		lblFromAfiliados.setBackground(Color.WHITE);
		lblFromAfiliados.setBounds(24, 63, 510, 30);
		frame.getContentPane().add(lblFromAfiliados);
		
		JLabel lblWhereAfiliccod = new JLabel("WHERE AfiLicCod = 1;");
		lblWhereAfiliccod.setHorizontalAlignment(SwingConstants.LEFT);
		lblWhereAfiliccod.setForeground(Color.BLACK);
		lblWhereAfiliccod.setFont(new Font("Georgia", Font.BOLD, 15));
		lblWhereAfiliccod.setBackground(Color.WHITE);
		lblWhereAfiliccod.setBounds(24, 91, 510, 30);
		frame.getContentPane().add(lblWhereAfiliccod);
		JScrollPane scrollPane = new JScrollPane(tableData);
		scrollPane.setBounds(0, 0, 545, 163);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(scrollPane);
		panel.setBounds(10, 136, 545, 163);
		frame.getContentPane().add(panel);
	}
}
