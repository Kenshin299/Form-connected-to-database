import java.awt.EventQueue;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Guardar extends JFrame {

	private JPanel contentPane;
	private JTextField textfPersona;
	private JTextField textfLugar;
	private JFormattedTextField textfFecha;
	private JTextField textfEvento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guardar frame = new Guardar();
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
	public Guardar() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Guardar");
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPersona = new JLabel("Persona");
		lblPersona.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPersona.setBounds(29, 42, 49, 14);
		contentPane.add(lblPersona);
		
		JLabel lblLugar = new JLabel("Lugar");
		lblLugar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLugar.setBounds(29, 92, 49, 22);
		contentPane.add(lblLugar);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(29, 145, 49, 14);
		contentPane.add(lblFecha);
		
		JLabel lblEvento = new JLabel("Evento");
		lblEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEvento.setBounds(29, 206, 49, 14);
		contentPane.add(lblEvento);
		
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
		
		textfPersona = new JTextField();
		textfPersona.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textfPersona.setBounds(88, 41, 227, 20);
		contentPane.add(textfPersona);
		textfPersona.setColumns(10);
		
		textfLugar = new JTextField();
		textfLugar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textfLugar.setColumns(10);
		textfLugar.setBounds(88, 95, 227, 20);
		contentPane.add(textfLugar);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		textfFecha = new JFormattedTextField(format);
		textfFecha.setToolTipText("yyyy-mm-dd");
		textfFecha.setColumns(8);
		textfFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textfFecha.setBounds(88, 144, 96, 20);
		contentPane.add(textfFecha);
		
		textfEvento = new JTextField();
		textfEvento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textfEvento.setColumns(10);
		textfEvento.setBounds(88, 205, 227, 20);
		contentPane.add(textfEvento);
		
		JLabel lblNewLabel = new JLabel("yyyy-mm-dd");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel.setBounds(194, 147, 72, 14);
		contentPane.add(lblNewLabel);
		
		String url = "jdbc:mysql://localhost:3306/eventos";
		String user = "root";
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textfPersona.getText().isBlank() && !textfLugar.getText().isBlank() && !textfFecha.getText().isBlank() && !textfEvento.getText().isBlank()) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						java.sql.Connection conexion = DriverManager.getConnection(url, user, "" );
						
						Statement statement = conexion.createStatement();
						((java.sql.Statement)statement).executeUpdate("INSERT INTO Eventos (PNombre, Lugar, Fecha, Desc_Evento)"
								+ "VALUES ('" +textfPersona.getText()+ "', '" +textfLugar.getText()+ "', '" +textfFecha.getText()
								+ "', '" +textfEvento.getText()+ "')");
						
						conexion.close();
						
					}catch (ClassNotFoundException o) {	
						o.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Complete todos los campos de la agenda");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(131, 276, 112, 41);
		contentPane.add(btnNewButton);
	}
}
