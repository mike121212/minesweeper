//package first;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BlankCheck {
//	
//	private boolean[][] mineField;
//	private 
//
//
//	
//	public Check(int coordX, int coordY)
//	{
//		
//		int mineCount = 0;
//		int j = 0;
//		int k = 1;
//		
//		List<Integer> checklist = new ArrayList<Integer>();
//		
//		checklist.add(coordX);
//		checklist.add(coordY);
//		
//		
//		if(! "-".equals(playerGrid[coordX][coordY]))
//		{
//			
//			return 10;
//			
//		}
//		
//		//If a cell with a mine is selected
//		if (mineField[coordX][coordY] == true)
//		{
//			mineCount = -1;
//			System.out.println();
//			updateGrid(coordX, coordY, mineCount);
//			System.out.print("You lose! ");
//			System.out.println("Try again?");
//			return mineCount;
//			
//		}
//		
//		
//		do
//		{
//		
//			List<Integer> tempCheck = new ArrayList<Integer>();
//			
//			//this check all cells around the selected cell, except any cells outside the playing area
//			for( int i= -1; i<=1; i++)
//			{
//				
//				try
//				{
//					if (mineField[checklist.get(j-1)][checklist.get(k+i)] == true)
//					{
//						mineCount++;
//						tempCheck.add(checklist.get(j-1));
//						tempCheck.add(checklist.get(k+i));
//	
//					}
//				
//				}
//				
//				//in case of side or corner cells 
//				catch(ArrayIndexOutOfBoundsException Exception)
//				{
//	
//				}
//				
//				//to avoid the selected cell
//				if ( i != 0)
//				{
//					try
//					{
//						if (mineField[checklist.get(j)][checklist.get(k+i)] == true)
//						{
//							mineCount++;
//							tempCheck.add(checklist.get(j));
//							tempCheck.add(checklist.get(k+i));
//	
//						}
//					}
//					catch(ArrayIndexOutOfBoundsException Exception)
//					{
//	
//					}
//				}
//				try
//				{
//					if (mineField[checklist.get(j+1)][checklist.get(k+i)] == true)
//					{
//						mineCount++;
//						tempCheck.add(checklist.get(j+1));
//						tempCheck.add(checklist.get(k+i));
//	
//					}
//				}
//				catch(ArrayIndexOutOfBoundsException Exception)
//				{
//					
//				}
//				
//			}
//			
//			
//			if(mineCount == 0)
//			{
//				
//				checklist.addAll(tempCheck);
//				
//			}
//			
//			j+=2;
//			k+=2;
//			
//			updateGrid(coordX, coordY, mineCount);
//			
//		}while(j< checklist.size());
//		
//		
//		
//		
//		
//		return mineCount;
//			
//		
//	}
//
//}