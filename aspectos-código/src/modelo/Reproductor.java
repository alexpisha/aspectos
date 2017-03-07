package modelo;

import javazoom.jlgui.basicplayer.BasicPlayer;

import java.io.File;
import java.util.Map;

public class Reproductor {

	private BasicPlayer player = new BasicPlayer();
	private double bytesLength;
	
	public Reproductor(){}
	
		public void play() throws Exception {
		  player.play();
		}
		 
		public void abrirFichero(String ruta) throws Exception {
		  player.open(new File(ruta));
		}
		 
		public void pausa() throws Exception {
		  player.pause();
		}
		 
		public void continuar() throws Exception {
		  player.resume();
		  
		}
		 
		public void opened(Object arg0, Map arg1) {
			 if (arg1.containsKey("audio.length.bytes")) {
			  bytesLength = Double.parseDouble(arg1.get("audio.length.bytes").toString());
			 }
			}
		
		public void progress(int bytesread, long microseconds, byte[] pcmdata,  Map properties) {
			 float progressUpdate = (float) (bytesread * 1.0f / bytesLength * 1.0f);
			 int progressNow = (int) (bytesLength * progressUpdate);
			 // Descomentando la siguiente línea se mosrtaría el progreso
			 // System.out.println(" -&gt; " + progressNow);
			}
		
		public void stop() throws Exception {
		  player.stop();
		}
}
