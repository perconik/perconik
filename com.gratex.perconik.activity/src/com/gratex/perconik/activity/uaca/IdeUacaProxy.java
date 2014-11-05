package com.gratex.perconik.activity.uaca;

import javax.ws.rs.client.WebTarget;

import com.gratex.perconik.services.uaca.ide.IdeCheckinEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeCodeElementEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeCodeElementEventType;
import com.gratex.perconik.services.uaca.ide.IdeCodeEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeCodeEventType;
import com.gratex.perconik.services.uaca.ide.IdeDocumentEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeDocumentEventType;
import com.gratex.perconik.services.uaca.ide.IdeFindEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeProjectEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeProjectEventType;
import com.gratex.perconik.services.uaca.ide.IdeStateChangeEventRequest;
import com.gratex.perconik.uaca.SharedUacaProxy;

public final class IdeUacaProxy extends SharedUacaProxy {
  IdeUacaProxy() {}

  public static IdeUacaProxy open() {
    return new IdeUacaProxy();
  }

  @Override
  protected WebTarget createTarget() {
    return super.createTarget().path("ide");
  }

  public void sendCheckinEvent(final IdeCheckinEventRequest request) {
    this.send("checkin", request);
  }

  public void sendCodeElementEvent(final IdeCodeElementEventRequest request, final IdeCodeElementEventType type) {
    this.send("codeelement/" + type.urlPath(), request);
  }

  public void sendCodeEvent(final IdeCodeEventRequest request, final IdeCodeEventType type) {
    this.send("code/" + type.urlPath(), request);
  }

  public void sendDocumentEvent(final IdeDocumentEventRequest request, final IdeDocumentEventType type) {
    this.send("document/" + type.urlPath(), request);
  }

  public void sendFindEvent(final IdeFindEventRequest request) {
    this.send("find", request);
  }

  public void sendProjectEvent(final IdeProjectEventRequest request, final IdeProjectEventType type) {
    this.send("project/" + type.urlPath(), request);
  }

  public void sendStateChangeEvent(final IdeStateChangeEventRequest request) {
    this.send("idestatechange", request);
  }
}
