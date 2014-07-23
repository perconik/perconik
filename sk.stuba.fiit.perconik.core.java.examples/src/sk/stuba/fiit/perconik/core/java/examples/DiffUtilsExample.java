package sk.stuba.fiit.perconik.core.java.examples;

import java.util.Arrays;
import java.util.List;

import difflib.Chunk;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

import static java.lang.System.out;

public final class DiffUtilsExample {
  public static void main(String[] args) {
    List<?> o0;
    List<?> r0;

    o0 = Arrays.asList("a", "b", "c", "d", "e", "f");
    r0 = Arrays.asList("x", "a", "b", "c", "x", "e");

    //o0 = Arrays.asList(1000, 1001, 1002, 1003, 1004, 1005, 1006);
    //r0 = Arrays.asList(2000, 1001, 1002, 1003, 2000, 1004, 1005);

    Patch p = DiffUtils.diff(o0, r0);

    out.println(patchToString(p));
  }

  public static final String patchToString(Patch p) {
    SmartStringBuilder builder = new SmartStringBuilder();

    List<Delta> deltas = p.getDeltas();

    builder.append("deltas: ").appendln(deltas.size());

    for (Delta d: deltas) {
      builder.appendln();
      builder.append("type: ").appendln(d.getType());
      builder.appendln("original:").append(chunkToString(d.getOriginal()));
      builder.appendln("revised:").append(chunkToString(d.getRevised()));
    }

    return builder.toString();
  }

  public static final String chunkToString(Chunk c) {
    SmartStringBuilder builder = new SmartStringBuilder();

    List<?> lines = c.getLines();

    builder.setIndent(2);
    builder.append("position: ").appendln(c.getPosition());
    builder.append("lines: ").appendln(lines.size());
    builder.setIndent(4);

    for (Object o: lines) {
      builder.appendln(o);
    }

    return builder.toString();
  }
}
