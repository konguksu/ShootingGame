public class BulletThread2 extends Thread{
    	
        Stage2 st2;
        
        
        public BulletThread2(Stage2 st2){
            
            this.st2 = st2;
        }
        
        public void run(){
            while(true){            	
            	int x=st2.bullet.getX();
                int y=st2.bullet.getY()-15;//5�ȼ��� ���� �̵��Ѵ�.=�Ѿ� �ӵ��� 5�ȼ�
                
                //�Ѿ��� ������ ������ ������ ��
                if(y<0){                	
                	st2.remove(st2.bullet);
                	st2.repaint();
                	st2.revalidate();
                    return;//�Ѿ� �����带 ���δ�.
                }
                //�Ѿ��� ������ �ȿ� ������ 5�ȼ��� �̵��Ѵ�.
                else
                	st2.bullet.setLocation(x, y);
                    
                                
                //0.02�ʸ��� 5�ȼ��� �̵�
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
        
        