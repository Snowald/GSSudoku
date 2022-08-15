package sudoku;
/**
 * Implements all the neccessary mehtods for creating and solving a sudoku
 */
public interface SudokuSolver {
	/**
	 * Attempts to solve the sudoku
	 * @return 	If the sudoku was solved or not
	 */
	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void add(int row, int col, int digit);

	/**
	 * Clears the box of row, col. Sets it to zero
	 * @param row	The row
	 * @param col   The column
	 * @throws IllegalArgumentException if row or col is outside the range
	 *                                  [0..9]
	 */
	void remove(int row, int col);

	/**
	 * Gets the number of the box row, col
	 * @param row	The row
	 * @param col   The column
	 * @return 		The number in the box
	 * @throws IllegalArgumentException if row or col is outside the range
	 *                                  [0..9]
	 */
	int get(int row, int col);

	/**
	 * Checks that all filled in digits follows the the sudoku rules
	 * @return Wherther all numbers follow the rules
	 */
	boolean isValid();

	/**
	 * Clears all numbers from the board and sets them to zero
	 */
	void clear();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setMatrix(int[][] m);

	/**
	 * Returns the matrix as an array with the dimentions 9 x 9
	 * @return 	The array containing the matrix 
	 */
	int[][] getMatrix();
}