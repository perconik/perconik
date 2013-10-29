
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HwUsageDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HwUsageDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}StateDto">
 *       &lt;attribute name="AvgCpuUsage" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="PeakCpuUsage" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="AvgMemUsage" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *       &lt;attribute name="PeakMemUsage" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HwUsageDto")
public class HwUsageDto
    extends StateDto
{

    @XmlAttribute(name = "AvgCpuUsage", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short avgCpuUsage;
    @XmlAttribute(name = "PeakCpuUsage", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short peakCpuUsage;
    @XmlAttribute(name = "AvgMemUsage", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short avgMemUsage;
    @XmlAttribute(name = "PeakMemUsage", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short peakMemUsage;

    /**
     * Gets the value of the avgCpuUsage property.
     * 
     */
    public short getAvgCpuUsage() {
        return avgCpuUsage;
    }

    /**
     * Sets the value of the avgCpuUsage property.
     * 
     */
    public void setAvgCpuUsage(short value) {
        this.avgCpuUsage = value;
    }

    /**
     * Gets the value of the peakCpuUsage property.
     * 
     */
    public short getPeakCpuUsage() {
        return peakCpuUsage;
    }

    /**
     * Sets the value of the peakCpuUsage property.
     * 
     */
    public void setPeakCpuUsage(short value) {
        this.peakCpuUsage = value;
    }

    /**
     * Gets the value of the avgMemUsage property.
     * 
     */
    public short getAvgMemUsage() {
        return avgMemUsage;
    }

    /**
     * Sets the value of the avgMemUsage property.
     * 
     */
    public void setAvgMemUsage(short value) {
        this.avgMemUsage = value;
    }

    /**
     * Gets the value of the peakMemUsage property.
     * 
     */
    public short getPeakMemUsage() {
        return peakMemUsage;
    }

    /**
     * Sets the value of the peakMemUsage property.
     * 
     */
    public void setPeakMemUsage(short value) {
        this.peakMemUsage = value;
    }

}
