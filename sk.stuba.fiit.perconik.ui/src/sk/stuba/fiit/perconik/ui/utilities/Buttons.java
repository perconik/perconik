package sk.stuba.fiit.perconik.ui.utilities;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.PixelConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import sk.stuba.fiit.perconik.eclipse.swt.widgets.WidgetListener;

public final class Buttons
{
	private Buttons()
	{
		throw new AssertionError();
	}
	
	public static final Button create(final Composite parent, final String text, final WidgetListener listener)
	{
		Button button = new Button(parent, SWT.PUSH);
		
		button.setText(text);
		button.setLayoutData(getGridData(button));
		button.addListener(SWT.Selection, listener);
		
		return button;
	}
	
	public static final void setDialogFont(final Button button)
	{
		button.setFont(JFaceResources.getDialogFont());
	}
	
	public static final GridData getGridData(final Button button)
	{
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		
		data.widthHint = getWidthHint(button);
		
		return data;
	}
	
	public static final int getWidthHint(final Button button)
	{
		setDialogFont(button);
		
		int width = new PixelConverter(button).convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
		
		return Math.max(width, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
	}
}
