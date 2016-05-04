package first;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.math.*;

public class MineField {

	
	private boolean[][] mineField;
	private String[][] playerGrid;
	private int fieldSizeX;
	private int fieldSizeY;
	private int mineNum;
	
	
	
	public int getFieldSizeX() {
		return fieldSizeX;
	}



	public int getFieldSizeY() {
		return fieldSizeY;
	}

	
	public MineField(int fieldSizeX, int fieldSizeY, int mineNum)
	{
		
		this.mineField = new boolean [fieldSizeX][fieldSizeY];
		this.playerGrid = new String [fieldSizeX][fieldSizeY];
		this.fieldSizeX = fieldSizeX;
		this.fieldSizeY = fieldSizeY;
		this.mineNum = mineNum;
		
		for (int i=0; i<fieldSizeX; i++)
		{
			
			for (int j=0; j<fieldSizeY; j++)
			{
				
				mineField [i][j] = false;
				playerGrid[i][j] = "-";

			}
			
		}
		
		
		
	}
	

	
	public void  addMines(int scanCheckX, int scanCheckY) {
	
	for (int i=0; i<mineNum; i++)
	{
		
		Random rnx = new Random();
		Random rny = new Random();
		int rndx;
		int rndy;
		
		do{
			rndx = rnx.nextInt(fieldSizeX);
			rndy = rny.nextInt(fieldSizeY);

		}while (Math.abs(rndx - scanCheckX) <= 1 && Math.abs(rndy - scanCheckY) <= 1 || mineField[rndx][rndy] == true);
		// so that the first selected tile and the surrounding 9 are free from mines.
		// also ensures mines are not repeatedly placed in the same location.
	
		
		mineField[rndx][rndy] = true;
	}
	
	updateGrid(scanCheckX, scanCheckY,0);
	
	}
	
	

