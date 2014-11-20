package sk.stuba.fiit.perconik.activity.listeners.refactor;

import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptorProxy;
import org.eclipse.ltk.core.refactoring.history.IRefactoringHistoryService;

import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
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
abstract class AbstractRefactoringListener extends CommonEventListener {
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

  abstract void process(final long time, final RefactoringEventProxy<?> event);

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
