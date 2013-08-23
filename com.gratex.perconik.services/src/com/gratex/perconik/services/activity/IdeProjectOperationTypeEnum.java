
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdeProjectOperationTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IdeProjectOperationTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SwitchTo"/>
 *     &lt;enumeration value="Add"/>
 *     &lt;enumeration value="Remove"/>
 *     &lt;enumeration value="Rename"/>
 *     &lt;enumeration value="Open"/>
 *     &lt;enumeration value="Close"/>
 *     &lt;enumeration value="Refresh"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IdeProjectOperationTypeEnum")
@XmlEnum
public enum IdeProjectOperationTypeEnum {

    @XmlEnumValue("SwitchTo")
    SWITCH_TO("SwitchTo"),
    @XmlEnumValue("Add")
    ADD("Add"),
    @XmlEnumValue("Remove")
    REMOVE("Remove"),
    @XmlEnumValue("Rename")
    RENAME("Rename"),
    @XmlEnumValue("Open")
    OPEN("Open"),
    @XmlEnumValue("Close")
    CLOSE("Close"),
    @XmlEnumValue("Refresh")
    REFRESH("Refresh");
    private final String value;

    IdeProjectOperationTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdeProjectOperationTypeEnum fromValue(String v) {
        for (IdeProjectOperationTypeEnum c: IdeProjectOperationTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
