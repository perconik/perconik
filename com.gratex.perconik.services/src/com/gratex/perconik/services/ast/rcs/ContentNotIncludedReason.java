
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContentNotIncludedReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ContentNotIncludedReason">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="Deleted"/>
 *     &lt;enumeration value="Binary"/>
 *     &lt;enumeration value="Restricted"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ContentNotIncludedReason")
@XmlEnum
public enum ContentNotIncludedReason {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Deleted")
    DELETED("Deleted"),
    @XmlEnumValue("Binary")
    BINARY("Binary"),
    @XmlEnumValue("Restricted")
    RESTRICTED("Restricted");
    private final String value;

    ContentNotIncludedReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ContentNotIncludedReason fromValue(String v) {
        for (ContentNotIncludedReason c: ContentNotIncludedReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
