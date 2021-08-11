import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class CheckersPanel extends JPanel{
	int[][] board;
	double width;
	double height;
	
	public CheckersPanel(int[][] in) {
		board = in;
	}
	public void setCells(int[][]newboard) {
		board = newboard;
	}
	
	public void paintComponent(Graphics g) { //Painting the board based on the int array	
		super.paintComponent(g);
		width = (double)this.getWidth() / board[0].length;
		height=(double)this.getHeight() / board.length;
	
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[0].length; column++) {
				if (board[row][column] == 0) {
					g.clearRect((int)Math.round(column*width),(int)Math.round(row*height) , (int)width+1, (int)height+1);
				}
				if (board[row][column] == 1) {
					g.setColor(Color.blue);
					g.fillRect((int)Math.round(column*width),(int)Math.round(row*height) , (int)width+1, (int)height+1);
				}
				if (board[row][column] == 2) {
					g.setColor(Color.red);
					g.fillRect((int)Math.round(column*width),(int)Math.round(row*height) , (int)width+1, (int)height+1);
				}
				if (board[row][column] == 3) {
					g.setColor(Color.green);
					g.fillRect((int)Math.round(column*width),(int)Math.round(row*height) , (int)width+1, (int)height+1);
				}
				if (board[row][column] == 4) {
					g.setColor(Color.black);
					g.fillRect((int)Math.round(column*width),(int)Math.round(row*height) , (int)width+1, (int)height+1);
				}
				if (board[row][column] == 5) {
					g.setColor(Color.yellow);
					g.fillRect((int)Math.round(column*width),(int)Math.round(row*height) , (int)width+1, (int)height+1);
				}
				if (board[row][column] == 6) {
					g.setColor(Color.gray);
					g.fillRect((int)Math.round(column*width),(int)Math.round(row*height) , (int)width+1, (int)height+1);
				}
				
			}
		}
		
		//To draw a grid
				g.setColor(Color.black);
				//Vertical and horizontal gridlines
				for (int x = 0; x < board.length; x++) {
					g.drawLine((int)Math.round(x*width), 0, ((int)Math.round(x*width)), this.getHeight());
				}
				for (int y = 0; y < board.length; y++) {
					g.drawLine(0, (int)Math.round(y*height), this.getWidth(), (int)Math.round(y*height));
				}
			
	}
	
}