package sk.stuba.fiit.perconik.core.debug.services.listeners;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.debug.listeners.CommandDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.CommandExecutionDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.CommandManagerDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.CompletionDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.DebugEventsDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.DocumentDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.EditorDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.FileBufferDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.GitConfigurationDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.GitIndexDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.GitReferenceDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.GitRepositoryDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.JavaElementDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.LaunchConfigurationDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.LaunchDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.LaunchesDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.MarkSelectionDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.OperationHistoryDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.PageDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.PartDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.PerspectiveDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.RefactoringExecutionDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.RefactoringHistoryDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.ResourceDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.SearchQueryDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.SearchResultDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.SelectionDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.StructuredSelectionDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.TestRunDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.TextSelectionDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.WindowDebugListener;
import sk.stuba.fiit.perconik.core.debug.listeners.WorkbenchDebugListener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviders;

public final class DebugListenerProviders {
  private static final Set<Class<? extends Listener>> classes;

  static {
    Builder<Class<? extends Listener>> builder = ImmutableSet.builder();

    builder.add(CommandDebugListener.class);
    builder.add(CommandExecutionDebugListener.class);
    builder.add(CommandManagerDebugListener.class);
    builder.add(CompletionDebugListener.class);
    builder.add(DebugEventsDebugListener.class);
    builder.add(DocumentDebugListener.class);
    builder.add(EditorDebugListener.class);
    builder.add(FileBufferDebugListener.class);
    builder.add(GitConfigurationDebugListener.class);
    builder.add(GitIndexDebugListener.class);
    builder.add(GitReferenceDebugListener.class);
    builder.add(GitRepositoryDebugListener.class);
    builder.add(JavaElementDebugListener.class);
    builder.add(LaunchDebugListener.class);
    builder.add(LaunchesDebugListener.class);
    builder.add(LaunchConfigurationDebugListener.class);
    builder.add(MarkSelectionDebugListener.class);
    builder.add(OperationHistoryDebugListener.class);
    builder.add(PageDebugListener.class);
    builder.add(PartDebugListener.class);
    builder.add(PerspectiveDebugListener.class);
    builder.add(RefactoringExecutionDebugListener.class);
    builder.add(RefactoringHistoryDebugListener.class);
    builder.add(ResourceDebugListener.class);
    builder.add(SearchQueryDebugListener.class);
    builder.add(SearchResultDebugListener.class);
    builder.add(SelectionDebugListener.class);
    builder.add(StructuredSelectionDebugListener.class);
    builder.add(TestRunDebugListener.class);
    builder.add(TextSelectionDebugListener.class);
    builder.add(WindowDebugListener.class);
    builder.add(WorkbenchDebugListener.class);

    classes = builder.build();
  }

  private DebugListenerProviders() {}

  public static DebugListenerProvider create() {
    return create(ListenerProviders.getSystemProvider());
  }

  public static DebugListenerProvider create(final ListenerProvider parent) {
    ListenerProvider provider = ListenerProviders.builder().addAll(classes).parent(parent).build();

    return DebugListenerProviderProxy.wrap(provider);
  }
}
