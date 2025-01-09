package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import game.Human;
import game.Player;
import game.Robot;

public class Controlador {

	private Vista vista;
	private VistaLogin vistaLogin;
	private VistaRegister vistaRegister;
	private Model model;

	public Controlador(Vista vista, VistaLogin vistaLogin, VistaRegister vistaRegister, Model model) {
		this.vista = vista;
		this.vistaLogin = vistaLogin;
		this.vistaRegister = vistaRegister;
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
					JOptionPane.showMessageDialog(null, "User logged out", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "You are not logged in", "Info",
							JOptionPane.INFORMATION_MESSAGE);
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
					Player[] players = new Player[2];
					String[] options = { "Crupier (A.I.)", "User (human)" };

					int choice = JOptionPane.showOptionDialog(null, "Who starts?", "Choose Starter",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
					int cardSuit = vista.getCboSuit().getSelectedIndex() + model.collectionsBeforeCards;

					JOptionPane.showMessageDialog(null, choice == 0 ? "Crupier starts!" : "Player starts!", "Start",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Not logged in!", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		vista.getBtnSave().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (model.isLogged()) {

				} else {
					JOptionPane.showMessageDialog(null, "Not logged in!", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		vista.getBtnHall().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (model.isLogged()) {

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
				JOptionPane.showMessageDialog(null,
						model.logInUser(userName, pwdField) ? "User logged in succesfully!"
								: "Error, couldn't log in user",
						"Info", JOptionPane.INFORMATION_MESSAGE);
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
	}
}
