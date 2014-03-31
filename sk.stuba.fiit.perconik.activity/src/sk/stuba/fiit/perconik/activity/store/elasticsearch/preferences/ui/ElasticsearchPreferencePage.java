package sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ui;

import static org.eclipse.jface.preference.BooleanFieldEditor.SEPARATE_LABEL;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.cluster.stats.ClusterStatsResponse;
import org.elasticsearch.common.settings.Settings;
import sk.stuba.fiit.perconik.activity.store.elasticsearch.Elasticsearch;
import sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences;
import sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys;
import sk.stuba.fiit.perconik.activity.ui.ActivityPreferencePage;
import sk.stuba.fiit.perconik.ui.utilities.Widgets;

public final class ElasticsearchPreferencePage extends ActivityPreferencePage
{
	private StringFieldEditor clusterName;
	
	private StringFieldEditor indexName;
	
	private StringFieldEditor transportHost;
	
	private IntegerFieldEditor transportPort;
	
	private BooleanFieldEditor transportSniff;
	
	public ElasticsearchPreferencePage()
	{
	}
	
	@Override
	protected final void createFieldEditors()
	{
		this.transportHost  = new StringFieldEditor(Keys.transportHost, "Transport host:", this.getFieldEditorParent());
		this.transportPort  = new IntegerFieldEditor(Keys.transportPort, "Transport port:", this.getFieldEditorParent(), 5);
		this.transportSniff = new BooleanFieldEditor(Keys.transportSniff, "Transport sniff:", SEPARATE_LABEL, this.getFieldEditorParent());

		this.transportPort.setValidRange(0, 65535);
		
		Widgets.createFieldSeparator(this.getFieldEditorParent());
		
		this.clusterName = new StringFieldEditor(Keys.clusterName, "Cluster name:", this.getFieldEditorParent());
		this.indexName   = new StringFieldEditor(Keys.indexName, "Index name:", this.getFieldEditorParent());
		
		Widgets.createFieldSeparator(this.getFieldEditorParent());
		
		this.logErrors = new BooleanFieldEditor(Keys.logErrors, "Log errors:", SEPARATE_LABEL, this.getFieldEditorParent());
		this.logEvents = new BooleanFieldEditor(Keys.logEvents, "Log events:", SEPARATE_LABEL, this.getFieldEditorParent());

		this.addField(prepare(this.transportHost));
		this.addField(prepare(this.transportPort));
		this.addField(this.transportSniff);
		
		this.addField(prepare(this.clusterName));
		this.addField(prepare(this.indexName));
		
		this.addField(this.logErrors);
		this.addField(this.logEvents);
	}

	@Override
	protected final void performApply()
	{
		this.performOk(true);
	}

	@Override
	public final boolean performOk()
	{
		return this.performOk(false);
	}
	
	private final boolean performOk(final boolean displayStatus)
	{
		if (super.performOk())
		{
			try
			{
				Settings settings = ElasticsearchPreferences.getInstance().toSettings();
				
				Elasticsearch store = new Elasticsearch(settings);
				
				ClusterStatsResponse stats = store.stats();
				
				if (displayStatus)
				{
					StringBuilder message = new StringBuilder();
					
					message.append("Cluster ").append(stats.getClusterNameAsString());
					message.append(", status ").append(stats.getStatus()).append(".");
					
					MessageDialog.openInformation(this.getShell(), "Elasticsearch status", message.toString());
				}

				return true;
			}
			catch (ElasticsearchException failure)
			{
				StringBuilder message = new StringBuilder();
				
				message.append(failure.getDetailedMessage());
				message.append(", status ").append(failure.status());
				
				MessageDialog.openError(this.getShell(), "Elasticsearch error", message.toString());
			}
			catch (Exception failure)
			{
				MessageDialog.openError(this.getShell(), "Unknown error", failure.getMessage());
			}
		}
		
		return false;
	}
}
