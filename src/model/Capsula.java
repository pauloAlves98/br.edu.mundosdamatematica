package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Capsula {
	
	private Image capsula;
	private Rectangle forma;
	private int x,y;
	
	public Capsula(int x,int y){
	   capsula = CarregadorDeImagem.carregar("Outros//capsulaInfo.png");
       this.x = x;
       this.y = y;
       this.forma = new Rectangle(x,y,20,30);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Rectangle getForma() {
		return forma;
	}
	public Image getCapsula() {
		return capsula;
	}
	public static boolean colisaoCapsula(Personagem p,ArrayList<Capsula>capsulas){
		for(Capsula ca:capsulas){
			if(p.colisao(ca.getForma())){
				capsulas.remove(ca);
				return true;
			}
		}
		return false;
	}
	
}
