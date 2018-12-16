package p6;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Test2UI extends JPanel implements ActionListener {
	private Controller controller;
	private JPanel pnl = new JPanel (new BorderLayout(15,15));
	private JPanel pnlNorth = new JPanel (new BorderLayout());
	private JPanel pnlSouth = new JPanel (new BorderLayout(15,0));
	private JPanel pnlGridArr = new JPanel (new GridLayout(7,7));
	private JPanel pnlGridColRight = new JPanel (new GridLayout(7,0));
	private JPanel pnlGridColLeft = new JPanel (new GridLayout(7,0));
	private JLabel lblCol= new JLabel ("Column");
	private JLabel lblRow= new JLabel ("Row");
	private JLabel lblArr7x7= new JLabel ("Array 7x7");
	private JLabel[][] arr7x7 = new JLabel[7][7];
	private JTextField[] arrColLeft = new JTextField[7];
	private JTextField[] arrColRight = new JTextField[7];
	private JButton btnMoveLeft = new JButton("<-- Move Left");
	private JButton btnMoveRight = new JButton("Move Right -->");
	public Test2UI(Controller controller) {	
		this.controller=controller;
		this.controller.setUserInput(this);
		
		setPreferredSize (new Dimension(450,450));
		setAlignment();
		lblRow.setPreferredSize(new Dimension(40,0));
		lblArr7x7.setPreferredSize(new Dimension(0,50));
		setBackground();
		setGrid();
		setColRight();
		setColLeft();
		pnlNorth.add(lblArr7x7, BorderLayout.CENTER);
		pnlSouth.add(btnMoveLeft, BorderLayout.WEST);
		pnlSouth.add(btnMoveRight, BorderLayout.EAST);
		pnl.add(pnlNorth, BorderLayout.NORTH);
		pnl.add(pnlGridColLeft, BorderLayout.WEST);
		pnl.add(pnlGridArr, BorderLayout.CENTER);
		pnl.add(pnlGridColRight, BorderLayout.EAST);
		pnl.add(pnlSouth, BorderLayout.SOUTH);
		add(pnl);
		setButton();
	}
	private void setButton() {
		btnMoveLeft.addActionListener( this );
		btnMoveRight.addActionListener( this );
	}
	private void setBackground() {
		pnlGridArr.setBackground(Color.LIGHT_GRAY);
		pnlGridColRight.setBackground(Color.WHITE);
		pnlGridColLeft.setBackground(Color.WHITE);
	}
	private void setAlignment() {
		lblCol.setHorizontalAlignment(SwingConstants.CENTER);
		lblCol.setVerticalAlignment(SwingConstants.CENTER);
		lblRow.setHorizontalAlignment(SwingConstants.CENTER);
		lblRow.setVerticalAlignment(SwingConstants.CENTER);
		lblArr7x7.setHorizontalAlignment(SwingConstants.CENTER);
		lblArr7x7.setVerticalAlignment(SwingConstants.CENTER);
	}
	private void setColLeft() {
		for(int i=0; i<arrColLeft.length; i++) {
			arrColLeft[i] = new JTextField("0");
			arrColLeft[i].setPreferredSize(new Dimension(40,40));
			arrColLeft[i].setHorizontalAlignment(SwingConstants.CENTER);
			arrColLeft[i].setBorder( LineBorder.createBlackLineBorder());
			pnlGridColLeft.add(arrColLeft[i]);
		}
	}
	private void setColRight() {
		for(int i=0; i<arrColRight.length; i++) {
			arrColRight[i] = new JTextField("0");
			arrColRight[i].setPreferredSize(new Dimension(40,40));
			arrColRight[i].setHorizontalAlignment(SwingConstants.CENTER);
			arrColRight[i].setBorder( LineBorder.createBlackLineBorder());
			pnlGridColRight.add(arrColRight[i]);
		}
	}
	private void setGrid() {
		for(int i=0; i<arr7x7.length; i++) {
			for(int j=0; j<arr7x7[i].length; j++) {
				if(arr7x7[i][j]==null) {
					arr7x7[i][j] = new JLabel();
				}
				if(arr7x7[i][j].getText().isEmpty()) {
					arr7x7[i][j].setText("0");
				}
				arr7x7[i][j].setPreferredSize(new Dimension(40,40));
				arr7x7[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				arr7x7[i][j].setVerticalAlignment(SwingConstants.CENTER);
				arr7x7[i][j].setBorder( LineBorder.createBlackLineBorder());

				pnlGridArr.add(arr7x7[i][j]);

			}
		}
	}

	public void setColRight(Array7 a7) {
		int[] arr = a7.toIntArray();
		for(int i=0;i<arr.length;i++) {
			arrColRight[i].setText(Integer.toString(arr[i]));
		}
	}
	public void setColumnLeft(Array7 a7) {
		int[] arr = a7.toIntArray();
		for(int i=0;i<arr.length;i++) {
			arrColLeft[i].setText(Integer.toString(arr[i]));
		}
	}
	public void setArray7x7(Array7x7 array) {
		for(int i=0;i<arr7x7.length;i++) {
			for (int j=0;j<arr7x7.length;j++) {
				arr7x7[i][j].setText(Integer.toString(array.getElement(i, j)));
			}
		}
	}
	private int[][] toInt2DArray(JLabel[][] lbl) {
		int[][] arr = new int[lbl.length][lbl.length];
		for(int i=0;i<lbl.length;i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j]=Integer.parseInt(lbl[i][j].getText());
			}
		}
		return arr;
	}
	private int[] toIntArray(JTextField[] tf) {
		int[] arr = new int[tf.length];
		for(int i=0;i<tf.length;i++) {
			arr[i]=Integer.parseInt(tf[i].getText());
		}
		return arr;
	}

	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == btnMoveRight ) {
			Array7x7 a7x7 = new Array7x7();
			Array7 a7 = new Array7();
			a7x7.setArray(toInt2DArray(arr7x7));
			a7.setArray(toIntArray(arrColLeft));
			controller.moveRight(a7);
		}
		else if( e.getSource() == btnMoveLeft ) {
			Array7x7 a7x7 = new Array7x7();
			Array7 a7 = new Array7();
			a7x7.setArray(toInt2DArray(arr7x7));
			a7.setArray(toIntArray(arrColRight));
			controller.moveLeft(a7);
		}
	}
	public static void test2() {
		Array7x7 arr7x7 = new Array7x7();
		Array7 leftColumn = new Array7();
		Array7 rightColumn = new Array7();
		Controller controller = new Controller(null, rightColumn, leftColumn, arr7x7);
		Test2UI ui = new Test2UI(controller);
		JFrame frame = new JFrame( "Viewer" );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.add( ui );
		frame.pack();
		frame.setVisible( true );
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)Math.round((screenSize.getWidth()/2)-(frame.getWidth()/1.5));
		int y = (int)Math.round((screenSize.getHeight()/2)-(frame.getHeight()/1.5));
		frame.setLocation(x, y);
	}
	public static void main(String[] args) {
		test2();
	}
}


