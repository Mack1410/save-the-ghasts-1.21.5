package roseherald.savetheghasts;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import roseherald.savetheghasts.block.ModBlocks;
import roseherald.savetheghasts.item.ModItems;

public class SaveTheGhasts implements ModInitializer {
	public static final String MOD_ID = "save-the-ghasts";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		ModItems.initialize();
	}
}