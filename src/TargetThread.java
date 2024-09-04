import java.io.File;
import javax.swing.*;

public class TargetThread extends Thread{
    JLabel target;
    Stage1 st1;
    File playerCrash,bullet_target_hit ;
    
    
    TargetThread(JLabel target, Stage1 st1){
        this.target=target;
        this.st1 = st1;
        playerCrash = new File("player_target_crash.wav");
        bullet_target_hit = new File("bullet_target_hit.wav");        
    }
    
    
    public void run(){
        while(true){    
        	//Ÿ�� - �÷��̾� �ε���
        	if(playerHit()) {
        		System.out.println("�÷��̾� �浹");
        		new Music().playSound(playerCrash, 1.0f, false);
        		st1.playerLife--;
        		st1.tL--;//���� ���� �� ����
        		st1.remove(target); //�гο��� Ÿ�� �����
        		st1.repaint();
            	st1.revalidate();
            	target = null; //Ÿ�� ����
            	
            	//�÷��̾� ��� 0 -> ���ӿ���
        		if(st1.playerLife == 0) {
        			st1.mainF.toGameOver(st1);
        			st1.bgm.stopBgm();
        			st1.cTargetThread.interrupt();        			      			        		        		
        		}
        		
        		//�÷��̾� ��� ���� ->���� ȭ�鿡 ǥ��
        		else {
        			switch(st1.playerLife) {
        			case 4: st1.life.setText("heart  ����������");break; //ȭ�鿡 ���ҵ� ��� ǥ�� 
        			case 3: st1.life.setText("heart  ����������");break;
        			case 2: st1.life.setText("heart  ����������");break;
        			case 1: st1.life.setText("heart  ����������");break;
        			}
        			st1.targetLeft.setText("   " + st1.tL);//ȭ�鿡 ������ �� ǥ��
        		}
        		 
        		return;
        	}
        	
        	
            
        	//�Ѿ��� Ÿ���� �����
            if(st1.bullet !=null && hit()) {
            	new Music().playSound(bullet_target_hit, 1, false);
            	st1.bulletThread.interrupt();//�Ѿ� �����带 ���δ�.
            	st1.remove(target); //�гο��� Ÿ�� �����
            	st1.remove(st1.bullet);// �гο��� �Ѿ� �����
            	st1.repaint();
            	st1.revalidate();
            	
            	target = null; //Ÿ�� ����
            	st1.bullet = null; //�Ѿ� ����
            	
            	st1.i++; // ���� ����            	
            	st1.tL--;//���� ���� �� ����
            	
            	//��ǥ ���� ����-> ���� Ŭ���� ȭ������ + Ÿ�� ���� ������ ����
            	if(st1.i>=5) {
                 	st1.mainF.toStage1Clear(st1);//�������� Ŭ���� ȭ������
                 	st1.bgm.stopBgm(); //��������1 ������� ����
                 	st1.cTargetThread.interrupt(); //Ÿ�ٻ��� ������ ����
                 }
            	//��ǥ ���� ���� ���� X + ���� ���� 0 -> ���� ����
				 else if (st1.i < 10 && st1.tL == 0)
				 {
					 st1.mainF.toGameOver(st1);
					 st1.bgm.stopBgm(); //��������1 ������� ����
	                 st1.cTargetThread.interrupt();					 				 
				 }
				//��ǥ ���� ���� ���� X + ���� �� ���� -> ȭ�鿡 ������ ���� + ���� �� ǥ��
				 else if(st1.i < 5 && st1.tL != 0) 
				 {
					 st1.score.setText("SCORE "+ st1.i + " / 5"); //ȭ�鿡 ������ ���� ǥ��
					 st1.targetLeft.setText("   " + st1.tL);//ȭ�鿡 ������ �� ǥ��
				 }
            	return;
            }
            //Ÿ���� ������ ���ҽ� -> Ÿ�� �����ϰ� �Ʒ��� �̵�
            else {
            	int x=target.getX();
                int y=target.getY()+5;//���ȼ��� �̵��Ұ�����(��������1 5�ȼ�)
                
            	 //������ ������ ������� Ÿ�� �гο��� �����&������ ����
                if(y>st1.getHeight()) {
                	st1.remove(target);//�гο��� Ÿ�� �����
                	st1.repaint();
                	st1.revalidate();
                	target = null;      //Ÿ�� ����
                	
                	//���� ���� �� ���� + ȭ�鿡 ǥ��
                	st1.tL--;
                	st1.targetLeft.setText("   " + st1.tL);
                	
                	//���� �� 0�Ǹ� ���� ����
                	if(st1.tL == 0) {
                     	st1.mainF.toGameOver(st1);//���ӿ���ȭ������
                     	st1.bgm.stopBgm(); //��������1 ������� ����                     	
                     }
                	//���� �� 0 �ƴϸ� ���� �� �� ȭ�鿡 ǥ��
                	else {
                		st1.targetLeft.setText("   " + st1.tL);                		
                		}
                	return;
                }
                
                //������ �ȿ� ���� ��� 5�ȼ��� �̵�
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
        int x=st1.bullet.getX();
        int y=st1.bullet.getY();
        int w=st1.bullet.getWidth();
        int h=st1.bullet.getHeight();
        
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
        int x=st1.player.getX();
        int y=st1.player.getY();
        int w=st1.player.getWidth();
        int h=st1.player.getHeight();
        
        if(targetContains(x,y)
                ||targetContains(x+w-1,y)
                ||targetContains(x+w-1,y+h-1)
                ||targetContains(x,y+h-1))
            {
        	
        	
        	return true;
        	}
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

