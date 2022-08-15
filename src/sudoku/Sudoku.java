package sudoku;

import java.util.ArrayList;
import java.util.List;

public class Sudoku implements SudokuSolver {

	private int[][] matrix;

	public Sudoku() {
		matrix = new int[9][9];
	}

	@Override
	public boolean solve() {
		return solve(0, 0);
	}

	private boolean solve(int i, int j) {
		j += i / 9;
		i %= 9;
		if (j > 8) {
			return true;
		}
		if (matrix[i][j] != 0) {
			return isValid(i, j) && solve(i + 1, j);
		}
		for (int number = 1; number < 10; number++) {
			matrix[i][j] = number;
			if(isValid(i, j)){
				if(solve(i+1, j)){
					return true;
				}
			}
		}
		matrix[i][j] = 0;
		return false;
	}


	@Override
	public void add(int row, int col, int digit) {
		validateRowAndColRange(row);
		validateRowAndColRange(col);
		validateDigitRange(digit);
		matrix[row][col] = digit;

	}

	@Override
	public void remove(int row, int col) {
		validateRowAndColRange(row);
		validateRowAndColRange(col);
		matrix[row][col] = 0;
	}

	@Override
	public int get(int row, int col) {
		validateRowAndColRange(row);
		validateRowAndColRange(col);
		return matrix[row][col];
	}

	private void validateRowAndColRange(int i) throws IllegalArgumentException {
		if (i < 0 || i > 8) {
			throw new IllegalArgumentException();
		}
	}

	private void validateDigitRange(int i) throws IllegalArgumentException {
		if (i < 0 || i > 9) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean isValid() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (!isValid(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValid(int row, int col) {
		int number = matrix[row][col];

		if (number == 0) {
			return true;
		}
		// Validate Column
		for (int i = 0; i < matrix.length; i++) {
			if (number == matrix[i][col] && i != row) {
				return false;
			}
		}
		// Validate Row
		for (int i = 0; i < matrix[row].length; i++) {
			if (number == matrix[row][i] && i != col) {
				return false;
			}
		}
		// validate 3 x 3
		for (int i = 0 + (row / 3) * 3; i < 3 + (row / 3) * 3; i++) {
			for (int j = 0 + (col / 3) * 3; j < 3 + (col / 3) * 3; j++) {
				if (number == matrix[i][j] && (!(i == row && j == col))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void clear() {
		matrix = new int[9][9];
	}

	@Override
	public void setMatrix(int[][] m) {
		if (m.length != 9) {
			throw new IllegalArgumentException();
		}
		if (m[0].length != 9) {
			throw new IllegalArgumentException();
		}
		for (int[] iv : m) {
			for (int i : iv) {
				validateDigitRange(i);
			}
		}
		matrix = m.clone();

	}

	@Override
	public int[][] getMatrix() {
		return matrix;
	}

}
