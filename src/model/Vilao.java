package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
//1-fazer inimigo atirar- feito
//2 - tratar a life do inimigo
public class Vilao extends Personagem {//vilao frezer 
	public  boolean visivel = true;//apenas ocultar as nao destruir 
	private boolean persegue;
	public boolean podePerseguir;//indica que o vilao esta apto a perseguir

	public Vilao(String file, int aparencia, int columns, int rows, int posX, int posY) {
		super(file, aparencia, columns, rows, posX, posY);
		super.life = 0;
		super.setCima(true);
		super.setDireita(true);
		super.setY(230);
		super.setX(312);
		super.setForma(new Rectangle(getX(),getY(),this.getSprite().sprites[aparencia].getWidth()-15,this.getSprite().sprites[super.getSprite().aparencia].getHeight()));
		super.setVelocidade(2);//velocidade aqui!
		super.poder = new Poder(getX(),getY(),100,2," ","Heroi//tiro.png");
		//barraLife = new Rectangle(getX(),getY() - 10,this.getSprite().sprites[aparencia].getWidth(),10);
		Personagem.personagens.add(this);//adiciona pra controlar a aparencia
	}
	public void novaDirecao(){
		
		Random random = new Random();
		int n = random.nextInt(4);

		if(n==0)
			direcaoAtiva("cima");
		else if(n==1)
			direcaoAtiva("Esquerda");
		else if(n==2)
			direcaoAtiva("Direita");
		else if(n==3)
			direcaoAtiva("Baixo");
	}

	public void mexer(int largura,int altura) {
		//largura tela / altura tela
		if(visivel){
			atualizacaoPosicao(largura,altura);
			super.getForma().setLocation(this.getX() + 10,this.getY() + 10);//nao meche igual por isto
		}
	}

	public void parado() {
		setDx(0);
		setDy(0);
		novaDirecao();
	}  
	public void perseguir(int xp, int yp, int lim){

		//OBS: Math.abs(this.x - xp) verifica se o personagem esta dentro da area de perseguição
		//this.x - xp >=0 garante que o vilao esta a direita do personagem
		//Math.abs(this.y -yp)<=5 condição pra fazer o vilao andar na msma linha do personagem
		
		if(podePerseguir==false || visivel == false)//ai este vilao nao pode  perseguir
			return;

		if(Math.abs(this.getForma().x - xp) <=lim && this.getForma().x - xp >=0 && Math.abs(this.getForma().y + 10 -yp)<=10){
			super.setDy(0);
			super.setDx(-getVelocidade());
			direcaoAtiva("Esquerda");
			persegue  = true;

			if(atirando == false){//atirando bloqueia o tiro ate ele dar false
				this.lancarPoder(this.getX() - this.getSprite().sprites[this.getSprite().aparencia].getWidth() + 10,this.getY()+this.getSprite().sprites[this.getSprite().aparencia].getHeight()/2 - 15 ,100, 2*this.getVelocidade(),"Esquerda");
				atirando = true;
			}	
		}
		else 								//this.x - xp <=0 essa condiçao garante que o vilao esta a esquerda do personagem
			if(Math.abs(this.getForma().x  - xp)<=lim && this.getForma().x - xp <=0 && Math.abs(this.getForma().y + 10 - yp)<=10){
				super.setDy(0);
				super.setDx(getVelocidade());
				direcaoAtiva("Direita");

				if(atirando == false){
					this.lancarPoder(this.getX() + this.getSprite().sprites[this.getSprite().aparencia].getWidth() - 20,this.getY() + this.getSprite().sprites[this.getSprite().aparencia].getHeight()/2 -15 ,100, 2*this.getVelocidade(),"Direita");
					atirando = true;
				}
				persegue  = true;
			}
			else								//this.y - yp>=0 garante que o vilao esta abaixo do personagem
				if(Math.abs(this.getForma().x - xp)<=lim && this.getForma().y  - yp>=0){
					super.setDy(-getVelocidade());
					super.setDx(0) ;
					direcaoAtiva("Cima");
					persegue  = true;
				}
				else								//this.y - yp>=0 garante que o vilao esta a cima do personagem
					if(Math.abs(this.getForma().x - xp)<=lim && this.getForma().y - yp<=0){
						super.setDy(getVelocidade());
						super.setDx(0) ;
						direcaoAtiva("Baixo");
						persegue  = true;
					}
					else
						persegue  = false;
	}
	public boolean checarLimites(int largura , int altura){

		if(getX()<=1){
			return true;
		}
		else if(getX()>largura - this.getSprite().sprites[this.getSprite().aparencia].getWidth()-1){
			return true;
		}
		if(getY()>=altura - this.getSprite().sprites[this.getSprite().aparencia].getHeight()-1){
			return true;
		}
		else if(getY()<=1){
			return true;
		}

		return false;
	}
	public static void plotarBarraLife(Graphics2D g,Vilao v){
		g.setColor(Color.green);
		g.drawRect(v.getX() - 1,v.getY() - 11,v.getSprite().sprites[v.getSprite().aparencia].getWidth() + 1,11);//borda
		g.setColor(Color.white);
		g.fillRect(v.getX(),v.getY()-10,v.getSprite().sprites[v.getSprite().aparencia].getWidth(),10);
		g.setColor(Color.BLACK);
		g.fillRect(v.getX(),v.getY()-10,v.getSprite().sprites[v.getSprite().aparencia].getWidth() - v.life,10);
	}
	public static void Render(Graphics g,ArrayList<Vilao>ps){

		for(int i = 0;i<ps.size();i++){
			
			Vilao p = ps.get(i);
			if(p.visivel){//so pinta ele se ele for visivel
				
				//pinta vilao
				g.drawImage(p.getSprite().sprites[p.getSprite().aparencia],p.getX(), p.getY(),p.getSprite().sprites[p.getSprite().aparencia].getWidth(),40,null);//36

				if(p.podePerseguir==true){//so os que perseguem podem atirar
					plotarBarraLife((Graphics2D)g,p);
					if(p.atirando){//se atirando printa o tiro
						g.drawImage(p.poder.sprite.sprites[p.poder.sprite.aparencia],p.poder.x,p.poder.y,40,40,null);
						//g.drawRect(p.poder.formaPoder.x,p.poder.formaPoder.y,p.poder.formaPoder.width,p.poder.formaPoder.height);
					}
				}
				//((Graphics2D) g).draw(ps.get(i).getForma());//tirar

			}
		}
	}
	public  void vilaoColisaoCenario(ArrayList<ArrayList<Rectangle>>formaTile){
		
		for(int i = 0;i<formaTile.size();i++){
			//verifica se a forma esta no intervalo descrito e indefica as colisoes somente da linha em que o personagem esta
			if(visivel == false)
				return;
			if(this.getForma().y + 10 <= formaTile.get(i).get(0).getY()+ 32 && getForma().y + 10>= formaTile.get(i).get(0).getY())
				if(this.colisao(formaTile.get(i))){
					if(this.persegue==false)
						this.novaDirecao();
						this.atirando = false;
					this.recuar();
					break;
				}
			
		}
	}
	public boolean isPersegue() {
		return persegue;
	}
	public void setPersegue(boolean persegue) {
		this.persegue = persegue;
	}
	public boolean isVisivel() {
		return visivel;
	}
	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
	public boolean isPodePerseguir() {
		return podePerseguir;
	}
	public void setPodePerseguir(boolean podePerseguir) {
		this.podePerseguir = podePerseguir;
	}
	
}
