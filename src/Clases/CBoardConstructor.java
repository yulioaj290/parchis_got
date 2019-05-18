/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Clases.Boxes.CBox;
import Clases.Boxes.CBoxPathGoal;
import Clases.Boxes.CBoxHome;
import Clases.Boxes.CBoxEnd;
import Clases.Boxes.CBoxNormal;
import Clases.Boxes.CBoxSafe;
import java.awt.Point;
import java.awt.Polygon;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alexis
 */
public abstract class CBoardConstructor {

    public static CBox[] generateHomes(int players) throws Exception {
        CBoxHome[] homes = new CBoxHome[players];
        int i = 0;

        int xpos[] = new int[4];
        int ypos[] = new int[4];

        // Generate the home of the 1st player
        if (i < players) {
            xpos[0] = 0;
            xpos[1] = 196;
            xpos[3] = 0;
            xpos[2] = 196;
            ypos[0] = 0;
            ypos[1] = 0;
            ypos[3] = 196;
            ypos[2] = 196;

            homes[i] = new CBoxHome(200, xpos, ypos, null, 0);

            i++;
        }
        // Generate the home of the 2nd player
        if (i < players) {
            xpos[0] = 0;
            xpos[1] = 196;
            xpos[3] = 0;
            xpos[2] = 196;
            ypos[0] = 404;
            ypos[1] = 404;
            ypos[3] = 600;
            ypos[2] = 600;
            homes[i] = new CBoxHome(201, xpos, ypos, null, 1);

            i++;
        }
        // Generate the home of the 3rd player
        if (i < players) {
            xpos[0] = 404;
            xpos[1] = 600;
            xpos[3] = 404;
            xpos[2] = 600;
            ypos[0] = 404;
            ypos[1] = 404;
            ypos[3] = 600;
            ypos[2] = 600;

            homes[i] = new CBoxHome(202, xpos, ypos, null, 2);

            i++;
        }
        // Generate the home of the 4th player
        if (i < players) {
            xpos[0] = 404;
            xpos[1] = 600;
            xpos[3] = 404;
            xpos[2] = 600;
            ypos[0] = 0;
            ypos[1] = 0;
            ypos[3] = 196;
            ypos[2] = 196;
            homes[i] = new CBoxHome(203, xpos, ypos, null, 3);
            i++;
        }
        return homes;

    }

