/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Clases.Boxes.CBoxHome;
import Clases.Boxes.CBoxEnd;
import GUI.CDice;
import GUI.CToken;
import GUI.CMainFrame;
import GUI.CBoard;
import java.awt.event.MouseListener;

/**
 *
 * @author Yulio
 */
public class CGameMaster {

    public static int status = 0;
    public static final int NOT_INITIATED = 0;
    public static final int INITIATED = 1;
    public static final int THROWED_DICES = 2;
    public static final int ANIMATING = 3;

    // Players
    public int nPlayers;
    private CPlayer[] players;
    public int inTurn = 0;

    private CBoard board;
    private CDice dice;

    private CTryMove[] moves;

    private CMainFrame mainFrame;

    // Game flow
    private CGameFlow gameFlow;

    public CGameMaster() {
        status = 0;
        inTurn = 0;
        board = new CBoard(this);
        dice = null;
        dice = new CDice(this);
        gameFlow = new CGameFlow(this);
    }

    // Prepare init the board
    public void newGame(int n) {
        nPlayers = n;
        inTurn = 0;

        // Add the tokens
        for (int i = 0; i < nPlayers; i++) {
            CToken[] tokens = new CToken[4];
            for (int j = 0; j < 4; j++) {
                tokens[j] = new CToken(j, board.homes[i], i, this.players[i].getHouse());
            }
            // I assign it to the players
            players[i].setTokens(tokens);
        }

        status = 1;
        gameFlow = new CGameFlow(this);
        gameFlow.start();
    }

    public void newGame(int nJugadores, int inTurn, CPlayer[] players) {

        this.players = players;
        this.nPlayers = nJugadores;
        this.inTurn = inTurn;

        status = 1;
        gameFlow = new CGameFlow(this);
        gameFlow.start();
    }

    public void endGame() {
        status = 0;
        // fi I have the Game initiated
        while (gameFlow.isAlive()) {
            gameFlow.interrupt();
        }

        // Come back to the board to his initial state
        board.quitMarks();         // quit marks
        clearMoves();           // remove moves
        board.clean();              // remove tokens
        players = null;

    }

    public void setMainFrame(CMainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    // Modification
    public void setMoves(CTryMove[] moves) {
        this.moves = moves;
    }

    public void setPlayers(CPlayer[] players) {
        this.players = players;
    }

    public void setBoard(CBoard board) {
        this.board = board;
    }

    public CMainFrame getMainFrame() {
        return mainFrame;
    }

    // Consulata
    public CTryMove[] getMoves() {
        return moves;
    }

    public CPlayer[] getPlayers() {
        return players;
    }

    public CBoard getBoard() {
        return board;
    }

    public int getStatus() {
        return status;
    }

    public boolean allInside(int player) {
        for (int j = 0; j < 4; j++) {
            // If it is not in Home
            if (!(players[player].getTokens()[j].getBox() instanceof CBoxEnd)) {
                return false;
            }
        }
        return true;
    }

    public boolean noneInHome(int jugador) {
        for (int j = 0; j < 4; j++) {
            // if it is not in Home
            if (!(players[jugador].getTokens()[j].getBox() instanceof CBoxHome)) {
                return false;
            }
        }
        return true;
    }

    public int getInTurn() {
        return inTurn;
    }

    public void setInTurn(int inTurn) {
        this.inTurn = inTurn;
    }

    // Utils
    public boolean endedGame() {
        for (int i = 0; i < nPlayers; i++) {
            if (!allInside(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean lightMoves(int diceValue) {
        boolean canPlay = false;
        int forward = diceValue;

        // Move rule
        // If it does not have tokens in home and the dice is 6
        if (noneInHome(inTurn) && diceValue == 6) {
            forward++;          // forward 7
        }
        // Calc the posible moves for each token of the player
        CTryMove mov = null;
        CToken[] tokens = players[inTurn].getTokens();
        moves = new CTryMove[tokens.length];

        boolean thereIsBarrier = false;
        for (int i = 0; i < tokens.length; i++) {
            mov = new CTryMove(this, tokens[i], forward);
            moves[i] = mov;

            // Move rule
            // If dice is 6 and I have a barrier
            if (diceValue == 6 && moves[i].isValid()) {
                if (moves[i].getOrigin().thereIsBarrier()) {
                    thereIsBarrier = true;
                }
                // Cancel the moves that do not need to open a barrier
                if (thereIsBarrier && !moves[i].getOrigin().thereIsBarrier()) {
                    moves[i].setValid(false);
                }
            }

            if (mov.isValid()) {
                canPlay = true;
            }
        }
        board.paint(board.getGraphics());
        return canPlay;
    }

    public void nextTurn() {
        // Pass turn
        inTurn++;
        inTurn = inTurn % nPlayers;

        while (!endedGame() && allInside(inTurn)) {
            inTurn++;
            inTurn = inTurn % nPlayers;
        }

        // Came back the board to the initial state
        board.quitMarks();         // quit marks
        this.status = this.INITIATED;    // Reset the status
        clearMoves();
    }

    public void clearMoves() {
        this.moves = null;       // remove moves
        MouseListener list[] = this.board.getMouseListeners();
        for (MouseListener mouseListener : list) {
            board.removeMouseListener(mouseListener);
        }
        board.paint(board.getGraphics());
    }

    public void setDice(CDice dice) {
        this.dice = dice;
    }

    public CDice getDice() {
        return dice;
    }

    public CGameFlow getGameFlow() {
        return gameFlow;
    }

    public void setGameFlow(CGameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

}
