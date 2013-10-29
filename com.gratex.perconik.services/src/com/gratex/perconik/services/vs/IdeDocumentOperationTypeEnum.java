
package com.gratex.perconik.services.vs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeDocumentOperationTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IdeDocumentOperationTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SwitchTo"/>
 *     &lt;enumeration value="Add"/>
 *     &lt;enumeration value="Open"/>
 *     &lt;enumeration value="Close"/>
 *     &lt;enumeration value="Remove"/>
 *     &lt;enumeration value="Save"/>
 *     &lt;enumeration value="Rename"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IdeDocumentOperationTypeEnum")
@XmlEnum
public enum IdeDocumentOperationTypeEnum {

    @XmlEnumValue("SwitchTo")
    SWITCH_TO("SwitchTo"),
    @XmlEnumValue("Add")
    ADD("Add"),
    @XmlEnumValue("Open")
    OPEN("Open"),
    @XmlEnumValue("Close")
    CLOSE("Close"),
    @XmlEnumValue("Remove")
    REMOVE("Remove"),
    @XmlEnumValue("Save")
    SAVE("Save"),
    @XmlEnumValue("Rename")
    RENAME("Rename");
    private final String value;

    IdeDocumentOperationTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdeDocumentOperationTypeEnum fromValue(String v) {
        for (IdeDocumentOperationTypeEnum c: IdeDocumentOperationTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
