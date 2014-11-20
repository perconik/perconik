package sk.stuba.fiit.perconik.eclipse.jface.text;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import static java.util.Objects.requireNonNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Throwables.propagate;

public final class LineRegion {
  public final LinePosition start;

  public final LinePosition end;

  public final String text;

  public LineRegion(final LinePosition start, final LinePosition end, final String text) {
    this.start = requireNonNull(start);
    this.end = requireNonNull(end);
    this.text = requireNonNull(text);
  }

  public static LineRegion of(final IDocument document, final int offset, final int length, final String text) {
    checkArgument(offset >= 0);
    checkArgument(length >= 0);

    try {
      int startLine = document.getLineOfOffset(offset);
      int endLine = document.getLineOfOffset(offset + length);

      int startOffset = offset - document.getLineOffset(startLine);
      int endOffset = offset + length - document.getLineOffset(endLine);

      String delimeter = document.getLineDelimiter(endLine);

      if (delimeter != null && text.endsWith(delimeter)) {
        endLine ++;
        endOffset = 0;
      }

      return new LineRegion(new LinePosition(startLine, startOffset), new LinePosition(endLine, endOffset), text);
    } catch (BadLocationException e) {
      throw propagate(e);
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof LineRegion)) {
      return false;
    }

    LineRegion other = (LineRegion) o;

    return this.start.equals(other.start) && this.end.equals(other.end) && this.text.equals(other.text);
  }

  @Override
  public int hashCode() {
    int result = 31 * this.text.hashCode();

    result = 31 * result + this.start.hashCode();
    result = 31 * result + this.end.hashCode();

    return result;
  }

  @Override
  public String toString() {
    return "start=[" + this.start + "], end=[" + this.end + "], text=" + this.text;
  }
}
