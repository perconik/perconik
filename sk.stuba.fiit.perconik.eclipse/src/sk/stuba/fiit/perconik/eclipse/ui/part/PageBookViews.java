package sk.stuba.fiit.perconik.eclipse.ui.part;

import javax.annotation.Nullable;

import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.PageBookView;

/**
 * Static utility methods pertaining to Eclipse page book views.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class PageBookViews {
  private PageBookViews() {}

  public static IPage getDefaultPage(@Nullable final PageBookView view) {
    return view != null ? view.getDefaultPage() : null;
  }

  public static IPage getCurrentPage(@Nullable final PageBookView view) {
    return view != null ? view.getCurrentPage() : null;
  }
}
