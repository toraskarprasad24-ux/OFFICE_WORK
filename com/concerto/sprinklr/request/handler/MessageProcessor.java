//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.request.handler;

import com.concerto.sprinklr.application.init.ServerInitializer;
import com.concerto.sprinklr.application.req.bean.ContextParams;
import com.concerto.sprinklr.application.req.bean.ExternalRequest;
import com.concerto.sprinklr.application.req.bean.Profile;
import com.concerto.sprinklr.application.req.bean.RequestString;
import com.concerto.sprinklr.application.req.bean.SessionContext;
import com.concerto.sprinklr.application.req.bean.SprinklrJourneyFacilitatorRequestDTO;
import com.concerto.sprinklr.application.req.bean.UnifiedProfile;
import com.concerto.sprinklr.application.util.GenerateRandomNumber;
import com.concerto.sprinklr.application.util.MaskSensitiveFields;
import com.concerto.sprinklr.jdbc.service.ExternalRequestDataService;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProcessor implements Runnable {
	static Logger logger = LoggerFactory.getLogger("AppLogger");
	private ExternalRequestDataService dataService = new ExternalRequestDataService();
	private ExternalRequest extRqst = new ExternalRequest();
	private SprinklrJourneyFacilitatorRequestDTO dto = new SprinklrJourneyFacilitatorRequestDTO();
	private SessionContext sessionContext = new SessionContext();
	private ContextParams contextParam = new ContextParams();
	private RequestString requestString = new RequestString();
	private UnifiedProfile UnifiedProfile = new UnifiedProfile();
	private Profile profile = new Profile();
	private List<Profile> profiles = new ArrayList();
	JSONParser parser = new JSONParser();
	ObjectMapper mapperObj = new ObjectMapper();
	String responseMsg = null;

	public MessageProcessor(ContextParams contextParam) {
		this.contextParam = contextParam;
	}

	public void run() {
		this.startProcess();
	}

	public void startProcess() {
		try {
			if (this.contextParam.getMobile_no() != null && !this.contextParam.getMobile_no().isEmpty() && !this.contextParam.getMobile_no().equals("") && !this.contextParam.getMobile_no().equalsIgnoreCase("NA")) {
				if (this.contextParam.getMobile_no().length() >= 10 && this.contextParam.getMobile_no().length() <= 12 && this.contextParam.getMobile_no().matches("[0-9]+") && !this.contextParam.getMobile_no().matches("^([0-9])\\1*$")) {
					this.profile.setChannelId(this.contextParam.getMobile_no().trim());
					this.profiles.add(this.profile);
					this.UnifiedProfile.setContact(ServerInitializer.contact);
					this.profile.setChannelType(ServerInitializer.channelType);
					this.profile.setName(ServerInitializer.name);
					this.UnifiedProfile.setProfiles(this.profiles);
					this.UnifiedProfile.setCreatedTime(System.currentTimeMillis() / 1000L);
					this.requestString.setJourneyId(ServerInitializer.journeyId);
					this.requestString.setContextParams(this.contextParam);
					this.requestString.setUnifiedProfile(this.UnifiedProfile);
					this.dto.setRequestString(this.requestString);
					this.dto.setHeaderParams(ServerInitializer.headerParams);
					this.sessionContext.setChannel(ServerInitializer.channel);
					this.sessionContext.setBankCode(ServerInitializer.bankCode);
					this.sessionContext.setUserId(ServerInitializer.userId);
					this.sessionContext.setTransactionBranch(ServerInitializer.transactionBranch);
					this.sessionContext.setTransactingPartyCode(ServerInitializer.transactingPartyCode);
					this.sessionContext.setExternalReferenceNo(String.valueOf(GenerateRandomNumber.generateRandomString(ServerInitializer.maxRandomNumber)));
					this.extRqst.setSprinklrJourneyFacilitatorRequestDTO(this.dto);
					this.extRqst.setSessionContext(this.sessionContext);
					String externalRequest = this.mapperObj.writeValueAsString(this.extRqst);
					String maskExternalRequest = null;
					if (ServerInitializer.reqLogMaskingEnable == 1 && !ServerInitializer.requestSensitiveFields.isEmpty()) {
						maskExternalRequest = MaskSensitiveFields.getKeysAndPosition(externalRequest, this.contextParam.getMessage_id(), ServerInitializer.requestSensitiveFieldsList);
					}

					this.dataService.updateStatus(this.contextParam.getRecord_source_id(), this.contextParam.getSd_tiebreaker(), this.contextParam.getDd_date(), this.contextParam.getAction(), 3, (String)null, this.contextParam.getMessage_id(), this.contextParam.getAction_level_id(), this.contextParam.getDd_partition_date(), this.contextParam.getAUTO_ACTION_TYPE_ID(), this.contextParam.getA_L_K());
					if (ServerInitializer.requestProcessingMethod.equals("HTTP_REQUESTER")) {
						HTTPRequester req = new HTTPRequester();
						this.responseMsg = req.postData(externalRequest, maskExternalRequest, this.contextParam.getMessage_id());
					} else if (ServerInitializer.requestProcessingMethod.equals("REST_REQUESTER")) {
						RestRequester req = new RestRequester();
						this.responseMsg = req.postData(externalRequest, maskExternalRequest, this.contextParam.getMessage_id());
					} else {
						logger.error("Please Configure Valid REQUESTER in configuration file.");
					}

					if (this.responseMsg != null && this.responseMsg.startsWith("200")) {
						JSONObject response = (JSONObject)this.parser.parse(this.responseMsg.substring(this.responseMsg.indexOf("{"), this.responseMsg.length()));
						JSONObject resMsg = (JSONObject)response.get("status");
						if (resMsg.get("errorCode").toString().equals("0")) {
							JSONObject responseString = (JSONObject)response.get("responseString");
							if (responseString != null) {
								Object data = responseString.get("data");
								Object error = responseString.get("errors");
								JSONObject dataObject = null;
								JSONArray errorArray = null;
								if (data instanceof JSONObject) {
									dataObject = (JSONObject)data;
									if (!dataObject.get("status").toString().equalsIgnoreCase("SUCCESS")) {
										logger.error("Failed data status in acknowledgement for message id : " + this.contextParam.getMessage_id() + "," + dataObject.toString());
										this.dataService.updateAck(this.contextParam.getRecord_source_id(), this.contextParam.getSd_tiebreaker(), this.contextParam.getDd_date(), this.contextParam.getAction(), 4, dataObject.get("message").toString(), this.contextParam.getMessage_id(), this.contextParam.getAction_level_id(), this.contextParam.getDd_partition_date(), this.contextParam.getAUTO_ACTION_TYPE_ID(), this.contextParam.getA_L_K());
									}
								} else if (data instanceof JSONArray) {
									errorArray = (JSONArray)error;
									if (!errorArray.isEmpty()) {
										JSONObject errorObject = null;

										for(int i = 0; i < errorArray.length(); ++i) {
											errorObject = (JSONObject)errorArray.get(i);
											logger.error("Error in acknowledgement for message id : " + this.contextParam.getMessage_id() + "," + errorObject.toString());
										}

										this.dataService.updateAck(this.contextParam.getRecord_source_id(), this.contextParam.getSd_tiebreaker(), this.contextParam.getDd_date(), this.contextParam.getAction(), 4, errorObject.get("message").toString(), this.contextParam.getMessage_id(), this.contextParam.getAction_level_id(), this.contextParam.getDd_partition_date(), this.contextParam.getAUTO_ACTION_TYPE_ID(), this.contextParam.getA_L_K());
									}
								} else {
									errorArray = (JSONArray)error;
									if (!errorArray.isEmpty()) {
										JSONObject errorObject = null;

										for(int i = 0; i < errorArray.length(); ++i) {
											errorObject = (JSONObject)errorArray.get(i);
											logger.error("Error in acknowledgement for message id : " + this.contextParam.getMessage_id() + "," + errorObject.toString());
										}

										this.dataService.updateAck(this.contextParam.getRecord_source_id(), this.contextParam.getSd_tiebreaker(), this.contextParam.getDd_date(), this.contextParam.getAction(), 4, errorObject.get("message").toString(), this.contextParam.getMessage_id(), this.contextParam.getAction_level_id(), this.contextParam.getDd_partition_date(), this.contextParam.getAUTO_ACTION_TYPE_ID(), this.contextParam.getA_L_K());
									}
								}
							} else {
								logger.error("Invalid response received for message id : " + this.contextParam.getMessage_id() + "," + this.responseMsg);
								this.dataService.updateAck(this.contextParam.getRecord_source_id(), this.contextParam.getSd_tiebreaker(), this.contextParam.getDd_date(), this.contextParam.getAction(), 2, resMsg.get("replyText").toString(), this.contextParam.getMessage_id(), this.contextParam.getAction_level_id(), this.contextParam.getDd_partition_date(), this.contextParam.getAUTO_ACTION_TYPE_ID(), this.contextParam.getA_L_K());
							}
						} else {
							logger.error("Invalid response received for message id : " + this.contextParam.getMessage_id() + "," + this.responseMsg);
							this.dataService.updateAck(this.contextParam.getRecord_source_id(), this.contextParam.getSd_tiebreaker(), this.contextParam.getDd_date(), this.contextParam.getAction(), 2, resMsg.get("replyText").toString(), this.contextParam.getMessage_id(), this.contextParam.getAction_level_id(), this.contextParam.getDd_partition_date(), this.contextParam.getAUTO_ACTION_TYPE_ID(), this.contextParam.getA_L_K());
						}
					} else {
						this.dataService.updateAck(this.contextParam.getRecord_source_id(), this.contextParam.getSd_tiebreaker(), this.contextParam.getDd_date(), this.contextParam.getAction(), 2, this.responseMsg, this.contextParam.getMessage_id(), this.contextParam.getAction_level_id(), this.contextParam.getDd_partition_date(), this.contextParam.getAUTO_ACTION_TYPE_ID(), this.contextParam.getA_L_K());
					}
				} else {
					this.dataService.updateStatus(this.contextParam.getRecord_source_id(), this.contextParam.getSd_tiebreaker(), this.contextParam.getDd_date(), this.contextParam.getAction(), 5, "[*INVALID MOBILE NUMBER CANNOT SEND TO SPRINKLR*]", this.contextParam.getMessage_id(), this.contextParam.getAction_level_id(), this.contextParam.getDd_partition_date(), this.contextParam.getAUTO_ACTION_TYPE_ID(), this.contextParam.getA_L_K());
				}
			} else {
				this.dataService.updateStatus(this.contextParam.getRecord_source_id(), this.contextParam.getSd_tiebreaker(), this.contextParam.getDd_date(), this.contextParam.getAction(), 5, "[*MOBILE NO. NOT PRESENT CANNOT SEND TO SPRINKLR*]", this.contextParam.getMessage_id(), this.contextParam.getAction_level_id(), this.contextParam.getDd_partition_date(), this.contextParam.getAUTO_ACTION_TYPE_ID(), this.contextParam.getA_L_K());
			}
		} catch (Exception e) {
			logger.error("Exception occured for message id : " + this.contextParam.getMessage_id() + ",", e);
			this.dataService.updateAck(this.contextParam.getRecord_source_id(), this.contextParam.getSd_tiebreaker(), this.contextParam.getDd_date(), this.contextParam.getAction(), 2, this.responseMsg, this.contextParam.getMessage_id(), this.contextParam.getAction_level_id(), this.contextParam.getDd_partition_date(), this.contextParam.getAUTO_ACTION_TYPE_ID(), this.contextParam.getA_L_K());
		}

	}
}
