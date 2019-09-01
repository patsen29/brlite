package com.patsenechal.brlite.data;

import java.util.List;

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
            return jerseyNumber;
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

    static class RosterStatus {
        private String code, description;
    }

}
