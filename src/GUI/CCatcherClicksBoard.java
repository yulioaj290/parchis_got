/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.CGameMaster;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author yulio
 */
public class CCatcherClicksBoard extends MouseAdapter {

    CGameMaster gameMaster;

    public CCatcherClicksBoard(CGameMaster gameMaster) {
        super();
        this.gameMaster = gameMaster;
    }

    public void mouseClicked(MouseEvent me) {
        boolean continuar = false;

        CToken[] fichas = gameMaster.getPlayers()[gameMaster.getInTurn()].getTokens();
        for (int i = 0; i < fichas.length; i++) {
            if (fichas[i].fueClickeada(me.getPoint())) {
                try {
                    gameMaster.getMoves()[i].doMove();
                    gameMaster.getGameFlow().release();
                    break;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }

    }

}
