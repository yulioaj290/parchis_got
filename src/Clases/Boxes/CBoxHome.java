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
public class CBoxHome extends CBox {

    int player;

    public CBoxHome(int id, int[] xpos, int[] ypos, CBox next, int player) throws Exception {
        super(id, xpos, ypos, next);
        this.player = player;
        generatePositions();
    }

    public int getPlayer() {
        return player;
    }

    public void generatePositions() {
        Point reference = new Point();
        positions = new Point[4];
        switch (player) {
            case 0:
                reference.x = area.xpoints[1] - 40;
                reference.y = (int) (area.ypoints[3] + area.ypoints[1]) / 2;
                break;
            case 1:
                reference.x = area.xpoints[1] - 40;
                reference.y = (int) (area.ypoints[3] + area.ypoints[1]) / 2;
                break;
            case 2:
                reference.x = area.xpoints[0] + 12;
                reference.y = (int) (area.ypoints[3] + area.ypoints[0]) / 2;
                break;
            case 3:
                reference.x = area.xpoints[0] + 12;
                reference.y = (int) (area.ypoints[3] + area.ypoints[0]) / 2;
                break;
        }

        positions[0] = new Point(reference.x, reference.y - 65);
        positions[1] = new Point(reference.x, reference.y - 32);
        positions[2] = new Point(reference.x, reference.y + 2);
        positions[3] = new Point(reference.x, reference.y + 35);
    }

    public void addToken(CToken token) throws Exception {
        if (tokens.size() >= 4) {
            throw new Exception("Exceded the box capacity.");
        } else {
            token.setBox(this);
            tokens.add(token);
            relocate();
        }
    }

    public void quitToken(CToken token) throws Exception {
        if (!tokens.isEmpty()) {
            tokens.remove(token);
            token.setBox(null);
            relocate();
        }
    }

    public void relocate() {
        for (int i = 0; i < tokens.size(); i++) {
            tokens.get(i).setLocation(positions[i]);
        }
    }

    public CToken whichCliked(Point p) {
        for (CToken token : tokens) {
            if ((token.getLocation().x - p.x <= 13)
                    && (token.getLocation().y - p.y <= 13)) {
                return token;
            }
        }
        return null;
    }

    public boolean eating(CToken token) {
        return false;
    }

    public boolean thereIsBarrier() {
        return false;
    }

    public CBox duplicate() {
        CBoxHome c = null;
        try {
            c = new CBoxHome(id, this.area.xpoints, this.area.ypoints, null, 0);
        } catch (Exception ex) {
            Logger.getLogger(CBoxHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    @Override
    public CBox getNext(int player) {
        return next;
    }

}
