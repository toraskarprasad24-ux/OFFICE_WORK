//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.request.handler;

import com.concerto.sprinklr.application.init.ServerInitializer;
import com.concerto.sprinklr.application.req.bean.ContextParams;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiProcessing implements Runnable {
	static Logger logger = LoggerFactory.getLogger("AppLogger");

	public MultiProcessing() {
	}

	public void run() {
		ExecutorService executor = Executors.newFixedThreadPool(ServerInitializer.parallelProcessingCount);

		while(true) {
			try {
				Map<String, ContextParams> messageDatas = ServerInitializer.requestDatas;
				if (messageDatas.size() > 0) {
					for(Map.Entry<String, ContextParams> entry : messageDatas.entrySet()) {
						ContextParams contextParams = (ContextParams)entry.getValue();
						ServerInitializer.requestDatas.remove(entry.getKey());
						Runnable processor = new MessageProcessor(contextParams);
						executor.execute(processor);
					}
				}

				if (ServerInitializer.processingInterval > (double)0.0F) {
					Thread.sleep((long)(ServerInitializer.processingInterval * (double)100.0F));
				}
			} catch (Exception e) {
				logger.error("Unable to start process,", e);
			}
		}
	}
}
