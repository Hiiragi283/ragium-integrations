package hiiragi283.ragium.integration

import hiiragi283.ragium.api.RagiumPlugin
import hiiragi283.ragium.api.energy.HTEnergyStorage
import hiiragi283.ragium.api.energy.HTEnergyType
import hiiragi283.ragium.api.extension.isModLoaded
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext
import net.fabricmc.fabric.api.transfer.v1.storage.Storage
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import team.reborn.energy.api.EnergyStorage

object RagiumEnergyPlugin : RagiumPlugin {
    override val priority: Int = 0

    override fun shouldLoad(): Boolean = isModLoaded("team_reborn_energy")

    override fun afterRagiumInit() {
        /*SIDED.registerFallback { world: World, pos: BlockPos, state: BlockState, blockEntity: BlockEntity?, direction: Direction? ->
            EnergyStorage.SIDED.find(world, pos, state, blockEntity, direction)?.let { storage: EnergyStorage ->
                object : SingleSlotStorage<HTEnergyType> {
                    override fun extract(
                        resource: HTEnergyType,
                        maxAmount: Long,
                        transaction: TransactionContext
                    ): Long = when {
                        resource.isOf(HTEnergyType.ELECTRICITY) ->
                            storage.extract(maxAmount, transaction)

                        else -> 0
                    }

                    override fun insert(
                        resource: HTEnergyType,
                        maxAmount: Long,
                        transaction: TransactionContext?
                    ): Long = when {
                        resource.isOf(HTEnergyType.ELECTRICITY) ->
                            storage.insert(maxAmount, transaction)

                        else -> 0
                    }

                    override fun isResourceBlank(): Boolean = false

                    override fun getResource(): HTEnergyType = HTEnergyType.ELECTRICITY

                    override fun getAmount(): Long = storage.amount

                    override fun getCapacity(): Long = storage.capacity
                }
            }
        }*/

        EnergyStorage.SIDED.registerFallback(::wrapSidedStorage)
        EnergyStorage.ITEM.registerFallback(::wrapItemStorage)
    }

    @JvmStatic
    private fun wrapSidedStorage(
        world: World,
        pos: BlockPos,
        state: BlockState,
        blockEntity: BlockEntity?,
        direction: Direction?,
    ): EnergyStorage? = HTEnergyStorage.SIDED.find(world, pos, state, blockEntity, direction)?.let(::wrapEnergyStorage)

    @JvmStatic
    private fun wrapItemStorage(stack: ItemStack, context: ContainerItemContext): EnergyStorage? =
        HTEnergyStorage.ITEM.find(stack, context)?.let(::wrapEnergyStorage)

    @JvmStatic
    private fun wrapEnergyStorage(storage: Storage<HTEnergyType>): EnergyStorage = object : EnergyStorage {
        override fun supportsInsertion(): Boolean = storage.supportsInsertion()

        override fun insert(maxAmount: Long, transaction: TransactionContext): Long =
            storage.insert(HTEnergyType.ELECTRICITY, maxAmount, transaction)

        override fun supportsExtraction(): Boolean = storage.supportsExtraction()

        override fun extract(maxAmount: Long, transaction: TransactionContext): Long =
            storage.extract(HTEnergyType.ELECTRICITY, maxAmount, transaction)

        override fun getAmount(): Long = storage.iterator().asSequence().sumOf(StorageView<HTEnergyType>::getAmount)

        override fun getCapacity(): Long = storage.iterator().asSequence().sumOf(StorageView<HTEnergyType>::getCapacity)
    }
}
