/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.BroomSticks.Quidditch;

import com.DanMan.BroomSticks.main.BroomSticks;
import java.util.ArrayList;
import org.bukkit.entity.Player;

/**
 *
 * @author DAY
 */
public class QMatch {
    private BroomSticks plugin;
    private ArrayList<Player> gryffindor = new ArrayList();
    private ArrayList<Player> slytherin = new ArrayList();
    private QArena arena;

    public QMatch(QArena arena, BroomSticks plug) {
        plugin = plug;        
    }
    
    public void preGame() {
    }
    
    public void addGryffindor(Player player) {
        gryffindor.add(player);
    }
    public void addSlytherin(Player player) {
        slytherin.add(player);
    }
    
}
