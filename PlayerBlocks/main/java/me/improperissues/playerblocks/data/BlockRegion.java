package me.improperissues.playerblocks.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlockRegion implements Serializable {

    private String name;
    private BlockLocation corner1, corner2;
    private HashMap<String,List<PlayerBlock>> playerBlocks;
    private List<String> blockLog;
    private boolean logging;

    public BlockRegion(String name, Location corner1, Location corner2) {
        this.name = name;
        this.corner1 = new BlockLocation(corner1);
        this.corner2 = new BlockLocation(corner2);
        this.playerBlocks = new HashMap<>();
        this.blockLog = new ArrayList<>();
        this.logging = true;
    }

    public void addPlayerBlock(Player player, PlayerBlock block) {
        List<PlayerBlock> blocks = playerBlocks.get(player.getName());
        if (blocks == null) blocks = new ArrayList<>();
        blocks.add(block);
        playerBlocks.put(player.getName(),blocks);
        blockLog.add(block.toString());
    }

    public void removePlayerBlock(Player player, PlayerBlock block) {
        List<PlayerBlock> blocks = playerBlocks.get(player.getName());
        if (blocks == null) blocks = new ArrayList<>();
        blocks.remove(block);
        playerBlocks.put(player.getName(),blocks);
        blockLog.remove(block.toString());
    }

    public void clearBlocks() {
        for (String key : playerBlocks.keySet()) {
            for (PlayerBlock pb : playerBlocks.get(key)) pb.clear();
            playerBlocks.put(key,new ArrayList<>());
        }
        blockLog = new ArrayList<>();
    }

    public boolean isWithin(Location location) {
        int[] loc = {
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ()
        };
        int[] min = {
                Math.min(this.corner1.getX(),this.corner2.getX()),
                Math.min(this.corner1.getY(),this.corner2.getY()),
                Math.min(this.corner1.getZ(),this.corner2.getZ())
        };
        int[] max = {
                Math.max(this.corner1.getX(),this.corner2.getX()),
                Math.max(this.corner1.getY(),this.corner2.getY()),
                Math.max(this.corner1.getZ(),this.corner2.getZ())
        };
        return (loc[0] >= min[0] && loc[0] <= max[0])
                && (loc[1] >= min[1] && loc[1] <= max[1])
                && (loc[2] >= min[2] && loc[2] <= max[2]);
    }

    public File getFile() {
        File file = new File("plugins/PlayerBlocks/blockregions/" + name + ".blocks");
        try {
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            if (!file.exists()) file.createNewFile();
        } catch (Exception exception) {
            Bukkit.getLogger().warning("Failed to create new file " + file.getPath() + "!");
        }
        return file;
    }

    public String getName() {
        return name;
    }

    public BlockLocation getCorner1() {
        return corner1;
    }

    public BlockLocation getCorner2() {
        return corner2;
    }

    public HashMap<String, List<PlayerBlock>> getPlayerBlocks() {
        return playerBlocks;
    }

    public List<String> getBlockLog() {
        return blockLog;
    }

    public boolean isLogging() {
        return logging;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCorner2(BlockLocation corner2) {
        this.corner2 = corner2;
    }

    public void setCorner1(BlockLocation corner1) {
        this.corner1 = corner1;
    }

    public void setPlayerBlocks(HashMap<String, List<PlayerBlock>> playerBlocks) {
        this.playerBlocks = playerBlocks;
    }

    public void setBlockLog(List<String> blockLog) {
        this.blockLog = blockLog;
    }

    public void setLogging(boolean logging) {
        this.logging = logging;
    }
}
