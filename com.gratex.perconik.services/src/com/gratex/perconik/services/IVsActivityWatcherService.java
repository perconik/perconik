
package com.gratex.perconik.services;

import java.util.concurrent.Future;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.Response;
import javax.xml.ws.ResponseWrapper;
import com.gratex.perconik.services.uaca.vs.IdeCheckinDto;
import com.gratex.perconik.services.uaca.vs.IdeCodeElementEventDto;
import com.gratex.perconik.services.uaca.vs.IdeCodeOperationDto;
import com.gratex.perconik.services.uaca.vs.IdeDocumentOperationDto;
import com.gratex.perconik.services.uaca.vs.IdeFindOperationDto;
import com.gratex.perconik.services.uaca.vs.IdeProjectOperationDto;
import com.gratex.perconik.services.uaca.vs.IdeStateChangeDto;
import com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCheckinResponse;
import com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeElementEventResponse;
import com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeOperationResponse;
import com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeDocumentOperationResponse;
import com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeFindOperationResponse;
import com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeProjectOperationResponse;
import com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeStateChangeResponse;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IVsActivityWatcherService", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    com.gratex.perconik.services.uaca.vs.ObjectFactory.class,
    com.gratex.perconik.services.uaca.vs.notifications.ObjectFactory.class
})
public interface IVsActivityWatcherService {


