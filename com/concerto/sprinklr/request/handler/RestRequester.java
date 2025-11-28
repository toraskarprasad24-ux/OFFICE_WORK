//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.request.handler;

import com.concerto.sprinklr.application.init.ServerInitializer;
import com.concerto.sprinklr.application.util.MaskSensitiveFields;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

public class RestRequester {
	static Logger logger = LoggerFactory.getLogger("AppLogger");

	public RestRequester() {
	}

	public String postData(String externalRequest, String maskExternalRequest, String messageId) {
		String line = "";
		if (ServerInitializer.reqLogMaskingEnable == 1) {
			logger.info("Masked Request for message id : " + messageId + " -->" + maskExternalRequest);
		} else {
			logger.info("Actual Request for message id : " + messageId + " -->" + externalRequest);
		}

		HttpComponentsClientHttpRequestFactory con = new HttpComponentsClientHttpRequestFactory();
		RestTemplate restTemplate = new RestTemplate(con);

		String var40;
		try {
			if (ServerInitializer.CertificateBypassEnable == 1) {
				TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;
				SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((KeyStore)null, acceptingTrustStrategy).build();
				SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
				CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
				con.setHttpClient(httpClient);
			}

			con.setConnectTimeout(ServerInitializer.connectionTimeOut);
			con.setReadTimeout(ServerInitializer.readTimeOut);
			HttpHeaders headers = new HttpHeaders();
			Map<String, String> map = new HashMap();
			String[] entries = ServerInitializer.requestHeaders.split(ServerInitializer.headerSeparator);

			for(String entry : entries) {
				String[] keyValue = entry.split(":::");
				map.put(keyValue[0], keyValue[1]);
			}

			for(Map.Entry<String, String> entry1 : map.entrySet()) {
				headers.set((String)entry1.getKey(), (String)entry1.getValue());
			}

			HttpEntity<String> entity = new HttpEntity(externalRequest, headers);
			logger.trace("Request Sent to API for message id : " + messageId);
			ResponseEntity<String> response = restTemplate.postForEntity(ServerInitializer.requestURL, entity, String.class, new Object[0]);
			logger.trace("Response received from API for message id : " + messageId);
			if (response.getStatusCodeValue() == 200) {
				line = response.getStatusCodeValue() + (String)response.getBody();
				if (ServerInitializer.resLogMaskingEnable == 1) {
					if (!ServerInitializer.responseSensitiveFields.isEmpty()) {
						logger.info("Masked acknowledgement for message id : " + messageId + " --> " + MaskSensitiveFields.getKeysAndPosition(line.substring(line.indexOf("{"), line.length()).replace("\n", ""), messageId, ServerInitializer.responseSensitiveFieldsList));
					} else {
						logger.info("Actual acknowledgement for message id : " + messageId + " --> " + line.replace("\n", ""));
					}

					return line;
				} else {
					logger.info("Actual acknowledgement for message id : " + messageId + " --> " + line.replace("\n", ""));
					return line;
				}
			}

			logger.error("Failed : HTTP error code : " + response.getStatusCodeValue());
			var40 = "Failed : HTTP error code : " + response.getStatusCodeValue();
			return var40;
		} catch (ResourceAccessException e) {
			logger.error("Connection Exception has occured for message id : " + messageId + ",", e);
			e.printStackTrace();
			var40 = e.getMessage();
		} catch (Exception e) {
			logger.error("Exception has occured for message id : " + messageId + ",", e);
			e.printStackTrace();
			var40 = e.getMessage();
			return var40;
		} finally {
			if (con != null) {
				try {
					con.destroy();
				} catch (Exception e) {
					logger.warn("Unable to close connection for message id : " + messageId + ",", e);
				}

				HttpComponentsClientHttpRequestFactory var31 = null;
			}

		}

		return var40;
	}
}
