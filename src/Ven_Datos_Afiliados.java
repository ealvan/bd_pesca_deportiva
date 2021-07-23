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

public class Ven_Datos_Afiliados {

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
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ven_Datos_Afiliados window = new Ven_Datos_Afiliados("a");
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
		
		String[] datos = new String [10];
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tabla + ";");
			
			while(rs.next()) {
				datos[0] = rs.getString(1);
				datos[1] = rs.getString(2);
				datos[2] = rs.getString(3);
				dtm.addRow(datos);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e + "Error en la consulta");
			// TODO: handle exception
		}
		
	}
	
	
	public Ven_Datos_Afiliados(String type) {
		Tname = type;
		create_Tablas(type);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 570, 670);
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
				
				String id = textField.getText();
				String des = textField_1.getText();
				int estado = (Integer)comboBox.getSelectedIndex();
				String auxS;
				
				
				if (estado == 0) {
					auxS = "A";
				} else if (estado == 1) {
					auxS = "I";
				} else {
					auxS = "E";
				}
				
				
				if(id != "" && des !="" ) {
					Object [] row = {
							id, des, auxS,
						};
						dtm.addRow(row);
				}
				
				
				try {
					
					ArrayList <String> k = getColumns(Tname,con);
					
					String columns = "(" + k.get(0) + ", " + k.get(1) + ", " + k.get(2) + ")";
					
					String query = " insert into " + Tname + columns + "\n values (?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setInt 	(1, Integer.parseInt(id));
				    preparedStmt.setString 	(2, des);
				    preparedStmt.setString 	(3, auxS);
				      
					preparedStmt.execute();
					
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f + "Error en al a�adir");
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
					
					int id = Integer.parseInt(tableData.getValueAt(fila, 0).toString());		
					String des = tableData.getValueAt(fila, 1).toString();
					String estado = tableData.getValueAt(fila, 2).toString();
					textField.setText(String.valueOf(id));
					textField_1.setText(des);
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
					String des = textField_1.getText();
					String estado = comboBox.getSelectedItem().toString();
					
					
					try {
						
						ArrayList <String> k = getColumns(Tname,con);
						String query = "update " + Tname + " set " + k.get(1) + " = ?, " + k.get(2) + " = ?   where " + k.get(0) + " = ?";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, des);
						preparedStmt.setString	(2, estado);
						preparedStmt.setInt		(3, id);
						preparedStmt.execute();
						
					    System.out.println("Se Actualizo Exitosamente el Registro numero : "+ id);
					    JOptionPane.showMessageDialog(frame, "Se Modifico exitosamente!!");
						//poniendo los datos, actualizados
					    DefaultTableModel model = (DefaultTableModel)tableData.getModel();
					    model.setValueAt(String.valueOf(id), fila, 0);
					    model.setValueAt(des, fila, 1);
					    model.setValueAt(estado, fila, 2);
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
						String query = "update " + Tname + " set " + k.get(2) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("E", fila, 2);
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
						String query = "update " + Tname + " set " + k.get(2) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("I", fila, 2);
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
						String query = "update " + Tname + " set " + k.get(2) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("A", fila, 2);
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
		frmtdtxtfldDescripcion.setBounds(10, 102, 90, 20);
		frmtdtxtfldDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldDescripcion.setText("Nombre:");
		
		JFormattedTextField frmtdtxtfldEstado = new JFormattedTextField();
		frmtdtxtfldEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldEstado.setBounds(264, 164, 87, 20);
		frmtdtxtfldEstado.setText("Estado:");
		
		textField = new JTextField();
		textField.setBounds(110, 71, 42, 20);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(110, 102, 115, 20);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(362, 164, 43, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"A", "I", "*"}));
		JPanel panel = new JPanel();
		panel.setBounds(10, 299, 534, 213);
		panel.setLayout(new BorderLayout(0, 0));
		
		JFormattedTextField frmtdtxtfldComunidad = new JFormattedTextField();
		frmtdtxtfldComunidad.setBounds(10, 133, 90, 20);
		frmtdtxtfldComunidad.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldComunidad.setText("Apellido Pat:");
		
		textField_2 = new JTextField();
		textField_2.setBounds(110, 133, 115, 20);
		textField_2.setColumns(10);
		
		JFormattedTextField frmtdtxtfldLugarCauce = new JFormattedTextField();
		frmtdtxtfldLugarCauce.setBounds(261, 71, 90, 20);
		frmtdtxtfldLugarCauce.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLugarCauce.setText("Pais Cod:");
		
		textField_3 = new JTextField();
		textField_3.setBounds(361, 71, 102, 20);
		textField_3.setColumns(10);
		frame.getContentPane().setLayout(null);
		adicionar.setBounds(33, 542, 99, 23);
		frame.getContentPane().add(adicionar);
		inactivar.setBounds(33, 576, 99, 23);
		frame.getContentPane().add(inactivar);
		reactivar.setBounds(166, 576, 99, 23);
		frame.getContentPane().add(reactivar);
		modificar.setBounds(166, 542, 99, 23);
		frame.getContentPane().add(modificar);
		actualizar.setBounds(291, 576, 99, 23);
		frame.getContentPane().add(actualizar);
		eliminar.setBounds(291, 542, 99, 23);
		frame.getContentPane().add(eliminar);
		cancelar.setBounds(414, 542, 89, 23);
		frame.getContentPane().add(cancelar);
		salir.setBounds(414, 576, 89, 23);
		frame.getContentPane().add(salir);
		frame.getContentPane().add(panel);
		tableData = new JTable(dtm);
		
		JScrollPane scrollPane = new JScrollPane(tableData);
		panel.add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane().add(frmtdtxtfldDescripcion);
		frame.getContentPane().add(frmtdtxtfldComunidad);
		frame.getContentPane().add(frmtdtxtfldLugarCauce);
		frame.getContentPane().add(lblNewJgoodiesLabel);
		frame.getContentPane().add(frmtdtxtfldId);
		frame.getContentPane().add(textField_3);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(textField_1);
		frame.getContentPane().add(textField_2);
		frame.getContentPane().add(txtpnEstadoDeRegistro);
		frame.getContentPane().add(frmtdtxtfldEstado);
		frame.getContentPane().add(comboBox);
		
		JFormattedTextField frmtdtxtfldApellidoMat = new JFormattedTextField();
		frmtdtxtfldApellidoMat.setText("Apellido Mat:");
		frmtdtxtfldApellidoMat.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldApellidoMat.setBounds(10, 164, 90, 20);
		frame.getContentPane().add(frmtdtxtfldApellidoMat);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(110, 164, 115, 20);
		frame.getContentPane().add(textField_4);
		
		JFormattedTextField frmtdtxtfldIncricionAo = new JFormattedTextField();
		frmtdtxtfldIncricionAo.setText("Incricion A\u00F1o:");
		frmtdtxtfldIncricionAo.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldIncricionAo.setBounds(10, 195, 90, 20);
		frame.getContentPane().add(frmtdtxtfldIncricionAo);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(110, 195, 115, 20);
		frame.getContentPane().add(textField_5);
		
		JFormattedTextField frmtdtxtfldIncricionMes = new JFormattedTextField();
		frmtdtxtfldIncricionMes.setText("Incricion Mes:");
		frmtdtxtfldIncricionMes.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldIncricionMes.setBounds(10, 226, 90, 20);
		frame.getContentPane().add(frmtdtxtfldIncricionMes);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(110, 226, 115, 20);
		frame.getContentPane().add(textField_6);
		
		JFormattedTextField frmtdtxtfldIncricionDa = new JFormattedTextField();
		frmtdtxtfldIncricionDa.setText("Incricion D\u00EDa");
		frmtdtxtfldIncricionDa.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldIncricionDa.setBounds(10, 257, 90, 20);
		frame.getContentPane().add(frmtdtxtfldIncricionDa);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(110, 257, 115, 20);
		frame.getContentPane().add(textField_7);
		
		JFormattedTextField frmtdtxtfldEstadoCivil = new JFormattedTextField();
		frmtdtxtfldEstadoCivil.setText("Estado civil Cod:");
		frmtdtxtfldEstadoCivil.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldEstadoCivil.setBounds(261, 102, 90, 20);
		frame.getContentPane().add(frmtdtxtfldEstadoCivil);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(361, 102, 102, 20);
		frame.getContentPane().add(textField_8);
		
		JFormattedTextField frmtdtxtfldLicenciaCod = new JFormattedTextField();
		frmtdtxtfldLicenciaCod.setText("Licencia Cod:");
		frmtdtxtfldLicenciaCod.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLicenciaCod.setBounds(261, 133, 90, 20);
		frame.getContentPane().add(frmtdtxtfldLicenciaCod);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(361, 133, 102, 20);
		frame.getContentPane().add(textField_9);
		eventos();
		
	}
}