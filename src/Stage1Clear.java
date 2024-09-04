import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Stage1Clear extends JPanel {
	MainFrame mainF;

	private JButton toStage2;
	private Image background;
	
	File stageClear;
	Music bgm;
	
	public Stage1Clear(MainFrame mainFrame) {
		mainF = mainFrame;
		Font font1 = new Font("DungGeunMo",Font.PLAIN,20);
		
		//���� ����
		bgm = new Music();
		stageClear = new File("stageClear.wav");
		bgm.playBgm(stageClear, 1, false);
		
		this.setLayout(null);//��ġ������
		
		//����̹���
		try {
	         background = ImageIO.read(new File("stageClear.png"));
	      }catch(IOException e) {
	         e.printStackTrace();
	      }
		
		//��ư
		toStage2 = new JButton("NEXT STAGE");
		toStage2.setFont(font1);
		toStage2.setBounds(220, 270, 150, 50);
		toStage2.setFocusPainted(false);
		toStage2.setForeground(Color.white); //���� �� -> ���
		toStage2.setBorderPainted(false); //�׵θ� ����
		toStage2.setContentAreaFilled(false); //ä��� ����
		
		//��ư ��� �߰�
		toStage2.addActionListener(e->{
			bgm.stopBgm();
			mainF.toStage2(this);
		});
		
		this.add(toStage2);
	}
	
	 protected void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      g.drawImage(background,0,0,600,370,null);
	   }
	
}
