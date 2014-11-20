package sk.stuba.fiit.perconik.activity.serializers.git;

import java.util.List;
import java.util.Set;

import org.eclipse.jgit.revwalk.FooterLine;
import org.eclipse.jgit.revwalk.RevCommit;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static com.google.common.collect.Lists.newArrayListWithExpectedSize;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.newStructuredContent;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class CommitSerializer extends AbstractConfigurableSerializer<RevCommit> {
  public CommitSerializer(final Option ... options) {
    super(options);
  }

  public CommitSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putCommit(final StructuredContent content, final RevCommit commit, final Set<Option> options) {
    content.put(key("timestamp"), commit.getCommitTime());
    content.put(key("name"), commit.getName());

    IdentitySerializer serializer = new IdentitySerializer(options);

    content.put(key("author"), serializer.serialize(commit.getAuthorIdent()));
    content.put(key("committer"), serializer.serialize(commit.getCommitterIdent()));

    content.put(key("message", "short"), commit.getShortMessage());
    content.put(key("message", "full"), commit.getFullMessage());

    content.put(key("encoding"), commit.getEncoding().toString());

    List<Content> lines = newArrayListWithExpectedSize(8);

    for (FooterLine line: commit.getFooterLines()) {
      StructuredContent lineContent = newStructuredContent();

      lineContent.put(key("key"), line.getKey());
      lineContent.put(key("value"), line.getValue());
      lineContent.put(key("email"), line.getEmailAddress());

      lines.add(lineContent);
    }

    content.put(key("footer"), lines);
  }

  @Override
  protected void put(final StructuredContent content, final RevCommit commit) {
    putCommit(content, commit, this.options);
  }
}
