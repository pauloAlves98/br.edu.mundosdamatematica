package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class CarregadorDeImagem {//model ou Controler
	
	public synchronized static BufferedImage carregar(String file){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Erro ao Carregar Imagem neste Endereco:\n"+file);
			e.printStackTrace();
			System.exit(0);
		}
		return image;
	}
}
