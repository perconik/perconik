
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LyncStatusTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LyncStatusTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Passive"/>
 *     &lt;enumeration value="Busy"/>
 *     &lt;enumeration value="Offline"/>
 *     &lt;enumeration value="None"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LyncStatusTypeEnum")
@XmlEnum
public enum LyncStatusTypeEnum {

    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Passive")
    PASSIVE("Passive"),
    @XmlEnumValue("Busy")
    BUSY("Busy"),
    @XmlEnumValue("Offline")
    OFFLINE("Offline"),
    @XmlEnumValue("None")
    NONE("None");
    private final String value;

    LyncStatusTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LyncStatusTypeEnum fromValue(String v) {
        for (LyncStatusTypeEnum c: LyncStatusTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
