import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Main 
{

  private static int [][] grid;

  
  public static void main(String[] args) throws Exception
  {
    
    // Ask the user to enter a file name.
    
    Scanner in = new Scanner(System.in);
		System.out.print("Enter file : ");
		String filename = in.nextLine();
		in.close();

    // Checking if the file exists.
    
    File file = new File(filename);
    Scanner sc = null;
    try 
      {
        grid = new int [9][9];
        sc = new Scanner(file);

        for(int i = 0; i < 9; i++)
        {
          for(int j = 0; j < 9; j++)
          {
            grid[i][j] = sc.nextInt();
            //System.out.print(grid[i][j] + " "); 
          }
          //System.out.println();
        }
        sc.close();
        
      }
  
      catch (FileNotFoundException e)
      {
        System.out.println("File not found.\n");
			  return;
      }


    // Print the original grid.
    print(grid);

    System.out.println("Solving the puzzle...\n");

    System.out.println("Here is the solution:\n");

    // Solve the sudoku puzzle.
    solve (grid);

    // Print the solved puzzle
    print(grid);
    

    
    
  }

  // Print the grid
  public static void print(int[][]grid)
    {
      for(int i = 0; i < 9; i++)
      {
        System.out.println();
        for(int j = 0; j < 9; j++)
        {
          System.out.print(grid[i][j] + " ");
        }
      }
      System.out.println("\n");
    }


  // Check if the number is already in the row. Return true if it is already there and false otherwise.
  public static boolean checkRow(int[][] grid, int row, int num)
  {
    for(int j = 0; j < 9; j++)
    {
      if (grid[row][j] == num)
      {
        return true;
      }
    }
    return false;
  }

  // Check if the number is already in the column. Return true if it is already there and false otherwise. 
  public static boolean checkCol(int[][] grid, int col, int num)
  {
    for(int i = 0; i < 9; i++)
    {
      if (grid[i][col] == num)
      {
        return true;
      }
    }
    return false;
  }

  // Check if the number is already in the 3x3 box. Return true if it is already there and false otherwise.
  public static boolean checkBox(int[][] grid, int row, int col, int num)
  {
    int r = row - row % 3;
		int c = col - col % 3;
    
    for(int i = r; i < r + 3; i++)
    {
      for(int j = c; j < c + 3; j++)
      {
        if (grid[i][j] == num)
        {
          return true;
        }
      }
    }
    return false;
  }


  // Check if it is a valid position for the number to add.
  public static boolean isValid(int [][] grid, int row, int col, int num)
  {
    if (checkRow(grid, row, num) == false && checkCol(grid, col, num) == false && checkBox(grid, row, col, num) == false)
    {
      return true;
    }
    return false;
  }

  // Solve the sudoku grid.
  public static boolean solve(int [][] grid)
  {
    for(int i = 0; i < 9; i++)
    {
      for(int j = 0; j < 9; j++)
      {
        if (grid[i][j] == 0)
        {
          for (int num = 1; num <= 9; num ++)
            {
              if (isValid(grid, i, j, num))
              {
                grid[i][j] = num;
                if (solve(grid))
                {
                  return true;
                }
                else
                {
                  grid[i][j] = 0;
                }
              }
            }
          return false;
        }
      }
    }
    return true;
  }

  

}