	public void  drawField() {
		
		for (int i=0; i<fieldSizeX; i++)
		{
			
			for (int j=0; j<fieldSizeY; j++)
			{
				if(mineField[i][j]==true)
				{
				
				System.out.print(mineField[i][j] + "   ");
				
				}
				else 
				{
					System.out.print(mineField[i][j] + "  ");
				}
			}
			
			System.out.println();
			
		}
		
		System.out.println();
		System.out.println(mineNum + " Mines remaining");
		System.out.println();
		
		
	}
	
	
	public void drawGridString()
	{
	
		
		for (int i=0; i<fieldSizeX; i++)
		{
			
			for (int j=0; j<fieldSizeY; j++)
			{
				{
					
					System.out.print("[" +playerGrid[i][j] + "] ");
					
				}
			}
			
			System.out.println();
			
		}
		
		System.out.println();
		System.out.println(mineNum + " Mines remaining");
		System.out.println();
		
	}
	
	
	public void drawGrid() {
	
		System.out.print("    ");
		//Numbers along the top
		for (int i=0; i<fieldSizeY; i++)
		{
			if(i<10)
			{
				System.out.print(" " +i + "  ");
			}
			else if(i>=10)
			{
				
				System.out.print(" " +i + " ");
				
			}
		}
		System.out.println();
		
		for (int i=0; i<fieldSizeX; i++)
		{
			//Numbers along the left
			if(i <10)
			{
				System.out.print(i + "   ");
			}
			
			else if(10<= i && i< 100)
			{
				
				System.out.print(i + "  ");
				
			}
			
			else
			{
				
				System.out.print(i + " ");
				
			}
			for (int j=0; j<fieldSizeY; j++)
			{
				
				System.out.print("[" +playerGrid[i][j] + "] ");
				
			}
			//Numbers along the right
			System.out.println(i);
			
		}
		
		System.out.print("    ");
		//Numbers along the bottom
		for (int i=0; i<fieldSizeY; i++)
		{
				if(i<10)
				{
					System.out.print(" " +i + "  ");
				}
				else if(i>=10)
				{
					
					System.out.print(" " +i + " ");
					
				}
		}
		System.out.println();
		
		System.out.println();
		//System.out.println(mineNum + " Mines remaining");
		System.out.println();
		
		
	}
	

public void updateGrid(int coordX, int coordY, int mineCount)
{
	
	if (mineCount>0)
	{
		String mineInt = Integer.toString(mineCount);
		playerGrid[coordX][coordY] = mineInt;
	}
	
	else if(mineCount == -1)
	{
		
		playerGrid[coordX][coordY] = "*";
		
	}
	
	//if there are no mines around the tile, reveal all tiles
	else if (mineCount == 0)
	{
		
		for( int i= -1; i<=1; i++)
		{
			for( int j= -1; j<=1; j++)
			{
				
				try
				{
//this should be an if probably					playerGrid[coordX+i][coordY+j] == " ");
					
				}
			
				//in case of side or corner cells 
				catch(ArrayIndexOutOfBoundsException Exception)
				{

				}
			
			}
		
		}
		
		//playerGrid[coordX][coordY] = " ";
	}
	
}


//old method for checking for mines, uses ifs
public int checkField(int coordX, int coordY)
	{
		// mine count is used to indicate the number of mines in surrounding tiles.
		// 0 indicates no mines, so surrounding tiles should be revealed, -1 indicates a mine, 10 indicates an already revealed tile.
	
		int mineCount =0;
		
		if(! "-".equals(playerGrid[coordX][coordY]))
		{
			
			return 10;
			
		}
		
		//If a cell with a mine is selected
		if (mineField[coordX][coordY] == true)
		{
			mineCount = -1;
			System.out.println();
			updateGrid(coordX, coordY, mineCount);
			System.out.print("You lose! ");
			System.out.println("Try again?");
			return mineCount;
			
		}
		
		if (mineField[coordX][coordY] == false)
		{
			//this check all cells around the selected cell, except any cells outside the playing area
			for( int i=-1; i<=1; i++)
			{
				
				try
				{
					if (mineField[coordX-1][coordY+i] == true)
					{
						mineCount++;

					}
				
				}
				
				//in case of side or corner cells 
				catch(ArrayIndexOutOfBoundsException Exception)
				{

				}
				
				//to avoid the selected cell
				if ( i != 0)
				{
					try
					{
						if (mineField[coordX][coordY+i] == true)
						{
							mineCount++;

						}
					}
					catch(ArrayIndexOutOfBoundsException Exception)
					{

					}
				}
				try
				{
					if (mineField[coordX+1][coordY+i] == true)
					{
						mineCount++;

					}
				}
				catch(ArrayIndexOutOfBoundsException Exception)
				{
					
				}
				
			}
				

			System.out.println();
			updateGrid(coordX, coordY, mineCount);
			
			 
		}
		
		return mineCount;
		
			
			
	}

public int check(int coordX, int coordY)
{
	
	int mineCount = 0;

	
	//this will trigger if the selected tile has already been selected
	if(! "-".equals(playerGrid[coordX][coordY]))
	{
		
		return 10;
		
	}
	
	//If a cell with a mine is selected
	if (mineField[coordX][coordY] == true)
	{
		mineCount = -1;
		System.out.println();
		updateGrid(coordX, coordY, mineCount);
		System.out.print("You lose! ");
		System.out.println("Try again?");
		return mineCount;
		
	}
	
	
		

	
	//this check all cells around the selected cell, except any cells outside the playing area
	for( int i= -1; i<=1; i++)
	{
		for( int j= -1; j<=1; j++)
		{
			
			try
			{
				if (mineField[coordX+i][coordY+j] == true)
				{
					mineCount++;
					
				}

			}
		
			//in case of side or corner cells 
			catch(ArrayIndexOutOfBoundsException Exception)
			{

			}
		
		}
	
	}
	

	//updateGrid(coordX, coordY, mineCount);
	updateGrid(coordX, coordY, mineCount);
	drawGrid();

	
	return mineCount;
		
	
	}


//non working method for checking mines that also shows connecting blanks
public int checkBlanks(int coordX, int coordY)
{
	
	return 1;
//	
//	int mineCount = 0;
//	int j = 0;
//	int k = 1;
//	
//	List<Integer> checklist = new ArrayList<Integer>();
//	
//	checklist.add(coordX);
//	checklist.add(coordY);
//	
//	
//	if(! "-".equals(playerGrid[coordX][coordY]))
//	{
//		
//		return 10;
//		
//	}
//	
//	//If a cell with a mine is selected
//	if (mineField[coordX][coordY] == true)
//	{
//		mineCount = -1;
//		System.out.println();
//		updateGrid(coordX, coordY, mineCount);
//		System.out.print("You lose! ");
//		System.out.println("Try again?");
//		return mineCount;
//		
//	}
//	
//	
//	do
//	{
//		
//		mineCount = 0;
//	
//		List<Integer> tempCheck = new ArrayList<Integer>();
//		
//		//this check all cells around the selected cell, except any cells outside the playing area
//		for( int i= -1; i<=1; i++)
//		{
//			System.out.println("here");
//			try
//			{
//				if (mineField[checklist.get(j)-1][checklist.get(k)+i] == true)
//				{
//					mineCount++;
//					System.out.println("here1");
//				}
//				
//				else
//				{
//					tempCheck.add(checklist.get(j)-1);
//				
//					tempCheck.add(checklist.get(k)+i);
//					System.out.println("here2");
//				}
//				
//			
//			}
//			
//			//in case of side or corner cells 
//			catch(ArrayIndexOutOfBoundsException Exception)
//			{
//
//			}
//			
//			//to avoid the selected cell
//			if ( i != 0)
//			{
//				try
//				{
//					if (mineField[checklist.get(j)][checklist.get(k)+i] == true)
//					{
//						mineCount++;
//					}
//					else
//					{
//						tempCheck.add(checklist.get(j));
//						tempCheck.add(checklist.get(k)+i);
//
//					}
//				}
//				catch(ArrayIndexOutOfBoundsException Exception)
//				{
//
//				}
//			}
//			try
//			{
//				if (mineField[checklist.get(j)+1][checklist.get(k)+i] == true)
//				{
//					mineCount++;
//					
//				}
//				else
//				{
//					
//					tempCheck.add(checklist.get(j)+1);
//					tempCheck.add(checklist.get(k)+i);
//
//				}
//			}
//			catch(ArrayIndexOutOfBoundsException Exception)
//			{
//				
//			}
//			
//		}
//		
//		
//		if(mineCount == 0)
//		{
//			
//			checklist.addAll(tempCheck);
//			
//		}
//		
//		j+=2;
//		k+=2;
//		
//		//updateGrid(coordX, coordY, mineCount);
//		updateGrid(checklist.get(j), checklist.get(k), mineCount);
//		drawGrid();
//		
//		
//	}while(j < checklist.size());
//	
//	
//	
//	
//	
//return mineCount;
//		
//	
	}

	
}