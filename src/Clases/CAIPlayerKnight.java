/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Clases.Boxes.CBox;
import Clases.Boxes.CBoxHome;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dairelys Garcia
 */
public class CAIPlayerKnight extends CPlayer {

    public CAIPlayerKnight(int id, String name, int house) {
        super(id, name, house, true);
    }

    public void play(CTryMove[] moves) {
        CTryMove mov = null;
        for (int i = moves.length - 1; i >= 0; i--) {
            if (moves[i] != null && moves[i].isValid()) {
                if (mov == null) {
                    mov = moves[i];
                } else {
                    if (moves[i].destiny.property == CBox.EAT) {
                        mov = moves[i];
                    } else if (mov.destiny.property != CBox.EAT && moves[i].origin instanceof CBoxHome) {
                        mov = moves[i];
                    }
                }
            }

        }
        try {
            mov.doMove();
        } catch (Exception ex) {
            Logger.getLogger(CAIPlayerSquire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
