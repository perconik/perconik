package com.gratex.perconik.services.uaca.ide2;

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

  public IdeFindResultRowData(Integer row, Integer column, String text) {
    this.row = row;
    this.column = column;
    this.text = text;
  }

  public Integer getRow() {
    return this.row;
  }

  public void setRow(Integer row) {
    this.row = row;
  }

  public Integer getColumn() {
    return this.column;
  }

  public void setColumn(Integer column) {
    this.column = column;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
