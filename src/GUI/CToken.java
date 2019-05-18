/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.Boxes.CBox;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Yulio
 */
public class CToken {
    //Attributes

    public static int id;
    private CBox box;           // current box
    private int player;         // Owner of this token
    private int house;
    public Point location;
    public Color color;
    public Image image;

    public Image loadImage(int house) {
        image = null;
        String imgPath = "/Images/Tokens/" + house + ".png";
        try {
            //image = (Image) ImageIO.read(new File(imgPath));
            image = (Image) ImageIO.read(getClass().getResource(imgPath));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return image;
    }

    private Color getColor(int player) {
        switch (player) {
            case 0:
                return new Color(255, 255, 0, 150);
            case 1:
                return new Color(0, 0, 255, 150);
            case 2:
                return new Color(255, 0, 0, 150);
            case 3:
                return new Color(0, 255, 0, 150);
        }
        return null;
    }

    public CToken(int id, CBox box, int player, int house) {
        this.location = null;
        this.id = id;
        this.box = box;
        loadImage(house);
        this.player = player;
        this.house = house;

        this.color = getColor(player);
        try {
            box.addToken(this);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    // Query functions
    public int getPlayer() {
        return player;
    }

    public int getHouse() {
        return house;
    }
    
    public CBox getBox() {
        return box;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    // Assign functions
    public void setBox(CBox casilla) {
        this.box = casilla;
    }

    boolean fueClickeada(Point point) {
        double r = Math.sqrt(Math.pow((this.location.x + 12) - point.x, 2) + Math.pow((this.location.y + 12) - point.y, 2));
        return r <= 12;
    }

}
