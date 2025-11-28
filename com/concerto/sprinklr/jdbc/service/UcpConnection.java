//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.jdbc.service;

import com.concerto.sprinklr.application.init.ServerInitializer;
import java.sql.SQLException;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UcpConnection {
	static Logger logger = LoggerFactory.getLogger("AppLogger");
	PoolDataSource pds;

	public UcpConnection() {
	}

	public PoolDataSource createDataSource() throws SQLException {
		Boolean isCheck = Boolean.valueOf(ServerInitializer.getAppConfigProps().get("Validate.connection.on.borrow").toString().trim());
		this.pds = PoolDataSourceFactory.getPoolDataSource();
		this.pds.setConnectionPoolName(ServerInitializer.dbPoolName);
		this.pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
		this.pds.setURL(ServerInitializer.getDbProperty().getUrl());
		this.pds.setUser(ServerInitializer.getDbProperty().getUsername());
		this.pds.setPassword(ServerInitializer.getDbProperty().getImp().toString());
		this.pds.setInitialPoolSize(Integer.parseInt(ServerInitializer.getAppConfigProps().get("InitialPoolSize").toString().trim()));
		this.pds.setMinPoolSize(Integer.parseInt(ServerInitializer.getAppConfigProps().get("MinPoolSize").toString().trim()));
		this.pds.setMaxPoolSize(Integer.parseInt(ServerInitializer.getAppConfigProps().get("MaxPoolSize").toString().trim()));
		this.pds.setValidateConnectionOnBorrow(isCheck);
		if (isCheck) {
			this.pds.setSecondsToTrustIdleConnection(Integer.parseInt(ServerInitializer.getAppConfigProps().get("Seconds.to.trust.idle.conn").toString().trim()));
		}

		return this.pds;
	}
}
