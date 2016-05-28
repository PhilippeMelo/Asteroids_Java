import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class PlayAudio extends Thread {
	
	String diretorioDoAudio;
	
	public PlayAudio( String dirDoAudio){
		diretorioDoAudio = dirDoAudio;
		
	}

	public void run(){
		
		File arqAudio = new File(diretorioDoAudio);
		
		
        try{
        	FileInputStream fis = new FileInputStream(arqAudio);
            BufferedInputStream bis = new BufferedInputStream(fis);

            Player player = new Player(bis);
            player.play();

        }catch(JavaLayerException ex){}
        catch(FileNotFoundException e){}
	}

	public String getDiretorioDoAudio() {
		return diretorioDoAudio;
	}

	public void setDiretorioDoAudio(String diretorioDoAudio) {
		this.diretorioDoAudio = diretorioDoAudio;
	}
	
	
}