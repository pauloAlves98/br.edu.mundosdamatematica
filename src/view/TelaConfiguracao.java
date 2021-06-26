package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;

public class TelaConfiguracao extends JPanel{
	
	private PainelConfig pConfig;
	private JSlider slider;
	private JLabel fundo,info;
	private JButton voltarButton;
	private boolean rodando  = true;
	
	public TelaConfiguracao(){
		
		setSize(500,500);
		setLayout(null);
		pConfig = new PainelConfig();
		slider = new JSlider(0,100);
		fundo = new JLabel(new ImageIcon("Outros//telaConfig.png"));
		info = new JLabel(new ImageIcon("Menu//velocidadeJogo.png"));
		voltarButton = new JButton(new ImageIcon("Menu//voltar.jpg"));
		
		add(info);
		add(voltarButton);
		add(pConfig);
		add(slider);
		add(fundo);
		
		//slider.setBackground(Color.BLACK);
		
		slider.setForeground(Color.BLACK);
	    slider.setOpaque(false);
		pConfig.setOpaque(false);
		
		slider.setMajorTickSpacing(10);// de 10 em 10
		slider.setMinorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setPaintTrack(true);
		slider.setValue(22);
		
		fundo.setBounds(0, 0, 500,500);
		info.setBounds(10,-2,200,100);
		pConfig.setBounds(0,50,500,150);
		slider.setBounds(0,201,490,50);
		voltarButton.setBorder(new LineBorder(Color.GREEN, 3, false));
		voltarButton.setBounds(200,300, 100, 40);
		
		setVisible(false);
	}

	public PainelConfig getpConfig() {
		return pConfig;
	}

	public void setpConfig(PainelConfig pConfig) {
		this.pConfig = pConfig;
	}

	public JSlider getSlider() {
		return slider;
	}

	public void setSlider(JSlider slider) {
		this.slider = slider;
	}

	public JLabel getFundo() {
		return fundo;
	}

	public void setFundo(JLabel fundo) {
		this.fundo = fundo;
	}

	public JLabel getInfo() {
		return info;
	}

	public void setInfo(JLabel info) {
		this.info = info;
	}
	public JButton getVoltarButton() {
		return voltarButton;
	}
	public void setVoltarButton(JButton voltarButton) {
		this.voltarButton = voltarButton;
	}
	public boolean isRodando() {
		return rodando;
	}
	public void setRodando(boolean rodando) {
		this.rodando = rodando;
	}
	
}
