package view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelNomeSingle extends JPanel{
	public JLabel nome,controles;
	public JTextField nomeField;
	public JButton iniciarButton;
	public PainelNomeSingle(){
		
		setSize(500,500);
		setOpaque(false);
		setLayout(null);
		nome = new JLabel(new ImageIcon("Menu//jogador.png"));
		nomeField = new JTextField(10);
		iniciarButton = new JButton(new ImageIcon("Menu//iniciar.jpg"));
		nomeField.setFont(new Font("Agency FB",Font.BOLD,25));
		controles = new JLabel(new ImageIcon("Outros//KeyBordPlayer1.png"));
		iniciarButton.setBorder(Menu.bordaPadrao);
		
		add(nome);
		add(nomeField);
		add(controles);
		add(iniciarButton);
		
		nome.setBounds(200,95,100,40);
		nomeField.setBounds(175,140,150,40);
		iniciarButton.setBounds(200,190,100,40);
		controles.setBounds(160,220,200,250);
		
		setVisible(false);
	}

}
