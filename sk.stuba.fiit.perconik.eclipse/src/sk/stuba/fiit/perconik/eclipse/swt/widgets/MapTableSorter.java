package sk.stuba.fiit.perconik.eclipse.swt.widgets;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;

import org.eclipse.swt.widgets.Table;

import static com.google.common.collect.Maps.newLinkedHashMap;

import static sk.stuba.fiit.perconik.utilities.MoreMaps.putAll;

public abstract class MapTableSorter<K, V> extends TableSorter<Entry<K, V>> {
  protected MapTableSorter(final Table table) {
    super(table);
  }

  protected MapTableSorter(final Table table, @Nullable final Comparator<? super Entry<K, V>> comparator) {
    super(table, comparator);
  }

  @Override
  public final Set<Entry<K, V>> loadData() {
    return this.loadMap().entrySet();
  }

  @Override
  public final void updateData(final Iterable<? extends Entry<K, V>> data) {
    Map<K, V> update = newLinkedHashMap();

    putAll(update, data);

    this.updateMap(update);
  }

  protected abstract Map<K, V> loadMap();

  protected abstract void updateMap(Map<K, V> map);
}
