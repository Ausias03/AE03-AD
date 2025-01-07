package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VistaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPwd;
	private JButton btnOk;
	private JButton btnCancelar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin frame = new VistaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaLogin() {
		setTitle("Log In");
		initComponents();
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 248, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUser.setBounds(12, 17, 69, 20);
		contentPane.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtUser.setBounds(90, 17, 123, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPwd = new JLabel("Password:");
		lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPwd.setBounds(12, 46, 69, 30);
		contentPane.add(lblPwd);
		
		txtPwd = new JTextField();
		txtPwd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPwd.setColumns(10);
		txtPwd.setBounds(90, 52, 123, 20);
		contentPane.add(txtPwd);
		
		btnOk = new JButton("Aceptar");
		btnOk.setBounds(12, 87, 89, 23);
		contentPane.add(btnOk);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(124, 87, 89, 23);
		contentPane.add(btnCancelar);
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JTextField getTxtUser() {
		return txtUser;
	}

	public JTextField getTxtPwd() {
		return txtPwd;
	}
}
