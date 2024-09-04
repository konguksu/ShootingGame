import java.io.File;
import javax.sound.sampled.*;


public class Music{
	 Clip bgmclip;
     public void playBgm(File file, float vol, boolean repeat){
             try{                     
                     bgmclip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
                     bgmclip.open(AudioSystem.getAudioInputStream(file));
                     bgmclip.addLineListener(new LineListener() {
                             @Override
                             public void update(LineEvent event) {
                                     // TODO Auto-generated method stub
                                     System.out.println("" + event.getType());
                                     if(event.getType()==LineEvent.Type.STOP){
                                             bgmclip.close();
                                     }
                             }
                     });
                     FloatControl volume = (FloatControl)bgmclip.getControl(FloatControl.Type.MASTER_GAIN);
                     volume.setValue(vol);
                     bgmclip.start();
                     if(repeat)
                             bgmclip.loop(bgmclip.LOOP_CONTINUOUSLY);
             }catch(Exception e){
                     e.printStackTrace();
             }
     }
     public void stopBgm(){
            
             if(bgmclip!=null && bgmclip.isRunning()){
                     bgmclip.stop();
                     bgmclip.close();
             }
     }
     public void playSound(File file, float vol, boolean repeat){
             try{
                     final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
                     clip.open(AudioSystem.getAudioInputStream(file));
                     clip.addLineListener(new LineListener() {
                             @Override
                             public void update(LineEvent event) {
                                     // TODO Auto-generated method stub
                                     if(event.getType()==LineEvent.Type.STOP){
                                    	 System.out.println("효과음 메모리에 쌓여서 크래시되는것 방지");
                                    	 //효과음 메모리에 쌓여서 크래시되는것 방지
                                             clip.close();
                                     }
                             }
                     });
                     FloatControl volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                     volume.setValue(vol);
                     System.out.println("한번 재생");
                     clip.start();
                     if(repeat)
                             clip.loop(Clip.LOOP_CONTINUOUSLY);
             }catch(Exception e){
                     e.printStackTrace();
             }
     }
}

/*public class Music {
	
	private Clip player;
	private String name;
	//private boolean isLoop;
	private FileInputStream fis;
	private AudioInputStream ais;
	private BufferedInputStream bis;
	
	public Music(String name) {
		try {
			//this.isLoop = isLoop;
			this.name = name;
			fis = new FileInputStream(this.name);
			bis = new BufferedInputStream(fis);
			ais = AudioSystem.getAudioInputStream(bis);			
			player = AudioSystem.getClip();
			player.stop();
			player.open(ais);					
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
	public void close() {
		player.stop();		
	}
	public void BGMusic() {
		player.start();
		player.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void playOnce() {
		player.start();
	}

}*/

