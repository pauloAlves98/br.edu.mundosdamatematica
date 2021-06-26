package controller;

import java.util.ArrayList;

import model.Capsula;
import model.Goku;
import model.Vilao;
import view.Cenario;
import view.Fase1;

public class ControleFase1 extends ControlerGenerico {
	
	private Fase1 fase1;
	private Cenario cena;
	private ControlerDS cds;

	public ControleFase1(Goku perso,Fase1 fase, Cenario cena ,ControlerDS cds){
		
		this.setGoku(perso);
		this.setFase1(fase);
		setViloes(fase.getViloes());
		this.cena  = cena;
		this.cds = cds;
		//Eventos de KEyListener adicionados ao Frame
	}
	public void moverCena(int x, int y){
		if(x> 400/2)
			if(x<(getFase1().getLarguraMapa() - 400/2))//pega a variação de movimento
				getFase1().setxCena(-(x-(400/2)));
		if(y>300/2)
			if(y < (getFase1().getAlturaMapa() - (300/2)))//pega a variação de movimento 
				getFase1().setyCena(-(y - 300/2));
		getFase1().setLocation(getFase1().getxCena(), getFase1().getyCena());
	}
	
	public void checarColisao(int largura, int altura){
		
		checarMovimento(largura,altura);
		getGoku().colisaoVilao(getViloes());
		
		for(int i = 0;i<getFase1().getCamada02().formaTile.size();i++){
			//verifica se a forma esta no intervalo descrito e indefica as colisoes somente da linha em que o personagem esta
			if(getGoku().getForma().y <= getFase1().getCamada02().formaTile.get(i).get(0).getY()+ 32 && getGoku().getForma().y >= getFase1().getCamada02().formaTile.get(i).get(0).getY())
				if(getGoku().colisao(getFase1().getCamada02().formaTile.get(i))){
					getGoku().recuar();
					break;
				}
			}
		for(Vilao v:getViloes()){//
			v.vilaoColisaoCenario(getFase1().getCamada02().formaTile);
		}
		
		if(Capsula.colisaoCapsula(getGoku(),getFase1().capsulas)){
			getFase1().setVisible(false);
			getGoku().stop();
			getFase1().setPaint(false);
			cds.destrava = 0;
			getFase1().getPd().setVisible(true);
		}
		if(getFase1().getFormaBarreira()!=null){
			if(getGoku().colisao(getFase1().getFormaBarreira())){
				if(getFase1().capsulas.size()<=0){//é por que conseguiu as capsulas
					getFase1().setFormaBarreira(null);
					getFase1().setBarreira(null);
					//destrutor generico nao funfou
				}else {
					cena.getAviso().setStr("PEGUE MAIS CAPSULAS!PRESS(x)");
					getGoku().recuar();//mostraAvisoBarreira
					getFase1().setMostraAviso(true);
					getFase1().setPaint(false);
				}
			}
		}
		if(getFase1().getCapsula()!=null){
			
			if(getGoku().colisao(getFase1().getCapsula().getForma())){
				cena.getAviso().setStr("AHHH!TRANSFORMAÇÃO CONCLUIDA!APERTE(x)");
				cena.getAviso().setCapsula(getFase1().getSayajinInfo());
				getFase1().setPaint(false);
				getGoku().setVelocidade(6);
				getFase1().setMostraAviso(true);
				getFase1().setCapsula(null);
				getGoku().getSprite().mudarSprite(getFase1().getSsj(),4,3);
			}
		}
		if(getFase1().getFormaEsfera()!=null){
			if(getGoku().colisao(getFase1().getFormaEsfera()) ){
				getFase1().setPaint(false);
				getFase1().setVisible(false);
				getFase1().getPd().setVisible(true);
				getFase1().setFormaEsfera(null);
				getFase1().setEsfera(null);
				getGoku().stop();
				cds.destrava = 0;
				System.gc();
			}
		}	
		if(getGoku().colisao(getFase1().getFormaNave()) ){
			
			getGoku().setX(225);
			getGoku().setY(291);
			getGoku().stop();
			cena.setVisible(true);
			getFase1().setPaint(false);
			cena.setPaint(true);
			//cena.xCena = 0;
			getFase1().setVisible(false);
			resetarPosicaoVilao();
			
		}
		//controler mensagem
		if(getFase1().isMostraAviso() == false){
			if(cena.getpInfo().isMostraE1() && cena.getpInfo().isMostraE2() && cena.getpInfo().mostraE3 && cena.getpInfo().mostraE4)
				cena.getAviso().reset("ENCONTRE O SR.KAIOH NA CIDADE!");
			else if(getFase1().capsulas.size()>0){
				cena.getAviso().setStr("PEGUE "+getFase1().capsulas.size()+" CAPSULAS!!");
			}else if(cena.getpInfo().isMostraE1())
				cena.getAviso().reset("RETORNE A CIDADE!");
			else if(getFase1().capsulas.size()<=0){
				cena.getAviso().setStr("CAPTURE A ESFERA!");
			}
		}
		
		updatePersonagem();
	}
	public void updatePersonagem(){
		getFase1().setGoku(getGoku());
//		getFase1().getGoku().setX(getGoku().getX());
//		getFase1().getGoku().setY(getGoku().getY());
//	    //fase1.goku.direita =  goku.direita;
//		//		fase1.goku.esquerda = goku.esquerda;
//		//		fase1.goku.baixo = goku.baixo;
//		//		fase1.goku.cima = goku.cima;
//		getFase1().getGoku().setLife(getGoku().getLife());
//		getFase1().getGoku().getSprite().sprites = getGoku().getSprite().sprites;
//		getFase1().getGoku().getSprite().aparencia = getGoku().getSprite().aparencia;
	}
	public Fase1 getFase1() {
		return fase1;
	}
	public void setFase1(Fase1 fase1) {
		this.fase1 = fase1;
	}
	public void resetarPosicaoVilao(){
		for(int i= 0 ;i<getFase1().getMatrizPosVilao().length;i++){
			getViloes().get(i).setX(getFase1().getMatrizPosVilao()[i][0]);
			getViloes().get(i).setY(getFase1().getMatrizPosVilao()[i][1]);
			getViloes().get(i).novaDirecao();
			if(i%2==0){
				getViloes().get(i).podePerseguir = true;
			}
			getViloes().get(i).visivel = true;
			getViloes().get(i).setLife(0);
		}
	}
}
