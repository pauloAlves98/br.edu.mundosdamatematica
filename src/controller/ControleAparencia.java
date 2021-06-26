package controller;

import java.util.ArrayList;

import model.Goku;
import model.Personagem;
import view.CenarioGenerico;

public class ControleAparencia extends Thread {
	public ArrayList<Personagem> personagens;
    static boolean rodando;
	public ControleAparencia(ArrayList<Personagem>personagens){
		this.personagens = personagens;
		rodando = true;
	}
	public void run(){
		try {
			
			while (rodando) {
				if(personagens.size()>0)
					
				for(Personagem personagem:personagens ){
					if(personagem.isCima()){
						switch (personagem.getUp()) {
						case 0:
							personagem.getSprite().aparencia=0;
							break;
						case 1:
							personagem.getSprite().aparencia=4;
							break;
						case 2:
							personagem.getSprite().aparencia=8;
							break;
						}
						if (personagem.getUp()==2) personagem.setUp(0);
						else personagem.setUp(personagem.getUp() + 1);
					}
					else if(personagem.isBaixo()){
						switch (personagem.getDown()) {
						case 0:
							personagem.getSprite().aparencia=2;
							break;
						case 1:
							personagem.getSprite().aparencia=6;
							break;
						case 2:
							personagem.getSprite().aparencia=10;
							break;
						}
						if (personagem.getDown()==2) personagem.setDown(0);
						else personagem.setDown(personagem.getDown() + 1);
					}
					else if(personagem.isEsquerda()){
						switch (personagem.getLeft()) {
						case 0:
							personagem.getSprite().aparencia=3;
							break;
						case 1:
							personagem.getSprite().aparencia=7;
							break;
						case 2:
							personagem.getSprite().aparencia=11;
							break;
						}

						if (personagem.getLeft()==2) personagem.setLeft(0);
						else personagem.setLeft(personagem.getLeft() + 1);
					}
					else if(personagem.isDireita()){
						switch (personagem.getRight()) {
						case 0:
							personagem.getSprite().aparencia=1;
							break;
						case 1:
							personagem.getSprite().aparencia=5;
							break;
						case 2:
							personagem.getSprite().aparencia=9;
							break;
						}
						if (personagem.getRight()==2) personagem.setRight(0);
						else personagem.setRight(personagem.getRight() + 1);	
					}
				}
				Thread.sleep((1000/CenarioGenerico.FPS) * 2);// troca a 0,375
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
