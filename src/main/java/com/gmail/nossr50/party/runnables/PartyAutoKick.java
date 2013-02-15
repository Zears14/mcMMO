package com.gmail.nossr50.party.runnables;

import java.util.ArrayList;

import org.bukkit.OfflinePlayer;

import com.gmail.nossr50.config.Config;
import com.gmail.nossr50.party.Party;
import com.gmail.nossr50.party.PartyManager;

public class PartyAutoKick implements Runnable {
    @Override
    public void run() {
        updatePartyMembers();
    }

    private void updatePartyMembers() {
        long currentTime = System.currentTimeMillis();
        long kickTime = 24L * 60L * 60L * 1000L * Config.getInstance().getAutoPartyKickTime();

        for (Party party : new ArrayList<Party>(PartyManager.getParties())) {
            for (OfflinePlayer member : new ArrayList<OfflinePlayer>(party.getMembers())) {
                if (currentTime - member.getLastPlayed() > kickTime) {
                    PartyManager.removeFromParty(member, party);
                }
            }
        }
    }
}
