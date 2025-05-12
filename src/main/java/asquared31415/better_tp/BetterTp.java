package asquared31415.better_tp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = BetterTp.MODID, version = Tags.VERSION, name = "Better TP", acceptedMinecraftVersions = "[1.7.10]")
public class BetterTp {

    public static final String MODID = "better_tp";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "asquared31415.better_tp.ClientProxy", serverSide = "asquared31415.better_tp.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
