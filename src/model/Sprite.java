package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite{
	
	public BufferedImage spriteSheet;   
	public int width, height;
	public int rows, columns;
	public int posX, posY;//é atributo de Personagem
	public BufferedImage[] sprites;
	public int aparencia;
	
	public Sprite(String file, int aparencia, int columns, int rows, int posX, int posY){
		spriteSheet = CarregadorDeImagem.carregar(file);
		//ImageIO.read(file);
		this.aparencia = aparencia;
//		this.width = width;
//		this.height = height;
		
		this.width = spriteSheet.getWidth()/columns;
		this.height = spriteSheet.getHeight()/rows;

		this.rows = rows;//nao eh nescssario
		this.columns = columns;//nao eh nescessario
		this.posX=posX;
		this.posY=posY;

		sprites = new BufferedImage[columns * rows];
			for(int i = 0; i < columns; i++) {
			for(int j = 0; j < rows; j++) {
				sprites[(i * rows) + j] = spriteSheet.getSubimage(i * width, j * height, width, height);
			}
		}
	}
	public void mudarSprite(BufferedImage image,int rows,int columns ){
		spriteSheet = (BufferedImage) image;
		this.aparencia = aparencia;
//		this.width = width;
//		this.height = height;
		
		this.width = spriteSheet.getWidth()/columns;
		this.height = spriteSheet.getHeight()/rows;

		this.rows = columns;
		this.columns = rows;

		for(int i = 0; i < columns; i++) {
			for(int j = 0; j < rows; j++) {
				sprites[(i * rows) + j] = spriteSheet.getSubimage(i * width, j * height, width, height);
			}
		}
	}
}