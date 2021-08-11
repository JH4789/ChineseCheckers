import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ChineseCheckers implements MouseListener, ActionListener {
	int[][] board = new int[25][17];
	boolean running = true;
	JFrame frame = new JFrame();
	Container container = new Container();
	int turn =1;
	boolean blueAI;
	boolean redAI;
	boolean greenAI;
	boolean blackAI;
	boolean yellowAI;
	boolean grayAI;
	boolean gamerunning;
	int playercount;
	int startingrow;
	int moverow;
	int startingcolumn;
	int movecolumn;
	boolean validmove;
	int processingrow;
	boolean multiplejump = false;
	int processingcolumn;
	boolean movement = false;
	boolean validmoverunning = true;
	CheckersPanel panel = new CheckersPanel(board);
	JButton checkwin = new JButton("Check for a win");
	JButton blueAi = new JButton("Blue AI");
	JButton redAi = new JButton("Red AI");
	JButton greenAi = new JButton("Green AI");
	JButton blackAi = new JButton("Black AI");
	JButton yellowAi = new JButton("Yellow AI");
	JButton grayAi = new JButton("Gray AI");
	JButton startgame = new JButton("Start Game");
	Container buttoncontainer = new Container();
	public ChineseCheckers() {
		frame.setSize(600, 600);
		
		System.out.println("Please enter the number of players in this game");
		Scanner playerscanner = new Scanner(System.in); //Checking for number of players
		String playerprocessingcount = playerscanner.nextLine();
		if(playerprocessingcount.equals("2") == false && playerprocessingcount.equals("3") == false && playerprocessingcount.equals("4") == false && playerprocessingcount.equals("6") == false) {
			System.out.println("Please enter a valid input");
			System.exit(0);
		}
		playercount = Integer.parseInt(playerprocessingcount);
		panel.addMouseListener(this);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		
		//Hardcoding galore!
		//Blue
		startgame.addActionListener(this);
		startgame.setEnabled(true);
		checkwin.addActionListener(this);
		checkwin.setEnabled(true);
		buttoncontainer.setLayout(new GridLayout(1,2));
		buttoncontainer.add(startgame);
		buttoncontainer.add(checkwin);
		frame.add(buttoncontainer, BorderLayout.SOUTH);
		//The if statements below change the board based on the number of players
		if(playercount ==2 || playercount == 3 || playercount ==4 || playercount ==6 ) {
			board[12][0] = 1;
			board[11][1] = 1;
			board[13][1] = 1;
			board[10][2] = 1;
			board[12][2] = 1;
			board[14][2] = 1;
			board[9][3] = 1;
			board[11][3] = 1;
			board[13][3] = 1;
			board[15][3] = 1;
		}
		
		//Red
		if(playercount ==2 || playercount ==4 || playercount ==6 ) {
		board[12][16] = 2;
		board[11][15] = 2;
		board[13][15] = 2;
		board[10][14] = 2;
		board[12][14] = 2;
		board[14][14] = 2;
		board[9][13] = 2;
		board[11][13] = 2;
		board[13][13] = 2;
		board[15][13] = 2;
		}
		else {
			board[12][16] = 7;
			board[11][15] = 7;
			board[13][15] = 7;
			board[10][14] = 7;
			board[12][14] = 7;
			board[14][14] = 7;
			board[9][13] = 7;
			board[11][13] = 7;
			board[13][13] = 7;
			board[15][13] = 7;
		}
		//Green
		if(playercount ==6 ) {
		board[0][4] =3;
		board[1][5] =3;
		board[2][4] =3;
		board[2][6] =3;
		board[3][5] =3;
		board[3][7] =3;
		board[4][4] =3;
		board[6][4] =3;
		board[5][5] =3;
		board[4][6] =3;
		}
		else {
			board[0][4] =7;
			board[1][5] =7;
			board[2][4] =7;
			board[2][6] =7;
			board[3][5] =7;
			board[3][7] =7;
			board[4][4] =7;
			board[6][4] =7;
			board[5][5] =7;
			board[4][6] =7;
		}
		//Black
		if(playercount ==3 || playercount ==4 || playercount ==6 ) {
		board[0][12] =4;
		board[1][11] =4;
		board[2][12] =4;
		board[2][10] =4;
		board[3][11] =4;
		board[3][9] = 4;
		board[4][12] =4;
		board[6][12] =4;
		board[5][11] =4;
		board[4][10] =4;
		}
		else {
			board[0][12] =7;
			board[1][11] =7;
			board[2][12] =7;
			board[2][10] =7;
			board[3][11] =7;
			board[3][9] = 7;
			board[4][12] =7;
			board[6][12] =7;
			board[5][11] =7;
			board[4][10] =7;
		}
		//Yellow
		if(playercount ==4 || playercount ==6 ) {
		board[24][4] =5;
		board[23][5] =5;
		board[22][4] =5;
		board[22][6] =5;
		board[21][5] =5;
		board[21][7] =5;
		board[20][4] =5;
		board[18][4] =5;
		board[19][5] =5;
		board[20][6] =5;
		}
		else {
			board[24][4] =7;
			board[23][5] =7;
			board[22][4] =7;
			board[22][6] =7;
			board[21][5] =7;
			board[21][7] =7;
			board[20][4] =7;
			board[18][4] =7;
			board[19][5] =7;
			board[20][6] =7;
		}
		//Grey
		if(playercount ==3 || playercount ==6 ) {
		board[24][12] = 6;
		board[23][11] = 6;
		board[22][12] = 6;
		board[22][10] = 6;
		board[21][11] = 6;
		board[21][9] = 6;
		board[20][12] = 6;
		board[18][12] = 6;
		board[19][11] = 6;
		board[20][10] = 6;
		}
		else {
			board[24][12] = 7;
			board[23][11] = 7;
			board[22][12] = 7;
			board[22][10] = 7;
			board[21][11] = 7;
			board[21][9] = 7;
			board[20][12] = 7;
			board[18][12] = 7;
			board[19][11] = 7;
			board[20][10] = 7;
		}
		//Filling in the playable parts of the board that don't have pieces in them
		board[8][4] = 7;
		board[6][8] = 7;
		board[4][8] = 7;
		board[8][8] = 7;
		board[10][8] = 7;
		board[12][8] = 7;
		board[14][8] = 7;
		board[16][8] = 7;
		board[18][8] = 7;
		board[20][8] = 7;
		board[12][4] = 7;
		board[12][6] = 7;
		board[12][8] = 7;
		board[12][10] = 7;
		board[12][12] = 7;
		board[10][4] = 7;
		board[10][6] = 7;
		board[10][8] = 7;
		board[10][10] = 7;
		board[10][12] = 7;
		board[8][10] = 7;
		board[8][12] = 7;
		board[8][6] = 7;
		
		board[7][5] = 7;
		board[7][7] = 7;
		board[7][9]=7;
		board[7][11] =7;
		board[6][6] =7;
		board[6][10] =7;
		board[5][7]=7;
		board[5][9]=7;
		board[14][4] =7;
		board[14][6] =7;
		board[14][10] =7;
		board[14][12] =7;
		board[16][4] =7;
		board[16][6] =7;
		board[16][10] =7;
		board[16][12] =7;
		board[18][6] =7;
		board[18][10] =7;
		board[19][7] = 7;
		board[19][9] =7;
		board[9][5] = 7;
		board[9][7] = 7;
		board[9][9] = 7;
		board[9][11] = 7;
		board[11][5] = 7;
		board[11][7] = 7;
		board[11][9] = 7;
		board[11][11] = 7;
		board[13][5] = 7;
		board[13][7] = 7;
		board[13][9] = 7;
		board[13][11] = 7;
		board[15][5] = 7;
		board[15][7] = 7;
		board[15][9] = 7;
		board[15][11] = 7;
		board[17][5] = 7;
		board[17][7] = 7;
		board[17][9] = 7;
		board[17][11] = 7;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		int startingrow =0;
		int startingcolumn = 0;
		int moverow = 0;
		int movecolumn = 0;
		int rowun = 0;
		int columnun = 0;
		int processingrow = 0;
		int processingcolumn = 0;
		int column =0;
		int row = 0;
		
	}

	
	
	public static void main (String args[]) {
		new ChineseCheckers();
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) { //Two buttons on the frame
		 if (e.getSource().equals(startgame)) {
			 gamerunning = true;
			 System.out.println("The game has started!");
		 }
		 if (e.getSource().equals(checkwin)) {
			checkwin();
		 }
	}
	public void checkwin() { //Checking for a win
		if(	board[12][16] == 1 && board[11][15] == 1 && board[13][15] == 1 && board[10][14] == 1 && board[12][14] == 1 && board[14][14] == 1 
				&& board[9][13] == 1 && board[11][13] == 1 && board[13][13] == 1 && board[15][13] == 1) {
			System.out.println("Blue wins!");
			System.exit(0);
		}
		if(board[12][0] == 2 && board[11][1] == 2 && board[13][1] == 2 && board[10][2] == 2 && board[12][2] == 2 && board[14][2] == 2 && board[9][3] == 2 &&
				board[11][3] == 2 && board[13][3] == 2 && board[15][3] == 2) {
			System.out.println("Red wins!");
			System.exit(0);
		}
		if(board[0][4] == 6 && board[1][5] == 6 && board[2][4] == 6 && board[2][6] == 6 && board[3][5] == 6 && 
				board[3][7] == 6 && board[4][4] == 6 && board[6][4] == 6 && board[5][5] == 6 && board[4][6] == 6) {
			System.out.println("Gray wins!");
			System.exit(0);
		}
		if(board[0][12] ==5 && board[1][11] ==5 && board[2][12] ==5 && board[2][10] ==5 && board[3][11] ==5 && board[3][9] ==5 && board[4][12] ==5 && board[6][12] ==5 && board[5][11] ==5 && board[4][10] ==5) {
			System.out.println("Yellow wins!");
			System.exit(0);
			
		}
		if(board[24][4] ==4 && board[23][5] ==4 && board[22][4] ==4 && board[22][6] ==4 && board[21][5] ==4 
				&& board[21][7] ==4 && board[20][4] ==4 && board[18][4] ==4 && board[19][5] ==4 && board[20][6] ==4) {
			System.out.println("Black wins!");
			System.exit(0);
		}
		if(board[24][12] == 3 && board[23][11] == 3 && board[22][12] == 3 && board[22][10] == 3 && board[21][11] == 3 && board[21][9] == 3 && board[20][12] == 3 && board[18][12] == 3 && board[19][11] == 3 && board[20][10] == 3) {
			System.out.println("Green wins!");
			System.exit(0);
		}
	}
	@Override
	
	public void mouseReleased(MouseEvent event) {
	//Double is used here to make things more accurate
	if(gamerunning == true){//Start game functionality
			double width = (double)panel.getWidth() / board[0].length;
			double height = (double)panel.getHeight() / board.length;
			int column = Math.min(10000,(int)(event.getX() / width));
			int row = Math.min(10000,(int)(event.getY() / height));
			frame.repaint();
			Scanner in = new Scanner(System.in);
			boolean valid = false;
			if (movement == false && multiplejump == false) {
				startingrow = row;
				startingcolumn = column;
			}
			if(movement == true) {
				moverow= row;
				movecolumn = column;
			}
			
			
			running = true;
			if(movement == true && board[row][column] == 7 || movement == false && board[row][column] != 0 && board[row][column] != 7) {
				while(running ==true) {//Break is used here as opposed to setting running to false because there was a bug where clicking on a blue square would print affirmative message and error message at the same time
				if(playercount ==4) { //Lots of repeated code in the following sections, I will only be commenting unique code
					if(board[row][column] == 1 && turn == 1 && movement == false) { //Selecting which piece to move
						System.out.println("You clicked on the blue square");
						System.out.println("Please click on the square that you want to move this piece to");
						board[row][column]=7;
						movement = true;
						break;
					}
					if(board[row][column] == 7 && turn == 1 && movement == true) { //Selecting where the piece will be moved to
						
						
						int rowun = startingrow - moverow;
						
						int columnun = startingcolumn -movecolumn;
						int processingrow = rowun;
						int processingcolumn = columnun;
						if(processingcolumn < 0) { //Positive numbers are needed in order to check for a valid jump move
							processingcolumn = processingcolumn * -1;
						}
						if(processingrow < 0) {
							processingrow = processingrow * -1;
						}
						if(processingrow ==1 && processingcolumn == 1 && multiplejump == false) {//Checking for and adjacent move
							board[moverow][movecolumn] = 1;
							movement =false;
							turn = 2;
							break;
						}
						if(processingrow == processingcolumn && processingrow != 1) {
							//Jump move cases 
							if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
								for (int i = 1; i < rowun ; i++) {
									if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							
							}
							if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
								for (int i = 1; i < columnun ; i++) {
									if(board[moverow-i][movecolumn+i] != 0 && board[moverow+i][movecolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
						}
						else {
							System.out.println("This is not a valid jump move");
						}
						if(valid == true) {
							//Asking if another jump move is to be made
							System.out.println("Would you like to make another jump move? Type y for yes and n for no");
							String input = in.nextLine();
							if(input.equals("y")) {
								multiplejump = true;
								startingrow = row;
								startingcolumn = column;
								break;
							}
							if(input.equals("n")) {
								movement = false;
								board[row][column] =1;
								turn =2; //Progressing to next players turn
								break;
							}
							
						}
						else {
							System.out.println("This is not a valid jump move");
						}
						break;
						
					}
					if(board[row][column] == 1 && turn != 1) { //Wrong turn scenario
						System.out.println("Its not your turn yet!");
						break;
					}
					if(board[row][column] == 2 && turn == 2 && movement == false) {
						System.out.println("You clicked on the red square");
						System.out.println("Please click on the square that you want to move this piece to");
						board[row][column]=7;
						movement = true;
						break;
					}
					if(board[row][column] == 7 && turn == 2 && movement == true) {

						int rowun = startingrow - moverow;
						
						int columnun = startingcolumn -movecolumn;
						int processingrow = rowun;
						int processingcolumn = columnun;
						if(processingcolumn < 0) {
							processingcolumn = processingcolumn * -1;
						}
						if(processingrow < 0) {
							processingrow = processingrow * -1;
						}
						if(processingrow ==1 && processingcolumn == 1 && multiplejump == false) {
							board[moverow][movecolumn] = 2;
							movement =false;
							turn = 3;
							break;
						}
						if(processingrow == processingcolumn && processingrow != 1) {
							if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
								for (int i = 1; i < rowun ; i++) {
									if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							
							}
							if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
								for (int i = 1; i < columnun ; i++) {
									if(board[moverow-i][movecolumn+i] != 0 && board[moverow-i][movecolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
						}
						else {
							System.out.println("This is not a valid jump move");
						}
						if(valid == true) {
							System.out.println("Would you like to make another jump move? Type y for yes and n for no");
							String input = in.nextLine();
							if(input.equals("y")) {
								multiplejump = true;
								startingrow = row;
								startingcolumn = column;
								break;
							}
							if(input.equals("n")) {
								movement = false;
								board[row][column] =2;
								turn =3;
								break;
							}
							
						}
						else {
							System.out.println("This is not a valid jump move");
						}
						break;
						
					}
					if(board[row][column] == 2 && turn != 2) {
						System.out.println("It's not your turn yet!");
						break; 
					}
					if(board[row][column] == 4 && turn == 3 && movement == false) {
						System.out.println("You clicked on the black square");
						System.out.println("Please click on the square that you want to move this piece to");
						board[row][column]=7;
						movement = true;
						break;
					}
					if(board[row][column] == 7 && turn == 3 && movement == true) {

						int rowun = startingrow - moverow;
						
						int columnun = startingcolumn -movecolumn;
						int processingrow = rowun;
						int processingcolumn = columnun;
						if(processingcolumn < 0) {
							processingcolumn = processingcolumn * -1;
						}
						if(processingrow < 0) {
							processingrow = processingrow * -1;
						}
						if(processingrow ==1 && processingcolumn == 1 && multiplejump == false ) {
							board[moverow][movecolumn] = 4;
							movement =false;
							turn = 4;
							break;
						}
						if(processingrow == processingcolumn && processingrow != 1) {
							if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
								for (int i = 1; i < rowun ; i++) {
									if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							
							}
							if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
								for (int i = 1; i < columnun ; i++) {
									if(board[moverow-i][movecolumn+i] != 0 && board[moverow-i][movecolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
						}
						
						if(valid == true) {
							System.out.println("Would you like to make another jump move? Type y for yes and n for no");
							String input = in.nextLine();
							if(input.equals("y")) {
								multiplejump = true;
								startingrow = row;
								startingcolumn = column;
								break;
							}
							if(input.equals("n")) {
								movement = false;
								board[row][column] =4;
								turn =4;
								break;
							}
						}
						else {
							System.out.println("This is not a valid jump move");
						}
						break;
						
					}
					if(board[row][column] == 4 && turn != 3) {
						System.out.println("Its not your turn yet!");
						break;
					}
					if(board[row][column] == 5 && turn == 4 && movement == false) {
						System.out.println("You clicked on the black square");
						System.out.println("Please click on the square that you want to move this piece to");
						board[row][column]=7;
						movement = true;
						break;
					}
					if(board[row][column] == 7 && turn == 4 && movement == true) {

						int rowun = startingrow - moverow;
						
						int columnun = startingcolumn -movecolumn;
						int processingrow = rowun;
						int processingcolumn = columnun;
						if(processingcolumn < 0) {
							processingcolumn = processingcolumn * -1;
						}
						if(processingrow < 0) {
							processingrow = processingrow * -1;
						}
						if(processingrow ==1 && processingcolumn == 1 && multiplejump == false) {
							board[moverow][movecolumn] = 5;
							movement =false;
							turn = 1;
							break;
						}
						if(processingrow == processingcolumn && processingrow != 1) {
							if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
								for (int i = 1; i < rowun ; i++) {
									if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							
							}
							if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
								for (int i = 1; i < columnun ; i++) {
									if(board[moverow-i][movecolumn+i] != 0 && board[moverow-i][movecolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
						}
						if(valid == true) {
							System.out.println("Would you like to make another jump move? Type y for yes and n for no");
							String input = in.nextLine();
							if(input.equals("y")) {
								multiplejump = true;
								startingrow = row;
								startingcolumn = column;
								break;
							}
							if(input.equals("n")) {
								movement = false;
								board[row][column] =5;
								turn =1;
								break;
							}
						}
						else {
							System.out.println("This is not a valid jump move");
						}
						break;
						
					}
					if(board[row][column] == 4 && turn != 4) {
						System.out.println("Its not your turn yet!");
						break;
					}
				}
				if(playercount == 3) { //Slight variation of turn cycle to account for 3 players
					if(board[row][column] == 1 && turn == 1 && movement == false) {
						System.out.println("You clicked on the blue square");
						System.out.println("Please click on the square that you want to move this piece to");
						board[row][column]=7;
						movement = true;
						break;
					}
					if(board[row][column] == 7 && turn == 1 && movement == true) {
						
						
						int rowun = startingrow - moverow;
						
						int columnun = startingcolumn -movecolumn;
						int processingrow = rowun;
						int processingcolumn = columnun;
						if(processingcolumn < 0) {
							processingcolumn = processingcolumn * -1;
						}
						if(processingrow < 0) {
							processingrow = processingrow * -1;
						}
						if(processingrow ==1 && processingcolumn == 1 && multiplejump == false) {
							board[moverow][movecolumn] = 1;
							turn = 2;
							movement = false;
							break;
						}
						if(processingrow == processingcolumn && processingrow != 1) {
							if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
								for (int i = 1; i < rowun ; i++) {
									if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							
							}
							if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
								for (int i = 1; i < columnun ; i++) {
									if(board[moverow-i][movecolumn+i] != 0 && board[moverow+i][movecolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
						}
						else {
							System.out.println("This is not a valid jump move");
						}
						if(valid == true) {
							System.out.println("Would you like to make another jump move? Type y for yes and n for no");
							String input = in.nextLine();
							if(input.equals("y")) {
								multiplejump = true;
								startingrow = row;
								startingcolumn = column;
								break;
							}
							if(input.equals("n")) {
								movement = false;
								board[row][column] =1;
								turn =2;
								break;
							}
							
						}
						else {
							System.out.println("This is not a valid jump move");
						}
						break;
						
					}
					if(board[row][column] == 1 && turn != 1) {
						System.out.println("Its not your turn yet!");
						break;
					}
					if(board[row][column] == 4 && turn == 2 && movement == false) {
						System.out.println("You clicked on the black square");
						System.out.println("Please click on the square that you want to move this piece to");
						board[row][column]=7;
						movement = true;
						break;
					}
					if(board[row][column] == 7 && turn == 2 && movement == true) {

						int rowun = startingrow - moverow;
						
						int columnun = startingcolumn -movecolumn;
						int processingrow = rowun;
						int processingcolumn = columnun;
						if(processingcolumn < 0) {
							processingcolumn = processingcolumn * -1;
						}
						if(processingrow < 0) {
							processingrow = processingrow * -1;
						}
						if(processingrow ==1 && processingcolumn == 1 && multiplejump == false) {
							board[moverow][movecolumn] = 4;
							turn = 3;
							movement = false;
							break;
						}
						if(processingrow == processingcolumn && processingrow != 1) {
							if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
								for (int i = 1; i < rowun ; i++) {
									if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							
							}
							if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
								for (int i = 1; i < columnun ; i++) {
									if(board[moverow-i][movecolumn+i] != 0 && board[moverow-i][movecolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
						}
						else {
							System.out.println("This is not a valid jump move");
						}
						if(valid == true) {
							System.out.println("Would you like to make another jump move? Type y for yes and n for no");
							String input = in.nextLine();
							if(input.equals("y")) {
								multiplejump = true;
								startingrow = row;
								startingcolumn = column;
								break;
							}
							if(input.equals("n")) {
								movement = false;
								board[row][column] =4;
								turn =3;
								break;
							}
							
						}
						else {
							System.out.println("This is not a valid jump move");
						}
						break;
						
					}
					if(board[row][column] == 4 && turn != 2) {
						System.out.println("It's not your turn yet!");
						break; 
					}
					if(board[row][column] == 6 && turn == 3 && movement == false) {
						System.out.println("You clicked on the gray square");
						System.out.println("Please click on the square that you want to move this piece to");
						board[row][column]=7;
						movement = true;
						break;
					}
					if(board[row][column] == 7 && turn == 3 && movement == true) {

						int rowun = startingrow - moverow;
						
						int columnun = startingcolumn -movecolumn;
						int processingrow = rowun;
						int processingcolumn = columnun;
						if(processingcolumn < 0) {
							processingcolumn = processingcolumn * -1;
						}
						if(processingrow < 0) {
							processingrow = processingrow * -1;
						}
						if(processingrow ==1 && processingcolumn == 1 && multiplejump == false ) {
							board[moverow][movecolumn] = 6;
							turn = 1;
							movement = false;
							break;
						}
						if(processingrow == processingcolumn && processingrow != 1) {
							if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
								for (int i = 1; i < rowun ; i++) {
									if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							
							}
							if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
								for (int i = 1; i < columnun ; i++) {
									if(board[moverow-i][movecolumn+i] != 0 && board[moverow-i][movecolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
							if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
								for (int i = 1; i < processingcolumn ; i++) {
									if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
										valid = true;
									}
									else {
										valid = false;
										break;
									}
								}
							}
						}
						
						if(valid == true) {
							System.out.println("Would you like to make another jump move? Type y for yes and n for no");
							String input = in.nextLine();
							if(input.equals("y")) {
								multiplejump = true;
								startingrow = row;
								startingcolumn = column;
								break;
							}
							if(input.equals("n")) {
								movement = false;
								board[row][column] =6;
								turn =1;
								break;
							}
						}
						else {
							System.out.println("This is not a valid jump move");
						}
						break;
						
					}
					if(board[row][column] == 6 && turn != 3) {
						System.out.println("Its not your turn yet!");
						break;
					}
				}
				if(playercount == 2) {
					
						
							if(board[row][column] == 1 && turn == 1 && movement == false) {
								System.out.println("You clicked on the blue square");
								System.out.println("Please click on the square that you want to move this piece to");
								board[row][column]=7;
								movement = true;
								break;
							}
							if(board[row][column] == 7 && turn == 1 && movement == true) {
								
								
								int rowun = startingrow - moverow;
								
								int columnun = startingcolumn -movecolumn;
								int processingrow = rowun;
								int processingcolumn = columnun;
								if(processingcolumn < 0) {
									processingcolumn = processingcolumn * -1;
								}
								if(processingrow < 0) {
									processingrow = processingrow * -1;
								}
								if(processingrow ==1 && processingcolumn == 1 && multiplejump == false) {
									board[moverow][movecolumn] = 1;
									movement= false;
									turn = 2;
									break;
								}
								if(processingrow == processingcolumn && processingrow != 1) {
									if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
										for (int i = 1; i < rowun ; i++) {
											if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
												valid = true;
											}
											else {
												valid = false;
												break;
											}
										}
									
									}
									if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
										for (int i = 1; i < columnun ; i++) {
											if(board[moverow-i][movecolumn+i] != 0 && board[moverow+i][movecolumn+i] != 7) {
												valid = true;
											}
											else {
												valid = false;
												break;
											}
										}
									}
									if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
										for (int i = 1; i < processingcolumn ; i++) {
											if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
												valid = true;
											}
											else {
												valid = false;
												break;
											}
										}
									}
									if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
										for (int i = 1; i < processingcolumn ; i++) {
											if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
												valid = true;
											}
											else {
												valid = false;
												break;
											}
										}
									}
								}
								else {
									System.out.println("This is not a valid jump move");
								}
								if(valid == true) {
									System.out.println("Would you like to make another jump move? Type y for yes and n for no");
									String input = in.nextLine();
									if(input.equals("y")) {
										multiplejump = true;
										startingrow = row;
										startingcolumn = column;
										break;
									}
									if(input.equals("n")) {
										movement = false;
										board[row][column] =1;
										turn =2;
										break;
									}
									
								}
								else {
									System.out.println("This is not a valid jump move");
								}
								break;
								
							}
							if(board[row][column] == 1 && turn != 1) {
								System.out.println("Its not your turn yet!");
								break;
							}
							if(board[row][column] == 2 && turn == 2 && movement == false) {
								System.out.println("You clicked on the red square");
								System.out.println("Please click on the square that you want to move this piece to");
								board[row][column]=7;
								movement = true;
								break;
							}
							if(board[row][column] == 7 && turn == 2 && movement == true) {

								int rowun = startingrow - moverow;
								
								int columnun = startingcolumn -movecolumn;
								int processingrow = rowun;
								int processingcolumn = columnun;
								if(processingcolumn < 0) {
									processingcolumn = processingcolumn * -1;
								}
								if(processingrow < 0) {
									processingrow = processingrow * -1;
								}
								if(processingrow ==1 && processingcolumn == 1 && multiplejump == false) {
									board[moverow][movecolumn] = 2;
									movement= false;
									turn = 1;
									break;
								}
								if(processingrow == processingcolumn && processingrow != 1) {
									if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
										for (int i = 1; i < rowun ; i++) {
											if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
												valid = true;
											}
											else {
												valid = false;
												break;
											}
										}
									
									}
									if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
										for (int i = 1; i < columnun ; i++) {
											if(board[moverow-i][movecolumn+i] != 0 && board[moverow-i][movecolumn+i] != 7) {
												valid = true;
											}
											else {
												valid = false;
												break;
											}
										}
									}
									if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
										for (int i = 1; i < processingcolumn ; i++) {
											if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
												valid = true;
											}
											else {
												valid = false;
												break;
											}
										}
									}
									if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
										for (int i = 1; i < processingcolumn ; i++) {
											if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
												valid = true;
											}
											else {
												valid = false;
												break;
											}
										}
									}
								}
								else {
									System.out.println("This is not a valid jump move");
								}
								if(valid == true) {
									System.out.println("Would you like to make another jump move? Type y for yes and n for no");
									String input = in.nextLine();
									if(input.equals("y")) {
										multiplejump = true;
										startingrow = row;
										startingcolumn = column;
										break;
									}
									if(input.equals("n")) {
										movement = false;
										board[row][column] =2;
										turn =1;
										break;
									}
									
								}
								else {
									System.out.println("This is not a valid jump move");
								}
								break;
								
							}
							if(board[row][column] == 2 && turn != 2) {
								System.out.println("It's not your turn yet!");
								break; 
							}
					}
					
				
				if(playercount == 6) {
				
					//Break is used here as opposed to setting running to false because there was a bug where clicking on a blue square would print affirmative message and error message at the same time
						if(board[row][column] == 1 && turn == 1 && movement == false) {
							System.out.println("You clicked on the blue square");
							System.out.println("Please click on the square that you want to move this piece to");
							board[row][column]=7;
							movement = true;
							break;
						}
						if(board[row][column] == 7 && turn == 1 && movement == true) {
							
							
							int rowun = startingrow - moverow;
							
							int columnun = startingcolumn -movecolumn;
							int processingrow = rowun;
							int processingcolumn = columnun;
							if(processingcolumn < 0) {
								processingcolumn = processingcolumn * -1;
							}
							if(processingrow < 0) {
								processingrow = processingrow * -1;
							}
							if(processingrow ==1 && processingcolumn == 1 && multiplejump == false) {
								board[moverow][movecolumn] = 1;
								movement =false;
								turn = 2;
								break;
							}
							if(processingrow == processingcolumn && processingrow != 1) {
								if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
									for (int i = 1; i < rowun ; i++) {
										if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								
								}
								if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
									for (int i = 1; i < columnun ; i++) {
										if(board[moverow-i][movecolumn+i] != 0 && board[moverow+i][movecolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
								if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
									for (int i = 1; i < processingcolumn ; i++) {
										if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
								if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
									for (int i = 1; i < processingcolumn ; i++) {
										if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
							}
							else {
								System.out.println("This is not a valid jump move");
							}
							if(valid == true) {
								System.out.println("Would you like to make another jump move? Type y for yes and n for no");
								String input = in.nextLine();
								if(input.equals("y")) {
									multiplejump = true;
									startingrow = row;
									startingcolumn = column;
									break;
								}
								if(input.equals("n")) {
									movement = false;
									board[row][column] =1;
									turn =2;
									break;
								}
								
							}
							else {
								System.out.println("This is not a valid jump move");
							}
							break;
							
						}
						if(board[row][column] == 1 && turn != 1) {
							System.out.println("Its not your turn yet!");
							break;
						}
						if(board[row][column] == 2 && turn == 2 && movement == false) {
							System.out.println("You clicked on the red square");
							System.out.println("Please click on the square that you want to move this piece to");
							board[row][column]=7;
							movement = true;
							break;
						}
						if(board[row][column] == 7 && turn == 2 && movement == true) {

							int rowun = startingrow - moverow;
							
							int columnun = startingcolumn -movecolumn;
							int processingrow = rowun;
							int processingcolumn = columnun;
							if(processingcolumn < 0) {
								processingcolumn = processingcolumn * -1;
							}
							if(processingrow < 0) {
								processingrow = processingrow * -1;
							}
							if(processingrow ==1 && processingcolumn == 1 && multiplejump == false) {
								board[moverow][movecolumn] = 2;
								movement =false;
								turn = 3;
								break;
							}
							if(processingrow == processingcolumn && processingrow != 1) {
								if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
									for (int i = 1; i < rowun ; i++) {
										if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								
								}
								if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
									for (int i = 1; i < columnun ; i++) {
										if(board[moverow-i][movecolumn+i] != 0 && board[moverow-i][movecolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
								if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
									for (int i = 1; i < processingcolumn ; i++) {
										if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
								if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
									for (int i = 1; i < processingcolumn ; i++) {
										if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
							}
							else {
								System.out.println("This is not a valid jump move");
							}
							if(valid == true) {
								System.out.println("Would you like to make another jump move? Type y for yes and n for no");
								String input = in.nextLine();
								if(input.equals("y")) {
									multiplejump = true;
									startingrow = row;
									startingcolumn = column;
									break;
								}
								if(input.equals("n")) {
									movement = false;
									board[row][column] =2;
									turn =3;
									break;
								}
								
							}
							else {
								System.out.println("This is not a valid jump move");
							}
							break;
							
						}
						if(board[row][column] == 2 && turn != 2) {
							System.out.println("It's not your turn yet!");
							break; 
						}
						if(board[row][column] == 3 && turn == 3 && movement == false) {
							System.out.println("You clicked on the green square");
							System.out.println("Please click on the square that you want to move this piece to");
							board[row][column]=7;
							movement = true;
							break;
						}
						if(board[row][column] == 7 && turn == 3 && movement == true) {

							int rowun = startingrow - moverow;
							
							int columnun = startingcolumn -movecolumn;
							int processingrow = rowun;
							int processingcolumn = columnun;
							if(processingcolumn < 0) {
								processingcolumn = processingcolumn * -1;
							}
							if(processingrow < 0) {
								processingrow = processingrow * -1;
							}
							if(processingrow ==1 && processingcolumn == 1 && multiplejump == false ) {
								board[moverow][movecolumn] = 3;
								movement =false;
								turn = 4;
								break;
							}
							if(processingrow == processingcolumn && processingrow != 1) {
								if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
									for (int i = 1; i < rowun ; i++) {
										if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								
								}
								if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
									for (int i = 1; i < columnun ; i++) {
										if(board[moverow-i][movecolumn+i] != 0 && board[moverow-i][movecolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
								if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
									for (int i = 1; i < processingcolumn ; i++) {
										if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
								if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
									for (int i = 1; i < processingcolumn ; i++) {
										if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
							}
							
							if(valid == true) {
								System.out.println("Would you like to make another jump move? Type y for yes and n for no");
								String input = in.nextLine();
								if(input.equals("y")) {
									multiplejump = true;
									startingrow = row;
									startingcolumn = column;
									break;
								}
								if(input.equals("n")) {
									movement = false;
									board[row][column] =3;
									turn =4;
									break;
								}
							}
							else {
								System.out.println("This is not a valid jump move");
							}
							break;
							
						}
						if(board[row][column] == 3 && turn != 3) {
							System.out.println("Its not your turn yet!");
							break;
						}
						if(board[row][column] == 4 && turn == 4 && movement == false) {
							System.out.println("You clicked on the black square");
							System.out.println("Please click on the square that you want to move this piece to");
							board[row][column]=7;
							movement = true;
							break;
						}
						if(board[row][column] == 7 && turn == 4 && movement == true) {

							int rowun = startingrow - moverow;
							
							int columnun = startingcolumn -movecolumn;
							int processingrow = rowun;
							int processingcolumn = columnun;
							if(processingcolumn < 0) {
								processingcolumn = processingcolumn * -1;
							}
							if(processingrow < 0) {
								processingrow = processingrow * -1;
							}
							if(processingrow ==1 && processingcolumn == 1 && multiplejump == false) {
								board[moverow][movecolumn] = 4;
								movement =false;
								turn = 5;
								break;
							}
							if(processingrow == processingcolumn && processingrow != 1) {
								if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
									for (int i = 1; i < rowun ; i++) {
										if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								
								}
								if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
									for (int i = 1; i < columnun ; i++) {
										if(board[moverow-i][movecolumn+i] != 0 && board[moverow-i][movecolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
								if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
									for (int i = 1; i < processingcolumn ; i++) {
										if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
								if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
									for (int i = 1; i < processingcolumn ; i++) {
										if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
							}
							if(valid == true) {
								System.out.println("Would you like to make another jump move? Type y for yes and n for no");
								String input = in.nextLine();
								if(input.equals("y")) {
									multiplejump = true;
									startingrow = row;
									startingcolumn = column;
									break;
								}
								if(input.equals("n")) {
									movement = false;
									board[row][column] =4;
									turn =5;
									break;
								}
							}
							else {
								System.out.println("This is not a valid jump move");
							}
							break;
							
						}
						if(board[row][column] == 4 && turn != 4) {
							System.out.println("Its not your turn yet!");
							break;
						}
						if(board[row][column] == 5 && turn == 5 && movement == false) {
							System.out.println("You clicked on the yellow square");
							System.out.println("Please click on the square that you want to move this piece to");
							board[row][column]=7;					
							movement = true;
							break;
						}
						if(board[row][column] == 7 && turn == 5 && movement == true) {

							int rowun = startingrow - moverow;
							
							int columnun = startingcolumn -movecolumn;
							int processingrow = rowun;
							int processingcolumn = columnun;
							if(processingcolumn < 0) {
								processingcolumn = processingcolumn * -1;
							}
							if(processingrow < 0) {
								processingrow = processingrow * -1;
							}
							if(processingrow ==1 && processingcolumn == 1 && multiplejump == false) {
								board[moverow][movecolumn] = 5;
								movement =false;
								turn = 6;
								break;
							}
							if(processingrow == processingcolumn && processingrow != 1) {
								if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
									for (int i = 1; i < rowun ; i++) {
										if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								
								}
								if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
									for (int i = 1; i < columnun ; i++) {
										if(board[moverow-i][movecolumn+i] != 0 && board[moverow+i][movecolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
								if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
									for (int i = 1; i < processingcolumn ; i++) {
										if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
								if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
									for (int i = 1; i < processingcolumn ; i++) {
										if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
							}
							
							if(valid == true) {
								System.out.println("Would you like to make another jump move? Type y for yes and n for no");
								String input = in.nextLine();
								if(input.equals("y")) {
									multiplejump = true;
									startingrow = row;
									startingcolumn = column;
									break;
								}
								if(input.equals("n")) {
									movement = false;
									board[row][column] =5;
									turn =6;
									break;
								}
							}
							else {
								System.out.println("This is not a valid jump move");
							}
							break;
							
						}
						if(board[row][column] == 5 && turn != 5) {
							System.out.println("Its not your turn yet!");
							break;
						}
						if(board[row][column] == 6 && turn == 6 && movement == false) {
							System.out.println("You clicked on the grey square");
							movement = true;
							System.out.println("Please click on the square that you want to move this piece to");
							board[row][column]=7;
							break;
						}
						if(board[row][column] == 7 && turn == 6 && movement == true) {

							int rowun = startingrow - moverow;
							
							int columnun = startingcolumn -movecolumn;
							int processingrow = rowun;
							int processingcolumn = columnun;
							if(processingcolumn < 0) {
								processingcolumn = processingcolumn * -1;
							}
							if(processingrow < 0) {
								processingrow = processingrow * -1;
							}
							if(processingrow ==1 && processingcolumn == 1 && multiplejump ==false) {
								board[moverow][movecolumn] = 6;
								movement =false;
								turn = 1;
								break;
							}
							if(processingrow == processingcolumn && processingrow != 1) {
								if(startingrow > moverow && startingcolumn < movecolumn) { //Up Right
									for (int i = 1; i < rowun ; i++) {
										if(board[startingrow-i][startingcolumn+i] != 0 && board[startingrow-i][startingcolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								
								}
								if(startingrow < moverow && startingcolumn > movecolumn) { // Down Left
									for (int i = 1; i < columnun ; i++) {
										if(board[moverow-i][movecolumn+i] != 0 && board[moverow+i][movecolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
								if(startingrow > moverow && startingcolumn > movecolumn) { // Up Left
									for (int i = 1; i < processingcolumn ; i++) {
										if(board[startingrow-i][startingcolumn-i] != 0 && board[startingrow-i][startingcolumn-i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
								if(startingrow < moverow && startingcolumn < movecolumn) { // Down Right
									for (int i = 1; i < processingcolumn ; i++) {
										if(board[startingrow+i][startingcolumn+i] != 0 && board[startingrow+i][startingcolumn+i] != 7) {
											valid = true;
										}
										else {
											valid = false;
											break;
										}
									}
								}
							}
							
							if(valid == true) {
								System.out.println("Would you like to make another jump move? Type y for yes and n for no");
								String input = in.nextLine();
								if(input.equals("y")) {
									multiplejump = true;
									startingrow = row;
									startingcolumn = column;
									break;
								}
								if(input.equals("n")) {
									movement = false;
									board[row][column] =6;
									turn =1;
									break;
								}
							}
							else {
								System.out.println("This is not a valid jump move");
							}
							break;
							
						}
						if(board[row][column] == 6 && turn != 6) {
							System.out.println("Its not your turn yet!");
							break;
						}
						else if(board[row][column] == 7) {
							System.out.println("This is not a valid move");
						}
						else {
							System.out.println("Select a valid piece");
							
							break;
						}
					
					
				}
				}
			
			}
		}
			
	}


	
	

	
	
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
