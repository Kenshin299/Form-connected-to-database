import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Borrar extends JFrame {
	private JPanel contentPane;
	private JTextField textfID;
	private JTextField textfEvento;
	private JTextField textfPersona;
	private JTextField textfFecha;
	private JTextField textfLugar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Borrar frame = new Borrar();
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
	public Borrar() {
		setTitle("Borrar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String url = "jdbc:mysql://localhost:3306/eventos";
		String user = "root";
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 17, 366, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 249, 366, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setBounds(10, 17, 2, 234);
		contentPane.add(separator_1_1);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1_1.setBounds(374, 17, 2, 234);
		contentPane.add(separator_1_1_1);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conexion = DriverManager.getConnection(url, user, "" );
					
					Statement statement = conexion.createStatement();
					String query = "Delete from eventos where id = " + textfID.getText();
					statement.executeUpdate(query);
					
					conexion.close();
					
				}catch (ClassNotFoundException o) {	
					o.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBorrar.setBounds(107, 276, 144, 41);
		contentPane.add(btnBorrar);
		
		textfID = new JTextField();
		textfID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textfID.setBounds(107, 30, 42, 20);
		contentPane.add(textfID);
		textfID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id Evento");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 30, 77, 20);
		contentPane.add(lblNewLabel);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conexion = DriverManager.getConnection(url, user, "" );
					
					Statement statement = conexion.createStatement();
					String query = "Select * from eventos where id = " + textfID.getText();
					ResultSet results = statement.executeQuery(query);
					if(results.next()) {
						textfEvento.setText(results.getString("Desc_Evento"));
						textfPersona.setText(results.getString("PNombre"));
						textfFecha.setText(results.getString("Fecha"));
						textfLugar.setText(results.getString("Lugar"));
					}
					
					conexion.close();
					
				}catch (ClassNotFoundException o) {	
					o.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscar.setBounds(197, 26, 92, 27);
		contentPane.add(btnBuscar);
		
		JLabel lblEvento = new JLabel("Nombre del Evento");
		lblEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEvento.setBounds(20, 117, 119, 20);
		contentPane.add(lblEvento);
		
		JLabel lblPersona = new JLabel("Persona");
		lblPersona.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPersona.setBounds(20, 148, 77, 20);
		contentPane.add(lblPersona);
		
		JLabel lblEvento_1 = new JLabel("Fecha");
		lblEvento_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEvento_1.setBounds(20, 181, 77, 20);
		contentPane.add(lblEvento_1);
		
		textfEvento = new JTextField();
		textfEvento.setEditable(false);
		textfEvento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textfEvento.setColumns(10);
		textfEvento.setBounds(149, 119, 215, 20);
		contentPane.add(textfEvento);
		
		textfPersona = new JTextField();
		textfPersona.setEditable(false);
		textfPersona.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textfPersona.setColumns(10);
		textfPersona.setBounds(149, 150, 215, 20);
		contentPane.add(textfPersona);
		
		textfFecha = new JTextField();
		textfFecha.setEditable(false);
		textfFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textfFecha.setColumns(10);
		textfFecha.setBounds(149, 183, 106, 20);
		contentPane.add(textfFecha);
		
		JLabel lblEvento_2 = new JLabel("Evento");
		lblEvento_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEvento_2.setBounds(20, 86, 77, 20);
		contentPane.add(lblEvento_2);
		
		JLabel lblLugar = new JLabel("Lugar");
		lblLugar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLugar.setBounds(20, 212, 77, 20);
		contentPane.add(lblLugar);
		
		textfLugar = new JTextField();
		textfLugar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textfLugar.setEditable(false);
		textfLugar.setColumns(10);
		textfLugar.setBounds(149, 218, 215, 20);
		contentPane.add(textfLugar);
	}
}
