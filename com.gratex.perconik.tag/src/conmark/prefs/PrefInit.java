package conmark.prefs;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import conmark.Activator;

public class PrefInit extends AbstractPreferenceInitializer{

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault("conmark.prefs.profile", "Test1");
		store.setDefault("conmark.prefs.user", new com.sun.security.auth.module.NTSystem().getDomain()+"/"+System.getProperty("user.name"));
		store.setDefault("conmark.prefs.ws", "http://perconikapp1:9903/Adm/Wcf");
	//	store.setDefault(RepositoryLocation.repProfile, "");
	}


//	public static void main(String[] args) {
//		com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
//		  System.out.println(NTSystem.getName());
//		  System.out.println(NTSystem.getDomain());
//	}
}
