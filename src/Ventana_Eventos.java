import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Ventana_Eventos {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	
	//Conexion
	private Conexion conection = new Conexion();
	private Connection con = conection.getConn();
	
	//Panel de datos
	private JPanel panel_1; 
	private DefaultTableModel dtm;
	private JTable tableData;
	
	//Botones
	private JButton adicionar = new JButton("Adicionar");
	private JButton modificar = new JButton("Modificar");
	private JButton eliminar = new JButton("Eliminar");
	private JButton cancelar = new JButton("Cancelar");
	private JButton inactivar = new JButton("Inactivar");
	private JButton reactivar = new JButton("Reactivar");
	private JButton actualizar = new JButton("Actualizar");
	private JButton salir = new JButton("Salir");
	
	//Los combo box
	private JComboBox comboBox; 
	private JComboBox comboBox_1;
	
	

	/*
	 * Launch the application.
	 */
	
	public void mostrarDatosClientes() {
		dtm = new DefaultTableModel();
		ArrayList<String[]> data = new ArrayList<String[]>();
		data = Conexion.getData("eventos", con);
		for(String col: data.get(0)) {
			dtm.addColumn(col);
		}
		
		for(int q =1; q < data.size(); q++) {
			String row[] = new String[data.get(q).length];
			for(int u =0; u < data.get(q).length; u++) {
				row[u] = data.get(q)[u];
			}
			dtm.addRow(row);
		}
		tableData = new JTable(dtm);
		/*
		 dtm = new DefaultTableModel();
		dtm.addColumn("ID");
		dtm.addColumn("Nombre");
		dtm.addColumn("Año");
		dtm.addColumn("Mes");
		dtm.addColumn("Dia");
		dtm.addColumn("Precio");
		dtm.addColumn("Participantes");
		dtm.addColumn("Caracter");
		dtm.addColumn("Lugar");
		dtm.addColumn("Estado");
		tableData = new JTable(dtm);
		
		String[] datos = new String [10];
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM eventos;");
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
				datos[9] = rs.getString(10);
				dtm.addRow(datos);
			}
			tableData = new JTable(dtm);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e + "Error en la consulta");
			// TODO: handle exception
		}
		 */
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Eventos window = new Ventana_Eventos();
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
	public Ventana_Eventos() {
		mostrarDatosClientes();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 779, 642);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JFormattedTextField frmtdtxtfldEventos = new JFormattedTextField();
		frmtdtxtfldEventos.setEditable(false);
		frmtdtxtfldEventos.setText("EVENTOS");
		
		JFormattedTextField frmtdtxtfldRegistro = new JFormattedTextField();
		frmtdtxtfldRegistro.setText("Registro");
		frmtdtxtfldRegistro.setEditable(false);
		
		JPanel panel = new JPanel();
		
		panel_1 = new JPanel();
		
//		JFormattedTextField formatTxtTable = new JFormattedTextField();
//		formatTxtTable.setText("Tabla");
//		formatTxtTable.setEditable(false);
		
        JScrollPane scrollPane = new JScrollPane(tableData);
        
		panel_1.add(scrollPane);
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addComponent(frmtdtxtfldRegistro, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 694, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 693, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(54)
							.addComponent(frmtdtxtfldEventos, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(45, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(adicionar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(inactivar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(74)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(modificar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(reactivar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(eliminar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(actualizar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(71)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(salir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(121))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(frmtdtxtfldEventos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(frmtdtxtfldRegistro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(adicionar)
						.addComponent(modificar)
						.addComponent(eliminar)
						.addComponent(cancelar))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(inactivar)
						.addComponent(reactivar)
						.addComponent(actualizar)
						.addComponent(salir))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JFormattedTextField frmtdtxtfldId = new JFormattedTextField();
		frmtdtxtfldId.setText("Id:");
		frmtdtxtfldId.setEditable(false);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JFormattedTextField frmtdtxtfldNombre = new JFormattedTextField();
		frmtdtxtfldNombre.setText("Nombre:");
		frmtdtxtfldNombre.setEditable(false);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JFormattedTextField frmtdtxtfldFecha = new JFormattedTextField();
		frmtdtxtfldFecha.setText("A\u00F1o:");
		frmtdtxtfldFecha.setEditable(false);
		
		JFormattedTextField frmtdtxtfldMes = new JFormattedTextField();
		frmtdtxtfldMes.setText("Mes:");
		frmtdtxtfldMes.setEditable(false);
		
		JFormattedTextField frmtdtxtfldDia = new JFormattedTextField();
		frmtdtxtfldDia.setText("Dia:");
		frmtdtxtfldDia.setEditable(false);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JFormattedTextField frmtdtxtfldPrecio = new JFormattedTextField();
		frmtdtxtfldPrecio.setText("Precio:");
		frmtdtxtfldPrecio.setEditable(false);
		
		JFormattedTextField frmtdtxtfldNumeroDeParticipantes = new JFormattedTextField();
		frmtdtxtfldNumeroDeParticipantes.setText("Numero de Participantes:");
		frmtdtxtfldNumeroDeParticipantes.setEditable(false);
		
		JFormattedTextField frmtdtxtfldCaracterDelEvento = new JFormattedTextField();
		frmtdtxtfldCaracterDelEvento.setText("Caracter del evento:");
		frmtdtxtfldCaracterDelEvento.setEditable(false);
		
		JFormattedTextField frmtdtxtfldIdlugar = new JFormattedTextField();
		frmtdtxtfldIdlugar.setText("Id_Lugar:");
		frmtdtxtfldIdlugar.setEditable(false);
		
		JFormattedTextField frmtdtxtfldEstadoDeRegistro = new JFormattedTextField();
		frmtdtxtfldEstadoDeRegistro.setText("Estado de Registro:");
		frmtdtxtfldEstadoDeRegistro.setEditable(false);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nacional", "Internacional"}));
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"A", "I", "*"}));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(frmtdtxtfldNombre, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldId, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(frmtdtxtfldDia, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldMes, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldFecha, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textField_3, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(textField_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
										.addComponent(textField_4, Alignment.LEADING, 0, 0, Short.MAX_VALUE))))))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(39)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(frmtdtxtfldEstadoDeRegistro, Alignment.TRAILING)
									.addComponent(frmtdtxtfldCaracterDelEvento, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(107)
								.addComponent(frmtdtxtfldPrecio))
							.addComponent(frmtdtxtfldNumeroDeParticipantes, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addComponent(frmtdtxtfldIdlugar, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(textField_5)
							.addComponent(textField_6)
							.addComponent(textField_7)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(161))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(frmtdtxtfldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldNumeroDeParticipantes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldCaracterDelEvento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldIdlugar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldEstadoDeRegistro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		eventos();
	}
	private void eventos() {
		adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				String nombre = textField_1.getText();
				String anio = textField_2.getText();
				String mes = textField_3.getText();
				String dia = textField_4.getText();
				String precio = textField_5.getText();
				String numero_participantes = textField_6.getText();
				String id_lugar = textField_7.getText();
				
				int caracter =  (Integer)comboBox.getSelectedIndex();
				int estado = (Integer)comboBox_1.getSelectedIndex();
				
				String auxC;
				String auxS;
				
				
				if (caracter == 0) {
					auxC = "Nacional";
				} else auxC = "Internacional";
				
				if (estado == 0) {
					auxS = "A";
				} else if (estado == 1) {
					auxS = "I";
				} else {
					auxS = "E";
				}
				
				
				if(id != "" && nombre !="" && anio !="" && mes !="" && dia !="" && numero_participantes !="" && id_lugar !="") {
					Object [] row = {
							id, nombre, anio,mes,dia,precio,numero_participantes,auxC,id_lugar,auxS,
						};
						dtm.addRow(row);
				}
				
				try {
					
					String columns = "(EveCod, EveNom, EveFecAnio, EveFecMes, EveFecDia, EvePre, EveNroPar , EveCar, EveLugCod,EveEstReg )";
					
					String query = " insert into eventos "+ columns + "\n values (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setInt 	(1, Integer.parseInt(id));//EveCod
				    preparedStmt.setString 	(2, nombre);//EveNom
				    preparedStmt.setInt 	(3, Integer.parseInt(anio));//EveFecAnio
				    preparedStmt.setInt 	(4, Integer.parseInt(mes));//EveFecMes
				    preparedStmt.setInt    	(5, Integer.parseInt(dia));//EveFecDia
				    preparedStmt.setDouble	(6, Double.parseDouble(precio));;//EvePre
				    preparedStmt.setInt    	(7, Integer.parseInt(numero_participantes));//EveNroPar
				    preparedStmt.setString  (8, auxC);//EveCar
				    preparedStmt.setInt    	(9, Integer.parseInt(id_lugar));//EveLugCod
				    preparedStmt.setString  (10, auxS);//EveEstReg 
					preparedStmt.execute();
					
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f + "Error en al añadir");
					// TODO: handle exception
				}
				
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");	
				System.out.println("Adicionar");
				
			}
		});
		
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Modificar");

				int fila = tableData.getSelectedRow();
				
				if (fila != -1) {
					//Codigo Funcional
					
					String columns = "(EveCod, EveNom, EveFecAnio, EveFecMes, EveFecDia, EvePre, EveNroPar , EveCar, EveLugCod,EveEstReg )";
					
					int id = Integer.parseInt(tableData.getValueAt(fila, 0).toString());		
					String nom = tableData.getValueAt(fila, 1).toString();
					int anio = Integer.parseInt(tableData.getValueAt(fila, 2).toString());
					int mes = Integer.parseInt(tableData.getValueAt(fila, 3).toString());
					int dia = Integer.parseInt(tableData.getValueAt(fila, 4).toString());
					double precio = Double.parseDouble(tableData.getValueAt(fila, 5).toString());
					int participantes = Integer.parseInt(tableData.getValueAt(fila, 6).toString());
					String caracter = tableData.getValueAt(fila, 7).toString();
					int lugar = Integer.parseInt(tableData.getValueAt(fila, 8).toString());
					String estado = tableData.getValueAt(fila, 9).toString();
					
					try {
						String query = "update eventos set EveNom = ?, EveFecAnio = ?, EveFecMes = ?, EveFecDia = ?,  EvePre = ?,  EveNroPar = ? , EveCar = ?, EveLugCod = ?,EveEstReg = ?   where EveCod = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, nom);
						preparedStmt.setInt		(2, anio);
						preparedStmt.setInt		(3, mes);
						preparedStmt.setInt		(4, dia);
						preparedStmt.setDouble  (5, precio);
						preparedStmt.setInt		(6, participantes);
						preparedStmt.setString	(7, caracter);
						preparedStmt.setInt		(8, lugar);
						preparedStmt.setString	(9, estado);
						preparedStmt.setInt		(10, id);
						preparedStmt.execute();
					    System.out.println("Se Actualizo Exitosamente el Registro numero : "+ id);
					}catch(Exception ha) {
						ha.printStackTrace();
					}
					
					System.out.println("Id encontrado:  " + id + " " + nom + " " + anio + " " + mes + " " + dia + " " + precio + " " + participantes + " " + caracter + " " + lugar + " " + estado) ;
					
				} else {
					JOptionPane.showMessageDialog(null, "No selecciono ninguno");
				}			
			}
		});
		
		//Se comento la parte netamente funcional porque pide que se vuelva * al eliminar su estado de registro, en este caso será E
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int fila = tableData.getSelectedRow();
				
				if (fila != -1) {
					//Codigo Funcional
					
					String columns = "(EveCod, EveNom, EveFecAnio, EveFecMes, EveFecDia, EvePre, EveNroPar , EveCar, EveLugCod,EveEstReg )";
					
					int id = Integer.parseInt(tableData.getValueAt(fila, 0).toString());		
//					String nom = tableData.getValueAt(fila, 1).toString();
//					int anio = Integer.parseInt(tableData.getValueAt(fila, 2).toString());
//					int mes = Integer.parseInt(tableData.getValueAt(fila, 3).toString());
//					int dia = Integer.parseInt(tableData.getValueAt(fila, 4).toString());
//					double precio = Double.parseDouble(tableData.getValueAt(fila, 5).toString());
//					int participantes = Integer.parseInt(tableData.getValueAt(fila, 6).toString());
//					String caracter = tableData.getValueAt(fila, 7).toString();
//					int lugar = Integer.parseInt(tableData.getValueAt(fila, 8).toString());
					String estado = tableData.getValueAt(fila, 9).toString();
//					
					try {
//						String query = "DELETE FROM eventos WHERE EveCod = ?";
//						PreparedStatement sentencia = con.prepareStatement(query);
//					    sentencia.setInt(1, id);
//					    sentencia.execute();
//					    dtm.removeRow(fila);
						estado = "E";
						String query = "update eventos set EveEstReg = ? where EveCod = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("E", fila, 9);
					    System.out.println("Se Borro Exitosamente el Registro numero : "+ id);
					}catch(Exception g) {
						g.printStackTrace();
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "No selecciono ninguno");
				}
			
				
				System.out.println("Eliminar");
				
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cancelar");
			}
		});
		
		inactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fila = tableData.getSelectedRow();
				
				if (fila != -1) {
					//Codigo Funcional
					
					String columns = "(EveCod, EveNom, EveFecAnio, EveFecMes, EveFecDia, EvePre, EveNroPar , EveCar, EveLugCod,EveEstReg )";
					
					int id = Integer.parseInt(tableData.getValueAt(fila, 0).toString());		
//					String nom = tableData.getValueAt(fila, 1).toString();
//					int anio = Integer.parseInt(tableData.getValueAt(fila, 2).toString());
//					int mes = Integer.parseInt(tableData.getValueAt(fila, 3).toString());
//					int dia = Integer.parseInt(tableData.getValueAt(fila, 4).toString());
//					double precio = Double.parseDouble(tableData.getValueAt(fila, 5).toString());
//					int participantes = Integer.parseInt(tableData.getValueAt(fila, 6).toString());
//					String caracter = tableData.getValueAt(fila, 7).toString();
//					int lugar = Integer.parseInt(tableData.getValueAt(fila, 8).toString());
					String estado = tableData.getValueAt(fila, 9).toString();
//					
					try {
//						String query = "DELETE FROM eventos WHERE EveCod = ?";
//						PreparedStatement sentencia = con.prepareStatement(query);
//					    sentencia.setInt(1, id);
//					    sentencia.execute();
//					    dtm.removeRow(fila);
						estado = "I";
						String query = "update eventos set EveEstReg = ? where EveCod = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("I", fila, 9);
					    System.out.println("Se Inactivo Exitosamente el Registro numero : "+ id);
					}catch(Exception g) {
						g.printStackTrace();
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "No selecciono ninguno");
				}
				
				System.out.println("Inactivar");
			}
		});
		
		reactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
