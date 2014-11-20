package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;

import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

import static java.util.Arrays.asList;

final class ClipboardReader extends DisplayTask<String> {
  static final ClipboardReader instance = new ClipboardReader();

  private static final Set<String> supportedTypeNames = ImmutableSet.of("Rich Text Format", "CF_UNICODETEXT", "CF_TEXT");

  private ClipboardReader() {}

  @Override
  public String call() {
    Clipboard clipboard = new Clipboard(Workbenches.getActiveWindow().getShell().getDisplay());

    if (Collections.disjoint(supportedTypeNames, asList(clipboard.getAvailableTypeNames()))) {
      return null;
    }

    String text = clipboard.getContents(TextTransfer.getInstance()).toString();

    clipboard.dispose();

    return text;
  }
}
