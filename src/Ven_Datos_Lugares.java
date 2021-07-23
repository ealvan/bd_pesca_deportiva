import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class Ven_Datos_Lugares {

	public JFrame frame;
	private JTextField textField_1;
	private String Tname;
	private JComboBox comboBox;
	
	private JButton adicionar = new JButton("Adicionar");
	private JButton modificar = new JButton("Modificar");
	private JButton eliminar = new JButton("Eliminar");
	private JButton cancelar = new JButton("Cancelar");
	private JButton inactivar = new JButton("Inactivar");
	private JButton reactivar = new JButton("Reactivar");
	private JButton actualizar = new JButton("Actualizar");
	private JButton salir = new JButton("Salir");
	
	
	
	private Conexion conection = new Conexion();
	private Connection con = conection.getConn();
	private JTable tableData;
	private DefaultTableModel dtm;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textoChidori;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ven_Datos_Lugares window = new Ven_Datos_Lugares("a");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 * @return 
	 */
	
	public void create_Tablas(String tabla) {
		dtm = new DefaultTableModel();
		dtm.addColumn(getColumns(tabla,con).get(0));
		dtm.addColumn(getColumns(tabla,con).get(1));
		dtm.addColumn(getColumns(tabla,con).get(2));
		dtm.addColumn(getColumns(tabla,con).get(3));
		dtm.addColumn(getColumns(tabla,con).get(4));
		dtm.addColumn(getColumns(tabla,con).get(5));
		dtm.addColumn(getColumns(tabla,con).get(6));
		dtm.addColumn(getColumns(tabla,con).get(7));
		
		
		String[] datos = new String [10];
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tabla + ";");
			
			while(rs.next()) {
				datos[0] = rs.getString(1);
				datos[1] = rs.getString(2);
				datos[2] = rs.getString(3);
				datos[3] = rs.getString(4);
				datos[4] = rs.getString(5);
				datos[5] = rs.getString(6);
				datos[6] = rs.getString(7);
				datos[7] = rs.getString(8);
				dtm.addRow(datos);
			}
			tableData = new JTable(dtm);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e + "Error en la consulta");
			// TODO: handle exception
		}
		
	}
	
	
	public Ven_Datos_Lugares(String type) {
		Tname = type;
		create_Tablas(type);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		create_dates("a");
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
	
	static ArrayList<String[]> getData(String tableName, Connection con) {
		//obtener columnas
		ArrayList<String> columns = getColumns(tableName, con);
		ArrayList<String[]> data = new ArrayList<String[]>();
		//pasamos a un array statico las columnas
		String[] cols = new String[columns.size()];
		int p = 0;
		for(String col: columns) {
			cols[p] = col;
			p++;
		}
		//agregamos como el head de la tabla
		//siempre la primera fila, son las columnas!
		data.add(cols);
		
		//get type of data
		String[] types = new String[columns.size()];
		//son las queryes que usamos
		//qtypes, se usa para la descripcion de de la tabla
		String qTypes = "SHOW COLUMNS FROM " + tableName+";";
		String qData = "SELECT * FROM "+tableName+";";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qTypes);
			int i =0;
			while(rs.next()) {
				types[i] = rs.getString(2);
				i++;
			}
			
			//get table of data
			Statement stmtData = con.createStatement();
			ResultSet resSet = stmtData.executeQuery(qData);
			//vamos fila, por fila
			while(resSet.next()) {
				String[] row = new String[types.length];
				//ahora vamos por las celdas de la fila
				for(int j =1; j <= row.length; j++) {
					if(types[j-1].startsWith("int")) {
						row[j-1] = String.valueOf(resSet.getInt(j));
					}else {
						row[j-1] = resSet.getString(j);
					}
				}
				data.add(row);
			}
			/*
			 * for(int q =0; q < data.size(); q++) {
				for(int u =0; u < data.get(q).length; u++) {
					System.out.print(data.get(q)[u] + " " );
				}
				System.out.println();
			}
			 * 
			 * */
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		//retornamos, si no se logra, tendra un error
		//de indexOfBounds Exception o otro error del ArrayList
		return data;
	}
	private void eventos() {
		adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = textoChidori.getText();
				String nombre = textField_1.getText();
				String comunidad = textField_2.getText();
				String cauce = textField_3.getText();
				String lugar_tip = textField_4.getText();
				String acotado =  textField_5.getText();
				String lugar_veda = textField_6.getText();
				
				int estado = (Integer)comboBox.getSelectedIndex();
				String auxS;
				
				
				if (estado == 0) {
					auxS = "A";
				} else if (estado == 1) {
					auxS = "I";
				} else {
					auxS = "E";
				}
				

				
				try {
					
					ArrayList <String> k = getColumns(Tname,con);
					
					String columns = "(" + k.get(0) + ", " + k.get(1) + ", " + k.get(2) + ", " + k.get(3) + ", " + k.get(4) + ", " + k.get(5) + ", " + k.get(6) + ", " + k.get(7) + ")";
					
					String query = " insert into " + Tname + columns + "\n values (?, ?, ?, ?, ?, ?, ?, ?)";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setInt 	(1, Integer.parseInt(id));
				    preparedStmt.setString 	(2, nombre);
				    preparedStmt.setString 	(3, comunidad);
				    preparedStmt.setInt 	(4, Integer.parseInt(cauce));
				    preparedStmt.setInt 	(5, Integer.parseInt(lugar_tip));
				    preparedStmt.setInt 	(6, Integer.parseInt(acotado));
				    preparedStmt.setInt 	(7, Integer.parseInt(lugar_veda));
				    preparedStmt.setString 	(8, auxS);
				      
					preparedStmt.execute();
					
					
					Object [] row = {id, nombre, comunidad,cauce,lugar_tip,acotado,lugar_veda,auxS};
					dtm.addRow(row);
					
					
					
					
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f + " Error en al a�adir");
					// TODO: handle exception
				}
				
				textoChidori.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				
				
				System.out.println("Adicionar");
				
			}
		});
		tableData.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	int fila = tableData.getSelectedRow();
				
				if (fila != -1) {
					//Codigo Funcional
					
					int id = Integer.parseInt(tableData.getValueAt(fila, 0).toString());
					String nombre = tableData.getValueAt(fila, 1).toString();
					String comunidad = tableData.getValueAt(fila, 2).toString();
					int cauce = Integer.parseInt(tableData.getValueAt(fila, 3).toString());
					int lugar_tip = Integer.parseInt(tableData.getValueAt(fila, 4).toString());
					int acotado =  Integer.parseInt(tableData.getValueAt(fila, 5).toString());
					int lugar_veda = Integer.parseInt(tableData.getValueAt(fila, 6).toString());
					String estado = tableData.getValueAt(fila, 7).toString();
					
					textoChidori.setText(String.valueOf(id));
					textField_1.setText(nombre);
					textField_2.setText(comunidad);
					textField_3.setText("" + lugar_tip);
					textField_4.setText("" +acotado);
					textField_5.setText("" +lugar_veda);
					textField_6.setText("" +lugar_veda);

					comboBox.getModel().setSelectedItem(estado);
					
				} else {
					JOptionPane.showMessageDialog(null, "No selecciono ninguno");
				}
	        	
	        }
	    });
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Modificar");

				int fila = tableData.getSelectedRow();
				
				if (fila != -1) {
					//Codigo Funcional
					
					int id = Integer.parseInt(textoChidori.getText());
					String nombre = textField_1.getText();
					String comunidad = textField_2.getText();
					int cauce = Integer.parseInt(textField_3.getText());
					int lugar_tip = Integer.parseInt(textField_4.getText());
					int acotado =  Integer.parseInt(textField_5.getText());
					int lugar_veda = Integer.parseInt(textField_6.getText());
					String estado = comboBox.getSelectedItem().toString();
					
					
					try {
						
						ArrayList <String> k = getColumns(Tname,con);
						String query = "update " + Tname + " set " + k.get(1) + " = ?, " + k.get(2) + " = ?, " + k.get(3) + " = ?, " + k.get(4) + " = ?, " + k.get(5) + " = ?, " + k.get(6) + " = ?, " + k.get(7) + " = ?   where " + k.get(0) + " = ?";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, nombre);
						preparedStmt.setString	(2, comunidad);
						preparedStmt.setInt		(3, cauce);
						preparedStmt.setInt		(4, lugar_tip);
						preparedStmt.setInt		(5, acotado);
						preparedStmt.setInt		(6, lugar_veda);
						preparedStmt.setString	(7, estado);
						preparedStmt.setInt		(8, id);
						preparedStmt.execute();
						
					    System.out.println("Se Actualizo Exitosamente el Registro numero : "+ id);
					    JOptionPane.showMessageDialog(frame, "Se Modifico exitosamente!!");
						//poniendo los datos, actualizados
					    DefaultTableModel model = (DefaultTableModel)tableData.getModel();
					    model.setValueAt(String.valueOf(id), fila, 0);
					    model.setValueAt(nombre, fila, 1);
					    model.setValueAt(comunidad, fila, 2);
					    model.setValueAt(cauce, fila, 3);
					    model.setValueAt(lugar_tip, fila, 4);
					    model.setValueAt(acotado, fila, 5);
					    model.setValueAt(lugar_veda, fila, 6);
					    model.setValueAt(estado, fila, 7);
					    
					}catch(Exception ha) {
						ha.printStackTrace();
					}
					
					
				} else {
					JOptionPane.showMessageDialog(frame, "No selecciono ningun registro :(", "Inane warning",
						    JOptionPane.WARNING_MESSAGE);
				}			
			}
		});
		
		//Se comento la parte netamente funcional porque pide que se vuelva * al eliminar su estado de registro, en este caso ser� E
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int fila = tableData.getSelectedRow();
				
				if (fila != -1) {
					//Codigo Funcional

					
					int id = Integer.parseInt(tableData.getValueAt(fila, 0).toString());		
					String estado;

					try {
						ArrayList <String> k = getColumns(Tname,con);
						estado = "E";
						String query = "update " + Tname + " set " + k.get(7) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("E", fila, 7);
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

					
					int id = Integer.parseInt(tableData.getValueAt(fila, 0).toString());		
					String estado;

					try {
						ArrayList <String> k = getColumns(Tname,con);
						estado = "I";
						String query = "update " + Tname + " set " + k.get(7) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("I", fila, 7);
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
					//Codigo Funcional

					
					int id = Integer.parseInt(tableData.getValueAt(fila, 0).toString());		
					String estado;

					try {
						ArrayList <String> k = getColumns(Tname,con);
						estado = "A";
						String query = "update " + Tname + " set " + k.get(7) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("A", fila, 7);
					    System.out.println("Se Activo Exitosamente el Registro numero : "+ id);
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
				System.out.println("UPDATE TABLE!");
				
			}
		});
		
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				System.out.println("Salir");
			}
		});
	}
	
	void create_dates(String a) {
		JTextPane txtpnEstadoDeRegistro = new JTextPane();
		txtpnEstadoDeRegistro.setBounds(55, 30, 125, 20);
		
		SimpleAttributeSet attributeSet = new SimpleAttributeSet();
		StyleConstants.setFontFamily(attributeSet, "arial");
        StyleConstants.setFontSize(attributeSet, 16);
        StyleConstants.setLineSpacing(attributeSet, -0.2f);
        StyleConstants.setBold(attributeSet, true);
        StyleConstants.setItalic(attributeSet, true);
        StyleConstants.setBackground(attributeSet, Color.pink);
        StyleConstants.setForeground(attributeSet, Color.black);
        StyleConstants.setLeftIndent(attributeSet, 4);

	    txtpnEstadoDeRegistro.setCharacterAttributes(attributeSet, true);
		
		txtpnEstadoDeRegistro.setText(Tname);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Tabla: ");
		lblNewJgoodiesLabel.setBounds(10, 30, 46, 14);
		
		JFormattedTextField frmtdtxtfldId = new JFormattedTextField();
		frmtdtxtfldId.setBounds(10, 71, 90, 20);
		frmtdtxtfldId.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldId.setText("Id:");
		
		JFormattedTextField frmtdtxtfldDescripcion = new JFormattedTextField();
		frmtdtxtfldDescripcion.setBounds(10, 97, 90, 20);
		frmtdtxtfldDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldDescripcion.setText("Nombre:");
		
		JFormattedTextField frmtdtxtfldEstado = new JFormattedTextField();
		frmtdtxtfldEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldEstado.setBounds(261, 123, 87, 20);
		frmtdtxtfldEstado.setText("Estado:");
		
		textField_1 = new JTextField();
		textField_1.setBounds(110, 97, 102, 20);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(356, 123, 43, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"A", "I", "*"}));
		
		JScrollPane scrollPane = new JScrollPane(tableData);
		scrollPane.setBounds(0, 0, 514, 165);
		JPanel panel = new JPanel();
		panel.setBounds(10, 228, 514, 165);
		panel.setLayout(null);
		panel.add(scrollPane);
		
		JFormattedTextField frmtdtxtfldComunidad = new JFormattedTextField();
		frmtdtxtfldComunidad.setBounds(10, 123, 90, 20);
		frmtdtxtfldComunidad.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldComunidad.setText("Comunidad:");
		
		textField_2 = new JTextField();
		textField_2.setBounds(110, 123, 102, 20);
		textField_2.setColumns(10);
		
		JFormattedTextField frmtdtxtfldLugarCauce = new JFormattedTextField();
		frmtdtxtfldLugarCauce.setBounds(10, 149, 90, 20);
		frmtdtxtfldLugarCauce.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLugarCauce.setText("Cauce:");
		
		textField_3 = new JTextField();
		textField_3.setBounds(110, 149, 102, 20);
		textField_3.setColumns(10);
		
		JFormattedTextField frmtdtxtfldLugarTipo = new JFormattedTextField();
		frmtdtxtfldLugarTipo.setBounds(10, 175, 90, 20);
		frmtdtxtfldLugarTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLugarTipo.setText("Lugar Tipo:");
		
		textField_4 = new JTextField();
		textField_4.setBounds(110, 175, 102, 20);
		textField_4.setColumns(10);
		
		JFormattedTextField frmtdtxtfldLugarAcotado = new JFormattedTextField();
		frmtdtxtfldLugarAcotado.setBounds(261, 71, 87, 20);
		frmtdtxtfldLugarAcotado.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLugarAcotado.setText("Acotado:");
		frame.getContentPane().setLayout(null);
		adicionar.setBounds(24, 428, 99, 23);
		frame.getContentPane().add(adicionar);
		inactivar.setBounds(24, 462, 99, 23);
		frame.getContentPane().add(inactivar);
		reactivar.setBounds(157, 462, 99, 23);
		frame.getContentPane().add(reactivar);
		modificar.setBounds(157, 428, 99, 23);
		frame.getContentPane().add(modificar);
		actualizar.setBounds(282, 462, 99, 23);
		frame.getContentPane().add(actualizar);
		eliminar.setBounds(282, 428, 99, 23);
		frame.getContentPane().add(eliminar);
		cancelar.setBounds(405, 428, 89, 23);
		frame.getContentPane().add(cancelar);
		salir.setBounds(405, 462, 89, 23);
		frame.getContentPane().add(salir);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(frmtdtxtfldDescripcion);
		frame.getContentPane().add(frmtdtxtfldComunidad);
		frame.getContentPane().add(frmtdtxtfldLugarCauce);
		frame.getContentPane().add(frmtdtxtfldLugarTipo);
		frame.getContentPane().add(lblNewJgoodiesLabel);
		frame.getContentPane().add(frmtdtxtfldId);
		frame.getContentPane().add(textField_4);
		frame.getContentPane().add(textField_3);
		frame.getContentPane().add(frmtdtxtfldLugarAcotado);
		frame.getContentPane().add(textField_1);
		frame.getContentPane().add(textField_2);
		frame.getContentPane().add(txtpnEstadoDeRegistro);
		frame.getContentPane().add(frmtdtxtfldEstado);
		frame.getContentPane().add(comboBox);
		
		JFormattedTextField frmtdtxtfldLugarVeda = new JFormattedTextField();
		frmtdtxtfldLugarVeda.setText("Lugar Veda:");
		frmtdtxtfldLugarVeda.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLugarVeda.setBounds(261, 97, 87, 20);
		frame.getContentPane().add(frmtdtxtfldLugarVeda);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(356, 71, 96, 20);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(356, 97, 96, 20);
		frame.getContentPane().add(textField_6);
		
		textoChidori = new JTextField();
		textoChidori.setBounds(110, 71, 86, 20);
		frame.getContentPane().add(textoChidori);
		textoChidori.setColumns(10);
		eventos();
		
	}
}