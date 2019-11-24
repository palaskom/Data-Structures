/*
 		        ΔΟΜΕΣ ΔΕΔΟΜΕΝΩΝ - 1η ΕΡΓΑΣΙΑ
 	Ονοματεπώνυμα:  Παλάσκος Αχιλλέας       Παλάσκος Μάριος
 	ΑΕΜ:                  8493                    8492
 	ΤΗΛ:               6946949687              6986012613
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

  //CONSTRUCTOR: αρχικοποίηση των παραπάνω μεταβλητών
  public Tile(int id, int x, int y, int color, boolean mark){
	  this.id = id;
	  this.x = x;
	  this.y = y;
	  this.color = color;
	  this.mark = mark;
  }
  
  //SETTERS
  
  // θέτει τιμή στην μεταβλητη id
  public void setId(int id){
	  this.id = id;
  }
  
  // θέτει τιμή στην μεταβλητη x
  public void setX(int x){
	  this.x = x;
  }
  
  // θέτει τιμή στην μεταβλητη y
  public void setY(int y){
	  this.y = y;
  }
  
  // θέτει τιμή στην μεταβλητη color
  public void setColor(int color){
	  this.color = color;
  }
  
  // θέτει τιμή στην μεταβλητη mark
  public void setMark(boolean mark){
	  this.mark = mark;
  }
  
  //GETTERS
  
  //επιστρέφεται στη συνάρτηση η τιμή της id
  public int getId(){
	  return id;
  }
  
  //επιστρέφεται στη συνάρτηση η τιμή της x
  public int getX(){
	  return x;
  }
  
  //επιστρέφεται στη συνάρτηση η τιμή της y
  public int getY(){
	  return y;
  }
  
  //επιστρέφεται στη συνάρτηση η τιμή της color
  public int getColor(){
	  return color;
  }
  
  //επιστρέφεται στη συνάρτηση η τιμή της mark
  public boolean getMark(){
	  return mark;
  }
}
