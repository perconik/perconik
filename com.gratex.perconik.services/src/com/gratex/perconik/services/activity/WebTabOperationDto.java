
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WebTabOperationDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WebTabOperationDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}WebEventDto">
 *       &lt;attribute name="Operation" use="required" type="{http://www.gratex.com/PerConIk/IActivitySvc}WebTabOperationTypeEnum" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WebTabOperationDto")
public class WebTabOperationDto
    extends WebEventDto
{

    @XmlAttribute(name = "Operation", required = true)
    protected WebTabOperationTypeEnum operation;

    /**
     * Gets the value of the operation property.
     * 
     * @return
     *     possible object is
     *     {@link WebTabOperationTypeEnum }
     *     
     */
    public WebTabOperationTypeEnum getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     * @param value
     *     allowed object is
     *     {@link WebTabOperationTypeEnum }
     *     
     */
    public void setOperation(WebTabOperationTypeEnum value) {
        this.operation = value;
    }

}
