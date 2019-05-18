/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.CBoardConstructor;
import Clases.CGameMaster;
import Clases.CTryMove;
import Clases.Boxes.CBox;
import Clases.Boxes.CBoxSafe;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author alexis
 */
public class CBoard extends JPanel {

    Image background;

    public CBox[] homes;
    public CBox[] goals;
    public ArrayList<CBox[]> pathEnd;
    public CBox[] map;
    public CGameMaster gameMaster;

    private void loadImage() throws IOException {
        background = (Image) ImageIO.read(getClass().getResource("/Images/Backs/FND.png"));
        //fondo = (Image) ImageIO.read(new File("./Images/Backs/FND.png"));
    }

    public CBoard(CGameMaster gameMaster) {
        super();
        this.gameMaster = gameMaster;

        try {
            loadImage();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        // Init boxes
        initialize();

    }

    public void initialize() {
        try {
            // build homes
            homes = CBoardConstructor.generateHomes(4);

            // build goals
            goals = CBoardConstructor.generateGoals();

            // build simple boxes
            map = CBoardConstructor.generateMap();

            // Generate goal paths
            pathEnd = new ArrayList<CBox[]>();
            CBox[] path;
            for (int i = 0; i < 4; i++) {
                path = CBoardConstructor.generatePathGoal(i);

                // connect goals with paths
                for (int j = 1; j < path.length; j++) {
                    path[j - 1].setNext(path[j]);

                }
                path[6].setNext(goals[i]);

                pathEnd.add(path);
            }

            // Connect inputs with simple boxes
            for (int i = 0; i < 4; i++) {
                ((CBoxSafe) map[((4 + i) * 17 - 1) % 68]).setInput(true);
                ((CBoxSafe) map[((4 + i) * 17 - 1) % 68]).setPlayer(i);
                ((CBoxSafe) map[((4 + i) * 17 - 1) % 68]).setHomePath(pathEnd.get(i)[0]);
            }

        } catch (Exception ex) {
            //ex.notify();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        // Homes with outputs
        homes[0].setNext(map[4]);
        homes[1].setNext(map[21]);
        homes[2].setNext(map[38]);
        homes[3].setNext(map[55]);

        // Connect simple boxes
        for (int i = 0; i < 67; i++) {
            map[i].id = i;
            map[i].setNext(map[i + 1]);

        }
        map[67].id = 67;
        map[67].setNext(map[0]);
    }

    public void clean() {
        for (CBox map1 : map) {
            map1.getTokens().clear();
        }
        // clean homes and goals
        for (int i = 0; i < 4; i++) {
            homes[i].getTokens().clear();
            goals[i].getTokens().clear();
        }

        // clean inputs
        for (int i = 0; i < 7; i++) {
            pathEnd.get(0)[i].getTokens().clear();
            pathEnd.get(1)[i].getTokens().clear();
            pathEnd.get(2)[i].getTokens().clear();
            pathEnd.get(3)[i].getTokens().clear();
        }

    }

    @Override
    public void repaint() {
        paint(this.getGraphics());
    }

    @Override
    public void paint(Graphics g) {

        BufferedImage img = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = (Graphics2D) img.getGraphics();

        if (background != null) {
            g2.drawImage(background, 0, 0, this);
            if (CGameMaster.status == 0) {
                g.drawImage((Image) img, 0, 0, this);
            }
        }
        if (CGameMaster.status > 0) {

            if (gameMaster.getStatus() != 0) {
                if (gameMaster.nPlayers == 1) {
                    g2.drawImage(gameMaster.getPlayers()[0].getBanner(), 0, 0, this);
                    g2.drawImage((Image)this.rotateImage(gameMaster.getPlayers()[0].getPathBuff(), 180.0), 150, 0, this);
                } else if (gameMaster.nPlayers == 2) {
                    g2.drawImage(gameMaster.getPlayers()[0].getBanner(), 0, 0, this);
                    g2.drawImage((Image)this.rotateImage(gameMaster.getPlayers()[0].getPathBuff(), 180.0), 150, 0, this);
                    
                    g2.drawImage(gameMaster.getPlayers()[1].getBanner(), 0, 404, this);
                    g2.drawImage((Image)this.rotateImage(gameMaster.getPlayers()[1].getPathBuff(), 90.0), 0, 150, this);
                } else if (gameMaster.nPlayers == 3) {
                    g2.drawImage(gameMaster.getPlayers()[0].getBanner(), 0, 0, this);
                    g2.drawImage((Image)this.rotateImage(gameMaster.getPlayers()[0].getPathBuff(), 180.0), 150, 0, this);
                    
                    g2.drawImage(gameMaster.getPlayers()[1].getBanner(), 0, 404, this);
                    g2.drawImage((Image)this.rotateImage(gameMaster.getPlayers()[1].getPathBuff(), 90.0), 0, 150, this);
                    
                    g2.drawImage(gameMaster.getPlayers()[2].getBanner(), 404, 404, this);
                    g2.drawImage((Image)this.rotateImage(gameMaster.getPlayers()[2].getPathBuff(), 0.0), 150, 300, this);
                } else if (gameMaster.nPlayers == 4) {
                    g2.drawImage(gameMaster.getPlayers()[0].getBanner(), 0, 0, this);
                    g2.drawImage((Image)this.rotateImage(gameMaster.getPlayers()[0].getPathBuff(), 180.0), 150, 0, this);
                    
                    g2.drawImage(gameMaster.getPlayers()[1].getBanner(), 0, 404, this);
                    g2.drawImage((Image)this.rotateImage(gameMaster.getPlayers()[1].getPathBuff(), 90.0), 0, 150, this);
                    
                    g2.drawImage(gameMaster.getPlayers()[2].getBanner(), 404, 404, this);
                    g2.drawImage((Image)this.rotateImage(gameMaster.getPlayers()[2].getPathBuff(), 0.0), 150, 300, this);
                    
                    g2.drawImage(gameMaster.getPlayers()[3].getBanner(), 404, 0, this);
                    g2.drawImage((Image)this.rotateImage(gameMaster.getPlayers()[3].getPathBuff(), -90.0), 300, 150, this);
                }
            }

            // Paint boxes
            CTryMove[] list = gameMaster.getMoves();
            if (list != null) {
                for (CTryMove mov : list) {
                    if (mov != null && mov.isValid()) {
                        mov.getDestiny().draw(g2);
                    }
                }
            }

            // Paint tokens
            CToken f = null;
            for (int i = 0; i < gameMaster.nPlayers; i++) {
                CToken[] tokens = gameMaster.getPlayers()[i].getTokens();
                for (CToken token : tokens) {
                    f = token;
                    g2.drawImage(f.image, f.getLocation().x, f.getLocation().y, null);
                }
            }

            gameMaster.getDice().getIcon().paintIcon(this, g2, 300 - 25, 300 - 25);

            g.drawImage((Image) img, 0, 0, this);

            //gameMaster.getDice().repaint();
        }

    }

    public Image rotateImage(BufferedImage img, double rad) {
        double rotationRequired = Math.toRadians(rad);
        double locationX = img.getWidth() / 2;
        double locationY = img.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return (Image) op.filter(img, null);
    }

    public void quitMarks() {
        CTryMove[] list = gameMaster.getMoves();
        if (list != null) {
            for (CTryMove mov : list) {
                if (mov.isValid()) {
                    mov.getDestiny().property = 0;
                }
            }
        }
    }

}
