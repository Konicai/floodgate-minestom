package me.konicai.floodgate.injector.minestom;

import org.geysermc.floodgate.inject.CommonPlatformInjector;

public class MinestomInjector extends CommonPlatformInjector {

    @Override
    public boolean inject() {
        return true;
    }

    @Override
    public boolean removeInjection() {
        return true;
    }

    @Override
    public boolean isInjected() {
        return true;
    }
}
