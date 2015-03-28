package sk.stuba.fiit.perconik.eclipse.ui.console;

import javax.annotation.Nullable;

import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.TextConsolePage;
import org.eclipse.ui.part.PageBookView;

import sk.stuba.fiit.perconik.eclipse.ui.part.PageBookViews;

/**
 * Static utility methods pertaining to Eclipse text console pages.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ConsoleViews {
  private ConsoleViews() {}

  public static TextConsolePage getCurrentTextConsolePage(@Nullable final IConsoleView view) {
    if (view instanceof PageBookView) {
      return TextConsolePages.from(PageBookViews.getCurrentPage((PageBookView) view));
    }

    return null;
  }
}
