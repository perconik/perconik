package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

import sk.stuba.fiit.perconik.activity.listeners.command.CommandListener;
import sk.stuba.fiit.perconik.activity.listeners.command.UndoableHistoryListener;
import sk.stuba.fiit.perconik.activity.listeners.command.UndoableOperationListener;
import sk.stuba.fiit.perconik.activity.listeners.debug.DebugListener;
import sk.stuba.fiit.perconik.activity.listeners.debug.LaunchListener;
import sk.stuba.fiit.perconik.activity.listeners.git.BranchListener;
import sk.stuba.fiit.perconik.activity.listeners.git.CommitListener;
import sk.stuba.fiit.perconik.activity.listeners.git.TagListener;
import sk.stuba.fiit.perconik.activity.listeners.refactor.RefactoringHistoryListener;
import sk.stuba.fiit.perconik.activity.listeners.refactor.RefactoringOperationListener;
import sk.stuba.fiit.perconik.activity.listeners.resource.ProjectListener;
import sk.stuba.fiit.perconik.activity.listeners.search.SearchQueryListener;
import sk.stuba.fiit.perconik.activity.listeners.search.SearchResultListener;
import sk.stuba.fiit.perconik.activity.listeners.test.TestCaseListener;
import sk.stuba.fiit.perconik.activity.listeners.test.TestSessionListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.PageListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.PartListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.PerspectiveListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.WindowListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.WorkbenchListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.assist.CompletionListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.assist.CompletionSelectionListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.assist.CompletionSessionListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.selection.StructuredSelectionListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.text.MarkSelectionListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.text.TextCopyListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.text.TextCutListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.text.TextPasteListener;
import sk.stuba.fiit.perconik.activity.listeners.ui.text.TextSelectionListener;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviderFactory;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviders;

public final class ListenerProviderExtension implements ListenerProviderFactory {
  private static final Set<Class<? extends Listener>> classes;

  static {
    Builder<Class<? extends Listener>> builder = ImmutableSet.builder();

    builder.add(WorkbenchListener.class);
    builder.add(WindowListener.class);
    builder.add(PageListener.class);
    builder.add(PartListener.class);

    builder.add(PerspectiveListener.class);

    builder.add(TextCopyListener.class);
    builder.add(TextCutListener.class);
    builder.add(TextPasteListener.class);
    builder.add(TextSelectionListener.class);

    builder.add(MarkSelectionListener.class);
    builder.add(StructuredSelectionListener.class);

    builder.add(CommandListener.class);
    builder.add(UndoableOperationListener.class);
    builder.add(UndoableHistoryListener.class);

    builder.add(RefactoringOperationListener.class);
    builder.add(RefactoringHistoryListener.class);

    builder.add(CompletionListener.class);
    builder.add(CompletionSessionListener.class);
    builder.add(CompletionSelectionListener.class);

    builder.add(SearchQueryListener.class);
    builder.add(SearchResultListener.class);

    builder.add(LaunchListener.class);
    builder.add(DebugListener.class);

    builder.add(TestSessionListener.class);
    builder.add(TestCaseListener.class);

    builder.add(ProjectListener.class);

    builder.add(BranchListener.class);
    builder.add(CommitListener.class);
    builder.add(TagListener.class);

    classes = builder.build();
  }

  public ListenerProviderExtension() {}

  public ListenerProvider create(final ListenerProvider parent) {
    return ListenerProviders.builder(parent).addAll(classes).build();
  }
}
