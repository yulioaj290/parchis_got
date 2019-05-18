/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import GUI.CToken;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Yulio
 */
public class CPlayer implements IAPlayer {

    //Atributos
    public int id;
    private String name;
    public int house;
    public Image banner;
    public BufferedImage bannerBuff;
    public BufferedImage pathBuff;
    public CToken[] tokens = null;
    private boolean AI;

    //Constructor
    public CPlayer(int id, String name, int house, boolean AI) {
        this.id = id;
        this.house = house;
        this.name = name;
        this.AI = AI;

        try {
            //banner = (Image) ImageIO.read(new File("./Images/Houses/"+ house +".png"));
            banner = (Image) ImageIO.read(getClass().getResource("/Images/Houses/" + house + ".jpg"));
            bannerBuff = ImageIO.read(getClass().getResource("/Images/Houses/" + house + ".jpg"));
            pathBuff = ImageIO.read(getClass().getResource("/Images/Paths/" + house + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(CPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isAI() {
        return AI;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Image getBanner() {
        return banner;
    }

    public BufferedImage getBannerBuff() {
        return bannerBuff;
    }

    public void setBannerBuff(BufferedImage bannerBuff) {
        this.bannerBuff = bannerBuff;
    }

    public BufferedImage getPathBuff() {
        return pathBuff;
    }

    public void setPathBuff(BufferedImage pathBuff) {
        this.pathBuff = pathBuff;
    }
    
    public void setTokens(CToken[] tokens) {
        this.tokens = tokens;
    }

    public CToken[] getTokens() {
        return tokens;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public void play(CTryMove[] moves) {
        throw new UnsupportedOperationException("This is a human.");
    }

}
