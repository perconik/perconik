
package com.gratex.perconik.iactivitysvc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeSlnPrjEventDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdeSlnPrjEventDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gratex.com/PerConIk/IActivitySvc}IdeEventDto">
 *       &lt;attribute name="SolutionName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ProjectName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdeSlnPrjEventDto")
@XmlSeeAlso({
    IdeStateChangeDto.class,
    IdeProjectOperationDto.class,
    IdeDocumentOperationDto.class,
    IdeCodeOperationDto.class,
    IdeFindOperationDto.class
})
public abstract class IdeSlnPrjEventDto
    extends IdeEventDto
{

    @XmlAttribute(name = "SolutionName")
    protected String solutionName;
    @XmlAttribute(name = "ProjectName")
    protected String projectName;

    /**
     * Gets the value of the solutionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolutionName() {
        return solutionName;
    }

    /**
     * Sets the value of the solutionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolutionName(String value) {
        this.solutionName = value;
    }

    /**
     * Gets the value of the projectName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the value of the projectName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectName(String value) {
        this.projectName = value;
    }

}
