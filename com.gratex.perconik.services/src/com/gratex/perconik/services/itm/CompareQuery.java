
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import com.gratex.perconik.services.itm.serialization.queries.CompareQueryFunctors;


/**
 * <p>Java class for CompareQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CompareQuery">
 *   &lt;complexContent>
 *     &lt;extension base="{http://perconik.fiit.stuba.sk/ITM}AttributeQuery">
 *       &lt;sequence>
 *         &lt;element name="Functor" type="{http://schemas.datacontract.org/2004/07/ITMaintenance.Lib.DataContract.AttributeQueries}CompareQuery.Functors" minOccurs="0"/>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompareQuery", propOrder = {
    "functor",
    "value"
})
public class CompareQuery
    extends AttributeQuery
{

    @XmlElement(name = "Functor")
    protected CompareQueryFunctors functor;
    @XmlElementRef(name = "Value", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> value;

    /**
     * Gets the value of the functor property.
     * 
     * @return
     *     possible object is
     *     {@link CompareQueryFunctors }
     *     
     */
    public CompareQueryFunctors getFunctor() {
        return functor;
    }

    /**
     * Sets the value of the functor property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompareQueryFunctors }
     *     
     */
    public void setFunctor(CompareQueryFunctors value) {
        this.functor = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setValue(JAXBElement<Object> value) {
        this.value = value;
    }

}
