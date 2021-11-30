package com.cometproject.server.game.commands.user;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.api.stats.CometStats;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.GameCycle;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.sessions.Session;

import java.text.NumberFormat;

public class AboutCommand extends ChatCommand {
    public void execute(Session client, String[] message) {

        CometStats cometStats = Comet.getStats();

        boolean aboutStats = client.getPlayer().getPermissions().getRank().aboutStats();

        String aboutContent = "<font color='#0D0106'><b>About Server:</b><br><font size=\"11\" color=\"#1C1C1C\"><font size=\"11\" color=\"#3E5F8A\">Eminus Emulator</font> powered by " + CometSettings.hotelName + " , our main goal is sharing Habbo basics with our customers, adding some untold content.</font><br><br><font size=\"12\" color=\"#0B4C5F\"><b>Stats:</b></font><br><font size=\"11\" color=\"#1C1C1C\"><b>· Users: </b> " + cometStats.getPlayers() + "<br><b>· Rooms: </b>" + cometStats.getRooms() + "<br><b>· Uptime: </b> " + cometStats.getUptime() + "<br></font>";
        String aboutStatsContent = "<font color='#0D0106'><br><font size=\"12\" color=\"#0B4C5F\"><b>Balance:</b></font><br><font size=\"11\" color=\"#1C1C1C\"><b>· Global Record: </b>"+ GameCycle.getInstance().getOnlineRecord() + "</font><br><font size=\"11\" color=\"#1C1C1C\"><b>· Last Record: </b>"+ GameCycle.getInstance().getCurrentOnlineRecord() +"</font></font><br><font size=\"11\" color=\"#1C1C1C\"><b>· Used memory: </b> " + cometStats.getUsedMemory() + " MB</font></font><br><br><font size=\"11\" color=\"#1C1C1C\"><b>· License to:</b> <b>" + CometSettings.hotelName + "</b></font></font><br>";

        if (aboutStats)
            aboutContent = aboutContent + aboutStatsContent;

        client.send(new AlertMessageComposer("<center>" + aboutContent + "</center>", ""));
    }

    public boolean isHidden() {
        return true;
    }

    public String getPermission() {
        return "about_command";
    }

    @Override
    public String getParameter() {
        return null;
    }

    public String getDescription() {
        return Locale.get("command.about.description");
    }
}
