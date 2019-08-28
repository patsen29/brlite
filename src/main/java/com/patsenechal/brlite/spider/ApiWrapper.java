package com.patsenechal.brlite.spider;

import com.google.gson.Gson;
import com.patsenechal.brlite.data.RosterHolder;
import com.patsenechal.brlite.data.Team;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ApiWrapper {
    private static final Gson GSON = new Gson();

    public static Map<String, Object> getRoster(String teamId) {
        Map<String, Object> map = new HashMap<>();
        map.put("teamId", teamId);
        map.put("team", Team.fromId(teamId));
        String url = String.format("https://statsapi.mlb.com/api/v1/teams/%s/roster", teamId);
        try {
            Reader r = new InputStreamReader(new URL(url).openStream());
            RosterHolder rosterHolder = GSON.fromJson(r, RosterHolder.class);
            map.put("roster", rosterHolder.roster);
        } catch (IOException e) {
            map.put("error", e.getMessage());
        }
        return map;
    }

    private static Map<String, Object> errorMessage(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", message);
        return map;
    }
}
