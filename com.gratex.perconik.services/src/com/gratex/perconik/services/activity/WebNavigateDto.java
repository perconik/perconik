
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WebNavigateDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WebNavigateDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}WebEventDto">
 *       &lt;attribute name="NavigationType" use="required" type="{http://www.gratex.com/PerConIk/IActivitySvc}WebNavigationTypeEnum" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WebNavigateDto")
public class WebNavigateDto
    extends WebEventDto
{

    @XmlAttribute(name = "NavigationType", required = true)
    protected WebNavigationTypeEnum navigationType;

    /**
     * Gets the value of the navigationType property.
     * 
     * @return
     *     possible object is
     *     {@link WebNavigationTypeEnum }
     *     
     */
    public WebNavigationTypeEnum getNavigationType() {
        return navigationType;
    }

    /**
     * Sets the value of the navigationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link WebNavigationTypeEnum }
     *     
     */
    public void setNavigationType(WebNavigationTypeEnum value) {
        this.navigationType = value;
    }

}
