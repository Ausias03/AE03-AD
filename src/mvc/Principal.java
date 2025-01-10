package mvc;

public class Principal {
	private static Controlador controlador;

	public static Controlador getControlador() {
		return controlador;
	}

	public static void main(String[] args) {
		Vista vista = new Vista();
		VistaLogin vistaLogin = new VistaLogin();
		VistaRegister vistaRegister = new VistaRegister();
		Model model = new Model();
		Principal.controlador = new Controlador(vista, vistaLogin, vistaRegister, model);
	}
}