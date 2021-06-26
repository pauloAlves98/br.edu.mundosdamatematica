package controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import model.Aluno;
import model.BaseDados;
import model.Goku;
import model.GravadorDeAluno;
import model.Perguntas;
import view.Aviso;
import view.CenarioGenerico;
import view.Desafio;
import view.Fase1;
import view.Fase2;
import view.Inventario;
import view.SimulandoJOptionPane;

public class ControlerDS {
	
	private Aviso aviso;//view
	private Inventario pInfo;//view
	private Goku goku;//model
	private MAdapter mAdapter;
	private Random randon = new Random();
	private Desafio desafio;
	private CenarioGenerico generico;
	private ArrayList <String>perguntas,respostas;
	public int destrava;
	private int num = 0;
	private String base;
	private ArrayList<Perguntas>perguntasAluno = new ArrayList<Perguntas>();
	private Perguntas per;
	private Aluno aluno;
	
	public ControlerDS(CenarioGenerico generico, Desafio pd,Goku goku,Inventario inventario, Aviso av,String base,Aluno aluno ){
		this.goku = goku;
		this.aviso = av;
		this.desafio = pd;
		this.generico = generico;
		this.base = base;
		pInfo = inventario;
		this.aluno = aluno;
        encolherBase();
		num = randon.nextInt(perguntas.size());
		per = new Perguntas(perguntas.get(num));
		this.mAdapter = new MAdapter();
		this.desafio.getPergunta().setText(perguntas.get(num));
		this.desafio.getOp1().addMouseListener(mAdapter);
		this.desafio.getOp2().addMouseListener(mAdapter);
		this.desafio.getOp3().addMouseListener(mAdapter);
		this.desafio.getOp4().addMouseListener(mAdapter);
		sortear(num);//sortea butao que contera a resposta
	}
	private class MAdapter extends MouseAdapter{

