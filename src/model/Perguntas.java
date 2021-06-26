package model;

import java.util.ArrayList;

public class Perguntas {
	private String nomePergunta;
	private ArrayList<String>respostas = new ArrayList<String>();
	
	public Perguntas(){}
	public Perguntas(String nome){
		this.nomePergunta = nome;
	}
	public Perguntas(String nome, ArrayList<String> respostas) {
		super();
		this.nomePergunta = nome;
		this.respostas = respostas;
	}
	public String getNomePergunta() {
		return nomePergunta;
	}
	public void setNomePergunta(String nome) {
		this.nomePergunta = nome;
	}
	public ArrayList<String> getRespostas() {
		return respostas;
	}
	public void setRespostas(ArrayList<String> respostas) {
		this.respostas = respostas;
	}
	
	
}
