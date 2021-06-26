package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import model.CarregadorDeImagem;
import model.Goku;
import model.Tempo;

public class Inventario extends JPanel {
	
	private Goku goku;
	private Font font,font2;
	private Image esfera1,esfera2,esfera3,esfera4,perfil;
	private boolean mostraE1 = false;
	private boolean mostraE2 = false;
	public boolean mostraE3 =  false,mostraE4=false;
	private Tempo tempo;
	private boolean rodando = true;
	
	public Inventario (Goku goku){
		setDoubleBuffered(true);
	
		this.tempo = new Tempo();
		this.setPreferredSize(new Dimension(400,100));
		this.goku = goku;
		this.font = new Font("Arial",Font.BOLD,30);
		this.font2 = new Font("Arial",Font.BOLD,15);


		esfera1 = CarregadorDeImagem.carregar("Outros//esferaSoma.png");
		esfera2 = CarregadorDeImagem.carregar("Outros//esferaSubtracao.png");
		esfera3 = CarregadorDeImagem.carregar("Outros//esferaMultiplicacao.png");
		esfera4 = CarregadorDeImagem.carregar("Outros//esferaDivisão.png");
		
		perfil = CarregadorDeImagem.carregar("Outros//Perfil.png");//GOKU DE PERFIL
		
		setVisible(true);
		//new ThreadInfo().start();
	}
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
	
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, 400, 100);
		g2d.setColor(Color.green);
		g2d.drawRect(1, 1, 397, 97);
		
		//Configurando barra de life
		g2d.drawImage(perfil,3,30,55,67,null);
		g2d.setColor(Color.yellow);
		g2d.setFont(font2);
		g2d.drawString("Life",2+60,44 );
		g2d.drawOval(34 + 60 ,30, 60, 60);
		g2d.setColor(Color.green);
		g2d.fillOval(34 + 60 , 30 , 60, 60);
		g2d.setColor(Color.white);
		//x,y,larg,alt,anguloInicial,anguloFinal
		g2d.fillArc(34 + 60,30,60, 60,0, goku.getLife());//Ganha life
		//Fim barra de life
		g2d.setColor(Color.white);
		
		//Esferas
		g2d.setFont(font2);
		g2d.drawString("Esferas",(70 + 58) + 20 + 48,44);
		g2d.setColor(Color.white);
		g2d.drawOval((65+45) + 50,55, 30, 30);
		g2d.drawOval((65+45) + 80,55, 30, 30);
		g2d.drawOval((65+45) + 110,55, 30, 30);
		g2d.drawOval((65+45) + 140,55, 30, 30);
		// x + Largura,y,twi,tHeigh
		
		//mostra esfera ganha
		g2d.drawString("Tempo",(70 + 58) + 170,44);
		g2d.setFont(font);
		g2d.drawString(tempo.getHora()+":"+tempo.getMinuto()+":"+tempo.getSegundo(),(70 + 58) +170,80);
		if(mostraE1)
			g2d.drawImage(esfera1,(65+35) + 50,48,50,45,null);
		if(mostraE2)
			g2d.drawImage(esfera2,(65+35)+ 80,48,50,45,null);
		if(mostraE3)
			g2d.drawImage(esfera3,(65+35) + 110,48,50,45,null);
		if(mostraE4)
			g2d.drawImage(esfera4,(65+35)+ 140,48,50,45,null);
		

	}
	public Goku getGoku() {
		return goku;
	}
	public void setGoku(Goku goku) {
		this.goku = goku;
	}
	public boolean isMostraE1() {
		return mostraE1;
	}
	public void setMostraE1(boolean mostraE1) {
		this.mostraE1 = mostraE1;
	}
	public boolean isMostraE2() {
		return mostraE2;
	}
	public void setMostraE2(boolean mostraE2) {
		this.mostraE2 = mostraE2;
	}
	public Tempo getTempo() {
		return tempo;
	}
	public void setTempo(Tempo tempo) {
		this.tempo = tempo;
	}
	public boolean isRodando() {
		return rodando;
	}
	public void setRodando(boolean rodando) {
		this.rodando = rodando;
	}
	public void setPerfil(Image perfil) {
		this.perfil = perfil;
	}
	
}
