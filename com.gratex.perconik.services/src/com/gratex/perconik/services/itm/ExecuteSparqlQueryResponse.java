
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.gratex.perconik.services.itm.serialization.arrays.ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExecuteSparqlQueryResult" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZ_P7" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "executeSparqlQueryResult"
})
@XmlRootElement(name = "ExecuteSparqlQueryResponse")
public class ExecuteSparqlQueryResponse {

    @XmlElementRef(name = "ExecuteSparqlQueryResult", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7> executeSparqlQueryResult;

    /**
     * Gets the value of the executeSparqlQueryResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7 }{@code >}
     *     
     */
    public JAXBElement<ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7> getExecuteSparqlQueryResult() {
        return executeSparqlQueryResult;
    }

    /**
     * Sets the value of the executeSparqlQueryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7 }{@code >}
     *     
     */
    public void setExecuteSparqlQueryResult(JAXBElement<ArrayOfArrayOfKeyValueOfstringRDFDataWYX0VZP7> value) {
        this.executeSparqlQueryResult = value;
    }

}
