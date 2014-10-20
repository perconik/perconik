package com.gratex.perconik.services.uaca.ide;

public class IdeFindResultRowData {
  /**
   * Zero based index of a row where the match has been found or null if it has not been determined
   */
  private Integer row;

  /**
   * Zero based index of a column on a row where the match has been found or null if it has not been determined
   */
  private Integer column;

  /**
   * Text that has been matched against search query on this row
   */
  private String text;

  public IdeFindResultRowData() {}

  public IdeFindResultRowData(final Integer row, final Integer column, final String text) {
    this.row = row;
    this.column = column;
    this.text = text;
  }

  public Integer getRow() {
    return this.row;
  }

  public void setRow(final Integer row) {
    this.row = row;
  }

  public Integer getColumn() {
    return this.column;
  }

  public void setColumn(final Integer column) {
    this.column = column;
  }

  public String getText() {
    return this.text;
  }

  public void setText(final String text) {
    this.text = text;
  }
}
