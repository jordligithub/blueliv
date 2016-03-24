/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

/**
 *
 * @author JordiL
 */
public class RoverSQ extends Rover {
    protected int positionX;
    protected int positionY;

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    
               
     public RoverSQ(int RoverID, String header, String movements, int positionX, int positionY){
      super(RoverID, header, movements);
      this.positionX = positionX;
      this.positionY = positionY;
   }
     
    @Override
     public String getPositionSring(){
         String str = this.positionX + " " + this.positionY + " " + this.getHeader();
         return str;
     }
}
