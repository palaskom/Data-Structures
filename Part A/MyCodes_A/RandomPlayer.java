/*	
 * Names:  Palaskos Achilleas		Palaskos Marios
 * DIN:           8493			    8492
 * email:   palach2@yahoo.gr	      mariospala@gmail.com
*/


package gr.auth.ee.dsproject.crush.player;

import gr.auth.ee.dsproject.crush.CrushUtilities;
import gr.auth.ee.dsproject.crush.board.Board;

import java.util.ArrayList;

public class RandomPlayer implements AbstractPlayer
{
  private int id;
  private String name;
  private int score;
  
 //CONSTRUCTOR: αρχικοποίηση της id
 public RandomPlayer(Integer pid){
	 id = pid;
 }
 
 // SETTERS
 
 // θέτει τιμή στην μεταβλητη id
 public void setId(int id){
	 this.id = id;
 }
 
 // θέτει τιμή στην name
 public void setName(String name){
	 this.name = name;
 }
 
 // θέτει τιμή στην μεταβλητη score
 public void setScore(int score){
	 this.score = score;
 }

 //GETTERS
 
 //επιστρέφεται στη συνάρτηση η τιμή της id
 public int getId(){
	 return id;
 }
 
 //επιστρέφεται στη συνάρτηση η τιμή της name
 public String getName(){
	 return name;
 }
 
 //επιστρέφεται στη συνάρτηση η τιμή της score
 public int getScore(){
	 return score;
 }


  /**
   * Υπολογισμός της τυχαίας κίνησης και επιστροφή αρχικής - τελικής θέσης
   * μέσω του πίνακα prev_next_pos.
   */
  public int[] getNextMove (ArrayList<int[]> availableMoves, Board board)
  {
	  // ο πίνακας nextMove περιέχει την επόμενη κίνηση
	  int [] nextΜοve = CrushUtilities.getRandomMove(availableMoves , (int)Math.random() * availableMoves.size());
	  
	  int [] prev_next_pos = new int [4]; //Δηλωση και δημιουργια επιστρεφομενου πινακα
	  prev_next_pos[0] = nextΜοve[0]; // οι πρωτες δυο τιμες του πινακα ειναι οι συν/νες της
	  prev_next_pos[1] = nextΜοve[1]; // αρχικης θεσης, που ειναι σταθερες/ιδιες σε καθε περιπτωση
	  
	  /** 
	   * αναλογα με την κατ/ση της επομενης κινησης (nextMove[2]) 
	   *  τροποποιουνται τα 2 τελευταια στοιχεια του επιστρεφομενου  
	   * πινακα που αντιστοιχουν στις συν/νες της νεας θεσης
	  */
	   switch(nextΜοve[2]){
	  	case CrushUtilities.LEFT: // περιπτωση 'αριστερης κινησης' -> το x μειωνεται κατα 1
	  		prev_next_pos[2] = nextΜοve[0] - 1;
	  		prev_next_pos[3] = nextΜοve[1];
	  		break;
	  	case CrushUtilities.RIGHT: // περιπτωση 'δεξιας κινησης' -> το x αυξανεται κατα 1
	  		prev_next_pos[2] = nextΜοve[0] + 1;
	  		prev_next_pos[3] = nextΜοve[1];
	  		break;
	  	case CrushUtilities.UP: //περιπτωση 'προς τα πανω' κινησης -> το y αυξανεται κατα 1 
	  		prev_next_pos[2] = nextΜοve[0];
	  		prev_next_pos[3] = nextΜοve[1] + 1;
	  		break;
	  	case CrushUtilities.DOWN: //περιπτωση 'προς τα κατω' κινησης -> το y μειωνεται κατα 1
	  		prev_next_pos[2] = nextΜοve[0];
	  		prev_next_pos[3] = nextΜοve[1] - 1;
	  		break;
	  }
	  return prev_next_pos;
  }

}
