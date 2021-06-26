package controller;

import model.Capsula;
import model.Goku;
import model.Vilao;
import view.Cenario;
import view.Fase1;

public class ControlerFase4 extends ControlerGenerico {
	private Fase1 fase4;
	private Cenario cena;
	private ControlerDS cds;
	public ControlerFase4(Goku perso,Fase1 fase4, Cenario cena ,ControlerDS cds){
		this.setGoku(perso);
		this.fase4 = fase4;
		setViloes(fase4.getViloes());
		this.cena  = cena;
		this.cds = cds;
		//this.getFase4().addKeyListener(new TAdapter(getGoku(),getFase4()));
	}
	public void moverCena(int x, int y){
		if(x> 400/2)
			if(x<(getFase4().getLarguraMapa() - 400/2))//pega a variação de movimento
				getFase4().setxCena(-(x-(400/2)));
		if(y>300/2)
			if(y < (getFase4().getAlturaMapa() - (300/2)))//pega a variação de movimento 
				getFase4().setyCena(-(y - 300/2));
		getFase4().setLocation(getFase4().getxCena(), getFase4().getyCena());
	}
	
	public void checarColisao(int largura, int altura){
		checarMovimento(largura,altura);
		getGoku().colisaoVilao(getViloes());
		
		for(int i = 0;i<getFase4().getCamada02().formaTile.size();i++){
			//verifica se a forma esta no intervalo descrito e indefica as colisoes somente da linha em que o personagem esta
			if(getGoku().getForma().y <= getFase4().getCamada02().formaTile.get(i).get(0).getY()+ 32 && getGoku().getForma().y >= getFase4().getCamada02().formaTile.get(i).get(0).getY())
				if(getGoku().colisao(getFase4().getCamada02().formaTile.get(i))){
					getGoku().recuar();
					break;
				}
			}
		for(Vilao v:getViloes()){//
			v.vilaoColisaoCenario(getFase4().getCamada02().formaTile);
		}
		
		if(Capsula.colisaoCapsula(getGoku(),getFase4().capsulas)){
			getFase4().setVisible(false);
			getGoku().stop();
			getFase4().setPaint(false);
			cds.destrava = 0;
			getFase4().getPd().setVisible(true);
		}
		if(getFase4().getFormaBarreira()!=null){
			if(getGoku().colisao(getFase4().getFormaBarreira())){
				if(getFase4().capsulas.size()<=0){//é por que conseguiu as capsulas
					getFase4().setFormaBarreira(null);
					getFase4().setBarreira(null);
					//destrutor generico nao funfou
				}else {
					cena.getAviso().setStr("PEGUE MAIS CAPSULAS!\nPRESS(x)");
					getGoku().recuar();//mostraAvisoBarreira
					getFase4().setMostraAviso(true);
					getFase4().setPaint(false);
				}
			}
		}
		if(getFase4().getCapsula()!=null){
			if(getGoku().colisao(getFase4().getCapsula().getForma())){
				getGoku().setLife(getGoku().getLife()-10);//ganha life
				getFase4().setCapsula(null);
			}
//			if(getGoku().colisao(getFase3().getCapsula().getForma())){
//				cena.getAviso().setStr("Ahhhhhh! TRANSFORMAÇÃO CONCLUIDA! APERTE(x)");
//				cena.getAviso().setCapsula(getFase3().getSayajinInfo());
//				getFase3().setPaint(false);
//				getGoku().setVelocidade(6);
//				getFase3().setMostraAviso(true);
//				getFase3().setCapsula(null);
//				getGoku().getSprite().mudarSprite(getFase3().getSsj(),4,3);
//			}
		}
		if(getFase4().getFormaEsfera()!=null){
			if(getGoku().colisao(getFase4().getFormaEsfera()) ){
				getFase4().setPaint(false);
				getFase4().setVisible(false);
				getFase4().getPd().setVisible(true);
				getFase4().setFormaEsfera(null);
				getFase4().setEsfera(null);
				getGoku().stop();
				cds.destrava = 0;
				System.gc();
			}
		}	
		if(getGoku().colisao(getFase4().getFormaNave()) ){
			getGoku().setX(225);
			getGoku().setY(291);
			getGoku().stop();
			cena.setVisible(true);
			getFase4().setPaint(false);
			cena.setPaint(true);
			//cena.xCena = 0;
			getFase4().setVisible(false);
			resetarPosicaoVilao();
			
		}
		//controler mensagem
		
		//tratar isso
		if(getFase4().isMostraAviso() == false){
			if(cena.getpInfo().isMostraE1() && cena.getpInfo().isMostraE2() && cena.getpInfo().mostraE3 && cena.getpInfo().mostraE4 )
				cena.getAviso().reset("ENCONTRE O SR.KAIOH NA CIDADE!");
			else if(getFase4().capsulas.size()>0){
				cena.getAviso().setStr("PEGUE "+getFase4().capsulas.size()+" CAPSULAS!!");
			}else if(getFase4().capsulas.size()<=0){
				cena.getAviso().setStr("CAPTURE A ESFERA!");
			}
		}
		updatePersonagem();
	}
	
	public void updatePersonagem() {
		fase4.setGoku(getGoku());
	}
	
	public Fase1 getFase4() {
		return fase4;
	}
	public void setFase4(Fase1 fase4) {
		this.fase4 = fase4;
	}
	public Cenario getCena() {
		return cena;
	}
	public void setCena(Cenario cena) {
		this.cena = cena;
	}
	public ControlerDS getCds() {
		return cds;
	}
	public void setCds(ControlerDS cds) {
		this.cds = cds;
	}
	public void resetarPosicaoVilao(){
		
		for(int i= 0 ;i<getFase4().getMatrizPosVilao().length;i++){
			getViloes().get(i).setX(getFase4().getMatrizPosVilao()[i][0]);
			getViloes().get(i).setY(getFase4().getMatrizPosVilao()[i][1]);
			getViloes().get(i).novaDirecao();
			if(i%2==0){
				getViloes().get(i).podePerseguir = true;
			}
			getViloes().get(i).visivel = true;
			getViloes().get(i).setLife(0);
		}
	}

}
