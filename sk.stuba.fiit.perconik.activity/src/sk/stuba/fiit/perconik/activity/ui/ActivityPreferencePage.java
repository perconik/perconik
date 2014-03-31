package sk.stuba.fiit.perconik.activity.ui;

import static org.eclipse.jface.preference.BooleanFieldEditor.SEPARATE_LABEL;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import sk.stuba.fiit.perconik.activity.plugin.Activator;
import sk.stuba.fiit.perconik.activity.preferences.ActivityPreferences.Keys;
import sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences;
import sk.stuba.fiit.perconik.ui.utilities.Widgets;

public final class ActivityPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	private StringFieldEditor clusterName;
	
	private StringFieldEditor indexName;
	
	private StringFieldEditor transportHost;
	
	private IntegerFieldEditor transportPort;
	
	private BooleanFieldEditor transportSniff;
	
	private BooleanFieldEditor logErrors;
	
	private BooleanFieldEditor logEvents;
	
	public ActivityPreferencePage()
	{
		super(GRID);
	}

	public final void init(final IWorkbench workbench)
	{
		this.setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
	
	private static final <E extends StringFieldEditor> E prepare(final E editor)
	{
		editor.setEmptyStringAllowed(false);
		editor.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
		
		return editor;
	}
	
	@Override
	protected final void createFieldEditors()
	{
		this.transportHost  = new StringFieldEditor(ElasticsearchPreferences.Keys.transportHost, "Transport host:", this.getFieldEditorParent());
		this.transportPort  = new IntegerFieldEditor(ElasticsearchPreferences.Keys.transportPort, "Transport port:", this.getFieldEditorParent(), 5);
		this.transportSniff = new BooleanFieldEditor(ElasticsearchPreferences.Keys.transportSniff, "Transport sniff:", SEPARATE_LABEL, this.getFieldEditorParent());

		this.transportPort.setValidRange(0, 65535);
		
		Widgets.createFieldSeparator(this.getFieldEditorParent());
		
		this.clusterName = new StringFieldEditor(ElasticsearchPreferences.Keys.clusterName, "Cluster name:", this.getFieldEditorParent());
		this.indexName   = new StringFieldEditor(ElasticsearchPreferences.Keys.indexName, "Index name:", this.getFieldEditorParent());
		
		Widgets.createFieldSeparator(this.getFieldEditorParent());
		
		this.logErrors = new BooleanFieldEditor(Keys.logErrors, "Log errors:", SEPARATE_LABEL, this.getFieldEditorParent());
		this.logEvents = new BooleanFieldEditor(Keys.logEvents, "Log events:", SEPARATE_LABEL, this.getFieldEditorParent());

		this.addField(prepare(this.transportHost));
		this.addField(prepare(this.transportPort));
		this.addField(this.transportSniff);
		
		this.addField(prepare(this.clusterName));
		this.addField(prepare(this.indexName));
		
		this.addField(this.logErrors);
		this.addField(this.logEvents);
	}
	
	@Override
	protected final IPreferenceStore doGetPreferenceStore()
	{
		return ElasticsearchPreferences.getInstance().getPreferenceStore();
	}

	@Override
	public final boolean performOk()
	{
		// TODO
		
		return super.performOk();
	}
}
