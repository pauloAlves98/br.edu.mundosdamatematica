package controller;

import java.util.ArrayList;

import model.Goku;
import model.Vilao;

public abstract class ControlerGenerico  implements ControlerInterface {
	private Goku goku;
	private ArrayList<Vilao>viloes;
	
	public void checarMovimento(int largura, int altura){
		
		for (int i=0;i<viloes.size();i++){//dava pra ser forEach!
			
			if(viloes.get(i).isVisivel() == false)
				continue;
			if(viloes.get(i).podePerseguir){
				viloes.get(i).perseguir(getGoku().getForma().x,getGoku().getForma().y,80);
				if(viloes.get(i).atirando)
					viloes.get(i).atirando = viloes.get(i).getPoder().atualizarSprite();
			}
			if(viloes.get(i).isPersegue() == false){

				if(viloes.get(i).checarLimites(largura,altura)){
					viloes.get(i).novaDirecao();//sorteia uma direcao pra o vilão!
					viloes.get(i).recuar();
				}

				if(viloes.get(i).isCima()==true){
					viloes.get(i).setDy(-viloes.get(i).getVelocidade());
					viloes.get(i).setDx(0);
				}
				else if(viloes.get(i).isBaixo()==true){
					viloes.get(i).setDy(viloes.get(i).getVelocidade());
					viloes.get(i).setDx(0);
				}
				else if(viloes.get(i).isDireita()==true){
					viloes.get(i).setDx(viloes.get(i).getVelocidade());
					viloes.get(i).setDy(0);
				}
				else if(viloes.get(i).isEsquerda()==true){
					viloes.get(i).setDx(-viloes.get(i).getVelocidade());
					viloes.get(i).setDy(0);
				}
			}

			viloes.get(i).mexer(largura,altura);
			viloes.get(i).getForma().setLocation(viloes.get(i).getX() + 5,viloes.get(i).getY() + 10);
		}
	}
	public Goku getGoku() {
		return goku;
	}
	public void setGoku(Goku goku) {
		this.goku = goku;
	}
	public ArrayList<Vilao> getViloes() {
		return viloes;
	}
	public void setViloes(ArrayList<Vilao> viloes) {
		this.viloes = viloes;
	}


}