int fila = tableData.getSelectedRow();
				
				if (fila != -1) {
					
					String columns = "(EveCod, EveNom, EveFecAnio, EveFecMes, EveFecDia, EvePre, EveNroPar , EveCar, EveLugCod,EveEstReg )";
					
					int id = Integer.parseInt(tableData.getValueAt(fila, 0).toString());		
//					String nom = tableData.getValueAt(fila, 1).toString();
//					int anio = Integer.parseInt(tableData.getValueAt(fila, 2).toString());
//					int mes = Integer.parseInt(tableData.getValueAt(fila, 3).toString());
//					int dia = Integer.parseInt(tableData.getValueAt(fila, 4).toString());
//					double precio = Double.parseDouble(tableData.getValueAt(fila, 5).toString());
//					int participantes = Integer.parseInt(tableData.getValueAt(fila, 6).toString());
//					String caracter = tableData.getValueAt(fila, 7).toString();
//					int lugar = Integer.parseInt(tableData.getValueAt(fila, 8).toString());
					String estado = tableData.getValueAt(fila, 9).toString();
//					
					try {
//						String query = "DELETE FROM eventos WHERE EveCod = ?";
//						PreparedStatement sentencia = con.prepareStatement(query);
//					    sentencia.setInt(1, id);
//					    sentencia.execute();
//					    dtm.removeRow(fila);
						estado = "A";
						String query = "update eventos set EveEstReg = ? where EveCod = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("A", fila, 9);
					    System.out.println("Se activo Exitosamente el Registro numero : "+ id);
					}catch(Exception g) {
						g.printStackTrace();
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "No selecciono ninguno");
				}
				
				System.out.println("Reactivar");
			}
		});
		
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//No funciona xd
				mostrarDatosClientes();	
				System.out.println("Se actualizo Exitosamente todos los registros");
				System.out.println("Actualizar");
			}
		});
		
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit( 0 );
				System.out.println("Salir");
			}
		});
	}
}
