package sk.stuba.fiit.perconik.activity.ide.ui;

import static org.eclipse.jface.preference.BooleanFieldEditor.SEPARATE_LABEL;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import sk.stuba.fiit.perconik.activity.ide.preferences.IdeActivityPreferenceKeys;
import sk.stuba.fiit.perconik.activity.ide.preferences.IdeActivityPreferences;
import sk.stuba.fiit.perconik.activity.plugin.Activator;

public final class IdeActivityPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	private BooleanFieldEditor logErrors;
	
	private BooleanFieldEditor logEvents;
	
	public IdeActivityPreferencePage()
	{
		super(GRID);
	}

	public final void init(final IWorkbench workbench)
	{
		this.setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
	
	@Override
	protected final void createFieldEditors()
	{
		this.logErrors = new BooleanFieldEditor(IdeActivityPreferenceKeys.logErrors, "Log errors:", SEPARATE_LABEL, this.getFieldEditorParent());
		this.logEvents = new BooleanFieldEditor(IdeActivityPreferenceKeys.logEvents, "Log events:", SEPARATE_LABEL, this.getFieldEditorParent());

		this.addField(this.logErrors);
		this.addField(this.logEvents);
	}
	
	@Override
	protected final IPreferenceStore doGetPreferenceStore()
	{
		return IdeActivityPreferences.getPreferenceStore();
	}

	@Override
	public final boolean performOk()
	{
		// TODO
		
		return super.performOk();
	}
}
