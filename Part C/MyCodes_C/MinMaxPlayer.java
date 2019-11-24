/*	
 * Names:  Palaskos Achilleas		Palaskos Marios
 * DIN:           8493			    8492
 * email:   palach2@yahoo.gr	      mariospala@gmail.com
*/

package gr.auth.ee.dsproject.crush.player;

import gr.auth.ee.dsproject.crush.board.Board;
import gr.auth.ee.dsproject.crush.board.CrushUtilities;
import gr.auth.ee.dsproject.crush.defplayers.AbstractPlayer;
import gr.auth.ee.dsproject.crush.node.Node84928493;

import java.util.ArrayList;

public class MinMaxPlayer implements AbstractPlayer
{
  // TODO Fill the class code.

  int score;
  int id;
  String name;

  public MinMaxPlayer (Integer pid)
  {
    id = pid;
    score = 0;
  }

  public String getName ()
  {

    return "MinMax";

  }

  public int getId ()
  {
    return id;
  }

  public void setScore (int score)
  {
    this.score = score;
  }

  public int getScore ()
  {
    return score;
  }

  public void setId (int id)
  {
    this.id = id;
  }

  public void setName (String name)
  {
    this.name = name;
  }

  public int[] getNextMove (ArrayList<int[]> availableMoves, Board board)
  {
	  Board nowBoard = CrushUtilities.cloneBoard(board); // board before my move
	  Node84928493 root = new Node84928493(nowBoard); // dhmiourgia ths rizas tou dentrou
	  createMySubTree(root,1); // dhmiourgia dentrou
	  
	  int indexBest = 0;
	  indexBest = chooseMove (root); // epistofh tou deikth kalyterhs kinhshs
	  int[] bestMove = availableMoves.get(indexBest); //euresh ths kinhshs pou antistoixei sto deikth
	  												  // auto.

	  return CrushUtilities.calculateNextMove(bestMove);
    
  }
  
  /*
   * Dimiourgia tou dikou mou ypodentrou pou prokyptei me basei tis diathesimes kiniseis mou kai 
   * kai klisi ths createOpponentSubTree ().
   */

  private void createMySubTree (Node84928493 parent, int depth)
  {
	  // Euresh twn diathesimwn kinhsewn mou
	  ArrayList<int[]> myAvailableMoves = new ArrayList<int[]>();
	  myAvailableMoves = CrushUtilities.getAvailableMoves(parent.getNodeBoard());
	  
	  //GIA KATHE MIA apo tis diathesimes kinhseis MOU:
	  //1.vriskw to board pou antistoixei sth sygkekrimenh kinhsh 
	  //2.ypologizw to evaluation ths kinhshs - tou komvou
	  //3.thetw kathena apo tous komvous autous ws paidi ths rizas
	  //4.dhmiourgw to ypodentro tou antipalou
	  
	  for(int i=0; i<myAvailableMoves.size(); i++){
		  //board after my move without deleting any candies
		  Board boardAfterMyMove = CrushUtilities.boardAfterFirstMove(parent.getNodeBoard(), myAvailableMoves.get(i));
		  //dhmiourgia komvou pou antistoixei sthn i-osth kinhsh
		  Node84928493 childNode = new Node84928493(depth, myAvailableMoves.get(i), boardAfterMyMove, parent);
		  //ypologismos tou evaluation ths kinhshs
		  childNode.setNodeEvaluation(childNode.getNodeMove(),parent.getNodeBoard());
		  parent.setChildren(childNode); //thetw ton komvo ws paidi ths rizas
		  //kaleitai h synarthsh dhmiourgias tou ypodentrou tou antipalou
		  createOpponentSubTree(childNode,childNode.getNodeDepth()+1);
	  }
  }
  
  /*
   * Dimiourgia tou ypodentrou tou antipalou pou prokyptei me basei tis diathesimes kiniseis tou 
   */

