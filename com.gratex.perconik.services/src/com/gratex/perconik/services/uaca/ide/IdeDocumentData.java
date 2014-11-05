package com.gratex.perconik.services.uaca.ide;

public class IdeDocumentData {
  /**
   * Specifies the version of the document
   */
  private String changesetIdInRcs;

  private String changesetIdInRcsOfPath;

  /**
   *  Full local path of the document
   */
  private String localPath;

  /**
   * Path of the document in the RCS. Null if not determined or in case of local file.
   */
  private String serverPath;

  /**
   * Rcs server to which this file version belongs. Null if not determined or in case of local file.
   */
  private IdeRcsServerData rcsServer;

  /**
   * Rcs branch name. Null if not determined or in case of local file.
   */
  private String branch;

  public IdeDocumentData() {}

  public IdeDocumentData(final String changesetIdInRcs, String changesetIdInRcsOfPath, final String localPath, final String serverPath, final IdeRcsServerData rcsServer, final String branch) {
    super();

    this.changesetIdInRcs = changesetIdInRcs;
    this.changesetIdInRcsOfPath = changesetIdInRcsOfPath;
    this.localPath = localPath;
    this.serverPath = serverPath;
    this.rcsServer = rcsServer;
    this.branch = branch;
  }

  public String getChangesetIdInRcs() {
    return this.changesetIdInRcs;
  }

  public void setChangesetIdInRcs(final String changesetIdInRcs) {
    this.changesetIdInRcs = changesetIdInRcs;
  }

  public String getChangesetIdInRcsOfPath() {
    return this.changesetIdInRcsOfPath;
  }

  public void setChangesetIdInRcsOfPath(final String changesetIdInRcsOfPath) {
    this.changesetIdInRcsOfPath = changesetIdInRcsOfPath;
  }

  public String getLocalPath() {
    return this.localPath;
  }

  public void setLocalPath(final String localPath) {
    this.localPath = localPath;
  }

  public String getServerPath() {
    return this.serverPath;
  }

  public void setServerPath(final String serverPath) {
    this.serverPath = serverPath;
  }

  public IdeRcsServerData getRcsServer() {
    return this.rcsServer;
  }

  public void setRcsServer(final IdeRcsServerData rcsServer) {
    this.rcsServer = rcsServer;
  }

  public String getBranch() {
    return this.branch;
  }

  public void setBranch(final String branch) {
    this.branch = branch;
  }
}
