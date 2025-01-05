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
class HTGeneratorCategory(key: HTMachineKey) : HTMachineCategoryBase(key) {
    override fun arrangeSlots(display: HTMachineRecipeDisplay, bounds: Rectangle, consumer: Consumer<Widget>) {
        // inputs
        createSlot(consumer, bounds, 1, 0, display.getFluidInput(0), true)
        createSlot(consumer, bounds, 2, 0, display.getItemInput(0), true)
        // outputs
        createSlot(consumer, bounds, 5, 0, display.outputEntries.getOrNull(0), false)
    }

    override val maxSlots: Int = 1
}
