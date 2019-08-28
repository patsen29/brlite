package com.patsenechal.brlite.data;

import java.util.Arrays;

public enum Team {
    BAL(110, "Baltimore"),
    BOS(111, "Boston"),
    NYY(147, "New York"),
    TBR(139, "Tampa Bay"),
    TOR(141, "Toronto"),
    CHW(145, "Chicago"),
    CLE(114, "Cleveland"),
    DET(116, "Detroit"),
    KCR(118, "Kansas City"),
    MIN(142, "Minnesota"),
    HOU(117, "Houston"),
    LAA(108, "Los Angeles"),
    OAK(133, "Oakland"),
    SEA(136, "Seattle"),
    TEX(140, "Texas"),
    ATL(144, "Atlanta"),
    MIA(146, "Miami"),
    NYM(121, "New York"),
    PHI(143, "Philadelphia"),
    WAS(120, "Washington"),
    CHC(112, "Chicago"),
    CIN(113, "Cincinnati"),
    MIL(158, "Milwaukee"),
    PIT(134, "Pittsburgh"),
    STL(138, "St. Louis"),
    ARI(109, "Arizona"),
    COL(115, "Colorado"),
    LAD(119, "Los Angeles"),
    SDP(135, "San Diego"),
    SFG(137, "San Francisco");

    Team(int id, String city) {
        this.id = id;
        this.city = city;
    }

    private int id;
    private String city;

    public int getId() {
        return id;
    }
    public String getCity() {
        return city;
    }
    public static Team fromId(String id) {
        return Arrays.stream(values())
                .filter(team -> Integer.toString(team.id).equals(id))
                .findAny()
                .orElse(null);
    }
}
