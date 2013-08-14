package sk.stuba.fiit.perconik.ui.preferences;

import java.text.Collator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.persistence.TemplatePersistenceData;
import org.eclipse.jface.util.BidiUtils;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import sk.stuba.fiit.perconik.ui.utilities.Buttons;
import sk.stuba.fiit.perconik.ui.utilities.Tables;
import sk.stuba.fiit.perconik.ui.utilities.Widgets;

public final class ResourcesPreferencePage extends PreferencePage implements IWorkbenchPreferencePage
{
	private CheckboxTableViewer tableViewer;

	private Button addButton;
	
	private Button removeButton;
	
	private Button importButton;
	
	private Button exportButton;
	
	@Override
	public final void init(final IWorkbench workbench)
	{
	}

	@Override
	protected final Control createContents(final Composite ancestor)
	{
		Composite parent = new Composite(ancestor, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		parent.setLayout(layout);

        Composite innerParent = new Composite(parent, SWT.NONE);
        GridLayout innerLayout = new GridLayout();
        innerLayout.numColumns = 2;
        innerLayout.marginHeight = 0;
        innerLayout.marginWidth = 0;
        innerParent.setLayout(innerLayout);
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 2;
        innerParent.setLayoutData(gd);

        Composite tableComposite = new Composite(innerParent, SWT.NONE);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.widthHint = 360;
        data.heightHint = convertHeightInCharsToPixels(10);
        tableComposite.setLayoutData(data);

        TableColumnLayout columnLayout = new TableColumnLayout();
        tableComposite.setLayout(columnLayout);
		Table table = new Table(tableComposite, SWT.CHECK | SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		GC gc = new GC(getShell());
		gc.setFont(JFaceResources.getDialogFont());

		TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setText("col 1");
		int minWidth = Tables.getMinimumColumnWidth(gc, column1.getText());
		columnLayout.setColumnData(column1, new ColumnWeightData(2, minWidth, true));

		TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setText("col 2");
		minWidth =  Tables.getMinimumColumnWidth(gc, column2.getText());
		columnLayout.setColumnData(column2, new ColumnWeightData(1, minWidth, true));

		TableColumn column3 = new TableColumn(table, SWT.NONE);
		column3.setText("col 3");
		minWidth =  Tables.getMinimumColumnWidth(gc, column3.getText());
		columnLayout.setColumnData(column3, new ColumnWeightData(3, minWidth, true));

		TableColumn column4 = new TableColumn(table, SWT.NONE);
		column4.setAlignment(SWT.CENTER);
		column4.setText("col 4");
		minWidth =  Tables.getMinimumColumnWidth(gc, column4.getText());
		minWidth = Math.max(minWidth,  Tables.getMinimumColumnWidth(gc, "hello"));// TODO
		columnLayout.setColumnData(column4, new ColumnPixelData(minWidth, false, false));

		gc.dispose();

		this.tableViewer = new CheckboxTableViewer(table);
		// TODO
		//this.tableViewer.setLabelProvider(new TemplateLabelProvider());
		//this.tableViewer.setContentProvider(new TemplateContentProvider());

		this.tableViewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object object1, Object object2) {
				if ((object1 instanceof TemplatePersistenceData) && (object2 instanceof TemplatePersistenceData)) {
					Template left = ((TemplatePersistenceData) object1).getTemplate();
					Template right = ((TemplatePersistenceData) object2).getTemplate();
					int result = Collator.getInstance().compare(left.getName(), right.getName());
					if (result != 0)
						return result;
					return Collator.getInstance().compare(left.getDescription(), right.getDescription());
				}
				return super.compare(viewer, object1, object2);
			}

			@Override
			public boolean isSorterProperty(Object element, String property) {
				return true;
			}
		});

		this.tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent e) {
				// TODO edit();
			}
		});

		this.tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent e) {
				// TODO selectionChanged1();
			}
		});

		this.tableViewer.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				TemplatePersistenceData d = (TemplatePersistenceData) event.getElement();
				d.setEnabled(event.getChecked());
			}
		});
		
		BidiUtils.applyTextDirection(this.tableViewer.getControl(), BidiUtils.BTD_DEFAULT);

		Composite buttons = new Composite(innerParent, SWT.NONE);
		buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttons.setLayout(layout);

		this.addButton = new Button(buttons, SWT.PUSH);
		this.addButton.setText("Add");
		this.addButton.setLayoutData(Buttons.getGridData(this.addButton));
		this.addButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				// TODO
			}
		});

		this.removeButton = new Button(buttons, SWT.PUSH);
		this.removeButton.setText("Remove");
		this.removeButton.setLayoutData(Buttons.getGridData(this.removeButton));
		this.removeButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				// TODO remove();
			}
		});

		Widgets.createSeparator(buttons);

		this.importButton = new Button(buttons, SWT.PUSH);
		this.importButton.setText("Import");
		this.importButton.setLayoutData(Buttons.getGridData(this.importButton));
		this.importButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				// TODO
			}
		});

		this.exportButton = new Button(buttons, SWT.PUSH);
		this.exportButton.setText("Export");
		this.exportButton.setLayoutData(Buttons.getGridData(this.exportButton));
		this.exportButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				// TODO
			}
		});

		//this.fTableViewer.setInput(fTemplateStore);
		this.tableViewer.setAllChecked(false);
		//this.fTableViewer.setCheckedElements(getEnabledTemplates());

		updateButtons();
		Dialog.applyDialogFont(parent);
		innerParent.layout();

		return parent;
	}
	
	private final void updateButtons()
	{
		IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();
		
		int selectionCount = selection.size();
		int itemCount      = this.tableViewer.getTable().getItemCount();
		
		this.removeButton.setEnabled(selectionCount > 0 && selectionCount <= itemCount);
		this.exportButton.setEnabled(selectionCount > 0);
	}
}
