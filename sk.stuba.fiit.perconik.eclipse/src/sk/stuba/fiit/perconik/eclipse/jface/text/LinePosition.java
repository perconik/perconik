package sk.stuba.fiit.perconik.eclipse.jface.text;

import static com.google.common.base.Preconditions.checkArgument;

public final class LinePosition {
  public final int line;

  public final int offset;

  public LinePosition(final int line, final int offset) {
    checkArgument(line >= 0);
    checkArgument(offset >= 0);

    this.line = line;
    this.offset = offset;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof LinePosition)) {
      return false;
    }

    LinePosition other = (LinePosition) o;

    return this.line == other.line && this.offset == other.offset;
  }

  @Override
  public int hashCode() {
    return this.line ^ this.offset;
  }

  @Override
  public String toString() {
    return "line=" + this.line + ", offset=" + this.offset;
  }
}
