package com.gratex.perconik.activity.ide.listeners;

import sk.stuba.fiit.perconik.core.annotations.Experimental;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;

//import static com.gratex.perconik.activity.ide.IdeActivityServices.performWatcherServiceOperation;
//import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setApplicationData;
//import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setEventData;
//import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setProjectData;
//import java.util.Map;
//import javax.annotation.concurrent.GuardedBy;
//import org.eclipse.jdt.core.IJavaElement;
//import org.eclipse.jdt.core.dom.ASTNode;
//import org.eclipse.jface.text.IViewportListener;
//import org.eclipse.jface.text.JFaceTextUtil;
//import org.eclipse.jface.text.source.ISourceViewer;
//import org.eclipse.ui.IEditorPart;
//import org.eclipse.ui.IEditorReference;
//import sk.stuba.fiit.perconik.core.annotations.Experimental;
//import sk.stuba.fiit.perconik.core.annotations.Unsupported;
//import sk.stuba.fiit.perconik.core.java.dom.NodePaths;
//import sk.stuba.fiit.perconik.core.listeners.EditorListener;
//import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;
//import sk.stuba.fiit.perconik.eclipse.ui.Editors;
//import com.google.common.collect.Maps;
//import com.gratex.perconik.activity.ide.IdeActivityServices.WatcherServiceOperation;
//import com.gratex.perconik.activity.ide.IdeApplication;
//import com.gratex.perconik.services.IVsActivityWatcherService;
//import com.gratex.perconik.services.uaca.vs.IdeCodeElementEventDto;
//import com.gratex.perconik.services.uaca.vs.IdeCodeElementEventTypeEnum;

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
public final class IdeElementListener extends IdeListener // implements EditorListener
{
}

//
//	private final Object lock = new Object();
//	
//	@GuardedBy("lock")
//	private final Map<ISourceViewer, IViewportListener> viewersToListeners;
//	
//	public IdeElementListener()
//	{
//		this.viewersToListeners = Maps.newHashMap();
//	}
//
//	static final void send(final IdeCodeElementEventDto data)
//	{
//		performWatcherServiceOperation(new WatcherServiceOperation()
//		{
//			public final void perform(final IVsActivityWatcherService service)
//			{
//				service.notifyIdeCodeElementEvent(data);
//			}
//		});
//	}
//	
//	static final IdeCodeElementEventDto build(final long time, final IJavaElement element, final ASTNode node, final IdeCodeElementEventTypeEnum type)
//	{
//		final IdeCodeElementEventDto data = new IdeCodeElementEventDto();
//
//		data.setEventType(type);
//		
//		data.setElementType(NodeType.valueOf(node).toString());
//		data.setElementFullName(NodePaths.namePathExtractor().apply(node).toString());
//		
//		// TODO
//		if (IdeApplication.getInstance().isDebug()){
//		System.out.println("-- ELEMENT --");
//		System.out.println(data.getElementType());
//		System.out.println(data.getElementFullName());
//		}
//		
//		setProjectData(data, element.getJavaProject().getProject());
//		setApplicationData(data);
//		setEventData(data, time);
//		
//		return data;
//	}
//	
//	final void process(final long time, final IEditorPart editor)
//	{
//		ISourceViewer viewer = Editors.getSourceViewer(editor);
//		
//		synchronized (this.lock)
//		{
//			if (!this.viewersToListeners.containsKey(viewer))
//			{
//				this.viewersToListeners.put(viewer, new ScrollListener(viewer));
//			}
//		}
//
//		
//	}
//
//	private static final class ScrollListener implements IViewportListener
//	{
//		private final ISourceViewer viewer;
//		
//		ScrollListener(ISourceViewer viewer)
//		{
//			assert viewer != null;
//			
//			this.viewer = viewer;
//		}
//
//		public final void viewportChanged(int offset)
//		{
//			int c = JFaceTextUtil.getPartialTopIndex(this.viewer);
//			int d = JFaceTextUtil.getPartialBottomIndex(this.viewer);
//
//			console.print("partially visible " + c + " - " + d);
//			
//			
//			
//		}
//	}
//	
//	public final void editorOpened(final IEditorReference reference)
//	{
//	}
//
//	public final void editorClosed(final IEditorReference reference)
//	{
//	}
//
//	public final void editorActivated(final IEditorReference reference)
//	{
//		// TODO add postRegister hook for already opened editor
//		
//		final long time = currentTime();
//		
//		executeSafely(new Runnable()
//		{
//			public final void run()
//			{
//				process(time, dereferenceEditor(reference));
//			}
//		});
//		
////		Display.getDefault().asyncExec(new Runnable()
////		{
////			public final void run()
////			{
////				final IEditorPart   editor = dereferenceEditor(reference);
////				final ISourceViewer viewer = Editors.getSourceViewer(editor);
////				
////				int a = viewer.getTopIndex();
////				int b = viewer.getBottomIndex();
////				
////				viewer.addViewportListener(new IViewportListener() {
////					
////					public void viewportChanged(int offset)
////					{
////						int a = viewer.getTopIndex();
////						int b = viewer.getBottomIndex();
////						
////						//console.print("offset: "+offset);
////						//console.print("line: "+text.getLineIndex(offset));
////						console.print("VISIBLE " + a + " - " + b);
////						
////						
////						int c = JFaceTextUtil.getPartialTopIndex(viewer);
////						int d = JFaceTextUtil.getPartialBottomIndex(viewer);
////
////						console.print("partially visible " + c + " - " + d);
////					}
////				});
////				
////				console.print("VISIBLE " + a + " - " + b + "            " + viewer.getClass());
////			}
////		});
//	}
//
//	public final void editorDeactivated(final IEditorReference reference)
//	{
//	}
//
//	public final void editorVisible(final IEditorReference reference)
//	{
//	}
//
//	public final void editorHidden(final IEditorReference reference)
//	{
//	}
//
//	public final void editorBroughtToTop(final IEditorReference reference)
//	{
//	}
//
//	public final void editorInputChanged(final IEditorReference reference)
//	{
//	}
//}

