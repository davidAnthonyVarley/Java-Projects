
public class CreatingAGridWith2Darrays {
	public static final char EMPTY_SPACE=' ';
	public static final char BORDER_SPACE='*';
	
	public static void main(String[] args) {
		
	char[][] grid = new char[20][10];
	for (int row = 0; row<grid.length; row++) {
		for (int column = 0; column<grid[row].length; column++) {
			
			if (row==0 || row==(grid.length-1))
				grid[row][column] = BORDER_SPACE;
			else if (column==0 || column==(grid[row].length-1))
				grid[row][column] = BORDER_SPACE;
			else
				grid[row][column] = EMPTY_SPACE;
		
		}
	}
	printGrid(grid);
	
	
	}
	
	public static void printGrid(char[][] grid)
	{
		for (int column = 0; column<grid[0].length; column++) {
			for (int row = 0; row<grid.length; row++) {
				
				if (row==(grid.length-1))
					System.out.println(grid[row][column]);
				else
					System.out.print(grid[row][column]);
			}
		}
	}
	}