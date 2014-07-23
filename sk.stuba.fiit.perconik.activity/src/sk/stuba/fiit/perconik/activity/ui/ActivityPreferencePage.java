package sk.stuba.fiit.perconik.activity.ui;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import sk.stuba.fiit.perconik.activity.plugin.Activator;
import sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences;

public abstract class ActivityPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	protected BooleanFieldEditor logErrors;
	
	protected BooleanFieldEditor logEvents;
	
	public ActivityPreferencePage()
	{
		super(GRID);
	}

	public final void init(final IWorkbench workbench)
	{
		this.setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
	
	protected static final <E extends StringFieldEditor> E prepare(final E editor)
	{
		editor.setEmptyStringAllowed(false);
		editor.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
		
		return editor;
	}
	
	@Override
	protected final IPreferenceStore doGetPreferenceStore()
	{
		return ElasticsearchPreferences.getInstance().getPreferenceStore();
	}
}
