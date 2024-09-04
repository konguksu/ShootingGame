import java.awt.Image;
import javax.swing.*;

public class Bullet extends JLabel{
	Stage1 st1;
	Stage2 st2;
		
	
	Bullet(Stage1 st1){
		this.st1 = st1;
		
		//�̹��� ������ ����
		ImageIcon p_img = new ImageIcon("bullet1.png");  //�̹��� ����
		Image changeImgS = p_img.getImage().getScaledInstance(18, 30, Image.SCALE_SMOOTH);
		p_img = new ImageIcon(changeImgS);
		
		this.setSize(p_img.getIconWidth(),p_img.getIconHeight());  //�̹��� ũ�⸸ŭ �Ѿ�(���̺�)ũ�� ����
		this.setIcon(p_img);
		this.setLocation(st1.player.getX()+6, st1.player.getY());
		st1.add(this);

	}
	
	Bullet(Stage2 st2){
		this.st2 = st2;
		
		//�̹��� ������ ����
		ImageIcon p_img = new ImageIcon("bullet2.png");  //�̹��� ����
		Image changeImgS = p_img.getImage().getScaledInstance(18, 35, Image.SCALE_SMOOTH);
		p_img = new ImageIcon(changeImgS);
		
		this.setSize(p_img.getIconWidth(),p_img.getIconHeight());  //�̹��� ũ�⸸ŭ �Ѿ�(���̺�)ũ�� ����
		this.setIcon(p_img);
		this.setLocation(st2.player.getX()+6, st2.player.getY());
		st2.add(this);

	}

}
