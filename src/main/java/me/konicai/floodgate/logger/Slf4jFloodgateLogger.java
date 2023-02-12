package me.konicai.floodgate.logger;

import static org.geysermc.floodgate.util.MessageFormatter.format;

import lombok.RequiredArgsConstructor;
import org.geysermc.floodgate.api.logger.FloodgateLogger;
import org.geysermc.floodgate.util.LanguageManager;
import org.slf4j.Logger;

@RequiredArgsConstructor
public final class Slf4jFloodgateLogger implements FloodgateLogger {
    private final Logger logger;
    private final LanguageManager languageManager;

    @Override
    public void error(String message, Object... args) {
        logger.error(message, args);
    }

    @Override
    public void error(String message, Throwable throwable, Object... args) {
        logger.error(format(message, args), throwable);
    }

    @Override
    public void warn(String message, Object... args) {
        logger.warn(message, args);
    }

    @Override
    public void info(String message, Object... args) {
        logger.info(message, args);
    }

    @Override
    public void translatedInfo(String message, Object... args) {
        logger.info(languageManager.getLogString(message, args));
    }

    @Override
    public void debug(String message, Object... args) {
        logger.debug(message, args);
    }

    @Override
    public void trace(String message, Object... args) {
        logger.trace(message, args);
    }

    @Override
    public boolean isDebug() {
        return logger.isDebugEnabled();
    }
}

