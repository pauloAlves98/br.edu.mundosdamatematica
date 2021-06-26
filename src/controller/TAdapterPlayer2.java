package controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.Goku;
import view.CenarioGenerico;

public class TAdapterPlayer2 extends TAdapterPlayer1{

	public TAdapterPlayer2(Goku g, ArrayList<CenarioGenerico> ge) {
		super(g, ge);
	}
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_W){
			goku.direcaoAtiva("Cima");
			goku.setDy(-goku.getVelocidade());
			goku.setDx(0);
			goku.setDirecaoAntiga("Cima");
		}
		else
			if (e.getKeyCode()==KeyEvent.VK_S){
				goku.direcaoAtiva("Baixo");
				goku.setDy(goku.getVelocidade());
				goku.setDx(0);
				goku.setDirecaoAntiga("Baixo");
			}else
				if (e.getKeyCode()==KeyEvent.VK_A){
					goku.direcaoAtiva("Esquerda");
					goku.setDx(-goku.getVelocidade());
					goku.setDy(0);
					goku.setDirecaoAntiga("Esquerda");
				}else
					if (e.getKeyCode()==KeyEvent.VK_D){
						goku.direcaoAtiva("Direita");
						goku.setDx(goku.getVelocidade());
						goku.setDy(0);
						goku.setDirecaoAntiga("Direita");
					}
		if(e.getKeyCode()==KeyEvent.VK_F){

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
		
		if (e.getKeyCode() == KeyEvent.VK_W){
			goku.setDy(0);
			goku.setCima(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_S){
			goku.setDy(0);
			goku.setBaixo(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_D ){
			goku.setDx(0);
			goku.setDireita(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_A){
			goku.setDx(0);
			goku.setEsquerda(false);
		}
	}
}
