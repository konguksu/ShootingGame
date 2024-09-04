import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class EndPage extends JPanel {
	MainFrame mainF;
	
	private JButton toStart;
	private Image background;
	
	Music bgm = new Music();	
	
	public EndPage(MainFrame mainFrame) {
		mainF = mainFrame;
		Font font1 = new Font("DungGeunMo",Font.PLAIN,20);
		
		//배경음악 & 총알 발사음
		bgm.playBgm(new File("gameClear.wav"),1,true);

		this.setLayout(null);//배치관리자
		
		//배경이미지
		try {
	         background = ImageIO.read(new File("gameClear.png"));
	      }catch(IOException e) {
	         e.printStackTrace();
	      }

		
		//버튼 생성
		toStart = new JButton("PLAY AGAIN");
		toStart.setFont(font1);		
		toStart.setBounds(220, 270, 150, 50);
		toStart.setFocusPainted(false);	
		toStart.setForeground(Color.white); //글자 색 -> 흰색
		toStart.setBorderPainted(false); //테두리 투명
		toStart.setContentAreaFilled(false); //채우기 투명
		
		
		//버튼 기능 추가
		toStart.addActionListener(e->{
			bgm.stopBgm();
			mainF.toStartPage(this);
		});
		
		this.add(toStart);

	}
	
	   protected void paintComponent(Graphics g) {
		      super.paintComponent(g);
		      g.drawImage(background,0,0,600,370,null);
		   }

}
