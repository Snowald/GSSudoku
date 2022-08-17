package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class sudokuViewController {


	public static void main(String[] args) {
		SudokuSolver s = new Sudoku();
		new sudokuViewController("SudokuSolver", s);
	}

	private String title;
	private SudokuSolver model;

	public sudokuViewController(String title, SudokuSolver model) {
		this.title = title;
		this.model = model;
		SwingUtilities.invokeLater(this::createWindow);
	}

	private void createWindow() {
		generateView();
		generateController();
		frame.pack();
		frame.setVisible(true);
	}

	// Components in the view
	private JFrame frame;
	private Container pane;
	private JPanel gridWithSudoku;
	private JTextField[] numbers;
	private Container southPanel;
	private JButton clear;
	private JButton solve;

	private void generateView() {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane = frame.getContentPane();

		southPanel = new JPanel();
		pane.add(southPanel, BorderLayout.SOUTH);

		solve = new JButton("Solve");
		clear = new JButton("Clear");
		southPanel.add(solve);
		southPanel.add(clear);

		numbers = new JTextField[81];
		gridWithSudoku = generateSudokuGrid();
		pane.add(gridWithSudoku);

	}

	private JPanel generateSudokuGrid() {
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(9, 9));

		for (int i = 0; i < 81; i++) {

			JTextField box = new JTextField();
			numbers[i] = box;
			box.setHorizontalAlignment(JTextField.CENTER);
			box.setPreferredSize(new Dimension(60, 60));
			box.setFont(new Font("", Font.BOLD, 25));
			box.addKeyListener(new solveOnEnter());
			int row = i / 9;
			int col = i % 9;
			if ((2 < row && row < 6) ^ (2 < col && col < 6)) {
				box.setBackground(Color.magenta);
			}

			grid.add(box);
		}
		setNumbersInGrid();
		return grid;
	}

	private void setNumbersInGrid() {
		for (int i = 0; i < 81; i++) {
			JTextField box = numbers[i];
			int row = i / 9;
			int col = i % 9;
			int numberFromModel = model.get(row, col);
			String text = numberFromModel == 0 ? "" : String.valueOf(numberFromModel);
			box.setText(text);

		}
	}

	private void setNumbersInModel() throws IllegalArgumentException {
		for (int i = 0; i < 81; i++) {
			JTextField box = numbers[i];
			int row = i / 9;
			int col = i % 9;
			String text = box.getText().trim();
			int number = text.equals("") ? 0 : Integer.parseInt(text);
			model.add(row, col, number);
		}
	}

	private void generateController() {
		clear.addActionListener(event -> {
			model.clear();
			setNumbersInGrid();
		});

		solve.addActionListener(event -> {
			try {
				setNumbersInModel();
				if (!model.solve()) {
					JOptionPane.showMessageDialog(pane, "Unsolvable sudoku");
				}
				setNumbersInGrid();
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(pane, "Unacceptable value in sudoku");
			}
		});

	}

	private class solveOnEnter implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				solve.doClick();
			}
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}

}
