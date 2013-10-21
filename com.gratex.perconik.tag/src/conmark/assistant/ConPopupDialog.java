package conmark.assistant;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import conmark.utils.MarkTemplate;
import conmark.ws.WsTags;

public class ConPopupDialog extends PopupDialog{

	class Data{
		String nm;
		String it;
		
		public Data(String nm, String it) {
			this.nm = nm;
			this.it = it;
		}
		
		@Override
		public String toString() {
			return nm + " : " + it;
		}

		public String getNm() {
			return nm;
		}

		public String getIt() {
			return nm + " " + it;
		}		
		
	}
	
	public String trim(String value) {
        int len = value.length();
        int st = 0;
        char[] val = value.toCharArray();    /* avoid getfield opcode */

//        while ((st < len) && (val[st] <= ' ')) {
//            st++;
//        }
        while ((st < len) && (val[len - 1] <= ' ')) {
            len--;
        }
        return ((st > 0) || (len < value.length())) ? value.substring(st, len) : value;
    }
	
	private IDocument doc;
	ITextSelection selection;
	
	void done(String it){
		try{
			int startLine = selection.getStartLine();
			int endLine = selection.getEndLine();
			
			if(startLine == endLine){
				int offset = selection.getOffset(); //doc.getLineOffset(startLine);
				int startOffest = doc.getLineOffset(startLine);
				String line = doc.get(startOffest, doc.getLineLength(startLine));
				
				line = line.replace("\n", "").replace("\r", "");
				
				String ln = trim(line);
				
				StringBuilder sb = new StringBuilder();
				sb.append("//"+it+" | ");
				sb.append(new MarkTemplate().getInfo());
				//sb.append("\n");
				
				if(!ln.isEmpty() && offset < startOffest+ln.length()){
					
					StringBuilder s = new StringBuilder();
					for(char c : line.toCharArray()){
						if(Character.isWhitespace(c)){
							s.append(c);
						}else{
							break;
						}
					}
					s.append(sb).append("\n");
					
					doc.replace(doc.getLineOffset(startLine), 0, s.toString());
				}else{
					if(ln.isEmpty()){
						doc.replace(doc.getLineOffset(startLine)+line.length(), 0, sb.toString());
					}else{
						doc.replace(doc.getLineOffset(startLine) + ln.length(), line.length() - ln.length(), sb.toString());
					}
				}
			}else{
				
				StringBuilder sbstart = new StringBuilder();
				int startOffest = doc.getLineOffset(startLine);
				
				String line = doc.get(startOffest, doc.getLineLength(startLine));
				
				for(char c : line.toCharArray()){
					if(Character.isWhitespace(c)){
						sbstart.append(c);
					}else{
						break;
					}
				}
				
				int tmpOf = doc.getLineOffset(endLine);
				int tmpLen = doc.getLineLength(endLine);
				
				StringBuilder sbend = new StringBuilder();
				line = doc.get(tmpOf, tmpLen);
				
				for(char c : line.toCharArray()){
					if(Character.isWhitespace(c)) sbend.append(c);
					else break;						
				}
				
				MarkTemplate templ = new MarkTemplate();
				
				sbend.append("//@< | ");
				sbend.append(templ.getInfo());
				sbend.append("\n");					
				doc.replace(tmpOf+tmpLen, 0, sbend.toString());
				
				sbstart.append("//"+it+" | ");
				sbstart.append(templ.getInfo());
				sbstart.append("\n");
				
				doc.replace(startOffest, 0, sbstart.toString());					
				
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	public ConPopupDialog(ITextSelection selection, IDocument doc, Shell parent){
		super(parent, SWT.RESIZE, true, true, true, true, true, "Insert tag", null);
		this.doc = doc;
		this.selection = selection;
	}
	
	@Override
	public int open() {
		int r = super.open();
		getShell().setSize(300, 150);
		return r;
	}
		
	@Override
	protected Control createDialogArea(Composite parent) {
		// Composite parent, int style, ILabelProvider labelProvider, boolean ignoreCase, boolean allowDuplicates, boolean matchEmptyString
		
//		Composite c = new Composite(parent, SWT.DEFAULT);
		
		FilteredTree filteredTree = new FilteredTree(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, new PatternFilter(), true);

		final TreeViewer actionViewer = filteredTree.getViewer();
		
		final ArrayList<Data> al = new ArrayList<>();
		for(WsTags t : WsTags.load()){
			al.add(new Data(t.name() , t.insertionText()));
		}		
		
		actionViewer.setContentProvider(new ITreeContentProvider() {
			
			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}
			
			@Override
			public void dispose() {}
			
			@Override
			public boolean hasChildren(Object element) {
				return false;
			}
			
			@Override
			public Object getParent(Object element) {
				return null;
			}
			
			@Override
			public Object[] getElements(Object inputElement) {
				return getChildren(inputElement);
			}
			
			@Override
			public Object[] getChildren(Object parentElement) {
				if(parentElement instanceof Data) return new Object[0];
				return al.toArray();
			}
		});
		actionViewer.setInput(al);
		actionViewer.setLabelProvider(new LabelProvider());

		actionViewer.getTree().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				Object sel = ((IStructuredSelection)actionViewer.getSelection()).getFirstElement();				
				if(sel != null)done(((Data)sel).getIt());
				ConPopupDialog.this.close();
			}
		});
		
