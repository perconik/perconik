package sk.stuba.fiit.perconik.activity.uaca;

import java.net.URI;
import java.nio.file.Path;

import sk.stuba.fiit.perconik.activity.data.AnyStructuredData;
import sk.stuba.fiit.perconik.activity.data.Content;

import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUri;

public class UacaData extends AnyStructuredData {
  public static final URI prefix = newUri("http://perconik.gratex.com/useractivity/event");

  protected URI uri;

  protected Content content;

  public UacaData() {}

  public static UacaData of(Path path, Content content) {
    return of(path.toString(), content);
  }

  public static UacaData of(String path, Content content) {
    UacaData data = new UacaData();

    data.uri = newUri(prefix + "/" + path);
    data.content = content;

    return data;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }

  public void setContent(Content content) {
    this.content = content;
  }

  public URI getUri() {
    return this.uri;
  }

  public Content getContent() {
    return this.content;
  }
}
