package sk.stuba.fiit.perconik.elasticsearch.ui.preferences;

import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.cluster.stats.ClusterStatsResponse;

import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithTextArea;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.WidgetListener;
import sk.stuba.fiit.perconik.elasticsearch.ElasticsearchProxy;
import sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions;
import sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchPreferences;
import sk.stuba.fiit.perconik.ui.Buttons;
import sk.stuba.fiit.perconik.ui.Groups;
import sk.stuba.fiit.perconik.ui.Labels;
import sk.stuba.fiit.perconik.utilities.configuration.MapOptions;
import sk.stuba.fiit.perconik.utilities.configuration.OptionParser;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Maps.newLinkedHashMap;

import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptionParsers.byteSizeParser;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptionParsers.timeParser;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.clientTransportAddresses;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.clientTransportIgnoreClusterName;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.clientTransportNodesSamplerInterval;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.clientTransportPingTimeout;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.clusterName;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.displayErrors;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.logErrors;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.logNotices;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.networkTcpKeepAlive;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.networkTcpNoDelay;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.networkTcpReceiveBufferSize;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.networkTcpReuseAddress;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.networkTcpSendBufferSize;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.nodeName;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.pathLogs;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.pathWork;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.transportTcpCompress;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.transportTcpConnectTimeout;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.pathParser;

