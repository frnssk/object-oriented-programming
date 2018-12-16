package p6;

public class Controller {
	private Viewer viewer;
	private Array7x7 array;
	private Array7 leftCol;
	private Array7 rightCol;
	private Test2UI ui;
	
	public Controller(Viewer viewer, Array7x7 array) {
		this.viewer = viewer;
		this.array = array;
	}
	public Controller(Test2UI ui, Array7 rightCol, Array7 leftCol, Array7x7 array) {
		this.ui=ui;
		this.rightCol=rightCol;
		this.leftCol=leftCol;
		this.array=array;
	}
	/**
	 * Reads the row of the array given 
	 * by the parameter and displays it.
	 * 
	 * @param row  which row will be read 
	 */
	public void readRow(int row) {
		Array7 a7 = array.getRow(row);
		viewer.setArray7x7(array);
		viewer.setRow(a7);
	}
	/**
	 * Reads the column of the array given 
	 * by the parameter and displays it.
	 * 
	 * @param col  which column will be read 
	 */
	public void readCol(int col) {
		Array7 a7 = array.getCol(col);
		viewer.setArray7x7(array);
		viewer.setColum(a7);
	}
	
	/**
	 * Writes the array given by the parameter
	 * on the row given by the parameter
	 * 
	 * @param row  in which row the array will be written
	 * @param arr7  the array to be written
	 */
	public void writeRow(int row, Array7 arr7) {
		array.setRow(row, arr7);
		viewer.setRowInArray(row,arr7);
	}

	/**
	 * Writes the array given by the parameter
	 * on the column given by the parameter
	 * 
	 * @param col  in which column the array will be written
	 * @param arr7  the array to be written
	 */
	public void writeCol(int col, Array7 arr7) {
		array.setCol(col, arr7);
		viewer.setColumInArray(col,arr7);
	}
	/**
	 * Moves the array to the right
	 * 
	 * @param arr7 an Array7-object
	 */
	public void moveRight(Array7 arr7) {
		Array7 a7 = array.shiftRight(arr7);
		ui.setArray7x7(array);
		ui.setColRight(a7);
		
	}
	/**
	 * Moves the array to the left
	 * 
	 * @param arr7 an Array7-object
	 */
	public void moveLeft(Array7 arr7) {
		Array7 a7 = array.shiftLeft(arr7);
		ui.setArray7x7(array);
		ui.setColumnLeft(a7);
	}
	public void setUserInput(Test2UI test2ui) {
		this.ui = test2ui;
	}
}
