import javax.swing.*;

public class MainFrame extends JFrame {
	
	Stage1 st1;	
	Stage2 st2;
	//static Clip clip;
	
	
	
    //규칙 화면
	public void toRules(JPanel currentPage) {
		this.remove(currentPage);
		this.add(new Rules(this));
		this.repaint();
		this.revalidate();
	}
	
	//스테이지 1 화면
	public void toStage1(JPanel currentPage) {
		this.remove(currentPage);
		st1 = new Stage1(this);
		this.add(st1);
		st1.setFocusable(true);
		st1.requestFocus();
		this.repaint();
		this.revalidate();

	}
	
	//시작화면으로
	public void toStartPage(JPanel currentPage) {
		this.remove(currentPage);
		this.add(new StartPage(this));
		this.repaint();
		this.revalidate();
	}
	
	
	//스테이지1클리어 화면
	public void toStage1Clear(JPanel currentPage) {
		this.remove(currentPage);		
		this.add(new Stage1Clear(this));
		this.repaint();
		this.revalidate();
	}
	
	//스테이지2
	public void toStage2(JPanel currentPage) {
		this.remove(currentPage);
		st2 = new Stage2(this);
		this.add(st2);
		st2.setFocusable(true);
		st2.requestFocus();
		this.repaint();
		this.revalidate();
		
	}
	
	//스테이지2 -> 게임 클리어
	public void toEndPage(JPanel currentPage) {
		this.remove(currentPage);
		this.add(new EndPage(this));
		this.repaint();
		this.revalidate();

	}
	
	//게임 오버
	public void toGameOver(JPanel currentPage) {
		this.remove(currentPage);		 
		this.add(new GameOver(this));
		this.repaint();
		this.revalidate();

	}
	

	//메인프레임 생성자
	public MainFrame() {
		this.setTitle("2021111235_구유나_게임"); //이름 설정
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //엑스 누르면 종료
        

        this.add(new StartPage(this));        
        
        this.setLocationRelativeTo(null); //화면 중앙에 뜨게
        this.setSize(600,400);    //사이즈 설정
        this.setResizable(false); //사이즈 변경 불가
        this.setVisible(true); //프레임 보이게
        
        
	}
	
	//메인 메소드
	public static void main(String[] args) {
		new MainFrame();
		
	}

}



