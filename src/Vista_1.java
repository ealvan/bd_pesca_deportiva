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

public class Vista_1 extends JFrame {
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
	JTextField i1;
	JTextField i2;
	JTextField i3;
	JTextField i4;
	final int x = 30;
	final int y_init = 60;
	final int y_inter = 20;
	//ahora la caja del label
	final int sy = 30;
	int sx;
	DefaultTableModel dtm;
	public Vista_1() {
		setSize(621,  600);
		setTitle("Vista Lugares_Peces");
		setLocation(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		init_components();
		eventos();
	}
	public void init_components(){
		panel = new JPanel();
		panel.setBackground(new Color(31, 97, 141  ));
		//panel.setBackground(Color.BLACK); //--> no cambia a negro el panel
		this.getContentPane().add(panel);
		
		init_labels();
		init_inputs();
		init_img();
	}
	public void init_labels() {
		Font font = new Font("georgia", Font.BOLD, 20);
		panel.setLayout(null);
		JLabel titulo = new JLabel(" Vista Lugares Peces");
		titulo.setBounds(125, 10, 230, 40);
		titulo.setFont(font);
		titulo.setForeground(Color.CYAN);
		titulo.setOpaque(true);
		titulo.setBackground(Color.black);
		panel.add(titulo);
		
		
		String[] labels = {
				"LugNom",
				"LugTipDes",
				"PecNom",
				"PecDes"
		};
		//sistema de pposicionamiiento propio
		//
		
		sx = 90;
		c1 = new JLabel(labels[0]);
		c1.setBounds(40, 61, 90, 25);
		c1.setOpaque(true);
		c1.setBackground(backLabel);

		
		sx = 120;
		c2 = new JLabel(labels[1]);
		c2.setBounds(10, 105, 120, 25);
		c2.setOpaque(true);
		c2.setBackground(backLabel);
		

		
		sx = 100;
		c3 = new JLabel(labels[2]);
		c3.setBounds(30, 163, 100, 25);
		c3.setOpaque(true);
		c3.setBackground(backLabel);
		

		
		sx = 90;
		c4 = new JLabel(labels[3]);
		c4.setBounds(40, 210, 90, 25);
		c4.setOpaque(true);
		c4.setBackground(backLabel);
		
		c1.setFont(font);
		c2.setFont(font);
		c3.setFont(font);
		c4.setFont(font);
		
		panel.add(c1);
		panel.add(c2);
		panel.add(c3);
		panel.add(c4);
	}
	public void init_inputs() {
		
		//label
		int six = 80;
		int siy = 25;
		
		i1 = new JTextField();
		i1.setBounds(140, 61, 90, 25);
		panel.add(i1);
		six = 100;
		
		i2 = new JTextField();
		i2.setBounds(140, 105, 99, 25);
		panel.add(i2);
		six = 100;
		
		i3 = new JTextField();
		i3.setBounds(140, 166, 100, 25);
		panel.add(i3);
		six = 100;
		
		i4 = new JTextField();
		i4.setBounds(140, 208, 100, 27);
		panel.add(i4);
		init_table(x+sx+10, y_init + sy*4+ y_inter*4);
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
		
		dtm = new DefaultTableModel( );
		ArrayList<String[] > dat = a.getDataView("lugares_peces");
		dtm.setColumnIdentifiers(cols);
		
		for(int q =1; q < dat.size(); q++) { 
			dtm.addRow(dat.get(q));
		}
		
		table = new JTable(dtm);	
//		table.setBounds(30, 290, 450, 90);
//		table.getTableHeader().setBounds(x-100,y,450,30);
		//style of JTable
		table.setBackground(new Color(69, 179, 157 ));
		table.setForeground(new Color(236, 240, 241 ));
		Font ft = new Font("georgia",Font.PLAIN, 14);
		table.setFont(ft);
		table.getTableHeader().setFont(new Font("georgia",Font.BOLD, 15));
		table.getTableHeader().setForeground(new Color(46, 64, 83));
		table.getTableHeader().setBackground(new Color(118, 215, 196 ));
		
		JScrollPane paneScroll = new JScrollPane();
		paneScroll.setViewportView(table);
		paneScroll.setBounds(30, 290, 450, 90); 
		panel.add(paneScroll);

	}
	public void init_img() {
		ImageIcon logo = new ImageIcon("fishing.jpg");
		JLabel img = new JLabel( );
		img.setBounds(300, 60, 200, 180);
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
	        		String[] rowSelect = new String[4];
	        		for(int i =0; i < rowSelect.length; i++) {
	        			rowSelect[i] = table.getValueAt(row, i).toString();
	        		}
	        		i1.setText(rowSelect[0]);
	        		i2.setText(rowSelect[1]);
	        		i3.setText(rowSelect[2]);
	        		i4.setText(rowSelect[3]);
	        		
	        		
	        	}else {
	        		JOptionPane.showMessageDialog(panel, "A deprecated call", "Warning",
	        		        JOptionPane.WARNING_MESSAGE);
	        	}
	        	
	        }
	    });
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vista_1 a = new Vista_1();
		
	}

}
