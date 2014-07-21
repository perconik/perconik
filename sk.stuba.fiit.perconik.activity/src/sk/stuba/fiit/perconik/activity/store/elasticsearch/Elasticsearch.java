package sk.stuba.fiit.perconik.activity.store.elasticsearch;

import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.cluster.stats.ClusterStatsResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import sk.stuba.fiit.perconik.activity.store.Store;

public final class Elasticsearch implements Store
{
	private final Settings settings;
	
	private final Client client;
	
	private final Admin admin;
	
	public Elasticsearch(final Settings settings)
	{
		this.settings = ImmutableSettings.builder().put(settings).build();

		String host = this.settings.get("client.transport.host", Defaults.transportHost);
		int    port = this.settings.getAsInt("client.transport.port", Defaults.transportPort);
		
		InetSocketTransportAddress address = new InetSocketTransportAddress(host, port);
		
		Client client = new TransportClient(settings).addTransportAddress(address);

		this.client = client;
		this.admin = new Admin(client.admin());
	}
	
	static final class Defaults
	{
		static final String transportHost = "localhost";
		
		static final int transportPort = 9300;
	}
	
	static final class Admin
	{
		final ClusterAdminClient cluster;
		
		final IndicesAdminClient indices;
		
		Admin(final AdminClient admin)
		{
			this.cluster = admin.cluster();
			this.indices = admin.indices();
		}
	}

	public final void close()
	{
		this.client.close();
	}

	public final ClusterStateResponse state()
	{
		return this.admin.cluster.prepareState().execute().actionGet();
	}
	
	public final ClusterStatsResponse stats()
	{
		return this.admin.cluster.prepareClusterStats().execute().actionGet();
	}
}
