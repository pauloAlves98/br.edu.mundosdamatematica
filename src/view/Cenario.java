package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import model.Camada;
import model.CarregadorDeImagem;
import model.Goku;
import model.Som;
import model.Vilao;
//recuar mais saida de tiro a esquerda
//tratar a fase3
public class Cenario extends CenarioGenerico  {

	private boolean ativado2 = false;
	private Image transporteAtivado,transporteDesativado,kame;
	private Rectangle formaTransporteSoma,formaTransporteSubtracao,
	formaKame,formaTransporteMult,formaTransporteDiv; 
	private Aviso aviso;
	private Inventario pInfo;
	public  Som somFundo;
	private Camada camada01piso;
	private Camada camada02casa;
	private Camada camada03mato;
	private  Font font;
	private int matrizPosVilao[][] = {{207,233},{207,353},{311,442},
			{357,400},{546,354},{546,230}};
	
	public Cenario(){
		
		setDoubleBuffered(true);
		setFocusable(true);
		setPreferredSize(new Dimension(640,640));
		setSize(getLarguraMapa(),getAlturaMapa());
		setLayout(null);
		super.setPaint(true);
		somFundo = new Som("Sons//musicaFundo.wav");
		somFundo.somEmLoop(true);
		camada01piso = new Camada(20,20, 32, 32, "Mapas//tile00.png", "camada1cena1.txt");
		camada02casa = new Camada(20,20, 32, 32, "Mapas//tile00.png", "camada2cena1.txt");
		camada03mato = new Camada(20,20, 32, 32, "Mapas//tile00.png", "camada3cena1.txt");
		camada02casa.montarColisao(1);

		//aparencia,colunas,linhas,x,y
		setGoku(new Goku("Heroi//GokuSSJ1.png",0,3,4,0,0));
		
		for(int i= 0 ;i<6;i++){
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
		
		kame = CarregadorDeImagem.carregar("Outros//Sr.png");
		transporteAtivado =  CarregadorDeImagem.carregar("Outros//transporte.png");
		transporteDesativado = CarregadorDeImagem.carregar("Outros//naotransporte.png");

		pInfo = new Inventario(getGoku());
		aviso = new Aviso("VÁ PARA O PLANETA SOMA!");
		
		formaKame = new Rectangle(222,550,32,64);
		formaTransporteSoma = new Rectangle(5,294 + 20,10,12);
		formaTransporteSubtracao = new Rectangle(617+10,294 + 20,10,10);
		formaTransporteMult = new Rectangle(277,20,20,12);
		formaTransporteDiv = new Rectangle(340  ,20,20,12);
		

		camada01piso.montarMapa(640, 640);
		camada02casa.montarMapa(640, 640);
		camada03mato.montarMapa(640, 640);
		font= new Font("Agency FB",Font.BOLD,15);

		setVisible(true);

	}
	public void paint(Graphics g){
		
		Graphics2D g2d =(Graphics2D)g;
		g2d.drawImage(camada01piso.camada,0,0,null);
		g2d.drawImage(camada02casa.camada,0,0,null);
		g2d.drawImage(transporteAtivado,5,294+15,20,20,null);
		
		g2d.setFont(font);
		g2d.setColor(Color.BLACK);
		
		if(pInfo.isMostraE1())
			g2d.drawImage(transporteAtivado,617,294+15,20,20,null);
		else
		 g2d.drawImage(transporteDesativado,617,294 +15,20,20,null);
		if(pInfo.isMostraE2())
			g2d.drawImage(transporteAtivado,277,20,20,20,null);
		else
		 g2d.drawImage(transporteDesativado,277,20,20,20,null);
		if(pInfo.mostraE3)
			g2d.drawImage(transporteAtivado,340,20,20,20,null);
		else
		 g2d.drawImage(transporteDesativado,340,20,20,20,null);
		 
		 g2d.drawString("SOMA +",30,325);
		 g2d.drawString("SUBTRAÇÃO -",530,325);
		 g2d.drawString("DIVISÃO /",360,50);
		 g2d.drawString("MULTIPLICAÇÃO X",190,50);
		 g2d.drawString("SR.KAIOH",272,600);
		 //g2d.draw(formaTransporteDiv);
		 //g2d.draw(formaTransporteMult);
		
		 //pintando personagem
		getGoku().gokuRender(g2d);
		//g2d.drawImage(super.getGoku().getSprite().sprites[super.getGoku().getSprite().aparencia], super.getGoku().getX(), super.getGoku().getY(),super.getGoku().getSprite().sprites[super.getGoku().getSprite().aparencia].getWidth(),36,null);
		g2d.setColor(Color.black);
	
		Vilao.Render(g2d,getViloes());
		g2d.drawImage(camada03mato.camada,0,0,null);
//		for(int i = 0;i<camada02casa.formaTile.size();i++){
//			for(int j = 0; j<camada02casa.formaTile.get(i).size();j++)
//				 g2d.draw(camada02casa.formaTile.get(i).get(j));//mostra retangulo
//		}
		g2d.drawImage(kame,222,550,32,64,null);
		//g2d.drawRect(formaKame.x,formaKame.y,formaKame.width,formaKame.height);
		//g2d.draw(super.getGoku().getForma().getBounds2D());//mostra retangulo
		g2d.dispose();
		g.dispose();
	}
	public boolean isAtivado2() {
		return ativado2;
	}
	public void setAtivado2(boolean ativado2) {
		this.ativado2 = ativado2;
	}
	public Image getTransporteAtivado() {
		return transporteAtivado;
	}
	public void setTransporteAtivado(Image transporteAtivado) {
		this.transporteAtivado = transporteAtivado;
	}
	public Image getTransporteDesativado() {
		return transporteDesativado;
	}
	public void setTransporteDesativado(Image transporteDesativado) {
		this.transporteDesativado = transporteDesativado;
	}
	public Image getKame() {
		return kame;
	}
	public void setKame(Image kame) {
		this.kame = kame;
	}
	public Rectangle getFormaTransporteSoma() {
		return formaTransporteSoma;
	}
	public void setFormaTransporteSoma(Rectangle formaTransporteSoma) {
		this.formaTransporteSoma = formaTransporteSoma;
	}
	public Rectangle getFormaTransporteSubtracao() {
		return formaTransporteSubtracao;
	}
	public void setFormaTransporteSubtracao(Rectangle formaTransporteSubtracao) {
		this.formaTransporteSubtracao = formaTransporteSubtracao;
	}
	public Rectangle getFormaKame() {
		return formaKame;
	}
	public void setFormaKame(Rectangle formaKame) {
		this.formaKame = formaKame;
	}
	public Aviso getAviso() {
		return aviso;
	}
	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}
	public Inventario getpInfo() {
		return pInfo;
	}
	public void setpInfo(Inventario pInfo) {
		this.pInfo = pInfo;
	}
	public Camada getCamada01piso() {
		return camada01piso;
	}
	public void setCamada01piso(Camada camada01piso) {
		this.camada01piso = camada01piso;
	}
	public Camada getCamada02casa() {
		return camada02casa;
	}
	public void setCamada02casa(Camada camada02casa) {
		this.camada02casa = camada02casa;
	}
	public Camada getCamada03mato() {
		return camada03mato;
	}
	public void setCamada03mato(Camada camada03mato) {
		this.camada03mato = camada03mato;
	}
	public Rectangle getFormaTransporteMult() {
		return formaTransporteMult;
	}
	public void setFormaTransporteMult(Rectangle formaTransporteMult) {
		this.formaTransporteMult = formaTransporteMult;
	}
	public Rectangle getFormaTransporteDiv() {
		return formaTransporteDiv;
	}
	public void setFormaTransporteDiv(Rectangle formaTransporteDiv) {
		this.formaTransporteDiv = formaTransporteDiv;
	}
	public int[][] getMatrizPosVilao() {
		return matrizPosVilao;
	}
	
}
