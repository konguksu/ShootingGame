import javax.swing.*;

public class MainFrame extends JFrame {
	
	Stage1 st1;	
	Stage2 st2;
	//static Clip clip;
	
	
	
    //��Ģ ȭ��
	public void toRules(JPanel currentPage) {
		this.remove(currentPage);
		this.add(new Rules(this));
		this.repaint();
		this.revalidate();
	}
	
	//�������� 1 ȭ��
	public void toStage1(JPanel currentPage) {
		this.remove(currentPage);
		st1 = new Stage1(this);
		this.add(st1);
		st1.setFocusable(true);
		st1.requestFocus();
		this.repaint();
		this.revalidate();

	}
	
	//����ȭ������
	public void toStartPage(JPanel currentPage) {
		this.remove(currentPage);
		this.add(new StartPage(this));
		this.repaint();
		this.revalidate();
	}
	
	
	//��������1Ŭ���� ȭ��
	public void toStage1Clear(JPanel currentPage) {
		this.remove(currentPage);		
		this.add(new Stage1Clear(this));
		this.repaint();
		this.revalidate();
	}
	
	//��������2
	public void toStage2(JPanel currentPage) {
		this.remove(currentPage);
		st2 = new Stage2(this);
		this.add(st2);
		st2.setFocusable(true);
		st2.requestFocus();
		this.repaint();
		this.revalidate();
		
	}
	
	//��������2 -> ���� Ŭ����
	public void toEndPage(JPanel currentPage) {
		this.remove(currentPage);
		this.add(new EndPage(this));
		this.repaint();
		this.revalidate();

	}
	
	//���� ����
	public void toGameOver(JPanel currentPage) {
		this.remove(currentPage);		 
		this.add(new GameOver(this));
		this.repaint();
		this.revalidate();

	}
	

	//���������� ������
	public MainFrame() {
		this.setTitle("2021111235_������_����"); //�̸� ����
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���� ������ ����
        

        this.add(new StartPage(this));        
        
        this.setLocationRelativeTo(null); //ȭ�� �߾ӿ� �߰�
        this.setSize(600,400);    //������ ����
        this.setResizable(false); //������ ���� �Ұ�
        this.setVisible(true); //������ ���̰�
        
        
	}
	
	//���� �޼ҵ�
	public static void main(String[] args) {
		new MainFrame();
		
	}

}



