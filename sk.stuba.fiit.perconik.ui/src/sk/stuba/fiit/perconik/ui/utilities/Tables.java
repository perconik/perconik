package sk.stuba.fiit.perconik.ui.utilities;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public final class Tables
{
	private Tables()
	{
		throw new AssertionError();
	}
	
	public static final Table create(final Composite parent, final int style)
	{
        Table table = new Table(parent, style);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		return table;
	}
	
	public static final TableColumn createColumn(final Table table, final TableColumnLayout layout, final String text, final GC gc, final int weight)
	{
		TableColumn column = new TableColumn(table, SWT.NONE);
		
		column.setText(text);
		
		ColumnWeightData data = new ColumnWeightData(weight, getMinimumColumnWidth(gc, column.getText()), true);
		
		layout.setColumnData(column, data);

		return column;
	}

	public static final int getMinimumColumnWidth(final GC gc, final String s)
	{
		return gc.stringExtent(s).x + 10;
	}
}
