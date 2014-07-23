package com.gratex.perconik.tag.popup.actions;
//package conmark.popup.actions;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.eclipse.core.runtime.IStatus;
//import org.eclipse.core.runtime.Status;
//import org.eclipse.jface.action.IAction;
//import org.eclipse.jface.text.BadLocationException;
//import org.eclipse.jface.text.IDocument;
//import org.eclipse.jface.text.ITextSelection;
//import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.ui.IEditorActionDelegate;
//import org.eclipse.ui.IEditorPart;
//import org.eclipse.ui.IWorkbench;
//import org.eclipse.ui.PlatformUI;
//import org.eclipse.ui.texteditor.IDocumentProvider;
//import org.eclipse.ui.texteditor.ITextEditor;
//
//import conmark.Activator;
//import conmark.dialog.MarkDialog;
//
//
//public class MarkEdit implements IEditorActionDelegate {
//	
//	// //[©] = @
//	// //[©] < @
//	// //[©] > @ : tato nie	
//	//                                                      01        2                                                                    3
//	private static final Pattern pattern = Pattern.compile("^([\\t ]*)(\\/\\/\\[\\©\\] [\\<\\=] \\@[a-zA-Z0-9]* \\[[0123456789 \\-\\:]*\\])(.*)");
//	
//	private IEditorPart editorPart;
//	
//	@Override
//	public void run(IAction act) {
//		
//		if(editorPart instanceof ITextEditor){
//			ITextEditor texteEditor = (ITextEditor)editorPart;
//			
//			IDocumentProvider dp = texteEditor.getDocumentProvider();
//			IDocument doc = dp.getDocument(texteEditor.getEditorInput());	
//			
//			ITextSelection selection = (ITextSelection)editorPart.getEditorSite().getSelectionProvider().getSelection();
//			int line = selection.getStartLine();
//			try {
//				int off = doc.getLineOffset(line);
//				int len = doc.getLineLength(line);
//				String text = doc.get(off, len);
//				while(text.endsWith("\n") || text.endsWith("\r")){
//					text = text.substring(0, text.length() - 1);
//					len --;
//				}
//				
//				Matcher m = pattern.matcher(text);
//				//System.out.println(">"+text+"<" +" "+m.matches());
//				if(m.matches()){
//					IWorkbench workbench = PlatformUI.getWorkbench();
//					MarkDialog md = new MarkDialog(workbench.getModalDialogShellProvider());
//					int tmp = m.group(1).length();
//					md.open(doc, off + tmp, len-tmp, m.group(2), m.group(3));
//				}				
//			} catch (BadLocationException e) {				
//				Activator.getDefault().getLog().log( new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Exception", e) );
//			}			
//		}
//		
//	}
//
//	@Override
//	public void setActiveEditor(IAction act, IEditorPart sel) {
//		this.editorPart = sel;
//	}
//
//	
//	@Override
//	public void selectionChanged(IAction act, ISelection sel) {}		
//}
