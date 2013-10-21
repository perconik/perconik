package conmark.popup.actions;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;


import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jdt.ui.IWorkingCopyManager;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.ui.JavaUI;

import conmark.Activator;
import conmark.assistant.ConPopupDialog;
import conmark.utils.MarkTemplate;

public class MarkAction implements IEditorActionDelegate {
	
	private IEditorPart editorPart = null;
	
	@Override
	public void run(IAction act) {		
		try {
			if(editorPart instanceof ITextEditor){
				ITextEditor texteEditor = (ITextEditor)editorPart;
				
				IDocumentProvider dp = texteEditor.getDocumentProvider();
				IDocument doc = dp.getDocument(texteEditor.getEditorInput());	
				
				ITextSelection selection = (ITextSelection)editorPart.getEditorSite().getSelectionProvider().getSelection();
				ConPopupDialog d = new ConPopupDialog(selection, doc, editorPart.getEditorSite().getShell());
				d.open();
				
//				String t = d.getTxt();
//				
//				System.out.println(">>"+t);
//				
//				if(t == null || t.isEmpty()) return;
//				
//				
				
				
			}
//				final int begin = selection.getOffset();
//				final int end = begin + selection.getLength();
//				
//				final AtomicInteger startIndex = new AtomicInteger(-1);
//				final AtomicInteger endIndex = new AtomicInteger(-1);
//				
//				IEditorInput editorInput = this.editorPart.getEditorInput();
//				IWorkingCopyManager manager = JavaUI.getWorkingCopyManager();
//				ICompilationUnit compUnit = manager.getWorkingCopy(editorInput); 
//
//				ASTParser parser = ASTParser.newParser(AST.JLS4);
//				parser.setSource(compUnit);
//				CompilationUnit comUnit = (CompilationUnit) parser.createAST(null);
//				
//				comUnit.accept(new ASTVisitor(true) {
//					@Override
//					public void preVisit(ASTNode node) {
//						int a = node.getStartPosition();
//						
//						int i = startIndex.get();
//						if(i < 0) i = a;
//						else if(begin >= a) i = i > a ? i : a;
//						startIndex.set(i);
//						
//						a += node.getLength();
//						
//						i = endIndex.get();					
//						if(i < 0) i = a;
//						else if(end <= a) i = i < a ? i : a;
//						endIndex.set(i);
//					}					
//				});
//				
//				int startLine = selection.getStartLine();
//				int tmp = doc.getLineOfOffset(startIndex.get());
//				startLine = startLine < tmp ? startLine : tmp;
//				
//				int endLine = selection.getEndLine();
//				tmp = doc.getLineOfOffset(endIndex.get());
//				endLine = endLine > tmp ? endLine : tmp;
//				
//				if(startLine == endLine){					
//					int offset = doc.getLineOffset(startLine);
//					String line = doc.get(offset, doc.getLineLength(startLine));
//					
//					StringBuilder sb = new StringBuilder();
//					for(char c : line.toCharArray()){
//						if(Character.isWhitespace(c)){
//							sb.append(c);
//						}else{
//							break;
//						}
//					}
//					
//					sb.append("// | ");
//					sb.append(new MarkTemplate().getInfo());
//					sb.append("\n");
//					
//					doc.replace(offset, 0, sb.toString());					
//				}else{
//					StringBuilder sbstart = new StringBuilder();
//					int startOffest = doc.getLineOffset(startLine);
//					String line = doc.get(startOffest, doc.getLineLength(startLine));
//					
//					for(char c : line.toCharArray()){
//						if(Character.isWhitespace(c)){
//							sbstart.append(c);
//						}else{
//							break;
//						}
//					}
//					
//					int tmpOf = doc.getLineOffset(endLine);
//					int tmpLen = doc.getLineLength(endLine);
//					
//					StringBuilder sbend = new StringBuilder();
//					line = doc.get(tmpOf, tmpLen);
//					
//					for(char c : line.toCharArray()){
//						if(Character.isWhitespace(c)) sbend.append(c);
//						else break;						
//					}
//					
//					MarkTemplate templ = new MarkTemplate();
//					
//					sbend.append("//@< | ");
//					sbend.append(templ.getInfo());
//					sbend.append("\n");					
//					doc.replace(tmpOf+tmpLen, 0, sbend.toString());
//					
//					sbstart.append("// | ");
//					sbstart.append(templ.getInfo());
//					sbstart.append("\n");
//					
//					doc.replace(startOffest, 0, sbstart.toString());					
//				}				
//			}		
		} catch (Exception e) {
			Activator.getDefault().getLog().log( new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Exception", e) );
		}
	}

	@Override
	public void setActiveEditor(IAction act, IEditorPart sel) {
		this.editorPart = sel;
	}

	
	@Override
	public void selectionChanged(IAction act, ISelection sel) {}		
} 
