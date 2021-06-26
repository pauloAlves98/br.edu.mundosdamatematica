package view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Mensagem extends JDialog{
	private JLabel fundo,mensagem;
	public Mensagem(String mensagem){
		
		setSize(400,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		
		this.mensagem = new JLabel(mensagem);
		this.fundo = new JLabel(new ImageIcon("Outros//fundoMensagem.png"));
		this.mensagem.setBounds(40,70,400,100);
		this.fundo.setBounds(0,0,400,250);
		
		this.mensagem.setFont(new Font("Arial",Font.BOLD,30));
		add(this.mensagem);
		add(this.fundo);
		
		setModal(true);//prende o foco
		setVisible(true);
	}
}