// TODO rm
//public final class IdeElementListener extends IdeListener implements JavaElementListener
//{
//	private final Cache<URI, CompilationUnit> cache;
//	
//	private final CompilationUnitDifferencer differencer;
//	
//	public IdeElementListener()
//	{
//		this.cache = CacheBuilder.newBuilder().weakValues().recordStats().build();
//		
//		this.differencer = new CompilationUnitDifferencer();
//	}
//	
//	private final CompilationUnit cache(final URI uri, final CompilationUnit revised)
//	{
//		CompilationUnit original = this.cache.getIfPresent(uri);
//	
//		if (revised != null)
//		{
//			this.cache.put(uri, revised);
//		}
//		
//		return original;
//	}
//
//	static final void send(final IdeCodeElementEventDto data)
//	{
//		performWatcherServiceOperation(new WatcherServiceOperation()
//		{
//			public final void perform(final IVsActivityWatcherService service)
//			{
//				service.notifyIdeCodeElementEvent(data);
//			}
//		});
//	}
//	
//	static final IdeCodeElementEventDto build(final long time, final IJavaElement element, final ASTNode node, final IdeCodeElementEventTypeEnum type)
//	{
//		final IdeCodeElementEventDto data = new IdeCodeElementEventDto();
//
//		data.setEventType(type);
//		
//		data.setElementType(NodeType.valueOf(node).toString());
//		data.setElementFullName(NodePaths.namePathExtractor().apply(node).toString());
//		
//		// TODO
//		if (IdeApplication.getInstance().isDebug()){
//		System.out.println("-- ELEMENT --");
//		System.out.println(data.getElementType());
//		System.out.println(data.getElementFullName());
//		}
//		
//		setProjectData(data, element.getJavaProject().getProject());
//		setApplicationData(data);
//		setEventData(data, time);
//		
//		return data;
//	}
//	
//	final void process(final long time, final ElementChangedEvent event)
//	{
//		final IJavaElementDelta delta = event.getDelta();
//		
//		if (!JavaElementDeltaFlag.setOf(delta.getFlags()).contains(JavaElementDeltaFlag.AST_AFFECTED))
//		{
//			return;
//		}
//		
//		IJavaElement element = delta.getElement();
//		
//		URI uri = JavaElements.resource(element).getLocationURI();
//		
//		CompilationUnit revised  = delta.getCompilationUnitAST();
//		CompilationUnit original = cache(uri, revised);
//		
//		if (revised != null && original != null)
//		{
//			NodeDeltaSet<ASTNode> difference = this.differencer.difference(original, revised);
//
//			for (NodeDelta<ASTNode> pair: difference)
//			{
//				send(build(time, element, pair.getOriginalNode(), null));
//			}
//		}
//	}
//	
//	public final void elementChanged(final ElementChangedEvent event)
//	{
//		final long time = currentTime();
//		
//		executor.execute(new Runnable()
//		{
//			public final void run()
//			{
//				process(time, event);
//			}
//		});
//	}
//
//	public final Set<JavaElementEventType> getEventTypes()
//	{
//		return ImmutableSet.of(POST_CHANGE, POST_RECONCILE);
//	}
//}
