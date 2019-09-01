package com.patsenechal.brlite.data;

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
