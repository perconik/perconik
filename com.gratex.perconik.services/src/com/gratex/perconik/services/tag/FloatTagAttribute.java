
package com.gratex.perconik.services.tag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FloatTagAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FloatTagAttribute">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/TagAdm/ITagProfileWcfSvc}TagAttribute">
 *       &lt;sequence>
 *         &lt;element name="Minimum" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Maximum" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FloatTagAttribute", propOrder = {
    "minimum",
    "maximum"
})
public class FloatTagAttribute
    extends TagAttribute
{

    @XmlElement(name = "Minimum", required = true, type = Float.class, nillable = true)
    protected Float minimum;
    @XmlElement(name = "Maximum", required = true, type = Float.class, nillable = true)
    protected Float maximum;

    /**
     * Gets the value of the minimum property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getMinimum() {
        return minimum;
    }

    /**
     * Sets the value of the minimum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setMinimum(Float value) {
        this.minimum = value;
    }

    /**
     * Gets the value of the maximum property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getMaximum() {
        return maximum;
    }

    /**
     * Sets the value of the maximum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setMaximum(Float value) {
        this.maximum = value;
    }

}
