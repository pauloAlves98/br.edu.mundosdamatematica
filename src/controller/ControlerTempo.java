package controller;

import javax.swing.JOptionPane;

import model.Goku;
import view.GameOver;
import view.Inventario;

public class ControlerTempo  extends Thread{
	private Inventario inventario;
	public ControlerTempo(Inventario inventario){
		this.inventario = inventario;
	}
	public void run(){
		while(inventario.isRodando()){
			try {
				inventario.getTempo().setSegundo(inventario.getTempo().getSegundo() + 1);
				if(inventario.getTempo().getSegundo()>=60){
					inventario.getTempo().setMinuto(inventario.getTempo().getMinuto() +1);
					inventario.getTempo().setSegundo(0);
				}
				if(inventario.getTempo().getMinuto()>=60){
					inventario.getTempo().setHora(inventario.getTempo().getHora() + 1);
					inventario.getTempo().setMinuto(0);
				}
				inventario.getTempo().setTempoTotal(inventario.getTempo().getTempoTotal() + 1);//total em segundos
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null,"Erro na Thread Tempo"+ e.getMessage() + e.getLocalizedMessage());
				e.printStackTrace();
			}
			//atualizar();
			//inventario.repaint();
		
		}
	}
}
