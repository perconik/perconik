
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ApplicationStateDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApplicationStateDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ApplicationRun" type="{http://www.gratex.com/PerConIk/IActivitySvc}ApplicationRunDto" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="IsResponding" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="WindowState" use="required" type="{http://www.gratex.com/PerConIk/IActivitySvc}WindowStateTypeEnum" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApplicationStateDto", propOrder = {
    "applicationRun"
})
public class ApplicationStateDto {

    @XmlElement(name = "ApplicationRun")
    protected ApplicationRunDto applicationRun;
    @XmlAttribute(name = "IsResponding", required = true)
    protected boolean isResponding;
    @XmlAttribute(name = "WindowState", required = true)
    protected WindowStateTypeEnum windowState;

    /**
     * Gets the value of the applicationRun property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationRunDto }
     *     
     */
    public ApplicationRunDto getApplicationRun() {
        return applicationRun;
    }

    /**
     * Sets the value of the applicationRun property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationRunDto }
     *     
     */
    public void setApplicationRun(ApplicationRunDto value) {
        this.applicationRun = value;
    }

    /**
     * Gets the value of the isResponding property.
     * 
     */
    public boolean isIsResponding() {
        return isResponding;
    }

    /**
     * Sets the value of the isResponding property.
     * 
     */
    public void setIsResponding(boolean value) {
        this.isResponding = value;
    }

    /**
     * Gets the value of the windowState property.
     * 
     * @return
     *     possible object is
     *     {@link WindowStateTypeEnum }
     *     
     */
    public WindowStateTypeEnum getWindowState() {
        return windowState;
    }

    /**
     * Sets the value of the windowState property.
     * 
     * @param value
     *     allowed object is
     *     {@link WindowStateTypeEnum }
     *     
     */
    public void setWindowState(WindowStateTypeEnum value) {
        this.windowState = value;
    }

}
