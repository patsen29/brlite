package com.patsenechal.brlite.spider;

import com.google.gson.Gson;
import com.patsenechal.brlite.data.PlayerHolder;
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

    public static final String PLAYER_URL = "https://statsapi.mlb.com/api/v1/people/%s?hydrate=stats(group=[hitting,pitching,fielding],type=[yearByYear])";
    public static final String ROSTER_URL = "https://statsapi.mlb.com/api/v1/teams/%s/roster";

    public static Map<String, Object> getPlayerData(String playerId) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", playerId);

        String url = String.format(PLAYER_URL, playerId);
        PlayerHolder.Person person = parseJsonFromUrl(url, PlayerHolder.class).getPerson();
        map.put("player", person);
        return map;
    }

    public static Map<String, Object> getRoster(String teamId) {
        Map<String, Object> map = new HashMap<>();
        map.put("teamId", teamId);
        map.put("team", Team.fromId(teamId));
        String url = String.format(ROSTER_URL, teamId);
        RosterHolder h = parseJsonFromUrl(url, RosterHolder.class);
        if (h != null) {
            map.put("roster", h.roster);
        }
        return map;
    }

    private static <T> T parseJsonFromUrl(String url, Class<T> clazz) {
        try {
            Reader r = new InputStreamReader(new URL(url).openStream());
            return GSON.fromJson(r, clazz);
        } catch (IOException e) {
            return null;
        }
    }
}
