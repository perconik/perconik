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
	
	public final static Label createSeparator(final Composite parent)
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
}
