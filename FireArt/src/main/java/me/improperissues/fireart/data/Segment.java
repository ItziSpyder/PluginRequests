package me.improperissues.fireart.data;

import org.bukkit.Material;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Segment implements Serializable {

    private String name;
    private List<SelectedBlock> blocks;
    private List<String> blockLog, locationIdLog;

    public Segment(String name) {
        this.name = name;
        this.blocks = new ArrayList<>();
        this.blockLog = new ArrayList<>();
        this.locationIdLog = new ArrayList<>();
    }

    public void revert() {
        for (SelectedBlock block : blocks) block.revert();
    }

    public void fillWith(Material material) {
        for (SelectedBlock block : blocks) block.toBlock().setType(material);
    }

    public String getName() {
        return name;
    }

    public List<SelectedBlock> getBlocks() {
        return blocks;
    }

    public List<String> getBlockLog() {
        return blockLog;
    }

    public List<String> getLocationIdLog() {
        return locationIdLog;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBlocks(List<SelectedBlock> blocks) {
        this.blocks = blocks;
    }

    public void setBlockLog(List<String> blockLog) {
        this.blockLog = blockLog;
    }

    public void setLocationIdLog(List<String> locationIdLog) {
        this.locationIdLog = locationIdLog;
    }
}
