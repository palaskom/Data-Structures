/*	
 * Names:  Palaskos Achilleas		Palaskos Marios
 * DIN:           8493			    8492
 * email:   palach2@yahoo.gr	      mariospala@gmail.com
*/

package gr.auth.ee.dsproject.crush.player;

import java.util.ArrayList;

import gr.auth.ee.dsproject.crush.board.Board;
import gr.auth.ee.dsproject.crush.board.CrushUtilities;
import gr.auth.ee.dsproject.crush.board.Tile;
import gr.auth.ee.dsproject.crush.defplayers.AbstractPlayer;

public class HeuristicPlayer implements AbstractPlayer
{
	 private static final int NUMBER_OF_PLAYABLE_ROWS = 0;
	// TODO Fill the class code.

	  int score;
	  int id;
	  String name;

	  public HeuristicPlayer (Integer pid)
	  {
	    id = pid;
	    score = 0;
	  }

	  @Override
	  public String getName ()
	  {

	    return "evaluation";

	  }

	  @Override
	  public int getId ()
	  {
	    return id;
	  }

	  @Override
	  public void setScore (int score)
	  {
	    this.score = score;
	  }

	  @Override
	  public int getScore ()
	  {
	    return score;
	  }

	  @Override
	  public void setId (int id)
	  {

	    this.id = id;

	  }

	  @Override
	  public void setName (String name)
	  {

	    this.name = name;

	  }

	  @Override
	  public int[] getNextMove (ArrayList<int[]> availableMoves, Board board)
	  {

	    int[] move = availableMoves.get(findBestMoveIndex(availableMoves, board));

	    return calculateNextMove(move);

	  }

	  int[] calculateNextMove (int[] move)
	  {

	    int[] returnedMove = new int[4];

	    if (move[2] == CrushUtilities.UP) {
	      // System.out.println("UP");
	      returnedMove[0] = move[0];
	      returnedMove[1] = move[1];
	      returnedMove[2] = move[0];
	      returnedMove[3] = move[1] + 1;
	    }
	    if (move[2] == CrushUtilities.DOWN) {
	      // System.out.println("DOWN");
	      returnedMove[0] = move[0];
	      returnedMove[1] = move[1];
	      returnedMove[2] = move[0];
	      returnedMove[3] = move[1] - 1;
	    }
	    if (move[2] == CrushUtilities.LEFT) {
	      // System.out.println("LEFT");
	      returnedMove[0] = move[0];
	      returnedMove[1] = move[1];
	      returnedMove[2] = move[0] - 1;
	      returnedMove[3] = move[1];
	    }
	    if (move[2] == CrushUtilities.RIGHT) {
	      // System.out.println("RIGHT");
	      returnedMove[0] = move[0];
	      returnedMove[1] = move[1];
	      returnedMove[2] = move[0] + 1;
	      returnedMove[3] = move[1];
	    }
	    return returnedMove;
	  }

	  int findBestMoveIndex (ArrayList<int[]> availableMoves, Board board)
	  {	
		  double[] evals = new double[availableMoves.size()];
		  int j = 0;
	      
		  // Apo8hkeuoume tis aksiologhseis twn available move se ena pinaka evals
		  for(int i=0; i<evals.length; i++)
	      {
	    	  evals[i] = moveEvaluation(availableMoves.get(i), board);
	      }
	      
		  /*Briskoume  ton deikth ths kinhshs(poio stoixeio tou pinaka - 1o, 2o klp) me th megisth (max)
		    aksiologhsh kai ton epistrefoume */
		  double max = evals[0];
	      for(int i=1; i<evals.length; i++)
	      {
	    	  if(evals[i] > max)
	    	  {
	    		  max = evals[i];
	    		  j = i;
	    	  }
	      }
	      return j;	 
	  }

	  
	  // Vlepe perigrafh sunarthshs sto pdf
	  double moveEvaluation (int[] move, Board board)
	  {
		    double totalPoints = 0.0;
			int moveResults[] = new int[3];
		  	moveResults = deletedCandies(move,board);
		  	int totalCandies = moveResults[0];
		  	
		  	// 0 pontoi an den feugoun stoixeia
		  	if(totalCandies==0) return 0;
		  	// upologismos sthn periptwsh pou yparxoun tiles pou feugoun
		  	else{
		  		totalPoints = 300.0 * totalCandies;
		  		//exoun fugei stoixeia se grammh
		  		if(moveResults[2]!=CrushUtilities.NUMBER_OF_PLAYABLE_ROWS){
		  			totalPoints +=0.01* Math.exp(CrushUtilities.NUMBER_OF_PLAYABLE_ROWS-moveResults[2]);
		  		}
		  		// exoun fugei se sthlh
		  		if(moveResults[1]!=CrushUtilities.NUMBER_OF_PLAYABLE_ROWS){
		  			totalPoints += 0.005*Math.exp(CrushUtilities.NUMBER_OF_PLAYABLE_ROWS-moveResults[1]);
		  		}
		  	}  
		    return totalPoints;
	  }

