package bluebox.levilamina.engine.events;

import bluebox.levilamina.engine.worlds.Player;

import java.util.LinkedList;

public class PlayerJoinEvent {
    private PlayerJoinEvent(){}
    private static final LinkedList<PlayerJoinListener> listeners = new LinkedList<>();
    public static void register(PlayerJoinListener listener){
        listeners.add(listener);
    }
    public Player player;
    private static void execute(Player player){
        PlayerJoinEvent event = new PlayerJoinEvent();
        event.player = player;
        for(PlayerJoinListener listener : listeners){
            listener.onPlayerJoin(event);
        }
    }
}
