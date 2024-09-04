public class BulletThread extends Thread{
    	
        Stage1 st1;
       
        
        public BulletThread(Stage1 st1){
            
            this.st1 = st1;
        }
        
        public void run(){
            while(true){            	
            	int x=st1.bullet.getX();
                int y=st1.bullet.getY()-15;//5픽셀씩 위로 이동한다.=총알 속도가 5픽셀
                
                //총알이 프레임 밖으로 나갔을 때
                if(y<0){                	
                	st1.remove(st1.bullet);
                	st1.repaint();
                	st1.revalidate();
                    return;//총알 스레드를 죽인다.
                }
                //총알이 프레임 안에 있으면 5픽셀씩 이동한다.
                else
                	st1.bullet.setLocation(x, y);
                    
                                
                //0.02초마다 5픽셀씩 이동
                try{
                    sleep(20);
                }
                
                catch(Exception e){
                	st1.remove(st1.bullet);
                    return;
                }
            }

    }
}                        