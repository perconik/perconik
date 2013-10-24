package com.gratex.perconik.tag.builder;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.gratex.perconik.tag.plugin.Activator;

public class ToggleNatureAction extends AbstractHandler{
	
	// ----------------------------------------
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		IProject project = resolveProject(selection);
		if(project != null){
			toggleNature(project);
		}
		return null;		
	}

	private IProject resolveProject(ISelection selection){
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
				Object element = it.next();
				IProject project = null;
				if (element instanceof IProject) {
					project = (IProject) element;
				} else if (element instanceof IAdaptable) {
					project = (IProject) ((IAdaptable) element) .getAdapter(IProject.class);
				}
				return project;				
			}
		}
		return null;
	}
	
	private void toggleNature(IProject project) {
		try {
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();

			for (int i = 0; i < natures.length; ++i) {
				if (Activator.NATURE_ID.equals(natures[i])) {
					// Remove markers
					String MARKER_TYPE = Activator.MARKER_TYPE;
					project.deleteMarkers(MARKER_TYPE+".Err", false, IResource.DEPTH_INFINITE);
					for(String t : new String[]{"Eq", "Lt", "Gt"}){
						for(String c : new String[]{"Blue", "Red", "Green"}){
							project.deleteMarkers(MARKER_TYPE+"."+t+c, false, IResource.DEPTH_INFINITE);
						}
					}
					
					IMarker mm[] = project.findMarkers(IMarker.PROBLEM, false, IResource.DEPTH_INFINITE);
					if(mm != null){
						for(IMarker m : mm){
							Object o = m.getAttribute(Activator.MARKER_ID);
							if(o instanceof String){
								m.delete();
							}
						}
					}
					
					// Remove the nature
					String[] newNatures = new String[natures.length - 1];
					System.arraycopy(natures, 0, newNatures, 0, i);
					System.arraycopy(natures, i + 1, newNatures, i, natures.length - i - 1);
					description.setNatureIds(newNatures);
					project.setDescription(description, null);
					
					MessageDialog.openInformation(Display.getDefault().getActiveShell(), "PerConIK Tags", "Tag nature disabled.");
					
					return;
				}
			}

			// Add the nature
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = Activator.NATURE_ID;
			description.setNatureIds(newNatures);
			project.setDescription(description, null);
			
			MessageDialog.openInformation(Display.getDefault().getActiveShell(), "PerConIK Tags", "Tag nature enabled.");
			
			//if (Environment.debug) Activator.getDefault().getConsole().print(Arrays.toString(newNatures));
		} catch (CoreException e) {
			Activator.getDefault().getLog().log( new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Exception", e) );
		}
	}

}
