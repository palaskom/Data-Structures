/*	
 * Names:  Palaskos Achilleas		Palaskos Marios
 * DIN:           8493			    8492
 * email:   palach2@yahoo.gr	      mariospala@gmail.com
*/

package gr.auth.ee.dsproject.crush.node;

import java.util.ArrayList;

import gr.auth.ee.dsproject.crush.board.Board;
import gr.auth.ee.dsproject.crush.board.CrushUtilities;
import gr.auth.ee.dsproject.crush.board.Tile;

public class Node84928493 
{
	//orismos matavlitwn klashs.
	Node84928493 Parent;
	ArrayList<Node84928493> children;
	int nodeDepth;
	int[] nodeMove;
	Board nodeBoard;
	double nodeEvaluation;
	
	/*
	 * CONTRUCTORS
	 */
	
	//kaleitai kata th dhmiourgia twn dikwn mou komvwn kai tou antipalou
	public Node84928493(int nodeDepth, int []nodeMove,Board nodeBoard, Node84928493 Parent){
		this.nodeDepth=nodeDepth;
		this.nodeEvaluation=0.0;
		this.nodeMove=nodeMove;
		this.nodeBoard=nodeBoard;
		this.children=new ArrayList<Node84928493>();
		this.Parent=Parent;
	}
	
	// kaleitai kata dhmioyrgia tou komvou th rizas
	public Node84928493(Board board){
		this.nodeDepth = 0;
		this.nodeEvaluation = 0.0;
		this.nodeBoard = board;
		this.children=new ArrayList<Node84928493>();
	}
	
	/*
	 * SETTERS
	 * (vlepe pdf gia perissoteres leptomereies)
	 */
	
	public void setParent(Node84928493 Parent){
		this.Parent=Parent;
	}
	
	public void setChildren(Node84928493 child){
		this.children.add(child);
	}
	
	public void setNodeBoard(Board nodeBoard){
		this.nodeBoard=nodeBoard;
	}
	
	public void setNodeDepth(int nodeDepth){
		this.nodeDepth=nodeDepth;
	}
	
	public void setNodeEvaluation(int[] move,Board board){
		if(getNodeDepth()==1){
			this.nodeEvaluation = moveEvaluation(move, board);
		}
		else if(getNodeDepth()==2){
			this.nodeEvaluation = - moveEvaluation(move, board);
		}
		
	}
	
	public void setNodeEvaluation(double k){
		this.nodeEvaluation = k;
	}
	
	public void setNodeEvaluation(){
		this.nodeEvaluation += getParent().getNodeEvaluation();
	}
	
	public void setNodeMove(int [] nodeMove){
		this.nodeMove=nodeMove;
	}
	
	/*
	 * GETTERS
	 * (vlepe pdf gia perissoteres leptomereies)
	 */
	
	public Node84928493 getParent(){
		return Parent;
	}
	
	public ArrayList<Node84928493> getChildren(){
		return children;
	}
	
	public Board getNodeBoard(){
		return nodeBoard;
	}
	
	public int[]  getNodeMove(){
		return nodeMove;
	}
	
	public int getNodeDepth(){
		return nodeDepth;
	}
	
	public double getNodeEvaluation(){
		return nodeEvaluation;
	}
	
	
	// Vlepe perigrafh sunarthshs sto pdf
		  double moveEvaluation (int[] move, Board board)
		  {
			    double totalPoints = 0.0;
				int moveResults[] = new int[3];
			  	moveResults = deletedCandies(move,board);
			  	int totalCandies = moveResults[0] + chainMoves(CrushUtilities.boardAfterFirstCrush(board, move));
			  	
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
		  
		  /*
		   * Synartisi pou ypologizei ta candies pou feugoun apo ta chain-moves
		   * (vlepe perigrafh sto pdf)
		   * 
		   * AYTH DEN YPHRXE STHN ERGASIA 2
		   */
		  
		  int chainMoves(Board board)
			{
				int[] a1 = new int[2];
				int[] a2 = new int[2];
				int sum = 0; // epipleon synolika candies pou feugoun me ta chain moves
				for(int i=0; i<CrushUtilities.NUMBER_OF_PLAYABLE_ROWS; i++)
				{
					for(int j=0; j<CrushUtilities.NUMBER_OF_COLUMNS; j++)
					{
						a1 = consecutiveHorTiles(i, j, board.giveTileAt(i, j).getColor(), board);
						a2 = consecutiveVerTiles(i, j, board.giveTileAt(i, j).getColor(), board);
						if((a1[0]>0) || (a2[0]>0)) sum++ ; // shmainei oti to tile sto shmeio (i,j) anhkei se toulaxiston mia n-ada
					}
				}
				return sum;
			}
			// int totalCandies = moveResults[0] + chainMoves(boardAfterFirstCrush(board, move));
	 
}
