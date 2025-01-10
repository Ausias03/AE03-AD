package mvc;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class VistaFame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalir;
	private JTextArea txaFame;

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

	public VistaFame() {
		setTitle("Log In");
		initComponents();
	}

	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHallOfFame = new JLabel("Hall Of Hame");
		lblHallOfFame.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHallOfFame.setBounds(102, 11, 138, 34);
		contentPane.add(lblHallOfFame);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(241, 392, 89, 23);
		contentPane.add(btnSalir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 320, 335);
		contentPane.add(scrollPane);
		
		txaFame = new JTextArea();
		scrollPane.setViewportView(txaFame);
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}
	
	public JTextArea getTxaFame() {
		return txaFame;
	}
}
