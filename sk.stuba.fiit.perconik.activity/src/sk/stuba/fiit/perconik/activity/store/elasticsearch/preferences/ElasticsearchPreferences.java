package sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences;

import static sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys.clusterName;
import static sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys.indexName;
import static sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys.transportHost;
import static sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys.transportPort;
import static sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys.transportSniff;
import java.net.InetSocketAddress;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import sk.stuba.fiit.perconik.activity.plugin.Activator;

public final class ElasticsearchPreferences
{
	private static final ElasticsearchPreferences instance = new ElasticsearchPreferences();
	
	private final IPreferenceStore store;
	
	private ElasticsearchPreferences()
	{
		this.store = Activator.getDefault().getPreferenceStore();
	}
	
	final void initialize()
	{
		this.store.setDefault(clusterName, "PerConIK Cluster");
		this.store.setDefault(transportHost, "localhost");
		this.store.setDefault(transportPort, 9300);
		this.store.setDefault(transportSniff, true);
		this.store.setDefault(indexName, "events");
	}
	
	public static final ElasticsearchPreferences getInstance()
	{
		return instance;
	}
	
	public static final class Initializer extends AbstractPreferenceInitializer
	{
		public Initializer()
		{
		}

		@Override
		public final void initializeDefaultPreferences()
		{
			ElasticsearchPreferences.getInstance().initialize();
		}
	}
	
	public static final class Keys
	{
		static final String prefix = Activator.PLUGIN_ID + ".preferences.elasticsearch";

		public static final String clusterName = prefix + ".cluster.name";
		
		public static final String indexName = prefix + ".index.name";

		public static final String transportHost = prefix + ".transport.host";
		
		public static final String transportPort = prefix + ".transport.port";
		
		public static final String transportSniff = prefix + ".transport.sniff";
		
		private Keys()
		{
			throw new AssertionError();
		}
	}
	
	public final String getClusterName()
	{
		return getPreferenceStore().getString(clusterName);
	}

	public final String getIndexName()
	{
		return this.getPreferenceStore().getString(indexName);
	}

	public final IPreferenceStore getPreferenceStore()
	{
		return this.store;
	}

	public final InetSocketAddress getTransportAddress()
	{
		return new InetSocketAddress(this.getTransportHost(), this.getTransportPort());
	}

	public final String getTransportHost()
	{
		return this.getPreferenceStore().getString(transportHost);
	}

	public final int getTransportPort()
	{
		return this.getPreferenceStore().getInt(transportPort);
	}
	
	public final boolean getTransportSniff()
	{
		return this.getPreferenceStore().getBoolean(transportSniff);
	}
}
