package org.hypejet.floodgate.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import org.hypejet.floodgate.addon.data.MinestomDataAddon;
import org.geysermc.floodgate.addon.AddonManagerAddon;
import org.geysermc.floodgate.addon.DebugAddon;
import org.geysermc.floodgate.addon.PacketHandlerAddon;
import org.geysermc.floodgate.api.inject.InjectorAddon;
import org.geysermc.floodgate.register.AddonRegister;

public final class MinestomAddonModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AddonRegister.class).asEagerSingleton();
    }

    @Singleton
    @ProvidesIntoSet
    public InjectorAddon managerAddon() {
        return new AddonManagerAddon();
    }

    @Singleton
    @ProvidesIntoSet
    public InjectorAddon dataAddon() {
        return new MinestomDataAddon();
    }

    @Singleton
    @ProvidesIntoSet
    public InjectorAddon debugAddon() {
        return new DebugAddon();
    }

    @Singleton
    @ProvidesIntoSet
    public InjectorAddon packetHandlerAddon() {
        return new PacketHandlerAddon();
    }
}