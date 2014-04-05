package com.gratex.perconik.tag.prefs;

import java.net.URL;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import sk.stuba.fiit.perconik.eclipse.jface.preference.ExtendedBooleanFieldEditor;
import sk.stuba.fiit.perconik.ui.utilities.Widgets;
import com.gratex.perconik.services.TagProfileWcfSvc;
import com.gratex.perconik.services.tag.ArrayOfTagProfileSearchResItemDto;
import com.gratex.perconik.services.tag.SearchTagProfileRequest;
import com.gratex.perconik.services.tag.SearchTagProfileResponse;
import com.gratex.perconik.services.tag.TagProfileSearchResItemDto;
import com.gratex.perconik.tag.assistant.ConAssist;
import com.gratex.perconik.tag.plugin.Activator;
import com.gratex.perconik.tag.ws.WsTags;

public class PrefPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage{

	public PrefPage() {
		super(GRID);
	}
	
	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());		
	}

	StringFieldEditor f;
	StringFieldEditor u;
	StringFieldEditor t;
	ExtendedBooleanFieldEditor v;
	
	static final <E extends StringFieldEditor> E prepare(final E editor)
	{
		editor.setEmptyStringAllowed(false);
		editor.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
		
		return editor;
	}
	
	@Override
	protected void createFieldEditors() {
		t = new StringFieldEditor(PrefKeys.url, "URL:", this.getFieldEditorParent());
		f = new StringFieldEditor(PrefKeys.profile, "Profile:", this.getFieldEditorParent());
		u = new StringFieldEditor(PrefKeys.user, "User:", this.getFieldEditorParent());
		
		Widgets.createFieldSeparator(this.getFieldEditorParent());

		v = new ExtendedBooleanFieldEditor(PrefKeys.validate, "Validate service on confirmation", this.getFieldEditorParent());
		
		addField(prepare(t));
		addField(prepare(f));
		addField(prepare(u));
		
		addField(v);
		
//		t.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
//		f.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
		
	/*	String s[] = RepositorySupport.get().getProfiles();
		String ss[][] = new String[s.length][2];
		for(int i = 0; i < s.length; i++) ss[i][0] = ss[i][1] = s[i];
		
		addField(new ComboFieldEditor(RepositoryLocation.repProfile, "Profile:", ss, getFieldEditorParent())); */		
	}
	
	@Override
	public boolean performOk() {
		return super.performOk() && (this.v.getBooleanValue() ? this.validate() : true);
	}
	
	boolean validate() {
		try {
			String tmp = t.getStringValue();
			if(!tmp.endsWith("/")) tmp = tmp + "/";
			TagProfileWcfSvc s = new TagProfileWcfSvc(new URL(tmp+"TagProfileWcfSvc.svc?singleWsdl"));
			SearchTagProfileRequest r = new SearchTagProfileRequest();
			r.setNameStartPart(f.getStringValue());
			//r.setNameStartPart("Test1");
			
			String id = null;
			
			SearchTagProfileResponse rs = s.getBasicHttpBindingITagProfileWcfSvc().searchTagProfile(r);
			ArrayOfTagProfileSearchResItemDto a = rs.getResultSet();
			for(TagProfileSearchResItemDto i : a.getTagProfileSearchResItem()){
				id = i.getId();
				break;
			}
			
			if(id == null || id.isEmpty()){				
				//MessageDialog.openError(this.getShell(), "Service error", "Profile not found.");
				boolean validate = MessageDialogWithToggle.openError(this.getShell(), "Tag profile service error", "Profile not found.", "Always validate on confirmation", this.v.getBooleanValue(), this.getPreferenceStore(), this.v.getPreferenceName()).getToggleState();
				this.v.getChangeControl().setSelection(validate);
				return false;
			}
		} catch (Exception e) {
			//MessageDialog.openError(this.getShell(), "Service error", e.getMessage());
			boolean validate = MessageDialogWithToggle.openError(this.getShell(), "Tag profile service error", e.getMessage(), "Always show on confirmation", this.v.getBooleanValue(), this.getPreferenceStore(), this.v.getPreferenceName()).getToggleState();
			this.v.getChangeControl().setSelection(validate);
			return false;
		}
		
		//boolean b = super.performOk();
		ConAssist.clear();
		WsTags.clear();
		//return b;
		return true;
	}	
	
}
