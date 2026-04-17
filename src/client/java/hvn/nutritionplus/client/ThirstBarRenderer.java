package hvn.nutritionplus.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.Minecraft;

/**
 * ThirstBarRenderer - Renders thirst bars
 */
public class ThirstBarRenderer {

	// Texture location for thirst icons 
	private static final ResourceLocation THIRST_TEXTURE = ResourceLocation.fromNamespaceAndPath("nutritionplus", "textures/gui/thirst_icon.png");
	
	private static final Minecraft minecraft = Minecraft.getInstance();
	
	// Icon dimensions (similar to heart size)
	private static final int ICON_SIZE = 10; // 
	private static final int ICON_SPACING = 8; // Space between icons
	
	/**
	 * Renders the thirst bar with texture sprites - called from HudRenderCallback
	 */
	public static void renderThirstBar(GuiGraphics guiGraphics) {
		// Get the player
		Player player = minecraft.player;
		if (player == null) {
			return;
		}
		
		// Get thirst level (using food level as thirst proxy)
		int thirstLevel = player.getFoodData().getFoodLevel();
		
		// Screen dimensions
		int screenWidth = minecraft.getWindow().getGuiScaledWidth();
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();
		
		// Position for thirst bar - above the vanilla hunger bar
		int x = screenWidth / 2 - 92; 
		int y = screenHeight - 60;
		
		// Render texture-based thirst icons
		renderThirstIcons(guiGraphics, x, y, thirstLevel);
	}
	
	/**
	 * Renders individual thirst icons in a row
	 * Each icon represents 2 thirst points (10 full icons for 20 thirst)
	 */
	private static void renderThirstIcons(GuiGraphics guiGraphics, int x, int y, int thirstLevel) {
		// Calculate how many full icons and if there's a half icon
		int fullIcons = thirstLevel / 2;
		boolean hasHalfIcon = thirstLevel % 2 == 1;
		
		int maxIcons = 10; // 20 thirst / 2 per icon = 10 icons
		
		// Render all icon slots
		for (int i = 0; i < maxIcons; i++) {
			int iconX = x + (i * ICON_SPACING);
			
			if (i < fullIcons) {
				// Render full thirst icon
				renderThirstIcon(guiGraphics, iconX, y, true, false);
			} else if (i == fullIcons && hasHalfIcon) {
				// Render half thirst icon
				renderThirstIcon(guiGraphics, iconX, y, false, true);
			} else {
				// Render empty thirst icon
				renderThirstIcon(guiGraphics, iconX, y, false, false);
			}
		}
	}
	
	/**
	 * Render a single thirst icon
	 * @param guiGraphics The rendering context
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param full Whether the icon is full
	 * @param half Whether the icon is half-filled
	 */
	private static void renderThirstIcon(GuiGraphics guiGraphics, int x, int y, boolean full, boolean half) {
		// Only render filled icons, skip empty slots
		if (full || half) {
			// Render the water droplet texture
			// blit signature: (texture, x, y, blitOffset, u, v, width, height, textureWidth, textureHeight)
			guiGraphics.blit(THIRST_TEXTURE, x, y, 0, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
			
			// For half icons, optionally darken or scale down
			if (half) {
				// Draw a semi-transparent overlay to indicate half-filled
				guiGraphics.fill(x + ICON_SIZE / 2, y, x + ICON_SIZE, y + ICON_SIZE, 0x44000000); // Semi-transparent black on right half
			}
		}
	}
}
