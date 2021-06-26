package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import controller.ControlerDS;
import model.Camada;
import model.Capsula;
import model.CarregadorDeImagem;
import model.Goku;
import model.Vilao;
//dar setPreffereSize(nos paineis, 400x300)


public class Fase2 extends CenarioGenerico {

	private Desafio pd;
	private  Image nave,esfera,portal;
	private Rectangle formaNave,formaEsfera,formaPortal;
	private Camada camada01,camada02,camada03;
	private ArrayList<Capsula>capsulas = new ArrayList<Capsula>();
	private ArrayList<Vilao> viloes = new ArrayList<Vilao>();
	private int matrizPosCapsulas[][] = {{545,118},{33,891},{210,660},{402,1212},
			{537,1131},{582,846},{924,813},{726,561},{912,534},{900,27}};
	private int matrizPosVilao[][] = {{96,792},{453,567},{579,924},
			{773,1221},{975,1083},{1218,255},{1029,285}};

	public Fase2(){
		
		setDoubleBuffered(true);
		setFocusable(true);
		setPreferredSize(new Dimension(1280,1280));
		setSize(1280,1280);
		setLayout(null);
		
		super.setGoku(new Goku("Heroi//GokuSSJ1.png",0,3,4,0,0));
		super.setLarguraMapa(1280);
		super.setAlturaMapa(1280);
		this.pd = new Desafio();
	
		camada01 =  new Camada(40,40, 32, 32, "Mapas//tile02.png", "camada1fase2.txt");
		camada02 =  new Camada(40,40, 32, 32, "Mapas//tile02.png", "camada2fase2.txt");
		camada03 =  new Camada(40,40, 32, 32, "Mapas//tile02.png", "camada3fase2.txt");

		camada02.montarColisao(1);

		portal = CarregadorDeImagem.carregar("Outros//portal.png");
		nave = CarregadorDeImagem.carregar("Outros//capsula.png");
		esfera  =  CarregadorDeImagem.carregar("Outros//esferaSubtracao.png");
		
		Random random = new Random();
		for(int i= 0 ;i<7;i++){
			Vilao vilao;
			int n = random.nextInt(3);
			if(n == 0)
				vilao = new Vilao("Inimigos//Vilao01.png",0,3,4,0,0);
			else if(n==1)
				vilao = new Vilao("Inimigos//Vilao02.png",0,3,4,0,0);
			else
				vilao = new Vilao("Inimigos//Frezer.png",0,3,4,0,0);
			vilao.setX(matrizPosVilao[i][0]);
			vilao.setY(matrizPosVilao[i][1]);
			if(i%2==0){
				vilao.podePerseguir = true;
				vilao.setVelocidade(1);
			}
			vilao .novaDirecao();
			viloes.add(vilao);
		}

		for(int i=0;i<matrizPosCapsulas.length;i++){
			capsulas.add(new Capsula(matrizPosCapsulas[i][0],matrizPosCapsulas[i][1]));
		}

		camada01.montarMapa(1280, 1280);
		camada02.montarMapa(1280, 1280);
		camada03.montarMapa(1280, 1280);

		formaNave = new Rectangle(20,60,32,32);
		formaEsfera = new Rectangle(1190,1200,50,32);
		formaPortal = new Rectangle(1185,1155,64,20);

		setVisible(false);
	}
	public void paintComponent(Graphics g){
	
		Graphics2D g2d =(Graphics2D) g;

		g2d.drawImage(camada01.camada,0,0,null);
		g2d.drawImage(camada02.camada,0,0,null);

		for(Capsula capsula:capsulas){
			g2d.drawImage(capsula.getCapsula(),capsula.getX(),capsula.getY(),30,30,null);
			//g2d.draw(capsula.getForma());
		}
		Vilao.Render(g2d,getViloes());
//		for(int i = 0;i<viloes.size();i++){//trocar por for de objeto
//			//usar Viloes.render
//			g2d.drawImage(viloes.get(i).getSprite().sprites[viloes.get(i).getSprite().aparencia],viloes.get(i).getX(), viloes.get(i).getY(),viloes.get(i).getSprite().sprites[viloes.get(i).getSprite().aparencia].getWidth(),40,null);
//			g2d.draw(viloes.get(i).getForma());
//		}
		
		getGoku().gokuRender(g2d);
		//g2d.drawImage(super.getGoku().getSprite().sprites[super.getGoku().getSprite().aparencia], super.getGoku().getX(), super.getGoku().getY(),super.getGoku().getSprite().sprites[super.getGoku().getSprite().aparencia].getWidth(),36,null);
		g2d.drawImage(nave,0,40,null);
		
		if(formaEsfera!=null){
			g2d.drawImage(esfera,1190,1200,64,32,null);
			//g2d.draw(formaEsfera);
		}
		if(formaPortal!=null){
			g2d.drawImage(portal,1185,1155,64,20,null);
			//g2d.draw(formaPortal);
		}
		//g2d.draw(formaNave);
		g2d.drawImage(camada03.camada,0,0,null);
		g.dispose();
	}
	public Desafio getPd() {
		return pd;
	}
	public void setPd(Desafio pd) {
		this.pd = pd;
	}
	public Image getNave() {
		return nave;
	}
	public void setNave(Image nave) {
		this.nave = nave;
	}
	public Image getEsfera() {
		return esfera;
	}
	public void setEsfera(Image esfera) {
		this.esfera = esfera;
	}
	public Image getPortal() {
		return portal;
	}
	public void setPortal(Image portal) {
		this.portal = portal;
	}
	public Rectangle getFormaNave() {
		return formaNave;
	}
	public void setFormaNave(Rectangle formaNave) {
		this.formaNave = formaNave;
	}
	public Rectangle getFormaEsfera() {
		return formaEsfera;
	}
	public void setFormaEsfera(Rectangle formaEsfera) {
		this.formaEsfera = formaEsfera;
	}
	public Rectangle getFormaPortal() {
		return formaPortal;
	}
	public void setFormaPortal(Rectangle formaPortal) {
		this.formaPortal = formaPortal;
	}
	public Camada getCamada01() {
		return camada01;
	}
	public void setCamada01(Camada camada01) {
		this.camada01 = camada01;
	}
	public Camada getCamada02() {
		return camada02;
	}
	public void setCamada02(Camada camada02) {
		this.camada02 = camada02;
	}
	public Camada getCamada03() {
		return camada03;
	}
	public void setCamada03(Camada camada03) {
		this.camada03 = camada03;
	}
	public ArrayList<Capsula> getCapsulas() {
		return capsulas;
	}
	public void setCapsulas(ArrayList<Capsula> capsulas) {
		this.capsulas = capsulas;
	}
	public ArrayList<Vilao> getViloes() {
		return viloes;
	}
	public void setViloes(ArrayList<Vilao> viloes) {
		this.viloes = viloes;
	}
	public int[][] getMatrizPosCapsulas() {
		return matrizPosCapsulas;
	}
	public void setMatrizPosCapsulas(int[][] matrizPosCapsulas) {
		this.matrizPosCapsulas = matrizPosCapsulas;
	}
	public int[][] getMatrizPosVilao() {
		return matrizPosVilao;
	}
	
}
