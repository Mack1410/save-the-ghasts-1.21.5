package roseherald.savetheghasts.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import roseherald.savetheghasts.SaveTheGhasts;

import java.util.function.Function;

public class ModItems {

    public static final Item SPONGEBALL = register("spongeball",
            SpongeballItem::new, new Item.Settings().maxCount(16));

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings){
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SaveTheGhasts.MOD_ID, name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((itemGroup) -> itemGroup.add(ModItems.SPONGEBALL));
    }
}
