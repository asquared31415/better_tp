package asquared31415.better_tp;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandBetterTeleport());
    }
}
