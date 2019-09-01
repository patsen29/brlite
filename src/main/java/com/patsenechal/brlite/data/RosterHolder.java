package com.patsenechal.brlite.data;

import java.util.List;

/**
 * This class includes all the data relating to team rosters, and all the subclasses needed to do so.
 * It is a strongly typed implementation of the MLB stats API, that it pulls the data from.
 */
public class RosterHolder {
    public List<RosterEntry> roster;

    public List<RosterEntry> getRoster() {
        return roster;
    }

    public static class RosterEntry {
        private RosterPerson person;
        private String jerseyNumber;
        private RosterPosition position;
        private RosterStatus status;
        private int parentTeamId;

        public RosterPerson getPerson() {
            return person;
        }

        public String getJerseyNumber() {
            if (jerseyNumber == null) return "--";
            return String.format("%2s", jerseyNumber).replace(' ', '\u00a0');
        }

        public RosterPosition getPosition() {
            return position;
        }
    }
    public static class RosterPerson {
        private int id;
        private String fullName;
        private String link;

        public int getId() {
            return id;
        }

        public String getFullName() {
            return fullName;
        }
    }

    private static class RosterStatus {
        private String code, description;
    }

}
