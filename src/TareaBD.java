import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

public class TareaBD {

	JFrame frmEventos;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TareaBD window = new TareaBD();
					window.frmEventos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TareaBD() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEventos = new JFrame();
		frmEventos.setTitle("Agenda Electronica");
		frmEventos.setBounds(100, 100, 600, 600);
		frmEventos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String url = "jdbc:mysql://localhost:3306/eventos";
		String user = "root";
		
		JMenuBar menuBar = new JMenuBar();
		frmEventos.setJMenuBar(menuBar);
		
		JMenu mnAcciones = new JMenu("Acciones");
		menuBar.add(mnAcciones);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guardar guardar = new Guardar();
				guardar.setVisible(true);
			}
		});
		mnAcciones.add(mntmGuardar);
		
		JMenuItem mntmActualizar = new JMenuItem("Actualizar");
		mntmActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar actualizar = new Actualizar();
				actualizar.setVisible(true);
			}
		});
		mnAcciones.add(mntmActualizar);
		
		JMenuItem mntmBorrar = new JMenuItem("Borrar");
		mntmBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borrar borrar = new Borrar();
				borrar.setVisible(true);
			}
		});
		mnAcciones.add(mntmBorrar);
		
		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar buscar = new Buscar();
				buscar.setVisible(true);
			}
		});
		mnAcciones.add(mntmBuscar);
		
		JMenu mnInfo = new JMenu("Información");
		menuBar.add(mnInfo);
		
		JMenuItem mntmPerfil = new JMenuItem("Perfil del estudiante");
		mntmPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInternalMessageDialog(null, "Kemyl Fernandez Bonifacio\n              2021-2302\n");
			}
		});
		mnInfo.add(mntmPerfil);
		frmEventos.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 45, 532, 444);
		frmEventos.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table_1);
	    table_1.setFillsViewportHeight(true);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"id", "Persona", "Lugar", "Fecha", "Evento"
				}
			);
	    table_1.setModel(model);
		
		JLabel lblNewLabel = new JLabel("Eventos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(36, 11, 61, 26);
		frmEventos.getContentPane().add(lblNewLabel);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model.setRowCount(0);
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conexion = DriverManager.getConnection(url, user, "" );
					
					Statement statement = conexion.createStatement();
					ResultSet results = statement.executeQuery("Select * from eventos");
					
					while (results.next())
					{
					   Object [] fila = new Object[5]; 
					   for (int i=0;i<5;i++) {
					      fila[i] = results.getObject(i+1);
					   }
					   model.addRow(fila);
					}
					
					conexion.close();
					
				}catch (ClassNotFoundException o) {	
					o.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnMostrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMostrar.setBounds(20, 500, 103, 30);
		frmEventos.getContentPane().add(btnMostrar);
	}
}
