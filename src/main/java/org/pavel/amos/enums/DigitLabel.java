package org.pavel.amos.enums;

import java.awt.Font;

import javax.swing.JLabel;

public enum DigitLabel {
	ONE {
		public JLabel getLabel(boolean bold) {
			JLabel l = new JLabel("1");
			l.setFont(getFont(bold));
			return l;
		}

		@Override
		public JLabel getLabel() {
			return getLabel(true);
		}

	},
	TWO {
		public JLabel getLabel(boolean bold) {
			JLabel l = new JLabel("2");
			l.setFont(getFont(bold));
			return l;
		}

		@Override
		public JLabel getLabel() {
			return getLabel(true);
		}
	},
	THREE {
		public JLabel getLabel(boolean bold) {
			JLabel l = new JLabel("3");
			l.setFont(getFont(bold));
			return l;
		}

		@Override
		public JLabel getLabel() {
			return getLabel(true);
		}

	},
	FOUR {
		public JLabel getLabel(boolean bold) {
			JLabel l = new JLabel("4");
			l.setFont(getFont(bold));
			return l;
		}

		@Override
		public JLabel getLabel() {
			return getLabel(true);
		}
	},
	FIVE {
		public JLabel getLabel(boolean bold) {
			JLabel l = new JLabel("5");
			l.setFont(getFont(bold));
			return l;
		}

		@Override
		public JLabel getLabel() {
			return getLabel(true);
		}
	},
	SIX {
		public JLabel getLabel(boolean bold) {
			JLabel l = new JLabel("6");
			l.setFont(getFont(bold));
			return l;
		}

		@Override
		public JLabel getLabel() {
			return getLabel(true);
		}
	},
	SEVEN {
		public JLabel getLabel(boolean bold) {
			JLabel l = new JLabel("7");
			l.setFont(getFont(bold));
			return l;
		}

		@Override
		public JLabel getLabel() {
			return getLabel(true);
		}
	},
	EIGHT {
		public JLabel getLabel(boolean bold) {
			JLabel l = new JLabel("8");
			l.setFont(getFont(bold));
			return l;
		}

		@Override
		public JLabel getLabel() {
			return getLabel(true);
		}
	},
	NINE {
		public JLabel getLabel(boolean bold) {
			JLabel l = new JLabel("9");
			l.setFont(getFont(bold));
			return l;
		}

		@Override
		public JLabel getLabel() {
			return getLabel(true);
		}
	};

	private static Font getFont(boolean bold) {
		return new Font("Arial", bold ? Font.BOLD : Font.PLAIN, 35);
	}

	public abstract JLabel getLabel();

	public abstract JLabel getLabel(boolean bold);

	public static DigitLabel getDigit(byte digit) {
		switch (digit) {
		case 1:
			return ONE;
		case 2:
			return TWO;
		case 3:
			return THREE;
		case 4:
			return FOUR;
		case 5:
			return FIVE;
		case 6:
			return SIX;
		case 7:
			return SEVEN;
		case 8:
			return EIGHT;
		case 9:
			return NINE;
		default:
			return null;
		}
	}
}
