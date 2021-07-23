import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Vista_1 extends JFrame {
	JPanel panel;
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
		JLabel c1 = new JLabel(labels[0]);
		c1.setOpaque(true);
		c1.setBackground(Color.pink);
		c1.setBounds(x, y_init, sx, sy);

		
		sx = 80;
		JLabel c2 = new JLabel(labels[1]);
		c2.setOpaque(true);
		c2.setBackground(Color.pink);
		c2.setBounds(x, y_init + sy+ y_inter, sx, sy);
		

		
		sx = 80;
		JLabel c3 = new JLabel(labels[2]);
		c3.setOpaque(true);
		c3.setBackground(Color.pink);
		c3.setBounds(x, y_init + sy*2+ y_inter*2 , sx, sy);
		

		
		sx = 80;
		JLabel c4 = new JLabel(labels[3]);
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
		
		JTextField i1 = new JTextField();
		i1.setBounds(x+sx,y_init+4,six, siy);
		panel.add(i1);
		six = 100;
		JTextField i2 = new JTextField();
		i2.setBounds(x+sx, y_init + sy+ y_inter,six, siy);
		panel.add(i2);
		six = 100;
		JTextField i3 = new JTextField();
		i3.setBounds(x+sx, y_init + sy*2+ y_inter*2 ,six, siy);
		panel.add(i3);
		six = 100;
		JTextField i4 = new JTextField();
		i4.setBounds(x+sx,  y_init + sy*3+ y_inter*3  ,six, siy);
		panel.add(i4);
		init_table(x+sx, y_init + sy*4+ y_inter*4);
	}
	public void init_table(int x, int y) {
		String[] cols = {
				"LugNom",
				"LugTipDes",
				"PecNom",
				"PecDes"
		};
		Object[][] data = {
				{"Kathy", "Smith", "Snowboarding",  new Boolean(false)},
				    {"John", "Doe","Rowing", new Boolean(true)},
				    {"Sue", "Black","Knitting", new Boolean(false)},
				    {"Jane", "White", "Speed reading",  new Boolean(true)},
				    {"Joe", "Brown","Pool",new Boolean(false)}
				
		};
		dtm = new DefaultTableModel(data, cols);
		
		JTable table = new JTable(dtm);	
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vista_1 a = new Vista_1();
		
	}

}
