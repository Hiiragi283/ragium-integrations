package hiiragi283.ragium.integration.accessories

import hiiragi283.ragium.api.RagiumAPI
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload

data object HTOpenBackpackPayload : CustomPayload {
    @JvmField
    val PACKET_CODEC: PacketCodec<RegistryByteBuf, HTOpenBackpackPayload> = PacketCodec.unit(HTOpenBackpackPayload)

    @JvmField
    val ID: CustomPayload.Id<HTOpenBackpackPayload> =
        CustomPayload.Id<HTOpenBackpackPayload>(RagiumAPI.id("open_backpack")).apply {
            PayloadTypeRegistry.playC2S().register(this, PACKET_CODEC)
        }

    override fun getId(): CustomPayload.Id<out CustomPayload> = ID
}
