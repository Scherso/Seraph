package dev.salmon.seraph.events;

import dev.salmon.seraph.util.locraw.LocrawInformation;
import net.minecraftforge.fml.common.eventhandler.Event;

public class LocrawEvent extends Event {
    public LocrawInformation locraw;

    public LocrawEvent(LocrawInformation locraw) {
        this.locraw = locraw;
    }
}
