
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import com.gratex.perconik.services.itm.serialization.arrays.ArrayOfanyType;
import com.gratex.perconik.services.itm.serialization.queries.ArrayQueryFunctors;


/**
 * <p>Java class for ArrayQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayQuery">
 *   &lt;complexContent>
 *     &lt;extension base="{http://perconik.fiit.stuba.sk/ITM}AttributeQuery">
 *       &lt;sequence>
 *         &lt;element name="Functor" type="{http://schemas.datacontract.org/2004/07/ITMaintenance.Lib.DataContract.AttributeQueries}ArrayQuery.Functors" minOccurs="0"/>
 *         &lt;element name="Values" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfanyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayQuery", propOrder = {
    "functor",
    "values"
})
public class ArrayQuery
    extends AttributeQuery
{

    @XmlElement(name = "Functor")
    protected ArrayQueryFunctors functor;
    @XmlElementRef(name = "Values", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfanyType> values;

    /**
     * Gets the value of the functor property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayQueryFunctors }
     *     
     */
    public ArrayQueryFunctors getFunctor() {
        return functor;
    }

    /**
     * Sets the value of the functor property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayQueryFunctors }
     *     
     */
    public void setFunctor(ArrayQueryFunctors value) {
        this.functor = value;
    }

    /**
     * Gets the value of the values property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfanyType }{@code >}
     *     
     */
    public JAXBElement<ArrayOfanyType> getValues() {
        return values;
    }

    /**
     * Sets the value of the values property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfanyType }{@code >}
     *     
     */
    public void setValues(JAXBElement<ArrayOfanyType> value) {
        this.values = value;
    }

}
