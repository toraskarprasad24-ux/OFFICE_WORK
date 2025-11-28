//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.jdbc.service;

import com.concerto.sprinklr.application.init.ServerInitializer;
import com.concerto.sprinklr.application.req.bean.ContextParams;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.ucp.UniversalConnectionPoolException;
import oracle.ucp.jdbc.ValidConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalRequestDataService {
	static Logger logger = LoggerFactory.getLogger("AppLogger");

	public ExternalRequestDataService() {
	}

	public ArrayList<ContextParams> getAllExternalRequests() {
		ArrayList<ContextParams> messageData = new ArrayList();
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		Connection connection = null;

		try {
			connection = ServerInitializer.connection;
			connection.setAutoCommit(true);
			callableStatement = connection.prepareCall("{call " + ServerInitializer.pollingProcedureName + " (?)}");
			callableStatement.registerOutParameter(1, -10);
			callableStatement.execute();
			rs = (ResultSet)callableStatement.getObject(1);
			if (rs != null) {
				while(rs.next()) {
					ContextParams contextParam = new ContextParams();
					contextParam.setRule_id(rs.getString("SHOST_DATA").trim());
					contextParam.setRule_name(rs.getString("SNOTE").trim());
					contextParam.setMessage_id(rs.getString("MESSAGE_ID").trim());
					contextParam.setPortfolio_id(rs.getString("PORTFOLIO_ID").trim());
					contextParam.setAction_level_id(rs.getString("ACTION_LEVEL_ID").trim());
					contextParam.setAction(rs.getString("ACTION").trim());
					contextParam.setMobile_no(rs.getString("SM_USER_MOB_PHN").trim());
					contextParam.setRecord_source_id(rs.getString("ND_RECORD_SOURCE_ID").trim());
					contextParam.setSm_user_home_phn(rs.getString("SM_USER_HOME_PHN").trim());
					contextParam.setSm_user_wrk_phn(rs.getString("SM_USER_WRK_PHN").trim());
					contextParam.setEmail_id(rs.getString("SM_PRI_EMAIL_ADDR").trim());
					contextParam.setInternational_travel_no(rs.getString("SM_USER_MOB_PHN").trim());
					contextParam.setCampaign_id(rs.getString("SREASON_CODE").trim());
					contextParam.setDd_partition_date(rs.getString("DD_PARTITION_DATE").trim());
					contextParam.setDd_date(rs.getString("DD_DATE").trim());
					contextParam.setSd_tiebreaker(rs.getString("SD_TIEBREAKER").trim());
					contextParam.setSd_pan(rs.getString("ACTION_LEVEL_KEY").trim());
					contextParam.setSd_retl_id(rs.getString("SD_RETL_ID").trim());
					contextParam.setSd_fiid(rs.getString("SD_FIID").trim());
					contextParam.setSd_uan(rs.getString("SD_UAN").trim());
					contextParam.setSd_term_id(rs.getString("SD_TERM_ID").trim());
					contextParam.setSd_prm_message_typ(rs.getString("SD_PRM_MESSAGE_TYP").trim());
					contextParam.setSd_prod_ind(rs.getString("SD_PROD_IND").trim());
					contextParam.setSd_seq_num(rs.getString("SD_SEQ_NUM").trim());
					contextParam.setSd_tran_cde(rs.getString("SD_TRAN_CDE").trim());
					contextParam.setMd_tran_amt1(rs.getString("MD_TRAN_AMT1").trim());
					contextParam.setSd_msg_typ(rs.getString("SD_MSG_TYP").trim());
					contextParam.setSd_resp_cde(rs.getString("SD_RESP_CDE").trim());
					contextParam.setSd_pt_srv_cond_cde(rs.getString("SD_PT_SRV_COND_CDE").trim());
					contextParam.setSd_pt_srv_entry_mde(rs.getString("SD_PT_SRV_ENTRY_MDE").trim());
					contextParam.setSd_emv_user_filler(rs.getString("SD_EMV_USER_FILLER").trim());
					contextParam.setSd_track1_2_ind(rs.getString("SD_TRACK1_2_IND").trim());
					contextParam.setSd_retl_sic_cde(rs.getString("SD_RETL_SIC_CDE").trim());
					contextParam.setSd_term_name_loc(rs.getString("SD_TERM_NAME_LOC").trim());
					contextParam.setSd_term_city(rs.getString("SD_TERM_CITY").trim());
					contextParam.setSd_term_st(rs.getString("SD_TERM_ST").trim());
					contextParam.setSd_term_cntry(rs.getString("SD_TERM_CNTRY").trim());
					contextParam.setSd_term_postal_cde(rs.getString("SD_TERM_POSTAL_CDE").trim());
					contextParam.setSd_mbr_num(rs.getString("SD_MBR_NUM").trim());
					contextParam.setSd_pin_ind(rs.getString("SD_PIN_IND").trim());
					contextParam.setSm_cust_mstr_fld1(rs.getString("SM_CUST_MSTR_FLD1").trim());
					contextParam.setSm_user_cntry(rs.getString("SM_USER_CNTRY").trim());
					contextParam.setSm_user_postal_cde(rs.getString("SM_USER_POSTAL_CDE").trim());
					contextParam.setSm_sec_first_nam(rs.getString("SM_SEC_FIRST_NAM").trim());
					contextParam.setIGNORE_DEDUP(true);
					contextParam.setAUTO_ACTION_TYPE_ID(rs.getString("ACTION_TYPE_ID").trim());
					contextParam.setA_L_K(rs.getString("A_L_K").trim());
					messageData.add(contextParam);
				}
			}
		} catch (SQLException e) {
			try {
				if (connection == null || !((ValidConnection)connection).isValid()) {
					ServerInitializer.mgr.refreshConnectionPool(ServerInitializer.dbPoolName);
					ServerInitializer.connection = ServerInitializer.pds.getConnection();
					logger.warn("Universal Connection Pool : " + ServerInitializer.dbPoolName + " refreshed.");
				}
			} catch (SQLException e1) {
				logger.error("SQLException occured while validating DB connection : ", e1);
			} catch (UniversalConnectionPoolException e2) {
				logger.error("UniversalConnectionPoolException occured while refreshing connection pool : ", e2);
			}

			logger.error("Problem when getting External Request Data from EMM,", e);
		} catch (Exception e) {
			logger.error("Problem when getting External Request Data from EMM,", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (callableStatement != null) {
					callableStatement.close();
				}
			} catch (Exception e) {
				logger.error("Unable to close connections,", e);
			}

		}

		return messageData;
	}

	public void updateStatus(String recordSourceID, String sdTiebreaker, String ddDate, String autoAction, int action, String dialerMessage, String messageId, String action_level_id, String dd_partition_date, String AUTO_ACTION_TYPE_ID, String actionLevelkey) {
		CallableStatement stmt = null;
		Connection connection = null;
		StringBuilder traceString = null;

		try {
			if (logger.isTraceEnabled()) {
				traceString = new StringBuilder();
				traceString.append(ServerInitializer.updateStatusProcedureName).append("[").append(recordSourceID).append(",").append(sdTiebreaker).append(",").append(ddDate).append(",").append(autoAction).append(",").append(action).append(",").append(dialerMessage).append(",").append(messageId).append(",").append(action_level_id).append(",").append(dd_partition_date).append(",").append(AUTO_ACTION_TYPE_ID).append(",").append(actionLevelkey).append("]");
			}

			logger.trace(ServerInitializer.pds.getAvailableConnectionsCount() + " available connections while executing SP : " + traceString + " for message id : " + messageId);
			connection = ServerInitializer.pds.getConnection();
			connection.setAutoCommit(true);
			stmt = connection.prepareCall("{call " + ServerInitializer.updateStatusProcedureName + "(?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.setInt(1, Integer.parseInt(recordSourceID));
			stmt.setString(2, sdTiebreaker);
			stmt.setString(3, ddDate);
			stmt.setString(4, autoAction);
			stmt.setInt(5, action);
			stmt.setString(6, dialerMessage);
			stmt.setInt(7, Integer.parseInt(messageId));
			stmt.setInt(8, Integer.parseInt(action_level_id));
			stmt.setString(9, dd_partition_date);
			stmt.setInt(10, Integer.parseInt(AUTO_ACTION_TYPE_ID));
			stmt.setString(11, actionLevelkey);
			logger.trace("Executing stored procedure : " + ServerInitializer.updateStatusProcedureName + " for updating STATUS for message id : " + messageId);
			stmt.executeUpdate();
			logger.trace("Execution completed for stored procedure : " + ServerInitializer.updateStatusProcedureName + " for updating STATUS for message id : " + messageId);
		} catch (SQLException e) {
			try {
				if (connection == null || !((ValidConnection)connection).isValid()) {
					ServerInitializer.mgr.refreshConnectionPool(ServerInitializer.dbPoolName);
					logger.warn("Universal Connection Pool : " + ServerInitializer.dbPoolName + " refreshed.");
				}
			} catch (SQLException e1) {
				logger.error("SQLException occured while validating DB connection,", e1);
			} catch (UniversalConnectionPoolException e2) {
				logger.error("UniversalConnectionPoolException occured while refreshing connection pool,", e2);
			}

			logger.error("Problem occured while updating STATUS in EMM for message id  : " + messageId + ",", e);
		} catch (Exception e) {
			logger.error("Problem occured while updating STATUS in EMM for message id  : " + messageId + ",", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e1) {
					logger.error("Unable to close connection for message id  : " + messageId + ",", e1);
				}

				Connection var39 = null;
			}

			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				logger.error("Problem occured while closing connections for message id : " + messageId + ",", e);
			}

		}

	}

	public void updateAck(String recordSourceID, String sdTiebreaker, String ddDate, String autoAction, int action, String dialerMessage, String messageId, String action_level_id, String dd_partition_date, String AUTO_ACTION_TYPE_ID, String actionLevelkey) {
		CallableStatement stmt = null;
		Connection connection = null;
		StringBuilder traceString = null;

		try {
			if (logger.isTraceEnabled()) {
				traceString = new StringBuilder();
				traceString.append(ServerInitializer.updateStatusProcedureName).append("[").append(recordSourceID).append(",").append(sdTiebreaker).append(",").append(ddDate).append(",").append(autoAction).append(",").append(action).append(",").append(dialerMessage).append(",").append(messageId).append(",").append(action_level_id).append(",").append(dd_partition_date).append(",").append(AUTO_ACTION_TYPE_ID).append(",").append(actionLevelkey).append("]");
			}

			logger.trace(ServerInitializer.pds.getAvailableConnectionsCount() + " available connections while executing SP : " + traceString + " for message id : " + messageId);
			connection = ServerInitializer.pds.getConnection();
			connection.setAutoCommit(true);
			stmt = connection.prepareCall("{call " + ServerInitializer.updateStatusProcedureName + "(?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.setInt(1, Integer.parseInt(recordSourceID));
			stmt.setString(2, sdTiebreaker);
			stmt.setString(3, ddDate);
			stmt.setString(4, autoAction);
			stmt.setInt(5, action);
			stmt.setString(6, dialerMessage);
			stmt.setInt(7, Integer.parseInt(messageId));
			stmt.setInt(8, Integer.parseInt(action_level_id));
			stmt.setString(9, dd_partition_date);
			stmt.setInt(10, Integer.parseInt(AUTO_ACTION_TYPE_ID));
			stmt.setString(11, actionLevelkey);
			logger.trace("Executing stored procedure : " + ServerInitializer.updateStatusProcedureName + " for updating acknowledgement for message id : " + messageId);
			stmt.executeUpdate();
			logger.trace("Execution completed for stored procedure : " + ServerInitializer.updateStatusProcedureName + " for updating acknowledgement for message id : " + messageId);
		} catch (SQLException e) {
			try {
				if (connection == null || !((ValidConnection)connection).isValid()) {
					ServerInitializer.mgr.refreshConnectionPool(ServerInitializer.dbPoolName);
					logger.warn("Universal Connection Pool :- " + ServerInitializer.dbPoolName + " refreshed.");
				}
			} catch (SQLException e1) {
				logger.error("SQLException occured while validating DB connection,", e1);
			} catch (UniversalConnectionPoolException e2) {
				logger.error("UniversalConnectionPoolException occured while refreshing connection pool,", e2);
			}

			logger.error("Problem occured while updating acknowledgement in EMM for message id : " + messageId + ",", e);
		} catch (Exception e) {
			logger.error("Problem occured while updating acknowledgement in EMM for message id : " + messageId + ",", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e1) {
					logger.error("Unable to close connection for message id : " + messageId + ",", e1);
				}

				Connection var39 = null;
			}

			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				logger.error("Problem occured while closing connections for message id : " + messageId + ",", e);
			}

		}

	}

	public void updatePendingRequest() {
		CallableStatement stmt = null;
		Connection connection = null;

		try {
			logger.trace(ServerInitializer.pds.getAvailableConnectionsCount() + " available connections while executing SP : " + ServerInitializer.updatePendingProcedureName);
			connection = ServerInitializer.pds.getConnection();
			stmt = connection.prepareCall("{call " + ServerInitializer.updatePendingProcedureName + "}");
			logger.trace("Executing stored procedure : " + ServerInitializer.updatePendingProcedureName);
			stmt.executeUpdate();
			logger.trace("Execution completed for stored procedure : " + ServerInitializer.updatePendingProcedureName);
		} catch (SQLException e) {
			try {
				if (connection == null || !((ValidConnection)connection).isValid()) {
					ServerInitializer.mgr.refreshConnectionPool(ServerInitializer.dbPoolName);
				}
			} catch (SQLException e1) {
				logger.error("SQLException occured while validating DB connection,", e1);
			} catch (UniversalConnectionPoolException e2) {
				logger.error("UniversalConnectionPoolException occured while refreshing connection pool,", e2);
			}

			logger.error("Problem occured while updating pending requests,", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Problem occured while updating pending requests,", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}

				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e1) {
						logger.error("Unable to close connection,", e1);
					}

					Connection var27 = null;
				}
			} catch (Exception e) {
				logger.error("Problem occured while closing connections,", e);
			}

		}

	}
}
