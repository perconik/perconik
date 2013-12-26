
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChangeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChangeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unchanged"/>
 *     &lt;enumeration value="Add"/>
 *     &lt;enumeration value="Edit"/>
 *     &lt;enumeration value="Remove"/>
 *     &lt;enumeration value="FileSystemOnly"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChangeType")
@XmlEnum
public enum ChangeType {

    @XmlEnumValue("Unchanged")
    UNCHANGED("Unchanged"),
    @XmlEnumValue("Add")
    ADD("Add"),
    @XmlEnumValue("Edit")
    EDIT("Edit"),
    @XmlEnumValue("Remove")
    REMOVE("Remove"),
    @XmlEnumValue("FileSystemOnly")
    FILE_SYSTEM_ONLY("FileSystemOnly");
    private final String value;

    ChangeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ChangeType fromValue(String v) {
        for (ChangeType c: ChangeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
