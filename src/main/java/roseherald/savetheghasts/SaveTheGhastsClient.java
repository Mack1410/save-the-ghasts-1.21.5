package roseherald.savetheghasts;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import roseherald.savetheghasts.entity.ModEntities;

public class SaveTheGhastsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SPONGEBALL, FlyingItemEntityRenderer::new);
    }
}
