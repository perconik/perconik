package com.gratex.perconik.tag.prefs;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import com.gratex.perconik.tag.plugin.Activator;

public class PrefInit extends AbstractPreferenceInitializer{

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PrefKeys.profile, "Test");
		store.setDefault(PrefKeys.user, new com.sun.security.auth.module.NTSystem().getDomain()+"/"+System.getProperty("user.name"));
		store.setDefault(PrefKeys.url, "http://perconik.fiit.stuba.sk/tagAdm/Wcf");
	//	store.setDefault(RepositoryLocation.repProfile, "");
	}


//	public static void main(String[] args) {
//		com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
//		  System.out.println(NTSystem.getName());
//		  System.out.println(NTSystem.getDomain());
//	}
}
