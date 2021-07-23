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
import java.awt.BorderLayout;

public class Ven_Datos_Capturas_Eventos {

	public JFrame frame;
	private JTextField textField;
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
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ven_Datos_Capturas_Eventos window = new Ven_Datos_Capturas_Eventos("capturas_eve");	
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
		ArrayList<String> cols = getColumns(tabla,con);
		for(String col: cols) {
			dtm.addColumn(col);
		}
		
		
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
				datos[8] = rs.getString(9);
				dtm.addRow(datos);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e + "Error en la consulta");
			// TODO: handle exception
		}
		
	}
	
	
	public Ven_Datos_Capturas_Eventos(String type) {
		Tname = type;
		create_Tablas(type);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		create_dates("a");
		eventos();
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
				
				int id = Integer.parseInt(textField.getText());
				int  capcod = Integer.parseInt(textField_1.getText());
				int mes =Integer.parseInt(textField_2.getText());
				//int afiCod = Integer.parseInt(textField_3.getText());
				int evecod = Integer.parseInt(textField_4.getText());
				int anio = Integer.parseInt(textField_5.getText());
				int dia = Integer.parseInt(textField_6.getText());
				int h = Integer.parseInt(textField_7.getText());
				int m = Integer.parseInt(textField_8.getText());
				String estado = (String) comboBox.getModel().getSelectedItem();
				
				try {
					
					ArrayList <String> k = getColumns(Tname,con);
					
					String newRow = k.get(0)+" = ?, "+ k.get(1)+" = ?, "+ k.get(2)+" = ?, "+ k.get(3)+" = ?, "+ k.get(4)+" = ?, "+ k.get(5)+" = ?, "+ k.get(6)+" = ?, "+ k.get(7)+" = ?, "+ k.get(8)+" = ?";
					System.out.println(newRow);
					String query = " insert into " + Tname +  " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setInt 	(1, id);
					preparedStmt.setInt 	(2, capcod);
					preparedStmt.setInt 	(3, evecod);
					preparedStmt.setInt 	(4, anio);
					preparedStmt.setInt 	(5, mes);
					preparedStmt.setInt 	(6, dia);
					preparedStmt.setInt 	(7, h);
					preparedStmt.setInt 	(8, m);
					preparedStmt.setString 	(9, estado);
					 
					preparedStmt.execute();
					
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f + "Error en al añadir");
					// TODO: handle exception
				}
				
				
				textField.setText("");
				textField_1.setText("");
				System.out.println("Adicionar");
				
			}
		});
		tableData.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	int fila = tableData.getSelectedRow();
				
				if (fila != -1) {
					//Codigo Funcional
					
					String id = tableData.getValueAt(fila, 0).toString();
					String capcod= tableData.getValueAt(fila, 1).toString();
					String evecod = tableData.getValueAt(fila, 2).toString();
					String anio = tableData.getValueAt(fila, 3).toString();
					String mes = tableData.getValueAt(fila, 4).toString();
					String dia = tableData.getValueAt(fila, 5).toString();
					String h = tableData.getValueAt(fila, 6).toString();
					String m = tableData.getValueAt(fila, 7).toString();
					String estado = tableData.getValueAt(fila, 8).toString();
					
					textField.setText(id);
					textField_1.setText(capcod);
					textField_2.setText(mes);
					//textField_3.setText(h);
					textField_4.setText(evecod);
					textField_5.setText(anio);
					textField_6.setText(dia);
					textField_7.setText(h);
					textField_8.setText(m);
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
					
					int id = Integer.parseInt(textField.getText());
					int  capcod = Integer.parseInt(textField_1.getText());
					int mes =Integer.parseInt(textField_2.getText());
					//int afiCod = Integer.parseInt(textField_3.getText());
					int evecod = Integer.parseInt(textField_4.getText());
					int anio = Integer.parseInt(textField_5.getText());
					int dia = Integer.parseInt(textField_6.getText());
					int h = Integer.parseInt(textField_7.getText());
					int m = Integer.parseInt(textField_8.getText());
					String estado = (String) comboBox.getModel().getSelectedItem();
					try {
						
						ArrayList <String> k = getColumns(Tname,con);
						String newRow = k.get(1)+" = ?, "+ k.get(2)+" = ?, "+ k.get(3)+" = ?, "+ k.get(4)+" = ?, "+ k.get(5)+" = ?, "+ k.get(6)+" = ?, "+ k.get(7)+" = ?, "+ k.get(8)+" = ?";
						System.out.println(newRow);
						String query = "update " + Tname + " set " + newRow +" where " + k.get(0) + " = ? ;";
						//UPDATE `bd_pescadeportiva`.`eventos_afi` SET `EveAfiPos` = '2' WHERE (`EveAfiCod` = '8');
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setInt	(1, capcod);
						preparedStmt.setInt	(2, evecod);
						preparedStmt.setInt	(3, anio);
						preparedStmt.setInt	(4, mes);
						preparedStmt.setInt	(5, dia);
						preparedStmt.setInt	(6, h);
						preparedStmt.setInt	(7, m);
						preparedStmt.setString	(8, estado);
						preparedStmt.setInt	(9, id);
						
						preparedStmt.execute();
						
					    System.out.println("Se Actualizo Exitosamente el Registro numero : "+ id);
					    JOptionPane.showMessageDialog(frame, "Se Modifico exitosamente!!");
						//poniendo los datos, actualizados
					    DefaultTableModel model = (DefaultTableModel)tableData.getModel();
					    model.setValueAt(String.valueOf(id), fila, 0);
					    model.setValueAt(String.valueOf(capcod), fila, 0);
					    model.setValueAt(String.valueOf(evecod), fila, 0);
					    
					    model.setValueAt(String.valueOf(anio), fila, 0);
					    model.setValueAt(String.valueOf(mes), fila, 0);
					    model.setValueAt(String.valueOf(dia), fila, 0);
					    model.setValueAt(String.valueOf(h), fila, 0);
					    model.setValueAt(String.valueOf(m), fila, 0);
					    model.setValueAt(String.valueOf(estado), fila, 0);
					     

					}catch(Exception ha) {
						ha.printStackTrace();
					}
					
					
				} else {
					JOptionPane.showMessageDialog(frame, "No selecciono ningun registro :(", "Inane warning",
						    JOptionPane.WARNING_MESSAGE);
				}			
			}
		});
		
		//Se comento la parte netamente funcional porque pide que se vuelva * al eliminar su estado de registro, en este caso será E
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
						String query = "update " + Tname + " set " + k.get(8) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("E", fila, 8);
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
						String query = "update " + Tname + " set " + k.get(8) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("I", fila, 8);
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
						String query = "update " + Tname + " set " + k.get(8) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("A", fila, 8);
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
		frmtdtxtfldId.setBounds(10, 71, 112, 20);
		frmtdtxtfldId.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldId.setText("Id:");
		
		JFormattedTextField frmtdtxtfldDescripcion = new JFormattedTextField();
		frmtdtxtfldDescripcion.setBounds(10, 102, 112, 20);
		frmtdtxtfldDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldDescripcion.setText("Captura Cod:");
		
		JFormattedTextField frmtdtxtfldEstado = new JFormattedTextField();
		frmtdtxtfldEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldEstado.setBounds(235, 195, 99, 20);
		frmtdtxtfldEstado.setText("Estado:");
		
		textField = new JTextField();
		textField.setBounds(140, 75, 56, 20);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(140, 106, 56, 20);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(368, 195, 43, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"A", "I", "*"}));
		JPanel panel = new JPanel();
		panel.setBounds(10, 230, 514, 200);
		panel.setLayout(new BorderLayout(0, 0));
		
		JFormattedTextField frmtdtxtfldLugarCauce = new JFormattedTextField();
		frmtdtxtfldLugarCauce.setBounds(235, 71, 99, 20);
		frmtdtxtfldLugarCauce.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLugarCauce.setText("Mes:");
		frame.getContentPane().setLayout(null);
		adicionar.setBounds(23, 480, 99, 23);
		frame.getContentPane().add(adicionar);
		inactivar.setBounds(23, 514, 99, 23);
		frame.getContentPane().add(inactivar);
		reactivar.setBounds(156, 514, 99, 23);
		frame.getContentPane().add(reactivar);
		modificar.setBounds(156, 480, 99, 23);
		frame.getContentPane().add(modificar);
		actualizar.setBounds(281, 514, 99, 23);
		frame.getContentPane().add(actualizar);
		eliminar.setBounds(281, 480, 99, 23);
		frame.getContentPane().add(eliminar);
		cancelar.setBounds(404, 480, 89, 23);
		frame.getContentPane().add(cancelar);
		salir.setBounds(404, 514, 89, 23);
		frame.getContentPane().add(salir);
		frame.getContentPane().add(panel);
		tableData = new JTable(dtm);
		
		JScrollPane scrollPane = new JScrollPane(tableData);
		panel.add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane().add(frmtdtxtfldDescripcion);
		frame.getContentPane().add(frmtdtxtfldLugarCauce);
		frame.getContentPane().add(lblNewJgoodiesLabel);
		frame.getContentPane().add(frmtdtxtfldId);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(textField_1);
		frame.getContentPane().add(txtpnEstadoDeRegistro);
		frame.getContentPane().add(frmtdtxtfldEstado);
		frame.getContentPane().add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(368, 71, 56, 20);
		frame.getContentPane().add(textField_2);
		
