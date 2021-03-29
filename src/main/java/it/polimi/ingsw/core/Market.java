package it.polimi.ingsw.core;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class which represents the market structure, composed by (3 lines) * (4 columns) marbles
 * @author Martina Magliani
 */

public class Market {

    Marble[][] structure;
    int line, column, pos;
    Marble reserveMarble, x;
    ArrayList<Marble> initialStructure = new ArrayList<Marble>();
    Random r = new Random();
    int count = 12;

    /**
     * Class constructor
     */
    public Market(){
        initialStructure.add(Marble.WHITE);
        initialStructure.add(Marble.WHITE);
        initialStructure.add(Marble.WHITE);
        initialStructure.add(Marble.WHITE);
        initialStructure.add(Marble.BLUE);
        initialStructure.add(Marble.BLUE);
        initialStructure.add(Marble.GREY);
        initialStructure.add(Marble.GREY);
        initialStructure.add(Marble.YELLOW);
        initialStructure.add(Marble.YELLOW);
        initialStructure.add(Marble.PURPLE);
        initialStructure.add(Marble.PURPLE);
        initialStructure.add(Marble.RED);

        structure = new Marble[3][4];

        for(int i=0; i<3; i++){
            for(int j=0; j<4; j++){
                pos = r.nextInt(count);
                x = initialStructure.get(pos);
                initialStructure.remove(pos);
                structure[i][j] = x;
                count--;
            }
        }
        reserveMarble = initialStructure.get(0);
    }

    /**
     * Getter Method
     * @return the structure of the market place
     */
    public Marble[][] getStructure() {
        return structure.clone();
    }

    /**
     * Getter method.
     * @param line the number of the line selected by the player
     * @return the selected line.
     */
    public ArrayList<Resource> getLine(int line){
        //ritorniamo l'array list delle 4 risorse che verranno gestite dal controller e sistemiamo il mercato
        return null;
    }

    /**
     * Getter method.
     * @param column the number of the column selected by the player
     * @return the selected column.
     */
    public ArrayList<Resource> getColumn(int column){
        return null;
    }

    //TODO: (marti) implementare i metodi getLine and getColumn, (group) selezionare i metodi utili alla classe.

}