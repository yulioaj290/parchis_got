/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Exceptions.NoValidMovesException;
import GUI.CCatcherClicksBoard;
import java.awt.Color;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexis
 */
public class CGameFlow extends Thread {

    CGameMaster gameMaster;
    public static AtomicBoolean block;

    public CGameFlow(CGameMaster gameMaster) {
        this.gameMaster = gameMaster;
        this.setName("Game Flow");
        block = new AtomicBoolean(false);
    }

    public synchronized void littlePause(int n) {
        try {
            sleep(n);
        } catch (InterruptedException ex) {
            Logger.getLogger(CGameMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private synchronized void hold() throws InterruptedException {
        block.set(true);
        while (block.get() && !interrupted()) {
            sleep(50);
        }
        //sleep(50);
    }

    public void release() {
        block.set(false);
    }

    // Principal !!!!!
    public void run() {

        int cont;
        while (!gameMaster.endedGame() && !interrupted()) {
            try {

                // Turn of the player $inTurn
                cont = 0;
                while ((cont % 6 == 0) && (cont < 18)) {  // while dice is 6 no more than 3 times
                    gameMaster.getDice().paintComponents(gameMaster.getDice().getGraphics());   // Paint Dice
                    gameMaster.getMainFrame().updateData(gameMaster.getPlayers()[gameMaster.inTurn].getName(), "Throw dice.", Color.WHITE);

                    if (gameMaster.getPlayers()[gameMaster.inTurn].isAI()) {
                        gameMaster.getDice().animate();
                        gameMaster.getDice().updateValue();
                    } else {
                        gameMaster.getDice().setActive(true);
                        hold();
                        // Thread killing
                        if (gameMaster.status == 0) {
                            return;
                        }
                        gameMaster.getDice().setActive(false);
                    }
                    cont += gameMaster.getDice().getValue();

                    // Light posible moves
                    if (gameMaster.lightMoves(gameMaster.getDice().getValue())) { // Play, if I can
                        playAThrow();
                    } else {
                        throw new NoValidMovesException();
                    }
                    // Thread killing
                    if (gameMaster.status == 0) {
                        return;
                    }

                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CGameMaster.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoValidMovesException ex) {
                gameMaster.getMainFrame().updateData(gameMaster.getPlayers()[gameMaster.inTurn].getName(), ex.getMessage(), Color.red);
                littlePause(500);
                //JOptionPane.showMessageDialog(getBoard(), ex);
            }

            gameMaster.nextTurn();
            // Garbage colector of JVM
            System.gc();
        }
    }

    public void playAThrow() throws InterruptedException {
        // Wait for the player shoose
        while (gameMaster.getMoves() != null && !interrupted()) {
            gameMaster.getBoard().addMouseListener(new CCatcherClicksBoard(gameMaster));

            if (gameMaster.getPlayers()[gameMaster.inTurn].isAI()) {
                littlePause(500);
                gameMaster.getPlayers()[gameMaster.inTurn].play(gameMaster.getMoves());
            } else {
                gameMaster.getMainFrame().updateData(gameMaster.getPlayers()[gameMaster.inTurn].getName(), "Select move.", Color.WHITE);
                hold();
                if (gameMaster.status == 0) {
                    return;
                }
            }
            // Review if I have bonus
            CTryMove next = null;

            for (CTryMove move : gameMaster.getMoves()) {
                if (move.getBonus() != 0) {
                    next = move;
                    break;
                }

            }

            // Clear old moves
            gameMaster.clearMoves();
            // if I have bonuses
            if (next != null) {
                // light new paths
                gameMaster.lightMoves(next.getBonus());
            }
        }
    }

}
