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
	
	
	private Font font = new Font("DungGeunMo",Font.PLAIN,20);  //폰트
	
	
	
	
	int i =0;  //점수 
	int tL = 50; //남은 적의 수
	
	
	
	//배경화면 그리기
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(img, 0, 0,600,370, null);
    }
	
    //스테이지1 생성자
	public Stage1(MainFrame mainFrame){
		
		//배경음악 & 총알 발사음
		bgm.playBgm(new File("stage1.wav"),1,true);
		File bulletFire = new File("bulletFire.wav");
		
		
		mainF = mainFrame;
		bulletThread = null;
		this.startGame();
		

						
		this.setLayout(null); //절대배치
		
		//배경이미지
		try {
			img = ImageIO.read(new File("stageBG.png"));
	      }catch(IOException e) {
	         e.printStackTrace();
	      };
    	
		//좌우 방향키로 플레이어 이동, 스페이스바로 총알 발사
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
	        			//총알 스레드가 죽어있는 상태인지 확인(null 또는 isAlive()가 false) 	 
	        			if(bulletThread==null || !bulletThread.isAlive()){
	        				new Music().playSound(bulletFire, 1, false); //총알 발사 효과음
	        				bullet = new Bullet(panel); //새 총알 객체 생성
	        				    	                    
    	                    bulletThread = new BulletThread(panel); //총알을 움직이는 스레드 생성
    	                    bulletThread.start();    	                       	                    
	        			}break;
	        		  }
	        		}
				public void keyTyped(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {					
				}
	        });
	      
	      
	      }	      	      	    	 
    
	
	//스테이지1 시작 메소드
    public void startGame(){    	
    	
        player = new Player(this);      
        target = new Target[50]; //타겟 배열 생성
                
        //베이스에 초점을 두고 엔터키 입력에 따라 bullet스레드 실행
        player.requestFocus();
        
        //점수레이블 설정
        score = new JLabel("SCORE "+ i + " / 5");
        score.setSize(250,20);
        score.setFont(font);
        score.setForeground(Color.white);
        score.setLocation(450,10);                            
        this.add(score);
        
        JLabel targetLImg = new JLabel();
        ImageIcon remainTImg = new ImageIcon("greenMonster.png");  //이미지 설정
		Image changeImgS = remainTImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		remainTImg = new ImageIcon(changeImgS);
		
		targetLImg.setSize(remainTImg.getIconWidth(),remainTImg.getIconHeight());  //이미지 크기만큼 (레이블)크기 설정
		targetLImg.setIcon(remainTImg);
		targetLImg.setLocation(470,70);
		this.add(targetLImg);
                                        
        
        //남은 적의 수 레이블 설정
        targetLeft = new JLabel("   " + tL);
        targetLeft.setSize(250,20);
        targetLeft.setFont(font);
        targetLeft.setForeground(Color.white);
        targetLeft.setLocation(490,70);
        this.add(targetLeft);
        
        //목숨 레이블 설정
        life = new JLabel("heart  ♥♥♥♥♥");
        life.setSize(250,20);
        life.setFont(font);
        life.setForeground(Color.white);
        life.setLocation(450,40);
        this.add(life);
        
        //타겟 생성 스레드 
        cTargetThread = new CreatTargetThread(this);
        cTargetThread.start();	  		    
                               
    }                               
}
