package me.improper.ogredupealias.commands.tpa;

import me.improper.ogredupealias.OgredupeAlias;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TpAsk {

    static List<TpRequest> activeRequests = new ArrayList<>();

    public static boolean hasRequest(Player player) {
        for (TpRequest request : activeRequests)
            if (request.getParticipants().contains(player)) return true;
        return false;
    }

    public static TpRequest getRequest(Player player) {
        for (TpRequest request : activeRequests)
            if (request.getParticipants().contains(player)) return request;
        return null;
    }

    public static void sendRequest(TpRequest request) {
        Player requester = request.getRequester();
        Player requested = request.getRequested();
        TpRequest.RequestType type = request.getType();

        requester.sendMessage("\n \n " +
                OgredupeAlias.STARTER +
                ChatColor.GRAY + "[" +
                ChatColor.GOLD + "TPA" +
                ChatColor.GRAY + "] " +
                ChatColor.WHITE + "You " +
                ChatColor.GRAY + "have sent a " +
                ChatColor.YELLOW + type.name() + " " +
                ChatColor.GRAY + "request to " +
                ChatColor.WHITE + requested.getName() + "!\n \n ");
        requested.sendMessage("\n \n " +
                OgredupeAlias.STARTER +
                ChatColor.GRAY + "[" +
                ChatColor.GOLD + "TPA" +
                ChatColor.GRAY + "] " +
                ChatColor.WHITE + requester.getName() + " " +
                ChatColor.GRAY + "has sent a " +
                ChatColor.YELLOW + type.name() + " " +
                ChatColor.GRAY + "request to " +
                ChatColor.WHITE + "you!\n \n ");

        activeRequests.add(request);
    }


    public static void acceptRequest(TpRequest request) {
        Player requester = request.getRequester();
        Player requested = request.getRequested();
        TpRequest.RequestType type = request.getType();

        requester.sendMessage("\n \n " +
                OgredupeAlias.STARTER +
                ChatColor.GRAY + "[" +
                ChatColor.GOLD + "TPA" +
                ChatColor.GRAY + "] " +
                ChatColor.WHITE + requested.getName() + " " +
                ChatColor.GRAY + "has accepted " +
                ChatColor.WHITE + "your " +
                ChatColor.YELLOW + type.name() + " " +
                ChatColor.GRAY + "request!\n \n ");
        requested.sendMessage("\n \n " +
                OgredupeAlias.STARTER +
                ChatColor.GRAY + "[" +
                ChatColor.GOLD + "TPA" +
                ChatColor.GRAY + "] " +
                ChatColor.WHITE + "You " +
                ChatColor.GRAY + "have accepted " +
                ChatColor.WHITE + requester.getName() +
                ChatColor.GRAY + "'s " +
                ChatColor.YELLOW + type.name() + " " +
                ChatColor.GRAY + "request!\n \n ");

        switch (request.getType()) {
            case TPA -> requester.teleport(requested.getLocation().getBlock().getLocation());
            case TPAHERE -> requested.teleport(requester.getLocation().getBlock().getLocation());
        }
        activeRequests.remove(request);
    }

    public static void denyRequest(TpRequest request) {
        Player requester = request.getRequester();
        Player requested = request.getRequested();
        TpRequest.RequestType type = request.getType();

        requester.sendMessage("\n \n " +
                OgredupeAlias.STARTER +
                ChatColor.GRAY + "[" +
                ChatColor.GOLD + "TPA" +
                ChatColor.GRAY + "] " +
                ChatColor.WHITE + requested.getName() + " " +
                ChatColor.GRAY + "has denied " +
                ChatColor.WHITE + "your " +
                ChatColor.YELLOW + type.name() + " " +
                ChatColor.GRAY + "request!\n \n ");
        requested.sendMessage("\n \n " +
                OgredupeAlias.STARTER +
                ChatColor.GRAY + "[" +
                ChatColor.GOLD + "TPA" +
                ChatColor.GRAY + "] " +
                ChatColor.WHITE + "You " +
                ChatColor.GRAY + "have denied " +
                ChatColor.WHITE + requester.getName() +
                ChatColor.GRAY + "'s " +
                ChatColor.YELLOW + type.name() + " " +
                ChatColor.GRAY + "request!\n \n ");

        activeRequests.remove(request);
    }

    public static void cancelRequest(TpRequest request) {
        Player requester = request.getRequester();
        Player requested = request.getRequested();

        requester.sendMessage("\n \n " +
                OgredupeAlias.STARTER +
                ChatColor.GRAY + "[" +
                ChatColor.GOLD + "TPA" +
                ChatColor.GRAY + "] " +
                ChatColor.WHITE + "Your " +
                ChatColor.GRAY + "teleportation request to " +
                ChatColor.WHITE + requested.getName() + " " +
                ChatColor.GRAY + "has been canceled!\n \n ");
        requested.sendMessage("\n \n " +
                OgredupeAlias.STARTER +
                ChatColor.GRAY + "[" +
                ChatColor.GOLD + "TPA" +
                ChatColor.GRAY + "] " +
                ChatColor.WHITE + "Your " +
                ChatColor.GRAY + "teleportation request from " +
                ChatColor.WHITE + requester.getName() + " " +
                ChatColor.GRAY + "has been canceled!\n \n ");

        activeRequests.remove(request);
    }

    public static void expireRequest(TpRequest request) {
        Player requester = request.getRequester();
        Player requested = request.getRequested();

        requester.sendMessage("\n \n " +
                OgredupeAlias.STARTER +
                ChatColor.GRAY + "[" +
                ChatColor.GOLD + "TPA" +
                ChatColor.GRAY + "] " +
                ChatColor.WHITE + "Your " +
                ChatColor.GRAY + "teleportation request to " +
                ChatColor.WHITE + requested.getName() + " " +
                ChatColor.GRAY + "has expired!\n \n ");
        requested.sendMessage("\n \n " +
                OgredupeAlias.STARTER +
                ChatColor.GRAY + "[" +
                ChatColor.GOLD + "TPA" +
                ChatColor.GRAY + "] " +
                ChatColor.WHITE + "Your " +
                ChatColor.GRAY + "teleportation request from " +
                ChatColor.WHITE + requester.getName() + " " +
                ChatColor.GRAY + "has expired!\n \n ");

        activeRequests.remove(request);
    }
}
