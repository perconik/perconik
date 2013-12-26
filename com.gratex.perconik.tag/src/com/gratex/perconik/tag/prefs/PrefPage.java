package com.gratex.perconik.tag.prefs;

import java.net.URL;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import com.gratex.perconik.services.TagProfileWcfSvc;
import com.gratex.perconik.services.tag.ArrayOfTagProfileSearchResItemDto;
import com.gratex.perconik.services.tag.SearchTagProfileRequest;
import com.gratex.perconik.services.tag.SearchTagProfileResponse;
import com.gratex.perconik.services.tag.TagProfileSearchResItemDto;
import com.gratex.perconik.tag.assistant.ConAssist;
import com.gratex.perconik.tag.plugin.Activator;
import com.gratex.perconik.tag.ws.WsTags;

public class PrefPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage{

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());		
	}

	StringFieldEditor f;
	StringFieldEditor u;
	StringFieldEditor t;
	
	private static final <E extends StringFieldEditor> E prepare(final E editor)
	{
		editor.setEmptyStringAllowed(false);
		editor.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
		
		return editor;
	}
	
	@Override
	protected void createFieldEditors() {
		Composite parent = this.getFieldEditorParent();
		
		t = prepare(new StringFieldEditor(PrefKeys.url, "URL:", parent));
		f = prepare(new StringFieldEditor(PrefKeys.profile, "Profile:", parent));
		u = prepare(new StringFieldEditor(PrefKeys.user, "User:", parent));
		
		addField(t);
		addField(f);
		addField(u);
		
		
//		t.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
//		f.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
		
	/*	String s[] = RepositorySupport.get().getProfiles();
		String ss[][] = new String[s.length][2];
		for(int i = 0; i < s.length; i++) ss[i][0] = ss[i][1] = s[i];
		
		addField(new ComboFieldEditor(RepositoryLocation.repProfile, "Profile:", ss, getFieldEditorParent())); */		
	}
	
	@Override
	public boolean performOk() {
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
				MessageDialog.openError(this.getShell(), "Service error", "Profile not found.");				
				return false;
			}
		} catch (Exception e) {
			MessageDialog.openError(this.getShell(), "Service error", e.getMessage());
			return false;
		}
		
		boolean b = super.performOk();
		ConAssist.clear();
		WsTags.clear();
		return b;
	}	
	
}
