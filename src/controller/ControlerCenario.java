package controller;

import java.util.ArrayList;

import model.Goku;
import model.Personagem;
import model.Som;
import model.Vilao;
import view.Cenario;
import view.Fase1;
import view.Fase2;
import view.TelaGanhou;
import view.TelaJogo;

public class ControlerCenario extends  ControlerGenerico{
	
	private Cenario cenario;
	private Fase1 fase1,fase3,fase4;
	private Fase2 fase2;
	private TelaGanhou ganhou;
	private TelaJogo tela;
	
	public ControlerCenario(Cenario cena,Fase1 f1,Fase2 f2,Fase1 f3,Fase1 f4, TelaGanhou g,TelaJogo t,String end){
		this.setCenario(cena);
		this.fase1 = f1;
		this.fase2 = f2;
		this.fase3 = f3;
		this.fase4 = f4;
		this.tela = t;
		this.ganhou = g;
		this.setGoku(new Goku(end,0,3,4,0,0));
		setViloes(getCenario().getViloes());
		//this.getCenario().addKeyListener(new TAdapter(getGoku(),getCenario()));
		Personagem.personagens.add(getGoku());//adicona esse goku pra controle de aparencia
	}
	public void moverCena(int x, int y){
		if(x> 400/2)
			if(x<(getCenario().getLarguraMapa() - 400/2))//pega a variação de movimento
				getCenario().setxCena(-(x-(400/2)));
		if(y>300/2)
			if(y < (getCenario().getAlturaMapa() - (300/2)))//pega a variação de movimento 
				getCenario().setyCena(-(y - 300/2));
		getCenario().setLocation(getCenario().getxCena(), getCenario().getyCena());
	}
	public void checarColisao(int largura ,int altura){
		
		checarMovimento(largura,altura);
		getGoku().colisaoVilao(getViloes());
		
		for(int i = 0;i<getCenario().getCamada02casa().formaTile.size();i++){
			//verifica se a forma esta no intervalo descrito e indefica as colisoes somente da linha em que o personagem esta
			//evitando iteraçoes desnecessarias
			if(getGoku().getForma().y < getCenario().getCamada02casa().formaTile.get(i).get(0).getY() + 32 && getGoku().getForma().y >= getCenario().getCamada02casa().formaTile.get(i).get(0).getY()){
				//				System.out.println("Tamanho  na linha:"+cenario.getCamada02casa().formaTile.get(i).size());
				if(getGoku().colisao(getCenario().getCamada02casa().formaTile.get(i))){
					getGoku().recuar();
					break;
				}
			}
		}
		for(Vilao v:getViloes()){//
			v.vilaoColisaoCenario(getCenario().getCamada02casa().formaTile);
		}
		if(getGoku().colisao(getCenario().getFormaKame())){
			if(getCenario().getpInfo().isMostraE1() && getCenario().getpInfo().isMostraE2() && getCenario().getpInfo().mostraE3 
			&& getCenario().getpInfo().mostraE4){
				//acionar tela final de jogo!//recebe tela ganhou //tela JFrame
				getCenario().getpInfo().setRodando(false);
				getCenario().somFundo.somEmLoop(false);
			
				tela.dispose();
			
				
				ganhou.setVisible(true);
				ganhou.getSom().somEmLoop(true);
				new ControlerGanhou(ganhou,getCenario().getpInfo().getTempo());
			}else{
				getGoku().recuar();
				getCenario().setPaint(false);
				getCenario().setMostraAviso(true);
				getCenario().getAviso().setStr("CONSIGA AS ESFERAS E VENHA AQUI! PRESS(X)");
			}	
		}
		if(getGoku().colisao(getCenario().getFormaTransporteSoma())){
			fase1.setVisible(true);
			fase1.setPaint(true);
			getCenario().setPaint(false);
			getCenario().setVisible(false);
			getGoku().setX(309);
			getGoku().setY(300);
			getGoku().stop();
			resetarPosicaoVilao();
		}
		if(getGoku().colisao(getCenario().getFormaTransporteSubtracao()) && getCenario().getpInfo().isMostraE1() ){
			getCenario().setPaint(false);
			fase2.setPaint(true);
			fase2.setVisible(true);
			getCenario().setVisible(false);
			getGoku().setX(60);
			getGoku().setY(60);
			fase2.setxCena(0);
			fase2.setyCena(0);
			getGoku().stop();
			resetarPosicaoVilao();
		}
		if(getGoku().colisao(getCenario().getFormaTransporteMult()) && getCenario().getpInfo().isMostraE2() ){
			
			fase3.setVisible(true);
			fase3.setPaint(true);
			getCenario().setPaint(false);
			getCenario().setVisible(false);
			getGoku().setX(309);
			getGoku().setY(300);
			getGoku().stop();
			resetarPosicaoVilao();
		}
	if(getGoku().colisao(getCenario().getFormaTransporteDiv()) && getCenario().getpInfo().mostraE3 ){
			
			fase4.setVisible(true);
			fase4.setPaint(true);
			getCenario().setPaint(false);
			getCenario().setVisible(false);
			getGoku().setX(309);
			getGoku().setY(300);
			getGoku().stop();
			resetarPosicaoVilao();
		}
		//contrler mensagem
		if(getCenario().isMostraAviso() == false){
			if(getCenario().getpInfo().isMostraE1() && getCenario().getpInfo().isMostraE2() && getCenario().getpInfo().mostraE3 &&  getCenario().getpInfo().mostraE4)
				getCenario().getAviso().reset("VA ATÉ O SR.KAIOH!");
			else if(getCenario().getpInfo().isMostraE2() == false && getCenario().getpInfo().isMostraE1())
				getCenario().getAviso().reset("TELEPORTE-SE PARA PLANETA SUB!");
			else if(getCenario().getpInfo().isMostraE1() == false)
				getCenario().getAviso().reset("TELEPORTE-SE PARA PLANETA SOMA!");
			else if(getCenario().getpInfo().mostraE3 == false && getCenario().getpInfo().isMostraE2() == true && getCenario().getpInfo().isMostraE1())
				getCenario().getAviso().reset("TELEPORTE-SE PARA PLANETA MULTIPLICAÇÃO!");
			else if(getCenario().getpInfo().mostraE4 == false && getCenario().getpInfo().isMostraE2() == true && getCenario().getpInfo().isMostraE1() && getCenario().getpInfo().mostraE3 )
				getCenario().getAviso().reset("TELEPORTE-SE PARA PLANETA DIVISÃO!");
		}
		updatePersonagem();
	}
	
