package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.thoughtworks.xstream.XStream;

public class GravadorDeAluno {
	
	public static boolean gravarAluno(Aluno aluno){
		
		File dir = new File("AquivoAlunosXML//");
		if(dir.exists() == false)
			dir.mkdirs();//cria o diretorio
		File arq = new File("AquivoAlunosXML//"+aluno.getNome().replace(" ","_")+".xml");
		
		try {
			if(arq.exists() == false)
				arq.createNewFile();
			FileWriter gravador = new FileWriter(arq);
			BufferedWriter bf = new BufferedWriter(gravador);
			XStream stream = new XStream();
			stream.alias(aluno.getNome().replace(" ","_"),Aluno.class);
			stream.aliasField("Perguntas",Aluno.class,"perguntas");
			stream.alias("Pergunta",Perguntas.class);
			stream.alias("resposta",String.class);
			stream.aliasField("pergunta_feita",Perguntas.class,"nomePergunta");
			bf.write(stream.toXML(aluno));
			bf.flush();
			bf.close();
			gravador.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static boolean verificaExistencia(String nomeArq){
		File file = new File("AquivoAlunosXML//"+nomeArq.replace(" ", "_")+".xml");
		if(file.exists())
			return true;
		return false;
	}
}
