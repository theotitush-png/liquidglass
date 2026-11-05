package dev.liquidglass;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;

/**
 * Performs one mace attack during a falling window when a living entity is
 * already under the crosshair. The mace must be in the hotbar.
 */
public final class AutoMace {
    private static boolean attemptedThisFall;

    private AutoMace() {
    }

    public static void tick(MinecraftClient client, ModuleManager modules) {
        boolean enabled = modules.all().stream()
                .anyMatch(module -> module.name().equals("Auto Mace")
                        && module.enabled());

        if (!enabled || client.player == null || client.world == null
                || client.interactionManager == null) {
            attemptedThisFall = false;
            return;
        }

        var player = client.player;
        if (player.isOnGround() || player.fallDistance < 2.0f) {
            if (player.isOnGround()) {
                attemptedThisFall = false;
            }
            return;
        }

        if (attemptedThisFall || !(client.crosshairTarget instanceof EntityHitResult hit)
                || !(hit.getEntity() instanceof LivingEntity target)
                || !target.isAlive() || target == player
                || player.squaredDistanceTo(target) > 25.0) {
            return;
        }

        int maceSlot = -1;
        for (int slot = 0; slot < 9; slot++) {
            if (player.getInventory().getStack(slot).isOf(Items.MACE)) {
                maceSlot = slot;
                break;
            }
        }

        if (maceSlot < 0) {
            return;
        }

        player.getInventory().selectedSlot = maceSlot;
        client.interactionManager.attackEntity(player, target);
        player.swingHand(Hand.MAIN_HAND);
        attemptedThisFall = true;
    }
}
