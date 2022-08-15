package sudoku;

public class Sudoku implements SudokuSolver {

	private int[][] matrix;

	public Sudoku()
	{
		matrix = new int[9][9];
	}

	@Override
	public boolean solve() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(int row, int col, int digit) {
		validateRange(row);
		validateRange(col);
		validateRange(digit);
		matrix[row-1][col-1] = digit;

	}

	@Override
	public void remove(int row, int col) {
		validateRange(row);
		validateRange(col);
		matrix[row - 1][col - 1] = 0;
	}

	@Override
	public int get(int row, int col) {
		validateRange(row);
		validateRange(col);
		return matrix[row-1][col-1];
	}

	private void validateRange(int i) throws IllegalArgumentException
	{
		if(i < 1 || i > 9){
			throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		matrix = new int[9][9];
	}

	@Override
	public void setMatrix(int[][] m) {
		// TODO Auto-generated method stub

	}

	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

}
