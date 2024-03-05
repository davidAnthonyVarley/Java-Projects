
import java.util.Scanner;

public class functionality {

	static char[][] board = 
		{ 
		  {'A', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '},      
		  {' ', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-'},
		  {'B', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '},
		  {' ', '-', '-', '-', '|', '-', '-', '-', '|', '-', '-', '-'},
		  {'C', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '},
		  {' ', ' ', '1', ' ', ' ', ' ', '2', ' ', ' ', ' ', '3', ' '},
        };
	
	
	void printBoard(char[][] board) {
		//i = col, j=row
		for (int i=0; i<board.length; i++) {
			
			for (int j=0; j<board[0].length; j++) {
				
				if (j==(board[0].length-1))
					System.out.println(board[i][j]);
				else
					System.out.print(board[i][j]);
					
			}
		}
	}
	
	
	
	

	
	void clearBoard(char[][] board) {
	
	for (int i=0; i<board.length; i++) {
		
		for (int j=0; j<board[0].length; j+=1) {
			board[i][j] = ' ';
			
		}
	}
	
}
	
	
	boolean canMakeMove(char[][] board, int x, int y) {
		if (board[x][y]==' ')
			return true;
		
		return false;
	}
	
	void makeMove(char[][] board, char input, int x , int y) {
		if (canMakeMove(board, x, y)) {
			board[x][y]=input;
		}
	}
	
	
	
	
	boolean isBoardFull(char[][] board) {
		
		for (int i=0; i<board.length; i+=2) {
			
			for (int j=2; j<board[0].length; j+=4) {
				if (board[i][j]==' ')
					return false;
				
			}
		}
		
		return true;
	}
	
	char winner(char[][] board) {
		char winner = ' ';
		
		boolean xWins = checkForWinner(board, 'X');
		boolean oWins = checkForWinner(board, 'O');
		
		if (xWins)
			return 'X';
		else if(oWins)
			return 'O';
		else
			return winner;
	}
	
	boolean checkForWinner(char[][] board, char letter) {

	if (checkRows(board, letter) || checkColumns(board, letter)  || checkDiagonals(board, letter) )
		return true;

		return false;
	}
	
	boolean checkRows(char[][] board, char letter) {
		for (int k=0; k<board.length; k+=2) {
			int count = 0;
			
			for (int i=2; i<board[0].length; i+=4) {
				char t = board[k][i];
				if (board[k][i]==letter) {
					count++;
					if (count==3)
						return true;
					
				} else {
					i=20;
				}
			}
		}
		
		return false;
	}
	
	
	boolean checkColumns(char[][] board, char letter) {
		
		for (int k=2; k<board[0].length; k+=4) {
			int count = 0;
			
			for (int i=0; i<board[0].length; i+=2) {
				
				char t = board[i][k];
				if (board[i][k]==letter) {
					count++;
					if (count==3)
						return true;
					
				} else {
					i=20;
				}
			}
		}
		
		
		return false;
		
	}
	
	boolean checkDiagonals(char[][] board, char letter) {
		int i = 0;
		int count =0;
		
		for (int k=2; k<board[0].length && i<board.length; k+=4, i+=2) {
			//char t = board[i][k];
			if (board[i][k]==letter) {
				count++;
				if (count==3)
					return true;
			}
			else {
				i=20;
				count=0;
			}
		}
		
		i = 0;
		
		for (int k=10; k>1 && i>=0; k-=4, i+=2) {
			//char t = board[i][k];
			if (board[i][k]==letter) {
				count++;
				if (count==3)
					return true;
			}
			else {
				i=-20;
			}
		}
		
		return false;
		
	}
	
	
	
	
	
	
	
	public static char changeBoard(int buttonChanged, char l) {
		functionality nc = new functionality();
		nc.board = board;	
			
		char letter = l;
			
			int x =0;
			int y=0;
			
			switch (buttonChanged) {
				case 0:
					x=0;
					y=2;
					break;
				case 1:
					x=0;
					y=6;
					break;
				case 2:
					x=0;
					y=10;
					break;
				case 3:
					x=2;
					y=2;
					break;
				case 4:
					x=2;
					y=6;
					break;
				case 5:
					x=2;
					y=10;
					break;
				case 6:
					x=4;
					y=2;
					break;
				case 7:
					x=4;
					y=6;
					break;
				case 8:
					x=4;
					y=10;
					break;
			}
			
		
			nc.makeMove(nc.board, letter, x, y);
		
			nc.printBoard(nc.board);
			
			
			char result = nc.winner(board);
			
			if (result=='X' || result=='O') {
				return result;
			}
			else
				return ' ';
		
		
		
	}

}
















