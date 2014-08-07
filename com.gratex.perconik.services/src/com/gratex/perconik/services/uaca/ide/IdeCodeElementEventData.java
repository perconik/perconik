package com.gratex.perconik.services.uaca.ide;


public class IdeCodeElementEventData extends IdeEventData {
  /**
   * Type of code element.
   * It should be in form of:
   * http://perconik.gratex.com/useractivity/enum/idecodeelementevent/codeelementtype#[value]
   * where value is "class", "method", "property" ...
   */
  private String codeElementTypeUri;

  /**
   * Full name of the code element
   */
  private String elementFullName;

  public IdeCodeElementEventData() {}

  public String getCodeElementTypeUri() {
    return this.codeElementTypeUri;
  }

  public void setCodeElementTypeUri(String codeElementTypeUri) {
    this.codeElementTypeUri = codeElementTypeUri;
  }

  public String getElementFullName() {
    return this.elementFullName;
  }

  public void setElementFullName(String elementFullName) {
    this.elementFullName = elementFullName;
  }
}
