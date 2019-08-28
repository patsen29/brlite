package com.patsenechal.brlite.data;

import java.util.List;

public class RosterHolder {
    public List<RosterEntry> roster;

    public List<RosterEntry> getRoster() {
        return roster;
    }

    public static class RosterEntry {
        public RosterPerson person;
        public String jerseyNumber;
        public RosterPosition position;
        public RosterStatus status;
        public int parentTeamId;

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
        public int id;
        public String fullName;
        public String link;

        public int getId() {
            return id;
        }

        public String getFullName() {
            return fullName;
        }
    }
    public static class RosterPosition {
        private String code, name, type, abbreviation;

        public String getType() {
            return type;
        }
        public String getAbbreviation() {
            return abbreviation;
        }
    }
    static class RosterStatus {
        public String code, description;
    }

}
