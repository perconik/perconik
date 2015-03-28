package sk.stuba.fiit.perconik.activity.listeners.ui.text;

abstract class AbstractTextEvent {
  final long time;

  AbstractTextEvent(final long time) {
    assert time >= 0L;

    this.time = time;
  }
}
