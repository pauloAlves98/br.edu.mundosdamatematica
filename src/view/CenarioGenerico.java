package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Goku;
import model.Vilao;

public abstract class CenarioGenerico extends JPanel {
	
	private int larguraMapa = 640, alturaMapa = 640;//altura padrao
	private  int xCena,yCena;
	public static int FPS = 22;//fps padrao
	private boolean paint = false;
	
	private ArrayList<Vilao> viloes = new ArrayList<Vilao>();
	private Goku goku;
	private boolean mostraAviso;
	
	public CenarioGenerico(){
		
	}
	public void chamaRepaint(){
		repaint();
	}
	public int getLarguraMapa() {
		return larguraMapa;
	}
	public void setLarguraMapa(int larguraMapa) {
		this.larguraMapa = larguraMapa;
	}
	public int getAlturaMapa() {
		return alturaMapa;
	}
	public void setAlturaMapa(int alturaMapa) {
		this.alturaMapa = alturaMapa;
	}
	public int getxCena() {
		return xCena;
	}
	public void setxCena(int xCena) {
		this.xCena = xCena;
	}
	public int getyCena() {
		return yCena;
	}
	public void setyCena(int yCena) {
		this.yCena = yCena;
	}
	
	public ArrayList<Vilao> getViloes() {
		return viloes;
	}
	public void setViloes(ArrayList<Vilao> viloes) {
		this.viloes = viloes;
	}
	public Goku getGoku() {
		return goku;
	}
	public void setGoku(Goku goku) {
		this.goku = goku;
	}
	public boolean isMostraAviso() {
		return mostraAviso;
	}
	public void setMostraAviso(boolean mostraAviso) {
		this.mostraAviso = mostraAviso;
	}
	public boolean isPaint() {
		return paint;
	}
	public void setPaint(boolean paint) {
		this.paint = paint;
	}
	
	
}
