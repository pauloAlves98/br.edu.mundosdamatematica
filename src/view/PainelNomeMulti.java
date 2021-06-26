package view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//desenhar Jogador1,Jogador 2, iniciar, falta bordas Botao
public class PainelNomeMulti extends JPanel {
	JLabel nome1,nome2,controles1,controles2;
	public JTextField nomeField1,nomeField2;
	public JButton iniciarButton;
	public PainelNomeMulti(){
		setSize(500,500);
		setOpaque(false);
		setLayout(null);
		
		nome1 = new JLabel(new ImageIcon("Menu//jogador1.png"));
		nomeField1 = new JTextField(10);
		nome2 = new JLabel(new ImageIcon("Menu//jogador2.png"));
		nomeField2 = new JTextField(10);
		iniciarButton = new JButton(new ImageIcon("Menu//iniciar.jpg"));
		iniciarButton.setBorder(Menu.bordaPadrao);
		
		controles1 = new JLabel(new ImageIcon("Outros//KeyBordPlayer1.png"));//falta posicionar
		controles2 = new JLabel(new ImageIcon("Outros//KeyBordPlayer2.png"));
		
		add(nome1);
		add(nomeField1);
		add(nome2);
		add(nomeField2);
		add(controles1);
		add(controles2);
		add(iniciarButton);
		
		nomeField1.setFont(new Font("Agency FB",Font.BOLD,25));
		nomeField2.setFont(new Font("Agency FB",Font.BOLD,25));
		
		nome1.setBounds(105,95,100,40);
		nomeField1.setBounds(70,140,150,40);
		nome2.setBounds(295,95,100,40);
		nomeField2.setBounds(260,140,150,40);
		controles1.setBounds(0,220,200,250);
		controles2.setBounds(296,220,200,250);
		iniciarButton.setBounds(185,220,100,40);
		
		setVisible(false);
	}
}
