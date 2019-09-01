package com.patsenechal.brlite;

import com.patsenechal.brlite.data.Team;
import com.patsenechal.brlite.spider.ApiWrapper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class StatsApp {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        get("/", StatsApp::teamList);
        get("/team/:teamId", StatsApp::teamRoster);
        get("/player/:playerId", StatsApp::player);
    }

    private static String teamList(Request req, Response res) {
        Map<String, Object> map = new HashMap<>();
        map.put("teams", Team.values());
        return render(map, "templates/league.htm");
        // TODO: Pull standings, and give a more useful front page?
    }
    private static String teamRoster(Request req, Response res) {
        String teamId = req.params("teamId");
        Map<String, Object> map = ApiWrapper.getRoster(teamId);
        return render(map, "templates/team.htm");
    }
    private static String player(Request req, Response res) {
        String playerId = req.params("playerId");
        Map<String, Object> data = ApiWrapper.getPlayerData(playerId);
        return render(data, "templates/player.htm");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}
