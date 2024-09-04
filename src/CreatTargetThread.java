public class CreatTargetThread extends Thread {
	
	Stage1 st1;
	Stage2 st2;
	int num;
	String stage;
	
    //스테이지 1일때 생성자
    public CreatTargetThread(Stage1 st1){
        
        this.st1 = st1;
        num = 0;
        stage = "stage1";
        
    }
    
  //스테이지 2일때 생성자
    public CreatTargetThread(Stage2 st2){
        
        this.st2 = st2;
        num = 0;
        stage = "stage2";
        
    }

    
    //run 메소드 오버라이딩
	public void run() {
		while(true){
			//스테이지 1
			if(stage == "stage1") {
				//타겟 배열 다 못채웠으면 타겟 생성
				if(num < 50) {
					this.targetMaker(st1.target[num],st1);
					num++;				
				}
				//타겟 배열 다 채우면 스레드 종료
				else {
					return;
				}
				
				try {
					Thread.sleep(1000); //10초 뒤 새로운 타겟 생성
				}
				catch(Exception e) {
					System.out.println("creatTaraget: 스테이지 1스레드 종료");
		            return;
				}
			}
			
			//스테이지 2
			else if(stage == "stage2"){
				//타겟 배열 다 못채웠으면 타겟 생성
				if(num < 50) {
					this.targetMaker(st2.target[num],st2);
					num++;				
				}
				//타겟 배열 다 채우면 스레드 종료
				else {
					return;
				}
				
				try {
					Thread.sleep(800); //8초 뒤 새로운 타겟 생성
				}
				catch(Exception e) {
					System.out.println("creatTaraget: 스테이지 2스레드 종료");
		            return;
				}
			}
			
			        			
		}
		
	}
	

	
	//타겟 생성 메소드
	public void targetMaker(Target newTarget,Stage1 stage) {
    	newTarget = new Target(stage); // 타겟 생성
    	new TargetThread(newTarget,stage).start(); // 타겟 움직이게 하는 스레드 시작
    	
    	
    }
	
	public void targetMaker(Target newTarget,Stage2 stage) {
    	newTarget = new Target(stage); // 타겟 생성
    	new TargetThread2(newTarget,stage).start(); // 타겟 움직이게 하는 스레드 시작
    	
    	
    }
	
	

}


