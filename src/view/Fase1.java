package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import model.Camada;
import model.Capsula;
import model.CarregadorDeImagem;
import model.Goku;
import model.Vilao;

//verificar interseçao com capsula e com esfera!
public class Fase1 extends CenarioGenerico{

	private boolean  pintaEsfera = true,pCapsula = true;
	private BufferedImage  ssj;
	private Image nave ,barreira,sayajinInfo,esfera;
	private Rectangle formaNave,formaBarreira,formaEsfera;
	private  Camada camada01,camada02,camada03,camada04;
	private Capsula capsula;
	private Desafio pd;
	public ArrayList<Capsula>capsulas = new ArrayList<Capsula>();
	private int matrizPosCapsulas[][] = {{617,180},{467,90},{547,400},{287,540}};
	private int matrizPosVilao[][] = {{27,370 +32},{144,364},{27,485},
			{494,321},{421,140},{247,97},{216,527},{433,508}};
	
	public Fase1(){
		
		setDoubleBuffered(true);
		setFocusable(true);
		setPreferredSize(new Dimension(640,640));
		setSize(640,640);
		
		setGoku(new Goku("Heroi//GokuSSJ1.png",0,3,4,0,0));
		pd = new Desafio();
	
		camada01 =  new Camada(20,20, 32, 32, "Mapas//tile01.png", "camada01soma.txt");
		camada02 =  new Camada(20,20, 32, 32, "Mapas//tile001.png", "camada02soma.txt");
		camada03 =  new Camada(20,20, 32, 32, "Mapas//tile0001.png", "camada03soma.txt");
		camada04 =  new Camada(20,20, 32, 32, "Mapas//tile001.png", "camada04soma.txt");

		camada02.montarColisao(1);

		camada01.montarMapa(640, 640);
		camada02.montarMapa(640, 640);
		camada03.montarMapa(640, 640);
		camada04.montarMapa(640, 640);


		nave = CarregadorDeImagem.carregar("Outros//capsula.png");
		barreira = CarregadorDeImagem.carregar("Outros//barreira.png");
		sayajinInfo = CarregadorDeImagem.carregar("Outros//sayajinInfo.png");
		ssj = CarregadorDeImagem.carregar("Heroi//GokuSSJ3.png");
		esfera = CarregadorDeImagem.carregar("Outros//esferaSoma.png");
		
		for(int i= 0 ;i<matrizPosVilao.length;i++){
			Vilao vilao;
			vilao = new Vilao("Inimigos//Frezer.png",0,3,4,0,0);
			vilao.setX(matrizPosVilao[i][0]);
			vilao.setY(matrizPosVilao[i][1]);
			vilao.novaDirecao();
			if(i%2==0){
				vilao.podePerseguir = true;
				vilao.setVelocidade(1);
			}
			getViloes().add(vilao);
		}
		for(int i=0;i<matrizPosCapsulas.length;i++){
			capsulas.add(new Capsula(matrizPosCapsulas[i][0],matrizPosCapsulas[i][1]));
		}
		
		capsula = new Capsula(75,298);//separada pois nao eh obrigado pegar
		formaNave  = new Rectangle(270,324,32,32);
		formaBarreira = new Rectangle(154,70,16,64);
		formaEsfera = new Rectangle(64,165,16,10);

		setVisible(false);

	}
	public void paint(Graphics g){

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(camada01.camada,0,0,null);
		g2d.drawImage(camada02.camada,0,0,null);
		g2d.drawImage(camada03.camada,0,0,null);
		
		for(Capsula capsula:capsulas){
			g2d.drawImage(capsula.getCapsula(),capsula.getX(),capsula.getY(),30,30,null);
			//g2d.draw(capsula.getForma());
		}
		if(formaEsfera!=null){
			g2d.drawImage(esfera, 40, 155,64,32, null);
			//g2d.draw(formaEsfera.getBounds2D());
		}
		if(capsula!=null){
			g2d.drawImage(capsula.getCapsula(), 68, 298,32,32, null);
			//g2d.draw(capsula.getForma().getBounds2D());
		}
		if(formaBarreira!=null){
			g2d.drawImage(barreira, 154, 80, null);
			//g2d.draw(formaBarreira.getBounds2D());
		}
		//g2d.drawImage(super.getGoku().getSprite().sprites[super.getGoku().getSprite().aparencia], super.getGoku().getX(), super.getGoku().getY(),super.getGoku().getSprite().sprites[super.getGoku().getSprite().aparencia].getWidth(),36,null);
		getGoku().gokuRender(g2d);
		//g2d.draw(getGoku().getForma());
		//g2d.draw(getGoku().intersecaoComVilao);
		
		Vilao.Render(g2d,getViloes());
//		for(int i = 0;i<getViloes().size();i++){//trocar por for de objeto
//			//trocar por vialo.reder
//			g2d.drawImage(getViloes().get(i).getSprite().sprites[getViloes().get(i).getSprite().aparencia],getViloes().get(i).getX(), getViloes().get(i).getY(),getViloes().get(i).getSprite().sprites[getViloes().get(i).getSprite().aparencia].getWidth(),40,null);
//			g2d.draw(getViloes().get(i).getForma());
//		}
		
		g2d.drawImage(camada04.camada,0,0,null);
		
		g2d.setColor(Color.black);
		
		
		
//		for(int i = 0;i<camada02.formaTile.size();i++){//array de Array pode remover!
//			for(int j = 0; j<camada02.formaTile.get(i).size();j++)
//				g2d.draw(camada02.formaTile.get(i).get(j));//mostra retangulo
//		}
		
		//g2d.draw(getGoku().getForma());//mostra retangulo
		//g2d.draw(getGoku().intersecaoComVilao);
		
		g2d.drawImage(nave, 250, 292, null);
		//g2d.draw(formaNave.getBounds2D());
		g.dispose();
	}
	public boolean isPintaEsfera() {
		return pintaEsfera;
	}
	public void setPintaEsfera(boolean pintaEsfera) {
		this.pintaEsfera = pintaEsfera;
	}
	public boolean ispCapsula() {
		return pCapsula;
	}
	public void setpCapsula(boolean pCapsula) {
		this.pCapsula = pCapsula;
	}
	public BufferedImage getSsj() {
		return ssj;
	}
	public void setSsj(BufferedImage ssj) {
		this.ssj = ssj;
	}
	public Image getNave() {
		return nave;
	}
	public void setNave(Image nave) {
		this.nave = nave;
	}
	public Image getBarreira() {
		return barreira;
	}
	public void setBarreira(Image barreira) {
		this.barreira = barreira;
	}
	public Image getSayajinInfo() {
		return sayajinInfo;
	}
	public void setSayajinInfo(Image sayajinInfo) {
		this.sayajinInfo = sayajinInfo;
	}
	public Image getEsfera() {
		return esfera;
	}
	public void setEsfera(Image esfera) {
		this.esfera = esfera;
	}
	public Rectangle getFormaNave() {
		return formaNave;
	}
	public void setFormaNave(Rectangle formaNave) {
		this.formaNave = formaNave;
	}
	public Rectangle getFormaBarreira() {
		return formaBarreira;
	}
	public void setFormaBarreira(Rectangle formaBarreira) {
		this.formaBarreira = formaBarreira;
	}
	public Rectangle getFormaEsfera() {
		return formaEsfera;
	}
	public void setFormaEsfera(Rectangle formaEsfera) {
		this.formaEsfera = formaEsfera;
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
	public Camada getCamada04() {
		return camada04;
	}
	public void setCamada04(Camada camada04) {
		this.camada04 = camada04;
	}
	public Capsula getCapsula() {
		return capsula;
	}
	public void setCapsula(Capsula capsula) {
		this.capsula = capsula;
	}
	public Desafio getPd() {
		return pd;
	}
	public void setPd(Desafio pd) {
		this.pd = pd;
	}
	public ArrayList<Capsula> getCapsulas() {
		return capsulas;
	}
	public void setCapsulas(ArrayList<Capsula> capsulas) {
		this.capsulas = capsulas;
	}
	public int[][] getMatrizPosVilao() {
		return matrizPosVilao;
	}
	
}
