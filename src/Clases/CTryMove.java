/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Exceptions.InvalidMoveException;
import Clases.Boxes.CBox;
import Clases.Boxes.CBoxHome;
import Clases.Boxes.CBoxEnd;
import Clases.Boxes.CBoxSafe;
import GUI.CToken;
import GUI.CBoard;

/**
 *
 * @author yulio
 */
public class CTryMove {

    // 0 - ready
    // 1 - correct
    // 2 - fail
    public int status;

    public int player;
    public CBoard board;
    public CToken token;
    public int forward;
    public boolean valid = false;
    public CBox destiny;
    public CBox origin;
    public int bonus;
    public CGameMaster gameMaster;

    public CTryMove(CGameMaster gameMaster, CToken token, int forward) {
        this.gameMaster = gameMaster;
        this.board = gameMaster.getBoard();
        this.token = token;
        this.origin = token.getBox();
        this.forward = forward;
        this.status = 0;

        this.destiny = null;
        this.bonus = 0;
        this.player = token.getPlayer();
        lightDestiny();
    }

    private void lightDestiny() {
        origin = token.getBox();
        //
        // If I am in the goal
        //
        if (origin instanceof CBoxEnd) {
            valid = false;
            return;
        }
        int c;
        CBox current;

        //
        // If I am in a home
        //
        if (origin instanceof CBoxHome) {
            // If the dice is 5
            if (forward == 5) {
                current = token.getBox().getNext(this.player);
                destiny = current;
                // If it is filled
                if (destiny.isFilled()) {
                    if (((CBoxSafe) destiny).eatExceptional(token)) {    // If Can I eat
                        destiny.property = CBox.EAT;
                        valid = true;
                    } else {   // There is a barrier
                        destiny.property = CBox.BARRIER;
                        valid = false;
                    }
                    return;
                }
                // If another token will be
                destiny.property = CBox.MOVE;
                valid = true;
                return;
            }
            valid = false;
            return;
        }
        //
        // Normal Box y Path to end
        //
        c = 0;
        current = origin;
        while (c < forward) {
            current = current.getNext(this.player);

            if (current == null || current.thereIsBarrier()) {
                break;
            }
            c++;
        }
        if (c == forward && current != null && !current.thereIsBarrier()) {
            if (current.eating(token)) {
                current.property = CBox.EAT;
            } else {
                current.property = CBox.MOVE;
            }
            valid = true;
            destiny = current;
        } else {
            if (current != null) {
                current.property = CBox.BARRIER;
            }
            valid = false;
        }
    }

    public void doMove() throws Exception {
        if (!valid) {
            status = 2;
            throw new InvalidMoveException();
        }
        if (destiny.property == CBox.EAT) {
            CToken food = null;
            for (CToken f : destiny.getTokens()) {
                if (f.getPlayer() != this.token.getPlayer()) {
                    food = f;
                }
            }
            // Quit the food token 
            destiny.quitToken(food);
            // Add the bonuses
            bonus = 20;

            // Add the food in his house
            board.homes[food.getPlayer()].addToken(food);

            // Quit the new token of the origin
            origin.quitToken(this.token);

            // Add the new token in the destiny
            destiny.addToken(this.token);

            //Next turn
            //gameMaster.nextTurn();
            return;

        }

        if (destiny.property == CBox.MOVE) {
            // Quit the new token of the origin
            origin.quitToken(this.token);

            // Add the new token in the destiny
            destiny.addToken(this.token);

            //Next turn
            //gameMaster.nextTurn();
            return;
        }

    }

    public int getForward() {
        return forward;
    }

    public int getBonus() {
        return bonus;
    }

    public CBox getDestiny() {
        return destiny;
    }

    public CBox getOrigin() {
        return origin;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

}
