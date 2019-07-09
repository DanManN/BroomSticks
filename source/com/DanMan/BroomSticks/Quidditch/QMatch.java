package com.DanMan.BroomSticks.Quidditch;

import com.DanMan.BroomSticks.main.BroomSticks;
import java.util.ArrayList;
import org.bukkit.entity.Player;

public class QMatch {
	private BroomSticks plugin;
	private ArrayList<Player> gryffindor = new ArrayList();
	private ArrayList<Player> slytherin = new ArrayList();
	private QArena arena;

	public QMatch(QArena arena, BroomSticks plug) { this.plugin = plug; }

	public void preGame() {}

	public void startGame() {}

	public void endGame() {}

	public void addGryffindor(Player player) { this.gryffindor.add(player); }

	public void addSlytherin(Player player) { this.slytherin.add(player); }
}
