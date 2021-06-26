package model;

import java.util.ArrayList;

public class Aluno {
	private String nome;
	private ArrayList<Perguntas>perguntas;
	
	public Aluno(String nome){
		this.nome = nome;
		perguntas = new ArrayList<Perguntas>();
	}
	public Aluno(String nome, ArrayList<Perguntas> perguntas) {
		super();
		this.nome = nome;
		this.perguntas = perguntas;
	}
	public Aluno() {
		nome = " ";
		perguntas = new ArrayList<Perguntas>();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<Perguntas> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(ArrayList<Perguntas> perguntas) {
		this.perguntas = perguntas;
	}
	
}
