//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.init;

import com.concerto.sprinklr.application.req.bean.Contact;
import com.concerto.sprinklr.application.req.bean.ContextParams;
import com.concerto.sprinklr.application.req.bean.HeaderParam;
import com.concerto.sprinklr.application.util.LogWatcher;
import com.concerto.sprinklr.crypto.PasswordDecryptor;
import com.concerto.sprinklr.jdbc.service.DbProperty;
import com.concerto.sprinklr.jdbc.service.ExternalRequestDataService;
import com.concerto.sprinklr.jdbc.service.UcpConnection;
import com.concerto.sprinklr.request.handler.MultiProcessing;
import com.concerto.sprinklr.request.handler.RequestPolling;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import oracle.ucp.UniversalConnectionPoolAdapter;
import oracle.ucp.admin.UniversalConnectionPoolManager;
import oracle.ucp.admin.UniversalConnectionPoolManagerImpl;
import oracle.ucp.jdbc.PoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerInitializer {
	public static Logger logger = LoggerFactory.getLogger("AppLogger");
	private static Properties appConfigProps = new Properties();
	private static DbProperty dbProperty;
	private static ExternalRequestDataService dataService = new ExternalRequestDataService();
	public static Map<String, ContextParams> requestDatas = new ConcurrentHashMap();
	public static int parallelProcessingCount;
	public static String pollingProcedureName;
	public static String updateStatusProcedureName;
	public static String updatePendingProcedureName;
	public static String requestURL;
	public static String databaseDateFormat;
	public static String requestProcessingMethod;
	public static String requestHeaders;
	public static String headerSeparator;
	public static int reqsortenable;
	public static int reqsorttype;
	public static String httprequestMethod;
	public static double pollingInterval;
	public static double processingInterval;
	public static int connectionTimeOut;
	public static int readTimeOut;
	public static double fileWatchingInterval;
	public static int CertificateBypassEnable;
	public static int reqLogMaskingEnable;
	public static int resLogMaskingEnable;
	public static String masking_character;
	public static List<String> requestSensitiveFieldsList = new ArrayList();
	public static List<String> responseSensitiveFieldsList = new ArrayList();
	public static String requestSensitiveFields;
	public static String responseSensitiveFields;
	public static String fieldSeprator;
	public static String dbPoolName;
	public static String log4jConfgKey;
	public static String log4jConfgPath;
	public static String journeyId;
	public static String channelType;
	public static String firstName;
	public static String name;
	public static String authorization;
	public static String authorizationValue;
	public static String key;
	public static String keyValue;
	public static String contentType;
	public static String contentTypeValue;
	public static String bankCode;
	public static String channel;
	public static String userId;
	public static String transactionBranch;
	public static String transactingPartyCode;
	public static int maxRandomNumber;
	public static boolean ignoreDedup;
	public static Connection connection;
	public static UcpConnection ucp = new UcpConnection();
	public static PoolDataSource pds;
	public static UniversalConnectionPoolManager mgr;
	public static Contact contact = new Contact();
	private static HeaderParam authHeader = new HeaderParam();
	private static HeaderParam keyHeader = new HeaderParam();
	private static HeaderParam contentTypeHeader = new HeaderParam();
	public static List<HeaderParam> headerParams = new ArrayList();

	static {
		try {
			logger.trace("Trace logs enabled!");
			File configPath = new File(System.getProperty("user.dir") + File.separator + "Configuration" + File.separator + "config.properties".replace("/", "").replace("..", ""));
			appConfigProps.load(new FileInputStream(configPath.getCanonicalPath()));
			Set<String> propKey = appConfigProps.stringPropertyNames();
			TreeSet<String> treeSet = new TreeSet(propKey);
			String propName = (String)treeSet.iterator().next();
			parallelProcessingCount = Integer.parseInt(appConfigProps.get("parallel.request.processing.count").toString().trim());
			pollingProcedureName = appConfigProps.get("polling.procedure.name").toString().trim();
			updateStatusProcedureName = appConfigProps.get("update.status.procedure.name").toString().trim();
			updatePendingProcedureName = appConfigProps.get("update.pending.procedure.name").toString().trim();
			requestURL = appConfigProps.get("request.url").toString().trim();
			databaseDateFormat = appConfigProps.get("database.date.format").toString().trim();
			requestProcessingMethod = appConfigProps.get("request.processing.method").toString().trim();
			requestHeaders = appConfigProps.getProperty("request.header");
			headerSeparator = appConfigProps.getProperty("header.separator");
			reqsortenable = Integer.parseInt(appConfigProps.get("req.sorting.enable").toString());
			reqsorttype = Integer.parseInt(appConfigProps.get("req.sorting.type").toString());
			httprequestMethod = appConfigProps.get("http.request.method").toString().trim();
			pollingInterval = Double.parseDouble(appConfigProps.get("request.polling.interval").toString().trim());
			processingInterval = Double.parseDouble(appConfigProps.get("request.processing.interval").toString().trim());
			connectionTimeOut = Integer.parseInt(appConfigProps.get("connection.timeout").toString().trim());
			readTimeOut = Integer.parseInt(appConfigProps.get("read.timeout").toString().trim());
			fileWatchingInterval = (double)Integer.parseInt(appConfigProps.getProperty("file.watching.interval"));
			CertificateBypassEnable = Integer.parseInt(appConfigProps.get("certificate.bypass.enable").toString().trim());
			reqLogMaskingEnable = Integer.parseInt(appConfigProps.get("req.log.masking.enable").toString().trim());
			resLogMaskingEnable = Integer.parseInt(appConfigProps.get("res.log.masking.enable").toString().trim());
			masking_character = appConfigProps.get("Masking.character").toString().trim();
			requestSensitiveFields = appConfigProps.get("request.sensitive.fields").toString().trim();
			responseSensitiveFields = appConfigProps.get("response.sensitive.fields").toString().trim();
			fieldSeprator = appConfigProps.get("field.separator").toString().trim();
			maxRandomNumber = Integer.parseInt(appConfigProps.get("max.random.number").toString().trim());
			ignoreDedup = Boolean.parseBoolean(appConfigProps.get("ignore.dedup").toString().trim());
			if (!requestSensitiveFields.isEmpty()) {
				requestSensitiveFieldsList = Arrays.asList(requestSensitiveFields.trim().split(","));
			}

			if (!responseSensitiveFields.isEmpty()) {
				responseSensitiveFieldsList = Arrays.asList(responseSensitiveFields.trim().split(","));
			}

			dbPoolName = appConfigProps.get("dbPoolName").toString().trim();
			log4jConfgKey = appConfigProps.get("log4j2.config.key").toString().trim();
			log4jConfgPath = System.getProperty(log4jConfgKey);
			journeyId = appConfigProps.get("journeyId").toString().trim();
			channelType = appConfigProps.get("channelType").toString().trim();
			firstName = appConfigProps.get("firstName").toString().trim();
			name = appConfigProps.get("name").toString().trim();
			authorization = appConfigProps.get("Authorization").toString().trim();
			authorizationValue = appConfigProps.get("Authorization.value").toString().trim();
			key = appConfigProps.get("key").toString().trim();
			keyValue = appConfigProps.get("key_value").toString().trim();
			contentType = appConfigProps.get("contenttype").toString().trim();
			contentTypeValue = appConfigProps.get("contenttype.value").toString().trim();
			bankCode = appConfigProps.get("bankCode").toString().trim();
			channel = appConfigProps.get("channel").toString().trim();
			userId = appConfigProps.get("userId").toString().trim();
			transactionBranch = appConfigProps.get("transactionBranch").toString().trim();
			transactingPartyCode = appConfigProps.get("transactingPartyCode").toString().trim();
			dbProperty = new DbProperty(appConfigProps.get("database.connection.driver").toString().replace("\n", "").replace("\r", ""), appConfigProps.get("1database.connection.username").toString().replace("\n", "").replace("\r", ""), getPasswordDecryptor(propName.substring(1)).decrypt(appConfigProps.get("database.connection.password").toString()), appConfigProps.get("database.connection.url").toString().replace("\n", "").replace("\r", ""));
			pds = ucp.createDataSource();
			mgr = UniversalConnectionPoolManagerImpl.getUniversalConnectionPoolManager();
			mgr.createConnectionPool((UniversalConnectionPoolAdapter)pds);
			mgr.startConnectionPool(dbPoolName);
			logger.trace("Available connection in " + dbPoolName + " : " + pds.getAvailableConnectionsCount());
			connection = pds.getConnection();
			authHeader.setKey(authorization);
			authHeader.setValue(authorizationValue);
			keyHeader.setKey(key);
			keyHeader.setValue(keyValue);
			contentTypeHeader.setKey(contentType);
			contentTypeHeader.setValue(contentTypeValue);
			headerParams.add(authHeader);
			headerParams.add(keyHeader);
			headerParams.add(contentTypeHeader);
			contact.setFirstName(firstName);
		} catch (IOException e) {
			logger.error("Application configuration property file not found,", e);
			Runtime.getRuntime().halt(0);
		} catch (NumberFormatException fne) {
			logger.error("Numeric value properties are not configured correctly in configuration file,", fne);
			Runtime.getRuntime().halt(0);
		} catch (NoSuchAlgorithmException e) {
			logger.error("Unable to decrypt password specified in the config file,", e);
			Runtime.getRuntime().halt(0);
		} catch (InvalidKeySpecException e) {
			logger.error("Unable to decrypt password specified in the config file,", e);
			Runtime.getRuntime().halt(0);
		} catch (GeneralSecurityException e) {
			logger.error("Unable to decrypt password specified in the config file,", e);
			Runtime.getRuntime().halt(0);
		} catch (SQLException e) {
			logger.error("DB connection not established,", e);

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception ex) {
				logger.error("Unable to close connections,", ex);
			}

			Runtime.getRuntime().halt(0);
		} catch (Exception e) {
			logger.error("Exception occured,", e);
			e.printStackTrace();
			Runtime.getRuntime().halt(0);
		}

	}

	public ServerInitializer() {
	}

	public static void main(String[] args) {
		logger.warn("Starting Server Please wait...");

		try {
			dataService.updatePendingRequest();
			Thread filewatching = new Thread(new LogWatcher());
			filewatching.setName("FileWatchingThread");
			filewatching.start();
			Thread.sleep(1000L);
			Thread requestPolling = new Thread(new RequestPolling());
			requestPolling.setName("RequestPollingThread");
			requestPolling.start();
			Thread.sleep(1000L);
			Thread multiProcessing = new Thread(new MultiProcessing());
			multiProcessing.setName("MultiProcessingThread");
			multiProcessing.start();
		} catch (Exception e) {
			logger.error("Exception in main,", e);
			System.exit(0);
		}

		System.out.println("Server Started...");
	}

	public static Properties getAppConfigProps() {
		return appConfigProps;
	}

	public static DbProperty getDbProperty() {
		return dbProperty;
	}

	public static PasswordDecryptor getPasswordDecryptor(String configurationId) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return PasswordDecryptor.newInstance(configurationId);
	}
}
