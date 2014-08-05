package com.gratex.perconik.services.uaca.ide;

public class IdeCheckinEventData extends IdeEventData {
  /**
   * Changeset id as specified in a RCS
   */
  private String changesetIdInRcs;

  /**
   * Target rcs server or remote repository
   */
  private IdeRcsServerData rcsServer;

  public IdeCheckinEventData() {}

  public String getChangesetIdInRcs() {
    return this.changesetIdInRcs;
  }

  public void setChangesetIdInRcs(String changesetIdInRcs) {
    this.changesetIdInRcs = changesetIdInRcs;
  }

  public IdeRcsServerData getRcsServer() {
    return this.rcsServer;
  }

  public void setRcsServer(IdeRcsServerData rcsServer) {
    this.rcsServer = rcsServer;
  }
}
