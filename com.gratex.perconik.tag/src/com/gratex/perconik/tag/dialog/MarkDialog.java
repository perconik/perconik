package com.gratex.perconik.tag.dialog;
//package conmark.dialog;
//
//import java.awt.DisplayMode;
//import java.awt.GraphicsEnvironment;
//
//import org.eclipse.core.runtime.IStatus;
//import org.eclipse.core.runtime.Status;
//import org.eclipse.jface.bindings.keys.KeyStroke;
//import org.eclipse.jface.bindings.keys.ParseException;
//import org.eclipse.jface.dialogs.Dialog;
//import org.eclipse.jface.fieldassist.ContentProposalAdapter;
//import org.eclipse.jface.fieldassist.TextContentAdapter;
//import org.eclipse.jface.text.BadLocationException;
//import org.eclipse.jface.text.IDocument;
//import org.eclipse.jface.window.IShellProvider;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Text;
//
//import conmark.Activator;
//import conmark.assistant.ConAssist;
//
//public class MarkDialog extends Dialog{
//	
//	@Override
//	protected boolean isResizable() { return true; }
//	
//	public MarkDialog(IShellProvider parentShell) { super(parentShell); }
//	
//	@Override
//	protected void configureShell(Shell shell) {
//		super.configureShell(shell);
//		shell.setText("Markers");
//		shell.setSize(350, 250);
//
//		DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
//		shell.setLocation(dm.getWidth()/2 - 350/2, dm.getHeight()/2 - 250/2);
//	}
//	
//	private Text idText;
//	private Text msgText;
//	
//	@Override
//	protected Control createDialogArea(Composite parent) {
//		Composite comp = (Composite) super.createDialogArea(parent);
//		comp.setLayout(new GridLayout(2, false));
//		
//		{
//	    	Label idLabel = new Label(comp, SWT.NONE);
//	    	idLabel.setText("id: ");
//	    	idLabel.setLayoutData( new GridData(SWT.BEGINNING, SWT.TOP, false, false) );
//	    }{
//	    	idText = new Text(comp, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
//	    	idText.setEnabled(false);
//	    	idText.setText(this.id);
//	    	idText.setLayoutData( new GridData(SWT.FILL, SWT.TOP, true, false) );
//	    }{
//	    	Label keyWordLabel = new Label(comp, SWT.NONE);
//	    	keyWordLabel.setText("Mark: ");
//	    	keyWordLabel.setLayoutData( new GridData(SWT.BEGINNING, SWT.TOP, false, false) );
//	    }{
//	    	msgText = new Text(comp, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
//	    	msgText.setLayoutData( new GridData(SWT.FILL, SWT.TOP, true, false) );
//	    	msgText.setText(this.msg);
//	    	
//	    	try {
//				new ContentProposalAdapter(msgText, new TextContentAdapter(), new ConAssist() , KeyStroke.getInstance("Ctrl+Space"), "#:".toCharArray());				
//			} catch (ParseException e) {
//				Activator.getDefault().getLog().log( new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Exception", e) );
//			}
//	    }{
//	    	Label keyWordLabel = new Label(comp, SWT.NONE);
//	    	keyWordLabel.setText("Help: ");
//	    	keyWordLabel.setLayoutData( new GridData(SWT.BEGINNING, SWT.TOP, false, false) );
//	    }{
//	    	Label help = new Label(comp, SWT.NONE);
//	    	help.setLayoutData( new GridData(SWT.FILL, SWT.TOP, true, false) );
//	    	help.setText("[+/-N]    : znamka\n" +
//	    			     "#abc      : keyword\n" +
//	    			     "key:value : key/value");
//	    }
//		
//		return comp;
//	}
//	
//	public void open(IDocument doc, int off, int len, String id, String msg){
//		this.doc = doc;
//		this.off = off;
//		this.len = len;
//		
//		this.id = id;
//		this.msg = msg.startsWith(" ") ? msg.substring(1) : msg;
//		
//		this.open();		
//	}
//	
//	private IDocument doc;
//	private int off;
//	private int len;
//	
//	private String id;
//	private String msg;
//	
//	@Override
//	protected void okPressed() {
//		// something
//		String m = this.msgText.getText();
//		if(!m.startsWith(" ")) m = " "+m;
//		
//		try {
//			doc.replace(off, len, id+m);
//			
//		} catch (BadLocationException e) {
//			Activator.getDefault().getLog().log( new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Exception", e) );
//		}
//		
//		super.okPressed();
//		
//	}
//	
//	
//	
//}
