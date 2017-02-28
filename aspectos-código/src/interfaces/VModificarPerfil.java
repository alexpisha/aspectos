package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
import modelo.Usuario;

public class VModificarPerfil extends JFrame {

	private JPanel contentPane;
	private JTextField textUser;
	private JTextField textPass;
	private JTextField textEmail;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VModificarPerfil frame = new VModificarPerfil();
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
	public VModificarPerfil() {
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
		JLabel lblBienvenida = new JLabel("Modificar Perfil");
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
		lblUser.setBounds(23, 30, 71, 46);
		panelInsertar.add(lblUser);
		
		textUser = new JTextField(ControladorMusica.getControladorMusica().getUsuario().getNombre());
		textUser.setBounds(138, 43, 272, 22);
		panelInsertar.add(textUser);
		textUser.setColumns(10);
			
		JLabel lblPass = new JLabel("Contrase\u00F1a:");
		lblPass.setFont(new Font("Arial", Font.PLAIN, 17));
		lblPass.setBounds(23, 94, 97, 22);
		panelInsertar.add(lblPass);
		
		
		textPass = new JPasswordField(ControladorMusica.getControladorMusica().getUsuario().getContrasena());
		textPass.setBounds(138, 95, 272, 23);
		panelInsertar.add(textPass);
		textPass.setColumns(10);
		
		JLabel label = new JLabel("Email:");
		label.setFont(new Font("Arial", Font.PLAIN, 17));
		label.setBounds(23, 141, 97, 22);
		panelInsertar.add(label);
		
		textField = new JTextField(ControladorMusica.getControladorMusica().getUsuario().getEmail());
		textField.setColumns(10);
		textField.setBounds(138, 142, 272, 22);
		panelInsertar.add(textField);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String user=textUser.getText();
	            String pass= textPass.getText();
	            String email= textPass.getText();
	            Usuario usuarioActual = ControladorMusica.getControladorMusica().getUsuario();
	            int id= Integer.parseInt(SGBD.getSGBD().obtenerId(usuarioActual.getNombre()));
	            System.out.println("el id es"+id);
	            
	            
	            if(user.isEmpty() && pass.isEmpty() && email.isEmpty()){
					JOptionPane.showMessageDialog(null, "No has rellenado nigún campo. Pulsa aceptar e inténtalo de nuevo.");
	            }else{
	            	if(!user.isEmpty()){
	            		try {
		            		String sql= "Update Usuario set nombre='"+user+"' where id='"+id+"'";
							SGBD.getSGBD().actualizar(sql);
							ControladorMusica.getControladorMusica().actualizarNombre(user);
	            		} catch (SQLException e1) {
							e1.printStackTrace();
						}
	            	}
	            	if(!pass.isEmpty()){
	            		try {
		            		String sql= "Update Usuario set contrasena='"+pass+"' where id='"+id+"'";
							SGBD.getSGBD().actualizar(sql);
							ControladorMusica.getControladorMusica().actualizarContrasena(pass);
						
	            		} catch (SQLException e1) {
							e1.printStackTrace();
						}
	            	}
	            	if(!email.isEmpty()){
	            		try {
		            		String sql= "Update Usuario set email='"+email+"' where id='"+id+"'";
							SGBD.getSGBD().actualizar(sql);
							ControladorMusica.getControladorMusica().actualizarEmail(email);

							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
	            	}
					JOptionPane.showMessageDialog(null, "Ya hemos modificado tus datos personales.");
					System.out.println(usuarioActual.getNombre()+"-"+usuarioActual.getContrasena()+"-"+usuarioActual.getEmail());
					VMenu v = new VMenu();
					v.setVisible(true);
					dispose();

	        }
	    }});

		btnAceptar.setBounds(138, 211, 97, 25);
		panelInsertar.add(btnAceptar);
				
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	VMenu v= new VMenu();
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

