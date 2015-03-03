package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SYS_LOGS database table.
 * 
 */
@Entity
@Table(name="SYS_LOGS")
@NamedQuery(name="SysLog.findAll", query="SELECT s FROM SysLog s")
public class SysLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYS_LOGS_LOGID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_LOGS_LOGID_GENERATOR")
	@Column(name="LOG_ID", unique=true, nullable=false, precision=12)
	private long logId;

	private Timestamp ctime;

	@Column(length=128)
	private String ipaddr;

	@Column(name="LOG_INFO", length=1024)
	private String logInfo;

	@Column(length=128)
	private String url;

	@Column(name="USER_ID", precision=12)
	private BigDecimal userId;

	public SysLog() {
	}

	public long getLogId() {
		return this.logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public String getIpaddr() {
		return this.ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getLogInfo() {
		return this.logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

}