/*
 		        ����� ��������� - 1� �������
 	�������������:  �������� ��������       �������� ������
 	���:                  8493                    8492
 	���:               6946949687              6986012613
 	e-mail:         palach2@yahoo.gr      mariospala@gmail.com
 */


package gr.auth.ee.dsproject.crush.board;

public class Tile
{

  protected int id;
  private int x;
  private int y;
  private int color;
  private boolean mark;

  //CONSTRUCTOR: ������������ ��� �������� ����������
  public Tile(int id, int x, int y, int color, boolean mark){
	  this.id = id;
	  this.x = x;
	  this.y = y;
	  this.color = color;
	  this.mark = mark;
  }
  
  //SETTERS
  
  // ����� ���� ���� ��������� id
  public void setId(int id){
	  this.id = id;
  }
  
  // ����� ���� ���� ��������� x
  public void setX(int x){
	  this.x = x;
  }
  
  // ����� ���� ���� ��������� y
  public void setY(int y){
	  this.y = y;
  }
  
  // ����� ���� ���� ��������� color
  public void setColor(int color){
	  this.color = color;
  }
  
  // ����� ���� ���� ��������� mark
  public void setMark(boolean mark){
	  this.mark = mark;
  }
  
  //GETTERS
  
  //������������ ��� ��������� � ���� ��� id
  public int getId(){
	  return id;
  }
  
  //������������ ��� ��������� � ���� ��� x
  public int getX(){
	  return x;
  }
  
  //������������ ��� ��������� � ���� ��� y
  public int getY(){
	  return y;
  }
  
  //������������ ��� ��������� � ���� ��� color
  public int getColor(){
	  return color;
  }
  
  //������������ ��� ��������� � ���� ��� mark
  public boolean getMark(){
	  return mark;
  }
}
