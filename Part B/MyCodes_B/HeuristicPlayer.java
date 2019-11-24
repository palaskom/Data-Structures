/*	
 * Names:  Palaskos Achilleas		Palaskos Marios
 * DIN:           8493			    8492
 * email:   palach2@yahoo.gr	      mariospala@gmail.com
*/


package gr.auth.ee.dsproject.crush.player;

import gr.auth.ee.dsproject.crush.CrushUtilities;
import gr.auth.ee.dsproject.crush.board.Board;

import java.util.ArrayList;

public class HeuristicPlayer implements AbstractPlayer
{
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
    // TODO Fill the missing code.

	  
  }

  double moveEvaluation (int[] move, Board board)
  {
    // TODO Fill the missing code.

  }

}
