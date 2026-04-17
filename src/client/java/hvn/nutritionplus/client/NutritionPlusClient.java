package hvn.nutritionplus.client;

import hvn.nutritionplus.NutritionPlus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class NutritionPlusClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		NutritionPlus.LOGGER.info("Initializing NutritionPlus Client...");
		
		try {
			// Register HUD render callback for custom hunger bar
			HudRenderCallback.EVENT.register((guiGraphics, deltaTracker) -> {
				ThirstBarRenderer.renderThirstBar(guiGraphics);
			});
			
			NutritionPlus.LOGGER.info("NutritionPlus Client LOADED!");
		} catch (Exception e) {
			NutritionPlus.LOGGER.error("Failed to initialize NutritionPlus Client", e);
			throw new RuntimeException(e);
		}
	}
}
