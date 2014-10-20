package com.gratex.perconik.services.uaca.ide;

public class IdeCheckinEventData extends IdeEventData {
  private String changesetIdInRcs;

  private IdeRcsServerData rcsServer;

  public IdeCheckinEventData() {}

  public String getChangesetIdInRcs() {
    return this.changesetIdInRcs;
  }

  public void setChangesetIdInRcs(final String changesetIdInRcs) {
    this.changesetIdInRcs = changesetIdInRcs;
  }

  public IdeRcsServerData getRcsServer() {
    return this.rcsServer;
  }

  public void setRcsServer(final IdeRcsServerData rcsServer) {
    this.rcsServer = rcsServer;
  }
}
