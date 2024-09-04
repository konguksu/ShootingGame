import java.awt.Image;
import javax.swing.*;

public class Target extends JLabel{
	Stage1 st1;
	Stage2 st2;
	
	Target(Stage1 st1){
		this.st1 = st1;
		
		//�̹��� ������ ����
		ImageIcon p_img = new ImageIcon("greenMonster.png");  //�̹��� ����
		Image changeImgS = p_img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		p_img = new ImageIcon(changeImgS);
		
		this.setSize(p_img.getIconWidth(),p_img.getIconHeight());  //�̹��� ũ�⸸ŭ(���̺�)ũ�� ����
		this.setIcon(p_img);
		
		this.setLocation((int)(Math.random()*530),-10);//��ġ����
		st1.add(this);		
	}
	Target(Stage2 st2){
		this.st2 = st2;
		
		//�̹��� ������ ����
		ImageIcon p_img = new ImageIcon("blueMonster.png");  //�̹��� ����
		Image changeImgS = p_img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		p_img = new ImageIcon(changeImgS);		
		
		this.setSize(p_img.getIconWidth(),p_img.getIconHeight());  //�̹��� ũ�⸸ŭ(���̺�)ũ�� ����
		this.setIcon(p_img);
		
		this.setLocation((int)(Math.random()*530),-10);//��ġ����
		st2.add(this);
	}	

	

}
