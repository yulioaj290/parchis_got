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
public class CBoxPathGoal extends CBoxNormal {

    private int player;
    public int rests;

    public CBoxPathGoal(int id, int[] xpos, int[] ypos, CBox next, int player, int rests) throws Exception {
        super(id, xpos, ypos, next);
        this.player = player;
        this.rests = rests;
    }

    @Override
    public void addToken(CToken token) throws Exception {
        if (tokens.size() >= 2) {
            throw new Exception("Exceded the box capacity.");
        } else {
            tokens.add(token);
            token.setBox(this);
            relocate();
        }

    }

    @Override
    public void relocate() {
        switch (tokens.size()) {
            case 1:
                tokens.get(0).setLocation(positions[0]);
                break;
            case 2:
                tokens.get(0).setLocation(positions[1]);
                tokens.get(1).setLocation(positions[2]);
                break;
        }
    }

    // See if in the home path there is barriers
    @Override
    public boolean eating(CToken token) {
        return false;
    }

    @Override
    public boolean thereIsBarrier() {
        return false;
    }

    @Override
    public CBox duplicate() {
        CBoxPathGoal c = null;
        try {
            c = new CBoxPathGoal(id, this.area.xpoints, this.area.ypoints, null, 0, rests);

        } catch (Exception ex) {
            Logger.getLogger(CBoxHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
}
