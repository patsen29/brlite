package com.patsenechal.brlite.data;

/**
 * This is a strongly typed interpretation of the positional data used by MLB stats API.
 */
public class RosterPosition {
    private String code, name, type, abbreviation;

    public String getType() {
        return type;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public boolean isPitcher() {
        return "Pitcher".equals(type);
    }
}
