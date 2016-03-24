/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

/**
 *
 * @author JordiL
 */
public abstract class Rover {
   protected int RoverID;
   protected String header;
   protected String movements;
   public Rover(int RoverID, String header, String movements){
       this.RoverID = RoverID;
       this.header = header;
       this.movements = movements;
   }
   abstract public String getPositionSring();

   
   public void setMovements(String movements){
       this.movements = movements;
   }

    public int getRoverID() {
        return RoverID;
    }

    public String getHeader() {
        return header;
    }

    public String getMovements() {
        return movements;
    }

    public void setRoverID(int RoveID) {
        this.RoverID = RoveID;
    }

    public void setHeader(String header) {
        this.header = header;
    }
   
}