		public void mouseClicked(MouseEvent e) {

			if(e.getSource() == desafio.getOp1()){

				desafio.getOp1().setBorder(desafio.bordaMudada);
				desafio.getOp1().setBackground(Color.YELLOW);
				desafio.click.tocarSom();
				
				if(desafio.getOp1().getText().equals(respostas.get(num))){ 
					per.getRespostas().add(respostas.get(num));
					aluno.getPerguntas().add(per);
					num = randon.nextInt(perguntas.size());//sorteia nova pergunta
					desafio.getPergunta().setText(perguntas.get(num));
					per = new Perguntas(perguntas.get(num));
					sortear(num);
					destrava++;
				}else{
					per.getRespostas().add(desafio.getOp1().getText());//resposta errada
					sortear(num);
					goku.setLife((int)goku.getLife() + 360*3/100);//goku perde 10% life;
				}
			}
			else if(e.getSource() == desafio.getOp2()){

				desafio.getOp2().setBorder(desafio.bordaMudada);
				desafio.getOp2().setBackground(Color.YELLOW);
				desafio.click.tocarSom();	

				if(desafio.getOp2().getText().equals(respostas.get(num))){

					per.getRespostas().add(respostas.get(num));
					aluno.getPerguntas().add(per);
					num = randon.nextInt(perguntas.size());//sorteia nova pergunta
					desafio.getPergunta().setText(perguntas.get(num));
					per = new Perguntas(perguntas.get(num));
					sortear(num);
					destrava++;

				}else{
					per.getRespostas().add(desafio.getOp2().getText());//adiciona resposta errada
					sortear(num);
					goku.setLife((int)goku.getLife() + 360*3/100);//goku perde 10% life;
				}
			}
			else if(e.getSource() == desafio.getOp3()){
				desafio.getOp3().setBorder(desafio.bordaMudada);
				desafio.getOp3().setBackground(Color.YELLOW);
				desafio.click.tocarSom();

				if(desafio.getOp3().getText().equals(respostas.get(num))){
					per.getRespostas().add(respostas.get(num));//adiciona resposta certa
					aluno.getPerguntas().add(per);
					num = randon.nextInt(perguntas.size());//sorteia nova pergunta
					desafio.getPergunta().setText(perguntas.get(num));
					per = new Perguntas(perguntas.get(num));
					sortear(num);//soteia butao com resposta
					destrava++;
				}else{
					per.getRespostas().add(desafio.getOp3().getText());//resposta errada
					sortear(num);
					goku.setLife((int)goku.getLife() + 360*3/100);//goku perde 3% life;
					//resposta errada.
				}
			}
			else if(e.getSource() == desafio.getOp4()){
				desafio.getOp4().setBorder(desafio.bordaMudada);
				desafio.getOp4().setBackground(Color.YELLOW);
				desafio.click.tocarSom();

				if(desafio.getOp4().getText().equals(respostas.get(num))){
					per.getRespostas().add(respostas.get(num));
					aluno.getPerguntas().add(per);
					num = randon.nextInt(perguntas.size());//sorteia nova pergunta
					desafio.getPergunta().setText(perguntas.get(num));
					per = new Perguntas(perguntas.get(num));
					sortear(num);
					destrava++;
				}else{
					per.getRespostas().add(desafio.getOp4().getText());
					sortear(num);
					goku.setLife((int)goku.getLife() + 360*3/100);//goku perde 3% life;
					//resposta errada.
				}
			}
			if(destrava>=3){//Passou.
				//como esse controler serve pro desafio tanto da fase1 quanto da fase 2, entao precisa - se indentificar qual eh a fase que chamou
				if(generico instanceof Fase1){
					Fase1 soma = (Fase1) generico;
					if(soma.getFormaEsfera() == null){
						//isso eh até criar novas fases;
					if(pInfo.mostraE3 == true)
						pInfo.mostraE4 = true;
					else if(pInfo.isMostraE1() == true)
						pInfo.mostraE3 = true;
						
						pInfo.setMostraE1(true);//da primeira vez ja é pra mostrar e1
						
						SimulandoJOptionPane.mostrarMensagem();
					}
					soma.getPd().setVisible(false);
				}
				else if(generico instanceof Fase2){
					Fase2 sub = (Fase2) generico;
					if(sub.getFormaEsfera()==null){
						pInfo.setMostraE2(true);
						SimulandoJOptionPane.mostrarMensagem();
					}else
						aviso.setStr("Restam "+sub.getCapsulas().size()+" Capsulas");
					sub.getPd().setVisible(false);
				}
				generico.setVisible(true);
				generico.setPaint(true);
				generico.requestFocus();

			}else
				aviso.setStr("Acerte mais "+(3-destrava)+" para sair!");
			pInfo.repaint();
			//gravar aki;
			if((GravadorDeAluno.gravarAluno(aluno))){//grava o aluno atualizando as perguntas
				System.out.println("Aluno gravado Com Sucesso");
			}
		}
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == desafio.getOp1()){

				desafio.getOp1().setBorder(desafio.bordaMudada);
				desafio.transicao.tocarSom();
				desafio.getOp1().setBackground(Color.red);

			}
			else if(e.getSource() == desafio.getOp2()){

				desafio.getOp2().setBorder(desafio.bordaMudada);
				desafio.transicao.tocarSom();
				desafio.getOp2().setBackground(Color.red);

			}
			else if(e.getSource() == desafio.getOp3()){

				desafio.getOp3().setBorder(desafio.bordaMudada);
				desafio.transicao.tocarSom();
				desafio.getOp3().setBackground(Color.red);

			}
			else if(e.getSource() == desafio.getOp4()){

				desafio.getOp4().setBorder(desafio.bordaMudada);
				desafio.transicao.tocarSom();
				desafio.getOp4().setBackground(Color.red);

			}

		}
		public void mouseExited(MouseEvent e) {

			if(e.getSource() == desafio.getOp1()){

				desafio.getOp1().setBorder(desafio.bordaPadrao);
				desafio.getOp1().setBackground(Color.black);

			}
			else if(e.getSource() == desafio.getOp2()){

				desafio.getOp2().setBorder(desafio.bordaPadrao);
				desafio.getOp2().setBackground(Color.black);

			}
			else if(e.getSource() == desafio.getOp3()){
				desafio.getOp3().setBorder(desafio.bordaPadrao);
				desafio.getOp3().setBackground(Color.black);
			}
			else if(e.getSource() == desafio.getOp4()){
				desafio.getOp4().setBorder(desafio.bordaPadrao);
				desafio.getOp4().setBackground(Color.black);
			}
		}
	}
	public void sortear(int num){
		//sorteia a pergunta que conterá a resposta certa
		//if(soma) AArayList Pergunta, arrayList resposta;
		Random random = new Random();
		int n = random.nextInt(4);
		int nS = random.nextInt(10);
		if(n == 0){//entao op1 tera a resposta certa
			this.desafio.getOp1().setText(respostas.get(num));
			this.desafio.getOp2().setText((Integer.parseInt(respostas.get(num)))-1+" ");
			this.desafio.getOp3().setText((Integer.parseInt(respostas.get(num)))+1+" ");
			this.desafio.getOp4().setText((Integer.parseInt(respostas.get(num)))+random.nextInt(3)+2+" ");
		}
		else if(n ==1){
			this.desafio.getOp2().setText(respostas.get(num));
			this.desafio.getOp3().setText((Integer.parseInt(respostas.get(num)))-1+" ");
			this.desafio.getOp1().setText((Integer.parseInt(respostas.get(num)))+1+" ");
			this.desafio.getOp4().setText((Integer.parseInt(respostas.get(num)))+random.nextInt(3)+2+" ");
		}
		else if(n == 2){
			
			this.desafio.getOp3().setText(respostas.get(num));
			this.desafio.getOp2().setText((Integer.parseInt(respostas.get(num)))-1+" ");
			this.desafio.getOp1().setText((Integer.parseInt(respostas.get(num)))+1+" ");
			this.desafio.getOp4().setText((Integer.parseInt(respostas.get(num)))+random.nextInt(3)+2+" ");
		}
		else if(n==3){
			this.desafio.getOp4().setText(respostas.get(num));
			this.desafio.getOp2().setText((Integer.parseInt(respostas.get(num)))-1+" ");
			this.desafio.getOp3().setText((Integer.parseInt(respostas.get(num)))+1+" ");
			this.desafio.getOp1().setText((Integer.parseInt(respostas.get(num)))+random.nextInt(3)+2+" ");

		}
	}
	public void encolherBase(){
		
		if(base.equalsIgnoreCase("Soma")){
			perguntas = BaseDados.getPerguntasSoma();
			respostas = BaseDados.getRespostasSoma();
		}
		else if(base.equalsIgnoreCase("Subtracao")){
			perguntas = BaseDados.getPerguntasSub();
			respostas = BaseDados.getRespostasSub();
		}
		else if(base.equalsIgnoreCase("Multiplicacao")){
			perguntas = BaseDados.getPerguntasMult();
			respostas = BaseDados.getRespostasMult();
		}
		else if(base.equalsIgnoreCase("Divisao")){
			perguntas = BaseDados.getPerguntasDiv();
			respostas = BaseDados.getRespostasDiv();
		}
	}
}
