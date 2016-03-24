/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

/**
 *
 * @author JordiL
 */
public class MarsRoverTesterFix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String file;
        
        if(args.length==0 || args[0].isEmpty())
            file = "C:\\Users\\JordiL\\Desktop\\Blueliv\\project\\mars.properties";
        else
            file = args[0];

        processTest(file);
    }

    public static void processTest(String propFile) {
        Functions.init(propFile);


        //MarsFunctions.readInputFile(Functions.inputFile);

        SquarePlateau plateau = new SquarePlateau(7, 5);

        plateau.createSQPlateau();

        Functions.mostrarMensajePorConsola(true, Functions.showClassInfo, "", "---------------------------------");
Functions.mostrarMensajePorConsola(true, Functions.showClassInfo, "", "Start Test");
        RoverSQ rover0 = new RoverSQ(0, "E", "MMM", 7, 2);
        RoverSQ rover1 = new RoverSQ(1, "S", "LLL", 7, 2);
        RoverSQ rover2 = new RoverSQ(2, "S", "LLLMLMMRMMMRMLMMMR", 7, 2);

        plateau.addRover(rover0);
        plateau.addRover(rover1);
        plateau.addRover(rover2);

        plateau.moveRover(0);
        plateau.moveRover(1);
        plateau.moveRover(2);

        String msg;
        if (rover0.getPositionX() == 7 && rover0.getPositionY() == 2 && rover0.getHeader().equalsIgnoreCase("E")) {
            msg = "Test Rover0:7 2 E;MMM;Test OK;obtenido:7 2 E";
            Functions.mostrarMensajePorConsola(true, Functions.showClassInfo, "", msg);

        } else {
            msg = "Test Rover0:7 2 E;MMM;Test NoOK;esperado:7 2 E;obtenido:" + rover0.getPositionSring();
            Functions.mostrarMensajePorConsola(true, Functions.showClassInfo, "", msg);

        }
        if (rover1.getPositionX() == 7 && rover1.getPositionY() == 2 && rover1.getHeader().equalsIgnoreCase("W")) {
            msg = "Test Rover1:7 2 S;LLL;Test OK;obtenido:7 2 W";
            Functions.mostrarMensajePorConsola(true, Functions.showClassInfo, "", msg);

        } else {
            msg = "Test Rover1:7 2 S;LLL;Test NoOK;esperado:7 2 W;obtenido:" + rover1.getPositionSring();
            Functions.mostrarMensajePorConsola(true, Functions.showClassInfo, "", msg);

        }

        if (rover2.getPositionX() == 0 && rover2.getPositionY() == 1 && rover2.getHeader().equalsIgnoreCase("N")) {
            msg = "Test Rover2:7 2 S;LLLMLMMRMMMRMLMMMR;Test OK;obtenido:0 1 N";
            Functions.mostrarMensajePorConsola(true, Functions.showClassInfo, "", msg);

        } else {
            msg = "Test Rover2:7 2 S;LLLMLMMRMMMRMLMMMR;Test NoOK;esperado:0 1 N;obtenido:" + rover1.getPositionSring();
            Functions.mostrarMensajePorConsola(true, Functions.showClassInfo, "", msg);

        }

Functions.mostrarMensajePorConsola(true, Functions.showClassInfo, "", "---------------------------------");


    }
}
