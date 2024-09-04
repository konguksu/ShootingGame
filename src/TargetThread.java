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
        	//타겟 - 플레이어 부딪힘
        	if(playerHit()) {
        		System.out.println("플레이어 충돌");
        		new Music().playSound(playerCrash, 1.0f, false);
        		st1.playerLife--;
        		st1.tL--;//남은 적의 수 감소
        		st1.remove(target); //패널에서 타겟 지우기
        		st1.repaint();
            	st1.revalidate();
            	target = null; //타겟 삭제
            	
            	//플레이어 목숨 0 -> 게임오버
        		if(st1.playerLife == 0) {
        			st1.mainF.toGameOver(st1);
        			st1.bgm.stopBgm();
        			st1.cTargetThread.interrupt();        			      			        		        		
        		}
        		
        		//플레이어 목숨 남음 ->감소 화면에 표시
        		else {
        			switch(st1.playerLife) {
        			case 4: st1.life.setText("heart  ♥♥♥♥♡");break; //화면에 감소된 목숨 표시 
        			case 3: st1.life.setText("heart  ♥♥♥♡♡");break;
        			case 2: st1.life.setText("heart  ♥♥♡♡♡");break;
        			case 1: st1.life.setText("heart  ♥♡♡♡♡");break;
        			}
        			st1.targetLeft.setText("   " + st1.tL);//화면에 감소한 적 표시
        		}
        		 
        		return;
        	}
        	
        	
            
        	//총알이 타겟을 맞출시
            if(st1.bullet !=null && hit()) {
            	new Music().playSound(bullet_target_hit, 1, false);
            	st1.bulletThread.interrupt();//총알 스레드를 죽인다.
            	st1.remove(target); //패널에서 타겟 지우기
            	st1.remove(st1.bullet);// 패널에서 총알 지우기
            	st1.repaint();
            	st1.revalidate();
            	
            	target = null; //타겟 삭제
            	st1.bullet = null; //총알 삭제
            	
            	st1.i++; // 점수 증가            	
            	st1.tL--;//남은 적의 수 감소
            	
            	//목표 점수 도달-> 게임 클리어 화면으로 + 타겟 생성 스레드 종료
            	if(st1.i>=5) {
                 	st1.mainF.toStage1Clear(st1);//스테이지 클리어 화면으로
                 	st1.bgm.stopBgm(); //스테이지1 배경음악 종료
                 	st1.cTargetThread.interrupt(); //타겟생성 스레드 종료
                 }
            	//목표 점수 아직 도달 X + 남은 적은 0 -> 게임 오버
				 else if (st1.i < 10 && st1.tL == 0)
				 {
					 st1.mainF.toGameOver(st1);
					 st1.bgm.stopBgm(); //스테이지1 배경음악 종료
	                 st1.cTargetThread.interrupt();					 				 
				 }
				//목표 점수 아직 도달 X + 남은 적 존재 -> 화면에 증가한 점수 + 남은 적 표시
				 else if(st1.i < 5 && st1.tL != 0) 
				 {
					 st1.score.setText("SCORE "+ st1.i + " / 5"); //화면에 증가한 점수 표시
					 st1.targetLeft.setText("   " + st1.tL);//화면에 감소한 적 표시
				 }
            	return;
            }
            //타겟을 맞추지 못할시 -> 타겟 일정하게 아래로 이동
            else {
            	int x=target.getX();
                int y=target.getY()+5;//몇픽셀씩 이동할것인지(스테이지1 5픽셀)
                
            	 //프레임 밖으로 나갈경우 타겟 패널에서 지우기&스레드 종료
                if(y>st1.getHeight()) {
                	st1.remove(target);//패널에서 타겟 지우기
                	st1.repaint();
                	st1.revalidate();
                	target = null;      //타겟 삭제
                	
                	//남은 적의 수 감소 + 화면에 표시
                	st1.tL--;
                	st1.targetLeft.setText("   " + st1.tL);
                	
                	//남은 적 0되면 게임 오버
                	if(st1.tL == 0) {
                     	st1.mainF.toGameOver(st1);//게임오버화면으로
                     	st1.bgm.stopBgm(); //스테이지1 배경음악 종료                     	
                     }
                	//남은 적 0 아니면 남은 적 수 화면에 표시
                	else {
                		st1.targetLeft.setText("   " + st1.tL);                		
                		}
                	return;
                }
                
                //프레임 안에 있을 경우 5픽셀씩 이동
                else
                    target.setLocation(x, y);
            }
                //0.02초마다 이동
                try{
                    sleep(20);
                }
                //스레드가 죽게되면 타겟 삭제
                catch(Exception e){
                	target = null;
                    return;
                }
            	
            }
            
           
        }        
  //타겟-총알 충돌 검사
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
    
  //타겟-플레이어 충돌 검사
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
	        //타겟의 x좌표가 총알 x좌표보다 작거나 같으며 총알 x좌표보다 타겟 x좌표 + 타겟의 가로 길이가 크고 
	        if(((target.getX()<=x)&&(x<target.getX()+target.getWidth()))   
	                //타겟의 y좌표가 총알 y좌표보다 작거나 같으며 총알 y좌표보다 타겟 y좌표 + 타겟의 세로 길이가 크면
	                &&((target.getY()<=y)&&(y<target.getY()+target.getHeight())))
	            return true;
	        
	        else
	            return false;}
}

