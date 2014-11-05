package com.gratex.perconik.services.uaca.ide;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class IdeEventData {
  public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

  private Date timestamp;

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

  public IdeEventData() {}

  public String getSessionId() {
    return this.sessionId;
  }

  public void setSessionId(final String sessionId) {
    this.sessionId = sessionId;
  }

  public String getAppName() {
    return this.appName;
  }

  public void setAppName(final String appName) {
    this.appName = appName;
  }

  public String getAppVersion() {
    return this.appVersion;
  }

  public void setAppVersion(final String appVersion) {
    this.appVersion = appVersion;
  }

  public String getProjectName() {
    return this.projectName;
  }

  public void setProjectName(final String projectName) {
    this.projectName = projectName;
  }

  public String getSolutionName() {
    return this.solutionName;
  }

  public void setSolutionName(final String solutionName) {
    this.solutionName = solutionName;
  }

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIMESTAMP_FORMAT)
  public Date getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(final Date timestamp) {
    this.timestamp = timestamp;
  }
}
