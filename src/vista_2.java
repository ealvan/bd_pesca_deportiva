import java.awt.Color;
import java.awt.Dimension;
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

public class vista_2 extends JFrame {
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
	
	JTextField i1;
	JTextField i2;
	JTextField i3;
	JTextField i4;
	JTextField i5;
	JTextField i6; 
	final int x = 30;
	final int y_init = 60;
	final int y_inter = 20;
	//ahora la caja del label
	final int sy = 30;
	int sx;
	DefaultTableModel dtm;
	public vista_2() {
		setSize(536,  519);
		setTitle("Vista Lugares_Peces");
		setLocation(500, 300);
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
		Font font = new Font("georgia", Font.BOLD, 20);
		JLabel titulo = new JLabel(" Vista Eventos Alfiliados");
		titulo.setFont(font);
		titulo.setForeground(Color.CYAN);
		titulo.setOpaque(true);
		titulo.setBackground(Color.black);
		titulo.setBounds(108, 40, 260, 40);
		panel.add(titulo);
		
		
		String[] labels = {
				"Nombre",
				"Apellido",
				"Evento",
				"Precio",
				"posicion",
				"trofeo"
		};
		//sistema de pposicionamiiento propio
		//
		
		sx = 90;
		c1 = new JLabel(labels[0]);
		c1.setOpaque(true);
		c1.setBackground(backLabel);
		c1.setBounds(40, 106, 90, 25);

		
		sx = 120;
		c2 = new JLabel(labels[1]);
		c2.setOpaque(true);
		c2.setBackground(backLabel);
		c2.setBounds(10, 142, 120, 25);
		

		
		sx = 100;
		c3 = new JLabel(labels[2]);
		c3.setOpaque(true);
		c3.setBackground(backLabel);
		c3.setBounds(30, 178 , sx, sy);
		

		
		sx = 90;
		c4 = new JLabel(labels[3]);
		c4.setOpaque(true);
		c4.setBackground(backLabel);
		c4.setBounds(30, 219 , 100, 30);
		
		c5 = new JLabel(labels[4]);
		c5.setOpaque(true);
		c5.setBackground(backLabel);
		c5.setBounds(30, 260 , 100, 30);
		
		c6 = new JLabel(labels[5]);
		c6.setOpaque(true);
		c6.setBackground(backLabel);
		c6.setBounds(30, 304 , 100, 30);
		
		c1.setFont(font);
		c2.setFont(font);
		c3.setFont(font);
		c4.setFont(font);
		c5.setFont(font);
		c6.setFont(font);
		panel.add(c1);
		panel.add(c2);
		panel.add(c3);
		panel.add(c4);
		panel.add(c5);
		panel.add(c6);
	}
	public void init_inputs() {
		
		//label
		int six = 80;
		int siy = 25;
		
		i1 = new JTextField();
		i1.setBounds(145,109,six, siy);
		panel.add(i1);
		six = 100;
		
		i2 = new JTextField();
		i2.setBounds(145, 145,six, siy);
		panel.add(i2);
		six = 100;
		
		i3 = new JTextField();
		i3.setBounds(145, 184 ,six, siy);
		panel.add(i3);
		six = 100;
		
		i4 = new JTextField();
		i4.setBounds(145,  225  ,100, 25);
		panel.add(i4);
 
		
		i5 = new JTextField();
		i5.setBounds(145,  261  ,100, 25);
		panel.add(i5);
		
		i6 = new JTextField();
		i6.setBounds(145,  310  ,100, 25);
		panel.add(i6);
		
		init_table(x+sx+10, y_init + sy*5+ y_inter*5);
		
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
		dtm = new DefaultTableModel( );
		ArrayList<String[] > dat = a.getDataView("eventos_afiliados");
		dtm.setColumnIdentifiers(dat.get(0));
		
		for(int q =1; q < dat.size(); q++) { 
			dtm.addRow(dat.get(q));
		}
		
		table = new JTable(dtm);	
		int ytable = y + 90;
		int highHeader = 30;
		int ybody = ytable+highHeader;
//		table.getTableHeader().setBounds(x-100,ytable,450,highHeader);
//		table.setBounds(x-100,ybody,450,90);
		//style of JTable
		table.setBackground(new Color(69, 179, 157 ));
		table.setForeground(new Color(236, 240, 241 ));
		Font ft = new Font("georgia",Font.PLAIN, 14);
		table.setFont(ft);
		table.getTableHeader().setFont(new Font("georgia",Font.BOLD, 15));
		table.getTableHeader().setForeground(new Color(46, 64, 83));
		table.getTableHeader().setBackground(new Color(118, 215, 196 ));
		
//		panel.add(table.getTableHeader());
//		panel.add(table);
		//table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		JScrollPane paneScroll = new JScrollPane();
		paneScroll.setViewportView(table);
		paneScroll.setBounds(31,363,450,90); 
		panel.add(paneScroll);
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
		img.setBounds(300,110, 210, 190);
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
	        		String[] rowSelect = new String[6];
	        		for(int i =0; i < rowSelect.length; i++) {
	        			rowSelect[i] = table.getValueAt(row, i).toString();
	        		}
	        		i1.setText(rowSelect[0]);
	        		i2.setText(rowSelect[1]);
	        		i3.setText(rowSelect[2]);
	        		i4.setText(rowSelect[3]);
	        		i5.setText(rowSelect[4]);
	        		i6.setText(rowSelect[5]);
	        		
	        		
	        	}else {
	        		JOptionPane.showMessageDialog(panel, "A deprecated call", "Warning",
	        		        JOptionPane.WARNING_MESSAGE);
	        	}
	        	
	        }
	    });
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		vista_2 a = new vista_2();
		
	}

}
