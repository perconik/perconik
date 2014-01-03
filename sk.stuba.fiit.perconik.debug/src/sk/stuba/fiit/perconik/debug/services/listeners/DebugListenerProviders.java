package sk.stuba.fiit.perconik.debug.services.listeners;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviders;
import sk.stuba.fiit.perconik.debug.listeners.CommandDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.CommandExecutionDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.CommandManagerDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.CompletionDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.DebugEventsDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.DocumentDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.EditorDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.FileBufferDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.GitConfigurationDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.GitIndexDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.GitReferenceDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.JavaElementDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.LaunchConfigurationDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.LaunchDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.LaunchesDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.MarkSelectionDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.OperationHistoryDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.PageDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.PartDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.PerspectiveDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.RefactoringExecutionDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.RefactoringHistoryDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.ResourceDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.SearchQueryDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.SearchResultDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.SelectionDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.StructuredSelectionDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.TestRunDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.TextSelectionDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.WindowDebugListener;
import sk.stuba.fiit.perconik.debug.listeners.WorkbenchDebugListener;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

public final class DebugListenerProviders
{
	private static final Set<Class<? extends Listener>> classes;
	
	static
	{
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
	
	private DebugListenerProviders()
	{
		throw new AssertionError();
	}

	public static final DebugListenerProvider create()
	{
		return create(ListenerProviders.getSystemProvider());
	}

	public static final DebugListenerProvider create(final ListenerProvider parent)
	{
		ListenerProvider provider = ListenerProviders.builder().addAll(classes).parent(parent).build();
		
		return DebugListenerProviderProxy.wrap(provider);
	}
}