	  // Vlepe perigrafh sunarthshs sto pdf
	  int [] consecutiveVerTiles(int x, int y, int color, Board board)
	  {
	  	int [] myarray = new int [2];
	  	int sumCol=1;
	  	int j; // counts vertically
	  	int minj;
	  	
	  	//check above
	  	j=y+1;
	  	while( j<CrushUtilities.NUMBER_OF_PLAYABLE_ROWS)
	  	{
	  		Tile colTile = new Tile();
	  		colTile = board.giveTileAt(x, j);
	  		if(color!=colTile.getColor()) break;
	  		else{
	  			sumCol++;
	  			j++;
	  		}
	  	}
	  	
	  	minj = y; // an sxhmatisei n-ada panw apo to (x,y) tote to mikrotero j einai to y
	  	
	  	// check underneath
	  	j = y-1;
	  	while(j>=0){
	  		Tile colTile = new Tile();
	  		colTile = board.giveTileAt(x, j);
	  		if(color!=colTile.getColor()) break;
	  		else {
	  			sumCol++;
	      		minj = j; // to minj allazei mono otan den exw vgei ektos oriwn kai exw vrei kai allo tile
	      		          // tou idiou xrwmatos me auto sth 8esh (x,y)		  
	      		j--;
	  		}
	  	}
	  	
	  	//epistrofh pinaka -> vlepe pdf
	  	if (sumCol<3){
	  		myarray[0] = 0;
	  		myarray[1] = CrushUtilities.NUMBER_OF_PLAYABLE_ROWS;
	  		return myarray;
	  	}
	  	else{
	  		myarray[0] = sumCol;
	  		myarray[1] = minj;
	  		return myarray;
	  	}
	  		
	  }

	  int [] consecutiveHorTiles(int x, int y, int color, Board board)
	  {
		  	int [] myarray = new int [2];
		  	int sumRows=1;
		  	int i; // counts horizontally
		  	
		  	//check right
		  	i=x+1;
		  	while( i<CrushUtilities.NUMBER_OF_COLUMNS)
		  	{
		  		Tile rowTile = new Tile();
		  		rowTile = board.giveTileAt(i, y);
		  		if(color!=rowTile.getColor()) break;
		  		else{
		  			sumRows++;
		  			i++;
		  		}
		  	}
		  	
		  	// check left
		  	i = x-1;
		  	while(i>=0){
		  		Tile colTile = new Tile();
		  		colTile = board.giveTileAt(i, y);
		  		if(color!=colTile.getColor()) break;
		  		else {
		  			sumRows++;
		      		i--;
		  		}
		  	}
		  	
		  	//epistrofh pinaka -> vlepe pdf
		  	if (sumRows<3){
		  		myarray[0] = 0;
		  		myarray[1] = CrushUtilities.NUMBER_OF_PLAYABLE_ROWS; 
		  		return myarray;
		  	}
		  	else{
		  		myarray[0] = sumRows;
		  		myarray[1] = y;
		  		return myarray;
		  	}
	  }
	  
	  
	//YPOLOGISMOS TWN CANDIES POU DIAGRAFONTAI, YPSOYS XAMHLOTEROY TILE POY FEYGEI KAI ELAXISTHS GRAMMHS
	  
