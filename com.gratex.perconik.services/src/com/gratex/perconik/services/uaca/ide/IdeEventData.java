package com.gratex.perconik.services.uaca.ide;

import javax.xml.datatype.XMLGregorianCalendar;

public class IdeEventData {
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

  public String getSessionId() {
    return this.sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getAppName() {
    return this.appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public String getAppVersion() {
    return this.appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }

  public String getProjectName() {
    return this.projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getSolutionName() {
    return this.solutionName;
  }

  public void setSolutionName(String solutionName) {
    this.solutionName = solutionName;
  }

  public XMLGregorianCalendar getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(XMLGregorianCalendar timestamp) {
    this.timestamp = timestamp;
  }
}
