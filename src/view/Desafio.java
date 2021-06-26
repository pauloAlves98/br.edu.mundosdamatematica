package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import model.BaseDados;
import model.Som;

public class Desafio extends JPanel {
	
	private JLabel fundo, radar, pergunta;
    private JButton op1;
	private JButton op2;
	private JButton op3;
	private JButton op4;
	private ImageIcon iconeRadar;
	public Border bordaPadrao, bordaMudada;
	private Font font;
	public Som click , transicao;
	
	public Desafio (){
		setPreferredSize(new Dimension(400,299));
		setLayout(null);
		//Instancias
		click = new Som("Sons//click.wav");
		transicao = new Som("Sons//transição.wav");
		fundo = new JLabel(new ImageIcon("Outros//planetaSoma.jpg"));
		iconeRadar = new ImageIcon("Outros//radarSemFundo.gif.");
		
		//Mudar tamanho da Imagem do radar
		iconeRadar.setImage(iconeRadar.getImage().getScaledInstance(300,220,8));
		radar = new JLabel(iconeRadar);
		pergunta = new JLabel("20 + 10");
		
		op1 = new JButton();
		op2 = new JButton();
		op3 = new JButton();
		op4 = new JButton();
		
		font = new Font("Arial",Font.ITALIC,35);
		bordaPadrao = new LineBorder(Color.RED, 3, false);
		bordaMudada = new LineBorder(Color.YELLOW, 3, true);
		
		pergunta.setForeground(Color.white);
		pergunta.setFont(font);
		fundo.setBounds(0, 0,400,300);
		radar.setBounds(80, 0, radar.getIcon().getIconWidth() - 40, 220);
		pergunta.setBounds(230-72,98,250,40);
		
		//Confg butões
		op1.setFocusPainted(false);
		op2.setFocusPainted(false);
		op3.setFocusPainted(false);
		op4.setFocusPainted(false);
		op1.setBackground(Color.black);
		op2.setBackground(Color.black);
		op3.setBackground(Color.black);
		op4.setBackground(Color.black);
		op1.setBorder(bordaPadrao);
		op2.setBorder(bordaPadrao);
		op3.setBorder(bordaPadrao);
		op4.setBorder(bordaPadrao);
		op1.setForeground(Color.white);
		op2.setForeground(Color.white);
		op3.setForeground(Color.white);
		op4.setForeground(Color.white);
		op1.setFont(font);
		op2.setFont(font);
		op3.setFont(font);
		op4.setFont(font);
		
		op1.setBounds(radar.getX()-55,radar.getY()+radar.getHeight(),80, 40);
		op2.setBounds(op1.getX() + op1.getWidth() + 10,op1.getY(),80, 40);
		op3.setBounds(op2.getX() + op2.getWidth() + 10,op2.getY(),80, 40);
		op4.setBounds(op3.getX() + op3.getWidth() + 10, op3.getY(),80, 40);
		
		
		add(op1);
		add(op2);
		add(op3);
		add(op4);
		add(pergunta);
		add(radar);
		add(fundo);
		
		setVisible(false);
	}

	public JLabel getPergunta() {
		return pergunta;
	}

	public void setPergunta(JLabel pergunta) {
		this.pergunta = pergunta;
	}

	public JButton getOp1() {
		return op1;
	}

	public void setOp1(JButton op1) {
		this.op1 = op1;
	}

	public JButton getOp2() {
		return op2;
	}

	public void setOp2(JButton op2) {
		this.op2 = op2;
	}

	public JButton getOp3() {
		return op3;
	}

	public void setOp3(JButton op3) {
		this.op3 = op3;
	}

	public JButton getOp4() {
		return op4;
	}

	public void setOp4(JButton op4) {
		this.op4 = op4;
	}

	public Som getClick() {
		return click;
	}

	public void setClick(Som click) {
		this.click = click;
	}

	public Som getTransicao() {
		return transicao;
	}

	public void setTransicao(Som transicao) {
		this.transicao = transicao;
	}
	
}
