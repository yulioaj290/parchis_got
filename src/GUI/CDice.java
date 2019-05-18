/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.CGameMaster;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author yulio
 */
public class CDice extends JLabel {

    private int value = 1;
    CGameMaster gameMaster;
    private boolean active = false;
    ImageIcon[] animation = null;

    public MouseListener diceActions = new MouseListener() {

        public void mouseClicked(MouseEvent me) {

        }

        public void mousePressed(MouseEvent me) {
            if (active) {
                active = false;
                animate();
                updateValue();
                gameMaster.getGameFlow().release();
            }
        }

        public void mouseReleased(MouseEvent me) {

        }

        public void mouseEntered(MouseEvent me) {

        }

        public void mouseExited(MouseEvent me) {

        }
    };

    public CDice(final CGameMaster gameMaster) {
        this.gameMaster = gameMaster;
        this.gameMaster.setDice(this);
        updateValue();

        animation = new ImageIcon[9];
        String imgPath = null;
        for (int i = 0; i < animation.length; i++) {
            try {
                imgPath = "/Images/Dices/animated/" + Integer.toString(i + 1) + ".png";
                animation[i] = new ImageIcon((Image) ImageIO.read(getClass().getResource(imgPath)));
            } catch (IOException ex) {
                Logger.getLogger(CDice.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        this.addMouseListener(diceActions);
    }

    public int getValue() {
        return value;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void updateValue() {
        value = Math.abs(new Random().nextInt()) % 6 + 1;
        this.setIcon(new ImageIcon(loadImage()));
    }

    static int timerCounter = 0;

    public void animate() {

        timerCounter = 0;
        while (timerCounter < 9) {
            try {
                setIcon(animation[timerCounter]);
                gameMaster.getBoard().paint(gameMaster.getBoard().getGraphics());
                timerCounter++;
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(CDice.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Image loadImage() {
        Image image = null;
        String imgPath = "/Images/Dices/" + Integer.toString(value) + ".png";
        try {
            //image = (Image) ImageIO.read(new File(imgPath));
            image = (Image) ImageIO.read(getClass().getResource(imgPath));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return image;
    }

}
