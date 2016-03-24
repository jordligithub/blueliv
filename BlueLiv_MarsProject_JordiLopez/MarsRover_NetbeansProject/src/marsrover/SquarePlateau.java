/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

import java.util.HashMap;

/**
 *
 * @author JordiL
 */
public class SquarePlateau extends Plateau {

    protected int maxSizeX;
    protected int maxSizeY;

    public SquarePlateau(int maxX, int maxY) {
        this.maxSizeX = maxX;
        this.maxSizeY = maxY;
        this.rovers = new HashMap<Integer, Rover>();
        this.plateau = new HashMap<Integer, HashMap>();


    }

    @Override
    public boolean moveAllowed(int y, int x, String header) {
        //TODO
        return false;
    }

    public boolean moveAllowed(int RoverID) {
        Rover rv = this.rovers.get(RoverID);
        if (rv.getClass() == RoverSQ.class) {
            int posX = ((RoverSQ) rv).getPositionX();
            int posY = ((RoverSQ) rv).getPositionY();
            String header = ((RoverSQ) rv).getHeader();

            if (header.equalsIgnoreCase("E") && posX + 1 <= maxSizeX) {
                return true;
            } else if (header.equalsIgnoreCase("W") && posX - 1 >= 0) {
                return true;
            } else if (header.equalsIgnoreCase("N") && posY + 1 <= maxSizeY) {
                return true;
            } else if (header.equalsIgnoreCase("S") && posY - 1 >= 0) {
                return true;
            }

        }
        return false;
    }

    public void processMoveRover(int RoverID) {
        //actualizar rover pos
         Rover rv = this.rovers.get(RoverID);
        if (rv.getClass() == RoverSQ.class) {
            int posX = ((RoverSQ) rv).getPositionX();
            int posY = ((RoverSQ) rv).getPositionY();
            String header = ((RoverSQ) rv).getHeader();
            ((SquarePlateauPoint) this.plateau.get(posY).get(posX)).setPositionOccupied(false);
            if (header.equalsIgnoreCase("E")) {
                posX +=1;
            } else if (header.equalsIgnoreCase("W")) {
                posX -=1;
            } else if (header.equalsIgnoreCase("N")) {
                posY+=1;
            } else if (header.equalsIgnoreCase("S")) {
                posY-=1;
            }else{
                System.out.println("error");
            }
            //actualizo rover
            ((RoverSQ) rv).setPositionX(posX);
            ((RoverSQ) rv).setPositionY(posY);
           //actualizo plateau
            ((SquarePlateauPoint) this.plateau.get(posY).get(posX)).setPositionOccupied(true);            
           
        }
    }

    public String getNewHeader(String actualHeader, String move) {

        if (move.equalsIgnoreCase("R")) {
            if (actualHeader.equalsIgnoreCase("N")) {
                return "E";
            } else if (actualHeader.equalsIgnoreCase("E")) {
                return "S";
            } else if (actualHeader.equalsIgnoreCase("S")) {
                return "W";
            } else if (actualHeader.equalsIgnoreCase("W")) {
                return "N";
            }
        }

        if (move.equalsIgnoreCase("L")) {
            if (actualHeader.equalsIgnoreCase("N")) {
                return "W";
            } else if (actualHeader.equalsIgnoreCase("E")) {
                return "N";
            } else if (actualHeader.equalsIgnoreCase("S")) {
                return "E";
            } else if (actualHeader.equalsIgnoreCase("W")) {
                return "S";
            }
        }
        System.out.println("Movimiento erroneo");
        return "Error";
    }

    @Override
    public void moveRover(int RoverID) {
        String movements = rovers.get(RoverID).getMovements();
        for (int i = 0; i < movements.length(); i++) {
            String move = Character.toString(movements.charAt(i));


            if (move.equalsIgnoreCase("L") || move.equalsIgnoreCase("R")) {
                String msg = this.rovers.get(RoverID).getHeader() + " -> ";
                this.rovers.get(RoverID).setHeader(getNewHeader(this.rovers.get(RoverID).getHeader(), move));
                 msg += this.rovers.get(RoverID).getHeader();
                                 Functions.mostrarMensajePorConsola(Functions.showMessages, Functions.showClassInfo, "", msg);

            } else if (move.equalsIgnoreCase("M")) {
                String msg = this.rovers.get(RoverID).getHeader()+ " "+rovers.get(RoverID).getPositionSring();
                boolean allowed=false;
                if (moveAllowed(RoverID)) {
                    processMoveRover(RoverID);
                    allowed=true;
                }
                msg +=" -> "+ rovers.get(RoverID).getPositionSring() +" allowed "+allowed;
                Functions.mostrarMensajePorConsola(Functions.showMessages, Functions.showClassInfo, "", msg);

            } else {
                System.out.println("error caracter:"+move);
                break;
            }


        }
     
    }

    public void createSQPlateau() {
        this.plateau = new HashMap<Integer, HashMap>();
        for (int i = 0; i <= this.maxSizeY; i++) {
            HashMap<Integer, PlateauPoint> row = new HashMap<Integer, PlateauPoint>();
            for (int j = 0; j <= this.maxSizeX; j++) {
                SquarePlateauPoint pos = new SquarePlateauPoint(j, i, false);
                row.put(j, pos);
            }
            this.plateau.put(i, row);
        }
    }
    //HashMap<Integer,Rover> Rovers;
    //HashMap<Integer,HashMap> Plateau;

    public void addRover(int id, String header, int positionY, int positionX) {
        /**
         * creates a Rover and adds it to the plateau
         */
        Rover rv = new RoverSQ(id, header, null, positionY, positionX);
        //HashMap<Integer,Rover> Rovers;
        //HashMap<Integer,HashMap> Plateau;    

        this.rovers.put(id, rv);
        ((SquarePlateauPoint) this.plateau.get(positionY).get(positionX)).setPositionOccupied(true);

    }

    public void addRover(Rover rover) {
        /**
         * adds a Rover to the plateau
         */
        if (!(rover == null || this.rovers == null)) {
            this.rovers.put(rover.getRoverID(), rover);

            if (rover.getClass() == RoverSQ.class) {
                int posX = ((RoverSQ) rover).getPositionX();
                int posY = ((RoverSQ) rover).getPositionY();
                ((SquarePlateauPoint) this.plateau.get(posY).get(posX)).setPositionOccupied(true);
            }
        } else {
            if (rover == null) {
                System.out.println("error rover null");
            }

            if (this.rovers == null) {
                System.out.println("error rovers null");
            }
        }
    }
}
