package controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import model.BaseDados;
import model.CarregadorDeImagem;
import model.Personagem;
import view.Cenario;
import view.CenarioGenerico;
import view.Fase1;
import view.Fase2;
import view.Menu;
import view.TelaGanhou;
import view.TelaJogo;

public class ControlerDeTudo {
	//falta encapsular!
	private static ControlerMenu controlMenu ;
	private static ControleAparencia controlAparencia;
	public ControlerDeTudo(){

		BaseDados.carregarBases();
		Menu menu = new Menu();
		new ControlerConfig(menu.getTelaConfig()).start();
		controlMenu =   new ControlerMenu(menu);
		controlAparencia = new ControleAparencia(Personagem.personagens);
		controlAparencia .start();
		//controlAparencia.destroy();
		//msm aluno em todos s controles
	}
	public static void comecarJogoSinglePlayer(){
		
		controlAparencia.rodando = false;
		
		
	
		
		Fase1 fase1;
		Fase2 fase2;
	    Fase1 fase3,fase4;
	    Cenario cenario;
		TelaJogo tela ;
		
		
		cenario = new Cenario();
		fase1 = new Fase1();
		fase2 = new Fase2();
		fase3 = new Fase1();
		fase4 = new Fase1();
		tela = new TelaJogo(cenario,fase1,fase2,fase3,fase4);

		
		
		ControlerCenario c = new ControlerCenario(cenario,fase1,fase2,fase3,fase4, new TelaGanhou(),tela,"Heroi//GokuSSJ1.png");

		ControlerDS cdFase1 = new ControlerDS(fase1,fase1.getPd(),c.getGoku(),cenario.getpInfo(),cenario.getAviso(),"Soma",controlMenu.getAluno());
		ControlerDS cdFase2 = new ControlerDS(fase2,fase2.getPd(),c.getGoku(),cenario.getpInfo(),cenario.getAviso(),"Subtracao",controlMenu.getAluno());
		ControlerDS cdFase3 =  new ControlerDS(fase3,fase3.getPd(),c.getGoku(),cenario.getpInfo(),cenario.getAviso(),"Multiplicacao",controlMenu.getAluno());
		ControlerDS cdFase4 =  new ControlerDS(fase4,fase4.getPd(),c.getGoku(),cenario.getpInfo(),cenario.getAviso(),"Divisao",controlMenu.getAluno());

		ControleFase1 cf1 = new ControleFase1(c.getGoku(),fase1,cenario,cdFase1);
		ControlerFase2 cf2 = new ControlerFase2(c.getGoku(),fase2,cenario,cdFase2);
		ControlerFase3 cf3 = new ControlerFase3(c.getGoku(),fase3,cenario,cdFase3);
		ControlerFase4 cf4 = new ControlerFase4(c.getGoku(),fase4,cenario,cdFase4);

		ArrayList<CenarioGenerico>generico = new   ArrayList<CenarioGenerico>();

		generico.add(cenario);
		generico.add(fase1);
		generico.add(fase2);
		generico.add(fase3);
		generico.add(fase4);

		tela.addKeyListener(new TAdapterPlayer1(c.getGoku(),generico));
		
		new ControlerTempo(cenario.getpInfo()).start();
		new LoopGameSinglePlayer(c,cf1,cf2,cf3,cf4,tela).start();//cf3
		
		
		//carregando.dispose();
		tela.setVisible(true);
		
		controlAparencia = new ControleAparencia(Personagem.personagens);
		controlAparencia .start();
	}
	public static void comecarJogoMultPlayer(){
		controlAparencia.rodando = false;
		Fase1 fase1;
		Fase2 fase2;
		Fase1 fase3,fase4;
		Cenario cenario;
		TelaJogo tela ;
		//Player 1
		cenario = new Cenario();
		fase1 = new Fase1();
		fase2 = new Fase2();
		fase3 = new Fase1();
		fase4 = new Fase1();
		tela = new TelaJogo(cenario,fase1,fase2,fase3,fase4);
	
		//Player 1
		ControlerCenario c = new ControlerCenario(cenario,fase1,fase2,fase3,fase4, new TelaGanhou(),tela,"Heroi//GokuSSJ1.png");
		ControlerDS cdFase1 = new ControlerDS(fase1,fase1.getPd(),c.getGoku(),cenario.getpInfo(),cenario.getAviso(),"Soma",controlMenu.getAluno());
		ControlerDS cdFase2 = new ControlerDS(fase2,fase2.getPd(),c.getGoku(),cenario.getpInfo(),cenario.getAviso(),"Subtracao",controlMenu.getAluno());
		ControlerDS cdFase3 =  new ControlerDS(fase3,fase3.getPd(),c.getGoku(),cenario.getpInfo(),cenario.getAviso(),"Multiplicacao",controlMenu.getAluno());
		ControlerDS cdFase4 =  new ControlerDS(fase4,fase4.getPd(),c.getGoku(),cenario.getpInfo(),cenario.getAviso(),"Divisao",controlMenu.getAluno());
		
		ControleFase1 cf1 = new ControleFase1(c.getGoku(),fase1,cenario,cdFase1);
		ControlerFase2 cf2 = new ControlerFase2(c.getGoku(),fase2,cenario,cdFase2);
		ControlerFase3 cf3 = new ControlerFase3(c.getGoku(),fase3,cenario,cdFase3);
		ControlerFase4 cf4 = new ControlerFase4(c.getGoku(),fase4,cenario,cdFase4);
		
		ArrayList<CenarioGenerico>generico = new   ArrayList<CenarioGenerico>();
		generico.add(cenario);
		generico.add(fase1);
		generico.add(fase2);
		generico.add(fase3);
		generico.add(fase4);
		
		//Player 2
		Cenario cenarioAluno2 = new Cenario();
		Fase1 fase1Aluno2 = new Fase1();
		Fase2 fase2Aluno2 = new Fase2();
		Fase1 fase33 = new Fase1();
		Fase1 fase4Aluno2 = new Fase1();
		TelaJogo tela2 = new TelaJogo(cenarioAluno2,fase1Aluno2,fase2Aluno2,fase33,fase4Aluno2);

		
		cenarioAluno2.getpInfo().setPerfil(CarregadorDeImagem.carregar("Outros//perfil2.png"));
		fase1Aluno2.setSsj(CarregadorDeImagem.carregar("Heroi//TruncksSSJ2.png"));
		
		ControlerCenario c2Aluno2 = new ControlerCenario(cenarioAluno2,fase1Aluno2,fase2Aluno2,fase33,fase4Aluno2, new TelaGanhou(),tela2,"Heroi//Truncks.png");//mudar
		ControlerDS cd2Fase1 = new ControlerDS(fase1Aluno2,fase1Aluno2.getPd(),c2Aluno2.getGoku(),cenarioAluno2.getpInfo(),cenarioAluno2.getAviso(),"Soma",controlMenu.getAluno2());
		ControlerDS cd2Fase2 = new ControlerDS(fase2Aluno2,fase2Aluno2.getPd(),c2Aluno2.getGoku(),cenarioAluno2.getpInfo(),cenarioAluno2.getAviso(),"Subtracao",controlMenu.getAluno2());
		ControlerDS cd2Fase3 =  new ControlerDS(fase33,fase33.getPd(),c2Aluno2.getGoku(),cenarioAluno2.getpInfo(),cenarioAluno2.getAviso(),"Multiplicacao",controlMenu.getAluno2());
		ControlerDS cd2Fase4 =  new ControlerDS(fase4Aluno2,fase4Aluno2.getPd(),c2Aluno2.getGoku(),cenarioAluno2.getpInfo(),cenarioAluno2.getAviso(),"Divisao",controlMenu.getAluno2());
		//player 2
		ControleFase1 cf11 = new ControleFase1(c2Aluno2.getGoku(),fase1Aluno2,cenarioAluno2,cd2Fase1);
		ControlerFase2 cf22 = new ControlerFase2(c2Aluno2.getGoku(),fase2Aluno2,cenarioAluno2,cd2Fase2);
		ControlerFase3 cf33 = new ControlerFase3(c2Aluno2.getGoku(),fase33,cenarioAluno2,cd2Fase3);
		ControlerFase4 cf44 = new ControlerFase4(c2Aluno2.getGoku(),fase4Aluno2,cenarioAluno2,cd2Fase4);
		
		//player 2
		ArrayList<CenarioGenerico>generico2 = new   ArrayList<CenarioGenerico>();
		
		generico2.add(cenarioAluno2);
		generico2.add(fase1Aluno2);
		generico2.add(fase2Aluno2);
		generico2.add(fase33);
		generico2.add(fase4Aluno2);

		
		//coloca o msm evento nas duas telas!
		tela.addKeyListener(new TAdapterPlayer1(c.getGoku(),generico));
		tela.addKeyListener(new TAdapterPlayer2(c2Aluno2.getGoku(),generico2));
		//tela2.addKeyListener(new TAdapterPlayer1(c.getGoku(),generico));
		tela2.addKeyListener(new TAdapterPlayer2(c2Aluno2.getGoku(),generico2));
		//as thread Comeca igual!
		
		new ControlerTempo(cenario.getpInfo()).start();
		new ControlerTempo(cenarioAluno2.getpInfo()).start();
		
		new LoopGamePlayer1(c,cf1,cf2,cf3,cf4,tela).start();
		new LoopGamePlayer2(c2Aluno2,cf11,cf22,cf33,cf44,tela2).start();
		
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension d = tk.getScreenSize();
	    System.out.println(d.height/2);
	    
	    
	    //Posicionamento Das Telas!
	    tela.setVisible(true);
	    tela2.setVisible(true);
		tela.setLocation((int)d.getWidth()/2 - 406, (int) (d.getHeight()/2 - 250));
		tela2.setLocation((int)d.getWidth()/2 + 20, (int) (d.getHeight()/2 - 250));
		
		controlAparencia = new ControleAparencia(Personagem.personagens);
		controlAparencia .start();
		
	}
}
