public class CreatTargetThread extends Thread {
	
	Stage1 st1;
	Stage2 st2;
	int num;
	String stage;
	
    //�������� 1�϶� ������
    public CreatTargetThread(Stage1 st1){
        
        this.st1 = st1;
        num = 0;
        stage = "stage1";
        
    }
    
  //�������� 2�϶� ������
    public CreatTargetThread(Stage2 st2){
        
        this.st2 = st2;
        num = 0;
        stage = "stage2";
        
    }

    
    //run �޼ҵ� �������̵�
	public void run() {
		while(true){
			//�������� 1
			if(stage == "stage1") {
				//Ÿ�� �迭 �� ��ä������ Ÿ�� ����
				if(num < 50) {
					this.targetMaker(st1.target[num],st1);
					num++;				
				}
				//Ÿ�� �迭 �� ä��� ������ ����
				else {
					return;
				}
				
				try {
					Thread.sleep(1000); //10�� �� ���ο� Ÿ�� ����
				}
				catch(Exception e) {
					System.out.println("creatTaraget: �������� 1������ ����");
		            return;
				}
			}
			
			//�������� 2
			else if(stage == "stage2"){
				//Ÿ�� �迭 �� ��ä������ Ÿ�� ����
				if(num < 50) {
					this.targetMaker(st2.target[num],st2);
					num++;				
				}
				//Ÿ�� �迭 �� ä��� ������ ����
				else {
					return;
				}
				
				try {
					Thread.sleep(800); //8�� �� ���ο� Ÿ�� ����
				}
				catch(Exception e) {
					System.out.println("creatTaraget: �������� 2������ ����");
		            return;
				}
			}
			
			        			
		}
		
	}
	

	
	//Ÿ�� ���� �޼ҵ�
	public void targetMaker(Target newTarget,Stage1 stage) {
    	newTarget = new Target(stage); // Ÿ�� ����
    	new TargetThread(newTarget,stage).start(); // Ÿ�� �����̰� �ϴ� ������ ����
    	
    	
    }
	
	public void targetMaker(Target newTarget,Stage2 stage) {
    	newTarget = new Target(stage); // Ÿ�� ����
    	new TargetThread2(newTarget,stage).start(); // Ÿ�� �����̰� �ϴ� ������ ����
    	
    	
    }
	
	

}


