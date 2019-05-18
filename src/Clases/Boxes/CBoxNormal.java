/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Boxes;

import GUI.CToken;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexis
 */
public class CBoxNormal extends CBox {

    public CBoxNormal(int id, int[] xpos, int[] ypos, CBox next) throws Exception {
        super(id, xpos, ypos, next);
    }

    public void generatePositions() {
        Point center, left, right;
        int w = Math.abs(area.xpoints[2] - area.xpoints[0]); // width
        int h = Math.abs(area.ypoints[2] - area.ypoints[0]); // height

        center = new Point((int) (area.xpoints[2] + area.xpoints[0]) / 2 - 14,
                (int) (area.ypoints[2] + area.ypoints[0]) / 2 - 14);

        if (w < h) {        // If it is more height than width
            left = new Point((int) center.getX(), (int) center.getY() - 14);
            right = new Point((int) center.getX(), (int) center.getY() + 14);
        } else {            // If it is more width than height 
            left = new Point((int) center.getX() - 14, (int) center.getY());
            right = new Point((int) center.getX() + 14, (int) center.getY());
        }

        // I put the info on the position's array
        positions = new Point[3];

        positions[0] = center;
        positions[1] = left;
        positions[2] = right;
    }

    public void addToken(CToken token) throws Exception {
        if (tokens.size() >= 2) {
            throw new Exception("Exceded the box capacity.");
        } else {
            token.setBox(this);
            tokens.add(token);
            relocate();
        }
    }

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

    public boolean eating(CToken token) {
        if ((tokens.size() >= 1) && // If have a token
                (tokens.get(0).getPlayer() != token.getPlayer())) { //
            return true;
        }
        return false;

    }

    public boolean thereIsBarrier() {
        if (tokens.size() == 2) {
            return true;
        }
        return false;
    }

    public void quitToken(CToken token) throws Exception {
        if (tokens.isEmpty()) {
            throw new Exception("There is no token in the box.");
        }
        token.setBox(null);
        tokens.remove(token);
        relocate();
    }

    public CToken whichCliked(Point p) {
        for (CToken ficha : tokens) {
            if ((ficha.getLocation().x - p.x <= 10)
                    && (ficha.getLocation().y - p.y <= 10)) {
                return ficha;
            }
        }
        return null;
    }

    public CBox duplicate() {
        CBoxNormal c = null;
        try {
            c = new CBoxNormal(id, this.area.xpoints, this.area.ypoints, null);

        } catch (Exception ex) {
            Logger.getLogger(CBoxHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public CBox getNext(int jugador) {
        return next;
    }
}
