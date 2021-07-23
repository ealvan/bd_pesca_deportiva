
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
	JComboBox comboBox_1;
	JButton Seleccionar_Tipo;
	JButton Seleccionar_Tipo_1;
	
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
		frame = 	new JFrame();
		frame.setBounds(100, 100, 450, 258);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		comboBox = new JComboBox();
		comboBox.setBounds(27, 58, 200, 20);
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
		Seleccionar_Tipo.setBounds(307, 57, 73, 23);
		frame.getContentPane().setLayout(null);

		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Seleccione la tabla referencial a editar");
		lblNewJgoodiesLabel.setBounds(27, 17, 182, 14);
		frame.getContentPane().add(lblNewJgoodiesLabel);
		frame.getContentPane().add(comboBox);
		frame.getContentPane().add(Seleccionar_Tipo);
		
		JLabel lblNewLabel = new JLabel("Seleccione una tabla de datos a editar");
		lblNewLabel.setBounds(27, 110, 200, 14);
		frame.getContentPane().add(lblNewLabel);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Lugares", "Peces", "Cauces", "Lugares Peces", "Afiliados", "Eventos", "Capturas Solos", "Capturas Eventos", "Veda", "Afiliados Licencias", "Eventos Afiliados"}));
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setBounds(27, 157, 200, 20);
		frame.getContentPane().add(comboBox_1);
		
		Seleccionar_Tipo_1 = new JButton("Ingresar");
		
		Seleccionar_Tipo_1.setBounds(307, 156, 73, 23);
		frame.getContentPane().add(Seleccionar_Tipo_1);
		eventos();
	}
	
	public String get_Tabla_Type_Referencial() {
		
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
	
	public String get_Tabla_Type_Datos() {
		
		int caracter =  (Integer)comboBox_1.getSelectedIndex();
		String [] nombres = {
				"lugares", 
				"peces", 
				"cauces", 
				"lugar_peces", 
				"afiliados", 
				"eventos", 
				"capturas_solos", 
				"capturas_eve", 
				"veda",
				"afi_lic",
				"eventos_afi",
				};
		return nombres[caracter];
	}
	
	private void eventos() {
		Seleccionar_Tipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = get_Tabla_Type_Referencial();
				Ven_Referenciales tr = new Ven_Referenciales(aux);
				//Ven_Referenciales tr = new Ven_Referenciales(aux);
				frame.setVisible(false);
				System.out.println(aux);
				tr.frame.setVisible(true);
				
			}
		});
		
		Seleccionar_Tipo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = get_Tabla_Type_Datos();
				System.out.println("es " + aux);
				
				if(aux.equals("lugares")) {
					
					Ven_Datos_Lugares tr = new Ven_Datos_Lugares(aux);
					//Ven_Referenciales tr = new Ven_Referenciales(aux);
					frame.setVisible(false);
					System.out.println(aux);
					tr.frame.setVisible(true);
					
				} else if(aux.equals("peces")) {
					
					Ven_Datos_Peces tr = new Ven_Datos_Peces(aux);
					//Ven_Referenciales tr = new Ven_Referenciales(aux);
					frame.setVisible(false);
					System.out.println(aux);
					tr.frame.setVisible(true);
					
				} else if(aux.equals("cauces")) {
					
					Ven_Datos_Cauces tr = new Ven_Datos_Cauces(aux);
					//Ven_Referenciales tr = new Ven_Referenciales(aux);
					frame.setVisible(false);
					System.out.println(aux);
					tr.frame.setVisible(true);
					
				} else if(aux.equals("lugar_peces")) {
					
					Ven_Datos_LugaresPec tr = new Ven_Datos_LugaresPec(aux);
					//Ven_Referenciales tr = new Ven_Referenciales(aux);
					frame.setVisible(false);
					System.out.println(aux);
					tr.frame.setVisible(true);
					
				} else if(aux.equals("afiliados")) {
					
					Ven_Datos_Afiliados tr = new Ven_Datos_Afiliados(aux);
					//Ven_Referenciales tr = new Ven_Referenciales(aux);
					frame.setVisible(false);
					System.out.println(aux);
					tr.frame.setVisible(true);
					
				} else if(aux.equals("eventos")) {
					
					Ven_Datos_Eventos tr = new Ven_Datos_Eventos(aux);
					//Ven_Referenciales tr = new Ven_Referenciales(aux);
					frame.setVisible(false);
					System.out.println(aux);
					tr.frame.setVisible(true);
					
				}
				
				
				else if(aux.equals("capturas_solos")) {
					
					Ven_Datos_Capturas_Solos tr = new Ven_Datos_Capturas_Solos(aux);
					//Ven_Referenciales tr = new Ven_Referenciales(aux);
					frame.setVisible(false);
					System.out.println(aux);
					tr.frame.setVisible(true);
					
				} else if(aux.equals("capturas_eve")) {
					
					Ven_Datos_Capturas_Eventos tr = new Ven_Datos_Capturas_Eventos(aux);
					//Ven_Referenciales tr = new Ven_Referenciales(aux);
					frame.setVisible(false);
					System.out.println(aux);
					tr.frame.setVisible(true);
					
				} else if(aux.equals("veda")) {
					
					Ven_Datos_Veda tr = new Ven_Datos_Veda(aux);
					//Ven_Referenciales tr = new Ven_Referenciales(aux);
					frame.setVisible(false);
					System.out.println(aux);
					tr.frame.setVisible(true);
					
				} else if(aux.equals("afi_lic")) {
					
					Ven_Datos_Afiliados_Lic tr = new Ven_Datos_Afiliados_Lic(aux);
					//Ven_Referenciales tr = new Ven_Referenciales(aux);
					frame.setVisible(false);
					System.out.println(aux);
					tr.frame.setVisible(true);
					
				} else if(aux.equals("eventos_afi")) {
					
					Ven_Datos_Eventos_Afiliados tr = new Ven_Datos_Eventos_Afiliados(aux);
					//Ven_Referenciales tr = new Ven_Referenciales(aux);
					frame.setVisible(false);
					System.out.println(aux);
					tr.frame.setVisible(true);
				}
				
			}
		});
		
	}
}
