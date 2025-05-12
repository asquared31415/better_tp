package asquared31415.better_tp;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandBetterTeleport extends CommandBase {

    @Override
    public String getCommandName() {
        return "bettertp";
    }

    @Override
    public int getRequiredPermissionLevel() {
        // same as vanilla tp command
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.bettertp.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 5) {
            throw new WrongUsageException("commands.bettertp.usage");
        }

        // possible args:
        // <pos> <rotation>
        // <target> <pos> <rotation>

        // relative positions should be relative to the sender
        var src = CommandBase.getCommandSenderAsPlayer(sender);

        var possibleTarget = MinecraftServer.getServer()
            .getConfigurationManager()
            .func_152612_a(args[0]);
        EntityPlayerMP target;
        int idx;
        if (possibleTarget != null) {
            if (args.length != 6) {
                throw new WrongUsageException("commands.bettertp.usage");
            }
            target = possibleTarget;
            idx = 1;
        } else {
            if (args.length != 5) {
                throw new WrongUsageException("commands.bettertp.usage");
            }
            target = src;
            idx = 0;
        }

        // parse pos and rotation
        var posX = CommandBase.func_110666_a(sender, src.posX, args[idx++]);
        var posY = CommandBase.func_110665_a(sender, src.posY, args[idx++], 0, 0);
        var posZ = CommandBase.func_110666_a(sender, src.posZ, args[idx++]);
        var rotationYaw = (float) CommandBase.parseDoubleBounded(sender, args[idx++], -180.0, 180.0);
        var rotationPitch = (float) CommandBase.parseDoubleBounded(sender, args[idx], -90.0, 90.0);

        target.mountEntity(null);
        target.playerNetServerHandler.setPlayerLocation(posX, posY, posZ, rotationYaw, rotationPitch);
        CommandBase
            .func_152373_a(sender, this, "commands.bettertp.success.coords", target.getDisplayName(), posX, posY, posZ);
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }
}