  private void createOpponentSubTree (Node84928493 parent, int depth)
  {
	  // board after my full move (deleting candies, chain moves etc)
	  // and before opponent's move.
	  Board boardBefOpMove = CrushUtilities.boardAfterFullMove(parent.getParent().getNodeBoard(), parent.getNodeMove());
	  
	  // Euresh twn diathesimwn kinhsewn tou antipalou
	  ArrayList<int[]> oponAvailableMoves = new ArrayList<int[]>();
	  oponAvailableMoves = CrushUtilities.getAvailableMoves(boardBefOpMove);
	  
	  //GIA KATHE MIA apo tis diathesimes kinhseis TOU ANTIPALOU:
	  //1.vriskw to board pou antistoixei sth sygkekrimenh diadoxh kinhsewn(dikia mou - diakia tou) 
	  //2.ypologizw to evaluation ths kinhshs
	  //3.allagh tou evaluation tou komvou ws to athroisma aksiologhsewn dikis mou + dikis tou kinhshs
	  //4.thetw kathena apo tous komvous autous ws paidi ths rizas
	  
	  for(int j=0; j<oponAvailableMoves.size(); j++){
		  //board after opponent's move without deleting any candies
		  Board boardAfterOponMove = CrushUtilities.boardAfterFirstMove(boardBefOpMove, oponAvailableMoves.get(j));
		  //each of the j "childNode2" is child of the corresponding "childNode"
		  Node84928493 childNode2 = new Node84928493(parent.getNodeDepth()+1, oponAvailableMoves.get(j), boardAfterOponMove, parent);
		  childNode2.setNodeEvaluation(childNode2.getNodeMove(),boardBefOpMove);//ypologismos tou evaluation ths kinhshs
		  childNode2.setNodeEvaluation(); //allagh tou evaluation tou komvou opws perigrafetai sto pdf
		  parent.setChildren(childNode2);//thetw ton komvo ws paidi ths rizas
	  }
  }
  
  /*
   * Meta th dhmiourgia oloklhrou tou dentrou efarmozw algoritmo MIN-MAX gia na brw to deikth ths kinhshs
   * mou h opoia mou apoferei to megalyterh bathmologia. O deikths autos epistrefetai apo thn synarthsh. 
   */

  private int chooseMove (Node84928493 root)
  {

	  //maxEval: h megisth timh apo tis aksiologiseis pou tha "anevoun" stous dikous mou komvous
	  //arxikopoieitai se mia megisth arnhtikh timh gia na doulepsei o algoritmos euresis megistou
	  double maxEval = -100000000000000.0;
	  int indexBest = 0; //o deikths ths kinhshs mou pou antistoixei sthn parapanw megisth timh.
	  					 //auth h timh epistrefetai apo th synarthsh
	  
	  
	  
	  for(int i=0; i<root.getChildren().size(); i++){
		  Node84928493 myNodes = root.getChildren().get(i);
		  
		  // An se kapoia apo tis kinhseis mou DEN yparxoun diathesimes kinhseis gia ton antipalo tote
		  // epeidh ginetai reset to board pairnw mono egw pontous ara symferei auth h kinhsh gi' auto 
		  // se authn thn periptvsh epistrefetai o deikths pou antistoixei se auth thn kinhsh.
		  if(myNodes.getChildren().size()==0) {
			  return i;
		  }
		  
		  //MinEval: to elaxisto evaluation apo auta twn komvwv tou antipalou pou antistoixoun ston
		  //		 i-sto diko mou.	
		  //Arxikopoiw thn minEval ws to evaluation tou PRWTOY komvou tou i-stou dikou mou node
		  double minEval = myNodes.getChildren().get(0).getNodeEvaluation(); 
		  
		  // diadikasia eureshs elaxistou -> ypologismos minEval
		  for(int j=1; j<myNodes.getChildren().size(); j++){
			  Node84928493 oponNodes = myNodes.getChildren().get(j);
			  
			  if(oponNodes.getNodeEvaluation()<minEval){
				  minEval = oponNodes.getNodeEvaluation();
			  }
		  }
		  myNodes.setNodeEvaluation(minEval); // thetw ws evaluation tou i-stou komvou mou to min
		  									  // "twv paidiwn tou".
		  // ypologismos tou indexBest
		  if(myNodes.getNodeEvaluation()>maxEval){
			  maxEval = myNodes.getNodeEvaluation();
			  indexBest = i;
		  }
	  }
	  return indexBest;
  }
}
