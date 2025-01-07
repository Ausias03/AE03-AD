package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
					vista.getBtnLogIn().setBackground(Color.gray);
					vista.getBtnLogIn().setEnabled(true);
					JOptionPane.showMessageDialog(null, "User logged out", "ACTION BUTTON SEARCH",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "You are not logged in", "ACTION BUTTON SEARCH",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		vista.getBtnLoadCards().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					model.loadCardsToDb();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		vistaLogin.getBtnOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = vistaLogin.getTxtUser().getText();
				String pwdField = vistaLogin.getTxtPwd().getText();
				boolean loggedIn = model.logInUser(userName, pwdField);
				JOptionPane.showMessageDialog(null,
						model.logInUser(userName, pwdField) ? "User logged in succesfully!"
								: "Error, couldn't log in user",
						"ACTION BUTTON SEARCH", JOptionPane.INFORMATION_MESSAGE);
				if (loggedIn) {
					vista.getBtnLogIn().setBackground(Color.green);
					vista.getBtnLogIn().setEnabled(false);
				}
				vistaLogin.getTxtUser().setText("");
				vistaLogin.getTxtPwd().setText("");
				vistaLogin.setVisible(false);
			}
		});

		vistaLogin.getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaLogin.getTxtUser().setText("");
				vistaLogin.getTxtPwd().setText("");
				vistaLogin.setVisible(false);
			}
		});

		vistaRegister.getBtnOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = vistaRegister.getTxtUser().getText();
				String pwdField1 = vistaRegister.getTxtPwd().getText();
				String pwdField2 = vistaRegister.getTxtPwd2().getText();
				if (pwdField1.equals(pwdField2)) {
					JOptionPane.showMessageDialog(null,
							model.signUpUser(userName, pwdField1) ? "User registered succesfully!"
									: "Error, couldn't register user",
							"ACTION BUTTON SEARCH", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "The passwords do not match", "ACTION BUTTON SEARCH",
							JOptionPane.INFORMATION_MESSAGE);
				}
				vistaRegister.getTxtUser().setText("");
				vistaRegister.getTxtPwd().setText("");
				vistaRegister.getTxtPwd2().setText("");
				vistaRegister.setVisible(false);
			}
		});

		vistaRegister.getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaRegister.getTxtUser().setText("");
				vistaRegister.getTxtPwd().setText("");
				vistaRegister.getTxtPwd2().setText("");
				vistaRegister.setVisible(false);
			}
		});
	}
}
