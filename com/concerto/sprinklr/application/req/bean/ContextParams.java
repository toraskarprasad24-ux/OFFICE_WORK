//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.req.bean;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class ContextParams {
	private String rule_id;
	private String rule_name;
	private String message_id;
	private String portfolio_id;
	private String action_level_id;
	private String action;
	private String mobile_no;
	private String record_source_id;
	private String sm_user_home_phn;
	private String sm_user_wrk_phn;
	private String email_id;
	private String international_travel_no;
	private String campaign_id;
	private String dd_partition_date;
	private String dd_date;
	private String sd_tiebreaker;
	private String sd_pan;
	private String sd_retl_id;
	private String sd_fiid;
	private String sd_uan;
	private String sd_term_id;
	private String sd_prm_message_typ;
	private String sd_prod_ind;
	private String sd_seq_num;
	private String sd_tran_cde;
	private String md_tran_amt1;
	private String sd_msg_typ;
	private String sd_resp_cde;
	private String sd_pt_srv_cond_cde;
	private String sd_pt_srv_entry_mde;
	private String sd_emv_user_filler;
	private String sd_track1_2_ind;
	private String sd_retl_sic_cde;
	private String sd_term_name_loc;
	private String sd_term_city;
	private String sd_term_st;
	private String sd_term_cntry;
	private String sd_term_postal_cde;
	private String sd_mbr_num;
	private String sd_pin_ind;
	private String sm_cust_mstr_fld1;
	private String sm_user_cntry;
	private String sm_user_postal_cde;
	private String sm_sec_first_nam;
	private boolean IGNORE_DEDUP;
	@JsonIgnore
	private String dialerMessage;
	@JsonIgnore
	private String autoAction;
	@JsonIgnore
	private String AUTO_ACTION_TYPE_ID;
	@JsonIgnore
	private String A_L_K;

	public ContextParams() {
	}

	public String getDialerMessage() {
		return this.dialerMessage;
	}

	@JsonIgnore
	public void setDialerMessage(String dialerMessage) {
		this.dialerMessage = dialerMessage;
	}

	public String getAutoAction() {
		return this.autoAction;
	}

	@JsonIgnore
	public void setAutoAction(String autoAction) {
		this.autoAction = autoAction;
	}

	public String getAUTO_ACTION_TYPE_ID() {
		return this.AUTO_ACTION_TYPE_ID;
	}

	@JsonIgnore
	public void setAUTO_ACTION_TYPE_ID(String aUTO_ACTION_TYPE_ID) {
		this.AUTO_ACTION_TYPE_ID = aUTO_ACTION_TYPE_ID;
	}

	@JsonIgnore
	public String getA_L_K() {
		return this.A_L_K;
	}

	@JsonIgnore
	public void setA_L_K(String a_L_K) {
		this.A_L_K = a_L_K;
	}

	public String getRule_id() {
		return this.rule_id;
	}

	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}

	public String getRule_name() {
		return this.rule_name;
	}

	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
	}

	public String getMessage_id() {
		return this.message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getPortfolio_id() {
		return this.portfolio_id;
	}

	public void setPortfolio_id(String portfolio_id) {
		this.portfolio_id = portfolio_id;
	}

	public String getAction_level_id() {
		return this.action_level_id;
	}

	public void setAction_level_id(String action_level_id) {
		this.action_level_id = action_level_id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMobile_no() {
		return this.mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getRecord_source_id() {
		return this.record_source_id;
	}

	public void setRecord_source_id(String record_source_id) {
		this.record_source_id = record_source_id;
	}

	public String getSm_user_home_phn() {
		return this.sm_user_home_phn;
	}

	public void setSm_user_home_phn(String sm_user_home_phn) {
		this.sm_user_home_phn = sm_user_home_phn;
	}

	public String getSm_user_wrk_phn() {
		return this.sm_user_wrk_phn;
	}

	public void setSm_user_wrk_phn(String sm_user_wrk_phn) {
		this.sm_user_wrk_phn = sm_user_wrk_phn;
	}

	public String getEmail_id() {
		return this.email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getInternational_travel_no() {
		return this.international_travel_no;
	}

	public void setInternational_travel_no(String international_travel_no) {
		this.international_travel_no = international_travel_no;
	}

	public String getCampaign_id() {
		return this.campaign_id;
	}

	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getDd_partition_date() {
		return this.dd_partition_date;
	}

	public void setDd_partition_date(String dd_partition_date) {
		this.dd_partition_date = dd_partition_date;
	}

	public String getDd_date() {
		return this.dd_date;
	}

	public void setDd_date(String dd_date) {
		this.dd_date = dd_date;
	}

	public String getSd_tiebreaker() {
		return this.sd_tiebreaker;
	}

	public void setSd_tiebreaker(String sd_tiebreaker) {
		this.sd_tiebreaker = sd_tiebreaker;
	}

	public String getSd_pan() {
		return this.sd_pan;
	}

	public void setSd_pan(String sd_pan) {
		this.sd_pan = sd_pan;
	}

	public String getSd_retl_id() {
		return this.sd_retl_id;
	}

	public void setSd_retl_id(String sd_retl_id) {
		this.sd_retl_id = sd_retl_id;
	}

	public String getSd_fiid() {
		return this.sd_fiid;
	}

	public void setSd_fiid(String sd_fiid) {
		this.sd_fiid = sd_fiid;
	}

	public String getSd_uan() {
		return this.sd_uan;
	}

	public void setSd_uan(String sd_uan) {
		this.sd_uan = sd_uan;
	}

	public String getSd_term_id() {
		return this.sd_term_id;
	}

	public void setSd_term_id(String sd_term_id) {
		this.sd_term_id = sd_term_id;
	}

	public String getSd_prm_message_typ() {
		return this.sd_prm_message_typ;
	}

	public void setSd_prm_message_typ(String sd_prm_message_typ) {
		this.sd_prm_message_typ = sd_prm_message_typ;
	}

	public String getSd_prod_ind() {
		return this.sd_prod_ind;
	}

	public void setSd_prod_ind(String sd_prod_ind) {
		this.sd_prod_ind = sd_prod_ind;
	}

	public String getSd_seq_num() {
		return this.sd_seq_num;
	}

	public void setSd_seq_num(String sd_seq_num) {
		this.sd_seq_num = sd_seq_num;
	}

	public String getSd_tran_cde() {
		return this.sd_tran_cde;
	}

	public void setSd_tran_cde(String sd_tran_cde) {
		this.sd_tran_cde = sd_tran_cde;
	}

	public String getMd_tran_amt1() {
		return this.md_tran_amt1;
	}

	public void setMd_tran_amt1(String md_tran_amt1) {
		this.md_tran_amt1 = md_tran_amt1;
	}

	public String getSd_msg_typ() {
		return this.sd_msg_typ;
	}

	public void setSd_msg_typ(String sd_msg_typ) {
		this.sd_msg_typ = sd_msg_typ;
	}

	public String getSd_resp_cde() {
		return this.sd_resp_cde;
	}

	public void setSd_resp_cde(String sd_resp_cde) {
		this.sd_resp_cde = sd_resp_cde;
	}

	public String getSd_pt_srv_cond_cde() {
		return this.sd_pt_srv_cond_cde;
	}

	public void setSd_pt_srv_cond_cde(String sd_pt_srv_cond_cde) {
		this.sd_pt_srv_cond_cde = sd_pt_srv_cond_cde;
	}

	public String getSd_pt_srv_entry_mde() {
		return this.sd_pt_srv_entry_mde;
	}

	public void setSd_pt_srv_entry_mde(String sd_pt_srv_entry_mde) {
		this.sd_pt_srv_entry_mde = sd_pt_srv_entry_mde;
	}

	public String getSd_emv_user_filler() {
		return this.sd_emv_user_filler;
	}

	public void setSd_emv_user_filler(String sd_emv_user_filler) {
		this.sd_emv_user_filler = sd_emv_user_filler;
	}

	public String getSd_track1_2_ind() {
		return this.sd_track1_2_ind;
	}

	public void setSd_track1_2_ind(String sd_track1_2_ind) {
		this.sd_track1_2_ind = sd_track1_2_ind;
	}

	public String getSd_retl_sic_cde() {
		return this.sd_retl_sic_cde;
	}

	public void setSd_retl_sic_cde(String sd_retl_sic_cde) {
		this.sd_retl_sic_cde = sd_retl_sic_cde;
	}

	public String getSd_term_name_loc() {
		return this.sd_term_name_loc;
	}

	public void setSd_term_name_loc(String sd_term_name_loc) {
		this.sd_term_name_loc = sd_term_name_loc;
	}

	public String getSd_term_city() {
		return this.sd_term_city;
	}

	public void setSd_term_city(String sd_term_city) {
		this.sd_term_city = sd_term_city;
	}

	public String getSd_term_st() {
		return this.sd_term_st;
	}

	public void setSd_term_st(String sd_term_st) {
		this.sd_term_st = sd_term_st;
	}

	public String getSd_term_cntry() {
		return this.sd_term_cntry;
	}

	public void setSd_term_cntry(String sd_term_cntry) {
		this.sd_term_cntry = sd_term_cntry;
	}

	public String getSd_term_postal_cde() {
		return this.sd_term_postal_cde;
	}

	public void setSd_term_postal_cde(String sd_term_postal_cde) {
		this.sd_term_postal_cde = sd_term_postal_cde;
	}

	public String getSd_mbr_num() {
		return this.sd_mbr_num;
	}

	public void setSd_mbr_num(String sd_mbr_num) {
		this.sd_mbr_num = sd_mbr_num;
	}

	public String getSd_pin_ind() {
		return this.sd_pin_ind;
	}

	public void setSd_pin_ind(String sd_pin_ind) {
		this.sd_pin_ind = sd_pin_ind;
	}

	public String getSm_cust_mstr_fld1() {
		return this.sm_cust_mstr_fld1;
	}

	public void setSm_cust_mstr_fld1(String sm_cust_mstr_fld1) {
		this.sm_cust_mstr_fld1 = sm_cust_mstr_fld1;
	}

	public String getSm_user_cntry() {
		return this.sm_user_cntry;
	}

	public void setSm_user_cntry(String sm_user_cntry) {
		this.sm_user_cntry = sm_user_cntry;
	}

	public String getSm_user_postal_cde() {
		return this.sm_user_postal_cde;
	}

	public void setSm_user_postal_cde(String sm_user_postal_cde) {
		this.sm_user_postal_cde = sm_user_postal_cde;
	}

	public String getSm_sec_first_nam() {
		return this.sm_sec_first_nam;
	}

	public void setSm_sec_first_nam(String sm_sec_first_nam) {
		this.sm_sec_first_nam = sm_sec_first_nam;
	}

	public boolean getIGNORE_DEDUP() {
		return this.IGNORE_DEDUP;
	}

	@JsonProperty("IGNORE_DEDUP")
	public void setIGNORE_DEDUP(boolean IGNORE_DEDUP) {
		this.IGNORE_DEDUP = IGNORE_DEDUP;
	}

	public String toString() {
		return "ContextParams [rule_id=" + this.rule_id + ", rule_name=" + this.rule_name + ", message_id=" + this.message_id + ", portfolio_id=" + this.portfolio_id + ", action_level_id=" + this.action_level_id + ", action=" + this.action + ", mobile_no=" + this.mobile_no + ", record_source_id=" + this.record_source_id + ", sm_user_home_phn=" + this.sm_user_home_phn + ", sm_user_wrk_phn=" + this.sm_user_wrk_phn + ", email_id=" + this.email_id + ", international_travel_no=" + this.international_travel_no + ", camapign_id=" + this.campaign_id + ", dd_partition_date=" + this.dd_partition_date + ", dd_date=" + this.dd_date + ", sd_tiebreaker=" + this.sd_tiebreaker + ", sd_pan=" + this.sd_pan + ", sd_retl_id=" + this.sd_retl_id + ", sd_fiid=" + this.sd_fiid + ", sd_uan=" + this.sd_uan + ", sd_term_id=" + this.sd_term_id + ", sd_prm_message_typ=" + this.sd_prm_message_typ + ", sd_prod_ind=" + this.sd_prod_ind + ", sd_seq_num=" + this.sd_seq_num + ", sd_tran_cde=" + this.sd_tran_cde + ", md_tran_amt1=" + this.md_tran_amt1 + ", sd_msg_typ=" + this.sd_msg_typ + ", sd_resp_cde=" + this.sd_resp_cde + ", sd_pt_srv_cond_cde=" + this.sd_pt_srv_cond_cde + ", sd_pt_srv_entry_mde=" + this.sd_pt_srv_entry_mde + ", sd_emv_user_filler=" + this.sd_emv_user_filler + ", sd_track1_2_ind=" + this.sd_track1_2_ind + ", sd_retl_sic_cde=" + this.sd_retl_sic_cde + ", sd_term_name_loc=" + this.sd_term_name_loc + ", sd_term_city=" + this.sd_term_city + ", sd_term_st=" + this.sd_term_st + ", sd_term_cntry=" + this.sd_term_cntry + ", sd_term_postal_cde=" + this.sd_term_postal_cde + ", sd_mbr_num=" + this.sd_mbr_num + ", sd_pin_ind=" + this.sd_pin_ind + ", sm_cust_mstr_fld1=" + this.sm_cust_mstr_fld1 + ", sm_user_cntry=" + this.sm_user_cntry + ", sm_user_postal_cde=" + this.sm_user_postal_cde + ", sm_sec_first_nam=" + this.sm_sec_first_nam + ", IGNORE_DEDUP=" + this.IGNORE_DEDUP + "]";
	}
}
