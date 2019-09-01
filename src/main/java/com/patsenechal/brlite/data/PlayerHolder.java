package com.patsenechal.brlite.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PlayerHolder {
    List<Person> people;

    public Person getPerson() {
        if (people.isEmpty()) {
            return null;
        }
        return people.get(0);
    }

    public static class Person {
        private int id;
        private String firstName, lastName, fullName, primaryNumber;
        private String birthDate;
        private String height;
        private int weight, currentAge;
        private RosterPosition primaryPosition;
        private List<StatHolder> stats;
        private ArmSide batSide, pitchHand;

        public LocalDate getBirthDate() {
            return LocalDate.parse(birthDate);
        }
        public String getFullName() {
            return fullName;
        }
        /** The effective birth year, for seasonal age calculations. */
        public int getSeasonalBirthYear() {
            LocalDate dob = LocalDate.parse(birthDate);
            int year = dob.getYear();
            if (dob.getMonth().compareTo(Month.JUNE) > 0) {
                return year + 1;
            } else {
                return year;
            }
        }
        public int getCurrentAge() {
            return currentAge;
        }
        public String getFirstName() {
            return firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public String getPrimaryNumber() {
            return primaryNumber;
        }
        public String getHeight() {
            return height;
        }
        public int getWeight() {
            return weight;
        }
        public RosterPosition getPrimaryPosition() {
            return primaryPosition;
        }
        public String getBatArm() {
            return batSide.code;
        }
        public String getThrowArm() {
            return pitchHand.code;
        }
        public List<StatLine> getStats(StatType statType) {
            if (stats == null) return Collections.emptyList();
            return stats.stream()
                    .filter(statHolder -> statHolder.isType(statType))
                    .map(statHolder -> statHolder.splits)
                    .findAny()
                    .orElse(Collections.emptyList());
        }
        public List<StatLine> getHitting() {
            return getStats(StatType.HITTING);
        }
        public List<StatLine> getPitching() {
            return getStats(StatType.PITCHING);
        }
        public List<StatLine> getFielding() {
            return getStats(StatType.FIELDING);
        }
    }

    enum StatType {
        HITTING, PITCHING, FIELDING
    }
    static class StatHolder {
        private DisplayName group;
        private List<StatLine> splits;
        boolean isType(StatType type) {
            return type.name().equalsIgnoreCase(group.displayName);
        }
    }
    public static class StatLine {
        private String season;
        private JsonObject stat;
        private TeamHolder team;

        public String getSeason() {
            return season;
        }
        public String getStat(String memberName) {
            JsonPrimitive res = stat.getAsJsonPrimitive(memberName);
            return res == null ? "" : res.getAsString();
        }
        public String getPos() {
            return stat.getAsJsonObject("position").getAsJsonPrimitive("abbreviation").getAsString();
        }
        public int getAge(int playerSeasonalYearOfBirth) {
            return Integer.parseInt(season) - playerSeasonalYearOfBirth;
        }
        public TeamHolder getTeam() {
            return team;
        }
        public String getTeamCode() {
            if (team == null) return "TOT";
            return Optional.of(team)
                    .map(teamHolder -> Team.fromId(team.id))
                    .map(Enum::name)
                    .orElse(team.name.substring(0, 3).toUpperCase());
        }
    }
    private static class TeamHolder {
        private int id;
        private String name;
    }

    static class DisplayName {
        private String displayName;
    }

    static class ArmSide {
        private String code;

        public String getCode() {
            return code;
        }
    }
}
