package dev.salmon.seraph.util.chat;

import dev.salmon.seraph.Seraph;
import dev.salmon.seraph.util.locraw.LocrawInfo;
import gg.essential.universal.ChatColor;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

interface ChatHelper {

    default int getPriority() {
        return 0;
    }

    default boolean isEnabled() {
        return true;
    }

    @NotNull
    default IChatComponent colorMessage(@NotNull String message) {
        return new ChatComponentText(ChatColor.Companion.translateAlternateColorCodes('&', message));
    }

    @Nullable
    default LocrawInfo getLowcraw() {
        return Seraph.Instance.getLocrawInfo();
    }

    default String getStrippedMessage(IChatComponent component) {
        return EnumChatFormatting.getTextWithoutFormattingCodes(component.getUnformattedText());
    }

}
