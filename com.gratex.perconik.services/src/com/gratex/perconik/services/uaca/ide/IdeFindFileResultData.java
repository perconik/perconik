package com.gratex.perconik.services.uaca.ide;

import java.util.ArrayList;
import java.util.List;

public class IdeFindFileResultData {
  public IdeDocumentData file;

  List<IdeFindResultRowData> rows;

  public IdeFindFileResultData() {}

  public IdeFindFileResultData(final IdeDocumentData file, final List<IdeFindResultRowData> rows) {
    super();

    this.file = file;
    this.rows = rows;
  }

  public IdeDocumentData getFile() {
    return this.file;
  }

  public void setFile(final IdeDocumentData file) {
    this.file = file;
  }

  public List<IdeFindResultRowData> getRows() {
    if (this.rows == null) {
      this.rows = new ArrayList<>();
    }

    return this.rows;
  }

  public void setRows(final List<IdeFindResultRowData> rows) {
    this.rows = rows;
  }
}
