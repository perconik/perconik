package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;

import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.activity.listeners.ui.text.TextCopyListener.Action.COPY;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.equalsIgnoreLineSeparators;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class TextCopyListener extends AbstractTextCopyListener implements CommandExecutionListener {
  public TextCopyListener() {}

  enum Action implements CommonEventListener.Action {
    COPY("org.eclipse.ui.edit.copy");

    private final String identifier;

    private final String name;

    private final String path;

    private Action(final String identifier) {
      this.identifier = requireNonNull(identifier);

      this.name = actionName("eclipse", "text", this);
      this.path = actionPath(this.name);
    }

    public String getIdentifier() {
      return this.identifier;
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  @Override
  boolean validate(final IEditorPart editor, final IDocument document, final LineRegion region, final String selection) {
    boolean valid = region.text != null && !(region.text.equals(selection) || equalsIgnoreLineSeparators(region.text, selection));

    if (!valid && Log.isEnabled()) {
      Log.message("copy: clipboard content not equal to editor selection '%s' != '%s'%n", region.text, selection).appendTo(this.log);
    }

    return valid;
  }

  public void preExecute(final String identifier, final ExecutionEvent event) {
    // ignore
  }

  public void postExecuteSuccess(final String identifier, final Object result) {
    final long time = currentTime();

    if (!COPY.getIdentifier().equals(identifier)) {
      return;
    }

    this.execute(new Runnable() {
      public void run() {
        process(time, COPY);
      }
    });
  }

  public void postExecuteFailure(final String identifier, final ExecutionException exception) {
    // ignore
  }

  public void notDefined(final String identifier, final NotDefinedException exception) {
    // ignore
  }

  public void notEnabled(final String identifier, final NotEnabledException exception) {
    // ignore
  }

  public void notHandled(final String identifier, final NotHandledException exception) {
    // ignore
  }
}