//		JFormattedTextField frmtdtxtfldLugarCauce_1 = new JFormattedTextField();
//		frmtdtxtfldLugarCauce_1.setText("Codigo del Lugar:");
//		frmtdtxtfldLugarCauce_1.setHorizontalAlignment(SwingConstants.RIGHT);
//		frmtdtxtfldLugarCauce_1.setBounds(10, 133, 112, 20);
//		//frame.getContentPane().add(frmtdtxtfldLugarCauce_1);
		
//		textField_3 = new JTextField();
//		textField_3.setColumns(10);
//		textField_3.setBounds(140, 137, 56, 20);
//		frame.getContentPane().add(textField_3);
		
		JFormattedTextField frmtdtxtfldLugarCauce_1_1 = new JFormattedTextField();
		frmtdtxtfldLugarCauce_1_1.setText("Codigo del Evento:");
		frmtdtxtfldLugarCauce_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLugarCauce_1_1.setBounds(10, 164, 112, 20);
		frame.getContentPane().add(frmtdtxtfldLugarCauce_1_1);
		
		JFormattedTextField frmtdtxtfldLugarCauce_1_1_1 = new JFormattedTextField();
		frmtdtxtfldLugarCauce_1_1_1.setText("A\u00F1o:");
		frmtdtxtfldLugarCauce_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLugarCauce_1_1_1.setBounds(10, 195, 112, 20);
		frame.getContentPane().add(frmtdtxtfldLugarCauce_1_1_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(140, 168, 56, 20);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(140, 199, 56, 20);
		frame.getContentPane().add(textField_5);
		
		JFormattedTextField frmtdtxtfldMes = new JFormattedTextField();
		frmtdtxtfldMes.setText("D\u00EDa:");
		frmtdtxtfldMes.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldMes.setBounds(235, 102, 99, 20);
		frame.getContentPane().add(frmtdtxtfldMes);
		
		JFormattedTextField frmtdtxtfldLugarCauce_2_1 = new JFormattedTextField();
		frmtdtxtfldLugarCauce_2_1.setText("Hora:");
		frmtdtxtfldLugarCauce_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLugarCauce_2_1.setBounds(235, 133, 99, 20);
		frame.getContentPane().add(frmtdtxtfldLugarCauce_2_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(368, 102, 56, 20);
		frame.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(368, 133, 56, 20);
		frame.getContentPane().add(textField_7);
		
		JFormattedTextField frmtdtxtfldLugarCauce_2_1_1 = new JFormattedTextField();
		frmtdtxtfldLugarCauce_2_1_1.setText("Minuto:");
		frmtdtxtfldLugarCauce_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLugarCauce_2_1_1.setBounds(235, 164, 99, 20);
		frame.getContentPane().add(frmtdtxtfldLugarCauce_2_1_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(368, 164, 56, 20);
		frame.getContentPane().add(textField_8);
		//eventos();
		
	}
}