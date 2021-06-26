package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import model.Goku;
import model.Personagem;

public class PainelConfig extends JPanel {
	
	private Personagem personagem;
	private Image fundo;
	
	public PainelConfig(){
		setSize(500,100);
		setLayout(null);
		
		personagem =  new Goku("Heroi//GokuSSJ3.png",0,3,4,0,0);
		personagem.setX(30);
		personagem.setY(30);
		
	}
	public void paint(Graphics g){
		paintComponent(g);
		BufferedImage buffer = new BufferedImage(500,150,BufferedImage.TYPE_INT_ARGB);
		buffer.getGraphics().drawImage(personagem.getSprite().sprites[personagem.getSprite().aparencia],personagem.getX(), personagem.getY(),50,100,null);
		g.drawImage(buffer,0,0,null);
		g.dispose();
		buffer = null;
	}
	public Personagem getPersonagem() {
		return personagem;
	}
	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}
	
}
