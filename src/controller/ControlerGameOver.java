package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Detalhes;
import model.SalvarTempo;
import model.Tempo;
import view.GameOver;
import view.Menu;

public class ControlerGameOver extends MouseAdapter{
	GameOver game;
	public ControlerGameOver(GameOver g){
		game = g;
		game.getSairButton().addMouseListener(this);
	}
	public void mouseClicked(MouseEvent e){
		if(e.getSource() == game.getSairButton())
			System.exit(0);
	}
	public void mouseEntered(MouseEvent e){
		if(e.getSource() == game.getSairButton())
			game.getSairButton().setBorder(Menu.bordaMudada);
	}
	public void mouseExited(MouseEvent e){
		if(e.getSource() == game.getSairButton())
			game.getSairButton().setBorder(Menu.bordaPadrao);
	}
}
