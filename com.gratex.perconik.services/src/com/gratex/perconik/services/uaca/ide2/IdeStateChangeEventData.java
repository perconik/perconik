package com.gratex.perconik.services.uaca.ide2;

public class IdeStateChangeEventData extends IdeEventData {
  /**
   * Uri specifying state/perspective to which has been IDE switched.
   * It should be in form of:
   * http://perconik.gratex.com/useractivity/enum/idestatechangeevent/statetype/[idename]#[value] where value is name of the state/perspective ("debug", "build", ..)
   * and ideName is "eclipse", "vs",
   */
  private String stateTypeUri;

  public IdeStateChangeEventData() {}

  public String getStateTypeUri() {
    return this.stateTypeUri;
  }

  public void setStateTypeUri(String stateTypeUri) {
    this.stateTypeUri = stateTypeUri;
  }
}
