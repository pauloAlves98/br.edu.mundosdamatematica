package controller;

import model.Capsula;
import model.Goku;
import model.Vilao;
import view.Cenario;
import view.Fase1;

public class ControlerFase3 extends ControlerGenerico {
	private Fase1 fase3;
	private Cenario cena;
	private ControlerDS cds;
	
	public ControlerFase3(Goku perso,Fase1 fase, Cenario cena ,ControlerDS cds){
		this.setGoku(perso);
		this.fase3= fase;
		setViloes(fase.getViloes());
		this.cena  = cena;
		this.cds = cds;
		//this.getFase3().addKeyListener(new TAdapter(getGoku(),getFase3()));
	}
	public void moverCena(int x, int y){
		if(x> 400/2)
			if(x<(getFase3().getLarguraMapa() - 400/2))//pega a variação de movimento
				getFase3().setxCena(-(x-(400/2)));
		if(y>300/2)
			if(y < (getFase3().getAlturaMapa() - (300/2)))//pega a variação de movimento 
				getFase3().setyCena(-(y - 300/2));
		getFase3().setLocation(getFase3().getxCena(), getFase3().getyCena());
	}
	public void checarColisao(int largura, int altura){
		checarMovimento(largura,altura);
		getGoku().colisaoVilao(getViloes());
		
		for(int i = 0;i<getFase3().getCamada02().formaTile.size();i++){
			//verifica se a forma esta no intervalo descrito e indefica as colisoes somente da linha em que o personagem esta
			if(getGoku().getForma().y <= getFase3().getCamada02().formaTile.get(i).get(0).getY()+ 32 && getGoku().getForma().y >= getFase3().getCamada02().formaTile.get(i).get(0).getY())
				if(getGoku().colisao(getFase3().getCamada02().formaTile.get(i))){
					getGoku().recuar();
					break;
				}
			}
		for(Vilao v:getViloes()){//
			v.vilaoColisaoCenario(getFase3().getCamada02().formaTile);
		}
		
		if(Capsula.colisaoCapsula(getGoku(),getFase3().capsulas)){
			getFase3().setVisible(false);
			getGoku().stop();
			getFase3().setPaint(false);
			cds.destrava = 0;
			getFase3().getPd().setVisible(true);
		}
		if(getFase3().getFormaBarreira()!=null){
			if(getGoku().colisao(getFase3().getFormaBarreira())){
				if(getFase3().capsulas.size()<=0){//é por que conseguiu as capsulas
					getFase3().setFormaBarreira(null);
					getFase3().setBarreira(null);
					//destrutor generico nao funfou
				}else {
					cena.getAviso().setStr("PEGUE MAIS CAPSULAS!\nPRESS(x)");
					getGoku().recuar();//mostraAvisoBarreira
					getFase3().setMostraAviso(true);
					getFase3().setPaint(false);
				}
			}
		}
		if(getFase3().getCapsula()!=null){
			if(getGoku().colisao(getFase3().getCapsula().getForma())){
				getGoku().setLife(getGoku().getLife()-10);//ganha life
				getFase3().setCapsula(null);
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
		if(getFase3().getFormaEsfera()!=null){
			if(getGoku().colisao(getFase3().getFormaEsfera()) ){
				getFase3().setPaint(false);
				getFase3().setVisible(false);
				getFase3().getPd().setVisible(true);
				getFase3().setFormaEsfera(null);
				getFase3().setEsfera(null);
				getGoku().stop();
				cds.destrava = 0;
				System.gc();
			}
		}	
		if(getGoku().colisao(getFase3().getFormaNave()) ){
			getGoku().setX(225);
			getGoku().setY(291);
			getGoku().stop();
			cena.setVisible(true);
			getFase3().setPaint(false);
			cena.setPaint(true);
			//cena.xCena = 0;
			getFase3().setVisible(false);
			resetarPosicaoVilao();
		}
		//controler mensagem
		
		//tratar isso
		if(getFase3().isMostraAviso() == false){
			if(cena.getpInfo().isMostraE1() && cena.getpInfo().isMostraE2() && cena.getpInfo().mostraE3 && cena.getpInfo().mostraE4 )
				cena.getAviso().reset("ENCONTRE O SR.KAIOH NA CIDADE!");
			else if(cena.getpInfo().mostraE4 == false && cena.getpInfo().mostraE3)
				cena.getAviso().reset("RETORNE A CIDADE!");
			else if(getFase3().capsulas.size()>0){
				cena.getAviso().setStr("PEGUE "+getFase3().capsulas.size()+" CAPSULAS!!");
			}else if(getFase3().capsulas.size()<=0){
				cena.getAviso().setStr("CAPTURE A ESFERA!");
			}
		}
		updatePersonagem();
	}
	public void updatePersonagem() {
		fase3.setGoku(getGoku());
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
	public Fase1 getFase3() {
		return fase3;
	}
	public void setFase3(Fase1 fase3) {
		this.fase3 = fase3;
	}
	public void resetarPosicaoVilao(){
		for(int i= 0 ;i<getFase3().getMatrizPosVilao().length;i++){
			getViloes().get(i).setX(getFase3().getMatrizPosVilao()[i][0]);
			getViloes().get(i).setY(getFase3().getMatrizPosVilao()[i][1]);
			getViloes().get(i).novaDirecao();
			if(i%2==0){
				getViloes().get(i).podePerseguir = true;
			}
			getViloes().get(i).visivel = true;
			getViloes().get(i).setLife(0);
		}
	}
	
}
