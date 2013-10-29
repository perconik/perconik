
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ApplicationRunDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApplicationRunDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Pid" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ApplicationName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RunId" use="required" type="{http://microsoft.com/wsdl/types/}guid" />
 *       &lt;attribute name="Hwnd" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApplicationRunDto")
public class ApplicationRunDto {

    @XmlAttribute(name = "Pid", required = true)
    protected int pid;
    @XmlAttribute(name = "ApplicationName")
    protected String applicationName;
    @XmlAttribute(name = "RunId", required = true)
    protected String runId;
    @XmlAttribute(name = "Hwnd", required = true)
    protected int hwnd;

    /**
     * Gets the value of the pid property.
     * 
     */
    public int getPid() {
        return pid;
    }

    /**
     * Sets the value of the pid property.
     * 
     */
    public void setPid(int value) {
        this.pid = value;
    }

    /**
     * Gets the value of the applicationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Sets the value of the applicationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationName(String value) {
        this.applicationName = value;
    }

    /**
     * Gets the value of the runId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRunId() {
        return runId;
    }

    /**
     * Sets the value of the runId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRunId(String value) {
        this.runId = value;
    }

    /**
     * Gets the value of the hwnd property.
     * 
     */
    public int getHwnd() {
        return hwnd;
    }

    /**
     * Sets the value of the hwnd property.
     * 
     */
    public void setHwnd(int value) {
        this.hwnd = value;
    }

}
