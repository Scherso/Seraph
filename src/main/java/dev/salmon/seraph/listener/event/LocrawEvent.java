package dev.salmon.seraph.listener.event;

import dev.salmon.seraph.util.locraw.LocrawInfo;
import net.minecraftforge.fml.common.eventhandler.Event;

public class LocrawEvent extends Event {
    private LocrawInfo locraw;

    public LocrawEvent(LocrawInfo locraw) {
        this.locraw = locraw;
    }

    public LocrawInfo getLocraw() { return this.locraw; }

    public static class JoinGame extends LocrawEvent {
        public JoinGame(LocrawInfo locraw) {
            super(locraw);
        }
    }

    public static class JoinLobby extends LocrawEvent {
        public JoinLobby(LocrawInfo locraw) {
            super(locraw);
        }
    }
}