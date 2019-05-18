/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

//import DEBUG.JDebugWind;
import GUI.CMainFrame;

/**
 *
 * @author alexis
 */
public class Main {

    static int c;

    public static void main(String[] args) {

        CGameMaster gameMaster = new CGameMaster();
        CMainFrame game = new CMainFrame(gameMaster);
        game.setVisible(true);

        gameMaster.setMainFrame(game);

        //JDebugWind devug = new JDebugWind(juego, false, gameMaster);
        //devug.setVisible(true);



    }

}
