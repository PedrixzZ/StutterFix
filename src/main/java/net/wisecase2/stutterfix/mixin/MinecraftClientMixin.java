package net.wisecase2.stutterfix.mixin;

import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(net.minecraft.client.MinecraftClient.class)
public class MinecraftClientMixin {
    @Redirect(method = "render(Z)V", at = @At(value = "INVOKE", target = "java/lang/Thread.yield ()V"))
    private void removeThreadYield() {
        //Remove yield() only for the mod that uses the "Wait for GPU" system
        String modName = "VulkanMod"; // Replace this with the name of the mod you want to check
        if (FabricLoader.getInstance().isModLoaded(VulkanMod)) {
            Thread.yield();
        }
    }
}
