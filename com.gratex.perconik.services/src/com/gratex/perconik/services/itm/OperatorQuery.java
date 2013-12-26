
package com.gratex.perconik.services.itm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import com.gratex.perconik.services.itm.serialization.queries.OperatorQueryOperators;


/**
 * <p>Java class for OperatorQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperatorQuery">
 *   &lt;complexContent>
 *     &lt;extension base="{http://perconik.fiit.stuba.sk/ITM}BaseAttributeQuery">
 *       &lt;sequence>
 *         &lt;element name="Operator" type="{http://schemas.datacontract.org/2004/07/ITMaintenance.Lib.DataContract.AttributeQueries}OperatorQuery.Operators" minOccurs="0"/>
 *         &lt;element name="SubQueries" type="{http://perconik.fiit.stuba.sk/ITM}ArrayOfBaseAttributeQuery" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperatorQuery", propOrder = {
    "operator",
    "subQueries"
})
public class OperatorQuery
    extends BaseAttributeQuery
{

    @XmlElement(name = "Operator")
    protected OperatorQueryOperators operator;
    @XmlElementRef(name = "SubQueries", namespace = "http://perconik.fiit.stuba.sk/ITM", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfBaseAttributeQuery> subQueries;

    /**
     * Gets the value of the operator property.
     * 
     * @return
     *     possible object is
     *     {@link OperatorQueryOperators }
     *     
     */
    public OperatorQueryOperators getOperator() {
        return operator;
    }

    /**
     * Sets the value of the operator property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperatorQueryOperators }
     *     
     */
    public void setOperator(OperatorQueryOperators value) {
        this.operator = value;
    }

    /**
     * Gets the value of the subQueries property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBaseAttributeQuery }{@code >}
     *     
     */
    public JAXBElement<ArrayOfBaseAttributeQuery> getSubQueries() {
        return subQueries;
    }

    /**
     * Sets the value of the subQueries property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBaseAttributeQuery }{@code >}
     *     
     */
    public void setSubQueries(JAXBElement<ArrayOfBaseAttributeQuery> value) {
        this.subQueries = value;
    }

}
