package model;

public class Detalhes implements Comparable<Detalhes> {
	private Tempo tempo;
	private String nome;
	
	public Detalhes(){}//o arquivo  solicitou um contrutor padrao
	public Detalhes(Tempo tempo,String nome){
		this.tempo = tempo;
		this.nome = nome;
	}
	@Override
	//pra ordenar com Collection.sort()
	public int compareTo(Detalhes e) {
		if (this.tempo.getTempoTotal() > e.tempo.getTempoTotal()) {
	          return 1;//manda pra direita na lista/fim
	     }
	     if (this.tempo.getTempoTotal() < e.tempo.getTempoTotal()) {
	          return -1;
	     }
		return 0;
	}
	public Tempo getTempo() {
		return tempo;
	}
	public String getNome() {
		return nome;
	}
	
}
