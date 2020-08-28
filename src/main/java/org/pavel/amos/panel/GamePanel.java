package org.pavel.amos.panel;

import java.awt.Button;
import java.awt.FlowLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private static GamePanel instance = null;

	Button startBtn;
	Button giveupBtn;
	Button hintBtn;

	public static synchronized GamePanel getInstance() {
		if (instance == null) {
			instance = new GamePanel();
		}
		return instance;
	}

	private GamePanel() {
		setLayout(new FlowLayout());
		startBtn = new Button("Let's rock");
		giveupBtn = new Button("Give up");
		hintBtn = new Button("Hint");
		giveupBtn.setEnabled(false);
		hintBtn.setEnabled(false);
		add(startBtn);
		add(giveupBtn);
		add(hintBtn);
		giveupBtn.setEnabled(false);
		hintBtn.setEnabled(false);
		startBtn.addActionListener(e -> {
			BoardPanel.getInstance().startNewGame();
			startBtn.setEnabled(false);
			giveupBtn.setEnabled(true);
			hintBtn.setEnabled(true);
		});
		giveupBtn.addActionListener(e -> {
			BoardPanel.getInstance().finishGame();
			setFinishState();
		});
		hintBtn.addActionListener(e -> {
			BoardPanel instance = BoardPanel.getInstance();
			instance.hint();
		});
	}

	public void setFinishState() {
		startBtn.setEnabled(true);
		giveupBtn.setEnabled(false);
		hintBtn.setEnabled(false);
	}
}