    /**
     * 
     * @param eventDto
     * @return
     *     returns javax.xml.ws.Response<com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeStateChangeResponse>
     */
    @WebMethod(operationName = "NotifyIdeStateChange", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeStateChange")
    @RequestWrapper(localName = "NotifyIdeStateChange", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeStateChange")
    @ResponseWrapper(localName = "NotifyIdeStateChangeResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeStateChangeResponse")
    public Response<NotifyIdeStateChangeResponse> notifyIdeStateChangeAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeStateChangeDto eventDto);

    /**
     * 
     * @param asyncHandler
     * @param eventDto
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "NotifyIdeStateChange", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeStateChange")
    @RequestWrapper(localName = "NotifyIdeStateChange", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeStateChange")
    @ResponseWrapper(localName = "NotifyIdeStateChangeResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeStateChangeResponse")
    public Future<?> notifyIdeStateChangeAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeStateChangeDto eventDto,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<NotifyIdeStateChangeResponse> asyncHandler);

    /**
     * 
     * @param eventDto
     */
    @WebMethod(operationName = "NotifyIdeStateChange", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeStateChange")
    @RequestWrapper(localName = "NotifyIdeStateChange", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeStateChange")
    @ResponseWrapper(localName = "NotifyIdeStateChangeResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeStateChangeResponse")
    public void notifyIdeStateChange(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeStateChangeDto eventDto);

    /**
     * 
     * @param eventDto
     * @return
     *     returns javax.xml.ws.Response<com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeDocumentOperationResponse>
     */
    @WebMethod(operationName = "NotifyIdeDocumentOperation", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeDocumentOperation")
    @RequestWrapper(localName = "NotifyIdeDocumentOperation", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeDocumentOperation")
    @ResponseWrapper(localName = "NotifyIdeDocumentOperationResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeDocumentOperationResponse")
    public Response<NotifyIdeDocumentOperationResponse> notifyIdeDocumentOperationAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeDocumentOperationDto eventDto);

    /**
     * 
     * @param asyncHandler
     * @param eventDto
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "NotifyIdeDocumentOperation", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeDocumentOperation")
    @RequestWrapper(localName = "NotifyIdeDocumentOperation", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeDocumentOperation")
    @ResponseWrapper(localName = "NotifyIdeDocumentOperationResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeDocumentOperationResponse")
    public Future<?> notifyIdeDocumentOperationAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeDocumentOperationDto eventDto,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<NotifyIdeDocumentOperationResponse> asyncHandler);

    /**
     * 
     * @param eventDto
     */
    @WebMethod(operationName = "NotifyIdeDocumentOperation", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeDocumentOperation")
    @RequestWrapper(localName = "NotifyIdeDocumentOperation", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeDocumentOperation")
    @ResponseWrapper(localName = "NotifyIdeDocumentOperationResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeDocumentOperationResponse")
    public void notifyIdeDocumentOperation(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeDocumentOperationDto eventDto);

    /**
     * 
     * @param eventDto
     * @return
     *     returns javax.xml.ws.Response<com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeProjectOperationResponse>
     */
    @WebMethod(operationName = "NotifyIdeProjectOperation", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeProjectOperation")
    @RequestWrapper(localName = "NotifyIdeProjectOperation", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeProjectOperation")
    @ResponseWrapper(localName = "NotifyIdeProjectOperationResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeProjectOperationResponse")
    public Response<NotifyIdeProjectOperationResponse> notifyIdeProjectOperationAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeProjectOperationDto eventDto);

    /**
     * 
     * @param asyncHandler
     * @param eventDto
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "NotifyIdeProjectOperation", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeProjectOperation")
    @RequestWrapper(localName = "NotifyIdeProjectOperation", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeProjectOperation")
    @ResponseWrapper(localName = "NotifyIdeProjectOperationResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeProjectOperationResponse")
    public Future<?> notifyIdeProjectOperationAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeProjectOperationDto eventDto,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<NotifyIdeProjectOperationResponse> asyncHandler);

    /**
     * 
     * @param eventDto
     */
    @WebMethod(operationName = "NotifyIdeProjectOperation", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeProjectOperation")
    @RequestWrapper(localName = "NotifyIdeProjectOperation", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeProjectOperation")
    @ResponseWrapper(localName = "NotifyIdeProjectOperationResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeProjectOperationResponse")
    public void notifyIdeProjectOperation(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeProjectOperationDto eventDto);

    /**
     * 
     * @param eventDto
     * @return
     *     returns javax.xml.ws.Response<com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCheckinResponse>
     */
    @WebMethod(operationName = "NotifyIdeCheckin", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeCheckin")
    @RequestWrapper(localName = "NotifyIdeCheckin", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCheckin")
    @ResponseWrapper(localName = "NotifyIdeCheckinResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCheckinResponse")
    public Response<NotifyIdeCheckinResponse> notifyIdeCheckinAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeCheckinDto eventDto);

    /**
     * 
     * @param asyncHandler
     * @param eventDto
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "NotifyIdeCheckin", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeCheckin")
    @RequestWrapper(localName = "NotifyIdeCheckin", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCheckin")
    @ResponseWrapper(localName = "NotifyIdeCheckinResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCheckinResponse")
    public Future<?> notifyIdeCheckinAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeCheckinDto eventDto,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<NotifyIdeCheckinResponse> asyncHandler);

    /**
     * 
     * @param eventDto
     */
    @WebMethod(operationName = "NotifyIdeCheckin", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeCheckin")
    @RequestWrapper(localName = "NotifyIdeCheckin", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCheckin")
    @ResponseWrapper(localName = "NotifyIdeCheckinResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCheckinResponse")
    public void notifyIdeCheckin(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeCheckinDto eventDto);

    /**
     * 
     * @param eventDto
     * @return
     *     returns javax.xml.ws.Response<com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeOperationResponse>
     */
    @WebMethod(operationName = "NotifyIdeCodeOperation", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeCodeOperation")
    @RequestWrapper(localName = "NotifyIdeCodeOperation", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeOperation")
    @ResponseWrapper(localName = "NotifyIdeCodeOperationResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeOperationResponse")
    public Response<NotifyIdeCodeOperationResponse> notifyIdeCodeOperationAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeCodeOperationDto eventDto);

    /**
     * 
     * @param asyncHandler
     * @param eventDto
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "NotifyIdeCodeOperation", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeCodeOperation")
    @RequestWrapper(localName = "NotifyIdeCodeOperation", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeOperation")
    @ResponseWrapper(localName = "NotifyIdeCodeOperationResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeOperationResponse")
    public Future<?> notifyIdeCodeOperationAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeCodeOperationDto eventDto,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<NotifyIdeCodeOperationResponse> asyncHandler);

    /**
     * 
     * @param eventDto
     */
    @WebMethod(operationName = "NotifyIdeCodeOperation", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeCodeOperation")
    @RequestWrapper(localName = "NotifyIdeCodeOperation", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeOperation")
    @ResponseWrapper(localName = "NotifyIdeCodeOperationResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeOperationResponse")
    public void notifyIdeCodeOperation(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeCodeOperationDto eventDto);

    /**
     * 
     * @param eventDto
     * @return
     *     returns javax.xml.ws.Response<com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeFindOperationResponse>
     */
    @WebMethod(operationName = "NotifyIdeFindOperation", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeFindOperation")
    @RequestWrapper(localName = "NotifyIdeFindOperation", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeFindOperation")
    @ResponseWrapper(localName = "NotifyIdeFindOperationResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeFindOperationResponse")
    public Response<NotifyIdeFindOperationResponse> notifyIdeFindOperationAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeFindOperationDto eventDto);

    /**
     * 
     * @param asyncHandler
     * @param eventDto
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "NotifyIdeFindOperation", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeFindOperation")
    @RequestWrapper(localName = "NotifyIdeFindOperation", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeFindOperation")
    @ResponseWrapper(localName = "NotifyIdeFindOperationResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeFindOperationResponse")
    public Future<?> notifyIdeFindOperationAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeFindOperationDto eventDto,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<NotifyIdeFindOperationResponse> asyncHandler);

    /**
     * 
     * @param eventDto
     */
    @WebMethod(operationName = "NotifyIdeFindOperation", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeFindOperation")
    @RequestWrapper(localName = "NotifyIdeFindOperation", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeFindOperation")
    @ResponseWrapper(localName = "NotifyIdeFindOperationResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeFindOperationResponse")
    public void notifyIdeFindOperation(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeFindOperationDto eventDto);

    /**
     * 
     * @param eventDto
     * @return
     *     returns javax.xml.ws.Response<com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeElementEventResponse>
     */
    @WebMethod(operationName = "NotifyIdeCodeElementEvent", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeCodeElementEvent")
    @RequestWrapper(localName = "NotifyIdeCodeElementEvent", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeElementEvent")
    @ResponseWrapper(localName = "NotifyIdeCodeElementEventResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeElementEventResponse")
    public Response<NotifyIdeCodeElementEventResponse> notifyIdeCodeElementEventAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeCodeElementEventDto eventDto);

    /**
     * 
     * @param asyncHandler
     * @param eventDto
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "NotifyIdeCodeElementEvent", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeCodeElementEvent")
    @RequestWrapper(localName = "NotifyIdeCodeElementEvent", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeElementEvent")
    @ResponseWrapper(localName = "NotifyIdeCodeElementEventResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeElementEventResponse")
    public Future<?> notifyIdeCodeElementEventAsync(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeCodeElementEventDto eventDto,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<NotifyIdeCodeElementEventResponse> asyncHandler);

    /**
     * 
     * @param eventDto
     */
    @WebMethod(operationName = "NotifyIdeCodeElementEvent", action = "http://tempuri.org/IVsActivityWatcherService/NotifyIdeCodeElementEvent")
    @RequestWrapper(localName = "NotifyIdeCodeElementEvent", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeElementEvent")
    @ResponseWrapper(localName = "NotifyIdeCodeElementEventResponse", targetNamespace = "http://tempuri.org/", className = "com.gratex.perconik.services.uaca.vs.notifications.NotifyIdeCodeElementEventResponse")
    public void notifyIdeCodeElementEvent(
        @WebParam(name = "eventDto", targetNamespace = "http://tempuri.org/")
        IdeCodeElementEventDto eventDto);

}