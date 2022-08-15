package sudokuTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
	void test() {
		fail("Not yet implemented");
	}

}
