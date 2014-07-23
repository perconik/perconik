
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.gratex.perconik.services.itm.serialization.arrays.ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7;
import com.gratex.perconik.services.itm.serialization.arrays.ArrayOfanyType;
import com.gratex.perconik.services.itm.serialization.arrays.ArrayOfint;
import com.gratex.perconik.services.itm.serialization.arrays.ArrayOfstring;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gratex.perconik.services.itm package. 
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

    private final static QName _DataAttribute_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "DataAttribute");
    private final static QName _Selector_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Selector");
    private final static QName _ArrayOfInformationTag_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "ArrayOfInformationTag");
    private final static QName _CompareQuery_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "CompareQuery");
    private final static QName _AttributeQuery_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "AttributeQuery");
    private final static QName _ConnectionDetails_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "ConnectionDetails");
    private final static QName _SpecificTarget_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "SpecificTarget");
    private final static QName _ArrayOfDataAttribute_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "ArrayOfDataAttribute");
    private final static QName _EventListenerDetails_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "EventListenerDetails");
    private final static QName _RDFObject_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "RDFObject");
    private final static QName _OperatorQuery_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "OperatorQuery");
    private final static QName _ArrayQuery_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "ArrayQuery");
    private final static QName _ArrayOfBaseAttributeQuery_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "ArrayOfBaseAttributeQuery");
    private final static QName _ITarget_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "ITarget");
    private final static QName _InformationTag_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "InformationTag");
    private final static QName _RDFData_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "RDFData");
    private final static QName _RDFLiteral_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "RDFLiteral");
    private final static QName _Target_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Target");
    private final static QName _AstRcsContextConstraint_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "AstRcsContextConstraint");
    private final static QName _ArrayOfITarget_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "ArrayOfITarget");
    private final static QName _InformationTagConstraint_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "InformationTagConstraint");
    private final static QName _ActionType_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "ActionType");
    private final static QName _Body_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Body");
    private final static QName _Literal_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Literal");
    private final static QName _ElementMatchQuery_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "ElementMatchQuery");
    private final static QName _LocalizedText_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "LocalizedText");
    private final static QName _BaseAttributeQuery_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "BaseAttributeQuery");
    private final static QName _EventListenerResponse_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "EventListenerResponse");
    private final static QName _StructuredData_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "StructuredData");
    private final static QName _AttributeQueryAttribute_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Attribute");
    private final static QName _GetInformationTagResponseGetInformationTagResult_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "GetInformationTagResult");
    private final static QName _UnregisterEventListenerEventId_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "eventId");
    private final static QName _UpdateInformationTagInformationTag_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "informationTag");
    private final static QName _ConnectionDetailsUserName_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "UserName");
    private final static QName _ConnectionDetailsEndpoint_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Endpoint");
    private final static QName _ConnectionDetailsPassword_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Password");
    private final static QName _ConnectionDetailsDomain_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Domain");
    private final static QName _ExecuteSparqlQueryResponseExecuteSparqlQueryResult_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "ExecuteSparqlQueryResult");
    private final static QName _ITargetUri_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Uri");
    private final static QName _GetInformationTagsResponseGetInformationTagsResult_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "GetInformationTagsResult");
    private final static QName _InformationTagConstraintAuthors_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Authors");
    private final static QName _InformationTagConstraintCreatedAfter_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "CreatedAfter");
    private final static QName _InformationTagConstraintSavedBefore_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "SavedBefore");
    private final static QName _InformationTagConstraintSavedAfter_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "SavedAfter");
    private final static QName _InformationTagConstraintGeneratedBefore_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "GeneratedBefore");
    private final static QName _InformationTagConstraintHasAncestors_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "HasAncestors");
    private final static QName _InformationTagConstraintCreatedBefore_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "CreatedBefore");
    private final static QName _InformationTagConstraintTypes_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Types");
    private final static QName _InformationTagConstraintGeneratedAfter_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "GeneratedAfter");
    private final static QName _LiteralValue_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Value");
    private final static QName _LiteralType_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Type");
    private final static QName _GetInformationTagsUriResponseGetInformationTagsUriResult_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "GetInformationTagsUriResult");
    private final static QName _DeleteInformationTagUri_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "uri");
    private final static QName _RDFLiteralLanguage_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Language");
    private final static QName _RDFLiteralTypeUri_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "TypeUri");
    private final static QName _OperatorQuerySubQueries_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "SubQueries");
    private final static QName _GetInformationTagOfVersionResponseGetInformationTagOfVersionResult_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "GetInformationTagOfVersionResult");
    private final static QName _EventListenerResponseEventListenerId_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "EventListenerId");
    private final static QName _ElementMatchQuerySubQuery_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "SubQuery");
    private final static QName _GetInformationTagsUriTargetUries_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "targetUries");
    private final static QName _GetInformationTagsUriAstRcsContextConstraint_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "astRcsContextConstraint");
    private final static QName _ExecuteSparqlQueryQuery_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "query");
    private final static QName _GetInformationTagsConstraint_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "constraint");
    private final static QName _GetInformationTagsHistoricallyResponseGetInformationTagsHistoricallyResult_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "GetInformationTagsHistoricallyResult");
    private final static QName _EventListenerDetailsTargets_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Targets");
    private final static QName _EventListenerDetailsConnection_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Connection");
    private final static QName _RegisterEventListenerEventListenerDetails_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "eventListenerDetails");
    private final static QName _StructuredDataAttributes_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Attributes");
    private final static QName _SpecificTargetHasSource_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "HasSource");
    private final static QName _SpecificTargetHasSelector_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "HasSelector");
    private final static QName _AddInformationTagResponseAddInformationTagResult_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "AddInformationTagResult");
    private final static QName _InformationTagHasAncestor_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "HasAncestor");
    private final static QName _InformationTagAuthor_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Author");
    private final static QName _InformationTagHasBody_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "HasBody");
    private final static QName _InformationTagHasTarget_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "HasTarget");
    private final static QName _ArrayQueryValues_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "Values");
    private final static QName _RegisterEventListenerResponseRegisterEventListenerResult_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "RegisterEventListenerResult");
    private final static QName _AstRcsContextConstraintAstRcsProjectIds_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "AstRcsProjectIds");
    private final static QName _AstRcsContextConstraintAstRcsFileVersionIds_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "AstRcsFileVersionIds");
    private final static QName _AstRcsContextConstraintAstRcsChildrenEntities_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "AstRcsChildrenEntities");
    private final static QName _AstRcsContextConstraintAstRcsFileIds_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "AstRcsFileIds");
    private final static QName _AstRcsContextConstraintAstRcsParents_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "AstRcsParents");
    private final static QName _AstRcsContextConstraintAstRcsParentEntities_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "AstRcsParentEntities");
    private final static QName _AstRcsContextConstraintAstRcsChildren_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "AstRcsChildren");
    private final static QName _DataAttributePropertyUri_QNAME = new QName("http://perconik.fiit.stuba.sk/ITM", "PropertyUri");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gratex.perconik.services.itm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LocalizedText }
     * 
     */
    public LocalizedText createLocalizedText() {
        return new LocalizedText();
    }

    /**
     * Create an instance of {@link AddInformationTagResponse }
     * 
     */
    public AddInformationTagResponse createAddInformationTagResponse() {
        return new AddInformationTagResponse();
    }

    /**
     * Create an instance of {@link EventListenerResponse }
     * 
     */
    public EventListenerResponse createEventListenerResponse() {
        return new EventListenerResponse();
    }

    /**
     * Create an instance of {@link StructuredData }
     * 
     */
    public StructuredData createStructuredData() {
        return new StructuredData();
    }

    /**
     * Create an instance of {@link DeleteInformationTagResponse }
     * 
     */
    public DeleteInformationTagResponse createDeleteInformationTagResponse() {
        return new DeleteInformationTagResponse();
    }

    /**
     * Create an instance of {@link BaseAttributeQuery }
     * 
     */
    public BaseAttributeQuery createBaseAttributeQuery() {
        return new BaseAttributeQuery();
    }

    /**
     * Create an instance of {@link GetInformationTagsUri }
     * 
     */
    public GetInformationTagsUri createGetInformationTagsUri() {
        return new GetInformationTagsUri();
    }

    /**
     * Create an instance of {@link AstRcsContextConstraint }
     * 
     */
    public AstRcsContextConstraint createAstRcsContextConstraint() {
        return new AstRcsContextConstraint();
    }

    /**
     * Create an instance of {@link DeleteInformationTag }
     * 
     */
    public DeleteInformationTag createDeleteInformationTag() {
        return new DeleteInformationTag();
    }

    /**
     * Create an instance of {@link GetInformationTagsHistorically }
     * 
     */
    public GetInformationTagsHistorically createGetInformationTagsHistorically() {
        return new GetInformationTagsHistorically();
    }

    /**
     * Create an instance of {@link InformationTagConstraint }
     * 
     */
    public InformationTagConstraint createInformationTagConstraint() {
        return new InformationTagConstraint();
    }

    /**
     * Create an instance of {@link ExecuteSparqlQueryResponse }
     * 
     */
    public ExecuteSparqlQueryResponse createExecuteSparqlQueryResponse() {
        return new ExecuteSparqlQueryResponse();
    }

    /**
     * Create an instance of {@link RegisterEventListenerResponse }
     * 
     */
    public RegisterEventListenerResponse createRegisterEventListenerResponse() {
        return new RegisterEventListenerResponse();
    }

    /**
     * Create an instance of {@link AddInformationTag }
     * 
     */
    public AddInformationTag createAddInformationTag() {
        return new AddInformationTag();
    }

    /**
     * Create an instance of {@link InformationTag }
     * 
     */
    public InformationTag createInformationTag() {
        return new InformationTag();
    }

    /**
     * Create an instance of {@link UpdateInformationTagResponse }
     * 
     */
    public UpdateInformationTagResponse createUpdateInformationTagResponse() {
        return new UpdateInformationTagResponse();
    }

    /**
     * Create an instance of {@link Body }
     * 
     */
    public Body createBody() {
        return new Body();
    }

    /**
     * Create an instance of {@link Literal }
     * 
     */
    public Literal createLiteral() {
        return new Literal();
    }

    /**
     * Create an instance of {@link GetInformationTagResponse }
     * 
     */
    public GetInformationTagResponse createGetInformationTagResponse() {
        return new GetInformationTagResponse();
    }

    /**
     * Create an instance of {@link ElementMatchQuery }
     * 
     */
    public ElementMatchQuery createElementMatchQuery() {
        return new ElementMatchQuery();
    }

    /**
     * Create an instance of {@link RegisterEventListener }
     * 
     */
    public RegisterEventListener createRegisterEventListener() {
        return new RegisterEventListener();
    }

    /**
     * Create an instance of {@link EventListenerDetails }
     * 
     */
    public EventListenerDetails createEventListenerDetails() {
        return new EventListenerDetails();
    }

    /**
     * Create an instance of {@link RecoverInformationTag }
     * 
     */
    public RecoverInformationTag createRecoverInformationTag() {
        return new RecoverInformationTag();
    }

    /**
     * Create an instance of {@link GetInformationTag }
     * 
     */
    public GetInformationTag createGetInformationTag() {
        return new GetInformationTag();
    }

    /**
     * Create an instance of {@link GetInformationTagOfVersionResponse }
     * 
     */
    public GetInformationTagOfVersionResponse createGetInformationTagOfVersionResponse() {
        return new GetInformationTagOfVersionResponse();
    }

    /**
     * Create an instance of {@link ExecuteSparqlQuery }
     * 
     */
    public ExecuteSparqlQuery createExecuteSparqlQuery() {
        return new ExecuteSparqlQuery();
    }

    /**
     * Create an instance of {@link GetInformationTagOfVersion }
     * 
     */
    public GetInformationTagOfVersion createGetInformationTagOfVersion() {
        return new GetInformationTagOfVersion();
    }

    /**
     * Create an instance of {@link ArrayOfITarget }
     * 
     */
    public ArrayOfITarget createArrayOfITarget() {
        return new ArrayOfITarget();
    }

    /**
     * Create an instance of {@link GetInformationTagsCount }
     * 
     */
    public GetInformationTagsCount createGetInformationTagsCount() {
        return new GetInformationTagsCount();
    }

    /**
     * Create an instance of {@link Target }
     * 
     */
    public Target createTarget() {
        return new Target();
    }

    /**
     * Create an instance of {@link RDFLiteral }
     * 
     */
    public RDFLiteral createRDFLiteral() {
        return new RDFLiteral();
    }

    /**
     * Create an instance of {@link GetInformationTagsUriResponse }
     * 
     */
    public GetInformationTagsUriResponse createGetInformationTagsUriResponse() {
        return new GetInformationTagsUriResponse();
    }

    /**
     * Create an instance of {@link ArrayOfBaseAttributeQuery }
     * 
     */
    public ArrayOfBaseAttributeQuery createArrayOfBaseAttributeQuery() {
        return new ArrayOfBaseAttributeQuery();
    }

    /**
     * Create an instance of {@link UnregisterEventListenerResponse }
     * 
     */
    public UnregisterEventListenerResponse createUnregisterEventListenerResponse() {
        return new UnregisterEventListenerResponse();
    }

    /**
     * Create an instance of {@link RDFData }
     * 
     */
    public RDFData createRDFData() {
        return new RDFData();
    }

    /**
     * Create an instance of {@link GetInformationTags }
     * 
     */
    public GetInformationTags createGetInformationTags() {
        return new GetInformationTags();
    }

    /**
     * Create an instance of {@link ITarget }
     * 
     */
    public ITarget createITarget() {
        return new ITarget();
    }

    /**
     * Create an instance of {@link GetInformationTagsCountResponse }
     * 
     */
    public GetInformationTagsCountResponse createGetInformationTagsCountResponse() {
        return new GetInformationTagsCountResponse();
    }

    /**
     * Create an instance of {@link RecoverInformationTagResponse }
     * 
     */
    public RecoverInformationTagResponse createRecoverInformationTagResponse() {
        return new RecoverInformationTagResponse();
    }

    /**
     * Create an instance of {@link ArrayQuery }
     * 
     */
    public ArrayQuery createArrayQuery() {
        return new ArrayQuery();
    }

    /**
     * Create an instance of {@link RDFObject }
     * 
     */
    public RDFObject createRDFObject() {
        return new RDFObject();
    }

    /**
     * Create an instance of {@link OperatorQuery }
     * 
     */
    public OperatorQuery createOperatorQuery() {
        return new OperatorQuery();
    }

    /**
     * Create an instance of {@link GetInformationTagsHistoricallyResponse }
     * 
     */
    public GetInformationTagsHistoricallyResponse createGetInformationTagsHistoricallyResponse() {
        return new GetInformationTagsHistoricallyResponse();
    }

    /**
     * Create an instance of {@link ArrayOfInformationTag }
     * 
     */
    public ArrayOfInformationTag createArrayOfInformationTag() {
        return new ArrayOfInformationTag();
    }

    /**
     * Create an instance of {@link ConnectionDetails }
     * 
     */
    public ConnectionDetails createConnectionDetails() {
        return new ConnectionDetails();
    }

    /**
     * Create an instance of {@link GetInformationTagsResponse }
     * 
     */
    public GetInformationTagsResponse createGetInformationTagsResponse() {
        return new GetInformationTagsResponse();
    }

    /**
     * Create an instance of {@link ArrayOfDataAttribute }
     * 
     */
    public ArrayOfDataAttribute createArrayOfDataAttribute() {
        return new ArrayOfDataAttribute();
    }

    /**
     * Create an instance of {@link UnregisterEventListener }
     * 
     */
    public UnregisterEventListener createUnregisterEventListener() {
        return new UnregisterEventListener();
    }

    /**
     * Create an instance of {@link SpecificTarget }
     * 
     */
    public SpecificTarget createSpecificTarget() {
        return new SpecificTarget();
    }

    /**
     * Create an instance of {@link DataAttribute }
     * 
     */
    public DataAttribute createDataAttribute() {
        return new DataAttribute();
    }

    /**
     * Create an instance of {@link Selector }
     * 
     */
    public Selector createSelector() {
        return new Selector();
    }

    /**
     * Create an instance of {@link CompareQuery }
     * 
     */
    public CompareQuery createCompareQuery() {
        return new CompareQuery();
    }

    /**
     * Create an instance of {@link AttributeQuery }
     * 
     */
    public AttributeQuery createAttributeQuery() {
        return new AttributeQuery();
    }

    /**
     * Create an instance of {@link UpdateInformationTag }
     * 
     */
    public UpdateInformationTag createUpdateInformationTag() {
        return new UpdateInformationTag();
    }

    /**
     * Create an instance of {@link LocalizedText.KeyValueOfstringstring }
     * 
     */
    public LocalizedText.KeyValueOfstringstring createLocalizedTextKeyValueOfstringstring() {
        return new LocalizedText.KeyValueOfstringstring();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataAttribute }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "DataAttribute")
    public JAXBElement<DataAttribute> createDataAttribute(DataAttribute value) {
        return new JAXBElement<DataAttribute>(_DataAttribute_QNAME, DataAttribute.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Selector }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Selector")
    public JAXBElement<Selector> createSelector(Selector value) {
        return new JAXBElement<Selector>(_Selector_QNAME, Selector.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfInformationTag }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "ArrayOfInformationTag")
    public JAXBElement<ArrayOfInformationTag> createArrayOfInformationTag(ArrayOfInformationTag value) {
        return new JAXBElement<ArrayOfInformationTag>(_ArrayOfInformationTag_QNAME, ArrayOfInformationTag.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompareQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "CompareQuery")
    public JAXBElement<CompareQuery> createCompareQuery(CompareQuery value) {
        return new JAXBElement<CompareQuery>(_CompareQuery_QNAME, CompareQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "AttributeQuery")
    public JAXBElement<AttributeQuery> createAttributeQuery(AttributeQuery value) {
        return new JAXBElement<AttributeQuery>(_AttributeQuery_QNAME, AttributeQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectionDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "ConnectionDetails")
    public JAXBElement<ConnectionDetails> createConnectionDetails(ConnectionDetails value) {
        return new JAXBElement<ConnectionDetails>(_ConnectionDetails_QNAME, ConnectionDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpecificTarget }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "SpecificTarget")
    public JAXBElement<SpecificTarget> createSpecificTarget(SpecificTarget value) {
        return new JAXBElement<SpecificTarget>(_SpecificTarget_QNAME, SpecificTarget.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDataAttribute }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "ArrayOfDataAttribute")
    public JAXBElement<ArrayOfDataAttribute> createArrayOfDataAttribute(ArrayOfDataAttribute value) {
        return new JAXBElement<ArrayOfDataAttribute>(_ArrayOfDataAttribute_QNAME, ArrayOfDataAttribute.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventListenerDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "EventListenerDetails")
    public JAXBElement<EventListenerDetails> createEventListenerDetails(EventListenerDetails value) {
        return new JAXBElement<EventListenerDetails>(_EventListenerDetails_QNAME, EventListenerDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RDFObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "RDFObject")
    public JAXBElement<RDFObject> createRDFObject(RDFObject value) {
        return new JAXBElement<RDFObject>(_RDFObject_QNAME, RDFObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperatorQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "OperatorQuery")
    public JAXBElement<OperatorQuery> createOperatorQuery(OperatorQuery value) {
        return new JAXBElement<OperatorQuery>(_OperatorQuery_QNAME, OperatorQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "ArrayQuery")
    public JAXBElement<ArrayQuery> createArrayQuery(ArrayQuery value) {
        return new JAXBElement<ArrayQuery>(_ArrayQuery_QNAME, ArrayQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBaseAttributeQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "ArrayOfBaseAttributeQuery")
    public JAXBElement<ArrayOfBaseAttributeQuery> createArrayOfBaseAttributeQuery(ArrayOfBaseAttributeQuery value) {
        return new JAXBElement<ArrayOfBaseAttributeQuery>(_ArrayOfBaseAttributeQuery_QNAME, ArrayOfBaseAttributeQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ITarget }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "ITarget")
    public JAXBElement<ITarget> createITarget(ITarget value) {
        return new JAXBElement<ITarget>(_ITarget_QNAME, ITarget.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformationTag }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "InformationTag")
    public JAXBElement<InformationTag> createInformationTag(InformationTag value) {
        return new JAXBElement<InformationTag>(_InformationTag_QNAME, InformationTag.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RDFData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "RDFData")
    public JAXBElement<RDFData> createRDFData(RDFData value) {
        return new JAXBElement<RDFData>(_RDFData_QNAME, RDFData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RDFLiteral }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "RDFLiteral")
    public JAXBElement<RDFLiteral> createRDFLiteral(RDFLiteral value) {
        return new JAXBElement<RDFLiteral>(_RDFLiteral_QNAME, RDFLiteral.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Target }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Target")
    public JAXBElement<Target> createTarget(Target value) {
        return new JAXBElement<Target>(_Target_QNAME, Target.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AstRcsContextConstraint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "AstRcsContextConstraint")
    public JAXBElement<AstRcsContextConstraint> createAstRcsContextConstraint(AstRcsContextConstraint value) {
        return new JAXBElement<AstRcsContextConstraint>(_AstRcsContextConstraint_QNAME, AstRcsContextConstraint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfITarget }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "ArrayOfITarget")
    public JAXBElement<ArrayOfITarget> createArrayOfITarget(ArrayOfITarget value) {
        return new JAXBElement<ArrayOfITarget>(_ArrayOfITarget_QNAME, ArrayOfITarget.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformationTagConstraint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "InformationTagConstraint")
    public JAXBElement<InformationTagConstraint> createInformationTagConstraint(InformationTagConstraint value) {
        return new JAXBElement<InformationTagConstraint>(_InformationTagConstraint_QNAME, InformationTagConstraint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "ActionType")
    public JAXBElement<ActionType> createActionType(ActionType value) {
        return new JAXBElement<ActionType>(_ActionType_QNAME, ActionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Body }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Body")
    public JAXBElement<Body> createBody(Body value) {
        return new JAXBElement<Body>(_Body_QNAME, Body.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Literal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Literal")
    public JAXBElement<Literal> createLiteral(Literal value) {
        return new JAXBElement<Literal>(_Literal_QNAME, Literal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElementMatchQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "ElementMatchQuery")
    public JAXBElement<ElementMatchQuery> createElementMatchQuery(ElementMatchQuery value) {
        return new JAXBElement<ElementMatchQuery>(_ElementMatchQuery_QNAME, ElementMatchQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocalizedText }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "LocalizedText")
    public JAXBElement<LocalizedText> createLocalizedText(LocalizedText value) {
        return new JAXBElement<LocalizedText>(_LocalizedText_QNAME, LocalizedText.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseAttributeQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "BaseAttributeQuery")
    public JAXBElement<BaseAttributeQuery> createBaseAttributeQuery(BaseAttributeQuery value) {
        return new JAXBElement<BaseAttributeQuery>(_BaseAttributeQuery_QNAME, BaseAttributeQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventListenerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "EventListenerResponse")
    public JAXBElement<EventListenerResponse> createEventListenerResponse(EventListenerResponse value) {
        return new JAXBElement<EventListenerResponse>(_EventListenerResponse_QNAME, EventListenerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StructuredData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "StructuredData")
    public JAXBElement<StructuredData> createStructuredData(StructuredData value) {
        return new JAXBElement<StructuredData>(_StructuredData_QNAME, StructuredData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Attribute", scope = AttributeQuery.class)
    public JAXBElement<String> createAttributeQueryAttribute(String value) {
        return new JAXBElement<String>(_AttributeQueryAttribute_QNAME, String.class, AttributeQuery.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformationTag }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "GetInformationTagResult", scope = GetInformationTagResponse.class)
    public JAXBElement<InformationTag> createGetInformationTagResponseGetInformationTagResult(InformationTag value) {
        return new JAXBElement<InformationTag>(_GetInformationTagResponseGetInformationTagResult_QNAME, InformationTag.class, GetInformationTagResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "eventId", scope = UnregisterEventListener.class)
    public JAXBElement<String> createUnregisterEventListenerEventId(String value) {
        return new JAXBElement<String>(_UnregisterEventListenerEventId_QNAME, String.class, UnregisterEventListener.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformationTag }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "informationTag", scope = UpdateInformationTag.class)
    public JAXBElement<InformationTag> createUpdateInformationTagInformationTag(InformationTag value) {
        return new JAXBElement<InformationTag>(_UpdateInformationTagInformationTag_QNAME, InformationTag.class, UpdateInformationTag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformationTag }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "informationTag", scope = AddInformationTag.class)
    public JAXBElement<InformationTag> createAddInformationTagInformationTag(InformationTag value) {
        return new JAXBElement<InformationTag>(_UpdateInformationTagInformationTag_QNAME, InformationTag.class, AddInformationTag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "UserName", scope = ConnectionDetails.class)
    public JAXBElement<String> createConnectionDetailsUserName(String value) {
        return new JAXBElement<String>(_ConnectionDetailsUserName_QNAME, String.class, ConnectionDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Endpoint", scope = ConnectionDetails.class)
    public JAXBElement<String> createConnectionDetailsEndpoint(String value) {
        return new JAXBElement<String>(_ConnectionDetailsEndpoint_QNAME, String.class, ConnectionDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Password", scope = ConnectionDetails.class)
    public JAXBElement<String> createConnectionDetailsPassword(String value) {
        return new JAXBElement<String>(_ConnectionDetailsPassword_QNAME, String.class, ConnectionDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Domain", scope = ConnectionDetails.class)
    public JAXBElement<String> createConnectionDetailsDomain(String value) {
        return new JAXBElement<String>(_ConnectionDetailsDomain_QNAME, String.class, ConnectionDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "ExecuteSparqlQueryResult", scope = ExecuteSparqlQueryResponse.class)
    public JAXBElement<ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7> createExecuteSparqlQueryResponseExecuteSparqlQueryResult(ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7 value) {
        return new JAXBElement<ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7>(_ExecuteSparqlQueryResponseExecuteSparqlQueryResult_QNAME, ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7 .class, ExecuteSparqlQueryResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Uri", scope = ITarget.class)
    public JAXBElement<String> createITargetUri(String value) {
        return new JAXBElement<String>(_ITargetUri_QNAME, String.class, ITarget.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfInformationTag }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "GetInformationTagsResult", scope = GetInformationTagsResponse.class)
    public JAXBElement<ArrayOfInformationTag> createGetInformationTagsResponseGetInformationTagsResult(ArrayOfInformationTag value) {
        return new JAXBElement<ArrayOfInformationTag>(_GetInformationTagsResponseGetInformationTagsResult_QNAME, ArrayOfInformationTag.class, GetInformationTagsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Authors", scope = InformationTagConstraint.class)
    public JAXBElement<ArrayOfstring> createInformationTagConstraintAuthors(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InformationTagConstraintAuthors_QNAME, ArrayOfstring.class, InformationTagConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "CreatedAfter", scope = InformationTagConstraint.class)
    public JAXBElement<XMLGregorianCalendar> createInformationTagConstraintCreatedAfter(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_InformationTagConstraintCreatedAfter_QNAME, XMLGregorianCalendar.class, InformationTagConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "SavedBefore", scope = InformationTagConstraint.class)
    public JAXBElement<XMLGregorianCalendar> createInformationTagConstraintSavedBefore(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_InformationTagConstraintSavedBefore_QNAME, XMLGregorianCalendar.class, InformationTagConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "SavedAfter", scope = InformationTagConstraint.class)
    public JAXBElement<XMLGregorianCalendar> createInformationTagConstraintSavedAfter(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_InformationTagConstraintSavedAfter_QNAME, XMLGregorianCalendar.class, InformationTagConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "GeneratedBefore", scope = InformationTagConstraint.class)
    public JAXBElement<XMLGregorianCalendar> createInformationTagConstraintGeneratedBefore(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_InformationTagConstraintGeneratedBefore_QNAME, XMLGregorianCalendar.class, InformationTagConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseAttributeQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "AttributeQuery", scope = InformationTagConstraint.class)
    public JAXBElement<BaseAttributeQuery> createInformationTagConstraintAttributeQuery(BaseAttributeQuery value) {
        return new JAXBElement<BaseAttributeQuery>(_AttributeQuery_QNAME, BaseAttributeQuery.class, InformationTagConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "HasAncestors", scope = InformationTagConstraint.class)
    public JAXBElement<ArrayOfstring> createInformationTagConstraintHasAncestors(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InformationTagConstraintHasAncestors_QNAME, ArrayOfstring.class, InformationTagConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "CreatedBefore", scope = InformationTagConstraint.class)
    public JAXBElement<XMLGregorianCalendar> createInformationTagConstraintCreatedBefore(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_InformationTagConstraintCreatedBefore_QNAME, XMLGregorianCalendar.class, InformationTagConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Types", scope = InformationTagConstraint.class)
    public JAXBElement<ArrayOfstring> createInformationTagConstraintTypes(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InformationTagConstraintTypes_QNAME, ArrayOfstring.class, InformationTagConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AstRcsContextConstraint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "AstRcsContextConstraint", scope = InformationTagConstraint.class)
    public JAXBElement<AstRcsContextConstraint> createInformationTagConstraintAstRcsContextConstraint(AstRcsContextConstraint value) {
        return new JAXBElement<AstRcsContextConstraint>(_AstRcsContextConstraint_QNAME, AstRcsContextConstraint.class, InformationTagConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "GeneratedAfter", scope = InformationTagConstraint.class)
    public JAXBElement<XMLGregorianCalendar> createInformationTagConstraintGeneratedAfter(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_InformationTagConstraintGeneratedAfter_QNAME, XMLGregorianCalendar.class, InformationTagConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Value", scope = Literal.class)
    public JAXBElement<Object> createLiteralValue(Object value) {
        return new JAXBElement<Object>(_LiteralValue_QNAME, Object.class, Literal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Type", scope = Literal.class)
    public JAXBElement<String> createLiteralType(String value) {
        return new JAXBElement<String>(_LiteralType_QNAME, String.class, Literal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "GetInformationTagsUriResult", scope = GetInformationTagsUriResponse.class)
    public JAXBElement<ArrayOfstring> createGetInformationTagsUriResponseGetInformationTagsUriResult(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_GetInformationTagsUriResponseGetInformationTagsUriResult_QNAME, ArrayOfstring.class, GetInformationTagsUriResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "uri", scope = DeleteInformationTag.class)
    public JAXBElement<String> createDeleteInformationTagUri(String value) {
        return new JAXBElement<String>(_DeleteInformationTagUri_QNAME, String.class, DeleteInformationTag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Language", scope = RDFLiteral.class)
    public JAXBElement<String> createRDFLiteralLanguage(String value) {
        return new JAXBElement<String>(_RDFLiteralLanguage_QNAME, String.class, RDFLiteral.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "TypeUri", scope = RDFLiteral.class)
    public JAXBElement<String> createRDFLiteralTypeUri(String value) {
        return new JAXBElement<String>(_RDFLiteralTypeUri_QNAME, String.class, RDFLiteral.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Value", scope = RDFLiteral.class)
    public JAXBElement<Object> createRDFLiteralValue(Object value) {
        return new JAXBElement<Object>(_LiteralValue_QNAME, Object.class, RDFLiteral.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBaseAttributeQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "SubQueries", scope = OperatorQuery.class)
    public JAXBElement<ArrayOfBaseAttributeQuery> createOperatorQuerySubQueries(ArrayOfBaseAttributeQuery value) {
        return new JAXBElement<ArrayOfBaseAttributeQuery>(_OperatorQuerySubQueries_QNAME, ArrayOfBaseAttributeQuery.class, OperatorQuery.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "uri", scope = RecoverInformationTag.class)
    public JAXBElement<String> createRecoverInformationTagUri(String value) {
        return new JAXBElement<String>(_DeleteInformationTagUri_QNAME, String.class, RecoverInformationTag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformationTag }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "GetInformationTagOfVersionResult", scope = GetInformationTagOfVersionResponse.class)
    public JAXBElement<InformationTag> createGetInformationTagOfVersionResponseGetInformationTagOfVersionResult(InformationTag value) {
        return new JAXBElement<InformationTag>(_GetInformationTagOfVersionResponseGetInformationTagOfVersionResult_QNAME, InformationTag.class, GetInformationTagOfVersionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "EventListenerId", scope = EventListenerResponse.class)
    public JAXBElement<String> createEventListenerResponseEventListenerId(String value) {
        return new JAXBElement<String>(_EventListenerResponseEventListenerId_QNAME, String.class, EventListenerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformationTag }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "InformationTag", scope = EventListenerResponse.class)
    public JAXBElement<InformationTag> createEventListenerResponseInformationTag(InformationTag value) {
        return new JAXBElement<InformationTag>(_InformationTag_QNAME, InformationTag.class, EventListenerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseAttributeQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "SubQuery", scope = ElementMatchQuery.class)
    public JAXBElement<BaseAttributeQuery> createElementMatchQuerySubQuery(BaseAttributeQuery value) {
        return new JAXBElement<BaseAttributeQuery>(_ElementMatchQuerySubQuery_QNAME, BaseAttributeQuery.class, ElementMatchQuery.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Uri", scope = RDFObject.class)
    public JAXBElement<String> createRDFObjectUri(String value) {
        return new JAXBElement<String>(_ITargetUri_QNAME, String.class, RDFObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "targetUries", scope = GetInformationTagsUri.class)
    public JAXBElement<ArrayOfstring> createGetInformationTagsUriTargetUries(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_GetInformationTagsUriTargetUries_QNAME, ArrayOfstring.class, GetInformationTagsUri.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AstRcsContextConstraint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "astRcsContextConstraint", scope = GetInformationTagsUri.class)
    public JAXBElement<AstRcsContextConstraint> createGetInformationTagsUriAstRcsContextConstraint(AstRcsContextConstraint value) {
        return new JAXBElement<AstRcsContextConstraint>(_GetInformationTagsUriAstRcsContextConstraint_QNAME, AstRcsContextConstraint.class, GetInformationTagsUri.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "query", scope = ExecuteSparqlQuery.class)
    public JAXBElement<String> createExecuteSparqlQueryQuery(String value) {
        return new JAXBElement<String>(_ExecuteSparqlQueryQuery_QNAME, String.class, ExecuteSparqlQuery.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformationTagConstraint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "constraint", scope = GetInformationTags.class)
    public JAXBElement<InformationTagConstraint> createGetInformationTagsConstraint(InformationTagConstraint value) {
        return new JAXBElement<InformationTagConstraint>(_GetInformationTagsConstraint_QNAME, InformationTagConstraint.class, GetInformationTags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "targetUries", scope = GetInformationTags.class)
    public JAXBElement<ArrayOfstring> createGetInformationTagsTargetUries(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_GetInformationTagsUriTargetUries_QNAME, ArrayOfstring.class, GetInformationTags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfInformationTag }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "GetInformationTagsHistoricallyResult", scope = GetInformationTagsHistoricallyResponse.class)
    public JAXBElement<ArrayOfInformationTag> createGetInformationTagsHistoricallyResponseGetInformationTagsHistoricallyResult(ArrayOfInformationTag value) {
        return new JAXBElement<ArrayOfInformationTag>(_GetInformationTagsHistoricallyResponseGetInformationTagsHistoricallyResult_QNAME, ArrayOfInformationTag.class, GetInformationTagsHistoricallyResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Targets", scope = EventListenerDetails.class)
    public JAXBElement<ArrayOfstring> createEventListenerDetailsTargets(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_EventListenerDetailsTargets_QNAME, ArrayOfstring.class, EventListenerDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformationTagConstraint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "InformationTagConstraint", scope = EventListenerDetails.class)
    public JAXBElement<InformationTagConstraint> createEventListenerDetailsInformationTagConstraint(InformationTagConstraint value) {
        return new JAXBElement<InformationTagConstraint>(_InformationTagConstraint_QNAME, InformationTagConstraint.class, EventListenerDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectionDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Connection", scope = EventListenerDetails.class)
    public JAXBElement<ConnectionDetails> createEventListenerDetailsConnection(ConnectionDetails value) {
        return new JAXBElement<ConnectionDetails>(_EventListenerDetailsConnection_QNAME, ConnectionDetails.class, EventListenerDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Value", scope = CompareQuery.class)
    public JAXBElement<Object> createCompareQueryValue(Object value) {
        return new JAXBElement<Object>(_LiteralValue_QNAME, Object.class, CompareQuery.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformationTagConstraint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "constraint", scope = GetInformationTagsCount.class)
    public JAXBElement<InformationTagConstraint> createGetInformationTagsCountConstraint(InformationTagConstraint value) {
        return new JAXBElement<InformationTagConstraint>(_GetInformationTagsConstraint_QNAME, InformationTagConstraint.class, GetInformationTagsCount.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "targetUries", scope = GetInformationTagsCount.class)
    public JAXBElement<ArrayOfstring> createGetInformationTagsCountTargetUries(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_GetInformationTagsUriTargetUries_QNAME, ArrayOfstring.class, GetInformationTagsCount.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformationTagConstraint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "constraint", scope = GetInformationTagsHistorically.class)
    public JAXBElement<InformationTagConstraint> createGetInformationTagsHistoricallyConstraint(InformationTagConstraint value) {
        return new JAXBElement<InformationTagConstraint>(_GetInformationTagsConstraint_QNAME, InformationTagConstraint.class, GetInformationTagsHistorically.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "targetUries", scope = GetInformationTagsHistorically.class)
    public JAXBElement<ArrayOfstring> createGetInformationTagsHistoricallyTargetUries(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_GetInformationTagsUriTargetUries_QNAME, ArrayOfstring.class, GetInformationTagsHistorically.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "uri", scope = GetInformationTagOfVersion.class)
    public JAXBElement<String> createGetInformationTagOfVersionUri(String value) {
        return new JAXBElement<String>(_DeleteInformationTagUri_QNAME, String.class, GetInformationTagOfVersion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventListenerDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "eventListenerDetails", scope = RegisterEventListener.class)
    public JAXBElement<EventListenerDetails> createRegisterEventListenerEventListenerDetails(EventListenerDetails value) {
        return new JAXBElement<EventListenerDetails>(_RegisterEventListenerEventListenerDetails_QNAME, EventListenerDetails.class, RegisterEventListener.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDataAttribute }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Attributes", scope = StructuredData.class)
    public JAXBElement<ArrayOfDataAttribute> createStructuredDataAttributes(ArrayOfDataAttribute value) {
        return new JAXBElement<ArrayOfDataAttribute>(_StructuredDataAttributes_QNAME, ArrayOfDataAttribute.class, StructuredData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Type", scope = StructuredData.class)
    public JAXBElement<String> createStructuredDataType(String value) {
        return new JAXBElement<String>(_LiteralType_QNAME, String.class, StructuredData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Uri", scope = StructuredData.class)
    public JAXBElement<String> createStructuredDataUri(String value) {
        return new JAXBElement<String>(_ITargetUri_QNAME, String.class, StructuredData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "uri", scope = GetInformationTag.class)
    public JAXBElement<String> createGetInformationTagUri(String value) {
        return new JAXBElement<String>(_DeleteInformationTagUri_QNAME, String.class, GetInformationTag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "HasSource", scope = SpecificTarget.class)
    public JAXBElement<String> createSpecificTargetHasSource(String value) {
        return new JAXBElement<String>(_SpecificTargetHasSource_QNAME, String.class, SpecificTarget.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Selector }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "HasSelector", scope = SpecificTarget.class)
    public JAXBElement<Selector> createSpecificTargetHasSelector(Selector value) {
        return new JAXBElement<Selector>(_SpecificTargetHasSelector_QNAME, Selector.class, SpecificTarget.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "AddInformationTagResult", scope = AddInformationTagResponse.class)
    public JAXBElement<String> createAddInformationTagResponseAddInformationTagResult(String value) {
        return new JAXBElement<String>(_AddInformationTagResponseAddInformationTagResult_QNAME, String.class, AddInformationTagResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "HasAncestor", scope = InformationTag.class)
    public JAXBElement<ArrayOfstring> createInformationTagHasAncestor(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InformationTagHasAncestor_QNAME, ArrayOfstring.class, InformationTag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Author", scope = InformationTag.class)
    public JAXBElement<String> createInformationTagAuthor(String value) {
        return new JAXBElement<String>(_InformationTagAuthor_QNAME, String.class, InformationTag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Body }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "HasBody", scope = InformationTag.class)
    public JAXBElement<Body> createInformationTagHasBody(Body value) {
        return new JAXBElement<Body>(_InformationTagHasBody_QNAME, Body.class, InformationTag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfITarget }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "HasTarget", scope = InformationTag.class)
    public JAXBElement<ArrayOfITarget> createInformationTagHasTarget(ArrayOfITarget value) {
        return new JAXBElement<ArrayOfITarget>(_InformationTagHasTarget_QNAME, ArrayOfITarget.class, InformationTag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Type", scope = InformationTag.class)
    public JAXBElement<String> createInformationTagType(String value) {
        return new JAXBElement<String>(_LiteralType_QNAME, String.class, InformationTag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Uri", scope = InformationTag.class)
    public JAXBElement<String> createInformationTagUri(String value) {
        return new JAXBElement<String>(_ITargetUri_QNAME, String.class, InformationTag.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfanyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Values", scope = ArrayQuery.class)
    public JAXBElement<ArrayOfanyType> createArrayQueryValues(ArrayOfanyType value) {
        return new JAXBElement<ArrayOfanyType>(_ArrayQueryValues_QNAME, ArrayOfanyType.class, ArrayQuery.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "RegisterEventListenerResult", scope = RegisterEventListenerResponse.class)
    public JAXBElement<String> createRegisterEventListenerResponseRegisterEventListenerResult(String value) {
        return new JAXBElement<String>(_RegisterEventListenerResponseRegisterEventListenerResult_QNAME, String.class, RegisterEventListenerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "AstRcsProjectIds", scope = AstRcsContextConstraint.class)
    public JAXBElement<ArrayOfint> createAstRcsContextConstraintAstRcsProjectIds(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_AstRcsContextConstraintAstRcsProjectIds_QNAME, ArrayOfint.class, AstRcsContextConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "AstRcsFileVersionIds", scope = AstRcsContextConstraint.class)
    public JAXBElement<ArrayOfint> createAstRcsContextConstraintAstRcsFileVersionIds(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_AstRcsContextConstraintAstRcsFileVersionIds_QNAME, ArrayOfint.class, AstRcsContextConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "AstRcsChildrenEntities", scope = AstRcsContextConstraint.class)
    public JAXBElement<ArrayOfint> createAstRcsContextConstraintAstRcsChildrenEntities(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_AstRcsContextConstraintAstRcsChildrenEntities_QNAME, ArrayOfint.class, AstRcsContextConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "AstRcsFileIds", scope = AstRcsContextConstraint.class)
    public JAXBElement<ArrayOfint> createAstRcsContextConstraintAstRcsFileIds(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_AstRcsContextConstraintAstRcsFileIds_QNAME, ArrayOfint.class, AstRcsContextConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "AstRcsParents", scope = AstRcsContextConstraint.class)
    public JAXBElement<ArrayOfint> createAstRcsContextConstraintAstRcsParents(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_AstRcsContextConstraintAstRcsParents_QNAME, ArrayOfint.class, AstRcsContextConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "AstRcsParentEntities", scope = AstRcsContextConstraint.class)
    public JAXBElement<ArrayOfint> createAstRcsContextConstraintAstRcsParentEntities(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_AstRcsContextConstraintAstRcsParentEntities_QNAME, ArrayOfint.class, AstRcsContextConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "AstRcsChildren", scope = AstRcsContextConstraint.class)
    public JAXBElement<ArrayOfint> createAstRcsContextConstraintAstRcsChildren(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_AstRcsContextConstraintAstRcsChildren_QNAME, ArrayOfint.class, AstRcsContextConstraint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfanyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "Values", scope = DataAttribute.class)
    public JAXBElement<ArrayOfanyType> createDataAttributeValues(ArrayOfanyType value) {
        return new JAXBElement<ArrayOfanyType>(_ArrayQueryValues_QNAME, ArrayOfanyType.class, DataAttribute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://perconik.fiit.stuba.sk/ITM", name = "PropertyUri", scope = DataAttribute.class)
    public JAXBElement<String> createDataAttributePropertyUri(String value) {
        return new JAXBElement<String>(_DataAttributePropertyUri_QNAME, String.class, DataAttribute.class, value);
    }

}
