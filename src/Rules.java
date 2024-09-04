import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Rules extends JPanel {
	MainFrame mainF;
	private Image background;
	private JButton startPage;
	
	public Rules(MainFrame mainFrame) {
		mainF = mainFrame;
		
		Font font = new Font("DungGeunMo",Font.PLAIN,20);

		this.setLayout(null);
		try {
	         background = ImageIO.read(new File("rules.png"));
	      }catch(IOException e) {
	         e.printStackTrace();
	      }
		
		startPage = new JButton("back to start");
		startPage.setFont(font);
		startPage.setFocusPainted(false);
		startPage.setBounds(400,10,200,30);
		startPage.setForeground(Color.white); //���� �� -> ���
		startPage.setBorderPainted(false); //�׵θ� ����
		startPage.setContentAreaFilled(false); //ä��� ����
		
		startPage.addActionListener(e -> {
			mainF.toStartPage(this);
		});
		this.add(startPage);

	}

	 protected void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      g.drawImage(background,0,0,600,370,null);
	   }
	
}
