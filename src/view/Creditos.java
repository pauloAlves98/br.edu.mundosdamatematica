package view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Creditos extends JPanel {
	JLabel fundo1,fundo2,fundo3;
	private  JButton voltarButton;
	
	public Creditos(){
		super.setLayout(null);
		setSize(500,500);
		init();
	    setVisible(false);
	}
	public void init(){
		Image i1,i2,i3;
		i1 = new ImageIcon("Outros//TelaCredito.png").getImage();
		i2  = new ImageIcon("Outros//fundoCredito02.jpg").getImage();
		i3 = new ImageIcon("Outros//fundoCredito01.gif").getImage();
		
		fundo1 = new JLabel(new ImageIcon(i1.getScaledInstance(500,500,8)));
		fundo1.setBounds(0,0,500,500);
		fundo2 =  new JLabel(new ImageIcon(i2.getScaledInstance(500,500,8)));
		fundo2.setBounds(0,0,500,500);
		fundo3 =  new JLabel(new ImageIcon(i3.getScaledInstance(500,500,8)));
		fundo3.setBounds(0,0,500,500);
		
		voltarButton = new JButton(new ImageIcon("Menu//voltar.jpg"));
		voltarButton.setBorder(new LineBorder(Color.GREEN, 3, false));
		voltarButton.setBounds(390, 415, 100, 40);
		
		add(voltarButton);
		add(fundo1);
		add(fundo3);
	    add(fundo2);
	}
	public JButton getVoltarButton() {
		return voltarButton;
	}
	public void setVoltarButton(JButton voltarButton) {
		this.voltarButton = voltarButton;
	}

}
