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
import java.util.Optional;

public class ApiWrapper {
    private static final Gson GSON = new Gson();

    public static final String PLAYER_URL = "https://statsapi.mlb.com/api/v1/people/%s?hydrate=stats(group=[hitting,pitching,fielding],type=[yearByYear])";
    public static final String ROSTER_URL = "https://statsapi.mlb.com/api/v1/teams/%s/roster";

    /**
     * Pulls player data from MLB stats api, and turns it into a map, suitable for the Velocity templates
     * @param playerId The 6-digit MLBAM id
     * @return A map for velocity, containing id, player if there's a valid player, and error otherwise.
     */
    public static Map<String, Object> getPlayerData(String playerId) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", playerId);

        String url = String.format(PLAYER_URL, playerId);
        PlayerHolder playerHolder = parseJsonFromUrl(url, PlayerHolder.class);
        if (playerHolder != null) {
            map.put("player", playerHolder.getPerson());
        } else {
            map.put("error", "Could not find player with id " + playerId);
        }
        return map;
    }

    /**
     * Pulls roster data from MLB stats api, and turns it into a map, suitable for the Velocity template.
     * @param teamId Either the numeric team id (141 for Tor), or a three letter abbreviation for one of the MLB teams.
     * @return A velocity map, containing teamId (numeric), teamName, and roster list.
     */
    public static Map<String, Object> getRoster(String teamId) {
        Map<String, Object> map = new HashMap<>();
        Team t = Team.fromId(teamId);
        if (t != null) teamId = Integer.toString(t.getId());
        map.put("teamId", t == null ? teamId : teamId);
        String teamName = Optional.ofNullable(t).map(Team::getCity).orElse("Team");
        map.put("teamName", teamName);
        String url = String.format(ROSTER_URL, teamId);
        RosterHolder h = parseJsonFromUrl(url, RosterHolder.class);
        if (h != null) {
            map.put("roster", h.roster);
        }
        return map;
    }

    /**
     * Utility function to parse json at a given URL, and map it into a given class structure.
     * @param url The URL containing the json to parse
     * @param clazz The class to parse to
     * @return A parsed Object of the requested type
     */
    public static <T> T parseJsonFromUrl(String url, Class<T> clazz) {
        try {
            Reader r = new InputStreamReader(new URL(url).openStream());
            return GSON.fromJson(r, clazz);
        } catch (IOException e) {
            return null;
        }
    }
}
