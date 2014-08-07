package com.gratex.perconik.services.uaca.ide2;

public class IdeDocumentData {
  /**
   * Specifies the version of the document
   */
  private String changesetIdInRcs;

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

  public IdeDocumentData(String changesetIdInRcs, String localPath, String serverPath, IdeRcsServerData rcsServer, String branch) {
    super();

    this.changesetIdInRcs = changesetIdInRcs;
    this.localPath = localPath;
    this.serverPath = serverPath;
    this.rcsServer = rcsServer;
    this.branch = branch;
  }

  public String getChangesetIdInRcs() {
    return this.changesetIdInRcs;
  }

  public void setChangesetIdInRcs(String changesetIdInRcs) {
    this.changesetIdInRcs = changesetIdInRcs;
  }

  public String getLocalPath() {
    return this.localPath;
  }

  public void setLocalPath(String localPath) {
    this.localPath = localPath;
  }

  public String getServerPath() {
    return this.serverPath;
  }

  public void setServerPath(String serverPath) {
    this.serverPath = serverPath;
  }

  public IdeRcsServerData getRcsServer() {
    return this.rcsServer;
  }

  public void setRcsServer(IdeRcsServerData rcsServer) {
    this.rcsServer = rcsServer;
  }

  public String getBranch() {
    return this.branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }
}
