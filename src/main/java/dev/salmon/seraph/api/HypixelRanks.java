package dev.salmon.seraph.api;

public enum HypixelRanks {
    REGULAR("REGULAR"),
    VIP("VIP"),
    VIP_PLUS("VIP_PLUS"),
    MVP("MVP"),
    MVP_PLUS("MVP_PLUS"),
    MVP_PLUS_PLUS("SUPERSTAR");

    private final String name;

    HypixelRanks(String name) {
        this.name = name;
    }
}