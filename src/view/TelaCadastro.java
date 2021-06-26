package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import model.Tempo;

public class TelaCadastro extends JPanel{
	
	private JFormattedTextField nome;
	private JLabel tempo,digiteNome;
	
	public TelaCadastro(){
		
		setSize(400,100);
		setLayout(new GridLayout(2,1));
		
		Font font = new Font("Agency FB",Font.BOLD,25);
		this.digiteNome = new JLabel("NOME:");
		this.nome = new JFormattedTextField();
		this.tempo = new JLabel("SEU TEMPO: "+"0:"+"0:");//padrao
		
		this.digiteNome.setFont(font);
		this.tempo.setFont(font);
		this.nome.setFont(font);
		this.digiteNome.setForeground(Color.WHITE);
		this.tempo.setForeground(Color.white);
		
		maskLetter(this.nome);//coloca limite no field 5 letras
		
		this.nome.setToolTipText("Coloque o cursor no inicio e digite seu nome!");
		this.nome.setText("NOME");
		
		add(this.digiteNome);
		add(this.nome);
		add(this.tempo);
		
		this.setOpaque(false);
		setVisible(true);
	}
	public void maskLetter(JFormattedTextField jtf) {
		MaskFormatter formatoDois;
		try {
			formatoDois = new MaskFormatter("*****");
			formatoDois.setValidCharacters("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ$*?!");
			formatoDois.install(jtf);
			jtf.setCaretPosition(0);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public JFormattedTextField getNome() {
		return nome;
	}
	public void setNome(JFormattedTextField nome) {
		this.nome = nome;
	}
	public JLabel getTempo() {
		return tempo;
	}
	public void setTempo(JLabel tempo) {
		this.tempo = tempo;
	}
	
}
