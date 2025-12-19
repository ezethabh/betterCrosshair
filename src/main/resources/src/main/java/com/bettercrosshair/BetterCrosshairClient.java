package com.bettercrosshair;

import net.fabricmc.api.ClientModInitializer;

public class BetterCrosshairClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("BetterCrosshair loaded");
    }
}