    public static CBox[] generateGoals() {
        CBoxEnd[] goals = new CBoxEnd[4];
        Point p13 = new Point(224, 224);
        Point p16 = new Point(376, 224);
        Point p25 = new Point(300, 300);
        Point p22 = new Point(224, 376);
        Point p19 = new Point(376, 376);

        Polygon pol = null;
        try {
            // Goal 1;
            pol = new Polygon();
            pol.addPoint(p13.x, p13.y);
            pol.addPoint(p16.x, p16.y);
            pol.addPoint(p25.x, p25.y);
            goals[0] = new CBoxEnd(300, pol.xpoints, pol.ypoints, 0);
            // Goal 2
            pol = null;
            pol = new Polygon();
            pol.addPoint(p13.x, p13.y);
            pol.addPoint(p25.x, p25.y);
            pol.addPoint(p22.x, p22.y);
            pol.addPoint(p22.x, p22.y); // Patch!!!!
            goals[1] = new CBoxEnd(301, pol.xpoints, pol.ypoints, 1);
            // Goal 3
            pol = new Polygon();
            pol.addPoint(p25.x, p25.y);
            pol.addPoint(p22.x, p22.y);
            pol.addPoint(p19.x, p19.y);
            pol.addPoint(p19.x, p19.y); // Patch !!!
            goals[2] = new CBoxEnd(302, pol.xpoints, pol.ypoints, 2);
            // Goal 4
            pol = new Polygon();
            pol.addPoint(p16.x, p16.y);
            pol.addPoint(p25.x, p25.y);
            pol.addPoint(p19.x, p19.y);
            pol.addPoint(p19.x, p19.y); // Patch !!!
            goals[3] = new CBoxEnd(303, pol.xpoints, pol.ypoints, 3);

        } catch (Exception ex) {
            Logger.getLogger(CBoardConstructor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return goals;
    }

    public static CBoxPathGoal[] generatePathGoal(int player) throws Exception {
        CBoxPathGoal[] path = new CBoxPathGoal[7];

        int xpos[] = new int[4];
        int ypos[] = new int[4];
        int basex = 0;
        int basey = 0;
        int dimx = 0;
        int dimy = 0;
        int vx = 0;
        int vy = 0;
        switch (player) {
            case 0:
                // init the indicators
                basex = 333;
                basey = 224;
                dimx = 68;
                dimy = 28;
                vx = 0;
                vy = -1;
                break;
            case 1:
                // init the indicators
                basex = 224;
                basey = 333;
                dimx = 28;
                dimy = 68;
                vx = -1;
                vy = 0;
                break;
            case 2:
                // init the indicators
                basex = 333;
                basey = 404;
                dimx = 68;
                dimy = 28;
                vx = 0;
                vy = +1;
                break;
            case 3:
                // init the indicators
                basex = 404;
                basey = 333;
                dimx = 28;
                dimy = 68;
                vx = +1;
                vy = 0;
                break;
        }
        // init the positions
        xpos[0] = basex - dimx;
        xpos[1] = basex;
        xpos[2] = basex;
        xpos[3] = basex - dimx;
        ypos[0] = basey - dimy;
        ypos[1] = basey - dimy;
        ypos[2] = basey;
        ypos[3] = basey;

        for (int i = 0; i < 7; i++) {
            path[6 - i] = new CBoxPathGoal(100 + (player * 10) + i, xpos, ypos, null, player, 7 - i);
            xpos[0] += dimx * vx;
            xpos[1] += dimx * vx;
            xpos[2] += dimx * vx;
            xpos[3] += dimx * vx;
            ypos[0] += dimy * vy;
            ypos[1] += dimy * vy;
            ypos[2] += dimy * vy;
            ypos[3] += dimy * vy;
        }

        return path;
    }

    public static CBox[] generatePathSegment(int player, int basex, int basey, int dimx, int dimy, int vx, int vy) {
        CBox[] path = new CBox[7];

        int xpos[] = new int[4];
        int ypos[] = new int[4];

        // init the positions
        xpos[0] = basex - dimx;
        xpos[1] = basex;
        xpos[2] = basex;
        xpos[3] = basex - dimx;
        ypos[0] = basey - dimy;
        ypos[1] = basey - dimy;
        ypos[2] = basey;
        ypos[3] = basey;

        for (int i = 0; i < path.length; i++) {
            try {
                if (i == 2) {
                    path[i] = new CBoxSafe(i, xpos, ypos, null, false, player, null);
                } else {
                    path[i] = new CBoxNormal(70 - i, xpos, ypos, null);

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            xpos[0] += dimx * vx;
            xpos[1] += dimx * vx;
            xpos[2] += dimx * vx;
            xpos[3] += dimx * vx;
            ypos[0] += dimy * vy;
            ypos[1] += dimy * vy;
            ypos[2] += dimy * vy;
            ypos[3] += dimy * vy;
        }

        return path;
    }

    public static CBox[] generateMap() throws Exception {
        CBox map[] = new CBox[68];

        CBox segment[] = new CBox[7];

        // Generando Casillas Normales
        int auxx = 0, auxy = 0;

        // 1 - 7
        segment = generatePathSegment(0, 264, 196, 68, 28, 0, -1);
        mergeArraysInv(segment, map, 0);
        map[7] = new CBoxNormal(7, generate4ptsCornerX(264, 68), generate4ptsCornerY(224, 28), null);
        // 8 - 15
        mirror(map, 0, 8, 8);
        // 16
        auxx = map[15].getArea().xpoints[2];
        auxy = map[15].getArea().ypoints[2] + 68;
        map[16] = new CBoxSafe(16, generate4ptsMediumX(auxx, 28), generate4ptsMediumY(auxy, 68), null, true, 1, null);

        // 17 - 32
        mirrorY(map, 0, 17, 16);

        //33
        auxx = map[32].getArea().xpoints[2] + 68;
        auxy = map[32].getArea().ypoints[2] + 28;
        map[33] = new CBoxSafe(33, generate4ptsMediumX(auxx, 68), generate4ptsMediumY(auxy, 28), null, true, 2, null);
        //33 - 67
        mirrorX(map, 0, 34, 33);
        //33
        auxx = map[49].getArea().xpoints[2] + 28;
        auxy = map[49].getArea().ypoints[2];

        map[50] = new CBoxSafe(50, generate4ptsMediumX(auxx, 28), generate4ptsMediumY(auxy, 68), null, true, 0, null);

        auxx = map[66].getArea().xpoints[2] - 1;
        auxy = map[66].getArea().ypoints[2];

        map[67] = new CBoxSafe(67, generate4ptsMediumX(auxx, 68), generate4ptsMediumY(auxy, 28), null, true, 0, null);
        return map;
    }

    // Auxiliar functions to generateMap()
    private static void mergeArraysInv(CBox[] origin, CBox[] destiny, int idx) {
        for (int i = 0; i < origin.length; i++) {
            destiny[idx + i] = origin[6 - i];       // <--- Change 6 * n
        }
    }

    private static void mergeArrays(CBox[] origin, CBox[] destiny, int idx) {
        for (int i = 0; i < origin.length; i++) {
            destiny[idx + i] = origin[i];       // <--- Change 6 * n
        }
    }

    private static int[] generate4ptsCornerX(int basex, int dimx) {
        int xpos[] = new int[4];
        if (dimx == 68) {
            xpos[0] = basex - dimx;
            xpos[1] = basex;
            xpos[2] = basex;
            xpos[3] = basex - 40;
        } else {
            xpos[0] = basex - dimx;
            xpos[1] = basex;
            xpos[2] = basex;
            xpos[3] = basex - dimx;
        }
        return xpos;

    }

    private static int[] generate4ptsCornerY(int basey, int dimy) {
        int ypos[] = new int[4];
        if (dimy == 68) {
            ypos[0] = basey - dimy;
            ypos[1] = basey - 40;
            ypos[2] = basey;
            ypos[3] = basey;
        } else {
            ypos[0] = basey - dimy;
            ypos[1] = basey - dimy;
            ypos[2] = basey;
            ypos[3] = basey;
        }
        return ypos;
    }

    private static int[] generate4ptsMediumX(int basex, int dimx) {
        int xpos[] = new int[4];
        xpos[0] = basex - dimx;
        xpos[1] = basex;
        xpos[2] = basex;
        xpos[3] = basex - dimx;
        return xpos;
    }

    private static int[] generate4ptsMediumY(int basey, int dimy) {
        int ypos[] = new int[4];
        ypos[0] = basey - dimy;
        ypos[1] = basey - dimy;
        ypos[2] = basey;
        ypos[3] = basey;
        return ypos;
    }

    private static void mirror(CBox map[], int origin, int destiny, int n) throws CloneNotSupportedException {

        CBox tmp;
        for (int i = 0; i < n; i++) {
            tmp = (CBox) map[origin + i].duplicate();
            for (int j = 0; j < map[origin + i].getArea().npoints; j++) {
                int t = (int) tmp.getArea().xpoints[j];
                tmp.getArea().xpoints[j] = tmp.getArea().ypoints[j];
                tmp.getArea().ypoints[j] = t;
            }
            tmp.generatePositions();
            map[destiny + (n - 1) - i] = tmp;
        }

    }

    private static void mirrorX(CBox map[], int origin, int destiny, int n) throws CloneNotSupportedException {
        CBox tmp;
        for (int i = 0; i < n; i++) {
            tmp = (CBox) map[origin + i].duplicate();
            for (int j = 0; j < map[origin + i].getArea().npoints; j++) {
                tmp.getArea().xpoints[j] = 599 - tmp.getArea().xpoints[j];
            }
            tmp.generatePositions();
            map[destiny + (n - 1) - i] = tmp;
        }
    }

    private static void mirrorY(CBox map[], int origin, int destiny, int n) throws CloneNotSupportedException {
        CBox tmp;
        for (int i = 0; i < n; i++) {
            tmp = (CBox) map[origin + i].duplicate();
            for (int j = 0; j < map[origin + i].getArea().npoints; j++) {
                tmp.getArea().ypoints[j] = 599 - tmp.getArea().ypoints[j];
            }
            tmp.generatePositions();
            map[destiny + (n - 1) - i] = tmp;
        }
    }

    private static void mirrorXY(CBox map[], int origin, int destiny, int n) throws CloneNotSupportedException {
        CBox tmp;
        for (int i = 0; i < n; i++) {
            tmp = (CBox) map[origin + i].duplicate();
            for (int j = 0; j < map[origin + i].getArea().npoints; j++) {
                tmp.getArea().ypoints[j] = 599 - tmp.getArea().ypoints[j];
                tmp.getArea().xpoints[j] = 599 - tmp.getArea().xpoints[j];
            }
            tmp.generatePositions();
            map[destiny + (n - 1) - i] = tmp;
        }
    }
}
