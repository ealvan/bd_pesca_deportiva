
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Ven_GenMenu {

	public JFrame frame;
	JComboBox comboBox;
	JButton Seleccionar_Tipo;
	
	static Ven_GenMenu window = new Ven_GenMenu();
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	
	public Ven_GenMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Estado Civil", 
				"Estado de Registro", 
				"Pais", 
				"Licencias", 
				"Acotado", 
				"Puede Capturar", 
				"Tipo de Lugares", 
				"Tipo de Captura", 
				"Trofeos"
				}));
		comboBox.setSelectedIndex(0);
		
		Seleccionar_Tipo = new JButton("Ingresar");

		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Seleccione la tabla referencial a editar");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewJgoodiesLabel)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
							.addComponent(Seleccionar_Tipo)
							.addGap(54))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewJgoodiesLabel)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Seleccionar_Tipo))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		eventos();
	}
	
	public String get_Tabla_Type() {
		
		int caracter =  (Integer)comboBox.getSelectedIndex();
		String [] nombres = {
				"estado_civil", 
				"estado_registro", 
				"pais", 
				"licencias", 
				"acotado", 
				"puede_cap", 
				"lugares_tip", 
				"captura_tip", 
				"trofeos"
				};
		return nombres[caracter];
	}
	
	private void eventos() {
		Seleccionar_Tipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = get_Tabla_Type();
				Ven_Referenciales tr = new Ven_Referenciales(aux);
				frame.setVisible(false);
				System.out.println(aux);
				tr.frame.setVisible(true);
				
			}
		});
		
	}
	

}
