package org.pavel.amos.util;

import java.util.Random;

public class Sudoku {

	private Sudoku(){
	}
	
	private static byte[][] valid = { { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
			{ 4, 5, 6, 7, 8, 9, 1, 2, 3 }, { 7, 8, 9, 1, 2, 3, 4, 5, 6 },
			{ 2, 3, 4, 5, 6, 7, 8, 9, 1 }, { 5, 6, 7, 8, 9, 1, 2, 3, 4 },
			{ 8, 9, 1, 2, 3, 4, 5, 6, 7 }, { 3, 4, 5, 6, 7, 8, 9, 1, 2 },
			{ 6, 7, 8, 9, 1, 2, 3, 4, 5 }, { 9, 1, 2, 3, 4, 5, 6, 7, 8 } };


	public static byte[][] generate() {
		byte[][] mat = valid;
		for (int i = 0; i < 200; i++) {
			Random rand = new Random();
			int row = rand.nextInt(3);
			int group = rand.nextInt(3);
			shuffleRowsInGroup(mat, row);
			shuffleColsInGroup(mat, group);
			shuffleRowsGroup(mat);
			shuffleColsGroup(mat);
			transposing(mat);
		}
		return mat;
	}

	private static void shuffleRowsInGroup(byte[][] mat, int rowGroup) {
		Random rand = new Random();
		int r1 = rand.nextInt(3);
		int r2;
		while ((r2 = rand.nextInt(3)) == r1);
		byte[] temp = mat[r1 + 3 * rowGroup];
		mat[r1 + 3 * rowGroup] = mat[r2 + 3 * rowGroup];
		mat[r2 + 3 * rowGroup] = temp;
	}

	private static void shuffleColsInGroup(byte[][] mat, int rowGroup) {
		Random rand = new Random();
		int c1 = rand.nextInt(3);
		int c2;
		while ((c2 = rand.nextInt(3)) == c1);
		c1 += rowGroup * 3;
		c2 += rowGroup * 3;
		replaceCols(mat, c1, c2);
	}

	private static void replaceCols(byte[][] mat, int c1, int c2) {
		byte[] temp = new byte[9];
		for (int i = 0; i < 9; i++) {
			temp[i] = mat[i][c1];
		}
		for (int i = 0; i < 9; i++) {
			mat[i][c1] = mat[i][c2];
		}
		for (int i = 0; i < 9; i++) {
			mat[i][c2] = temp[i];
		}
	}

	private static void shuffleRowsGroup(byte[][] mat) {
		Random rand = new Random();
		int r1 = rand.nextInt(3);
		int r2;
		while ((r2 = rand.nextInt(3)) == r1);
		byte[] temp = mat[3 * r1];
		mat[3 * r1] = mat[3 * r2];
		mat[r2 * 3] = temp;

		temp = mat[3 * r1 + 1];
		mat[3 * r1 + 1] = mat[3 * r2 + 1];
		mat[r2 * 3 + 1] = temp;

		temp = mat[3 * r1 + 2];
		mat[3 * r1 + 2] = mat[3 * r2 + 2];
		mat[r2 * 3 + 2] = temp;
	}

	private static void shuffleColsGroup(byte[][] mat) {
		Random rand = new Random();
		int c1 = rand.nextInt(3);
		int c2;
		while ((c2 = rand.nextInt(3)) == c1);
		replaceCols(mat, c1 * 3, c2 * 3);
		replaceCols(mat, c1 * 3 + 1, c2 * 3 + 1);
		replaceCols(mat, c1 * 3 + 2, c2 * 3 + 2);
	}
	
	private static void transposing(byte[][] mat){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (i != j){
					mat[i][j] += mat[j][i];
					mat[j][i] = (byte)(mat[i][j] - mat[j][i]);
					mat[i][j] = (byte)(mat[i][j] - mat[j][i]);
				}
			}
		}
	}
}
