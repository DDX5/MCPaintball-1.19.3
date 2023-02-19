package org.multicoder.mcpaintball.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import org.multicoder.mcpaintball.capability.PlayerTeamCapabilityProvider;
import org.multicoder.mcpaintball.client.ClientPlayerTeamData;

import java.util.function.Supplier;

public class TeamUpdateS2CPacket
{
    private final int Team;
    public TeamUpdateS2CPacket(int T){this.Team = T;}
    public TeamUpdateS2CPacket(FriendlyByteBuf buffer){Team = buffer.readInt();}
    public void toBytes(FriendlyByteBuf buffer){buffer.writeInt(Team);}

    public boolean handlePacket(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context ctx = supplier.get();
        Player player = ctx.getSender();
        Level level = player.getLevel();
        ClientPlayerTeamData.SetTeam(Team);
        player.sendSystemMessage(Component.literal("Team Updated To: " + Team));
        return true;
    }
}
