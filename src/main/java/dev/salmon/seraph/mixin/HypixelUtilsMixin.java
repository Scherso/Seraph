package dev.salmon.seraph.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "cc.polyfrost.oneconfig.utils.hypixel.HypixelUtils")
public class HypixelUtilsMixin {

    @Inject(method = "onTick()Z", at = @At("HEAD"), remap = false, cancellable = true)
    private void onTick(CallbackInfo ci) {
        ci.cancel();
    }

}
