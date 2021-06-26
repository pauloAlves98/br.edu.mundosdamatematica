package model;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * @author P
 *
 */
public abstract class Personagem{
	private int dx;
	private int dy;
	private int x;
	private int y;
	private int left;
	private int right;
	private int up;
	private int down;
	private int velocidade;
	private boolean cima,baixo,direita,esquerda;
	private Rectangle forma;
	private Sprite sprite;
	public boolean atirando = false;
	 //ArrayList<Poder>poderes = new ArrayList<Poder>();
	protected  Poder poder;
	protected  int life;
	public static ArrayList<Personagem>personagens = new  ArrayList<Personagem>();
	
	public Personagem(String string, int aparencia, int columns, int rows, int posX, int posY){
		sprite = new Sprite(string, aparencia, columns, rows, posX, posY);
	}
	public void direcaoAtiva(String direcao){//Diz direcao da sprite
		cima = false;
		baixo = false;
		direita = false;
		esquerda = false;
		if(direcao.equalsIgnoreCase("Cima"))
			cima = true;
		else if(direcao.equalsIgnoreCase("Baixo"))
			baixo = true;
		else if(direcao.equalsIgnoreCase("Direita"))
			direita = true;
		else if(direcao.equalsIgnoreCase("Esquerda"))
			esquerda = true;

	}
	public abstract void mexer(int largura, int altura);
	public void recuar(){//volta o personagem pra sua posição anterior
		this.x+= -dx;
		this.y+= -dy;
	}
	public boolean colisao(ArrayList<Rectangle> tmp) {
		for (Rectangle rectangle : tmp) {
			if(this.forma.intersects(rectangle)){
				//System.out.println("colidiu");
				return true;
			}	
		}
		return false;
	}
	public boolean colisao(Rectangle tmp) {//uso de sobrecarga
			if(this.forma.intersects(tmp)){
				//System.out.println("colidiu");
				return true;
			}
		return false;
	}
	public void atualizacaoPosicao(int largura, int altura){
		
		setX(getX() + getDx());
		setY(getY() + getDy());	
		
		if(getX()<=0)
			setX(0);
		if(getX()>largura - this.getSprite().sprites[this.getSprite().aparencia].getWidth())
			setX(largura - this.getSprite().sprites[this.getSprite().aparencia].getWidth());
		if(getY()>=altura - this.getSprite().sprites[this.getSprite().aparencia].getHeight())
			setY(altura - this.getSprite().sprites[this.getSprite().aparencia].getHeight());
		if(getY()<=0)
			setY(0);
	}
	public void lancarPoder(int x ,int y,int lim,int vel, String direcao){
		//poderes.add(new Poder(x,y,lim,vel,direcao));
		poder.x  = x;
		poder.y = y;
		poder.direcao = direcao;
		poder.xInicial = x;
		poder.yInicial = y;
		poder.lim = lim;
		poder.vel = vel;
		poder.visivel = true;
		poder.cont = 0;
		//poder = new Poder(x,y,lim,vel,direcao);
	}
	public int getDx() {
		return dx;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public int getDy() {
		return dy;
	}
	public void setDy(int dy) {
		this.dy = dy;
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
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public int getUp() {
		return up;
	}
	public void setUp(int up) {
		this.up = up;
	}
	public int getDown() {
		return down;
	}
	public void setDown(int down) {
		this.down = down;
	}
	public int getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}
	public boolean isCima() {
		return cima;
	}
	public void setCima(boolean cima) {
		this.cima = cima;
	}
	public boolean isBaixo() {
		return baixo;
	}
	public void setBaixo(boolean baixo) {
		this.baixo = baixo;
	}
	public boolean isDireita() {
		return direita;
	}
	public void setDireita(boolean direita) {
		this.direita = direita;
	}
	public boolean isEsquerda() {
		return esquerda;
	}
	public void setEsquerda(boolean esquerda) {
		this.esquerda = esquerda;
	}
	public Rectangle getForma() {
		return forma;
	}
	public void setForma(Rectangle forma) {
		this.forma = forma;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public boolean isAtirando() {
		return atirando;
	}
	public void setAtirando(boolean atirando) {
		this.atirando = atirando;
	}
	public Poder getPoder() {
		return poder;
	}
	public void setPoder(Poder poder) {
		this.poder = poder;
	}
	
	
}
