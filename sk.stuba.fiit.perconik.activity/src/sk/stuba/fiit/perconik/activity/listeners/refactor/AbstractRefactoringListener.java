package sk.stuba.fiit.perconik.activity.listeners.refactor;

import com.google.common.base.Optional;

import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptorProxy;
import org.eclipse.ltk.core.refactoring.history.IRefactoringHistoryService;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.refactor.RefactoringDescriptorProxySerializer;
import sk.stuba.fiit.perconik.activity.serializers.refactor.RefactoringDescriptorSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.history.RefactoringEventProxy;

import static com.google.common.base.Throwables.propagate;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractRefactoringListener extends ActivityListener {
  AbstractRefactoringListener() {}

  static final void put(final StructuredContent content, final RefactoringEventProxy<?> event) {
    content.put(key("type"), event.getType().toString().toLowerCase());

    content.put(key("service"), identifyObject(event.getHistoryService()));

    RefactoringDescriptorProxy proxy = event.getDescriptor();

    content.put(key("refactoring", "proxy"), new RefactoringDescriptorProxySerializer().serialize(proxy));

    RefactoringDescriptor descriptor = proxy.requestDescriptor(null);

    if (descriptor != null) {
      content.put(key("refactoring"), new RefactoringDescriptorSerializer().serialize(descriptor));
    }
  }

  static final Event build(final long time, final Action action, final RefactoringEventProxy<?> event) {
    Event data = LocalEvent.of(time, action.getName());

    put(data, event);

    return data;
  }

  abstract Optional<? extends Action> resolve(RefactoringEventProxy<?> event);

  final void process(final long time, final RefactoringEventProxy<?> event) {
    Optional<? extends Action> option = this.resolve(event);

    if (option.isPresent()) {
      Action action = option.get();

      this.send(action.getPath(), build(time, action, event));
    }
  }

  final void execute(final long time, final RefactoringEventProxy<?> event) {
    this.execute(new Runnable() {
      public void run() {
        final IRefactoringHistoryService service = event.getHistoryService();

        service.connect();

        try {
          process(time, event);
        } catch (Exception failure) {
          propagate(failure);
        } finally {
          service.disconnect();
        }
      }
    });
  }
}
