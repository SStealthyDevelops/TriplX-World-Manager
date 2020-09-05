package core.triplxmc.world.log.debug;

import lombok.Getter;

@Getter

public enum DebugLevel {

    INFO("&f[TWM INFO]", false), WARNING("&6[TWM WARNING]", false), ERROR("&c[TWM ERROR]", false), CRITICAL("&4[TWM CRITICAL]", true);


    private final boolean critical;
    private final String prefix;
    DebugLevel(String prefix, boolean shutDownServer) {
        this.prefix = prefix;
        this.critical = shutDownServer;
    }

}
