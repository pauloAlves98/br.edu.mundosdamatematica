package model;

import java.util.ArrayList;
import java.util.Random;

public class BaseDados {
	
	private static ArrayList<String>perguntasSoma = new ArrayList<String>();
	private static ArrayList<String>respostasSoma = new ArrayList<String>();
	private static ArrayList<String>respostasSub = new ArrayList<String>();
	private static ArrayList<String>perguntasSub = new ArrayList<String>();
	private static ArrayList<String>perguntasMult = new ArrayList<String>();
	private static ArrayList<String>respostasMult = new ArrayList<String>();
	private static ArrayList<String>perguntasDiv = new ArrayList<String>();
	private static ArrayList<String>respostasDiv = new ArrayList<String>();
	
	public static void adicionarPerguntaSoma(String pergunta){
		perguntasSoma.add(pergunta);
	}
	public static void adicionarRespostaSoma(String resposta) {
		respostasSoma.add(resposta);
	}
	public static void adicionarPerguntaSub(String pergunta){
		perguntasSub.add(pergunta);
	}
	public static void adicionarPerguntaMult(String pergunta){
		perguntasMult.add(pergunta);
	}

	public static void adicionarRespostaSub(String resposta) {
		respostasSub.add(resposta);
	}
	public static void adicionarRespostaMult(String resposta) {
		respostasMult.add(resposta);
	}
	public static void adicionarRespostaDiv(String resposta) {
		respostasDiv.add(resposta);
	}
	public static void adicionarPerguntaDiv(String pergunta) {
		perguntasDiv.add(pergunta);
	}
	
	public static ArrayList<String> getPerguntasSoma() {
		return perguntasSoma;
	}
	public static void setPerguntasSoma(ArrayList<String> perguntasSoma) {
		BaseDados.perguntasSoma = perguntasSoma;
	}
	public static ArrayList<String> getRespostasSoma() {
		return respostasSoma;
	}
	public static void setRespostasSoma(ArrayList<String> respostasSoma) {
		BaseDados.respostasSoma = respostasSoma;
	}
	public static  void carregarBases(){
		
		for(int i = 0;i<100;i++){//carregar da base Excluindo 0da divisao , para dar certo
			int num1,num2;
			num1 = new Random().nextInt(10);
			num2 = new Random().nextInt(10);
			while(existe(""+num1+" + "+ num2+"",perguntasSoma) == true){
				num1 = new Random().nextInt(10);
				num2 = new Random().nextInt(10);
			}
			System.out.println("PERGUNTA:"+(i+1)+":"+num1+" + "+ num2+"");
			BaseDados.adicionarPerguntaSoma(""+num1+" + "+ num2+"");
			BaseDados.adicionarRespostaSoma((num1+num2)+"");
			
			while(existe(""+num1+" - "+ num2+"",perguntasSub) == true){
				num1 = new Random().nextInt(10);
				num2 = new Random().nextInt(10);
			}
			
			System.out.println("PERGUNTA:"+(i+1)+":"+num1+" - "+ num2+"");
			BaseDados.adicionarPerguntaSub(""+num1+" - "+ num2+"");
			BaseDados.adicionarRespostaSub((num1-num2)+"");
			
			while(existe(""+num1+" * "+ num2+"",perguntasMult) == true){
				num1 = new Random().nextInt(10);
				num2 = new Random().nextInt(10);
			}
			System.out.println("PERGUNTA:"+(i+1)+":"+num1+" * "+ num2+"");
			BaseDados.adicionarPerguntaMult(""+num1+" * "+ num2+"");
			BaseDados.adicionarRespostaMult((num1*num2)+"");
			
		}
		
		for(int i = 0;i<78;i++){//tirando a possililidade de 0 no denominador ou no denominador ou numerador rstam 80 possibilidades
			int num1,num2;
			
			num1 = new Random().nextInt(10);
			num2 = new Random().nextInt(10);
			
			int result = num1*num2;
			
			int divisor = 1;
			
			if(i %2 == 0)
				divisor = num1;
			else
				divisor = num2;

			while(existe(""+result+" ÷ "+divisor+"",perguntasDiv) == true || num1 ==0 || num2 ==0){
				num1 = new Random().nextInt(10);
				num2 = new Random().nextInt(10);
				 result = num1*num2;
				 divisor = 1;
				if(i %2 == 0)
					divisor = num1;
				else
					divisor = num2;
			}
//			while(existe(""+num1+" ÷ "+ num2+"",perguntasDiv) == true || (num2 == 0) || ((float)(num1/num2) % 2 != 0) || (num2 > num1)){
//				num1 = new Random().nextInt(20);
//				num2 = new Random().nextInt(16);
//			}
			System.out.println(""+result+" / "+ divisor+"");
			//System.out.println("PERGUNTA:"+(i+1)+":"+result+"\n______\n"+ divisor+"");
			//BaseDados.adicionarPerguntaDiv(""+result+" ÷ "+ divisor+"");
			BaseDados.adicionarPerguntaDiv(""+result+" / "+ divisor+"");
			BaseDados.adicionarRespostaDiv((result/divisor)+"");	
		}
	}
	private static boolean existe(String pergunta, ArrayList<String>perguntas){
		for(String per:perguntas){
			if(per.equalsIgnoreCase(pergunta))
				return true;
		}
		return false;
	}
	public static ArrayList<String> getRespostasSub() {
		return respostasSub;
	}
	public static ArrayList<String> getPerguntasSub() {
		return perguntasSub;
	}
	public static ArrayList<String> getPerguntasMult() {
		return perguntasMult;
	}
	public static ArrayList<String> getRespostasMult() {
		return respostasMult;
	}
	public static ArrayList<String> getPerguntasDiv() {
		return perguntasDiv;
	}
	public static ArrayList<String> getRespostasDiv() {
		return respostasDiv;
	}
	
}
