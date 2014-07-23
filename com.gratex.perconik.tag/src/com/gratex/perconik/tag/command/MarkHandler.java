package com.gratex.perconik.tag.command;
//package conmark.command;
//
//import org.eclipse.core.commands.AbstractHandler;
//import org.eclipse.core.commands.ExecutionEvent;
//import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.ui.IEditorPart;
//import org.eclipse.ui.IWorkbench;
//import org.eclipse.ui.IWorkbenchPage;
//import org.eclipse.ui.IWorkbenchWindow;
//import org.eclipse.ui.PlatformUI;
//
//import conmark.popup.actions.MarkAction;
//
//public class MarkHandler extends AbstractHandler{
//
//	@Override
//	public Object execute(ExecutionEvent event) throws ExecutionException {
//		IWorkbench workbench = PlatformUI.getWorkbench();
//		IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
//
//		final IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
//		if (activePage == null) {
//			return null;
//		}
//
//		IEditorPart activeEditor = activePage.getActiveEditor();
//		
//		MarkAction act = new MarkAction();
//		act.setActiveEditor(null, activeEditor);
//		act.run(null);
//		
//		return null;
//	}
//
//}
