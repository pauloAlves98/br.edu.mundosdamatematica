package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelEscolha extends JPanel {
	JLabel fundo;
	public JButton multiPlayerButton, singlePlayerButton;
	public JPanel painelMultSing;
	public PainelNomeSingle pNomeSingle;
	public PainelNomeMulti pNomeMulti;
	
	public PainelEscolha (){
		
		setSize(500,500);
		setLayout(null);
		
		fundo = new JLabel(new ImageIcon("Outros//telaConfig.png"));
		
		multiPlayerButton = new JButton(new ImageIcon("Menu//multiplayer.jpg"));
		singlePlayerButton = new JButton(new ImageIcon("Menu//singlePlayer.jpg"));
		painelMultSing = new JPanel(null);
		
		pNomeSingle = new PainelNomeSingle();
		pNomeMulti = new PainelNomeMulti();
		
		painelMultSing.add(singlePlayerButton);
		painelMultSing.add(multiPlayerButton);
		
		singlePlayerButton.setBounds(33,0,200,40);
		multiPlayerButton.setBounds(263,0,200,40);
		
		singlePlayerButton.setBorder(Menu.bordaPadrao);
		multiPlayerButton.setBorder(Menu.bordaPadrao); 
		
		painelMultSing.setBounds(0,210,500,40);
		fundo.setBounds(0, 0, 500, 500);
		painelMultSing.setOpaque(false);
		pNomeSingle.setBounds(0,0,500,500);
		pNomeMulti.setBounds(0,0,500,500);
		
		add(painelMultSing);
		add(pNomeSingle);
		add(pNomeMulti);
		add(fundo);
		
		setVisible(false);
	}

}
