/**
 * Class for menu. Calling corresponding function in the main.
 */

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class MenuPanel extends JPanel {

	private Connect4 mainGame;

	// mute function fields
	private JButton muteButton;
	private ActionListener muteActionListener;
	private ImageIcon muteButtonIcon = new ImageIcon("src/pics/Mute.png");
	private ImageIcon speakerButtonIcon = new ImageIcon("src/pics/Speaker.png");

	public MenuPanel(final Connect4 mainGame) {
		this.mainGame = mainGame;

		setLayout(new GridBagLayout());
		setBorder(new TitledBorder("New Game"));

		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0.5;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;

		// Button for Single Player
		gc.gridx = 0;
		gc.gridy = 0;
		ImageIcon singlePlayerButtonIcon = new ImageIcon("src/pics/single.png");
		JButton singlePlayerButton = new JButton(" Single Player",
				singlePlayerButtonIcon);
		singlePlayerButton.setToolTipText("Start a game against with BOT");
		singlePlayerButton.setFont(new Font("Arial", Font.PLAIN, 20));
		add(singlePlayerButton, gc);
		singlePlayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainGame.isMuted())
					ButtonSound.music("src/sound/button.wav");
				mainGame.changeGlassPane(1);
			}
		});

		// Button for Two Player
		gc.gridx = 0;
		gc.gridy = 1;
		ImageIcon twoPlayersButtonIcon = new ImageIcon("src/pics/Group-50.png");
		JButton twoPlayersButton = new JButton("Co-op Player",
				twoPlayersButtonIcon);
		twoPlayersButton.setToolTipText("Enter a mutiplayer VS Your Friend");
		twoPlayersButton.setFont(new Font("Arial", Font.PLAIN, 20));
		add(twoPlayersButton, gc);
		twoPlayersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainGame.isMuted())
					ButtonSound.music("src/sound/button.wav");
				mainGame.changeGlassPane(2);
			}

		});

		// Button for Statistic
		gc.gridx = 0;
		gc.gridy = 2;
		ImageIcon statisticButtonIcon = new ImageIcon("src/pics/Trophy-50.png");
		JButton statisticButton = new JButton(" High Score",
				statisticButtonIcon);
		statisticButton.setToolTipText("Check High Scored Record");
		statisticButton.setFont(new Font("Arial", Font.PLAIN, 20));
		add(statisticButton, gc);
		statisticButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainGame.isMuted())
					ButtonSound.music("src/sound/button.wav");
				mainGame.changeGlassPane(3);
			}
		});

		// Button for Credits
		gc.gridx = 0;
		gc.gridy = 3;
		ImageIcon creditsButtonIcon = new ImageIcon("src/pics/Gorilla-50.png");
		JButton creditsButton = new JButton("  Credits", creditsButtonIcon);
		creditsButton.setToolTipText("View the Development Team");
		creditsButton.setFont(new Font("Arial", Font.PLAIN, 20));
		add(creditsButton, gc);
		creditsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainGame.isMuted())
					ButtonSound.music("src/sound/button.wav");
				mainGame.changeGlassPane(4);
			}
		});

		// button for mute/play music
		gc.gridx = 0;
		gc.gridy = 4;
		muteButton = new JButton("Mute Music", muteButtonIcon);
		muteButton.setToolTipText("Mute/Unmute Music");
		muteButton.setFont(new Font("Arial", Font.PLAIN, 20));
		add(muteButton, gc);
		muteActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainGame.isMuted()) {
					mainGame.setisMuted(true);
				} else {
					mainGame.setisMuted(false);
					BackgroundMusic.music("src/sound/2-06_Awash_in_Ale_but_Nary_a_Mug.wav");
				}
			}
		};
		muteButton.addActionListener(muteActionListener);

		// Button for Quit
		gc.gridx = 0;
		gc.gridy = 5;
		ImageIcon quitButtonIcon = new ImageIcon("src/pics/Exit-50.png");
		JButton quitButton = new JButton("Quit Game", quitButtonIcon);
		quitButton.setToolTipText("Exit the Game");
		quitButton.setFont(new Font("Arial", Font.PLAIN, 20));
		add(quitButton, gc);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainGame.isMuted())
					ButtonSound.music("src/sound/button.wav");
				System.exit(0);
			}

		});
	}

	public ActionListener getMuteActionListener() {
		return muteActionListener;
	}

	public void mute() {
		muteButton.setIcon(speakerButtonIcon);
		muteButton.setText("Unmute");
		BackgroundMusic.stopMusic();
	}

	public void unmute() {
		muteButton.setIcon(muteButtonIcon);
		muteButton.setText("Mute");
		ButtonSound.music("src/sound/button.wav");		
	}
}
