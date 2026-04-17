package hvn.foodrevamped.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import hvn.foodrevamped.FoodRevamped;

public class FoodRevampedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		FoodRevamped.LOGGER.info("Initializing FoodRevamped Client...");
		
		try {
			// Register HUD render callback for custom hunger bar
			HudRenderCallback.EVENT.register((guiGraphics, deltaTracker) -> {
				HungerBarRenderer.renderHungerBar(guiGraphics);
			});
			
			FoodRevamped.LOGGER.info("FoodRevamped Client LOADED!");
		} catch (Exception e) {
			FoodRevamped.LOGGER.error("Failed to initialize FoodRevamped Client", e);
			throw new RuntimeException(e);
		}
	}
}
