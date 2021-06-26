package model;

import java.awt.Rectangle;

public class Poder {
	protected Rectangle formaPoder;
	public Sprite sprite;
	protected int cont;
	protected int x, y,yInicial,xInicial,vel;
	protected boolean visivel;
	protected String direcao;
	int lim;

	public Poder(int x , int y,int lim, int vel, String direcao, String arq){
		cont = 0;
		this.vel = vel;
		this.direcao  = direcao;
		this.x = x;
		this.y = y;
		this.xInicial =x;
		this.yInicial = y;
		this.lim  = lim;
		formaPoder = new Rectangle(x,y+10,20,30);
		sprite = new Sprite(arq,0,4,4,x,y);
		visivel = true;
	}
	public boolean atualizarSprite(){

		if(direcao.equalsIgnoreCase("Cima")){
			y-=vel;
			if(cont==0)
				sprite.aparencia = 3;
			else
				sprite.aparencia +=4;//por que a sprite é de 4 e 4
		}
		if(direcao.equalsIgnoreCase("Baixo")){
			y+=vel;
			if(cont==0)
				sprite.aparencia = 0;
			else
				sprite.aparencia +=4;
		}
		if(direcao.equalsIgnoreCase("Direita")){

			x+=vel;
			if(cont==0)
				sprite.aparencia = 2;
			else
				sprite.aparencia +=4;
		}
		if(direcao.equalsIgnoreCase("Esquerda")){
			x-=vel;
			if(cont==0)
				sprite.aparencia = 1;
			else
				sprite.aparencia +=4;
		}

		if(Math.abs(yInicial-y)>lim || Math.abs(xInicial-x) >=lim){
			visivel = false;
			return  visivel;
		}
		formaPoder.setLocation(x, y + 10);
		if(cont>=3){
			cont=0;
		}
		else
			cont++;
		return visivel;
	}


	public Rectangle getFormaPoder() {
		return formaPoder;
	}
	public void setFormaPoder(Rectangle formaPoder) {
		this.formaPoder = formaPoder;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
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
	public int getyInicial() {
		return yInicial;
	}
	public void setyInicial(int yInicial) {
		this.yInicial = yInicial;
	}
	public int getxInicial() {
		return xInicial;
	}
	public void setxInicial(int xInicial) {
		this.xInicial = xInicial;
	}
	public int getVel() {
		return vel;
	}
	public void setVel(int vel) {
		this.vel = vel;
	}
	public boolean isVisivel() {
		return visivel;
	}
	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
	public String getDirecao() {
		return direcao;
	}
	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}
	public int getLim() {
		return lim;
	}
	public void setLim(int lim) {
		this.lim = lim;
	}

}

