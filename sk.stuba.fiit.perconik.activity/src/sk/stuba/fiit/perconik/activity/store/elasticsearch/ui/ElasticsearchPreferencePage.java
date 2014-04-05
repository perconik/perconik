package sk.stuba.fiit.perconik.activity.store.elasticsearch.ui;

import static org.eclipse.jface.preference.BooleanFieldEditor.SEPARATE_LABEL;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.cluster.stats.ClusterStatsResponse;
import org.elasticsearch.common.settings.Settings;
import sk.stuba.fiit.perconik.activity.store.elasticsearch.Elasticsearch;
import sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences;
import sk.stuba.fiit.perconik.activity.store.elasticsearch.preferences.ElasticsearchPreferences.Keys;
import sk.stuba.fiit.perconik.activity.ui.ActivityPreferencePage;
import sk.stuba.fiit.perconik.eclipse.jface.preference.ExtendedBooleanFieldEditor;
import sk.stuba.fiit.perconik.ui.utilities.Widgets;

public final class ElasticsearchPreferencePage extends ActivityPreferencePage
{
	private StringFieldEditor clusterName;

	private StringFieldEditor indexName;
	
	private StringFieldEditor transportHost;
	
	private IntegerFieldEditor transportPort;
	
	private BooleanFieldEditor transportSniff;
	
	private ExtendedBooleanFieldEditor validate;

	private Button statusButton;
	
	public ElasticsearchPreferencePage()
	{
	}
	
	@Override
	protected final void contributeButtons(final Composite parent)
	{
		this.statusButton = new Button(parent, SWT.PUSH);
		
		this.statusButton.setText("Status");
		
		Dialog.applyDialogFont(this.statusButton);
		
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		
		int hint = this.convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
		int size = this.statusButton.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x;
		
		data.widthHint = Math.max(hint, size);

		this.statusButton.setLayoutData(data);
		
		this.statusButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			@SuppressWarnings("synthetic-access")
			public final void widgetSelected(final SelectionEvent e)
			{
				ElasticsearchPreferencePage.super.performOk();
				ElasticsearchPreferencePage.this.validate(true);
			}
		});
		
		((GridLayout) parent.getLayout()).numColumns ++;
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
		
		this.logErrors = new BooleanFieldEditor(Keys.logErrors, "Write errors to Error Log", this.getFieldEditorParent());
		this.logEvents = new BooleanFieldEditor(Keys.logEvents, "Log processed events (for debug only)", this.getFieldEditorParent());	
		
		this.validate = new ExtendedBooleanFieldEditor(Keys.validate, "Request cluster status on confirmation", this.getFieldEditorParent());
		
		this.addField(prepare(this.transportHost));
		this.addField(prepare(this.transportPort));
		this.addField(this.transportSniff);
		
		this.addField(prepare(this.clusterName));
		this.addField(prepare(this.indexName));
		
		this.addField(this.logErrors);
		this.addField(this.logEvents);
		
		this.addField(this.validate);
	}

	@Override
	public final boolean performOk()
	{
		return super.performOk() && (this.validate.getBooleanValue() ? this.validate(false) : true);
	}
	
	final boolean validate(final boolean display)
	{
		try
		{
			Settings settings = ElasticsearchPreferences.getInstance().toSettings();
			
			ClusterStatsResponse stats = new Elasticsearch(settings).stats();
			
			String desiredCluster  = this.clusterName.getStringValue();
			String receivedCluster = stats.getClusterNameAsString();

			String message;
			
			if (!desiredCluster.equals(receivedCluster))
			{
				message  = "Connected to cluster " + desiredCluster;
				message += " instead of " + receivedCluster + ". ";
				message += "Please correct the Cluster name setting.";
				
				throw new IllegalStateException(message);
			}
			
			if (display)
			{
				message  = "Cluster " + receivedCluster + ", ";
				message += "status " + stats.getStatus() + ".";
				
				MessageDialog.openInformation(this.getShell(), "Elasticsearch status", message.toString());
			}
			
			return true;
		}
		catch (Exception failure)
		{
			String title, message;
			
			if (failure instanceof ElasticsearchException)
			{
				ElasticsearchException exception = (ElasticsearchException) failure;
				
				title   = "Elasticsearch error";
				message = exception.getDetailedMessage() + ", status " + exception.status();
			}
			else
			{
				title   = failure instanceof IllegalStateException ? "Elasticsearch error" : "Unknown error";
				message = failure.getMessage();
			}
			
			boolean validate = MessageDialogWithToggle.openError(this.getShell(), title, message, "Always validate on confirmation", this.validate.getBooleanValue(), this.getPreferenceStore(), this.validate.getPreferenceName()).getToggleState();

			this.validate.getChangeControl().setSelection(validate);
			
			return false;
		}
	}
}
