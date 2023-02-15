package me.improper.ogredupealias.commands.tpa;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class TpRequest {

    public enum RequestType {
        TPA,
        TPAHERE
    }

    private Player requester, requested;
    private long started;
    private RequestType type;

    public TpRequest(RequestType type, Player requester, Player requested) {
        this.type = type;
        this.requested = requested;
        this.requester = requester;
        this.started = System.currentTimeMillis();
    }

    public long getStarted() {
        return started;
    }

    public Player getRequested() {
        return requested;
    }

    public Player getRequester() {
        return requester;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public void setRequester(Player requester) {
        this.requester = requester;
    }

    public void setRequested(Player requested) {
        this.requested = requested;
    }

    public String toString() {
        return "TpRequest:{type=" + type.name() +
                "requester=" + requester.getName() +
                "requested=" + requested.getName() +
                "started=" + started +
                "}";
    }

    public List<Player> getParticipants() {
        return Arrays.asList(requested,requester);
    }
}
