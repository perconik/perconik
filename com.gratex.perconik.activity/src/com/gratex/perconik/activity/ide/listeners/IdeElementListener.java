package com.gratex.perconik.activity.ide.listeners;

import static com.gratex.perconik.activity.ide.IdeActivityServices.performWatcherServiceOperation;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setEventData;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setProjectData;
import static sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType.POST_CHANGE;
import static sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType.POST_RECONCILE;
import java.net.URI;
import java.util.Set;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import sk.stuba.fiit.perconik.core.annotations.Experimental;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.java.JavaElements;
import sk.stuba.fiit.perconik.core.java.dom.AstTransformers;
import sk.stuba.fiit.perconik.core.java.dom.difference.AstDifference;
import sk.stuba.fiit.perconik.core.java.dom.difference.AstNodeDelta;
import sk.stuba.fiit.perconik.core.listeners.JavaElementListener;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementDeltaFlag;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.AstNodeType;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableSet;
import com.gratex.perconik.activity.ide.IdeActivityServices.WatcherServiceOperation;
import com.gratex.perconik.activity.ide.IdeApplication;
import com.gratex.perconik.services.vs.IVsActivityWatcherService;
import com.gratex.perconik.services.vs.IdeCodeElementEventDto;
import com.gratex.perconik.services.vs.IdeCodeElementEventTypeEnum;

/**
 * A listener of {@code IdeCodeElement} events. This listener creates
 * {@link IdeCodeElementEventDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p> TODO document how DTOs are build and what data they contain
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Experimental
@Unsupported
public final class IdeElementListener extends IdeListener implements JavaElementListener
{
	private final Cache<URI, CompilationUnit> cache;
	
	private final CompilationUnitDifferencer differencer;
	
	public IdeElementListener()
	{
		this.cache = CacheBuilder.newBuilder().weakValues().recordStats().build();
		
		this.differencer = new CompilationUnitDifferencer();
	}
	
	private final CompilationUnit cache(final URI uri, final CompilationUnit revised)
	{
		CompilationUnit original = this.cache.getIfPresent(uri);
	
		if (revised != null)
		{
			this.cache.put(uri, revised);
		}
		
		return original;
	}

	static final void send(final IdeCodeElementEventDto data)
	{
		performWatcherServiceOperation(new WatcherServiceOperation()
		{
			public final void perform(final IVsActivityWatcherService service)
			{
				service.notifyIdeCodeElementEvent(data);
			}
		});
	}
	
	static final IdeCodeElementEventDto build(final long time, final IJavaElement element, final ASTNode node, final IdeCodeElementEventTypeEnum type)
	{
		final IdeCodeElementEventDto data = new IdeCodeElementEventDto();

		data.setEventType(type);
		
		data.setElementType(AstNodeType.valueOf(node).toString());
		data.setElementFullName(AstTransformers.namePathExtractor().transform(node).toString());
		
		// TODO
		if (IdeApplication.getInstance().isDebug()){
		System.out.println("-- ELEMENT --");
		System.out.println(data.getElementType());
		System.out.println(data.getElementFullName());
		}
		
		setProjectData(data, element.getJavaProject().getProject());
		setApplicationData(data);
		setEventData(data, time);
		
		return data;
	}
	
	final void process(final long time, final ElementChangedEvent event)
	{
		final IJavaElementDelta delta = event.getDelta();
		
		if (!JavaElementDeltaFlag.setOf(delta.getFlags()).contains(JavaElementDeltaFlag.AST_AFFECTED))
		{
			return;
		}
		
		IJavaElement element = delta.getElement();
		
		URI uri = JavaElements.resource(element).getLocationURI();
		
		CompilationUnit revised  = delta.getCompilationUnitAST();
		CompilationUnit original = cache(uri, revised);
		
		if (revised != null && original != null)
		{
			AstDifference<ASTNode> difference = this.differencer.difference(original, revised);

			for (AstNodeDelta<ASTNode> pair: difference)
			{
				send(build(time, element, pair.getOriginalNode(), null));
			}
		}
	}
	
	public final void elementChanged(final ElementChangedEvent event)
	{
		final long time = currentTime();
		
		executor.execute(new Runnable()
		{
			public final void run()
			{
				process(time, event);
			}
		});
	}

	public final Set<JavaElementEventType> getEventTypes()
	{
		return ImmutableSet.of(POST_CHANGE, POST_RECONCILE);
	}
}
