
package com.gratex.perconik.services.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumTagAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnumTagAttribute">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/TagAdm/ITagProfileWcfSvc}TagAttribute">
 *       &lt;sequence>
 *         &lt;element name="Values" type="{http://www.gratex.com/PerConIk/TagAdm/ITagProfileWcfSvc}ArrayOfEnumValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnumTagAttribute", propOrder = {
    "values"
})
public class EnumTagAttribute
    extends TagAttribute
{

    @XmlElement(name = "Values")
    protected ArrayOfEnumValue values;

    /**
     * Gets the value of the values property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEnumValue }
     *     
     */
    public ArrayOfEnumValue getValues() {
        return values;
    }

    /**
     * Sets the value of the values property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEnumValue }
     *     
     */
    public void setValues(ArrayOfEnumValue value) {
        this.values = value;
    }

}
