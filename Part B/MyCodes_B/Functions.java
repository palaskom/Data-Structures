/*	
 * Names:  Palaskos Achilleas		Palaskos Marios
 * DIN:           8493			    8492
 * email:   palach2@yahoo.gr	      mariospala@gmail.com
*/


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

 
  
 //........................................................................
 
 /* Periptwsh amunas: 
  * a) Eite thelw na antallaksw duo tiles idiou xrwmatos(dinw bathmo 1), kathws kai pali ekei konta
  *    o antipalos den mporei na sxhmatisei n-ada (afou an htan tha thn eixa sxhmatisei egw prwtos)
  * b) Eite thelw me thn kinhsh, se katakorufo kai orizontio aksona me shmeio anaforas to shmeio 
  *    katalhkshs tou tile pou metakinw, na exw tile idiou xrwmatos me autou pou tha metakinhthei se apostash
  *    toulaxiston duo tiles.
  */
 
 boolean nearTiles(int x, int y, int color, Board board)
 {
	  boolean f1, f2, f3, f4;
	  int i = 0;
	  int j = 0;
	  
	  // KATAKORYFA
	  
	  // pros ta panw apo to (x,y)
	  j = y+1;
	  while((j<NUMBER_OF_PLAYABLE_ROWS) && ((j-y)<=2))
	  {
		  if(color == board.giveTileAt(x, j).getColor()) break;
		  else j++;
	  }
	  if(j==NUMBER_OF_PLAYABLE_ROWS) f1 = true;
	  else
	  {
		  if((j-y)>2) f1 = true;
		  else f1 = false;
	  }
	  
	  //pros ta katw apo to (x,y)
	  j = y-1;
	  while((j>=0) && ((y-j)<=2))
	  {
		  if(color == board.giveTileAt(x, j).getColor()) break;
		  else j--;
	  }
	  if(j==-1) f2 = true; // mono otan y<=2 kai ta duo tiles apo katw den einai tou idiou xrwmatos
	  else
	  {
		  if((y-j)>2) f2 = true;
		  else f2 = false;
	  }
	  
	  // ORIZONTIA
	  
	 // pros ta deksia apo to (x,y)
	  
	  i = x+1;
	  while((i<NUMBER_OF_PLAYABLE_ROWS) && ((i-x)<=2))
	  {
		  if(color == board.giveTileAt(i, y).getColor()) break;
		  else i++;
	  }
	  if(i==NUMBER_OF_PLAYABLE_ROWS) f3 = true;
	  else
	  {
		  if((i-x)>2) f3 = true;
		  else f3 = false;
	  }

	// pros ta aristera apo to (x,y)
	  
	  i = x-1;
	  while((i>=0) && ((x-i)<=2))
	  {
		  if(color == board.giveTileAt(i, y).getColor()) break;
		  else i--;
	  }
	  if(i==-1) f4 = true;
	  else
	  {
		  if((x-i)>2) f4 = true;
		  else f4 = false;
	  }
		  
	  /* Prepei kai pros tis 4 kateu8unseis to tile koinou xrwmatos na apexei apostash > 2
	     apo alla tiles idiou xrwmatos */ 
	  if(f1 && f2 && f3 && f4) return true;
	  else return false;
	  
 }
  
  int noTakenCandies(int[] move, Board board)
  {
	  boolean f1, f2;
	  Tile moveTile = board.giveTileAt(move[0], move[1]);
	  switch(move[2])
	  {
	  case CrushUtilities.RIGHT:
		  Tile closeTileR = board.giveTileAt(move[0]+1, move[1]);
		  if(moveTile.getColor()==closeTileR.getColor()) return 1;
		  else
		  {
			  f1 = nearTiles(move[0]+1, move[1], moveTile.getColor(), board);
			  f2 = nearTiles(move[0], move[1], closeTileR.getColor(), board);
			  if(f1 && f2) return 2;
		  }
		  break;
	  case CrushUtilities.LEFT:
		  Tile closeTileL = board.giveTileAt(move[0]-1, move[1]);
		  if(moveTile.getColor()==closeTileL.getColor()) return 1;
		  else
		  {
			  f1 = nearTiles(move[0]-1, move[1], moveTile.getColor(), board);
			  f2 = nearTiles(move[0], move[1], closeTileL.getColor(), board);
			  if(f1 && f2) return 2;
		  }
		  break;
	  case CrushUtilities.UP:
		  Tile closeTileU = board.giveTileAt(move[0], move[1]+1);
		  if(moveTile.getColor()==closeTileU.getColor()) return 1;
		  else
		  {
			  f1 = nearTiles(move[0], move[1]+1, moveTile.getColor(), board);
			  f2 = nearTiles(move[0], move[1], closeTileU.getColor(), board);
			  if(f1 && f2) return 2;
		  }
		  break;
	  case CrushUtilities.DOWN:
		  Tile closeTileD = board.giveTileAt(move[0], move[1]-1);
		  if(moveTile.getColor()==closeTileD.getColor()) return 1;
		  else
		  {
			  f1 = nearTiles(move[0], move[1]-1, moveTile.getColor(), board);
			  f2 = nearTiles(move[0], move[1], closeTileD.getColor(), board);
			  if(f1 && f2) return 2;
		  }
		  break;
	  }
	  return 0;
  }