 package controller;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.Capsula;
import model.Goku;
import model.Vilao;
import view.Cenario;
import view.Fase1;
import view.Fase2;

public class ControlerFase2 extends ControlerGenerico {
	//comecar fase 3
	//slterar inventario
	private Fase2 fase2View;
	
	private Cenario cenarioView;
	private ControlerDS controlerDesafio;//controler Desafio!
	public ControlerFase2(Goku perso,Fase2 fase, Cenario cena,	ControlerDS cds){
		this.fase2View = fase;
		setGoku(perso);
		this.cenarioView = cena;
		setViloes(fase.getViloes());
		this.controlerDesafio = cds;
		//this.fase2View.addKeyListener(new TAdapter(getGoku(),fase2View));
	}
	public void moverCena(int x, int y){
		if(x> 400/2)
			if(x<(fase2View.getLarguraMapa() - 400/2))//pega a variação de movimento
				fase2View.setxCena(-(x-(400/2)));
		if(y>300/2)
			if(y < (fase2View.getAlturaMapa() - (300/2)))//pega a variação de movimento 
				fase2View.setyCena(-(y - 300/2));
		fase2View.setLocation(fase2View.getxCena(), fase2View.getyCena());
	}
	public void checarColisao(int largura, int altura){
		
		checarMovimento(largura,altura);//atualiza viloes
		getGoku().colisaoVilao(getViloes());
		
		for(int i = 0;i<fase2View.getCamada02().formaTile.size();i++){
			//demorei muito pra descobrr esse lag que estava dando por causa disto
			//verifica se a forma esta no intervalo descrito e indefica as colisoes somente da linha em que o personagem esta
			//desta maneira evitasse interaçoes com formas que nao estao proximas ao personagem 
			//aumanetando a performace; 32 é o tamanho do tileset
			//camada02.formaTile.get(i).get(0).getY() eh pegado somente o promeiro retangulo 
			//do array de formas para conparaçao, pra onfirma se o personagem esta nesta linha
			if(getGoku().getForma().y <= fase2View.getCamada02().formaTile.get(i).get(0).getY() + 32 && getGoku().getForma().y >= fase2View.getCamada02().formaTile.get(i).get(0).getY())
				if(getGoku().colisao(fase2View.getCamada02().formaTile.get(i))){
					getGoku().recuar();
					break;
				}
		}
		for(Vilao v:getViloes()){//
			v.vilaoColisaoCenario(getFase2View().getCamada02().formaTile);
		}
		

		if(Capsula.colisaoCapsula(getGoku(), fase2View.getCapsulas())){
			fase2View.setVisible(false);
			getGoku().stop();
			fase2View.setPaint(false);
			controlerDesafio.destrava = 0;
			fase2View.getPd().setVisible(true);
		}
		if(fase2View.getFormaPortal()!=null)
			if(getGoku().colisao(fase2View.getFormaPortal())){
				if(fase2View.getCapsulas().size()==0){
					fase2View.setPortal(null);
					fase2View.setFormaPortal(null);
					System.gc();
				}else{
					fase2View.setMostraAviso(true);
					fase2View.setPaint(false);
					getGoku().recuar();
					getGoku().stop();
					cenarioView.getAviso().setStr("ENCONTRE "+fase2View.getCapsulas().size()+" CAPSULAS! PRESS(X)");
				}
			}
		if(fase2View.getFormaEsfera()!=null){
			if(getGoku().colisao(fase2View.getFormaEsfera())){
				fase2View.setFormaEsfera(null);
				fase2View.setEsfera(null);
				fase2View.getPd().setVisible(true);
				fase2View.setVisible(false);
				getGoku().stop();
				fase2View.setPaint(false);
				controlerDesafio.destrava = 0;
				System.gc();
			}
		}
		if(getGoku().colisao(fase2View.getFormaNave())){
			cenarioView.setVisible(true);
			cenarioView.setPaint(true);
			fase2View.setPaint(false);
			fase2View.setVisible(false);
			getGoku().setX(572);
			getGoku().setY(291);
			getGoku().stop();
			resetarPosicaoVilao();
		}
		//controle de avisos
		if(fase2View.isMostraAviso() == false){
			if(cenarioView.getpInfo().isMostraE1() && cenarioView.getpInfo().isMostraE2())
				cenarioView.getAviso().reset("VÁ ATÉ A CIDADE!");
			else if(fase2View.getCapsulas().size()>0){
				cenarioView.getAviso().setStr("ENCONTRE "+fase2View.getCapsulas().size()+" CAPSULAS!");
			}else if(fase2View.getCapsulas().size()<=0){
				cenarioView.getAviso().setStr("CAPTURE A ESFERA!");
			}
		}
		cenarioView.getpInfo().repaint();
		updatePersonagem();
	}

	public void updatePersonagem(){
		fase2View.setGoku(getGoku());
		fase2View.getGoku().setX(getGoku().getX());
		fase2View.getGoku().setY(getGoku().getY());
		//		fase2.goku.direita =  goku.direita;
		//		fase2.goku.esquerda = goku.esquerda;
		//		fase2.goku.baixo = goku.baixo;
		//		fase2.goku.cima = goku.cima;
		fase2View.getGoku().setLife(getGoku().getLife());
		fase2View.getGoku().getSprite().sprites = getGoku().getSprite().sprites;
		fase2View.getGoku().getSprite().sprites = getGoku().getSprite().sprites;
		fase2View.getGoku().getSprite().aparencia = getGoku().getSprite().aparencia;
		//Life
	}
	
	
	public Fase2 getFase2View() {
		return fase2View;
	}
	public void setFase2View(Fase2 fase2View) {
		this.fase2View = fase2View;
	}
	public Cenario getCenarioView() {
		return cenarioView;
	}
	public void setCenarioView(Cenario cenarioView) {
		this.cenarioView = cenarioView;
	}
	public void resetarPosicaoVilao(){
		
		for(int i= 0 ;i<getFase2View().getMatrizPosVilao().length;i++){
			getViloes().get(i).setX(getFase2View().getMatrizPosVilao()[i][0]);
			getViloes().get(i).setY(getFase2View().getMatrizPosVilao()[i][1]);
			getViloes().get(i).novaDirecao();
			if(i%2==0){
				getViloes().get(i).podePerseguir = true;
				getViloes().get(i).setVelocidade(1);
			}
			getViloes().get(i).visivel = true;
			getViloes().get(i).setLife(0);
		}
	}

}
