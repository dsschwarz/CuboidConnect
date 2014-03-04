import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TicTacToe3D
extends JFrame
implements ActionListener
{
	public String playerActionType = "place";

	public int X = 0;
	public int Y = 1;
	public int Z = 2;

	public int I = 0;
	public int J = 1;
	public int K = 2;
	public int Size = 3;
	int[][][] space = new int[Size][Size][Size];
	int[] dirVar = { X, Y, Z }; // X is going top to bottom, Y is columns (left to right), Z is depth
	int[] dir = { 1, 1, 1 };
	int playerTurn = 1;
	public boolean showDepth = true;
	
	public JButton buttonLeft = new JButton();
	public JButton buttonRight = new JButton();
	public JButton buttonUp = new JButton();
	public JButton buttonDown = new JButton();
	
	public JButton button00 = new JButton();
	public JButton button01 = new JButton();
	public JButton button02 = new JButton();
	public JButton button10 = new JButton();
	public JButton button11 = new JButton();
	public JButton button12 = new JButton();
	public JButton button20 = new JButton();
	public JButton button21 = new JButton();
	public JButton button22 = new JButton();

	public JLabel output = new JLabel("Player one's turn: Place a Piece");
	public JButton showDepthToggle = new JButton();
	public JButton reset = new JButton();
	public GPanel panel = new GPanel();
	public int end = 0;
	private static final long serialVersionUID = 1L;

	public TicTacToe3D()
	{
		super("Rotational Connect-Three");
		buttonLeft.setMnemonic(37);
		buttonRight.setMnemonic(39);
		buttonUp.setMnemonic(38);
		buttonDown.setMnemonic(40);
		button00.addActionListener(this);
		button01.addActionListener(this);
		button02.addActionListener(this);
		button10.addActionListener(this);
		button11.addActionListener(this);
		button12.addActionListener(this);
		button20.addActionListener(this);
		button21.addActionListener(this);
		button22.addActionListener(this);
		
		buttonLeft.addActionListener(this);
		buttonRight.addActionListener(this);
		buttonUp.addActionListener(this);
		buttonDown.addActionListener(this);
		
		button00.setBounds(80, 50, 45, 30);
		button01.setBounds(150, 50, 45, 30);
		button02.setBounds(220, 50, 45, 30);
		button10.setBounds(80, 100, 45, 30);
		button11.setBounds(150, 100, 45, 30);
		button12.setBounds(220, 100, 45, 30);
		button20.setBounds(80, 150, 45, 30);
		button21.setBounds(150, 150, 45, 30);
		button22.setBounds(220, 150, 45, 30);

		button00.setMargin(new java.awt.Insets(1, 2, 1, 2));
		button01.setMargin(new java.awt.Insets(1, 2, 1, 2));
		button02.setMargin(new java.awt.Insets(1, 2, 1, 2));
		button10.setMargin(new java.awt.Insets(1, 2, 1, 2));
		button11.setMargin(new java.awt.Insets(1, 2, 1, 2));
		button12.setMargin(new java.awt.Insets(1, 2, 1, 2));
		button20.setMargin(new java.awt.Insets(1, 2, 1, 2));
		button21.setMargin(new java.awt.Insets(1, 2, 1, 2));
		button22.setMargin(new java.awt.Insets(1, 2, 1, 2));
		
		buttonLeft.setBounds(20, 100, 45, 30);
		buttonRight.setBounds(280, 100, 45, 30);
		buttonUp.setBounds(150, 0, 45, 30);
		buttonDown.setBounds(150, 200, 45, 30);
		output.setBounds(150, 240, 600, 30);
		
		showDepthToggle.setBounds(0, 210, 100, 30);
		showDepthToggle.setMargin(new java.awt.Insets(1, 2, 1, 2));
		showDepthToggle.setText("Show Depth");
		showDepthToggle.addActionListener(this);
		
		reset.setBounds(0, 250, 100, 30);
		reset.setMargin(new java.awt.Insets(1, 2, 1, 2));
		reset.setText("Reset");
		reset.addActionListener(this);

		panel.setLayout(null);
		panel.setBounds(80, 60, 500, 400);

		panel.add(button00);
		panel.add(button01);
		panel.add(button02);
		panel.add(button10);
		panel.add(button11);
		panel.add(button12);
		panel.add(button20);
		panel.add(button21);
		panel.add(button22);
		
		panel.add(buttonLeft);
		panel.add(buttonRight);
		panel.add(buttonUp);
		panel.add(buttonDown);
		panel.add(output);
		panel.add(showDepthToggle);
		panel.add(reset);


		setBounds(100, 100, 500, 400);
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		add(panel);
		setLayout(null);
		setVisible(true);
		for (int i = 0; i < Size; i++) {
			for (int j = 0; j < Size; j++) {
				for (int k = 0; k < Size; k++) {
					this.space[i][j][k] = 0;
				}
			}
		}
	}

	public void actionPerformed(ActionEvent event)
	{
		boolean invalid = false;
		int row = 0;int col = 0;
		Object source = event.getSource();

		if (source == showDepthToggle)
		{
			showDepth = !showDepth;
			displayTable();
			return;
		}
		else if (source == reset)
		{
			for (int i = 0; i < Size; i++) {
				for (int j = 0; j < Size; j++) {
					for (int k = 0; k < Size; k++) {
						this.space[i][j][k] = 0;
					}
				}
			}
			dirVar[0] = X;
			dirVar[1] = Y;
			dirVar[2] = Z;
			
			dir[0] = 1;
			dir[1] = 1;
			dir[2] = 1;
			
			playerActionType = "place";
			playerTurn = 1;
			displayTable();
			return;
		}
		
		if (this.playerActionType == "place")
		{
			if (source == button00)
			{
				row = 0;
				col = 0;
			}
			else if (source == button01)
			{
				row = 0;
				col = 1;
			}
			else if (source == button02)
			{
				row = 0;
				col = 2;
			}
			else if (source == button10)
			{
				row = 1;
				col = 0;
			}
			else if (source == button11)
			{
				row = 1;
				col = 1;
			}
			else if (source == button12)
			{
				row = 1;
				col = 2;
			}
			else if (source == button20)
			{
				row = 2;
				col = 0;
			}
			else if (source == button21)
			{
				row = 2;
				col = 1;
			}
			else if (source == button22)
			{
				row = 2;
				col = 2;
			}
			else
			{
				invalid = true;
			}
			if (!invalid) {
				placePiece(row, col);
			}
			// Only other option
		}
		else if (this.playerActionType == "rotate")
		{
			// Assume a rotate button was pressed
			this.playerActionType = "place";
			if (source == buttonUp)
			{
				int temp1 = this.dirVar[I];
				this.dirVar[I] = this.dirVar[K];
				this.dirVar[K] = temp1;
				int temp2 = this.dir[I];
				this.dir[I] = this.dir[K];
				this.dir[K] = (-temp2);
				System.out.print("Up");
				output.setText("Player " + this.playerTurn + " 's turn: Place a Piece");
			}
			else if (source == buttonRight)
			{
				int temp1 = this.dirVar[J];
				this.dirVar[J] = this.dirVar[K];
				this.dirVar[K] = temp1;
				int temp2 = this.dir[J];
				this.dir[J] = (-this.dir[K]);
				this.dir[K] = temp2;
				System.out.print("Right");
				output.setText("Player " + this.playerTurn + " 's turn: Place a Piece");
			}
			else if (source == buttonDown)
			{
				int temp1 = this.dirVar[I];
				this.dirVar[I] = this.dirVar[K];
				this.dirVar[K] = temp1;
				int temp2 = this.dir[I];
				this.dir[I] = (-this.dir[K]);
				this.dir[K] = temp2;
				System.out.print("Down");
				output.setText("Player " + this.playerTurn + " 's turn: Place a Piece");
			}
			else if (source == buttonLeft)
			{
				int temp1 = this.dirVar[J];
				this.dirVar[J] = this.dirVar[K];
				this.dirVar[K] = temp1;
				int temp2 = this.dir[J];
				this.dir[J] = this.dir[K];
				this.dir[K] = (-temp2);
				System.out.print("Left");
				output.setText("Player " + this.playerTurn + " 's turn: Place a Piece");
			} else {
				this.playerActionType = "rotate"; // Revert if not a rotate button
			}
			displayTable();
		}
	}

	public boolean checkWin()
	{
		for (int i = 0; i < Size; i++) {
			for (int j = 0; j < Size; j++)
			{
				// Check every possible row for a straight line
				// There are 27 possible ways to win

				// Going down
				for (int k = 0; k < Size; k++)
				{
					if (this.space[i][j][k] != this.playerTurn) {
						break;
					}
					if (k == Size - 1) {
						return true;
					}
				}

				// Going across, left -> right
				for (int k = 0; k < Size; k++)
				{
					if (this.space[i][k][j] != this.playerTurn) {
						break;
					}
					if (k == Size - 1) {
						return true;
					}
				}

				// Going across, top -> bottom
				for (int k = 0; k < Size; k++)
				{
					if (this.space[k][j][i] != this.playerTurn) {
						break;
					}
					if (k == Size - 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void placePiece(int row, int col)
	{
		row = 1 + (row - 1) * this.dir[I];
		System.out.println("Row: " + row);
		col = 1 + (col - 1) * this.dir[J];
		System.out.println("Col: " + col);

		if ((this.dirVar[I] == X) && (this.dirVar[J] == Y) && (this.space[row][col][(1 - this.dir[K])] == 0))
		{
			int i = 1 - this.dir[K];
			do
			{
				if (((i != 1 + this.dir[K]) && (this.space[row][col][(i + this.dir[K])] != 0)) || (i == 1 + this.dir[K]))
				{
					this.space[row][col][i] = this.playerTurn;
					this.playerActionType = "rotate";
					System.out.println("Placed1: " + i);
					break;
				}
				i += this.dir[K];
				if (i >= Size) {
					break;
				}
			} while (i >= 0);
		}
		else if ((this.dirVar[I] == X) && (this.dirVar[J] == Z) && (this.space[row][(1 - this.dir[K])][col] == 0))
		{
			int i = 1 - this.dir[K];
			do
			{
				if (((i != 1 + this.dir[K]) && (this.space[row][(i + this.dir[K])][col] != 0)) || (i == 1 + this.dir[K]))
				{
					this.space[row][i][col] = this.playerTurn;
					this.playerActionType = "rotate";
					System.out.println("Placed2: " + i);
					break;
				}
				i += this.dir[K];
				if (i >= Size) {
					break;
				}
			} while (i >= 0);
		}
		else if ((this.dirVar[I] == 1) && (this.dirVar[J] == 2) && (this.space[(1 - this.dir[K])][row][col] == 0))
		{
			int i = 1 - this.dir[K];
			do
			{
				if (((i != 1 + this.dir[K]) && (this.space[(i + this.dir[K])][row][col] != 0)) || (i == 1 + this.dir[K]))
				{
					this.space[i][row][col] = this.playerTurn;
					this.playerActionType = "rotate";
					System.out.println("Placed3: " + i);
					break;
				}
				i += this.dir[K];
				if (i >= Size) {
					break;
				}
			} while (i >= 0);
		}
		else if ((this.dirVar[I] == 1) && (this.dirVar[J] == 0) && (this.space[col][row][(1 - this.dir[K])] == 0))
		{
			int i = 1 - this.dir[K];
			do
			{
				if (((i != 1 + this.dir[K]) && (this.space[col][row][(i + this.dir[K])] != 0)) || (i == 1 + this.dir[K]))
				{
					this.space[col][row][i] = this.playerTurn;
					this.playerActionType = "rotate";
					System.out.println("Placed4: " + i);
					break;
				}
				i += this.dir[K];
				if (i >= Size) {
					break;
				}
			} while (i >= 0);
		}
		else if ((this.dirVar[I] == 2) && (this.dirVar[J] == 0) && (this.space[col][(1 - this.dir[K])][row] == 0))
		{
			int i = 1 - this.dir[K];
			do
			{
				if (((i != 1 + this.dir[K]) && (this.space[col][(i + this.dir[K])][row] != 0)) || (i == 1 + this.dir[K]))
				{
					this.space[col][i][row] = this.playerTurn;
					this.playerActionType = "rotate";
					System.out.println("Placed5: " + i);
					break;
				}
				i += this.dir[K];
				if (i >= Size) {
					break;
				}
			} while (i >= 0);
		}
		else if ((this.dirVar[I] == 2) && (this.dirVar[J] == 1) && (this.space[(1 - this.dir[K])][col][row] == 0))
		{
			for (int i = 1 - this.dir[K]; (i < Size) && (i >= 0); i += this.dir[K]) {
				if (((i != 1 + this.dir[K]) && (this.space[(i + this.dir[K])][col][row] != 0)) || (i == 1 + this.dir[K]))
				{
					this.space[i][col][row] = this.playerTurn;
					this.playerActionType = "rotate";
					System.out.println("Placed6: " + i);
					break;
				}
			}
		}
		if (this.playerActionType == "rotate")
		{
			displayTable();
			if (checkWin())
			{
				output.setText("Player " + this.playerTurn + " Wins!");
				System.out.println("Winner!");
			}
			else
			{
				if (this.playerTurn == 1) {
					this.playerTurn = 2;
				} else {
					this.playerTurn = 1;
				}
				output.setText("Player " + this.playerTurn + " 's turn: Rotate Cube");
			}
		}
	}
	
	// UPDATE DISPLAY
	void displayTable()
	{
		String[] charVar = new String[9];
		for (int i = 0; i < 9; i++) {
			charVar[i] = " ";
		}
		int x = 0;int y = 0;int z = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				for (int k = -1; k <= 1; k++)
				{
					if (this.dirVar[I] == X) {
						x = 1 + i * this.dir[I];
					} else if (this.dirVar[I] == Y) {
						y = 1 + i * this.dir[I];
					} else if (this.dirVar[I] == Z) {
						z = 1 + i * this.dir[I];
					}
					if (this.dirVar[J] == X) {
						x = 1 + j * this.dir[Y];
					} else if (this.dirVar[J] == Y) {
						y = 1 + j * this.dir[Y];
					} else if (this.dirVar[J] == Z) {
						z = 1 + j * this.dir[Y];
					}
					if (this.dirVar[K] == X) {
						x = 1 + k * this.dir[Z];
					} else if (this.dirVar[K] == Y) {
						y = 1 + k * this.dir[Z];
					} else if (this.dirVar[K] == Z) {
						z = 1 + k * this.dir[Z];
					}
					if (this.space[x][y][z] == 2)
					{
						charVar[((i + 1) * Size + (j + 1))] = "X";
						if (showDepth) {
							charVar[((i + 1) * Size + (j + 1))] += "(" + (k+2) + ")";
						}
						break;
					}
					if (this.space[x][y][z] == 1)
					{
						charVar[((i + 1) * Size + (j + 1))] = "0";
						if (showDepth) {
							charVar[((i + 1) * Size + (j + 1))] += "(" + (k+2) + ")";
						}
						break;
					}
					
					charVar[((i + 1) * Size + (j + 1))] = "";
				}
			}
			panel.repaint();
		}
		System.out.println(this.dir[I] + " " + this.dirVar[I]);
		System.out.println(this.dir[J] + " " + this.dirVar[J]);
		System.out.println(this.dir[K] + " " + this.dirVar[K]);
		button00.setText(charVar[0]);
		button01.setText(charVar[1]);
		button02.setText(charVar[2]);
		button10.setText(charVar[3]);
		button11.setText(charVar[4]);
		button12.setText(charVar[5]);
		button20.setText(charVar[6]);
		button21.setText(charVar[7]);
		button22.setText(charVar[8]);
	}
	class GPanel extends JPanel {
		private static final long serialVersionUID= 2L;
	    public GPanel() {
	        
	    }

	    @Override
	    public void paintComponent(Graphics g1) {
	        super.paintComponent(g1);
	        Graphics2D g = (Graphics2D)g1.create();
	        Color[] colors = {Color.BLUE, Color.GREEN, Color.RED, Color.CYAN, Color.ORANGE, Color.MAGENTA};
	        g.setStroke(new BasicStroke(4));
	        
	        // Top
	        g.setColor(colors[dirVar[X] + (int)(1.5 - 1.5*dir[X])]); // Yields 0, 1, 3 for positive dir, 3, 4, 5 for negative
			g.drawLine(80, 45, 265, 45);
		
			// Right
	        g.setColor(colors[dirVar[Y] + (int)(1.5 + 1.5*dir[Y])]);
			g.drawLine(270, 50, 270, 180);
			
			// Bottom
	        g.setColor(colors[dirVar[X] + (int)(1.5 + 1.5*dir[X])]); // Opposite from top
			g.drawLine(80, 185, 265, 185);
			
			// Left
	        g.setColor(colors[dirVar[Y] + (int)(1.5 - 1.5*dir[Y])]); // Opposite from right
			g.drawLine(75, 50, 75, 180);

			// Interior lines
	        g.setStroke(new BasicStroke(2));
	        g.setColor(colors[dirVar[Z] + (int)(1.5 - 1.5*dir[Z])]);
			g.drawLine(85, 90, 260, 90);
			g.drawLine(85, 140, 260, 140);
			g.drawLine(137, 55, 137, 175);
			g.drawLine(208, 55, 208, 175);
	    }
	}
	public static void main(String[] args)
	{
		new TicTacToe3D();
	}
}
