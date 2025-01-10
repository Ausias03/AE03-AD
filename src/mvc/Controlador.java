package mvc;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import game.Card;
import game.Game;
import game.Player;

public class Controlador {

	private Vista vista;
	private VistaLogin vistaLogin;
	private VistaRegister vistaRegister;
	private VistaFame vistaFame;
	private Model model;

	public Controlador(Vista vista, VistaLogin vistaLogin, VistaRegister vistaRegister, VistaFame vistaFame,
			Model model) {
		this.vista = vista;
		this.vistaLogin = vistaLogin;
		this.vistaRegister = vistaRegister;
		this.vistaFame = vistaFame;
		this.model = model;

		try {
			model.openConnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
		}

		initEventHandlers();
	}

	public void initEventHandlers() {
		vista.getBtnRegister().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaRegister.setVisible(true);
			}
		});

		vista.getBtnLogIn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaLogin.setVisible(true);
			}
		});

		vista.getBtnLogout().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (model.logOutUser()) {
					vista.getBtnLogIn().setBackground(null);
					vista.getBtnLogIn().setEnabled(true);
					JOptionPane.showMessageDialog(null, "User logged out", "Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Not logged in!", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		vista.getBtnLoadCards().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					model.loadCardsToDb();
					JOptionPane.showMessageDialog(null, "Card images loaded succesfully", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		vista.getBtnStart().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (model.isLogged()) {
					if (model.cardsLoaded()) {
						vista.getBtnCrupierCard().setIcon(null);
						vista.getBtnPlayerCard().setIcon(null);
						vista.getLblScoreHistoryCrupierValue().setText("");
						vista.getLblScoreHistoryPlayerValue().setText("");
						vista.getLblTotalScoreCrupierValue().setText("0");
						vista.getLblTotalScorePlayerValue().setText("0");
						String[] options = { "Crupier (A.I.)", "User (human)" };

						int choice = JOptionPane.showOptionDialog(null, "Who starts?", "Choose Starter",
								JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
						int cardSuit = vista.getCboSuit().getSelectedIndex();

						model.startGame(choice, cardSuit);
					} else {
						JOptionPane.showMessageDialog(null, "Load cards first!", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Not logged in!", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		vista.getBtnSave().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (model.isLogged()) {
					if (model.getGame() != null) {
						model.saveGameToScores();
						JOptionPane.showMessageDialog(null, "Game Saved!", "Info", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "No game registered!", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Not logged in!", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		vista.getBtnHall().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (model.isLogged()) {
					String scores = "Scores:" + System.lineSeparator() + model.obtainHallOfFame();
					vistaFame.getTxaFame().setText(scores);
					vistaFame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Not logged in!", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		vistaLogin.getBtnOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = vistaLogin.getTxtUser().getText();
				String pwdField = String.valueOf(vistaLogin.getPwdField().getPassword());
				boolean loggedIn = model.logInUser(userName, pwdField);
				JOptionPane.showMessageDialog(null, model.logInUser(userName, pwdField) ? "User logged in succesfully!"
						: "Error, couldn't log in user", "Info", JOptionPane.INFORMATION_MESSAGE);
				if (loggedIn) {
					vista.getBtnLogIn().setBackground(Color.green);
					vista.getBtnLogIn().setEnabled(false);
				}
				vistaLogin.getTxtUser().setText("");
				vistaLogin.getPwdField().setText("");
				vistaLogin.setVisible(false);
			}
		});

		vistaLogin.getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaLogin.getTxtUser().setText("");
				vistaLogin.getPwdField().setText("");
				vistaLogin.setVisible(false);
			}
		});

		vistaRegister.getBtnOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = vistaRegister.getTxtUser().getText();
				String pwdField1 = String.valueOf(vistaRegister.getPwdField1().getPassword());
				String pwdField2 = String.valueOf(vistaRegister.getPwdField2().getPassword());
				if (pwdField1.equals(pwdField2)) {
					JOptionPane.showMessageDialog(null,
							model.signUpUser(userName, pwdField1) ? "User registered succesfully!"
									: "Error, couldn't register user",
							"Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "The passwords do not match", "ACTION BUTTON SEARCH",
							JOptionPane.INFORMATION_MESSAGE);
				}
				vistaRegister.getTxtUser().setText("");
				vistaRegister.getPwdField1().setText("");
				vistaRegister.getPwdField2().setText("");
				vistaRegister.setVisible(false);
			}
		});

		vistaRegister.getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaRegister.getTxtUser().setText("");
				vistaRegister.getPwdField1().setText("");
				vistaRegister.getPwdField2().setText("");
				vistaRegister.setVisible(false);
			}
		});

		vistaFame.getBtnSalir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaFame.getTxaFame().setText("");
				vistaFame.setVisible(false);
			}
		});
	}

	public void updateCard(Card card, Player player) {
		if (card == null) {
			return;
		}
		try {
			byte[] imageBytes = Base64.getDecoder().decode(card.getBase64());
			ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
			BufferedImage img = ImageIO.read(bis);

			int buttonWidth = vista.getBtnPlayerCard().getWidth();
			int buttonHeight = vista.getBtnPlayerCard().getHeight();
			Image resizedImg = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
			ImageIcon imgIcon = new ImageIcon(resizedImg);
			if (player.getType().equals("Human")) {
				vista.getBtnPlayerCard().setIcon(imgIcon);
				String history = vista.getLblScoreHistoryPlayerValue().getText();				
				int points = card.getPoints();
				if (card.getPoints() == 1) {
					points = player.getPoints() - Integer.parseInt(vista.getLblTotalScorePlayerValue().getText());
				}
				vista.getLblScoreHistoryPlayerValue().setText(history + " " + points);
				vista.getLblTotalScorePlayerValue().setText(String.valueOf(player.getPoints()));
			} else {
				vista.getBtnCrupierCard().setIcon(imgIcon);
				String history = vista.getLblScoreHistoryCrupierValue().getText();
				int points = card.getPoints();
				if (card.getPoints() == 1) {
					points = player.getPoints() - Integer.parseInt(vista.getLblTotalScoreCrupierValue().getText());
				}
				vista.getLblScoreHistoryCrupierValue().setText(history + " " + points);
				vista.getLblTotalScoreCrupierValue().setText(String.valueOf(player.getPoints()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
