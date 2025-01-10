package mvc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private String[] suits = new String[] { "ES", "FR" };
	private JPanel contentPane;
	private JButton btnRegister;
	private JButton btnLogIn;
	private JButton btnLogout;
	private JButton btnLoadCards;
	private JButton btnStart;
	private JButton btnSave;
	private JButton btnHall;
	private JButton btnCrupierCard;
	private JButton btnPlayerCard;
	private JComboBox cboSuit;
	private JLabel lblTotalScoreCrupierValue;
	private JLabel lblTotalScorePlayerValue;
	private JLabel lblScoreHistoryCrupierValue;
	private JLabel lblScoreHistoryPlayerValue;	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Vista() {
		setTitle("AE03");
		initComponents();
	}

	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnLoadCards = new JButton("Load Cards");
		btnLoadCards.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLoadCards.setBounds(10, 11, 100, 23);
		contentPane.add(btnLoadCards);

		btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRegister.setBounds(120, 11, 80, 23);
		contentPane.add(btnRegister);

		btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLogIn.setBounds(207, 11, 80, 23);
		contentPane.add(btnLogIn);

		JLabel lblSuit = new JLabel("Cards Suit:");
		lblSuit.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSuit.setBounds(305, 15, 61, 14);
		contentPane.add(lblSuit);
		
		cboSuit = new JComboBox(suits);
		cboSuit.setBounds(368, 11, 47, 22);
		contentPane.add(cboSuit);

		btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnStart.setBounds(425, 11, 73, 23);
		contentPane.add(btnStart);

		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSave.setBounds(508, 11, 73, 23);
		contentPane.add(btnSave);

		btnHall = new JButton("Hall Of Fame");
		btnHall.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnHall.setBounds(591, 11, 100, 23);
		contentPane.add(btnHall);

		btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLogout.setBounds(701, 11, 77, 23);
		contentPane.add(btnLogout);

		JLabel lblCrupier = new JLabel("Crupier:");
		lblCrupier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCrupier.setBounds(20, 45, 46, 14);
		contentPane.add(lblCrupier);

		btnCrupierCard = new JButton("");
		btnCrupierCard.setBounds(21, 70, 266, 325);
		contentPane.add(btnCrupierCard);

		JLabel lblTotalScoreCrupier = new JLabel("Total Score:");
		lblTotalScoreCrupier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTotalScoreCrupier.setBounds(20, 407, 90, 14);
		contentPane.add(lblTotalScoreCrupier);

		JLabel lblScoreHistoryCrupier = new JLabel("Score History:");
		lblScoreHistoryCrupier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblScoreHistoryCrupier.setBounds(20, 435, 90, 14);
		contentPane.add(lblScoreHistoryCrupier);

		btnPlayerCard = new JButton("");
		btnPlayerCard.setBounds(426, 70, 266, 325);
		contentPane.add(btnPlayerCard);

		JLabel lblPlayer = new JLabel("Player:");
		lblPlayer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPlayer.setBounds(425, 45, 46, 14);
		contentPane.add(lblPlayer);

		JLabel lblTotalScorePlayer = new JLabel("Total Score:");
		lblTotalScorePlayer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTotalScorePlayer.setBounds(425, 407, 90, 14);
		contentPane.add(lblTotalScorePlayer);

		JLabel lblScoreHistoryPlayer = new JLabel("Score History:");
		lblScoreHistoryPlayer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblScoreHistoryPlayer.setBounds(425, 435, 90, 14);
		contentPane.add(lblScoreHistoryPlayer);

		lblTotalScoreCrupierValue = new JLabel("0");
		lblTotalScoreCrupierValue.setBounds(98, 408, 61, 14);
		contentPane.add(lblTotalScoreCrupierValue);

		lblScoreHistoryCrupierValue = new JLabel("");
		lblScoreHistoryCrupierValue.setBounds(108, 436, 61, 14);
		contentPane.add(lblScoreHistoryCrupierValue);

		lblScoreHistoryPlayerValue = new JLabel("");
		lblScoreHistoryPlayerValue.setBounds(512, 435, 61, 14);
		contentPane.add(lblScoreHistoryPlayerValue);

		lblTotalScorePlayerValue = new JLabel("0");
		lblTotalScorePlayerValue.setBounds(505, 407, 61, 14);
		contentPane.add(lblTotalScorePlayerValue);
		setVisible(true);
	}

	public JButton getBtnRegister() {
		return btnRegister;
	}

	public JButton getBtnLogIn() {
		return btnLogIn;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}
	
	public JButton getBtnLoadCards() {
		return btnLoadCards;
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public JButton getBtnHall() {
		return btnHall;
	}

	public JButton getBtnCrupierCard() {
		return btnCrupierCard;
	}

	public JButton getBtnPlayerCard() {
		return btnPlayerCard;
	}

	public JComboBox getCboSuit() {
		return cboSuit;
	}

	public JLabel getLblTotalScoreCrupierValue() {
		return lblTotalScoreCrupierValue;
	}

	public JLabel getLblTotalScorePlayerValue() {
		return lblTotalScorePlayerValue;
	}

	public JLabel getLblScoreHistoryCrupierValue() {
		return lblScoreHistoryCrupierValue;
	}

	public JLabel getLblScoreHistoryPlayerValue() {
		return lblScoreHistoryPlayerValue;
	}
}
