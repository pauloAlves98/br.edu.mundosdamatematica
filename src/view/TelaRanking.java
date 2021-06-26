package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Detalhes;

public class TelaRanking extends JPanel {
	private JButton voltarButton;
	private JLabel fundo;
	private Font font;
	public TelaRanking(ArrayList<Detalhes>detalhes){
		setLayout(null);
		font = new Font("Agency FB",Font.BOLD,20);
		fundo = new JLabel(new ImageIcon("Outros//ranking.jpg"));
		JLabel auxiliar = new JLabel("NOME");
		voltarButton = new JButton(new ImageIcon("Menu//voltar.jpg"));
		auxiliar.setFont(new Font("Agency FB",Font.BOLD,30));
		auxiliar.setBounds(20, 0,100,50);
		auxiliar.setForeground(Color.black);
		add(auxiliar);
		
		auxiliar =  new JLabel("TEMPO");
		auxiliar.setFont(new Font("Agency FB",Font.BOLD,30));
		auxiliar.setBounds(150,0,100,50);
		auxiliar.setForeground(Color.black);
		add(auxiliar);
		for(int i=0;i<detalhes.size();i++){
			
			if(i>=10)
				break;
			
			auxiliar = new JLabel(i+1+" - "+detalhes.get(i).getNome());
			auxiliar.setFont(font);
			auxiliar.setBounds(4,(i+1)*41,100,44);
			auxiliar.setForeground(Color.black);
			add(auxiliar);

			auxiliar = new JLabel(detalhes.get(i).getTempo().getHora()+":"+detalhes.get(i).getTempo().getMinuto()+
		   ":"+detalhes.get(i).getTempo().getSegundo());
			auxiliar.setFont(font);
			auxiliar.setBounds(160,(i+1)*41,100,44);
			auxiliar.setForeground(Color.black);
			add(auxiliar);
		}
		
		voltarButton.setBorder(Menu.bordaPadrao);
		voltarButton.setBounds(380,415, 100, 40);
		fundo.setBounds(0,0,500,500);
		add(voltarButton);
		add(fundo);
		setVisible(false);
	}
	public JButton getVoltarButton() {
		return voltarButton;
	}
	public void setVoltarButton(JButton voltarButton) {
		this.voltarButton = voltarButton;
	}
	
}
