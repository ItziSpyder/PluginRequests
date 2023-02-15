package me.improperissues.fireart.data;

import me.improperissues.fireart.FireArt;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaintAction {

    private static HashMap<String,List<PaintAction>> ACTIONS = new HashMap<>();

    public static void restoreLastAction(Player player) {
        List<PaintAction> actions = ACTIONS.get(player.getName());
        if (actions == null || actions.size() == 0) return;
        PaintAction action = actions.get(actions.size() - 1);
        action.undo();
        actions.remove(actions.size() - 1);
        ACTIONS.put(player.getName(),actions);
        player.sendMessage(FireArt.STARTER + ChatColor.GREEN + "Action restored! " + ChatColor.GRAY + "(" + ChatColor.YELLOW + actions.size() + ChatColor.GRAY + "/100)");
    }

    public static void addPlayerAction(Player player, PaintAction action) {
        List<PaintAction> actions = ACTIONS.get(player.getName());
        if (actions == null) actions = new ArrayList<>();
        actions.add(action);
        if (actions.size() > 100) actions.remove(0);
        ACTIONS.put(player.getName(),actions);
    }

    private List<PaintedBlock> changed;

    public PaintAction() {
        this.changed = new ArrayList<>();
    }

    public void undo() {
        for (PaintedBlock block : changed) {
            Block restoring = block.toBlock();
            restoring.setType(block.getOgType());
            restoring.setBlockData(block.getOgData());
        }
    }

    public void addChanged(PaintedBlock block) {
        this.changed.add(block);
    }

    public void removeChanged(PaintedBlock block) {
        this.changed.remove(block);
    }

    public List<PaintedBlock> getChanged() {
        return changed;
    }

    public void setChanged(List<PaintedBlock> changed) {
        this.changed = changed;
    }
}
