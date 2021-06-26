package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

public class SalvarTempo {
	public synchronized static boolean GravarDetalhes(Detalhes detalhe){
		//ordena por tempo total de jogo implementei Comparable
		File dir = new File("arquivo");
		if(dir.exists() == false)
			dir.mkdirs();//cria o diretorio
		File arq = new File("arquivo//ranking.xml");
		try {
			ArrayList<Detalhes> detalhes = carregar();
			detalhes.add(detalhe);
			Collections.sort(detalhes);
			if(arq.exists() == false)
				arq.createNewFile();
			FileWriter gravador = new FileWriter(arq);
			BufferedWriter bf = new BufferedWriter(gravador);
			XStream stream = new XStream();
			stream.alias("Detalhes",ArrayList.class);
			stream.alias("Detalhe",Detalhes.class);
			bf.write(stream.toXML(detalhes));
			bf.flush();
			bf.close();
			gravador.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static ArrayList carregar(){
		File dir = new File("arquivo");
		if(dir.exists() == false)
			dir.mkdirs();//cria o diretorio
		File arq = new File("arquivo//ranking.xml");
		
		FileReader leitor = null;
		BufferedReader bf = null;
		XStream stream = null;
		ArrayList<Detalhes> array = null;
		try {
			if(!arq.exists())
				arq.createNewFile();
			if(arq.length()<=0)//arquivo esta vazio
				return new ArrayList<Detalhes>();//retorn arraylist vazio
			leitor = new FileReader(arq);
			bf = new BufferedReader(leitor);
			stream = new XStream(new Dom4JDriver());
			stream.alias("Detalhes",ArrayList.class);
			stream.alias("Detalhe",Detalhes.class);
			array = (ArrayList)stream.fromXML(bf);
			bf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}
}
