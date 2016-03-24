/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

/**
 *
 * @author JordiL
 */
public class MarsRover {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String file;
     //   String file = args[0];
         if(args.length==0 || args[0].isEmpty())
                file = "C:\\Users\\JordiL\\Desktop\\Blueliv\\project\\marsTest.properties";
        else
            file = args[0];

        process(file);
    }
    
    public static void process(String propFile){
        Functions.init(propFile);
        MarsFunctions.readInputFile(Functions.inputFile);
        
        SquarePlateau plateau = new SquarePlateau(MarsFunctions.getPlateauMaxX() , MarsFunctions.getPlateauMaxY());
        
        plateau.createSQPlateau();
        
        for(int i=0; i< MarsFunctions.getRoverSize(); i++){
            plateau.addRover(MarsFunctions.getRover(i));
        }
        for(int i=0; i< MarsFunctions.getRoverSize(); i++){
            plateau.moveRover(i);
        }
        
        
      
        
        if(!Functions.getTestResultExpected().isEmpty()){
            String msg1;
            
            
            if(Functions.getTestResultExpected().trim().equalsIgnoreCase(plateau.getRoversPositions().trim()))
                msg1="Test OK";
            else
                msg1="Test No OK";
                      
            String msg2="ExpectedResult: "+Functions.getTestResultExpected();
            String msg3="ObtainedResult: "+plateau.getRoversPositions();
            
            Functions.writeOutput(Functions.clearOutputFile, "-----------------------");
            Functions.writeOutput(false, msg1);
            Functions.writeOutput(false, msg2);
            Functions.writeOutput(false, msg3);
            Functions.writeOutput(false, "-----------------------");
        }else{
            Functions.writeOutput(Functions.clearOutputFile, plateau.getRoversPositions());
        }

    }
    

}
