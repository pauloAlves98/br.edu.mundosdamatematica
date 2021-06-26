package controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.Border;

import model.Aluno;
import model.GravadorDeAluno;
import view.Menu;
import view.SimulandoJOptionPane;

public class ControlerMenu {
	private Menu menu;
	private MAdapter mouseA;
	private Aluno aluno1,aluno2;
	public static boolean MULTIPLAYER = false;
	public ControlerMenu(Menu telaJogo){
	
		this.mouseA = new MAdapter();
		this.menu = telaJogo;
		//butoes do menu
		this.menu.getJogarButton().addMouseListener(mouseA);
		this.menu.getCreditoButton().addMouseListener(mouseA);
		this.menu.getConfiguracoesButton().addMouseListener(mouseA);
		this.menu.getSairButton().addMouseListener(mouseA);
		this.menu.getRankingButton().addMouseListener(mouseA);
		//butoes das telas internas
		this.menu.getTelaCreditos().getVoltarButton().addMouseListener(mouseA);
		this.menu.getTelaConfig().getVoltarButton().addMouseListener(mouseA);
		this.menu.getTelaRanking().getVoltarButton().addMouseListener(mouseA);
		this.menu.telaEscolha.singlePlayerButton.addMouseListener(mouseA);
		this.menu.telaEscolha.multiPlayerButton.addMouseListener(mouseA);
		this.menu.telaEscolha.pNomeSingle.iniciarButton.addMouseListener(mouseA);
		this.menu.telaEscolha.pNomeMulti.iniciarButton.addMouseListener(mouseA);
		//this.telaJogo.getMenu().telaEscolha.
		//todas no mesm controler
		this.aluno1 = new Aluno();
		this.aluno2 = new Aluno();

		
	}
	private class MAdapter extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e) {
			//falta multiplayer
			if(e.getSource() == menu.getJogarButton()){
				modificaButao(menu.getJogarButton(),menu.bordaPadrao);
				menu.getPainelOpcoes().setVisible(false);
				menu.telaEscolha.setVisible(true);
				menu.getTelaConfig().setRodando(false);//para a thread
				ControlerConfig.parar();//nao preciso mais da thread deste controler
			}
			else if(e.getSource() == menu.telaEscolha.singlePlayerButton){
				menu.click.tocarSom();
				modificaButao(menu.telaEscolha.singlePlayerButton,menu.bordaPadrao);
				menu.telaEscolha.painelMultSing.setVisible(false);
				menu.telaEscolha.pNomeSingle.setVisible(true);
			}
			else if(e.getSource() == menu.telaEscolha.multiPlayerButton){
				MULTIPLAYER = true;
				menu.click.tocarSom();
				modificaButao(menu.telaEscolha.multiPlayerButton,menu.bordaPadrao);
				menu.telaEscolha.painelMultSing.setVisible(false);
				menu.telaEscolha.pNomeMulti.setVisible(true);
			}
			else if(e.getSource() == menu.telaEscolha.pNomeMulti.iniciarButton){
				menu.click.tocarSom();
				menu.abertura.somParar();
				
				if(menu.telaEscolha.pNomeMulti.nomeField1.getText().isEmpty()){
					SimulandoJOptionPane.mostrarMensagem("JOGADOR1 VAZIO!");
					return;
				}else if(menu.telaEscolha.pNomeMulti.nomeField2.getText().isEmpty()){
					SimulandoJOptionPane.mostrarMensagem("JOGADOR2 VAZIO!");
					return;	
				}
				else
					if(GravadorDeAluno.verificaExistencia(menu.telaEscolha.pNomeMulti.nomeField1.getText())==true){
						SimulandoJOptionPane.mostrarMensagem("JOGADOR1 JÁ EXISTE!");
						return;
					}
				else
					if(GravadorDeAluno.verificaExistencia(menu.telaEscolha.pNomeMulti.nomeField2.getText())==true){
						SimulandoJOptionPane.mostrarMensagem("JOGADOR2 JÁ EXISTE!");
						return;
					}
				else
					if(menu.telaEscolha.pNomeMulti.nomeField2.getText().equals(menu.telaEscolha.pNomeMulti.nomeField1.getText())){
						SimulandoJOptionPane.mostrarMensagem("JOGADORES IGUAIS!!");
						return;
					}
				
				String nome1 = menu.telaEscolha.pNomeMulti.nomeField1.getText();//tirar
				String nome2 = menu.telaEscolha.pNomeMulti.nomeField2.getText();//tirar
				
				aluno1.setNome(nome1);
				aluno2.setNome(nome2);
				
				modificaButao(menu.telaEscolha.pNomeMulti.iniciarButton,menu.bordaPadrao);
			
				ControlerDeTudo.comecarJogoMultPlayer();
			
				menu.setVisible(false);
				menu.dispose();
				menu = null;
				System.gc();
			}
			else if(e.getSource() == menu.telaEscolha.pNomeSingle.iniciarButton){
				menu.click.tocarSom();
				menu.abertura.somParar();
				
				if(menu.telaEscolha.pNomeSingle.nomeField.getText().isEmpty()){
					SimulandoJOptionPane.mostrarMensagem("CAMPO NOME VAZIO!");
					return;
				}else
					if(GravadorDeAluno.verificaExistencia(menu.telaEscolha.pNomeSingle.nomeField.getText())==true){
						SimulandoJOptionPane.mostrarMensagem("ALUNO JÁ EXISTE");
						return;
					}
				
				String nome = menu.telaEscolha.pNomeSingle.nomeField.getText();//tirar
				
				
				aluno1.setNome(nome);
				modificaButao(menu.telaEscolha.pNomeSingle.iniciarButton,menu.bordaPadrao);
				//ControlerDeTudo.comecarJogoSinglePlayer();
				ControlerDeTudo.comecarJogoSinglePlayer();
				//menu.setSize(406,520);
				menu.setVisible(false);
				menu.dispose();
				menu = null;
				System.gc();
				
			}
			else if(e.getSource() == menu.getSairButton()){
				menu.click.tocarSom();
				modificaButao(menu.getSairButton(),menu.bordaPadrao);
				System.exit(0);
			}
			else if(e.getSource() == menu.getCreditoButton()){
				modificaButao(menu.getCreditoButton(),menu.bordaPadrao);
				menu.getTelaCreditos().setVisible(true);
				menu.getPainelOpcoes().setVisible(false);
				menu.getCreditoButton().setBackground(Color.BLACK);
			}
			else if(e.getSource() == menu.getConfiguracoesButton()){
				
				modificaButao(menu.getConfiguracoesButton(),menu.bordaMudada);
				menu.click.tocarSom();
				menu.getTelaConfig().setVisible(true);
				menu.getPainelOpcoes().setVisible(false);
				
			}
			else if(e.getSource() == menu.getRankingButton()){
	
				modificaButao(menu.getRankingButton(),menu.bordaMudada);
				menu.click.tocarSom();
				menu.getTelaRanking().setVisible(true);
				menu.getPainelOpcoes().setVisible(false);
			}
			//botoes internos
			else if(e.getSource() == menu.getTelaCreditos().getVoltarButton()){
				modificaButao(menu.getTelaCreditos().getVoltarButton(),menu.getBordaMudada());
				menu.click.tocarSom();
				menu.getPainelOpcoes().setVisible(true);
				menu.getTelaCreditos().setVisible(false);
			}
			else if(e.getSource() == menu.getTelaConfig().getVoltarButton()){
				
				modificaButao( menu.getTelaConfig().getVoltarButton(),menu.bordaMudada);
				menu.click.tocarSom();
				menu.getPainelOpcoes().setVisible(true);
				menu.getTelaConfig().setVisible(false);
			}
			else if(e.getSource() == menu.getTelaRanking().getVoltarButton()){
				
				modificaButao( menu.getTelaRanking().getVoltarButton(),menu.bordaMudada);
				menu.click.tocarSom();
				menu.getPainelOpcoes().setVisible(true);
				menu.getTelaRanking().setVisible(false);
			}
		}
		public void mouseEntered(MouseEvent e) {//quando esta em cima
			if(e.getSource() == menu.getJogarButton()){
				menu.transicao.tocarSom();
				menu.getJogarButton().setSize(200,100);
				modificaButao(menu.getJogarButton(),menu.bordaMudada);
			}
			else if(e.getSource() == menu.getSairButton()){
				menu.transicao.tocarSom();
				menu.getSairButton().setSize(200,100);
				modificaButao(menu.getSairButton(),menu.bordaMudada);
			}
			else if(e.getSource() == menu.getCreditoButton()){
				menu.transicao.tocarSom();
				menu.getCreditoButton().setSize(200,100);
				modificaButao(menu.getCreditoButton(),menu.bordaMudada);
			}
			else if(e.getSource() == menu.getConfiguracoesButton()){
				menu.transicao.tocarSom();
				menu.getConfiguracoesButton().setSize(200,100);
				modificaButao(menu.getConfiguracoesButton(),menu.bordaMudada);
			}
			else if(e.getSource() == menu.getRankingButton()){
				menu.transicao.tocarSom();
				menu.getRankingButton().setSize(200,100);
				modificaButao(menu.getRankingButton(),menu.bordaMudada);
			}
			//botoes internos
			else if(e.getSource() == menu.getTelaCreditos().getVoltarButton()){
				menu.getTransicao().tocarSom();
				modificaButao(menu.getTelaCreditos().getVoltarButton(),menu.getBordaMudada());
			}
			
			else if(e.getSource() == menu.getTelaConfig().getVoltarButton()){
				   menu.transicao.tocarSom();
				   modificaButao(menu.getTelaConfig().getVoltarButton(),menu.bordaMudada);
			}
			else if(e.getSource() == menu.getTelaRanking().getVoltarButton()){
				   menu.transicao.tocarSom();
				   modificaButao(menu.getTelaRanking().getVoltarButton(),menu.bordaMudada);
			}
			else if(e.getSource() == menu.telaEscolha.singlePlayerButton  ){
				   menu.transicao.tocarSom();
				   modificaButao(menu.telaEscolha.singlePlayerButton,menu.bordaMudada);
			}
			else if(e.getSource() == menu.telaEscolha.pNomeSingle.iniciarButton){
				   menu.transicao.tocarSom();
				   modificaButao(menu.telaEscolha.pNomeSingle.iniciarButton,menu.bordaMudada);
			}
			else if(e.getSource() == menu.telaEscolha.multiPlayerButton  ){
				   menu.transicao.tocarSom();
				   modificaButao(menu.telaEscolha.multiPlayerButton,menu.bordaMudada);
			}
			else if(e.getSource() == menu.telaEscolha.pNomeMulti.iniciarButton){
				   menu.transicao.tocarSom();
				   modificaButao(menu.telaEscolha.pNomeMulti.iniciarButton,menu.bordaMudada);
			}
		}
		public void mouseExited(MouseEvent e) {
			if( menu==null)
				return;
			if(e.getSource() == menu.getJogarButton()){
				menu.getJogarButton().setSize(200,40);
				modificaButao(menu.getJogarButton(),menu.getBordaPadrao());
			}
			else if(e.getSource() == menu.getSairButton()){
				menu.getSairButton().setSize(200,40);
				modificaButao(menu.getSairButton(),menu.bordaPadrao);
			}
			else if(e.getSource() == menu.getCreditoButton()){
				menu.getCreditoButton().setSize(200,40);
				modificaButao(menu.getCreditoButton(),menu.bordaPadrao);
			}
			else if(e.getSource() == menu.getConfiguracoesButton()){
				menu.getConfiguracoesButton().setSize(200,40);
				modificaButao(menu.getConfiguracoesButton(),menu.bordaPadrao);
			}
			else if(e.getSource() == menu.getRankingButton()){
				menu.getRankingButton().setSize(200,40);
				modificaButao(menu.getRankingButton(),menu.bordaPadrao);
			}
			//botoes das telas internas
			else if(e.getSource() == menu.getTelaCreditos().getVoltarButton()){
				modificaButao(menu.getTelaCreditos().getVoltarButton(),menu.bordaPadrao);
			}
			else if(e.getSource() == menu.getTelaConfig().getVoltarButton()){
				modificaButao(menu.getTelaConfig().getVoltarButton(),menu.bordaPadrao);
			}
			else if(e.getSource() == menu.getTelaRanking().getVoltarButton()){
				modificaButao(menu.getTelaRanking().getVoltarButton(),menu.bordaPadrao);
			}
			else if(e.getSource() == menu.telaEscolha.singlePlayerButton  ){
				   menu.transicao.tocarSom();
				   modificaButao(menu.telaEscolha.singlePlayerButton,menu.bordaPadrao);
			}
			else if(e.getSource() == menu.telaEscolha.pNomeSingle.iniciarButton){
				   menu.transicao.tocarSom();
				   modificaButao(menu.telaEscolha.pNomeSingle.iniciarButton,menu.bordaPadrao);
			}
			else if(e.getSource() == menu.telaEscolha.multiPlayerButton  ){
				   menu.transicao.tocarSom();
				   modificaButao(menu.telaEscolha.multiPlayerButton,menu.bordaPadrao);
			}
			else if(e.getSource() == menu.telaEscolha.pNomeMulti.iniciarButton){
				   menu.transicao.tocarSom();
				   modificaButao(menu.telaEscolha.pNomeMulti.iniciarButton,menu.bordaPadrao);
			}
		}
		public void mousePressed(MouseEvent e) {}
	}
	public void modificaButao(JButton butao, Border borda){
		butao.setBorder(borda);
	}
	public Aluno getAluno() {
		return aluno1;
	}
	public void setAluno(Aluno aluno) {
		this.aluno1 = aluno;
	}
	public Aluno getAluno1() {
		return aluno1;
	}
	public void setAluno1(Aluno aluno1) {
		this.aluno1 = aluno1;
	}
	public Aluno getAluno2() {
		return aluno2;
	}
	public void setAluno2(Aluno aluno2) {
		this.aluno2 = aluno2;
	}
	
}
