import java.awt.Image;
import javax.swing.*;


public class Player extends JLabel {
	Stage1 st1;
	Stage2 st2;
	
	Player(Stage1 st1){
		this.st1 = st1;
		
		//�̹��� ������ ����
		ImageIcon p_img = new ImageIcon("player.png");  //�̹��� ����
		Image changeImgS = p_img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		p_img = new ImageIcon(changeImgS);
		
		this.setSize(p_img.getIconWidth(),p_img.getIconHeight());  //�̹��� ũ�⸸ŭ �÷��̾�(���̺�)ũ�� ����
		this.setIcon(p_img);
		this.setLocation(275,310); //�÷��̾� �ʱ� ��ġ
		st1.add(this);

	}
	Player(Stage2 st2){
		this.st2 = st2;
		
		//�̹��� ������ ����
		ImageIcon p_img = new ImageIcon("player.png");  //�̹��� ����
		Image changeImgS = p_img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	    p_img = new ImageIcon(changeImgS);
		
		this.setSize(p_img.getIconWidth(),p_img.getIconHeight());  //�̹��� ũ�⸸ŭ �÷��̾�(���̺�)ũ�� ����
		this.setIcon(p_img);
		this.setLocation(275,310);//�÷��̾� �ʱ� ��ġ
		st2.add(this);

	}

}
