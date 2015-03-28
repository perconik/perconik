package sk.stuba.fiit.perconik.eclipse.ui.console;

import javax.annotation.Nullable;

import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.console.TextConsolePage;
import org.eclipse.ui.console.TextConsoleViewer;
import org.eclipse.ui.part.IPage;

import sk.stuba.fiit.perconik.eclipse.ui.Parts;

/**
 * Static utility methods pertaining to Eclipse text console pages.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class TextConsolePages {
  private TextConsolePages() {}

  public static TextConsolePage from(@Nullable final IPage page) {
    return page instanceof TextConsolePage ? (TextConsolePage) page : null;
  }

  public static TextConsoleViewer getTextConsoleViewer(@Nullable final TextConsolePage page) {
    return page != null ? page.getViewer() : null;
  }

  public static StyledText getStyledText(@Nullable final TextConsolePage page) {
    return Parts.getStyledText(getTextConsoleViewer(page));
  }

  public static IDocument getDocument(@Nullable final TextConsolePage page) {
    return Parts.getDocument(getTextConsoleViewer(page));
  }
}
