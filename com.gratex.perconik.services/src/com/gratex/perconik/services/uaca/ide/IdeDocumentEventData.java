package com.gratex.perconik.services.uaca.ide;

public class IdeDocumentEventData extends IdeEventData {
  /**
   * Document that has been subject of this event
   */
  private IdeDocumentData document;

  public IdeDocumentEventData() {}

  public IdeDocumentData getDocument() {
    return this.document;
  }

  public void setDocument(IdeDocumentData document) {
    this.document = document;
  }
}
