public class BulletThread extends Thread{
    	
        Stage1 st1;
       
        
        public BulletThread(Stage1 st1){
            
            this.st1 = st1;
        }
        
        public void run(){
            while(true){            	
            	int x=st1.bullet.getX();
                int y=st1.bullet.getY()-15;//5�ȼ��� ���� �̵��Ѵ�.=�Ѿ� �ӵ��� 5�ȼ�
                
                //�Ѿ��� ������ ������ ������ ��
                if(y<0){                	
                	st1.remove(st1.bullet);
                	st1.repaint();
                	st1.revalidate();
                    return;//�Ѿ� �����带 ���δ�.
                }
                //�Ѿ��� ������ �ȿ� ������ 5�ȼ��� �̵��Ѵ�.
                else
                	st1.bullet.setLocation(x, y);
                    
                                
                //0.02�ʸ��� 5�ȼ��� �̵�
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