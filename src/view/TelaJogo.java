package view;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.CarregadorDeImagem;

public class TelaJogo extends JFrame{
	
	private  JPanel painel;
	private  Cenario cenario1;
	private Fase1 fase1,fase3,fase4;
	private Fase2 fase2;
	///private Menu menu;
	private int largura = 500, altura = 520;
	
	public TelaJogo(Cenario cenario1 , Fase1 fase1, Fase2 fase2,Fase1 fase3,Fase1 fase4){
		//setUndecorated(true);
		
		setIconImage(new ImageIcon("Outros//uast.png").getImage());
		setResizable(false);
		//setSize(largura,altura);
		setSize(406,520);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
	
		
		this.fase1 = fase1;
		this.fase2  = fase2;
		this.fase3 = fase3;
		this.fase4 = fase4;
		this.painel = new JPanel(null);
		//this.menu = menu;
		this.cenario1 = cenario1;
		
		//adicionado ao frame
		//add(this.menu);
		add(this.painel);//adicionado ao frame
		add(this.cenario1.getpInfo());//inventario
		add(this.cenario1.getAviso());//aviso
		
		//adicionado ao painel
		this.painel.add(this.cenario1);
		this.painel.add(this.fase1);
		this.painel.add(this.fase2);
		this.painel.add(this.fase3);
		this.painel.add(this.fase4);
		this.painel.add(this.fase1.getPd());//desafio da fase1
		this.painel.add(this.fase2.getPd());//desafio da fase2
		this.painel.add(this.fase3.getPd());//desafio da fase1
		this.painel.add(this.fase4.getPd());//desafio da fase1
		this.painel.setBounds(0,100,400,300);
		
		//mudarCursor("Menu//esfera.png");
		
		//this.menu.setBounds(0, 0, largura, altura);
		this.cenario1.setBounds(0, 0, 640, 640);
		this.fase1.setBounds(0, 0, 640, 640);
		this.fase2.setBounds(0, 0,1280,1280);
		this.fase3.setBounds(0, 0, 640, 640);
		this.fase4.setBounds(0, 0, 640, 640);
		this.cenario1.getpInfo().setBounds(0, 0,400, 100);
		this.cenario1.getAviso().setBounds(0, 400, 400, 100);
		this.fase1.getPd().setBounds(0,0,400, 300);
		this.fase2.getPd().setBounds(0,0,400, 300);
		this.fase3.getPd().setBounds(0,0,400, 300);
		this.fase4.getPd().setBounds(0,0,400, 300);
		
		//getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setVisible(false);//tem que esta depois de tudo;
		//this.menu.setVisible(true);
	}
	public void mudarCursor(String diretorio){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		String fileName = diretorio;
		//File file = new File(fileName);
		Image image = null;
		image = CarregadorDeImagem.carregar(diretorio);
		Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "cursor");
		getContentPane().setCursor(c);
	}
	public JPanel getPainel() {
		return painel;
	}
	public void setPainel(JPanel painel) {
		this.painel = painel;
	}
	public Cenario getCenario1() {
		return cenario1;
	}
	public void setCenario1(Cenario cenario1) {
		this.cenario1 = cenario1;
	}
	public Fase1 getFase1() {
		return fase1;
	}
	public void setFase1(Fase1 fase1) {
		this.fase1 = fase1;
	}
	public Fase2 getFase2() {
		return fase2;
	}
	public void setFase2(Fase2 fase2) {
		this.fase2 = fase2;
	}
//	public Menu getMenu() {
//		return menu;
//	}
//	public void setMenu(Menu menu) {
//		this.menu = menu;
//	}
	public int getLargura() {
		return largura;
	}
	public void setLargura(int largura) {
		this.largura = largura;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
}
