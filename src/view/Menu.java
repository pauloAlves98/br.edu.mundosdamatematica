package view;
import java.awt.Color;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import model.SalvarTempo;
import model.Som;

public class Menu extends JFrame{

	public static Som abertura,click,transicao;
	private JButton jogarButton;//rankingButton;
	private JButton sairButton;
	private JButton creditoButton,configuracoesButton,rankingButton;
	private JLabel fundo;
	public static Border bordaPadrao = new LineBorder(Color.YELLOW, 3, false);
	public static Border bordaMudada = new LineBorder(Color.RED, 3, false);;
	private JPanel painelOpcoes;
	private Creditos telaCreditos;
	private TelaRanking telaRanking;
	private TelaConfiguracao telaConfig;
	public PainelEscolha telaEscolha;
	
	public Menu(){
		
		setIconImage(new ImageIcon("Outros//uast.png").getImage());
		setResizable(false);
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(500,500);
		setLayout(null);
		
		fundo = new JLabel(new ImageIcon("Menu//Fundo03.jpg"));
		fundo.setBounds(0, 0, 500, 500);
		
		painelOpcoes = new JPanel(null);
		jogarButton = new JButton(new ImageIcon("Menu//jogar.jpg"));
		jogarButton.setBorder(bordaPadrao);
		sairButton = new JButton(new ImageIcon("Menu//sair.jpg"));
		sairButton.setBorder(bordaPadrao);
		creditoButton = new JButton(new ImageIcon("Menu//creditos.jpg"));
		creditoButton.setBorder(bordaPadrao);
		configuracoesButton = new JButton(new ImageIcon("Menu//Configuracoes.jpg"));
		configuracoesButton.setBorder(bordaPadrao);
		rankingButton = new JButton(new ImageIcon("Menu//ranking.jpg"));
		rankingButton.setBorder(bordaPadrao);
		
		jogarButton.setBackground(Color.BLACK);
		jogarButton.setFocusPainted(false);
		creditoButton.setBackground(Color.BLACK);
		creditoButton.setFocusPainted(false);
		configuracoesButton .setBackground(Color.BLACK);
		configuracoesButton .setFocusPainted(false);
		rankingButton.setFocusPainted(false);
		rankingButton.setBackground(Color.BLACK);
		sairButton.setBackground(Color.BLACK);
		sairButton.setFocusPainted(false);
		//sairButton.setBorderPainted(false);
		
		telaCreditos = new Creditos();
		telaConfig = new TelaConfiguracao();
		telaRanking = new TelaRanking(SalvarTempo.carregar());
		telaEscolha = new PainelEscolha();
		//Bounds
		jogarButton.setBounds(fundo.getWidth()/2 - 100, fundo.getHeight()/2-10, 200, 40);
		creditoButton.setBounds(fundo.getWidth()/2 - 100, jogarButton.getY()+ 43, 200, 40);
		configuracoesButton.setBounds(fundo.getWidth()/2 - 100,creditoButton.getY() + 43, 200, 40);
		rankingButton.setBounds(fundo.getWidth()/2 - 100, configuracoesButton.getY() + 43, 200, 40);
		sairButton.setBounds(fundo.getWidth()/2 - 100, rankingButton.getY() + 43, 200, 40);
		
		
		painelOpcoes.setBounds(0, 0, 500, 500);
		telaEscolha.setBounds(0, 0, 500, 500);
		telaCreditos.setBounds(0, 0, 500, 500);
		telaConfig.setBounds(0, 0, 500, 500);
		telaRanking.setBounds(0,0,500,500);
		
		abertura = new Som("Sons//audioDesafio02.wav");
		click = new Som("Sons//click.wav");
		transicao = new Som("Sons//transição.wav");

		painelOpcoes.add(jogarButton);
		painelOpcoes.add(creditoButton);
		painelOpcoes.add(configuracoesButton);
		painelOpcoes.add(rankingButton);
		painelOpcoes.add(sairButton);
		painelOpcoes.add(fundo);
		
		
		add(painelOpcoes);//tela de escolha
		add(telaCreditos);
		add(telaConfig);
		add(telaRanking);
		add(telaEscolha);
		//add(fundo);
		
		abertura.somEmLoop(true);
		setVisible(true);
	}

	public static Som getAbertura() {
		return abertura;
	}
	public static void setAbertura(Som abertura) {
		Menu.abertura = abertura;
	}
	public static Som getClick() {
		return click;
	}
	public static void setClick(Som click) {
		Menu.click = click;
	}
	public static Som getTransicao() {
		return transicao;
	}
	public static void setTransicao(Som transicao) {
		Menu.transicao = transicao;
	}
	public JButton getJogarButton() {
		return jogarButton;
	}
	public void setJogarButton(JButton jogarButton) {
		this.jogarButton = jogarButton;
	}
	public JButton getSairButton() {
		return sairButton;
	}
	public void setSairButton(JButton sairButton) {
		this.sairButton = sairButton;
	}
	public JButton getCreditoButton() {
		return creditoButton;
	}
	public void setCreditoButton(JButton creditoButton) {
		this.creditoButton = creditoButton;
	}
	public JButton getConfiguracoesButton() {
		return configuracoesButton;
	}
	public void setConfiguracoesButton(JButton configuracoesButton) {
		this.configuracoesButton = configuracoesButton;
	}
	public JLabel getFundo() {
		return fundo;
	}
	public void setFundo(JLabel fundo) {
		this.fundo = fundo;
	}
	public static Border getBordaPadrao() {
		return bordaPadrao;
	}
	public static void setBordaPadrao(Border bordaPadrao) {
		Menu.bordaPadrao = bordaPadrao;
	}
	public static Border getBordaMudada() {
		return bordaMudada;
	}
	public static void setBordaMudada(Border bordaMudada) {
		Menu.bordaMudada = bordaMudada;
	}
	public JPanel getPainelOpcoes() {
		return painelOpcoes;
	}
	public void setPainelOpcoes(JPanel painelOpcoes) {
		this.painelOpcoes = painelOpcoes;
	}
	public Creditos getTelaCreditos() {
		return telaCreditos;
	}
	public void setTelaCreditos(Creditos telaCreditos) {
		this.telaCreditos = telaCreditos;
	}
	public TelaConfiguracao getTelaConfig() {
		return telaConfig;
	}
	public void setTelaConfig(TelaConfiguracao telaConfig) {
		this.telaConfig = telaConfig;
	}

	public JButton getRankingButton() {
		return rankingButton;
	}

	public void setRankingButton(JButton rankingButton) {
		this.rankingButton = rankingButton;
	}

	public TelaRanking getTelaRanking() {
		return telaRanking;
	}

	public void setTelaRanking(TelaRanking telaRanking) {
		this.telaRanking = telaRanking;
	}
	
}
