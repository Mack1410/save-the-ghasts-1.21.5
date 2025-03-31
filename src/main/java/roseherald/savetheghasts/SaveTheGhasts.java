package roseherald.savetheghasts;

import net.fabricmc.api.ModInitializer;

import roseherald.savetheghasts.block.ModBlocks;
import roseherald.savetheghasts.item.ModItems;

public class SaveTheGhasts implements ModInitializer {
	public static final String MOD_ID = "save-the-ghasts";

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		ModItems.initialize();
	}
}