	public void updatePersonagem(){
		getCenario().setGoku(getGoku());
		//		getCenario().getGoku().setX(getGoku().getX());
		//		getCenario().getGoku().setY(getGoku().getY());
		////		cenario.goku.direita =  goku.direita;
		////		cenario.goku.esquerda = goku.esquerda;
		////		cenario.goku.baixo = goku.baixo;
		////		cenario.goku.cima = goku.cima;
		//		getCenario().getGoku().setLife(getGoku().getLife());
		//		getCenario().getGoku().getSprite().sprites = getGoku().getSprite().sprites;
		//		getCenario().getGoku().getSprite().aparencia = getGoku().getSprite().aparencia;
		//		getCenario().getGoku().poder = getGoku().poder;
		//		getGoku().atirando = getGoku().atirando = getGoku().atirando;
	}
	public Cenario getCenario() {
		return cenario;
	}
	public void setCenario(Cenario cenario) {
		this.cenario = cenario;
	}
	public void resetarPosicaoVilao(){
		for(int i= 0 ;i<cenario.getMatrizPosVilao().length;i++){
			getViloes().get(i).setX(cenario.getMatrizPosVilao()[i][0]);
			getViloes().get(i).setY(cenario.getMatrizPosVilao()[i][1]);
			getViloes().get(i).novaDirecao();
			if(i%2==0){
				getViloes().get(i).podePerseguir = true;
			}
			getViloes().get(i).visivel = true;
			getViloes().get(i).setLife(0);
		}
	}
}
