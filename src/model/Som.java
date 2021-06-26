package model;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JLayer;

public class Som {
	private Clip som;
	public Som(String diretorio){
		
		try {
			 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(diretorio).getAbsoluteFile());
	         som = AudioSystem.getClip();
	         som.open(audioInputStream);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
	public void tocarSom(){
		 som.start();
	}
	public void somParar(){
		som.stop();
	}
	public void somEmLoop(boolean loop){
		if(loop == true)
			som.loop(Clip.LOOP_CONTINUOUSLY);
		else
			som.stop();
	}
}