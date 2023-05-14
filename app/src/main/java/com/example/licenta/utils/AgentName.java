package com.example.licenta.utils;

public enum AgentName {
    ANDREI_MARIN("Andrei Marin"),
    IOANA_GEORGESCU("Ioana Georgescu"),
    CRISTIAN_MATEI("Cristian Matei"),
    ALINA_STEFANESCU("Alina Stefanescu");

    private String agentName;

    private AgentName(String agentName) {
        this.agentName = agentName;
    }

    @Override
    public String toString() {
        return agentName;
    }

    public static AgentName getName(String fromString) {
        for (AgentName name : AgentName.values()
        ) {
            if (name.agentName.equals(fromString)) {
                return name;
            }
        }
        return null;
    }
}
