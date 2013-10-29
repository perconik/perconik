
package com.gratex.perconik.services.activity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for XmlSerializableInterfaceCollection-Gratex.PerConIK.UserActivity.Svc.Interfaces.EventDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="XmlSerializableInterfaceCollection-Gratex.PerConIK.UserActivity.Svc.Interfaces.EventDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}OneNoteNavigateDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}OneNoteViewChangeDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}LyncStatusChangeDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}ApplicationFocusLostDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}WebNavigateDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}WebBookmarkDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}WebSaveDocumentDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}WebTabOperationDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}IdeCheckinDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}IdeDocumentOperationDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}IdeStateChangeDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}IdeCodeElementEventDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}IdeProjectOperationDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}IdeCodeOperationDto"/>
 *           &lt;element ref="{http://www.gratex.com/PerConIk/IActivitySvc}IdeFindOperationDto"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XmlSerializableInterfaceCollection-Gratex.PerConIK.UserActivity.Svc.Interfaces.EventDto", propOrder = {
    "oneNoteNavigateDtoOrOneNoteViewChangeDtoOrLyncStatusChangeDto"
})
public class XmlSerializableInterfaceCollectionGratexPerConIKUserActivitySvcInterfacesEventDto {

    @XmlElements({
        @XmlElement(name = "OneNoteNavigateDto", type = OneNoteNavigateDto.class, nillable = true),
        @XmlElement(name = "OneNoteViewChangeDto", type = OneNoteViewChangeDto.class, nillable = true),
        @XmlElement(name = "LyncStatusChangeDto", type = LyncStatusChangeDto.class, nillable = true),
        @XmlElement(name = "ApplicationFocusLostDto", type = ApplicationFocusLostDto.class, nillable = true),
        @XmlElement(name = "WebNavigateDto", type = WebNavigateDto.class, nillable = true),
        @XmlElement(name = "WebBookmarkDto", type = WebBookmarkDto.class, nillable = true),
        @XmlElement(name = "WebSaveDocumentDto", type = WebSaveDocumentDto.class, nillable = true),
        @XmlElement(name = "WebTabOperationDto", type = WebTabOperationDto.class, nillable = true),
        @XmlElement(name = "IdeCheckinDto", type = IdeCheckinDto.class, nillable = true),
        @XmlElement(name = "IdeDocumentOperationDto", type = IdeDocumentOperationDto.class, nillable = true),
        @XmlElement(name = "IdeStateChangeDto", type = IdeStateChangeDto.class, nillable = true),
        @XmlElement(name = "IdeCodeElementEventDto", type = IdeCodeElementEventDto.class, nillable = true),
        @XmlElement(name = "IdeProjectOperationDto", type = IdeProjectOperationDto.class, nillable = true),
        @XmlElement(name = "IdeCodeOperationDto", type = IdeCodeOperationDto.class, nillable = true),
        @XmlElement(name = "IdeFindOperationDto", type = IdeFindOperationDto.class, nillable = true)
    })
    protected List<EventDto> oneNoteNavigateDtoOrOneNoteViewChangeDtoOrLyncStatusChangeDto;

    /**
     * Gets the value of the oneNoteNavigateDtoOrOneNoteViewChangeDtoOrLyncStatusChangeDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the oneNoteNavigateDtoOrOneNoteViewChangeDtoOrLyncStatusChangeDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOneNoteNavigateDtoOrOneNoteViewChangeDtoOrLyncStatusChangeDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OneNoteNavigateDto }
     * {@link OneNoteViewChangeDto }
     * {@link LyncStatusChangeDto }
     * {@link ApplicationFocusLostDto }
     * {@link WebNavigateDto }
     * {@link WebBookmarkDto }
     * {@link WebSaveDocumentDto }
     * {@link WebTabOperationDto }
     * {@link IdeCheckinDto }
     * {@link IdeDocumentOperationDto }
     * {@link IdeStateChangeDto }
     * {@link IdeCodeElementEventDto }
     * {@link IdeProjectOperationDto }
     * {@link IdeCodeOperationDto }
     * {@link IdeFindOperationDto }
     * 
     * 
     */
    public List<EventDto> getOneNoteNavigateDtoOrOneNoteViewChangeDtoOrLyncStatusChangeDto() {
        if (oneNoteNavigateDtoOrOneNoteViewChangeDtoOrLyncStatusChangeDto == null) {
            oneNoteNavigateDtoOrOneNoteViewChangeDtoOrLyncStatusChangeDto = new ArrayList<EventDto>();
        }
        return this.oneNoteNavigateDtoOrOneNoteViewChangeDtoOrLyncStatusChangeDto;
    }

}
