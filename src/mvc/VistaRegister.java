package mvc;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

public class VistaRegister extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JButton btnOk;
	private JButton btnCancelar;
	private JLabel lblPwd2;
	private JPasswordField pwdField1;
	private JPasswordField pwdField2;

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

	public VistaRegister() {
		setTitle("Register");
		initComponents();
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUser.setBounds(12, 17, 69, 16);
		contentPane.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtUser.setBounds(133, 17, 123, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPwd = new JLabel("Password:");
		lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPwd.setBounds(12, 52, 69, 20);
		contentPane.add(lblPwd);
		
		btnOk = new JButton("Aceptar");
		btnOk.setBounds(167, 128, 89, 23);
		contentPane.add(btnOk);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(12, 128, 89, 23);
		contentPane.add(btnCancelar);
		
		lblPwd2 = new JLabel("Repeat Password:");
		lblPwd2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPwd2.setBounds(12, 87, 116, 30);
		contentPane.add(lblPwd2);
		
		pwdField1 = new JPasswordField();
		pwdField1.setBounds(133, 53, 123, 20);
		contentPane.add(pwdField1);
		
		pwdField2 = new JPasswordField();
		pwdField2.setBounds(133, 93, 123, 20);
		contentPane.add(pwdField2);
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

	public JPasswordField getPwdField1() {
		return pwdField1;
	}

	public JPasswordField getPwdField2() {
		return pwdField2;
	}
}
