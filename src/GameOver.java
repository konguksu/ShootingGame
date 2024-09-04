import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameOver extends JPanel {
	MainFrame mainF;

	private JButton tryAgain;
	private Image background;
	
	File gameOver;
	Music bgm;
	
	public GameOver(MainFrame mainFrame) {
		mainF = mainFrame;
		Font font1 = new Font("DungGeunMo",Font.PLAIN,20);
		
		//사운드 관련
		bgm = new Music();
		gameOver = new File("gameOver.wav");		
		bgm.playBgm(gameOver, 1, true);
		
		this.setLayout(null);
		
		try {
	         background = ImageIO.read(new File("gameOver.png"));
	      }catch(IOException e) {
	         e.printStackTrace();
	      }
		
		tryAgain = new JButton("TRY AGAIN?");
		tryAgain.setFont(font1);
		tryAgain.setBounds(220, 270, 150, 50);
		tryAgain.setFocusPainted(false);
		tryAgain.setForeground(Color.white); //글자 색 -> 흰색
		tryAgain.setBorderPainted(false); //테두리 투명
		tryAgain.setContentAreaFilled(false); //채우기 투명
		
		tryAgain.addActionListener(e->{
			bgm.stopBgm();
			mainF.toStartPage(this);
		});
		
		this.add(tryAgain);
	}
	
	 protected void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      g.drawImage(background,0,0,600,370,null);
	   }

}
