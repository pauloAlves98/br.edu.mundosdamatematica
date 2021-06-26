package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Som;

public class GameOver extends JFrame{
	
	private JLabel gameOver;
	private JButton sairButton;
	public Som fim;
	
	public GameOver(){
		
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		
		fim = new Som("Sons//gameOver.wav");
		gameOver = new JLabel(new ImageIcon("Outros//gameOver.jpg"));
		sairButton = new JButton(new ImageIcon("Menu//sair.jpg"));
		sairButton.setBorder(Menu.bordaPadrao);
		
		gameOver.setBounds(0, 0, 500, 500);
		sairButton.setBounds(150,350,200,40);
		
		add(sairButton);
		add(gameOver);
		
		setVisible(true);
		fim.tocarSom();
	}
	public JButton getSairButton() {
		return sairButton;
	}
	public void setSairButton(JButton sairButton) {
		this.sairButton = sairButton;
	}
	
}
