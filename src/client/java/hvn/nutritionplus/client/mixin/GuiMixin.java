package hvn.foodrevamped.client.mixin;

import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
@Mixin(Gui.class)
public class GuiMixin {
	// Keep vanilla hunger bar - custom bar will render above it
	// No need to cancel the vanilla rendering anymore
}
