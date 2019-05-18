/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases.Boxes;

import GUI.CToken;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexis
 */
public class CBoxSafe extends CBoxNormal {

    boolean input;
    int player;
    CBox homePath;

    public CBoxSafe(int id, int[] xpos, int[] ypos, CBox next, boolean input, int player, CBox homePath) throws Exception {
        super(id, xpos, ypos, next);
        this.input = input;
        this.player = player;
        this.homePath = homePath;
    }


    public CBox getHomePath() {
        return homePath;
    }

    public void setHomePath(CBox homePath) {
        this.homePath = homePath;
    }

    public boolean isInput() {
        return input;
    }

    public int getPlayer() {
        return player;
    }

    public void setInput(boolean input) {
        this.input = input;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    @Override
    public boolean eating(CToken token) {
        return false;
    }
    public boolean eatExceptional(CToken token) {
        for (CToken cToken : tokens) {
            if (cToken.getPlayer() != token.getPlayer())
                return true;
        }
        return false;
    }

    @Override
    public CBox duplicate() {
        CBoxSafe c = null;
        try {
            c = new CBoxSafe(id, this.area.xpoints, this.area.ypoints, null, input, player, null);

        } catch (Exception ex) {
            Logger.getLogger(CBoxHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    @Override
    public CBox getNext(int jugador) {
        if ((input) && (jugador == this.player) )
            return homePath;
        else
            return next;
    }

}
