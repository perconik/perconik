package sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.history;

import org.eclipse.ltk.core.refactoring.RefactoringDescriptorProxy;
import org.eclipse.ltk.core.refactoring.history.IRefactoringHistoryService;
import org.eclipse.ltk.core.refactoring.history.RefactoringExecutionEvent;
import org.eclipse.ltk.core.refactoring.history.RefactoringHistoryEvent;

import static java.util.Objects.requireNonNull;

public abstract class RefactoringEventProxy<E> {
  final E raw;

  RefactoringEventProxy(final E raw) {
    this.raw = requireNonNull(raw);
  }

  public static final RefactoringEventProxy<RefactoringExecutionEvent> wrap(final RefactoringExecutionEvent event) {
    return new Execution(event);
  }

  public static final RefactoringEventProxy<RefactoringHistoryEvent> wrap(final RefactoringHistoryEvent event) {
    return new History(event);
  }

  private static final class Execution extends RefactoringEventProxy<RefactoringExecutionEvent> {
    Execution(final RefactoringExecutionEvent event) {
      super(event);
    }

    @Override
    public RefactoringEventType getType() {
      return RefactoringExecutionEventType.valueOf(this.raw.getEventType());
    }

    @Override
    public RefactoringDescriptorProxy getDescriptor() {
      return this.raw.getDescriptor();
    }

    @Override
    public IRefactoringHistoryService getHistoryService() {
      return this.raw.getHistoryService();
    }
  }

  private static final class History extends RefactoringEventProxy<RefactoringHistoryEvent> {
    History(final RefactoringHistoryEvent event) {
      super(event);
    }

    @Override
    public RefactoringEventType getType() {
      return RefactoringHistoryEventType.valueOf(this.raw.getEventType());
    }

    @Override
    public RefactoringDescriptorProxy getDescriptor() {
      return this.raw.getDescriptor();
    }

    @Override
    public IRefactoringHistoryService getHistoryService() {
      return this.raw.getHistoryService();
    }
  }

  public final E getRaw() {
    return this.raw;
  }

  public abstract RefactoringEventType getType();

  public abstract RefactoringDescriptorProxy getDescriptor();

  public abstract IRefactoringHistoryService getHistoryService();
}
