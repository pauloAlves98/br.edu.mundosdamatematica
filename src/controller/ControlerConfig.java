package controller;

import java.io.File;
import java.io.IOException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Goku;
import model.Personagem;
import view.CenarioGenerico;
import view.TelaConfiguracao;

public class ControlerConfig extends Thread {
	private Goku perso;
	private TelaConfiguracao telaC;
	
	public ControlerConfig(TelaConfiguracao telaC){
		perso = new Goku("Heroi//GokuSSJ3.png",0,3,4,0,0);
		perso.setDx(4);
		perso.setDireita(true);
		Personagem.personagens.add(perso);
		this.telaC = telaC;
		this.telaC.getSlider().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if(telaC.getSlider().getValue()<=0)
					CenarioGenerico.FPS = 1;
				else
					CenarioGenerico.FPS = telaC.getSlider().getValue();
			}
		});
	}
	public void run(){
		while( telaC.isRodando()==true ){
			try {
				atualizar();
				Thread.sleep(1000/CenarioGenerico.FPS);
			} catch (InterruptedException e) {
			}
		}	
	}
	public void atualizar(){
		
		if(perso.getX() >=500 - 50){
			perso.setDx(-4);
			perso.direcaoAtiva("Esquerda");
		}
		else if(perso.getX() <=0){
			perso.direcaoAtiva("Direita");
			perso.setDx(4);
		}
		perso.setX(perso.getX() + perso.getDx());
		
		telaC.getpConfig().getPersonagem().setDireita(perso.isDireita());
		telaC.getpConfig().getPersonagem().setEsquerda(perso.isEsquerda());
		telaC.getpConfig().getPersonagem().setX(perso.getX());
		telaC.getpConfig().getPersonagem().getSprite().aparencia = perso.getSprite().aparencia;
		telaC.getpConfig().repaint();
	}
	public static void parar(){
		Thread.interrupted();//nao parou
	}
}
