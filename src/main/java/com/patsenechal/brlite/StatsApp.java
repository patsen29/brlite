package com.patsenechal.brlite;

import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class StatsApp {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        get("/", StatsApp::teamList);
        get("/team/:teamId", StatsApp::teamRoster);
        get("/player/:playerId", StatsApp::player);
    }

    private static Object teamList(Request req, Response res) {
        return "Teams go here";
    }
    private static Object teamRoster(Request req, Response res) {
        return String.format("Team %s goes here", req.params("teamId"));
    }
    private static Object player(Request req, Response res) {
        return String.format("Player %s goes here", req.params("playerId"));
    }

    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}
