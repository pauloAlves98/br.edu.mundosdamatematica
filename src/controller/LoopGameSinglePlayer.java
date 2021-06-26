package controller;

import javax.swing.JOptionPane;

import view.Cenario;
import view.CenarioGenerico;
import view.Fase1;
import view.Fase2;
import view.GameOver;
import view.TelaJogo;



public class LoopGameSinglePlayer extends Thread {
	
	protected  ControlerCenario controlCenario;
	protected ControleFase1 controlFase1;
	protected ControlerFase2 controlFase2;
	protected ControlerFase3 controlFase3;
	protected ControlerFase4 controlFase4;
	protected TelaJogo tela;
	protected boolean running = true;;
	protected long beforeTime = 0, afterTime = 0,totalTime = 0;
	
	public LoopGameSinglePlayer(ControlerCenario cenario, ControleFase1 fase1, ControlerFase2 fase2,ControlerFase3 fase3,ControlerFase4 fase4,TelaJogo tela) {
		this.controlCenario = cenario;
		this.setControlFase1(fase1);
		this.controlFase2 = fase2;
		this.controlFase3= fase3;
		this.controlFase4 = fase4;
		this.tela = tela;//frame
	}
	public void run(){
		Cenario cenario = controlCenario.getCenario();
		Fase1 fase1 = getControlFase1().getFase1();
		Fase2 fase2 = controlFase2.getFase2View();
		Fase1 fase3 = controlFase3.getFase3();
		Fase1 fase4 = controlFase4.getFase4();
				
		cenario.getpInfo().setGoku(controlCenario.getGoku());
		
		while(running){
			
			 rodarJogo();
			
			try {
				if(totalTime < (1000/CenarioGenerico.FPS) ){
					Thread.sleep((1000/CenarioGenerico.FPS) - totalTime );
					System.out.println("Total Time"+totalTime);
				}else{
					
					System.out.println("Total Time MAIORRRRRRRRRRRRRRRRRRRR"+totalTime);
					Thread.sleep(1);
				}
			}catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null,"ThreadJogoInterrompida\n"+ e.getMessage());
				e.printStackTrace();//tirar
			}
			if(controlCenario.getGoku().getLife()>=360){
				tela.setVisible(false);
				cenario.somFundo.somParar();
				new ControlerGameOver(new GameOver());
				running = false;
			}
			tela.requestFocus();
			controlCenario.getCenario().getpInfo().repaint();
			//retirar das colisoes o repaint do inventario;
			System.out.println();
		}//fim while
	}
	public ControleFase1 getControlFase1() {
		return controlFase1;
	}
	public void setControlFase1(ControleFase1 controlFase1) {
		this.controlFase1 = controlFase1;
	}
	public ControlerCenario getControlCenario() {
		return controlCenario;
	}
	public void setControlCenario(ControlerCenario controlCenario) {
		this.controlCenario = controlCenario;
	}
	public ControlerFase2 getControlFase2() {
		return controlFase2;
	}
	public void setControlFase2(ControlerFase2 controlFase2) {
		this.controlFase2 = controlFase2;
	}
	public ControlerFase3 getControlFase3() {
		return controlFase3;
	}
	public void setControlFase3(ControlerFase3 controlFase3) {
		this.controlFase3 = controlFase3;
	}
	public ControlerFase4 getControlFase4() {
		return controlFase4;
	}
	public void setControlFase4(ControlerFase4 controlFase4) {
		this.controlFase4 = controlFase4;
	}
	public void rodarJogo(){
		
		Cenario cenario = controlCenario.getCenario();
		Fase1 fase1 = getControlFase1().getFase1();
		Fase2 fase2 = controlFase2.getFase2View();
		Fase1 fase3 = controlFase3.getFase3();
		Fase1 fase4 = controlFase4.getFase4();
		
		if(cenario.isPaint()) {
			beforeTime = System.currentTimeMillis();
			
			controlCenario.getGoku().mexer(cenario.getLarguraMapa(),cenario.getAlturaMapa());
			controlCenario.checarColisao(cenario.getLarguraMapa(),cenario.getAlturaMapa());
			controlCenario.moverCena(controlCenario.getGoku().getX(),controlCenario.getGoku().getY());//Move cenario
			
			cenario.chamaRepaint();
			
			afterTime = System.currentTimeMillis();
			totalTime = afterTime - beforeTime;
			System.out.println("Cenario 1 - Depois:"+cenario.getGoku().getX()+","+cenario.getGoku().getY());//tirar
		}
		else if(fase1.isPaint()){
			beforeTime = System.currentTimeMillis();
			
			getControlFase1().getGoku().mexer(fase1.getLarguraMapa(),fase1.getAlturaMapa());
			System.out.println("Depois:"+ getControlFase1().getGoku().getX() +"," +getControlFase1().getGoku().getY());
			getControlFase1().checarColisao(fase1.getLarguraMapa(),fase1.getAlturaMapa());
			getControlFase1().moverCena(getControlFase1().getGoku().getX(),getControlFase1().getGoku().getY());//Move cenario
			fase1.chamaRepaint();
			
			afterTime = System.currentTimeMillis();
			totalTime = afterTime - beforeTime;
			//System.out.println("Depois:"+xCena+","+yCena);
		}
		else if(fase2.isPaint()){
			beforeTime = System.currentTimeMillis();
			
			controlFase2.getGoku().mexer(fase2.getLarguraMapa(),fase2.getAlturaMapa());
			controlFase2.checarColisao(fase2.getLarguraMapa(),fase2.getAlturaMapa());
			controlFase2.moverCena(controlFase2.getGoku().getX(),controlFase2.getGoku().getY());//Move cenario
			System.out.println("            Depois:"+ fase2.getGoku().getX() +"," +fase2.getGoku().getY());
			
			fase2.chamaRepaint();
			
			afterTime = System.currentTimeMillis();
			totalTime = afterTime - beforeTime;
		}
		else if(fase3.isPaint()){
			
			beforeTime = System.currentTimeMillis();
			
			controlFase3.getGoku().mexer(fase3.getLarguraMapa(),fase3.getAlturaMapa());
			System.out.println("Depois:"+ controlFase3.getGoku().getX() +"," +controlFase3.getGoku().getY());
			controlFase3.checarColisao(fase3.getLarguraMapa(),fase3.getAlturaMapa());
			controlFase3.moverCena(controlFase3.getGoku().getX(),controlFase3.getGoku().getY());//Move cenario
			fase3.chamaRepaint();
			
			afterTime = System.currentTimeMillis();
			totalTime = afterTime - beforeTime;
			//System.out.println("Depois:"+xCena+","+yCena);
		}
		
		else if(fase4.isPaint()){
			beforeTime = System.currentTimeMillis();
			
			controlFase4.getGoku().mexer(fase4.getLarguraMapa(),fase4.getAlturaMapa());
			System.out.println("Depois:"+ controlFase4.getGoku().getX() +"," +controlFase4.getGoku().getY());
			controlFase4.checarColisao(fase4.getLarguraMapa(),fase4.getAlturaMapa());
			controlFase4.moverCena(controlFase4.getGoku().getX(),controlFase4.getGoku().getY());//Move cenario
			fase4.chamaRepaint();
			
			afterTime = System.currentTimeMillis();
			totalTime = afterTime - beforeTime;
			//System.out.println("Depois:"+xCena+","+yCena);
		}
	}
}
