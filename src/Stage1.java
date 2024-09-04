import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Stage1 extends JPanel{
	MainFrame mainF; 
	Stage1 panel = this;
	
	Player player;
	int playerLife = 5;

	JLabel bullet;
	BulletThread bulletThread;
	CreatTargetThread cTargetThread;
	
	Target[] target;
	JLabel score;
	JLabel targetLeft;
	JLabel life;
	
	Image img;
	
	Music bgm = new Music();
	
	
	private Font font = new Font("DungGeunMo",Font.PLAIN,20);  //��Ʈ
	
	
	
	
	int i =0;  //���� 
	int tL = 50; //���� ���� ��
	
	
	
	//���ȭ�� �׸���
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(img, 0, 0,600,370, null);
    }
	
    //��������1 ������
	public Stage1(MainFrame mainFrame){
		
		//������� & �Ѿ� �߻���
		bgm.playBgm(new File("stage1.wav"),1,true);
		File bulletFire = new File("bulletFire.wav");
		
		
		mainF = mainFrame;
		bulletThread = null;
		this.startGame();
		

						
		this.setLayout(null); //�����ġ
		
		//����̹���
		try {
			img = ImageIO.read(new File("stageBG.png"));
	      }catch(IOException e) {
	         e.printStackTrace();
	      };
    	
		//�¿� ����Ű�� �÷��̾� �̵�, �����̽��ٷ� �Ѿ� �߻�
	      this.addKeyListener(new KeyListener(){
				
	        	public void keyPressed(KeyEvent e) {
	        		int keycode = e.getKeyCode();

	        		switch(keycode) {
	        		case KeyEvent.VK_LEFT: if(player.getX() > 20) {
	        			player.setLocation(player.getX()-20,310);
	        			} break;
	        		case KeyEvent.VK_RIGHT: if(player.getX() < 510) {
	        			player.setLocation(player.getX()+20,310);	        			
	        			} break;
	        			
	        		case KeyEvent.VK_SPACE: 	        			
	        			//�Ѿ� �����尡 �׾��ִ� �������� Ȯ��(null �Ǵ� isAlive()�� false) 	 
	        			if(bulletThread==null || !bulletThread.isAlive()){
	        				new Music().playSound(bulletFire, 1, false); //�Ѿ� �߻� ȿ����
	        				bullet = new Bullet(panel); //�� �Ѿ� ��ü ����
	        				    	                    
    	                    bulletThread = new BulletThread(panel); //�Ѿ��� �����̴� ������ ����
    	                    bulletThread.start();    	                       	                    
	        			}break;
	        		  }
	        		}
				public void keyTyped(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {					
				}
	        });
	      
	      
	      }	      	      	    	 
    
	
	//��������1 ���� �޼ҵ�
    public void startGame(){    	
    	
        player = new Player(this);      
        target = new Target[50]; //Ÿ�� �迭 ����
                
        //���̽��� ������ �ΰ� ����Ű �Է¿� ���� bullet������ ����
        player.requestFocus();
        
        //�������̺� ����
        score = new JLabel("SCORE "+ i + " / 5");
        score.setSize(250,20);
        score.setFont(font);
        score.setForeground(Color.white);
        score.setLocation(450,10);                            
        this.add(score);
        
        JLabel targetLImg = new JLabel();
        ImageIcon remainTImg = new ImageIcon("greenMonster.png");  //�̹��� ����
		Image changeImgS = remainTImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		remainTImg = new ImageIcon(changeImgS);
		
		targetLImg.setSize(remainTImg.getIconWidth(),remainTImg.getIconHeight());  //�̹��� ũ�⸸ŭ (���̺�)ũ�� ����
		targetLImg.setIcon(remainTImg);
		targetLImg.setLocation(470,70);
		this.add(targetLImg);
                                        
        
        //���� ���� �� ���̺� ����
        targetLeft = new JLabel("   " + tL);
        targetLeft.setSize(250,20);
        targetLeft.setFont(font);
        targetLeft.setForeground(Color.white);
        targetLeft.setLocation(490,70);
        this.add(targetLeft);
        
        //��� ���̺� ����
        life = new JLabel("heart  ����������");
        life.setSize(250,20);
        life.setFont(font);
        life.setForeground(Color.white);
        life.setLocation(450,40);
        this.add(life);
        
        //Ÿ�� ���� ������ 
        cTargetThread = new CreatTargetThread(this);
        cTargetThread.start();	  		    
                               
    }                               
}
