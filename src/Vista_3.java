import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.sun.jdi.connect.spi.Connection;

public class Vista_3 extends JFrame {
//	Conexion con = new Conexion();
//	Connection conn = con.getConn();
	Conexion a = new Conexion();
	Color backLabel = new Color(70, 161, 206);
	JPanel panel;
	JLabel titulo;
	JTable table;
	JLabel c1;
	JLabel c2;
	JLabel c3;
	JLabel c4;
	JLabel c5;
	JLabel c6;
	JLabel c7;

	JTextField i1;
	JTextField i2;
	JTextField i3;
	JTextField i4;
	JTextField i5;
	JTextField i6; 
	JTextField i7;
	final int x = 30;
	final int y_init = 60;
	final int y_inter = 20;
	//ahora la caja del label
	final int sy = 30;
	int sx;
	DefaultTableModel dtm;
	public Vista_3() {
		setSize(750,  600);
		setTitle("Vista Afiliado con Referenciales");
		setLocation(130, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		init_components();
		eventos();
	}
	public void init_components(){
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(31, 97, 141  ));
		//panel.setBackground(Color.BLACK); //--> no cambia a negro el panel
		this.getContentPane().add(panel);
		
		init_labels();
		init_inputs();
		init_img();
	}
	public void init_labels() {
		Font font = new Font("georgia", Font.BOLD, 14);
		Font fontTitle = new Font("georgia", Font.BOLD, 20);
		JLabel titulo = new JLabel(" Vista Afiliado con Referenciales.");
		titulo.setFont(fontTitle);
		titulo.setForeground(Color.CYAN);
		titulo.setOpaque(true);
		titulo.setBackground(Color.black);
		titulo.setBounds(125, 10, 350, 40);
		panel.add(titulo);
		
		
		String[] labels = {
				"Estado de Registro",
				"Licencia",
				"Nombre Afiliado",
				"Apellido Paterno",
				"Apellido Materno",
				"Estado Civil",
				"Pais"
		};
		//sistema de pposicionamiiento propio
		//
		
		sx = 160;
		c1 = new JLabel(labels[0]);
		c1.setOpaque(true);
		c1.setBackground(backLabel);
		c1.setBounds(x, y_init, sx, sy);

		
		sx = 80;
		c2 = new JLabel(labels[1]);
		c2.setOpaque(true);
		c2.setBackground(backLabel);
		c2.setBounds(x, y_init + sy+ y_inter, sx, sy);
		

		
		sx = 150;
		c3 = new JLabel(labels[2]);
		c3.setOpaque(true);
		c3.setBackground(backLabel);
		c3.setBounds(x, y_init + sy*2+ y_inter*2 , sx, sy);
		

		
		sx = 160;
		c4 = new JLabel(labels[3]);
		c4.setOpaque(true);
		c4.setBackground(backLabel);
		c4.setBounds(x, y_init + sy*3+ y_inter*3 , sx, sy);
		 
		c5 = new JLabel(labels[4]);
		c5.setOpaque(true);
		c5.setBackground(backLabel);
		c5.setBounds(x, y_init + sy*4+ y_inter*4 , sx, sy);
		sx= 100;
		c6 = new JLabel(labels[5]);
		c6.setOpaque(true);
		c6.setBackground(backLabel);
		c6.setBounds(x, y_init + sy*5+ y_inter*5 , sx, sy);
		
		c7 = new JLabel(labels[6]);
		c7.setOpaque(true);
		c7.setBackground(backLabel);
		c7.setBounds(x, y_init + sy*6+ y_inter*6 , sx, sy);
		
		
		c1.setFont(font);
		c2.setFont(font);
		c3.setFont(font);
		c4.setFont(font);
		c5.setFont(font);
		c6.setFont(font);
		c7.setFont(font);
		
		panel.add(c1);
		panel.add(c2);
		panel.add(c3);
		panel.add(c4);
		panel.add(c5);
		panel.add(c6);
		panel.add(c7);

	}
	public void init_inputs() {
		
		//label
		int six = 80;
		int siy = 25;
		
		i1 = new JTextField();
		i1.setBounds(x+sx+80,y_init+4,six, siy);
		panel.add(i1);
		six = 100;
		
		i2 = new JTextField();
		i2.setBounds(x+sx+80, y_init + sy+ y_inter,six, siy);
		panel.add(i2);
		six = 100;
		
		i3 = new JTextField();
		i3.setBounds(x+sx+80, y_init + sy*2+ y_inter*2 ,six, siy);
		panel.add(i3);
		six = 100;
		
		i4 = new JTextField();
		i4.setBounds(x+sx +80,  y_init + sy*3+ y_inter*3  ,six, siy);
		panel.add(i4);
 
		
		i5 = new JTextField();
		i5.setBounds(x+sx +80,  y_init + sy*4+ y_inter*4  ,six, siy);
		panel.add(i5);
		
		i6 = new JTextField();
		i6.setBounds(x+sx+80,  y_init + sy*5+ y_inter*6  ,six, siy);
		panel.add(i6);
		
		i7 = new JTextField();
		i7.setBounds(x+sx+80,  y_init + sy*6+ y_inter*6  ,six, siy);
		panel.add(i7);
		
		init_table(x+sx+10, y_init + sy*6+ y_inter*6);
		
//		i1.setEnabled(false);
//		i2.setEnabled(false);
//		i3.setEnabled(false);
//		i4.setEnabled(false);
		
	
	}
	public void init_table(int x, int y) {
		String[] cols = {
				"LugNom",
				"LugTipDes",
				"PecNom",
				"PecDes"
		};
		
		
		//ArrayList<String[] > dat = Conexion.getData("lugares_peces",);
		Object[][] data = {
				{"Kathy", "Smith", "Snowboarding",  new Boolean(false)},
				    {"John", "Doe","Rowing", new Boolean(true)},
				    {"Sue", "Black","Knitting", new Boolean(false)},
				    {"Jane", "White", "Speed reading",  new Boolean(true)},
				    {"Joe", "Brown","Pool",new Boolean(false)}
				
		};
		dtm = new DefaultTableModel( );
		ArrayList<String[] > dat = a.getDataView("afiliados_referenciales");
		dtm.setColumnIdentifiers(dat.get(0));
		
		for(int q =1; q < dat.size(); q++) { 
			dtm.addRow(dat.get(q));
		}
		
		table = new JTable(dtm);	
		int ytable = y + 90;
		int highHeader = 30;
		int ybody = ytable+highHeader;
		int wtable = 660;
		table.getTableHeader().setBounds(x-100,ytable,wtable,highHeader);
		table.setBounds(x-100,ybody,wtable,90);
		//style of JTable
		table.setBackground(new Color(69, 179, 157 ));
		table.setForeground(new Color(236, 240, 241 ));
		Font ft = new Font("georgia",Font.PLAIN, 14);
		table.setFont(ft);
		table.getTableHeader().setFont(new Font("georgia",Font.BOLD, 15));
		table.getTableHeader().setForeground(new Color(46, 64, 83));
		table.getTableHeader().setBackground(new Color(118, 215, 196 ));
		
		panel.add(table.getTableHeader());
		panel.add(table);
//		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setViewportView(table);
//		//table.setFillsViewportHeight(true);
//		table.setBounds(30, 300, 200, 200 );
//		add(scrollPane);
//		add(scrollPane);
	}
	public void init_img() {
		ImageIcon logo = new ImageIcon("fishing.jpg");
		JLabel img = new JLabel( );
		int width = 300;
		img.setBounds(360,110, width, width - 30);
		img.setIcon(new ImageIcon(logo.getImage().getScaledInstance(img.getWidth()
				, img.getHeight()
				, Image.SCALE_SMOOTH)));
		panel.add(img);
	}
	public void eventos() {
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	            //System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	        	int row = table.getSelectedRow();
	        	if(row != -1) {
	        		String[] rowSelect = new String[7];
	        		for(int i =0; i < rowSelect.length; i++) {
	        			rowSelect[i] = table.getValueAt(row, i).toString();
	        		}
	        		i1.setText(rowSelect[0]);
	        		i2.setText(rowSelect[1]);
	        		i3.setText(rowSelect[2]);
	        		i4.setText(rowSelect[3]);
	        		i5.setText(rowSelect[4]);
	        		i6.setText(rowSelect[5]);
	        		i7.setText(rowSelect[6]);
	        		
	        	}else {
	        		JOptionPane.showMessageDialog(panel, "A deprecated call", "Warning",
	        		        JOptionPane.WARNING_MESSAGE);
	        	}
	        	
	        }
	    });
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vista_3 a = new Vista_3();
		
	}

}
