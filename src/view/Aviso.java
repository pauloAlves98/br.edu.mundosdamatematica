package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import model.CarregadorDeImagem;

public class Aviso extends JPanel{

	private Image capsula,padrao;
	private String str;
	private int xImage = 3,yImage = 4, xString = 100,yString = 50,x = 0,y = 0;
	private Font font;
	
	public  Aviso (String str){
		
		setPreferredSize(new Dimension(400,100));
		this.str = str;
		this.padrao = CarregadorDeImagem.carregar("Outros//Sr.png");
		this.capsula = padrao;
		this.font = new Font("Agency FB",Font.BOLD,19);
		setVisible(true);
	}
	public void paint(Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.setFont(font);
		g2d.fillRect(x, y, 400, 95);
		g2d.setColor(Color.blue);
		g2d.drawRect(x+1,y + 1, 397, 89);
		g2d.setColor(Color.white);
		g2d.drawImage(capsula,xImage,yImage,60,80,null);
		g2d.drawString(str,xString,yString);
	}
	public void reset(String str){//retorna a imagem do sr 
		this.str = str;
		setCapsula(padrao);
		repaint();
	}
	public void setCapsula(Image capsula) {
		this.capsula = capsula;
		repaint();
	}
	public void setStr(String str){
		this.str = str;
		repaint();
	}
	public void setxImage(int xImage) {
		this.xImage = xImage;
	}
	public void setyImage(int yImage) {
		this.yImage = yImage;
	}
	public void setxString(int xString) {
		this.xString = xString;
	}
	public void setyString(int yString) {
		this.yString = yString;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
}
