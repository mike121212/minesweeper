package first;

import java.util.Scanner;


public class Minesweeper {
	

	public static void main(String[] args) {
		
		int scanMine = 4;
		int scanX = 4;
		int scanY = 4;
		int mineCount = 0;
		
		Scanner scan= new Scanner(System.in);
	
		
		
		do{
		System.out.println("Please enter number of rows: ");
		scanX = scan.nextInt();
		}while (scanX < 3 || scanX > 100);
		
		do{
		System.out.println("Now enter number of columns: ");
		scanY = scan.nextInt();
		}while (scanY < 3 || scanY > 100);
	
	
		do{
		System.out.println("Finally enter number of mines: ");
		scanMine = scan.nextInt();
		}while (scanMine < 1 || scanMine >= (scanX*scanY)-9);
		
		
		//find the number of safe places
		int safeCells = (scanX*scanY) - scanMine;
	
		MineField mineField = new MineField(scanX, scanY, scanMine);
		
		
		int scanCheckX = 0;
		int scanCheckY = 0;
		
		mineField.drawGrid();
	
		//this runs before mines have been generated
		do{
			System.out.println("Please enter the number of the row to sweep: ");
			scanCheckX = scan.nextInt();
		}while (scanCheckX < 0 || scanCheckX >= mineField.getFieldSizeX());
		
		do{
		System.out.println("Finally enter number of column to sweep: ");
		scanCheckY = scan.nextInt();
		}while (scanCheckY < 0 || scanCheckY >= mineField.getFieldSizeY());
		
		mineCount = mineField.check(scanCheckX, scanCheckY);
		mineField.addMines(scanCheckX, scanCheckY);
		mineField.drawField();
		mineField.drawGrid();
		
		
		
		do
			{
			 scanCheckX = 0;
			 scanCheckY = 0;
			
				do{
					System.out.println("Please enter the number of the row to sweep: ");
					scanCheckX = scan.nextInt();
				}while (scanCheckX < 0 || scanCheckX >= mineField.getFieldSizeX());
				
				do{
				System.out.println("Finally enter number of column to sweep: ");
				scanCheckY = scan.nextInt();
				}while (scanCheckY < 0 || scanCheckY >= mineField.getFieldSizeY());
				
				
				mineCount = mineField.check(scanCheckX, scanCheckY);
				mineField.drawGrid();
				
				//Triggers if no input problems
				if(mineCount!=10 && mineCount != -1)
				{
					
					safeCells--;
					System.out.println(safeCells + " Safe cells remaining");
					System.out.println(scanMine + " Mines remaining");
					
				}
				
				//Triggers if the cell has already been selected
				else if (mineCount!= -1 && safeCells != 0)
				{
					
					System.out.println("Already sweeped, please try again");
					System.out.println();
					
				}
				
				
				
			}while(mineCount != -1 && safeCells != 0);

			if (safeCells == 0 && mineCount != -1)
			{
				System.out.println();
				System.out.println("All mines located!");
				System.out.println("Congratulations!");
			}
		
		
	scan.close();
	
	}

	
}