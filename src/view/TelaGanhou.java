package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Som;

public class TelaGanhou extends JFrame{
	private JButton sairButton,cadastrarButton;
	private JLabel fundo,mensagem;
	private Som somFim;
	private TelaCadastro telaCadastro;
	
	public TelaGanhou(){
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		
		telaCadastro = new TelaCadastro();
		fundo = new JLabel(new ImageIcon("Outros//ganhou.gif"));
		mensagem = new JLabel(new ImageIcon("Outros//mensagem.png"));
		sairButton = new JButton(new ImageIcon("Menu//sair.jpg"));
		sairButton.setBorder(Menu.bordaPadrao);
		cadastrarButton = new JButton(new ImageIcon("Menu//cadastrarTempo.jpg"));
		cadastrarButton.setBorder(Menu.bordaPadrao);
		somFim = new Som("Sons//audioEntrada.wav");//carrega apenas
		fundo.setBounds(0,0,500,500);
		mensagem.setBounds(50,50,300,100);
		sairButton.setBounds(265,330,200,40);
		cadastrarButton.setBounds(40,330,200,40);
		telaCadastro.setBounds(40,200,400,80);
		
		add(telaCadastro);
		add(mensagem);
		add(sairButton);
		add(cadastrarButton);
		add(fundo);
		
		setVisible(false);
	}

	public JButton getSairButton() {
		return sairButton;
	}

	public void setSairButton(JButton sairButton) {
		this.sairButton = sairButton;
	}

	public JButton getCadastrarButton() {
		return cadastrarButton;
	}
	public Som getSom() {
		return somFim;
	}
	public void setCadastrarButton(JButton cadastrarButton) {
		this.cadastrarButton = cadastrarButton;
	}

	public TelaCadastro getTelaCadastro() {
		return telaCadastro;
	}

	public void setTelaCadastro(TelaCadastro telaCadastro) {
		this.telaCadastro = telaCadastro;
	}
	
	
}
