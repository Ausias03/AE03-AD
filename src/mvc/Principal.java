package mvc;

public class Principal {
	public static void main(String[] args) {
		Vista vista = new Vista();
		VistaLogin vistaLogin = new VistaLogin();
		VistaRegister vistaRegister = new VistaRegister();
		Model model = new Model();
		Controlador controlador = new Controlador(vista, vistaLogin, vistaRegister, model);
	}
}