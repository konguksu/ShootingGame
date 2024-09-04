import java.io.File;
import javax.swing.JLabel;

public class TargetThread2 extends Thread{
    JLabel target;
    Stage2 st2;
    File playerCrash,bullet_target_hit ; //�÷��̾� �浹 ȿ����, Ÿ�� ���� ȿ����
    
    TargetThread2(JLabel target, Stage2 st2){
        this.target=target;
        this.st2 = st2;
        playerCrash = new File("player_target_crash.wav");
        bullet_target_hit = new File("bullet_target_hit.wav");
    }
    
    
    public void run(){
        while(true){
        	//Ÿ�� - �÷��̾� �ε���
        	if(playerHit()) {
        		System.out.println("�÷��̾� �浹");
        		new Music().playSound(playerCrash, 1.0f, false); //�浹 ȿ����
        		st2.playerLife--;
        		st2.tL--;//���� ���� �� ����
        		st2.remove(target); //�гο��� Ÿ�� �����
        		st2.repaint();
            	st2.revalidate();
            	target = null; //Ÿ�� ����
            	
            	//�÷��̾� ��� 0 -> ���ӿ���
        		if(st2.playerLife == 0) { 
        			st2.mainF.toGameOver(st2);
        			st2.bgm.stopBgm();
        			st2.cTargetThread.interrupt();  
        		}
        		
        		//�÷��̾� ��� ���� ->���� ȭ�鿡 ǥ��
        		else {
        			switch(st2.playerLife) {
        			case 4: st2.life.setText("heart  ����������");break; //ȭ�鿡 ���ҵ� ��� ǥ�� 
        			case 3: st2.life.setText("heart  ����������");break;
        			case 2: st2.life.setText("heart  ����������");break;
        			case 1: st2.life.setText("heart  ����������");break;
        			}
        			st2.targetLeft.setText("   " + st2.tL);//ȭ�鿡 ������ �� ǥ��
        		}
        		return;
        	}
       	
            
        	//�Ѿ��� �����ϰ� Ÿ���� �����
            if(st2.bullet !=null && hit()) { 
            	new Music().playSound(bullet_target_hit, 1, false); //���� ȿ���� ���
            	st2.bulletThread2.interrupt();//�Ѿ� �����带 ���δ�.
            	st2.remove(target); //�гο��� Ÿ�� �����
            	st2.remove(st2.bullet);// �гο��� �Ѿ� �����
            	st2.repaint();
            	st2.revalidate();
            	
            	target = null; //Ÿ�� ����
            	st2.bullet = null; //�Ѿ� ����
            	
            	st2.i++; // ���� ����            	
            	st2.tL--;//���� ���� �� ����
            	
            	//��ǥ ���� ����-> ���� Ŭ���� ȭ������ + Ÿ�� ���� ������ ����
            	if(st2.i>=10) {
            		st2.mainF.toEndPage(st2); //���� Ŭ���� ȭ������
                 	st2.bgm.stopBgm(); //��������2 ������� ����
                 	st2.cTargetThread.interrupt(); //Ÿ�ٻ��� ������ ����
                 }
            	//��ǥ ���� ���� ���� X + ���� ���� 0 -> ���� ����
				 else if (st2.i < 10 && st2.tL == 0)
				 {
					 st2.mainF.toGameOver(st2); //���ӿ���ȭ������
					 st2.bgm.stopBgm(); //��������1 ������� ����
	                 st2.cTargetThread.interrupt();	//Ÿ�ٻ��� ������ ����				 				 				 
				 }
            	//��ǥ ���� ���� ���� X + ���� �� ���� -> ȭ�鿡 ������ ���� + ���� �� ǥ��
				 else if(st2.i < 10 && st2.tL != 0) 
				 {
					 st2.score.setText("SCORE "+ st2.i + " / 10"); //ȭ�鿡 ������ ���� ǥ��
					 st2.targetLeft.setText("   " + st2.tL);//ȭ�鿡 ������ �� ǥ��
				 }
            	return;
            }
            //Ÿ���� ������ ���ҽ� -> Ÿ�� �����ϰ� �Ʒ��� �̵�
            else {
            	int x=target.getX();
                int y=target.getY()+7;//���ȼ��� �̵��Ұ�����(��������2 7�ȼ�)
                
            	 //������ ������ ������� Ÿ�� �гο��� �����&������ ����
                if(y>st2.getHeight()) {
                	st2.remove(target);//�гο��� Ÿ�� �����
                	st2.repaint();
                	st2.revalidate();
                	target = null;      //Ÿ�� ����
                	
                	//���� ���� �� ���� + ȭ�鿡 ǥ��
                	st2.tL--;
                	st2.targetLeft.setText("   " + st2.tL);
                	
                	//���� �� 0�Ǹ� ���� ����
                	if(st2.tL == 0) {
                     	st2.mainF.toGameOver(st2); //���ӿ���ȭ������
                     	st2.bgm.stopBgm(); //��������2 ������� ����
                     }
                	//���� �� 0 �ƴϸ� ���� �� �� ȭ�鿡 ǥ��
                	else {
                		st2.targetLeft.setText("   " + st2.tL);                		
                		}
                	return;
                }                
                //������ �ȿ� ���� ��� 7�ȼ��� �̵�
                else
                    target.setLocation(x, y);
            }
                //0.02�ʸ��� �̵�
                try{
                    sleep(20);
                }
                //�����尡 �װԵǸ� Ÿ�� ����
                catch(Exception e){   
                	target = null;
                    return;
                }
            	
            }
            
           
        }        
    
  //Ÿ��-�Ѿ� �浹 �˻�
    private boolean hit(){
        int x=st2.bullet.getX();
        int y=st2.bullet.getY();
        int w=st2.bullet.getWidth();
        int h=st2.bullet.getHeight();
        
        if(targetContains(x,y)
                ||targetContains(x+w-1,y)
                ||targetContains(x+w-1,y+h-1)
                ||targetContains(x,y+h-1))
            return true;
        else
            return false;
    }
    
    //Ÿ��-�÷��̾� �浹 �˻�
    private boolean playerHit(){
        int x=st2.player.getX();
        int y=st2.player.getY();
        int w=st2.player.getWidth();
        int h=st2.player.getHeight();
        
        if(targetContains(x,y)
                ||targetContains(x+w-1,y)
                ||targetContains(x+w-1,y+h-1)
                ||targetContains(x,y+h-1))
            return true;
        else
            return false;
    }
	 
	 private boolean targetContains(int x, int y){
	        //Ÿ���� x��ǥ�� �Ѿ� x��ǥ���� �۰ų� ������ �Ѿ� x��ǥ���� Ÿ�� x��ǥ + Ÿ���� ���� ���̰� ũ�� 
	        if(((target.getX()<=x)&&(x<target.getX()+target.getWidth()))   
	                //Ÿ���� y��ǥ�� �Ѿ� y��ǥ���� �۰ų� ������ �Ѿ� y��ǥ���� Ÿ�� y��ǥ + Ÿ���� ���� ���̰� ũ��
	                &&((target.getY()<=y)&&(y<target.getY()+target.getHeight())))
	            return true;
	        
	        else
	            return false;}
}
