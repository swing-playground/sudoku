package org.pavel.amos.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import org.pavel.amos.enums.DigitLabel;
import org.pavel.amos.util.Sudoku;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	private boolean gameOn;

	private JPanel[][] cells;

	private byte[][] solvedMatrix;
	private byte[][] visibleBoard;

	private static BoardPanel instance = null;

	public static synchronized BoardPanel getInstance() {
		if (instance == null) {
			instance = new BoardPanel();
		}
		return instance;
	}

	private BoardPanel() {
		setLayout(new GridLayout(9, 9));
		cells = new JPanel[9][9];
		IntStream.range(0, 9).forEach(x -> IntStream.range(0, 9).forEach(y -> {
			cells[x][y] = new JPanel();
			cells[x][y].setLayout(new GridBagLayout());
			cells[x][y].setBackground((x + y) % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
			cells[x][y].setBorder(new MatteBorder(x == 0 ? 1 : 0, y == 0 ? 1 : 0,
					(x + 1) % 3 == 0 ? 1 : 0, (y + 1) % 3 == 0 ? 1 : 0, Color.BLACK));
			add(cells[x][y]);
		}));
		Dimension dimension = new Dimension(700, 700);
		setSize(dimension);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
	}

	public void startNewGame() {
		gameOn = true;
		generateBoard();
		IntStream.range(0, 9).forEach(x -> IntStream.range(0, 9).forEach(y -> {
			cells[x][y].removeAll();
			DigitLabel digit = DigitLabel.getDigit(visibleBoard[x][y]);
			if (digit != null) {
				cells[x][y].add(digit.getLabel());
			}
			else {

				cells[x][y].addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
						if (gameOn) {
							String digitStr = JOptionPane.showInputDialog("Enter digit");
							byte digit = 0;
							try {
								digit = (byte) Integer.parseInt(digitStr);
							}
							catch (Exception er) {
							}

							if (solvedMatrix[x][y] == digit) {
								visibleBoard[x][y] = digit;
								JLabel label = DigitLabel.getDigit(solvedMatrix[x][y])
										.getLabel(false);
								cells[x][y].add(label);
								revalidate();
								validate();
								repaint();
								if (isFinished()) {
									setFinishState();
								}
							}
						}
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseClicked(MouseEvent e) {
					}
				});
			}
		}));
		revalidate();
		validate();
		repaint();
	}

	private void generateBoard() {
		solvedMatrix = Sudoku.generate();
		visibleBoard = new byte[9][9];
		IntStream.range(0, 9).forEach(x -> IntStream.range(0, 9)
				.forEach(y -> visibleBoard[x][y] = solvedMatrix[x][y]));
		Set<Byte> toRemove = new HashSet<>();
		Random rand = new Random();
		while (toRemove.size() < 50) {
			toRemove.add((byte) rand.nextInt(81));
		}
		toRemove.stream().forEach(t -> {
			int x = t / 9;
			int y = t % 9;
			visibleBoard[x][y] = 0;
		});
	}

	/**
	 * 
	 */
	public void finishGame() {
		IntStream.range(0, 9).forEach(x -> IntStream.range(0, 9).forEach(y -> {
			if (visibleBoard[x][y] == 0) {
				cells[x][y].removeAll();
				JLabel label = DigitLabel.getDigit(solvedMatrix[x][y]).getLabel(false);
				label.setForeground(Color.MAGENTA);
				cells[x][y].add(label);
			}
		}));
		revalidate();
		validate();
		repaint();
		setFinishState();
	}

	private void setFinishState() {
		gameOn = false;
		GamePanel.getInstance().setFinishState();
	}

	public void hint() {
		IntStream.range(0, 81).filter(n -> visibleBoard[n / 9][n % 9] == 0).findAny()
				.ifPresent(n -> {
					int x = n / 9;
					int y = n % 9;
					visibleBoard[x][y] = solvedMatrix[x][y];
					cells[x][y].removeAll();
					JLabel label = DigitLabel.getDigit(solvedMatrix[x][y])
							.getLabel(false);
					label.setForeground(Color.CYAN);
					cells[x][y].add(label);
				});
		revalidate();
		validate();
		repaint();
		if (isFinished()) {
			setFinishState();
		}
	}

	public boolean isFinished() {
		return !IntStream.range(0, 81).anyMatch(n -> visibleBoard[n / 9][n % 9] == 0);
	}
}
