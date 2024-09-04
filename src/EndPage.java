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
		
		//������� & �Ѿ� �߻���
		bgm.playBgm(new File("gameClear.wav"),1,true);

		this.setLayout(null);//��ġ������
		
		//����̹���
		try {
	         background = ImageIO.read(new File("gameClear.png"));
	      }catch(IOException e) {
	         e.printStackTrace();
	      }

		
		//��ư ����
		toStart = new JButton("PLAY AGAIN");
		toStart.setFont(font1);		
		toStart.setBounds(220, 270, 150, 50);
		toStart.setFocusPainted(false);	
		toStart.setForeground(Color.white); //���� �� -> ���
		toStart.setBorderPainted(false); //�׵θ� ����
		toStart.setContentAreaFilled(false); //ä��� ����
		
		
		//��ư ��� �߰�
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
