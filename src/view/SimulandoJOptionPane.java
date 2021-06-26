package view;

import javax.swing.JDialog;

public class SimulandoJOptionPane  {
	public static void destroyer(Object ob) {
		ob = null;
		System.gc();
	}
	public static void mostrarMensagem(){
		//setModal Prende o foco
		Mensagem m = new Mensagem("ESFERA CAPTURADA!!");
		destroyer(m);
	}
	public static void mostrarMensagem(String mensagem){
		//setModal Prende o foco
		Mensagem m = new Mensagem(mensagem);
		destroyer(m);
	}
}
