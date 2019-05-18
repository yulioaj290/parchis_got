/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Clases.Boxes.CBox;
import GUI.CToken;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author yulio
 */
public class CFileManager {

    private static String text = null;
    private static FileReader FR;
    private static Scanner input;
    private static FileWriter FW;
    private static PrintWriter output;
    private static int opt = 0;

    public static void loadGameFromFile(CGameMaster gameMaster, Component comp) throws Exception {
        // Show the file shooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("ParchisGame.prs"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Parchís files (*.prs)", "prs");
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
        fileChooser.setFileFilter(filter);
        opt = fileChooser.showOpenDialog(comp);

        if (opt == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // if the file do not exist
            if (!file.exists()) {
                throw new FileNotFoundException(file.getAbsolutePath());
            }

            FR = new FileReader(file);
            input = new Scanner(FR);

            int nPlayers = input.nextInt();     // read the number of players

            int inTurn = input.nextInt();        // read the player in turn

            CPlayer players[] = new CPlayer[nPlayers];
            String name;
            boolean ai;
            int aiInt;
            int house = 0;
            int boxes[][] = new int[nPlayers][4];
            for (int i = 0; i < nPlayers; i++) {
                // read data of the player i            
                name = input.next();    // read name
                house = input.nextInt(); // read the house
                aiInt = input.nextInt();

                if (aiInt == 0) {
                    ai = false;             // read if is AI
                } else {
                    ai = true;              //
                }

                // Create the player
                players[i] = new CPlayer(i, name, house, ai);

                // read the token's positions
                for (int j = 0; j < 4; j++) {
                    boxes[i][j] = input.nextInt();
                }
            }

            // Create the tokens
            CToken[] tokens;
            CBox box = null;
            for (int i = 0; i < nPlayers; i++) {
                tokens = new CToken[4];
                for (int j = 0; j < 4; j++) {
                    // Shoose the box
                    // Normal boxes
                    if (boxes[i][j] < 68) {
                        box = gameMaster.getBoard().map[boxes[i][j]];
                    }

                    // Boxes, path to end
                    if (boxes[i][j] >= 100 && boxes[i][j] < 200) {
                        box = gameMaster.getBoard().pathEnd.get(i)[boxes[i][j] % 10];
                    }

                    // Home Boxes
                    if (boxes[i][j] >= 200 && boxes[i][j] < 300) {
                        box = gameMaster.getBoard().homes[boxes[i][j] % 10];
                    }

                    // Goals Boxes
                    if (boxes[i][j] >= 300 && boxes[i][j] < 400) {
                        box = gameMaster.getBoard().goals[boxes[i][j] % 10];
                    }

                    // create the token
                    tokens[j] = new CToken(j, box, i, players[i].getHouse());

                }
                // Put the tokens to the player i
                players[i].setTokens(tokens);
            }
            input.close();

            // Begin the game
            gameMaster.newGame(nPlayers, inTurn, players);
            gameMaster.getBoard().paint(gameMaster.getBoard().getGraphics());

        }
    }

    public static void saveFile(CGameMaster gameMaster, Component comp) throws IOException {

        // show the file shooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("ParchisGame.prs"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Parchís files (*.prs)", "prs");
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
        fileChooser.setFileFilter(filter);

        opt = fileChooser.showSaveDialog(comp);

        if (opt == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();

            // Create the file to write
            File ficheroW = new File(fileChooser.getSelectedFile().getAbsolutePath());
            FW = new FileWriter(ficheroW);
            output = new PrintWriter(FW);

            // Write the numbers of players
            output.println(gameMaster.nPlayers);
            // Write the player in turn
            output.println(gameMaster.inTurn);

            for (int i = 0; i < gameMaster.nPlayers; i++) {

                // Write the data of the player i
                output.println(gameMaster.getPlayers()[i].getName()); // write the name
                output.println(gameMaster.getPlayers()[i].getHouse()); // write the house
                if (gameMaster.getPlayers()[i].isAI()) {              // write if is AI
                    output.println(1);
                } else {
                    output.println(0);
                }

                // write the positions of the tokens
                for (int j = 0; j < 4; j++) {
                    output.println(gameMaster.getPlayers()[i].getTokens()[j].getBox().id);
                }
            }
            output.close();

        }
    }
}
