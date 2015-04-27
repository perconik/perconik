package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;

import static com.google.common.base.Preconditions.checkNotNull;

import static sk.stuba.fiit.perconik.activity.listeners.ui.text.TextPasteListener.Action.PASTE;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.5.alpha")
public final class TextPasteListener extends AbstractTextClipboardListener implements CommandExecutionListener {
  public TextPasteListener() {}

  enum Action implements ActivityListener.Action {
    PASTE("org.eclipse.ui.edit.paste");

    private final String identifier;

    private final String name;

    private final String path;

    private Action(final String identifier) {
      this.identifier = checkNotNull(identifier);

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
  boolean validate(final IWorkbenchPart part, final IDocument document, final LineRegion region, final String selection) {
    boolean valid = region.text != null && selection.isEmpty();

    if (!valid && this.log.isEnabled()) {
      this.log.print("%s: part selection not empty '%s'", "text-paste", selection);
    }

    return valid;
  }

  public void preExecute(final String identifier, final ExecutionEvent event) {
    // ignore
  }

  public void postExecuteSuccess(final String identifier, final Object result) {
    final long time = this.currentTime();

    if (!PASTE.getIdentifier().equals(identifier)) {
      return;
    }

    this.execute(new Runnable() {
      public void run() {
        process(time, PASTE);
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
