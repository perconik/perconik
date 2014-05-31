package com.gratex.perconik.services.uaca.ide;

import javax.xml.datatype.XMLGregorianCalendar;

public class BaseIdeEventRequest{
	private XMLGregorianCalendar timestamp;
	/**
	 * Application instance identifier (for instance pid)
	 */
	private String sessionId;
	/**
	 * Name of the IDE
	 */
	private String appName;
	/**
	 * Version of the IDE
	 */
	private String appVersion;
	/**
	 * Name of the current project
	 */
	private String projectName;
	/**
	 * Name of the current solution/workspace
	 */
	private String solutionName;
	/**
	 * @return the {@link #sessionId}
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param {@link #sessionId}
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the {@link #appName}
	 */
	public String getAppName() {
		return appName;
	}
	/**
	 * @param {@link #appName}
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	/**
	 * @return the {@link #appVersion}
	 */
	public String getAppVersion() {
		return appVersion;
	}
	/**
	 * @param {@link #appVersion}
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	/**
	 * @return the {@link #projectName}
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param {@link #projectName}
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the {@link #solutionName}
	 */
	public String getSolutionName() {
		return solutionName;
	}
	/**
	 * @param {@link #solutionName}
	 */
	public void setSolutionName(String solutionName) {
		this.solutionName = solutionName;
	}
	public XMLGregorianCalendar getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(XMLGregorianCalendar timestamp) {
		this.timestamp = timestamp;
	}
}