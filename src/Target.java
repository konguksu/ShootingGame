import java.awt.Image;
import javax.swing.*;

public class Target extends JLabel{
	Stage1 st1;
	Stage2 st2;
	
	Target(Stage1 st1){
		this.st1 = st1;
		
		//이미지 사이즈 조정
		ImageIcon p_img = new ImageIcon("greenMonster.png");  //이미지 설정
		Image changeImgS = p_img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		p_img = new ImageIcon(changeImgS);
		
		this.setSize(p_img.getIconWidth(),p_img.getIconHeight());  //이미지 크기만큼(레이블)크기 설정
		this.setIcon(p_img);
		
		this.setLocation((int)(Math.random()*530),-10);//위치랜덤
		st1.add(this);		
	}
	Target(Stage2 st2){
		this.st2 = st2;
		
		//이미지 사이즈 조정
		ImageIcon p_img = new ImageIcon("blueMonster.png");  //이미지 설정
		Image changeImgS = p_img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		p_img = new ImageIcon(changeImgS);		
		
		this.setSize(p_img.getIconWidth(),p_img.getIconHeight());  //이미지 크기만큼(레이블)크기 설정
		this.setIcon(p_img);
		
		this.setLocation((int)(Math.random()*530),-10);//위치랜덤
		st2.add(this);
	}	

	

}
