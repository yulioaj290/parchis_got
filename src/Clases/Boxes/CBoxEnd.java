/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases.Boxes;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexis
 */
public class CBoxEnd extends CBoxHome{

    public CBoxEnd(int id, int[] xpos, int[] ypos,  int player) throws Exception {
        super(id, xpos, ypos, null, player);
    }

    public CBox duplicate() {
        CBoxEnd c = null;
        try {
            c = new CBoxEnd(id, this.area.xpoints, this.area.ypoints, 0);

        } catch (Exception ex) {
            Logger.getLogger(CBoxHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public void generatePositions() {
        Point reference = new Point();
        positions = new Point[4];
        switch (player) {
            case 0:
                reference.x = this.area.xpoints[2]-12;
                reference.y = this.area.ypoints[2]-12-60;

                positions[0] = new Point(reference.x, reference.y);
                positions[1] = new Point(reference.x-30, reference.y);
                positions[2] = new Point(reference.x+30, reference.y);
                positions[3] = new Point(reference.x, reference.y + 30);
                break;
            case 1:
                reference.x = this.area.xpoints[1]-12-60;
                reference.y = this.area.ypoints[1]-12;

                positions[0] = new Point(reference.x, reference.y);
                positions[1] = new Point(reference.x, reference.y - 30);
                positions[2] = new Point(reference.x, reference.y + 30);
                positions[3] = new Point(reference.x + 30, reference.y);
                break;
            case 2:
                reference.x = this.area.xpoints[0]-12;
                reference.y = this.area.ypoints[0]-12+60;

                positions[0] = new Point(reference.x, reference.y);
                positions[1] = new Point(reference.x-30, reference.y);
                positions[2] = new Point(reference.x+30, reference.y);
                positions[3] = new Point(reference.x, reference.y - 30);
                break;
            case 3:
                reference.x = this.area.xpoints[1]-12+60;
                reference.y = this.area.ypoints[1]-12;

                positions[0] = new Point(reference.x, reference.y);
                positions[1] = new Point(reference.x, reference.y - 30);
                positions[2] = new Point(reference.x, reference.y + 30);
                positions[3] = new Point(reference.x - 30, reference.y);
        }
    }

    @Override
    public CBox getNext(int player) {
        return null;
    }
}
