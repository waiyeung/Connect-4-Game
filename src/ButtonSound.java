/**
 * This class plays sound clip when Button is pressed
 * 
 * @author Jiangkan Pan
 *
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;

public class ButtonSound {

	// static Clip clip;
	static AudioInputStream ais;
	static AudioClip clip;
	static URL url;

	/**
	 * The constructor takes in the path of the audio file
	 * 
	 * @param songName
	 */

	public static void music(String songName) {

		try {
			url = new File(songName).toURI().toURL();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		clip = Applet.newAudioClip(url);
		clip.play();

	}

}
