package hvn.foodrevamped.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.Minecraft;

/**
 * HungerBarRenderer - Renders a custom hunger bar on the HUD
 */
public class HungerBarRenderer {
	
	private static final Minecraft minecraft = Minecraft.getInstance();
	
	/**
	 * Renders the custom hunger bar - called from HudRenderCallback
	 */
	public static void renderHungerBar(GuiGraphics guiGraphics) {
		// Get the player
		Player player = minecraft.player;
		if (player == null) {
			return;
		}
		
		// Get hunger and saturation
		int foodLevel = player.getFoodData().getFoodLevel();
		float saturation = player.getFoodData().getSaturationLevel();
		
		// Example: Custom hunger bar rendering
		// The hunger bar is normally rendered at the bottom of the screen
		int screenWidth = minecraft.getWindow().getGuiScaledWidth();
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();
		
		// Position for custom hunger bar (perfectly aligned on top of vanilla bar)
		int x = screenWidth / 2 - 91; // Left side of hunger bar (same as vanilla)
		int y = screenHeight - 	60; // Y position of hunger bar
		
		// Render custom hunger bar
		renderCustomHungerBar(guiGraphics, x, y, foodLevel, saturation);
	}
	
	/**
	 * Custom hunger bar rendering method
	 * @param guiGraphics The GuiGraphics object for rendering
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param foodLevel Current food level (0-20)
	 * @param saturation Saturation level
	 */
	private static void renderCustomHungerBar(GuiGraphics guiGraphics, int x, int y, int foodLevel, float saturation) {
		// Render background bar (grey)
		guiGraphics.fill(x, y, x + 81, y + 9, 0xFF808080);
		
		// Render filled portion based on food level
		// Food level goes from 0-20, so we need to scale it to the bar width (81 pixels)
		int filledWidth = (int) ((foodLevel / 20.0f) * 81);
		
		// Color based on food level
		int barColor = getHungerBarColor(foodLevel);
		guiGraphics.fill(x, y, x + filledWidth, y + 9, barColor);
		
		// Render saturation indicator on top (optional)
		if (saturation > 0) {
			int saturationWidth = (int) ((saturation / 20.0f) * 81);
			guiGraphics.fill(x, y + 2, x + saturationWidth, y + 4, 0xFFFFD700); // Gold color
		}
		
		// Render border
		guiGraphics.fill(x - 1, y - 1, x + 82, y, 0xFF000000); // Top
		guiGraphics.fill(x - 1, y + 9, x + 82, y + 10, 0xFF000000); // Bottom
		guiGraphics.fill(x - 1, y - 1, x, y + 10, 0xFF000000); // Left
		guiGraphics.fill(x + 81, y - 1, x + 82, y + 10, 0xFF000000); // Right
	}
	
	/**
	 * Determine hunger bar color based on food level
	 * @param foodLevel Current food level (0-20)
	 * @return Color as ARGB integer
	 */
	private static int getHungerBarColor(int foodLevel) {
		if (foodLevel >= 15) {
			return 0xFF00FF00; // Green - Well fed
		} else if (foodLevel >= 10) {
			return 0xFFFFFF00; // Yellow - Moderate
		} else if (foodLevel >= 5) {
			return 0xFFFF8800; // Orange - Low
		} else {
			return 0xFFFF0000; // Red - Very low
		}
	}
}
