
package com.gratex.perconik.services.activity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gratex.perconik.services.activity package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ApplicationFocusLostDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "ApplicationFocusLostDto");
    private final static QName _KeyboardStateDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "KeyboardStateDto");
    private final static QName _OneNoteViewChangeDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "OneNoteViewChangeDto");
    private final static QName _IdeFindOperationDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "IdeFindOperationDto");
    private final static QName _LyncStatusChangeDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "LyncStatusChangeDto");
    private final static QName _RunningApplicationsListDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "RunningApplicationsListDto");
    private final static QName _IdeCodeOperationDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "IdeCodeOperationDto");
    private final static QName _HwUsageDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "HwUsageDto");
    private final static QName _IdeCheckinDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "IdeCheckinDto");
    private final static QName _IdeDocumentOperationDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "IdeDocumentOperationDto");
    private final static QName _IdeStateChangeDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "IdeStateChangeDto");
    private final static QName _IdeCodeElementEventDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "IdeCodeElementEventDto");
    private final static QName _IdeProjectOperationDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "IdeProjectOperationDto");
    private final static QName _WebBookmarkDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "WebBookmarkDto");
    private final static QName _WebSaveDocumentDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "WebSaveDocumentDto");
    private final static QName _MouseStateDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "MouseStateDto");
    private final static QName _OneNoteNavigateDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "OneNoteNavigateDto");
    private final static QName _WebNavigateDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "WebNavigateDto");
    private final static QName _WebTabOperationDto_QNAME = new QName("http://www.gratex.com/PerConIk/IActivitySvc", "WebTabOperationDto");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gratex.perconik.services.activity
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IdeStateChangeDto }
     * 
     */
    public IdeStateChangeDto createIdeStateChangeDto() {
        return new IdeStateChangeDto();
    }

    /**
     * Create an instance of {@link WebSaveDocumentDto }
     * 
     */
    public WebSaveDocumentDto createWebSaveDocumentDto() {
        return new WebSaveDocumentDto();
    }

    /**
     * Create an instance of {@link IdeProjectOperationDto }
     * 
     */
    public IdeProjectOperationDto createIdeProjectOperationDto() {
        return new IdeProjectOperationDto();
    }

    /**
     * Create an instance of {@link WebNavigateDto }
     * 
     */
    public WebNavigateDto createWebNavigateDto() {
        return new WebNavigateDto();
    }

    /**
     * Create an instance of {@link OneNoteNavigateDto }
     * 
     */
    public OneNoteNavigateDto createOneNoteNavigateDto() {
        return new OneNoteNavigateDto();
    }

    /**
     * Create an instance of {@link GetActivity }
     * 
     */
    public GetActivity createGetActivity() {
        return new GetActivity();
    }

    /**
     * Create an instance of {@link GetActivityResponse }
     * 
     */
    public GetActivityResponse createGetActivityResponse() {
        return new GetActivityResponse();
    }

    /**
     * Create an instance of {@link ActivityDto }
     * 
     */
    public ActivityDto createActivityDto() {
        return new ActivityDto();
    }

    /**
     * Create an instance of {@link RunningApplicationsListDto }
     * 
     */
    public RunningApplicationsListDto createRunningApplicationsListDto() {
        return new RunningApplicationsListDto();
    }

    /**
     * Create an instance of {@link LyncStatusChangeDto }
     * 
     */
    public LyncStatusChangeDto createLyncStatusChangeDto() {
        return new LyncStatusChangeDto();
    }

    /**
     * Create an instance of {@link CommitActivity }
     * 
     */
    public CommitActivity createCommitActivity() {
        return new CommitActivity();
    }

    /**
     * Create an instance of {@link OneNoteViewChangeDto }
     * 
     */
    public OneNoteViewChangeDto createOneNoteViewChangeDto() {
        return new OneNoteViewChangeDto();
    }

    /**
     * Create an instance of {@link IdeCodeOperationDto }
     * 
     */
    public IdeCodeOperationDto createIdeCodeOperationDto() {
        return new IdeCodeOperationDto();
    }

    /**
     * Create an instance of {@link CommitActivityResponse }
     * 
     */
    public CommitActivityResponse createCommitActivityResponse() {
        return new CommitActivityResponse();
    }

    /**
     * Create an instance of {@link HwUsageDto }
     * 
     */
    public HwUsageDto createHwUsageDto() {
        return new HwUsageDto();
    }

    /**
     * Create an instance of {@link IdeCheckinDto }
     * 
     */
    public IdeCheckinDto createIdeCheckinDto() {
        return new IdeCheckinDto();
    }

    /**
     * Create an instance of {@link IdeCodeElementEventDto }
     * 
     */
    public IdeCodeElementEventDto createIdeCodeElementEventDto() {
        return new IdeCodeElementEventDto();
    }

    /**
     * Create an instance of {@link MouseStateDto }
     * 
     */
    public MouseStateDto createMouseStateDto() {
        return new MouseStateDto();
    }

    /**
     * Create an instance of {@link WebBookmarkDto }
     * 
     */
    public WebBookmarkDto createWebBookmarkDto() {
        return new WebBookmarkDto();
    }

    /**
     * Create an instance of {@link GetActivitiesResponse }
     * 
     */
    public GetActivitiesResponse createGetActivitiesResponse() {
        return new GetActivitiesResponse();
    }

    /**
     * Create an instance of {@link ArrayOfActivityDto }
     * 
     */
    public ArrayOfActivityDto createArrayOfActivityDto() {
        return new ArrayOfActivityDto();
    }

    /**
     * Create an instance of {@link WebTabOperationDto }
     * 
     */
    public WebTabOperationDto createWebTabOperationDto() {
        return new WebTabOperationDto();
    }

    /**
     * Create an instance of {@link KeyboardStateDto }
     * 
     */
    public KeyboardStateDto createKeyboardStateDto() {
        return new KeyboardStateDto();
    }

    /**
     * Create an instance of {@link ApplicationFocusLostDto }
     * 
     */
    public ApplicationFocusLostDto createApplicationFocusLostDto() {
        return new ApplicationFocusLostDto();
    }

    /**
     * Create an instance of {@link GetActivities }
     * 
     */
    public GetActivities createGetActivities() {
        return new GetActivities();
    }

    /**
     * Create an instance of {@link ActivityFilter }
     * 
     */
    public ActivityFilter createActivityFilter() {
        return new ActivityFilter();
    }

    /**
     * Create an instance of {@link IdeFindOperationDto }
     * 
     */
    public IdeFindOperationDto createIdeFindOperationDto() {
        return new IdeFindOperationDto();
    }

    /**
     * Create an instance of {@link IdeDocumentOperationDto }
     * 
     */
    public IdeDocumentOperationDto createIdeDocumentOperationDto() {
        return new IdeDocumentOperationDto();
    }

    /**
     * Create an instance of {@link ArrayOfMouseMoveDto }
     * 
     */
    public ArrayOfMouseMoveDto createArrayOfMouseMoveDto() {
        return new ArrayOfMouseMoveDto();
    }

    /**
     * Create an instance of {@link ArrayOfIdeFindFileResultDto }
     * 
     */
    public ArrayOfIdeFindFileResultDto createArrayOfIdeFindFileResultDto() {
        return new ArrayOfIdeFindFileResultDto();
    }

    /**
     * Create an instance of {@link RcsServerDto }
     * 
     */
    public RcsServerDto createRcsServerDto() {
        return new RcsServerDto();
    }

    /**
     * Create an instance of {@link XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesEventDto }
     * 
     */
    public XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesEventDto createXmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesEventDto() {
        return new XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesEventDto();
    }

    /**
     * Create an instance of {@link ArrayOfIdeFindResultRowDto }
     * 
     */
    public ArrayOfIdeFindResultRowDto createArrayOfIdeFindResultRowDto() {
        return new ArrayOfIdeFindResultRowDto();
    }

    /**
     * Create an instance of {@link MouseMoveDto }
     * 
     */
    public MouseMoveDto createMouseMoveDto() {
        return new MouseMoveDto();
    }

    /**
     * Create an instance of {@link XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesStateDto }
     * 
     */
    public XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesStateDto createXmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesStateDto() {
        return new XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesStateDto();
    }

    /**
     * Create an instance of {@link OneNoteSectionGroupDto }
     * 
     */
    public OneNoteSectionGroupDto createOneNoteSectionGroupDto() {
        return new OneNoteSectionGroupDto();
    }

    /**
     * Create an instance of {@link ApplicationStateDto }
     * 
     */
    public ApplicationStateDto createApplicationStateDto() {
        return new ApplicationStateDto();
    }

    /**
     * Create an instance of {@link KeyboardGraphDto }
     * 
     */
    public KeyboardGraphDto createKeyboardGraphDto() {
        return new KeyboardGraphDto();
    }

    /**
     * Create an instance of {@link IdeFindFileResultDto }
     * 
     */
    public IdeFindFileResultDto createIdeFindFileResultDto() {
        return new IdeFindFileResultDto();
    }

    /**
     * Create an instance of {@link ArrayOfKeyboardGraphDto }
     * 
     */
    public ArrayOfKeyboardGraphDto createArrayOfKeyboardGraphDto() {
        return new ArrayOfKeyboardGraphDto();
    }

    /**
     * Create an instance of {@link MouseDragDropDto }
     * 
     */
    public MouseDragDropDto createMouseDragDropDto() {
        return new MouseDragDropDto();
    }

    /**
     * Create an instance of {@link ArrayOfMouseClickDto }
     * 
     */
    public ArrayOfMouseClickDto createArrayOfMouseClickDto() {
        return new ArrayOfMouseClickDto();
    }

    /**
     * Create an instance of {@link ArrayOfMouseDragDropDto }
     * 
     */
    public ArrayOfMouseDragDropDto createArrayOfMouseDragDropDto() {
        return new ArrayOfMouseDragDropDto();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link IdeFindResultRowDto }
     * 
     */
    public IdeFindResultRowDto createIdeFindResultRowDto() {
        return new IdeFindResultRowDto();
    }

    /**
     * Create an instance of {@link IdeDocumentDto }
     * 
     */
    public IdeDocumentDto createIdeDocumentDto() {
        return new IdeDocumentDto();
    }

    /**
     * Create an instance of {@link KeyboardStateBlobDto }
     * 
     */
    public KeyboardStateBlobDto createKeyboardStateBlobDto() {
        return new KeyboardStateBlobDto();
    }

    /**
     * Create an instance of {@link ArrayOfMouseScrollDto }
     * 
     */
    public ArrayOfMouseScrollDto createArrayOfMouseScrollDto() {
        return new ArrayOfMouseScrollDto();
    }

    /**
     * Create an instance of {@link OneNoteSectionDto }
     * 
     */
    public OneNoteSectionDto createOneNoteSectionDto() {
        return new OneNoteSectionDto();
    }

    /**
     * Create an instance of {@link MouseStateBlobDto }
     * 
     */
    public MouseStateBlobDto createMouseStateBlobDto() {
        return new MouseStateBlobDto();
    }

    /**
     * Create an instance of {@link ArrayOfApplicationStateDto }
     * 
     */
    public ArrayOfApplicationStateDto createArrayOfApplicationStateDto() {
        return new ArrayOfApplicationStateDto();
    }

    /**
     * Create an instance of {@link ApplicationRunDto }
     * 
     */
    public ApplicationRunDto createApplicationRunDto() {
        return new ApplicationRunDto();
    }

    /**
     * Create an instance of {@link OneNotePageDto }
     * 
     */
    public OneNotePageDto createOneNotePageDto() {
        return new OneNotePageDto();
    }

    /**
     * Create an instance of {@link MouseClickDto }
     * 
     */
    public MouseClickDto createMouseClickDto() {
        return new MouseClickDto();
    }

    /**
     * Create an instance of {@link OneNoteNotebookDto }
     * 
     */
    public OneNoteNotebookDto createOneNoteNotebookDto() {
        return new OneNoteNotebookDto();
    }

    /**
     * Create an instance of {@link MousePosDto }
     * 
     */
    public MousePosDto createMousePosDto() {
        return new MousePosDto();
    }

    /**
     * Create an instance of {@link MouseScrollDto }
     * 
     */
    public MouseScrollDto createMouseScrollDto() {
        return new MouseScrollDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApplicationFocusLostDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "ApplicationFocusLostDto")
    public JAXBElement<ApplicationFocusLostDto> createApplicationFocusLostDto(ApplicationFocusLostDto value) {
        return new JAXBElement<ApplicationFocusLostDto>(_ApplicationFocusLostDto_QNAME, ApplicationFocusLostDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyboardStateDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "KeyboardStateDto")
    public JAXBElement<KeyboardStateDto> createKeyboardStateDto(KeyboardStateDto value) {
        return new JAXBElement<KeyboardStateDto>(_KeyboardStateDto_QNAME, KeyboardStateDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OneNoteViewChangeDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "OneNoteViewChangeDto")
    public JAXBElement<OneNoteViewChangeDto> createOneNoteViewChangeDto(OneNoteViewChangeDto value) {
        return new JAXBElement<OneNoteViewChangeDto>(_OneNoteViewChangeDto_QNAME, OneNoteViewChangeDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdeFindOperationDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "IdeFindOperationDto")
    public JAXBElement<IdeFindOperationDto> createIdeFindOperationDto(IdeFindOperationDto value) {
        return new JAXBElement<IdeFindOperationDto>(_IdeFindOperationDto_QNAME, IdeFindOperationDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LyncStatusChangeDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "LyncStatusChangeDto")
    public JAXBElement<LyncStatusChangeDto> createLyncStatusChangeDto(LyncStatusChangeDto value) {
        return new JAXBElement<LyncStatusChangeDto>(_LyncStatusChangeDto_QNAME, LyncStatusChangeDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunningApplicationsListDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "RunningApplicationsListDto")
    public JAXBElement<RunningApplicationsListDto> createRunningApplicationsListDto(RunningApplicationsListDto value) {
        return new JAXBElement<RunningApplicationsListDto>(_RunningApplicationsListDto_QNAME, RunningApplicationsListDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdeCodeOperationDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "IdeCodeOperationDto")
    public JAXBElement<IdeCodeOperationDto> createIdeCodeOperationDto(IdeCodeOperationDto value) {
        return new JAXBElement<IdeCodeOperationDto>(_IdeCodeOperationDto_QNAME, IdeCodeOperationDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HwUsageDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "HwUsageDto")
    public JAXBElement<HwUsageDto> createHwUsageDto(HwUsageDto value) {
        return new JAXBElement<HwUsageDto>(_HwUsageDto_QNAME, HwUsageDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdeCheckinDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "IdeCheckinDto")
    public JAXBElement<IdeCheckinDto> createIdeCheckinDto(IdeCheckinDto value) {
        return new JAXBElement<IdeCheckinDto>(_IdeCheckinDto_QNAME, IdeCheckinDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdeDocumentOperationDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "IdeDocumentOperationDto")
    public JAXBElement<IdeDocumentOperationDto> createIdeDocumentOperationDto(IdeDocumentOperationDto value) {
        return new JAXBElement<IdeDocumentOperationDto>(_IdeDocumentOperationDto_QNAME, IdeDocumentOperationDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdeStateChangeDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "IdeStateChangeDto")
    public JAXBElement<IdeStateChangeDto> createIdeStateChangeDto(IdeStateChangeDto value) {
        return new JAXBElement<IdeStateChangeDto>(_IdeStateChangeDto_QNAME, IdeStateChangeDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdeCodeElementEventDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "IdeCodeElementEventDto")
    public JAXBElement<IdeCodeElementEventDto> createIdeCodeElementEventDto(IdeCodeElementEventDto value) {
        return new JAXBElement<IdeCodeElementEventDto>(_IdeCodeElementEventDto_QNAME, IdeCodeElementEventDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdeProjectOperationDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "IdeProjectOperationDto")
    public JAXBElement<IdeProjectOperationDto> createIdeProjectOperationDto(IdeProjectOperationDto value) {
        return new JAXBElement<IdeProjectOperationDto>(_IdeProjectOperationDto_QNAME, IdeProjectOperationDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WebBookmarkDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "WebBookmarkDto")
    public JAXBElement<WebBookmarkDto> createWebBookmarkDto(WebBookmarkDto value) {
        return new JAXBElement<WebBookmarkDto>(_WebBookmarkDto_QNAME, WebBookmarkDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WebSaveDocumentDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "WebSaveDocumentDto")
    public JAXBElement<WebSaveDocumentDto> createWebSaveDocumentDto(WebSaveDocumentDto value) {
        return new JAXBElement<WebSaveDocumentDto>(_WebSaveDocumentDto_QNAME, WebSaveDocumentDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MouseStateDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "MouseStateDto")
    public JAXBElement<MouseStateDto> createMouseStateDto(MouseStateDto value) {
        return new JAXBElement<MouseStateDto>(_MouseStateDto_QNAME, MouseStateDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OneNoteNavigateDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "OneNoteNavigateDto")
    public JAXBElement<OneNoteNavigateDto> createOneNoteNavigateDto(OneNoteNavigateDto value) {
        return new JAXBElement<OneNoteNavigateDto>(_OneNoteNavigateDto_QNAME, OneNoteNavigateDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WebNavigateDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "WebNavigateDto")
    public JAXBElement<WebNavigateDto> createWebNavigateDto(WebNavigateDto value) {
        return new JAXBElement<WebNavigateDto>(_WebNavigateDto_QNAME, WebNavigateDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WebTabOperationDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/IActivitySvc", name = "WebTabOperationDto")
    public JAXBElement<WebTabOperationDto> createWebTabOperationDto(WebTabOperationDto value) {
        return new JAXBElement<WebTabOperationDto>(_WebTabOperationDto_QNAME, WebTabOperationDto.class, null, value);
    }

}
