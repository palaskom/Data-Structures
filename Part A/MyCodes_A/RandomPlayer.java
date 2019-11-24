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
  
 //CONSTRUCTOR: ������������ ��� id
 public RandomPlayer(Integer pid){
	 id = pid;
 }
 
 // SETTERS
 
 // ����� ���� ���� ��������� id
 public void setId(int id){
	 this.id = id;
 }
 
 // ����� ���� ���� name
 public void setName(String name){
	 this.name = name;
 }
 
 // ����� ���� ���� ��������� score
 public void setScore(int score){
	 this.score = score;
 }

 //GETTERS
 
 //������������ ��� ��������� � ���� ��� id
 public int getId(){
	 return id;
 }
 
 //������������ ��� ��������� � ���� ��� name
 public String getName(){
	 return name;
 }
 
 //������������ ��� ��������� � ���� ��� score
 public int getScore(){
	 return score;
 }


  /**
   * ����������� ��� ������� ������� ��� ��������� ������� - ������� �����
   * ���� ��� ������ prev_next_pos.
   */
  public int[] getNextMove (ArrayList<int[]> availableMoves, Board board)
  {
	  // � ������� nextMove �������� ��� ������� ������
	  int [] next��ve = CrushUtilities.getRandomMove(availableMoves , (int)Math.random() * availableMoves.size());
	  
	  int [] prev_next_pos = new int [4]; //������ ��� ���������� �������������� ������
	  prev_next_pos[0] = next��ve[0]; // �� ������ ��� ����� ��� ������ ����� �� ���/��� ���
	  prev_next_pos[1] = next��ve[1]; // ������� �����, ��� ����� ��������/����� �� ���� ���������
	  
	  /** 
	   * ������� �� ��� ���/�� ��� �������� ������� (nextMove[2]) 
	   *  �������������� �� 2 ��������� �������� ��� ��������������  
	   * ������ ��� ������������ ���� ���/��� ��� ���� �����
	  */
	   switch(next��ve[2]){
	  	case CrushUtilities.LEFT: // ��������� '��������� �������' -> �� x ��������� ���� 1
	  		prev_next_pos[2] = next��ve[0] - 1;
	  		prev_next_pos[3] = next��ve[1];
	  		break;
	  	case CrushUtilities.RIGHT: // ��������� '������ �������' -> �� x ��������� ���� 1
	  		prev_next_pos[2] = next��ve[0] + 1;
	  		prev_next_pos[3] = next��ve[1];
	  		break;
	  	case CrushUtilities.UP: //��������� '���� �� ����' ������� -> �� y ��������� ���� 1 
	  		prev_next_pos[2] = next��ve[0];
	  		prev_next_pos[3] = next��ve[1] + 1;
	  		break;
	  	case CrushUtilities.DOWN: //��������� '���� �� ����' ������� -> �� y ��������� ���� 1
	  		prev_next_pos[2] = next��ve[0];
	  		prev_next_pos[3] = next��ve[1] - 1;
	  		break;
	  }
	  return prev_next_pos;
  }

}
