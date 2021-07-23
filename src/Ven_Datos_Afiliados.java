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
	private JTextField id_cod;
	private JTextField nombre;
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
	private JTextField pat;
	private JTextField pais;
	private JTextField mat;
	private JTextField anio;
	private JTextField mes;
	private JTextField dia;
	private JTextField est_civil;
	private JTextField lic_cod;

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
		dtm.addColumn(getColumns(tabla,con).get(3));
		dtm.addColumn(getColumns(tabla,con).get(4));
		dtm.addColumn(getColumns(tabla,con).get(5));
		dtm.addColumn(getColumns(tabla,con).get(6));
		dtm.addColumn(getColumns(tabla,con).get(7));
		dtm.addColumn(getColumns(tabla,con).get(8));
		dtm.addColumn(getColumns(tabla,con).get(9));
		dtm.addColumn(getColumns(tabla,con).get(10));
		
		String[] datos = new String [11];
		
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
				datos[9] = rs.getString(10);
				datos[10] = rs.getString(11);
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
				
				String id = id_cod.getText();
				String nom = nombre.getText();
				String apat = pat.getText();
				String amat = mat.getText();
				String ianio = anio.getText();
				String imes = mes.getText();
				String idia = dia.getText();
				String pai = pais.getText();
				String e_C = est_civil.getText();
				String lic = lic_cod.getText();
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
					
					String columns = "(" + k.get(0) + ", " + k.get(1) + ", " + k.get(2) + ", " + k.get(3) + ", " + k.get(4) + ", " + k.get(5) + ", " + k.get(6) + ", " + k.get(7) + ", " + k.get(8) + ", " + k.get(9) + ", " + k.get(10) + ")";
					
					String query = " insert into " + Tname + columns + "\n values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setInt 	(1, Integer.parseInt(id));
				    preparedStmt.setString 	(2, nom);
				    preparedStmt.setString 	(3, apat);
				    preparedStmt.setString 	(4, amat);
				    preparedStmt.setInt 	(5, Integer.parseInt(ianio));
				    preparedStmt.setInt 	(6, Integer.parseInt(imes));
				    preparedStmt.setInt 	(7, Integer.parseInt(idia));
				    preparedStmt.setInt 	(8, Integer.parseInt(pai));
				    preparedStmt.setInt 	(9, Integer.parseInt(e_C));
				    preparedStmt.setInt 	(10, Integer.parseInt(lic));
				    preparedStmt.setString 	(11, auxS);
				      
					preparedStmt.execute();
					
					Object [] row = {id, 2, nom, apat,amat,ianio,imes,idia,pai,e_C,lic,auxS};
					dtm.addRow(row);
					
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, f + "Error en al añadir");
					// TODO: handle exception
				}
				
				id_cod.setText("");
				nombre.setText("");
				pat.setText("");
				mat.setText("");
				anio.setText("");
				mes.setText("");
				dia.setText("");
				pais.setText("");
				est_civil.setText("");
				lic_cod.setText("");
				
				System.out.println("Adicionar");
				
			}
		});
		tableData.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	int fila = tableData.getSelectedRow();
				
				if (fila != -1) {
					//Codigo Funcional
					
					
					String id = 	tableData.getValueAt(fila, 0).toString();
					String nom = 	tableData.getValueAt(fila, 1).toString();
					String apat = 	tableData.getValueAt(fila, 2).toString();
					String amat = 	tableData.getValueAt(fila, 3).toString();
					String ianio = 	tableData.getValueAt(fila, 4).toString();
					String imes = 	tableData.getValueAt(fila, 5).toString();
					String idia = 	tableData.getValueAt(fila, 6).toString();
					String pai = 	tableData.getValueAt(fila, 7).toString();
					String e_C = 	tableData.getValueAt(fila, 8).toString();
					String lic = 	tableData.getValueAt(fila, 9).toString();
					String estado = tableData.getValueAt(fila, 10).toString();
					
					id_cod.setText(String.valueOf(id));
					nombre.setText(String.valueOf(nom));
					pat.setText(String.valueOf(apat));
					mat.setText(String.valueOf(amat));
					anio.setText(String.valueOf(ianio));
					mes.setText(String.valueOf(imes));
					dia.setText(String.valueOf(idia));
					pais.setText(String.valueOf(pai));
					est_civil.setText(String.valueOf(e_C));
					lic_cod.setText(String.valueOf(lic));
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
					
					int id = 		Integer.parseInt(id_cod.getText());
					String nom = 	nombre.getText();
					String apat = 	pat.getText();
					String amat = 	mat.getText();
					int ianio = 	Integer.parseInt(anio.getText());
					int imes = 		Integer.parseInt(mes.getText());
					int idia = 		Integer.parseInt(dia.getText());
					int pai = 		Integer.parseInt(pais.getText());
					int e_C = 		Integer.parseInt(est_civil.getText());
					int lic = 		Integer.parseInt(lic_cod.getText());
					String estado = comboBox.getSelectedItem().toString();

					
					
					try {
						
						ArrayList <String> k = getColumns(Tname,con);
						String query = "update " + Tname + " set " + k.get(1) + " = ?, " + k.get(2) + " = ?, " + k.get(3) + " = ?, " + k.get(4) + " = ?, " + k.get(5) + " = ?, " + k.get(6) + " = ?, " + k.get(7) + " = ?, " + k.get(8) + " = ?, " + k.get(9) + " = ?, " + k.get(10) + " = ?   where " + k.get(0) + " = ?";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, nom);
						preparedStmt.setString	(2, apat);
						preparedStmt.setString	(3, amat);
						preparedStmt.setInt		(4, ianio);
						preparedStmt.setInt		(5, imes);
						preparedStmt.setInt		(6, idia);
						preparedStmt.setInt		(7, pai);
						preparedStmt.setInt		(8, e_C);
						preparedStmt.setInt		(9, lic);
						preparedStmt.setString	(10, estado);
						preparedStmt.setInt		(11, id);
						preparedStmt.execute();
						
					    System.out.println("Se Actualizo Exitosamente el Registro numero : "+ id);
					    JOptionPane.showMessageDialog(frame, "Se Modifico exitosamente!!");
						//poniendo los datos, actualizados
					    DefaultTableModel model = (DefaultTableModel)tableData.getModel();
					    model.setValueAt(String.valueOf(id), fila, 0);
					    model.setValueAt(nom, fila, 1);
					    model.setValueAt(apat, fila, 2);
					    model.setValueAt(amat, fila, 2);
					    model.setValueAt(String.valueOf(ianio), fila, 0);
					    model.setValueAt(String.valueOf(imes), fila, 0);
					    model.setValueAt(String.valueOf(idia), fila, 0);
					    model.setValueAt(String.valueOf(pai), fila, 0);
					    model.setValueAt(String.valueOf(e_C), fila, 0);
					    model.setValueAt(String.valueOf(lic), fila, 0);
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
						String query = "update " + Tname + " set " + k.get(10) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("E", fila, 10);
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
						String query = "update " + Tname + " set " + k.get(10) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("I", fila, 10);
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
						String query = "update " + Tname + " set " + k.get(10) + " = ? where " + k.get(0) + " = ?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString	(1, estado);
						preparedStmt.setInt		(2, id);
						preparedStmt.execute();
						tableData.setValueAt("A", fila, 10);
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
		
		id_cod = new JTextField();
		id_cod.setBounds(110, 71, 42, 20);
		id_cod.setColumns(10);
		
		nombre = new JTextField();
		nombre.setBounds(110, 102, 115, 20);
		nombre.setColumns(10);
		
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
		
		pat = new JTextField();
		pat.setBounds(110, 133, 115, 20);
		pat.setColumns(10);
		
		JFormattedTextField frmtdtxtfldLugarCauce = new JFormattedTextField();
		frmtdtxtfldLugarCauce.setBounds(261, 71, 90, 20);
		frmtdtxtfldLugarCauce.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLugarCauce.setText("Pais Cod:");
		
		pais = new JTextField();
		pais.setBounds(361, 71, 102, 20);
		pais.setColumns(10);
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
		frame.getContentPane().add(pais);
		frame.getContentPane().add(id_cod);
		frame.getContentPane().add(nombre);
		frame.getContentPane().add(pat);
		frame.getContentPane().add(txtpnEstadoDeRegistro);
		frame.getContentPane().add(frmtdtxtfldEstado);
		frame.getContentPane().add(comboBox);
		
		JFormattedTextField frmtdtxtfldApellidoMat = new JFormattedTextField();
		frmtdtxtfldApellidoMat.setText("Apellido Mat:");
		frmtdtxtfldApellidoMat.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldApellidoMat.setBounds(10, 164, 90, 20);
		frame.getContentPane().add(frmtdtxtfldApellidoMat);
		
		mat = new JTextField();
		mat.setColumns(10);
		mat.setBounds(110, 164, 115, 20);
		frame.getContentPane().add(mat);
		
		JFormattedTextField frmtdtxtfldIncricionAo = new JFormattedTextField();
		frmtdtxtfldIncricionAo.setText("Incricion A\u00F1o:");
		frmtdtxtfldIncricionAo.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldIncricionAo.setBounds(10, 195, 90, 20);
		frame.getContentPane().add(frmtdtxtfldIncricionAo);
		
		anio = new JTextField();
		anio.setColumns(10);
		anio.setBounds(110, 195, 115, 20);
		frame.getContentPane().add(anio);
		
		JFormattedTextField frmtdtxtfldIncricionMes = new JFormattedTextField();
		frmtdtxtfldIncricionMes.setText("Incricion Mes:");
		frmtdtxtfldIncricionMes.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldIncricionMes.setBounds(10, 226, 90, 20);
		frame.getContentPane().add(frmtdtxtfldIncricionMes);
		
		mes = new JTextField();
		mes.setColumns(10);
		mes.setBounds(110, 226, 115, 20);
		frame.getContentPane().add(mes);
		
		JFormattedTextField frmtdtxtfldIncricionDa = new JFormattedTextField();
		frmtdtxtfldIncricionDa.setText("Incricion D\u00EDa");
		frmtdtxtfldIncricionDa.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldIncricionDa.setBounds(10, 257, 90, 20);
		frame.getContentPane().add(frmtdtxtfldIncricionDa);
		
		dia = new JTextField();
		dia.setColumns(10);
		dia.setBounds(110, 257, 115, 20);
		frame.getContentPane().add(dia);
		
		JFormattedTextField frmtdtxtfldEstadoCivil = new JFormattedTextField();
		frmtdtxtfldEstadoCivil.setText("Estado civil Cod:");
		frmtdtxtfldEstadoCivil.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldEstadoCivil.setBounds(261, 102, 90, 20);
		frame.getContentPane().add(frmtdtxtfldEstadoCivil);
		
		est_civil = new JTextField();
		est_civil.setColumns(10);
		est_civil.setBounds(361, 102, 102, 20);
		frame.getContentPane().add(est_civil);
		
		JFormattedTextField frmtdtxtfldLicenciaCod = new JFormattedTextField();
		frmtdtxtfldLicenciaCod.setText("Licencia Cod:");
		frmtdtxtfldLicenciaCod.setHorizontalAlignment(SwingConstants.RIGHT);
		frmtdtxtfldLicenciaCod.setBounds(261, 133, 90, 20);
		frame.getContentPane().add(frmtdtxtfldLicenciaCod);
		
		lic_cod = new JTextField();
		lic_cod.setColumns(10);
		lic_cod.setBounds(361, 133, 102, 20);
		frame.getContentPane().add(lic_cod);
		
	}
}