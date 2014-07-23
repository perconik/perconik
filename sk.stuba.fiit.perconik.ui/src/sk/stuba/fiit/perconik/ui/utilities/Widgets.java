package sk.stuba.fiit.perconik.ui.utilities;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public final class Widgets
{
	private Widgets()
	{
		throw new AssertionError();
	}
	
	public final static Label createButtonSeparator(final Composite parent)
	{
		Label separator = new Label(parent, SWT.NONE);
	
		separator.setVisible(false);
		
		GridData data = new GridData();
		
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment   = GridData.BEGINNING;
		data.heightHint          = 4;
		
		separator.setLayoutData(data);
		
		return separator;
	}
	
	public static final Label createFieldSeparator(final Composite parent)
	{
		Label separator = new Label(parent, SWT.NONE);
		
		GridData data  = new GridData(GridData.FILL_HORIZONTAL);
		
		data.horizontalSpan = 2;
		
		separator.setLayoutData(data);
		
		return separator;
	}
}
