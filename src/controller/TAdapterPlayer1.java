package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.Goku;
import view.CenarioGenerico;

public class TAdapterPlayer1 extends KeyAdapter{

	protected Goku goku;
	protected  ArrayList<CenarioGenerico> cenario;

	public TAdapterPlayer1(Goku g,ArrayList<CenarioGenerico> ge){
		goku = g;
		cenario = ge;
	}
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP){
			goku.direcaoAtiva("Cima");
			goku.setDy(-goku.getVelocidade());
			goku.setDx(0);
			goku.setDirecaoAntiga("Cima");
		}
		else
			if (e.getKeyCode()==KeyEvent.VK_DOWN){
				goku.direcaoAtiva("Baixo");
				goku.setDy(goku.getVelocidade());
				goku.setDx(0);
				goku.setDirecaoAntiga("Baixo");
			}else
				if (e.getKeyCode()==KeyEvent.VK_LEFT){
					goku.direcaoAtiva("Esquerda");
					goku.setDx(-goku.getVelocidade());
					goku.setDy(0);
					goku.setDirecaoAntiga("Esquerda");
				}else
					if (e.getKeyCode()==KeyEvent.VK_RIGHT){
						goku.direcaoAtiva("Direita");
						goku.setDx(goku.getVelocidade());
						goku.setDy(0);
						goku.setDirecaoAntiga("Direita");
					}
		if(e.getKeyCode()==KeyEvent.VK_SPACE){

			if(goku.atirando == false){
				if(goku.getDirecaoAntiga().equals("Cima")){
					goku.lancarPoder(goku.getX() - 5,goku.getY() - 20 ,100, goku.getVelocidade()*2,"Cima");
				}
				if(goku.getDirecaoAntiga().equals("Direita")){
					goku.lancarPoder(goku.getX() + goku.getSprite().sprites[goku.getSprite().aparencia].getWidth() - 10,goku.getY()+goku.getSprite().sprites[goku.getSprite().aparencia].getHeight()/2 - 18 ,100, goku.getVelocidade()*2,"Direita");
				}
				if(goku.getDirecaoAntiga().equals("Esquerda")){
					goku.lancarPoder(goku.getX() - 20,goku.getY()+goku.getSprite().sprites[goku.getSprite().aparencia].getHeight()/2 - 18 ,100, goku.getVelocidade()*2,"Esquerda");
				}
				if(goku.getDirecaoAntiga().equals("Baixo")){
					goku.lancarPoder(goku.getX() - 8,goku.getY()+goku.getSprite().sprites[goku.getSprite().aparencia].getHeight() - 10,100, goku.getVelocidade()*2,"Baixo");
				}
				goku.atirando = true;
				//entrou aki;
			}
		}	

		if(e.getKeyCode() == KeyEvent.VK_X){
			for(CenarioGenerico cena : cenario){
				if(cena.isMostraAviso()){
					cena.setPaint(true);
					cena.setMostraAviso(false);
					break;
				}
			}
		}
	}
	public void keyReleased(KeyEvent e) {
		
			if (e.getKeyCode() == KeyEvent.VK_UP){
				goku.setDy(0);
				goku.setCima(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN){
				goku.setDy(0);
				goku.setBaixo(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT ){
				goku.setDx(0);
				goku.setDireita(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT){
				goku.setDx(0);
				goku.setEsquerda(false);
			}
	}
}
