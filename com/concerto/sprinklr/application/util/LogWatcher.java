//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.util;

import com.concerto.sprinklr.application.init.ServerInitializer;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogWatcher implements Runnable {
	static Logger logger = LoggerFactory.getLogger("AppLogger");

	public LogWatcher() {
	}

	public void run() {
		this.logWatch();
	}

	public void logWatch() {
		WatchService watchService = null;
		WatchKey key = null;

		try {
			Path configFilePath = Paths.get(ServerInitializer.log4jConfgPath);
			watchService = FileSystems.getDefault().newWatchService();
			configFilePath.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

			while((key = watchService.take()) != null) {
				if (ServerInitializer.fileWatchingInterval > (double)0.0F) {
					Thread.sleep((long)(ServerInitializer.fileWatchingInterval * (double)100.0F));
				}

				for(WatchEvent<?> event : key.pollEvents()) {
					if (event.context().toString().equals(configFilePath.getFileName().toString())) {
						Configurator.reconfigure(configFilePath.toUri());
						logger.warn(event.context().toString() + " configurations updated sucessfully.");
					}
				}

				key.reset();
			}
		} catch (Exception e) {
			logger.error("Problem while updating log4j2.xml configurations,", e);
		} finally {
			if (key != null) {
				key.reset();
			}

			if (watchService != null) {
				try {
					watchService.close();
				} catch (IOException i) {
					logger.error("Unable to close watch service,", i);
				}
			}

		}

	}
}
