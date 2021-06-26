package controller;

import javax.swing.JOptionPane;

import view.Cenario;
import view.CenarioGenerico;
import view.Fase1;
import view.Fase2;
import view.GameOver;
import view.TelaJogo;

public class LoopGamePlayer1 extends LoopGameSinglePlayer{
	static boolean  emJogo;
	public LoopGamePlayer1(ControlerCenario cenario, ControleFase1 fase1, ControlerFase2 fase2, ControlerFase3 fase3,
			ControlerFase4 fase4, TelaJogo tela) {
		super(cenario, fase1, fase2, fase3, fase4, tela);
		emJogo = true;
	}
	public void run(){
		
//		long beforeTime = 0;
//		long afterTime = 0;
//		long totalTime = 0;
		
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
			
			controlCenario.getCenario().getpInfo().repaint();
			
			if(controlCenario.getGoku().getLife()>=360){
				 LoopGamePlayer1.emJogo = false;
				if( LoopGamePlayer2.emJogo){
					GameOver gm = new GameOver();
					gm.getSairButton().setVisible(false);
					gm.setLocation(tela.getX() - 100,tela.getY());
					tela.setVisible(false);
					//criar tela de espera!
				}else{
					tela.setVisible(false);
					cenario.somFundo.somParar();
					new ControlerGameOver(new GameOver());
				}
				tela = null;
				System.gc();
				running = false;
			}
			if(tela!=null)
			tela.requestFocus();//Esta tela ira conter o foco do Jogo
			System.out.println();
		}//fim while
	}
	
}
