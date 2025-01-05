package hiiragi283.ragium.integration.rei.category

import hiiragi283.ragium.api.machine.HTMachineKey
import hiiragi283.ragium.integration.rei.createSlot
import hiiragi283.ragium.integration.rei.display.HTMachineRecipeDisplay
import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.gui.widgets.Widget
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import java.util.function.Consumer

@Environment(EnvType.CLIENT)
class HTAssemblyLineCategory(key: HTMachineKey) : HTMachineCategoryBase(key) {
    override fun arrangeSlots(display: HTMachineRecipeDisplay, bounds: Rectangle, consumer: Consumer<Widget>) {
        // inputs
        createSlot(consumer, bounds, 0, 0, display.getFluidInput(0), true)
        createSlot(consumer, bounds, 0, 1, display.getFluidInput(1), true)
        createSlot(consumer, bounds, 1, 0, display.getItemInput(0), true)
        createSlot(consumer, bounds, 2, 0, display.getItemInput(1), true)
        createSlot(consumer, bounds, 1, 1, display.getItemInput(2), true)
        createSlot(consumer, bounds, 2, 1, display.getItemInput(3), true)
        // outputs
        createSlot(consumer, bounds, 5, 0, display.getItemOutput(0), false)
        createSlot(consumer, bounds, 5, 1, display.getFluidOutput(0), false)
        // catalyst
        createSlot(consumer, bounds, 3.5, 1.0, display.catalyst, true)
    }

    override val maxSlots: Int = 2
}
