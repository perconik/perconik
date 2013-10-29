
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OneNoteViewTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OneNoteViewTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Normal"/>
 *     &lt;enumeration value="FullPage"/>
 *     &lt;enumeration value="Docked"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OneNoteViewTypeEnum")
@XmlEnum
public enum OneNoteViewTypeEnum {

    @XmlEnumValue("Normal")
    NORMAL("Normal"),
    @XmlEnumValue("FullPage")
    FULL_PAGE("FullPage"),
    @XmlEnumValue("Docked")
    DOCKED("Docked");
    private final String value;

    OneNoteViewTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OneNoteViewTypeEnum fromValue(String v) {
        for (OneNoteViewTypeEnum c: OneNoteViewTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