public final class ElasticsearchPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
  private final Map<String, FieldEditor> editors;

  public ElasticsearchPreferencePage() {
    this.editors = newLinkedHashMap();
  }

  public void init(final IWorkbench workbench) {}

  @Override
  protected void createFieldEditors() {
    Composite parent = this.getFieldEditorParent();

    GridDataFactory factory = GridDataFactory.fillDefaults().grab(true, false).span(2, 1);

    Group nodeGroup = Groups.create(parent, "Node", factory.create());

    this.addField(new StringFieldEditor(nodeName.getKey(), "Name:", nodeGroup));

    Groups.updateMargins(nodeGroup);

    Group clusterGroup = Groups.create(parent, "Cluster", factory.create());

    this.addField(new StringFieldEditor(clusterName.getKey(), "Name:", nodeGroup));

    Groups.updateMargins(clusterGroup);

    Group clientGroup = Groups.create(parent, "Client", factory.create());

    this.addField(new StringFieldEditor(clientTransportAddresses.getKey(), "Addresses:", clientGroup));
    this.addField(new BooleanFieldEditor(clientTransportIgnoreClusterName.getKey(), "Ignore cluster name validation", clientGroup));
    this.addField(new StringOptionFieldEditor<>(timeParser(), clientTransportPingTimeout.getKey(), "Ping timeout", clientGroup));
    this.addField(new StringOptionFieldEditor<>(timeParser(), clientTransportNodesSamplerInterval.getKey(), "Nodes sampler interval", clientGroup));

    Groups.updateMargins(clientGroup);

    Group transportGroup = Groups.create(parent, "Transport", factory.create());

    this.addField(new StringOptionFieldEditor<>(timeParser(), transportTcpConnectTimeout.getKey(), "TCP connect timeout", transportGroup));
    this.addField(new BooleanFieldEditor(transportTcpCompress.getKey(), "TCP compress", transportGroup));

    Groups.updateMargins(transportGroup);

    Group networkGroup = Groups.create(parent, "Network", factory.create());

    this.addField(new BooleanFieldEditor(networkTcpNoDelay.getKey(), "TCP no delay", networkGroup));
    this.addField(new BooleanFieldEditor(networkTcpKeepAlive.getKey(), "TCP keep alive", networkGroup));
    this.addField(new BooleanFieldEditor(networkTcpReuseAddress.getKey(), "TCP reuse address", networkGroup));

    this.addField(new StringOptionFieldEditor<>(byteSizeParser(), networkTcpSendBufferSize.getKey(), "TCP send buffer size", networkGroup));
    this.addField(new StringOptionFieldEditor<>(byteSizeParser(), networkTcpReceiveBufferSize.getKey(), "TCP receive buffer size", networkGroup));

    Labels.createFieldSeparator(networkGroup);
    Labels.create(clientGroup, "Leave blank to use default buffer sizes");

    Groups.updateMargins(networkGroup);

    Group pathGroup = Groups.create(parent, "Path", factory.create());

    // TODO
    this.addField(new StringOptionFieldEditor<>(pathParser(), pathLogs.getKey(), "Logs path", pathGroup));
    this.addField(new DirectoryFieldEditor(pathWork.getKey(), "Work path", pathGroup));

    Groups.updateMargins(pathGroup);

    Group notificationGroup = Groups.create(parent, "Notification", factory.create());

    this.addField(new BooleanFieldEditor(displayErrors.getKey(), "Display warning on service failure", notificationGroup));

    Groups.updateMargins(notificationGroup);

    Group logGroup = Groups.create(parent, "Log", factory.create());

    this.addField(new BooleanFieldEditor(logNotices.getKey(), "Write notices to Workspace Log on proxy management", logGroup));
    this.addField(new BooleanFieldEditor(logErrors.getKey(), "Write errors to Error Log on service failure", logGroup));

    Groups.updateMargins(logGroup);
  }

  @Override
  protected void contributeButtons(final Composite parent) {
    Buttons.createCentering(parent, "Settings", GridData.HORIZONTAL_ALIGN_FILL, new WidgetListener() {
      public void handleEvent(final Event event) {
        ElasticsearchOptions options = getElasticsearchOptions();

        String title = "Elasticsearch Proxy Settings";
        String message = "Elasticsearch settings to create a proxy:";
        String text = ElasticsearchHandler.formatSettings(options.toSettings());

        MessageDialogWithTextArea.openInformation(getShell(), title, message, text);
      }
    });

    Buttons.createCentering(parent, "Status", GridData.HORIZONTAL_ALIGN_FILL, new WidgetListener() {
      public void handleEvent(final Event event) {
        requestClusterState(true);
      }
    });

    ((GridLayout) parent.getLayout()).numColumns += 2;
  }

  protected static class StringOptionFieldEditor<T> extends StringFieldEditor {
    protected OptionParser<? extends T> parser;

    public StringOptionFieldEditor(final OptionParser<? extends T> parser, final String name, final String label, final Composite parent) {
      super(name, label, parent);

      this.parser = checkNotNull(parser);
    }

    @Override
    protected boolean doCheckState() {
      return this.getOptionValue() != null;
    }

    public T getOptionValue() {
      try {
        return this.parser.parse(this.getStringValue());
      } catch (RuntimeException e) {
        return null;
      }
    }
  }

  @Override
  protected void addField(final FieldEditor editor) {
    this.editors.put(editor.getPreferenceName(), editor);

    super.addField(editor);
  }

  protected void addField(final StringFieldEditor editor) {
    editor.setEmptyStringAllowed(false);
    editor.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);

    this.addField((FieldEditor) editor);
  }

  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return ElasticsearchPreferences.getShared().getPreferenceStore();
  }

  boolean requestClusterState(final boolean display) {
    try {
      ElasticsearchOptions options = this.getElasticsearchOptions();
      ElasticsearchProxy proxy = ElasticsearchHandler.createProxy(options);

      ClusterStatsResponse response = ElasticsearchHandler.requestClusterStats(proxy);

      String desiredCluster = ((StringFieldEditor) this.editors.get(clusterName.getKey())).getStringValue();
      String receivedCluster = response.getClusterNameAsString();

      String message;

      if (!desiredCluster.equals(receivedCluster)) {
        message = "Connected to cluster " + desiredCluster;
        message += " instead of " + receivedCluster + ", ";
        message += "consider connecting to the cluster with correct name";

        throw new IllegalStateException(message);
      }

      if (display) {
        message = "Cluster " + receivedCluster + ", ";
        message += "status " + response.getStatus() + ".";

        MessageDialog.openInformation(this.getShell(), "Elasticsearch status", message.toString());
      }

      return true;
    } catch (Exception failure) {
      String title, message;

      if (failure instanceof ElasticsearchException) {
        ElasticsearchException exception = (ElasticsearchException) failure;

        title = "Elasticsearch Error";
        message = exception.getDetailedMessage() + ", status " + exception.status();
      } else {
        title = failure instanceof IllegalStateException ? "Elasticsearch Error" : "Unknown Error";
        message = failure.getMessage();
      }

      MessageDialog.openError(this.getShell(), title, message);

      return false;
    }
  }

  ElasticsearchOptions getElasticsearchOptions() {
    Map<String, Object> options = newLinkedHashMap();

    for (FieldEditor editor: this.editors.values()) {
      if (editor instanceof BooleanFieldEditor) {
        options.put(editor.getPreferenceName(), ((BooleanFieldEditor) editor).getBooleanValue());
      } else if (editor instanceof StringFieldEditor) {
        options.put(editor.getPreferenceName(), ((StringFieldEditor) editor).getStringValue());
      } else {
        throw new IllegalStateException();
      }
    }

    return ElasticsearchOptions.View.of(MapOptions.view(options));
  }
}
