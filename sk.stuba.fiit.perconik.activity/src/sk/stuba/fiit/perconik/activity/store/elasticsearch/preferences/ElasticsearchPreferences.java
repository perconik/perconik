package sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences;

import java.net.InetSocketAddress;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.settings.Settings;

import sk.stuba.fiit.perconik.activity.plugin.Activator;
import sk.stuba.fiit.perconik.activity.preferences.ActivityPreferences;

import static sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys.clusterName;
import static sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys.displayErrors;
import static sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys.indexName;
import static sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys.requestClusterState;
import static sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys.transportHost;
import static sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys.transportPort;
import static sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys.transportSniff;

public final class ElasticsearchPreferences extends ActivityPreferences
{
	private static final ElasticsearchPreferences instance = new ElasticsearchPreferences();
	
	private ElasticsearchPreferences()
	{
	}
	
	@Override
	protected final void initialize()
	{
		super.initialize();
		
		this.store.setDefault(displayErrors, true);

		this.store.setDefault(clusterName, "PerConIK-Cluster");
		
		this.store.setDefault(indexName, "events");

		this.store.setDefault(requestClusterState, true);
		
		this.store.setDefault(transportHost, "localhost");
		this.store.setDefault(transportPort, 9300);
		this.store.setDefault(transportSniff, true);
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
	
	public static final class Keys extends ActivityPreferences.Keys
	{
		static final String prefix = Activator.PLUGIN_ID + ".preferences.elasticsearch.";

		public static final String clusterName = prefix + "cluster.name";
		
		public static final String displayErrors = prefix + "displayErrors";

		public static final String indexName = prefix + "index.name";

		public static final String requestClusterState = prefix + "requestClusterState";

		public static final String transportHost = prefix + "transport.host";
		
		public static final String transportPort = prefix + "transport.port";
		
		public static final String transportSniff = prefix + "transport.sniff";
		
		private Keys()
		{
			throw new AssertionError();
		}
	}
	
	public final Settings toSettings()
	{
		Builder builder = ImmutableSettings.builder();
		
		builder.put("client.transport.host", this.getTransportHost());
		builder.put("client.transport.port", this.getTransportPort());
		builder.put("client.transport.sniff", this.getTransportSniff());
		builder.put("cluster.name", this.getClusterName());
		builder.put("index.name", this.getIndexName());
				
		return builder.build();
	}
	
	public final String getClusterName()
	{
		return getPreferenceStore().getString(clusterName);
	}

	public final String getIndexName()
	{
		return this.getPreferenceStore().getString(indexName);
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
