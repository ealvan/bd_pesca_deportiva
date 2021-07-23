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

public class Ven_Datos_Eventos_Afiliados {

	public JFrame frame;
	private JTextField textField;
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
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ven_Datos_Eventos_Afiliados window = new Ven_Datos_Eventos_Afiliados("eventos_afi");
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
				dtm.addRow(datos);
			}
			
			tableData = new JTable(dtm);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e + "Error en la consulta");
			// TODO: handle exception
		}
		
	}
	
	
	public Ven_Datos_Eventos_Afiliados(String type) {
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
				int  pos = Integer.parseInt(textField_1.getText());
				int evecod =Integer.parseInt(textField_2.getText());
				int afiCod = Integer.parseInt(textField_3.getText());
				int trocod = Integer.parseInt(textField_4.getText());
				
				int estado = (Integer)comboBox.getSelectedIndex();
				String auxS;
				
				System.out.println(id);
				System.out.println(pos);
				System.out.println(evecod);
				System.out.println(afiCod);
				
				
				if (estado == 0) {
					auxS = "A";
				} else if (estado == 1) {
					auxS = "I";
				} else {
					auxS = "E";
				}
				
				
//				
				try {
					
					String query = " insert into " + Tname + " values (?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setInt 	(1, id);
				    preparedStmt.setInt 	(2, pos);
				    preparedStmt.setInt 	(3, evecod);
				    preparedStmt.setInt 	(4, afiCod);
				    preparedStmt.setInt 	(5, trocod);
				    preparedStmt.setString 	(6, auxS);
					preparedStmt.execute();
					
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f + "Error al añadir");
					// TODO: handle exception
				}
//				
				
				textField.setText("");
				textField_1.setText("");
				textField_3.setText("");
				textField_4.setText("");
			
				System.out.println("Adicionar");
				
			}
		});
		tableData.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	int fila = tableData.getSelectedRow();
				
				if (fila != -1) {
					//Codigo Funcional
					
					String id = tableData.getValueAt(fila, 0).toString();		
					String pos = tableData.getValueAt(fila, 1).toString();
					String evecod = tableData.getValueAt(fila, 2).toString();
					String aficod = tableData.getValueAt(fila, 3).toString();
					String trocod = tableData.getValueAt(fila, 4).toString();
					String estado = tableData.getValueAt(fila, 5).toString();
					
					textField.setText(id);
					textField_1.setText(pos);
					textField_2.setText(evecod);
					textField_3.setText(aficod);
					textField_4.setText(trocod);
					//textField_5.setText(trofeo);
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
					int  pos = Integer.parseInt(textField_1.getText());
					int evecod =Integer.parseInt(textField_2.getText());
					int afiCod = Integer.parseInt(textField_3.getText());
					int trocod = Integer.parseInt(textField_4.getText());
					
					int estado = (Integer)comboBox.getSelectedIndex();
					String auxS;
					
//					System.out.println(id);
//					System.out.println(pos);
//					System.out.println(evecod);
//					System.out.println(afiCod);
					
					
					if (estado == 0) {
						auxS = "A";
					} else if (estado == 1) {
						auxS = "I";
					} else {
						auxS = "E";
					}
					
					
					try {
						
						ArrayList <String> k = getColumns(Tname,con);
						String newRow = k.get(1)+" = ?, "+ k.get(2)+" = ?, "+ k.get(3)+" = ?, "+ k.get(4)+" = ?";
						System.out.println(newRow);
						String query = "update " + Tname + " set " + newRow +" where " + k.get(0) + " = ? ;";
						//UPDATE `bd_pescadeportiva`.`eventos_afi` SET `EveAfiPos` = '2' WHERE (`EveAfiCod` = '8');
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setInt	(1, pos);
						preparedStmt.setInt	(2, evecod);
						preparedStmt.setInt	(3, afiCod);
						preparedStmt.setInt	(4, trocod);
						//preparedStmt.setInt	(5, estado);
						preparedStmt.setInt	(5, id);
						
						preparedStmt.execute();
						
					    System.out.println("Se Actualizo Exitosamente el Registro numero : "+ id);
					    JOptionPane.showMessageDialog(frame, "Se Modifico exitosamente!!");
						//poniendo los datos, actualizados
					    DefaultTableModel model = (DefaultTableModel)tableData.getModel();
					    model.setValueAt(String.valueOf(id), fila, 0);
					    model.setValueAt(String.valueOf(pos), fila, 1);
					    model.setValueAt(String.valueOf(evecod), fila, 2);
					    model.setValueAt(String.valueOf(afiCod), fila, 3);
					    model.setValueAt(String.valueOf(trocod), fila, 4);
					    model.setValueAt(String.valueOf(estado), fila, 5);
					    
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
						String query = "update " + Tname + " set " + k.get(5) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("E", fila, 5);
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
						String query = "update " + Tname + " set " + k.get(5) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("I", fila, 5);
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
		////VISTA
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
						String query = "update " + Tname + " set " + k.get(5) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("A", fila, 5);
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
		ArrayList<String> cols = getColumns(Tname, con);//labels sacados de la bd
		
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
		frmtdtxtfldId.setText(cols.get(0));
		
		JFormattedTextField frmtdtxtfldDescripcion = new JFormattedTextField();
		frmtdtxtfldDescripcion.setBounds(10, 102, 90, 20);
		frmtdtxtfldDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldDescripcion.setText( cols.get(1));
		
		JFormattedTextField frmtdtxtfldEstado = new JFormattedTextField();
		frmtdtxtfldEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldEstado.setBounds(258, 133, 87, 20);
		frmtdtxtfldEstado.setText(cols.get(5));
		
		textField = new JTextField();
		textField.setBounds(110, 71, 42, 20);
		textField.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(355, 133, 43, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"A", "I", "*"}));
		
		JScrollPane scrollPane = new JScrollPane(tableData);
		JPanel panel = new JPanel();
		panel.setBounds(10, 193, 514, 200);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(scrollPane);
		
		JFormattedTextField frmtdtxtfldComunidad = new JFormattedTextField();
		frmtdtxtfldComunidad.setBounds(10, 133, 90, 20);
		frmtdtxtfldComunidad.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldComunidad.setText(cols.get(2));
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
		frame.getContentPane().add(lblNewJgoodiesLabel);
		frame.getContentPane().add(frmtdtxtfldId);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(txtpnEstadoDeRegistro);
		frame.getContentPane().add(frmtdtxtfldEstado);
		frame.getContentPane().add(comboBox);
		
		
		JFormattedTextField frmtdtxtfldAfiliadoCod = new JFormattedTextField();
		frmtdtxtfldAfiliadoCod.setText(cols.get(3));
		frmtdtxtfldAfiliadoCod.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldAfiliadoCod.setBounds(255, 71, 90, 20);
		frame.getContentPane().add(frmtdtxtfldAfiliadoCod);
		
		JFormattedTextField frmtdtxtfldTrofeoCod = new JFormattedTextField();
		frmtdtxtfldTrofeoCod.setText(cols.get(4));
		frmtdtxtfldTrofeoCod.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldTrofeoCod.setBounds(255, 102, 90, 20);
		frame.getContentPane().add(frmtdtxtfldTrofeoCod);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(356, 71, 80, 20);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(356, 102, 80, 20);
		frame.getContentPane().add(textField_4);
		
		textField_1 = new JTextField();
		textField_1.setColumns(40);
		textField_1.setBounds(110, 102, 80, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(110, 133, 80, 20);
		frame.getContentPane().add(textField_2);
		
		
	}
}