	int[] deletedCandies(int[] move, Board board)
	{
		  int[] r1 = new int[2];
		  int[] r2 = new int[2];
		  int[] r3 = new int[2];
		  int[] r4 = new int[2];
		  
		  int[] result = new int[3]; // pinakas epistofhs -> ta periexomena tou sto pdf

		  Tile moveTile = board.giveTileAt(move[0], move[1]); // to tile pou tha metakinhsoume
		  Tile closeTile = new Tile(); // to diplano tile me to opoio tha ginei antallagh (oi syntetagmenes tou eksartwntai apo thn kinhsh)
		  
		  // klhsh twn consecutive synarthsewn, analoga me thn kinhsh
		  switch(move[2])
		  {
		  case CrushUtilities.RIGHT:
			  // closeTile = deksia tou moveTile
			  closeTile = board.giveTileAt(move[0]+1, move[1]); 
			  // xrwma tou moveTile kai deksi diplano shmeio (x+1,y) -> vlepe pdf
			  r1 = consecutiveVerTiles(move[0]+1, move[1], moveTile.getColor(), board);
			  r2 = consecutiveHorTiles(move[0]+1, move[1], moveTile.getColor(), board);
			  // xrwma tou closeTile kai to shmeio (x,y) tou moveTile -> vlepe pdf
			  r3 = consecutiveVerTiles(move[0], move[1], closeTile.getColor(), board);
			  r4 = consecutiveHorTiles(move[0], move[1], closeTile.getColor(), board);
			  break;
		  case CrushUtilities.LEFT:
			  // closeTile = aristera tou moveTile
			  closeTile = board.giveTileAt(move[0]-1, move[1]);
			  // xrwma tou moveTile kai aritero diplano shmeio (x-1,y)
			  r1 = consecutiveVerTiles(move[0]-1, move[1], moveTile.getColor(), board);
			  r2 = consecutiveHorTiles(move[0]-1, move[1], moveTile.getColor(), board);
			  // xrwma tou closeTile kai to shmeio (x,y) tou moveTile -> vlepe pdf
			  r3 = consecutiveVerTiles(move[0], move[1], closeTile.getColor(), board);
			  r4 = consecutiveHorTiles(move[0], move[1], closeTile.getColor(), board);
			  break;
		  case CrushUtilities.UP:
			  // closeTile = panw tou moveTile
			  closeTile = board.giveTileAt(move[0], move[1]+1); 
			  // xrwma tou moveTile kai epanw diplano shmeio (x,y+1)
			  r1 = consecutiveVerTiles(move[0], move[1]+1, moveTile.getColor(), board);
			  r2 = consecutiveHorTiles(move[0], move[1]+1, moveTile.getColor(), board);
			  // xrwma tou closeTile kai to shmeio (x,y) tou moveTile 
			  r3 = consecutiveVerTiles(move[0], move[1], closeTile.getColor(), board);
			  r4 = consecutiveHorTiles(move[0], move[1], closeTile.getColor(), board); 
			  break;
		  case CrushUtilities.DOWN:
			  // closeTile = katw tou moveTile
			  closeTile = board.giveTileAt(move[0], move[1]-1); 
			  // xrwma tou moveTile kai katw diplano shmeio (x,y-1)
			  r1 = consecutiveVerTiles(move[0], move[1]-1, moveTile.getColor(), board);
			  r2 = consecutiveHorTiles(move[0], move[1]-1, moveTile.getColor(), board);
			  // xrwma tou closeTile kai to shmeio (x,y) tou moveTile 
			  r3 = consecutiveVerTiles(move[0], move[1], closeTile.getColor(), board);
			  r4 = consecutiveHorTiles(move[0], move[1], closeTile.getColor(), board);
			  break;
		  }
		 
		  result[0] = r1[0] + r2[0] + r3[0] + r4[0]; // sunolika pou diagrafontai
		  
		  /* YPSOS KATAKORYFOY XAMHLOTEROY TILE
		     Ypsos xamhloterou Tile pou tha diagrafei apo mia sthlh(or apo duo sthles pairnw thn mikroterh 
		     apostash tis duo)  - perissotera sto pdf - */
		  
		  if(r1[1]<r3[1]) result[1]=r1[1];
		  else result[1]=r3[1];
		  
		  /* YPSOS XAMHLOTERHS GRAMMHS
		    Ypsos ths xamhloterhs grammhs apo thn opoia tha diagrafoun toulaxiston 3 tiles
		    - perissotera sto pdf */
		  
		  if(r2[1]<r4[1]) result[2] = r2[1];
		  else result[2] = r4[1];
		  
		  return result;
	}
	
}
