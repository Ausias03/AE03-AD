package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		vistaLogin.getBtnOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaLogin.setVisible(false);
			}
		});
		
		vistaLogin.getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaLogin.setVisible(false);
			}
		});
		
		vistaRegister.getBtnOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaRegister.setVisible(false);
			}
		});
		
		vistaRegister.getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaRegister.setVisible(false);
			}
		});
	}
}
