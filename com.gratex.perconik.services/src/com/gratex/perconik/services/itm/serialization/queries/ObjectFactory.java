
package com.gratex.perconik.services.itm.serialization.queries;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gratex.perconik.services.itm.serialization.queries package. 
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

    private final static QName _CompareQueryFunctors_QNAME = new QName("http://schemas.datacontract.org/2004/07/ITMaintenance.Lib.DataContract.AttributeQueries", "CompareQuery.Functors");
    private final static QName _ArrayQueryFunctors_QNAME = new QName("http://schemas.datacontract.org/2004/07/ITMaintenance.Lib.DataContract.AttributeQueries", "ArrayQuery.Functors");
    private final static QName _OperatorQueryOperators_QNAME = new QName("http://schemas.datacontract.org/2004/07/ITMaintenance.Lib.DataContract.AttributeQueries", "OperatorQuery.Operators");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gratex.perconik.services.itm.serialization.queries
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompareQueryFunctors }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/ITMaintenance.Lib.DataContract.AttributeQueries", name = "CompareQuery.Functors")
    public JAXBElement<CompareQueryFunctors> createCompareQueryFunctors(CompareQueryFunctors value) {
        return new JAXBElement<CompareQueryFunctors>(_CompareQueryFunctors_QNAME, CompareQueryFunctors.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayQueryFunctors }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/ITMaintenance.Lib.DataContract.AttributeQueries", name = "ArrayQuery.Functors")
    public JAXBElement<ArrayQueryFunctors> createArrayQueryFunctors(ArrayQueryFunctors value) {
        return new JAXBElement<ArrayQueryFunctors>(_ArrayQueryFunctors_QNAME, ArrayQueryFunctors.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperatorQueryOperators }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/ITMaintenance.Lib.DataContract.AttributeQueries", name = "OperatorQuery.Operators")
    public JAXBElement<OperatorQueryOperators> createOperatorQueryOperators(OperatorQueryOperators value) {
        return new JAXBElement<OperatorQueryOperators>(_OperatorQueryOperators_QNAME, OperatorQueryOperators.class, null, value);
    }

}
