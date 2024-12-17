package hiiragi283.ragium.integration.jade

import hiiragi283.ragium.api.block.HTMachineBlockEntityBase
import snownee.jade.api.BlockAccessor

//    BlockAccessor    //

val BlockAccessor.machineBlockEntity: HTMachineBlockEntityBase?
    get() = blockEntity as? HTMachineBlockEntityBase
