package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.util.Collection;
import java.util.Map;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.ui.IPackagesViewPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.navigator.CommonNavigator;
import com.google.common.collect.Maps;

/**
 * Static utility methods pertaining to Eclipse projects.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Projects
{
	private Projects()
	{
		throw new AssertionError();
	}
	
	public static final IProject getProject(final IWorkbenchPage page)
	{
		IEditorPart editor = page.getActiveEditor();

		if (editor != null)
		{
			return getProject(editor);
		}

		for (IViewReference reference: page.getViewReferences())
		{
			IViewPart view = reference.getView(false);
			
			ISelection selection = null;

			if (view instanceof IPackagesViewPart)
			{
				selection = ((IPackagesViewPart) view).getTreeViewer().getSelection();
			}
			else if (view instanceof CommonNavigator)
			{
				selection = ((CommonNavigator) view).getCommonViewer().getSelection();
			}

			if (selection instanceof IStructuredSelection)
			{
				IProject project = getProject((IStructuredSelection) selection);

				if (project != null)
				{
					return project;
				}
			}
		}

		return null;
	}
	
	public static final IProject getProject(final IStructuredSelection selection)
	{
		Object element = selection.getFirstElement();
	
		if (element instanceof IResource)
		{
			return ((IResource) element).getProject();
		}
		else if (element instanceof IJavaElement)
		{
			return ((IJavaElement) element).getJavaProject().getProject();
		}
		else if (element instanceof IAdaptable)
		{
			IAdaptable adaptable = (IAdaptable) element;
			
			IWorkbenchAdapter adapter = (IWorkbenchAdapter) adaptable.getAdapter(IWorkbenchAdapter.class);
			
			if (adapter != null)
			{
				Object parent = adapter.getParent(adaptable);
				
				if (parent instanceof IJavaProject)
				{
					return ((IJavaElement) parent).getJavaProject().getProject();
				}
			}
		}
	
		return null;
	}

	public static final IProject getProject(final IEditorPart editor)
	{
		IEditorInput input = editor.getEditorInput();
		
		if (input instanceof IFileEditorInput)
		{
			IFile file = ((IFileEditorInput) input).getFile();
			
			if (file != null)
			{
				return file.getProject();
			}
		}
		
		IResource resource = (IResource) input.getAdapter(IResource.class);
		
		return resource != null ? resource.getProject() : null;
	}

	public static final Collection<IProject> getProjects(final ILaunch launch)
	{
		return getProjects(launch.getLaunchConfiguration());
	}

	public static final Collection<IProject> getProjects(final ILaunchConfiguration configuration)
	{
		IResource[] resources;
		
		try
		{
			resources = configuration.getMappedResources();
		}
		catch (CoreException e)
		{
			throw new IllegalStateException(e);
		}
		
		Map<String, IProject> projects = Maps.newHashMapWithExpectedSize(resources.length);
		
		for (IResource resource: resources)
		{
			IProject project = resource.getProject();
			
			projects.put(project.getFullPath().toString(), project);
		}
		
		return projects.values();
	}
}
