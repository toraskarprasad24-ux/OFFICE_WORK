//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.request.handler;

import com.concerto.sprinklr.application.init.ServerInitializer;
import com.concerto.sprinklr.application.req.bean.ContextParams;
import com.concerto.sprinklr.application.util.ComapareByMessageid;
import com.concerto.sprinklr.jdbc.service.ExternalRequestDataService;
import java.util.ArrayList;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestPolling implements Runnable {
	private ExternalRequestDataService service = new ExternalRequestDataService();
	static Logger logger = LoggerFactory.getLogger("AppLogger");

	public RequestPolling() {
	}

	public void run() {
		while(true) {
			logger.warn("Polling Dialer Requests.");

			try {
				ArrayList<ContextParams> requestData = this.service.getAllExternalRequests();
				if (requestData.size() >= 1) {
					if (ServerInitializer.reqsortenable == 1) {
						Collections.sort(requestData, new ComapareByMessageid());
					}

					for(int i = 0; i < requestData.size(); ++i) {
						ContextParams contextParams = (ContextParams)requestData.get(i);
						ServerInitializer.requestDatas.put(contextParams.getMessage_id(), contextParams);
						logger.trace("Polling Dialer Request Completed for message id : " + contextParams.getMessage_id());
					}

					logger.warn("Polling Dialer Request Completed. " + requestData.size() + " Request found.");
				}

				if (ServerInitializer.pollingInterval > (double)0.0F) {
					Thread.sleep((long)(ServerInitializer.pollingInterval * (double)100.0F));
				}
			} catch (Exception e) {
				logger.error("Unable to poll request data,", e);
			}
		}
	}
}
