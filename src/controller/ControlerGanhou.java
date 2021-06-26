package controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import model.Detalhes;
import model.SalvarTempo;
import model.Tempo;
import view.TelaGanhou;
import view.Menu;

public class ControlerGanhou{
	TelaGanhou ganhou;
	Tempo tempo;
	
	public ControlerGanhou(TelaGanhou ganhou, Tempo tempo){
		this.ganhou = ganhou;
		this.tempo = tempo;
		GanhouAdapter g = new GanhouAdapter();
		this.ganhou.getSairButton().addMouseListener(g);
		this.ganhou.getCadastrarButton().addMouseListener(g);
		this.ganhou.getTelaCadastro().getTempo().setText("SEU TEMPO: "+tempo.getHora()+":"+tempo.getMinuto()+":"+tempo.getSegundo());
	}
	private class GanhouAdapter extends MouseAdapter{

		public void mouseClicked(MouseEvent e){
			if(e.getSource() == ganhou.getSairButton()){
				if(ControlerMenu.MULTIPLAYER){
					if(LoopGamePlayer1.emJogo || LoopGamePlayer2.emJogo)
						ganhou.dispose();
					else
						System.exit(0);
				}else
					System.exit(0);
				
			}
			else if(e.getSource() == ganhou.getCadastrarButton()){
			
				if(ganhou.getTelaCadastro().getNome().getText().equalsIgnoreCase("     ")){
					ganhou.requestFocus();
					JOptionPane.showMessageDialog(null,"Campo Nome Vazio!");
				}
				else 
					if(SalvarTempo.GravarDetalhes(new Detalhes(tempo,ganhou.getTelaCadastro().getNome().getText())) == true ){
					System.out.println("Gravou Belza");
					if(ControlerMenu.MULTIPLAYER){
						if(LoopGamePlayer1.emJogo || LoopGamePlayer2.emJogo)
							ganhou.dispose();
						else
							System.exit(0);
					}else
						System.exit(0);
				}
			}
		}
		public void mouseEntered(MouseEvent e){
			if(e.getSource() == ganhou.getSairButton())
				ganhou.getSairButton().setBorder(Menu.bordaMudada);
			else if(e.getSource() == ganhou.getCadastrarButton())
				ganhou.getCadastrarButton().setBorder(Menu.bordaMudada);
		}
		public void mouseExited(MouseEvent e){
			if(e.getSource() == ganhou.getSairButton())
				ganhou.getSairButton().setBorder(Menu.bordaPadrao);
			else if(e.getSource() == ganhou.getCadastrarButton())
				ganhou.getCadastrarButton().setBorder(Menu.bordaPadrao);
		}
	}
}
