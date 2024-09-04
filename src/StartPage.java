import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class StartPage extends JPanel{
	MainFrame mainF;
	
	private JButton startgame;
	private JButton rules;
	private Image background;
	File file;
	private Music bgm;
	
	
	
	//스타트 페이지 생성자
	public StartPage(MainFrame mainFrame) {
		bgm = new Music();
		file = new File("music_start.wav");
		bgm.playBgm(file,1,true);
		
		
		mainF = mainFrame;
		Font font1 = new Font("DungGeunMo",Font.PLAIN,20);
		//Font font2 = new Font("DungGeunMo",Font.PLAIN,15);
				
    	

		this.setLayout(null);
		try {
	         background = ImageIO.read(new File("startPage.png"));
	      }catch(IOException e) {
	         e.printStackTrace();
	      }

		
		//버튼 생성
		startgame = new JButton("START GAME");
		startgame.setFont(font1);
		startgame.setForeground(Color.white); //글자 색 -> 흰색
		startgame.setBounds(220, 220, 150, 40);
		startgame.setFocusPainted(false);
		startgame.setBorderPainted(false); //테두리 투명
		startgame.setContentAreaFilled(false); //채우기 투명
		
		rules = new JButton("how to play");
		rules.setFont(font1);
		rules.setForeground(Color.white); //글자 색 -> 흰색
		rules.setBounds(220,180,150,40);
		rules.setFocusPainted(false);
		rules.setBorderPainted(false); //테두리 투명
		rules.setContentAreaFilled(false); //채우기 투명
		
		
		//버튼기능추가(startgame - 스테이지1화면으로 / rules - 규칙 화면으로)
		startgame.addActionListener(e->{
			bgm.stopBgm();
			mainF.toStage1(this);			
		});
		rules.addActionListener(e -> {
			bgm.stopBgm();
			mainF.toRules(this);			
		});
		
		this.add(startgame);
		this.add(rules);

	}
	
	   protected void paintComponent(Graphics g) {
		      super.paintComponent(g);
		      g.drawImage(background,0,0,600,370,null);
		   }




}
