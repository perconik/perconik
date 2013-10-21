package conmark.prefs;

import java.net.URL;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import com.gratex.perconik.services.tag.ArrayOfTagProfileSearchResItemDto;
import com.gratex.perconik.services.tag.SearchTagProfileRequest;
import com.gratex.perconik.services.tag.SearchTagProfileResponse2;
import com.gratex.perconik.services.tag.TagProfileSearchResItemDto;
import com.gratex.perconik.services.tag.TagProfileWcfSvc;
import conmark.Activator;
import conmark.assistant.ConAssist;
import conmark.ws.WsTags;

public class PrefPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage{

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());		
	}

	StringFieldEditor f;
	StringFieldEditor t;
	
	@Override
	protected void createFieldEditors() {
		
		addField(f = new StringFieldEditor("conmark.prefs.profile", "Profile:", getFieldEditorParent()));
		addField(new StringFieldEditor("conmark.prefs.user", "User:", getFieldEditorParent()));
		addField(t = new StringFieldEditor("conmark.prefs.ws", "Url:", getFieldEditorParent()));
		
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
			
			SearchTagProfileResponse2 rs = s.getBasicHttpBindingITagProfileWcfSvc().searchTagProfile(r);
			ArrayOfTagProfileSearchResItemDto a = rs.getResultSet();
			for(TagProfileSearchResItemDto i : a.getTagProfileSearchResItem()){
				id = i.getId();
				break;
			}
			
			if(id == null || id.isEmpty()){				
				MessageBox dialog = new MessageBox(getShell(), SWT.ICON_ERROR | SWT.OK);
				dialog.setText("Error");
				dialog.setMessage("Unable find profile");
				dialog.open(); 				
				return false;
			}
		} catch (Exception e) {
			MessageBox dialog = new MessageBox(getShell(), SWT.ICON_ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage(e.getMessage());
			dialog.open();
			return false;
		}
		
		boolean b = super.performOk();
		ConAssist.clear();
		WsTags.clear();
		return b;
	}	
	
}
