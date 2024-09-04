public class BulletThread2 extends Thread{
    	
        Stage2 st2;
        
        
        public BulletThread2(Stage2 st2){
            
            this.st2 = st2;
        }
        
        public void run(){
            while(true){            	
            	int x=st2.bullet.getX();
                int y=st2.bullet.getY()-15;//5픽셀씩 위로 이동한다.=총알 속도가 5픽셀
                
                //총알이 프레임 밖으로 나갔을 때
                if(y<0){                	
                	st2.remove(st2.bullet);
                	st2.repaint();
                	st2.revalidate();
                    return;//총알 스레드를 죽인다.
                }
                //총알이 프레임 안에 있으면 5픽셀씩 이동한다.
                else
                	st2.bullet.setLocation(x, y);
                    
                                
                //0.02초마다 5픽셀씩 이동
                try{
                    sleep(20);
                }
                
                catch(Exception e){
                	st2.remove(st2.bullet);
                    return;
                }
            }

    }
}
        
        