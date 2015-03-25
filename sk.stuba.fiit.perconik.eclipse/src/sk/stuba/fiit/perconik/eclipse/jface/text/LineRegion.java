package sk.stuba.fiit.perconik.eclipse.jface.text;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import static java.util.Objects.requireNonNull;

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

  private static LineRegion construct(final int startLine, final int startOffset, final int endLine, final int endOffset, final String text) {
    return new LineRegion(new LinePosition(startLine, startOffset), new LinePosition(endLine, endOffset), text);
  }

  public static LineRegion compute(final IDocument document, final int offset, final int length) {
    try {
      return compute(document, offset, length, document.get(offset, length));
    } catch (BadLocationException e) {
      throw propagate(e);
    }
  }

  public static LineRegion compute(final IDocument document, final int offset, final int length, final String text) {
    try {
      int startLine = document.getLineOfOffset(offset);
      int endLine = document.getLineOfOffset(offset + length);

      int startOffset = offset - document.getLineOffset(startLine);
      int endOffset = offset + length - document.getLineOffset(endLine);

      String delimeter = document.getLineDelimiter(endLine);

      if (delimeter != null && text.endsWith(delimeter) && endOffset != 0) {
        endLine ++;
        endOffset = 0;
      }

      return construct(startLine, startOffset, endLine, endOffset, text);
    } catch (BadLocationException e) {
      throw propagate(e);
    }
  }

  public static LineRegion between(final IDocument document, final int top, final int bottom) {
    try {
      int offset = document.getLineOffset(top);
      int length = document.getLineOffset(bottom) + document.getLineLength(bottom) - offset;

      String text = document.get(offset, length);

      return between(document, top, bottom, text);
    } catch (BadLocationException e) {
      throw propagate(e);
    }
  }

  public static LineRegion between(final IDocument document, final int top, final int bottom, final String text) {
    try {
      return construct(top, 0, bottom, document.getLineLength(bottom), text);
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
