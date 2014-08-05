package com.gratex.perconik.activity.uaca;

import javax.ws.rs.client.WebTarget;

import com.gratex.perconik.services.uaca.ide.IdeCheckinEventData;
import com.gratex.perconik.services.uaca.ide.IdeCodeElementEventData;
import com.gratex.perconik.services.uaca.ide.IdeCodeElementEventType;
import com.gratex.perconik.services.uaca.ide.IdeCodeEventData;
import com.gratex.perconik.services.uaca.ide.IdeCodeEventType;
import com.gratex.perconik.services.uaca.ide.IdeDocumentEventData;
import com.gratex.perconik.services.uaca.ide.IdeDocumentEventType;
import com.gratex.perconik.services.uaca.ide.IdeFindEventData;
import com.gratex.perconik.services.uaca.ide.IdeProjectEventData;
import com.gratex.perconik.services.uaca.ide.IdeProjectEventType;
import com.gratex.perconik.services.uaca.ide.IdeStateChangeEventData;
import com.gratex.perconik.uaca.SharedUacaProxy;

public final class IdeUacaProxy extends SharedUacaProxy {
  public IdeUacaProxy() {
  }

  @Override
  protected final WebTarget target() {
    return super.target().path("ide");
  }

  public final void sendCheckinEvent(final IdeCheckinEventData request) {
    this.postRequest("checkin", request);
  }

  public final void sendCodeElementEvent(final IdeCodeElementEventData request, final IdeCodeElementEventType type) {
    this.postRequest("codeelement/" + type.urlPath(), request);
  }

  public final void sendCodeEvent(final IdeCodeEventData request, final IdeCodeEventType type) {
    this.postRequest("code/" + type.urlPath(), request);
  }

  public final void sendDocumentEvent(final IdeDocumentEventData request, final IdeDocumentEventType type) {
    this.postRequest("document/" + type.urlPath(), request);
  }

  public final void sendFindEvent(final IdeFindEventData request) {
    this.postRequest("find", request);
  }

  public final void sendProjectEvent(final IdeProjectEventData request, final IdeProjectEventType type) {
    this.postRequest("project/" + type.urlPath(), request);
  }

  public final void sendStateChangeEvent(final IdeStateChangeEventData request) {
    this.postRequest("idestatechange", request);
  }
}
