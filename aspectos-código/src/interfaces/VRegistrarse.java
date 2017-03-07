package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.ControladorMusica;
import modelo.SGBD;

public class VRegistrarse extends JFrame {

	private JPanel contentPane;
	private JTextField textUser;
	private JTextField textPass;
	private JTextField textEmail;
	private JTextField textPass2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VRegistrarse frame = new VRegistrarse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VRegistrarse() {
		Image iconPrincipal = new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage();
		setIconImage(iconPrincipal);
		setTitle("EUITI MUSIC PLAYER 3");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelTitulo(), BorderLayout.NORTH);
		contentPane.add(getPanelInsertar(), BorderLayout.CENTER);
		

		setContentPane(contentPane);
	}
	
	private JPanel getPanelTitulo(){
		JPanel panelTitulo = new JPanel();
		JLabel lblBienvenida = new JLabel("Registrarse");
		lblBienvenida.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBienvenida.setFont(new Font("Arial", Font.BOLD, 26));
		panelTitulo.add(lblBienvenida);
		return panelTitulo;
	}


	
	private JPanel getPanelInsertar(){
		JPanel panelInsertar = new JPanel();
		panelInsertar.setLayout(null);
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Arial", Font.PLAIN, 17));
		lblUser.setBounds(23, 4, 71, 46);
		panelInsertar.add(lblUser);
		
		textUser = new JTextField();
		textUser.setBounds(186, 17, 272, 22);
		panelInsertar.add(textUser);
		textUser.setColumns(10);
			
		JLabel lblPass = new JLabel("Contrase\u00F1a:");
		lblPass.setFont(new Font("Arial", Font.PLAIN, 17));
		lblPass.setBounds(23, 63, 97, 22);
		panelInsertar.add(lblPass);
		
		textPass = new JPasswordField();
		textPass.setBounds(186, 64, 272, 23);
		panelInsertar.add(textPass);
		textPass.setColumns(10);
		
		
		JLabel lblPass2 = new JLabel("Repetir Contrase\u00F1a:");
		lblPass2.setFont(new Font("Arial", Font.PLAIN, 17));
		lblPass2.setBounds(23, 111, 151, 22);
		panelInsertar.add(lblPass2);
		
		textPass2 = new JPasswordField();
		textPass2.setColumns(10);
		textPass2.setBounds(186, 112, 272, 23);
		panelInsertar.add(textPass2);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 17));
		lblEmail.setBounds(23, 157, 97, 22);
		panelInsertar.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(186, 158, 272, 23);
		panelInsertar.add(textEmail);

			
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String user=textUser.getText();
	            String pass= textPass.getText();
	            String pass2= textPass.getText();
	     
	            String email= textEmail.getText();
	            if(user.isEmpty() || email.isEmpty() ||pass.isEmpty()){
					JOptionPane.showMessageDialog(null, "Es necesario rellenar todos los campos. Pulsa aceptar e inténtalo de nuevo.");
	            }else{
					if(!SGBD.getSGBD().existeUsuario(user)){
						if(pass.equals(pass2)){
							SGBD.getSGBD().ingresarJugador(user,  pass, email);
							ControladorMusica.getControladorMusica().establecerDatosUsuario(user, pass, email);
							VMenu menu = new VMenu();
							menu.setVisible(true);
							setVisible(false);
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "Las contraseñas no se corresponden.");

						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Ya existe un usuario con el mismo nombre.");
					}
	        }
	    }});

		btnAceptar.setBounds(138, 211, 97, 25);
		panelInsertar.add(btnAceptar);
				
		
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	VPrincipal v= new VPrincipal();
	        	v.setVisible(true);
	        	dispose();
	        
	    }});
		cancelar.setBounds(274, 211, 97, 25);
		panelInsertar.add(cancelar);
		
		JLabel fondo = new JLabel();
		fondo.setIcon(new ImageIcon(VEscucharCancion.class.getResource("/imagenes/ecualizador1.gif")));
		fondo.setBounds(5, 45, 484, 308);
		panelInsertar.add(fondo);
		

		
		return panelInsertar;
	}
}
