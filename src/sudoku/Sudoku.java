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
		validateRange(digit-1);
		matrix[row][col] = digit;

	}

	@Override
	public void remove(int row, int col) {
		validateRange(row);
		validateRange(col);
		matrix[row][col] = 0;
	}

	@Override
	public int get(int row, int col) {
		validateRange(row);
		validateRange(col);
		return matrix[row][col];
	}

	private void validateRange(int i) throws IllegalArgumentException
	{
		if(i < 0 || i > 8){
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
		matrix = m;

	}

	@Override
	public int[][] getMatrix() {
		return matrix;
	}

}
