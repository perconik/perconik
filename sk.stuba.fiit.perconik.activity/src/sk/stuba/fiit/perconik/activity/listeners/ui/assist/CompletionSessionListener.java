package sk.stuba.fiit.perconik.activity.listeners.ui.assist;

import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;

import static sk.stuba.fiit.perconik.activity.listeners.ui.assist.CompletionSessionListener.Action.FINISH;
import static sk.stuba.fiit.perconik.activity.listeners.ui.assist.CompletionSessionListener.Action.RESTART;
import static sk.stuba.fiit.perconik.activity.listeners.ui.assist.CompletionSessionListener.Action.START;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
@Unsupported
public final class CompletionSessionListener extends CommonEventListener implements sk.stuba.fiit.perconik.core.listeners.CompletionListener {
  public CompletionSessionListener() {}

  enum Action implements CommonEventListener.Action {
    START,

    RESTART,

    FINISH;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "completion", "session", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  static Event build(final long time, final Action action, final ContentAssistEvent event) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("assistant"), identifyObject(event.assistant));
    data.put(key("processor"), identifyObject(event.processor));

    data.put(key("isAutoActivated"), event.isAutoActivated);

    return data;
  }

  void process(final long time, final Action action, final ContentAssistEvent assist) {
    this.send(action.getPath(), build(time, action, assist));
  }

  void execute(final long time, final Action action, final ContentAssistEvent assist) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action, assist);
      }
    });
  }

  public void assistSessionStarted(final ContentAssistEvent event) {
    final long time = this.currentTime();

    this.execute(time, START, event);
  }

  public void assistSessionRestarted(final ContentAssistEvent event) {
    final long time = this.currentTime();

    this.execute(time, RESTART, event);
  }

  public void assistSessionEnded(final ContentAssistEvent event) {
    final long time = this.currentTime();

    this.execute(time, FINISH, event);
  }

  public void selectionChanged(final ICompletionProposal proposal, final boolean smart) {
    // ignore
  }

  public void applied(final ICompletionProposal proposal) {
    // ignore
  }
}
