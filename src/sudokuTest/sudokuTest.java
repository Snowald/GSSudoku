package sudokuTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sudoku.Sudoku;

class sudokuTest {
	
	private Sudoku s;

	@BeforeEach
	void setUp() throws Exception {
		s = new Sudoku();
	}

	@AfterEach
	void tearDown() throws Exception {
		s = null;
	}

	@Test
	void testAddAndGet() {
		s.add(2, 2, 5);
		assertEquals(5, s.get(2, 2));
	}

	@Test
	void testAddAndGetThrowsCorrectExceptions() {
		assertThrows(IllegalArgumentException.class, () -> s.add(10, 2, 4));
		assertThrows(IllegalArgumentException.class, () -> s.add(5, -1, 4));
		assertThrows(IllegalArgumentException.class, () -> s.add(5, 3, -1));
		assertThrows(IllegalArgumentException.class, () -> s.add(5, 3, 10));
		assertDoesNotThrow(() -> s.add(5, 3, 0));
		assertThrows(IllegalArgumentException.class, () -> s.get(5, -1));
		assertThrows(IllegalArgumentException.class, () -> s.get(10, 2));
	}
	
	@Test
	void testRemove() {
		s.add(2, 4, 5);
		s.remove(2, 4);
		assertEquals(0, s.get(2, 4));
	}
	
	@Test
	void testRemoveThrowsCorrectExceptions() {
		assertThrows(IllegalArgumentException.class, () -> s.remove(9, 2));
		assertThrows(IllegalArgumentException.class, () -> s.remove(3, -1));
	}
	
	
	
	@Test
	void testClear() {
		s.add(2, 3, 4);
		s.add(4, 2, 9);
		s.clear();
		assertEquals(0, s.get(2, 3));
		assertEquals(0, s.get(4, 2));
	}
	
	@Test
	void testSetMatrix() {
		int [][] m1 = new int[9][9];
		m1[2][3] = 9;
		m1[3][3] = 2;
		m1[4][5] = 6;
		s.setMatrix(m1);
		assertEquals(9, s.get(2, 3));
		assertEquals(2, s.get(3, 3));
		assertEquals(6, s.get(4, 5));		
	}

	@Test
	void testGetMatrix() {
		s.add(4, 6, 5);
		s.add(2, 6, 8);
		s.add(8, 3, 1);
		int [][] m1 = new int[9][9];
		m1[4][6] = 5;
		m1[2][6] = 8;
		m1[8][3] = 1;
		assertArrayEquals(m1, s.getMatrix());
	}

	@Test
	void testSetMatrixGeneratesCorrectExceptions() {
		int [][] m1 = new int[8][9];
		assertThrows(IllegalArgumentException.class, () -> s.setMatrix(m1));
		int [][] m2  = new int[9][10];
		assertThrows(IllegalArgumentException.class, () -> s.setMatrix(m2));
		int [][] m3  = new int[9][9];
		m3[8][8] = 10;
		assertThrows(IllegalArgumentException.class, () -> s.setMatrix(m3));

	}

	@Test
	void testIsValid() {
		s.add(0, 0, 1);
		assertTrue(s::isValid);
		s.add(1, 1, 1);
		assertFalse(s::isValid);
		s.clear();
		s.add(3, 3, 7);
		s.add(4, 5, 7);
		assertFalse(s::isValid);
		s.clear();
		s.add(3, 3, 7);
		s.add(4, 5, 8);
		assertTrue(s::isValid);
	}

	@Test
	void testSolveOfEmpty() {
		assertTrue(s::solve);
		assertTrue(s::isValid);

	}
	@Test
	void testIllegalSolveWithDuplicates() {
		s.add(0, 0, 5);
		s.add(1, 0, 5);
		assertFalse(s::solve);
		s.clear();
		s.add(0, 0, 5);
		s.add(0, 4, 5);
		assertFalse(s::solve);
		s.clear();
		s.add(2, 2, 5);
		s.add(1, 1, 5);
		assertFalse(s::solve);
	}
	
	@Test
	void testIllegalSolveWithBoardLayout() {
		s.add(0, 0, 1);
		s.add(0, 1, 2);
		s.add(0, 2, 3);
		s.add(1, 0, 4);
		s.add(1, 1, 5);
		s.add(1, 2, 6);
		s.add(2, 3, 7);
		assertFalse(s::solve);
		s.remove(2, 3);
		assertTrue(s::solve);		
	}
	
	@Test
	void testClearAndSolve() {
		s.add(0, 0, 5);
		s.add(1, 0, 5);
		assertFalse(s::solve);
		s.clear();
		assertTrue(s::solve);
	}
	
	@Test
	void testSolveWithRealSudoku() throws FileNotFoundException{
		s.add(0, 2, 8);
		s.add(0, 5, 9);
		s.add(0, 7, 6);
		s.add(0, 8, 2);
		s.add(1, 8, 5);
		s.add(2, 0, 1);
		s.add(2, 2, 2);
		s.add(2, 3, 5);
		s.add(3, 3, 2);
		s.add(3, 4, 1);
		s.add(3, 7, 9);
		s.add(4, 1, 5);
		s.add(4, 6, 6);
		s.add(5, 0, 6);
		s.add(5, 7, 2);
		s.add(5, 8, 8);
		s.add(6, 0, 4);
		s.add(6, 1, 1);
		s.add(6, 3, 6);
		s.add(6, 5, 8);
		s.add(7, 0, 8);
		s.add(7, 1, 6);
		s.add(7, 4, 3);
		s.add(7, 6, 1);
		s.add(8, 6, 4);
		assertTrue(s::solve);
		assertArrayEquals(generateSolvedSudoku(), s.getMatrix());
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(s.get(i, j)+ " ");
			}
			System.out.println();
		}
	}
	
	private int[][] generateSolvedSudoku() throws FileNotFoundException{
		Scanner scan = new Scanner(new File("src/sudokuTest/solvedSudoku"));
		int[][] solved = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				solved[i][j] = scan.nextInt();
			}
			
		}
		scan.close();
		return solved;
	}
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}
