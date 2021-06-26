package model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Goku extends Personagem{
	private Rectangle intersecaoComVilao;
	private  String direcaoAntiga = " Direita";
	private int dano;//porcentagem

	public Goku(String string, int aparencia, int columns, int rows, int posX, int posY) {
		super(string, aparencia, columns, rows, posX, posY);
		life = 0;
		dano = 20;//dano em porcentagem
		setY(230);//padrao
		setX(312);//padrao
		setForma(new Rectangle(getX() ,getY() + this.getSprite().sprites[this.getSprite().aparencia].getHeight()/2,this.getSprite().sprites[this.getSprite().aparencia].getWidth()/2,2));
		intersecaoComVilao = new Rectangle(getX() ,getY() + this.getSprite().sprites[this.getSprite().aparencia].getHeight()/2,this.getSprite().sprites[this.getSprite().aparencia].getWidth()/2,36);
		poder = new Poder(getX(),getY(),100,4,"Direita","Heroi//poder01.png");
		setVelocidade(4);//comeca com 4
	}
	public void mexer(int largura,int altura){
		super.atualizacaoPosicao(largura,altura);
		//se diferencia de vilao aki
		getForma().setLocation(getX() + this.getSprite().sprites[this.getSprite().aparencia].getWidth()/4 ,getY()  + this.getSprite().sprites[this.getSprite().aparencia].getHeight() - 6);
		intersecaoComVilao.setLocation(getX() + this.getSprite().sprites[this.getSprite().aparencia].getWidth()/4 ,getY());
		if(atirando){
			//System.out.println("Atirando!!!!!!!!!!!!!!!!!");
			atirando = poder.atualizarSprite();
		}
	}

	public void stop(){
		super.direcaoAtiva(" ");
		super.setDx(0);
		super.setDy(0);
	}
	public void colisaoVilao(ArrayList<Vilao> vilao) {

		for (Vilao v : vilao) {
			if(v.isVisivel()==false)
				continue;
			if(this.intersecaoComVilao.intersects(v.getForma())){
				life+= 1 * 360/100;//1 % de dano
				this.recuar(); 
				v.novaDirecao();
				v.recuar();
			}
			if(v.podePerseguir){
				if(this.atirando){//olha se o goku esta atirando
					if(this.poder.formaPoder.intersects(v.getForma())){
						this.atirando = false;//para de atirar
						v.life+=(int)(v.getSprite().sprites[v.getSprite().aparencia].getWidth()*dano/100);//computa o dano
						if(v.getSprite().sprites[v.getSprite().aparencia].getWidth() - v.life <=0){
							//System.out.println("Life------------------------"+v.life+" +"+v.getSprite().sprites[v.getSprite().aparencia].getWidth());
							v.atirando = false;
							v.setVisivel(false);
							v.setPersegue(false);
						}
					}
				}
				if(v.atirando){
					if(v.poder.formaPoder.intersects(this.intersecaoComVilao)){
						v.atirando = false;
						this.setLife(this.getLife() + 1 * 360/100);//Dano 1%
					}
				}
			}
		}
	}
	public void gokuRender(Graphics2D g2d){
		//pinta o goku
		g2d.drawImage(this.getSprite().sprites[this.getSprite().aparencia], this.getX(), this.getY(),	this.getSprite().sprites[this.getSprite().aparencia].getWidth(),38,null);
		//pinta o tiro
		if(atirando){
			g2d.drawImage(poder.sprite.sprites[poder.sprite.aparencia],poder.x,poder.y,40,40,null);
			//g2d.draw(poder.formaPoder.getBounds2D());
		}

	}
	public Rectangle getIntersecaoComVilao() {
		return intersecaoComVilao;
	}
	public void setIntersecaoComVilao(Rectangle intersecaoComVilao) {
		this.intersecaoComVilao = intersecaoComVilao;
	}
	public String getDirecaoAntiga() {
		return direcaoAntiga;
	}
	public void setDirecaoAntiga(String direcaoAntiga) {
		this.direcaoAntiga = direcaoAntiga;
	}
	public int getDano() {
		return dano;
	}
	public void setDano(int dano) {
		this.dano = dano;
	}

}