		actionViewer.getTree().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR){ // Enter key
					Object sel = ((IStructuredSelection)actionViewer.getSelection()).getFirstElement();				
					if(sel != null)done(((Data)sel).getIt());
					ConPopupDialog.this.close();
			   	} 
			}
		});
		
		return filteredTree;
		
		
//		final FilteredList tv = new FilteredList(parent, SWT.H_SCROLL | SWT.V_SCROLL, new LabelProvider(), true, true, true);
//		
//		ArrayList<Data> al = new ArrayList<>();
//		for(WsTags t : WsTags.load()){
//			al.add(new Data(t.name() , t.insertionText()));
//		}		
//		tv.setElements(al.toArray());
//		
//		tv.setFilter("");
//		
//		tv.addMouseListener(new MouseListener() {			
//			@Override
//			public void mouseUp(MouseEvent e) {}			
//			@Override
//			public void mouseDown(MouseEvent e) {}
//			
//			@Override
//			public void mouseDoubleClick(MouseEvent e) {
//				Object sel[] = tv.getSelection();
//				txt = sel == null || sel.length == 0 ? null : ((Data)sel[0]).it;
//				ConPopupDialog.this.close();
//			}		
//		});
//		
//		tv.addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyReleased(KeyEvent e) {				
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.keyCode == 0x0D || e.keyCode == SWT.KEYPAD_CR){ // Enter key
//					Object sel[] = tv.getSelection();
//					txt = sel == null || sel.length == 0 ? null : ((Data)sel[0]).it;
//					ConPopupDialog.this.close();
//			   	} 
//			}
//		});
//		
//		return tv;
		
//		fTreeViewer = createTreeViewer(parent, fTreeStyle);
//
//		fCustomFiltersActionGroup= new CustomFiltersActionGroup(getId(), fTreeViewer);
//
//		final Tree tree= fTreeViewer.getTree();
//		tree.addKeyListener(new KeyListener() {
//			public void keyPressed(KeyEvent e)  {
//				if (e.character == 0x1B) // ESC
//					dispose();
//			}
//			public void keyReleased(KeyEvent e) {
//				// do nothing
//			}
//		});
//
//		tree.addSelectionListener(new SelectionListener() {
//			public void widgetSelected(SelectionEvent e) {
//				// do nothing
//			}
//			public void widgetDefaultSelected(SelectionEvent e) {
//				gotoSelectedElement();
//			}
//		});
//
//		tree.addMouseMoveListener(new MouseMoveListener()	 {
//			TreeItem fLastItem= null;
//			public void mouseMove(MouseEvent e) {
//				if (tree.equals(e.getSource())) {
//					Object o= tree.getItem(new Point(e.x, e.y));
//					if (fLastItem == null ^ o == null) {
//						tree.setCursor(o == null ? null : tree.getDisplay().getSystemCursor(SWT.CURSOR_HAND));
//					}
//					if (o instanceof TreeItem) {
//						Rectangle clientArea = tree.getClientArea();
//						if (!o.equals(fLastItem)) {
//							fLastItem= (TreeItem)o;
//							tree.setSelection(new TreeItem[] { fLastItem });
//						} else if (e.y - clientArea.y < tree.getItemHeight() / 4) {
//							// Scroll up
//							Point p= tree.toDisplay(e.x, e.y);
//							Item item= fTreeViewer.scrollUp(p.x, p.y);
//							if (item instanceof TreeItem) {
//								fLastItem= (TreeItem)item;
//								tree.setSelection(new TreeItem[] { fLastItem });
//							}
//						} else if (clientArea.y + clientArea.height - e.y < tree.getItemHeight() / 4) {
//							// Scroll down
//							Point p= tree.toDisplay(e.x, e.y);
//							Item item= fTreeViewer.scrollDown(p.x, p.y);
//							if (item instanceof TreeItem) {
//								fLastItem= (TreeItem)item;
//								tree.setSelection(new TreeItem[] { fLastItem });
//							}
//						}
//					} else if (o == null) {
//						fLastItem= null;
//					}
//				}
//			}
//		});
//
//		tree.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseUp(MouseEvent e) {
//
//				if (tree.getSelectionCount() < 1)
//					return;
//
//				if (e.button != 1)
//					return;
//
//				if (tree.equals(e.getSource())) {
//					Object o= tree.getItem(new Point(e.x, e.y));
//					TreeItem selection= tree.getSelection()[0];
//					if (selection.equals(o))
//						gotoSelectedElement();
//				}
//			}
//		});
//
//		installFilter();
//
//		addDisposeListener(this);
//		return fTreeViewer.getControl();
	}
	
}
