@file:Environment(EnvType.CLIENT)

package hiiragi283.ragium.integration.jade

import hiiragi283.ragium.api.block.entity.HTMachineBlockEntityBase
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import snownee.jade.api.BlockAccessor

//    BlockAccessor    //

val BlockAccessor.machineBlockEntity: HTMachineBlockEntityBase?
    get() = blockEntity as? HTMachineBlockEntityBase
