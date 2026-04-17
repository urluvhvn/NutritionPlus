package hvn.foodrevamped.Items;

import hvn.foodrevamped.FoodRevamped;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class Items {

    // FOOD ITEMS EFFECTS

    public static final FoodProperties REGEN_FOOD = new FoodProperties.Builder()
            // HUNGER BARS
            .nutrition(4)

            // SATURATION POINT
            .saturationModifier(4)

            // Whether the food can be eaten at any time or not
            .alwaysEdible()


            // FIRST VALUE
            // Effect Duration: e.g. 1 second = 20 ticks, in this case 2 times 20 ticks

            // SECOND VALUE
            // Effect Multiplier: e.g. Regen II, in this case being 1
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 4 * 20, 1), 1.0f)
            .build();


    // NEW ITEMS

    public static final Item STRAWBERRY = register(
            new Item(new Item.Properties().food(REGEN_FOOD)),
            "strawberry"
    );




    // REGISTER NEW ITEMS

    public static Item register(Item item, String id) {
        ResourceLocation itemID = ResourceLocation.fromNamespaceAndPath(FoodRevamped.MOD_ID, id);

        return Registry.register(BuiltInRegistries.ITEM, itemID, item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((itemGroup) -> itemGroup.accept(Items.STRAWBERRY));
    }
}


