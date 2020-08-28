package org.pavel.amos;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.pavel.amos.panel.BoardPanel;
import org.pavel.amos.panel.GamePanel;

@SuppressWarnings("serial")
public class App extends JFrame {

	public App() {
		createGUI();
	}

	private void createGUI() {
		BoardPanel boardPanel = BoardPanel.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(GamePanel.getInstance(), BorderLayout.PAGE_START);
		add(boardPanel, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(App::new);
	}
}
