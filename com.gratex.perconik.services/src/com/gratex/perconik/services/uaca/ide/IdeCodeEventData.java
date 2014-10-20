package com.gratex.perconik.services.uaca.ide;

public class IdeCodeEventData extends IdeEventData {
  /**
   * Text that was subject of a given event. For instance text that was pasted from the web.
   */
  private String text;

  /**
   * Zero based start row index. Null if not determined
   */
  private Integer startRowIndex;

  /**
   * Zero based end row index. Null if not determined
   */
  private Integer endRowIndex;

  /**
   * Zero based start column index. Null if not determined
   */
  private Integer startColumnIndex;

  /**
   * Zero based end column index. Null if not determined
   */
  private Integer endColumnIndex;

  /**
   * Document for which has been this event generated
   */
  private IdeDocumentData document;

  public IdeCodeEventData() {}

  public String getText() {
    return this.text;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public Integer getStartRowIndex() {
    return this.startRowIndex;
  }

  public void setStartRowIndex(final Integer startRowIndex) {
    this.startRowIndex = startRowIndex;
  }

  public Integer getEndRowIndex() {
    return this.endRowIndex;
  }

  public void setEndRowIndex(final Integer endRowIndex) {
    this.endRowIndex = endRowIndex;
  }

  public Integer getStartColumnIndex() {
    return this.startColumnIndex;
  }

  public void setStartColumnIndex(final Integer startColumnIndex) {
    this.startColumnIndex = startColumnIndex;
  }

  public Integer getEndColumnIndex() {
    return this.endColumnIndex;
  }

  public void setEndColumnIndex(final Integer endColumnIndex) {
    this.endColumnIndex = endColumnIndex;
  }

  public IdeDocumentData getDocument() {
    return this.document;
  }

  public void setDocument(final IdeDocumentData document) {
    this.document = document;
  }
}
