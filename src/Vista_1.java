import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

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
		setSize(500,  600);
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
		//panel.setBackground(Color.BLACK); //--> no cambia a negro el panel
		this.getContentPane().add(panel);
		
		init_labels();
		init_inputs();
	}
	public void init_labels() {
		Font font = new Font("arial", Font.ROMAN_BASELINE, 20);
		JLabel titulo = new JLabel(" Vista Lugares Peces");
		titulo.setFont(font);
		titulo.setForeground(Color.CYAN);
		titulo.setOpaque(true);
		titulo.setBackground(Color.black);
		titulo.setBounds(125, 10, 200, 40);
		panel.add(titulo);
		
		
		String[] labels = {
				"LugNom",
				"LugTipDes",
				"PecNom",
				"PecDes"
		};
		//sistema de pposicionamiiento propio
		//
		
		sx = 70;
		c1 = new JLabel(labels[0]);
		c1.setOpaque(true);
		c1.setBackground(Color.pink);
		c1.setBounds(x, y_init, sx, sy);

		
		sx = 80;
		c2 = new JLabel(labels[1]);
		c2.setOpaque(true);
		c2.setBackground(Color.pink);
		c2.setBounds(x, y_init + sy+ y_inter, sx, sy);
		

		
		sx = 80;
		c3 = new JLabel(labels[2]);
		c3.setOpaque(true);
		c3.setBackground(Color.pink);
		c3.setBounds(x, y_init + sy*2+ y_inter*2 , sx, sy);
		

		
		sx = 80;
		c4 = new JLabel(labels[3]);
		c4.setOpaque(true);
		c4.setBackground(Color.pink);
		c4.setBounds(x, y_init + sy*3+ y_inter*3 , sx, sy);
		

		
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
		i1.setBounds(x+sx,y_init+4,six, siy);
		panel.add(i1);
		six = 100;
		
		i2 = new JTextField();
		i2.setBounds(x+sx, y_init + sy+ y_inter,six, siy);
		panel.add(i2);
		six = 100;
		
		i3 = new JTextField();
		i3.setBounds(x+sx, y_init + sy*2+ y_inter*2 ,six, siy);
		panel.add(i3);
		six = 100;
		
		i4 = new JTextField();
		i4.setBounds(x+sx,  y_init + sy*3+ y_inter*3  ,six, siy);
		panel.add(i4);
		init_table(x+sx, y_init + sy*4+ y_inter*4);
		i1.setEnabled(false);
		i2.setEnabled(false);
		i3.setEnabled(false);
		i4.setEnabled(false);
		
		
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
		dtm = new DefaultTableModel(data, cols);
		Conexion a = new Conexion();
		Connection con = (Connection) a.getConn();
		ArrayList<String[] > dat = Conexion.getData("lugares_peces", (java.sql.Connection) con);
		
		
		table = new JTable(dtm);	
		table.getTableHeader().setBounds(x-50,y,350,30);
		table.setBounds(x-50,y+30,350,90);
		panel.add(table.getTableHeader());
		panel.add(table);
//		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setViewportView(table);
//		//table.setFillsViewportHeight(true);
//		table.setBounds(30, 300, 200, 200 );
//		add(scrollPane);
//		add(scrollPane);
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
