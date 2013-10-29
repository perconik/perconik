
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WebTabOperationTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WebTabOperationTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SwitchTo"/>
 *     &lt;enumeration value="Open"/>
 *     &lt;enumeration value="Close"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WebTabOperationTypeEnum")
@XmlEnum
public enum WebTabOperationTypeEnum {

    @XmlEnumValue("SwitchTo")
    SWITCH_TO("SwitchTo"),
    @XmlEnumValue("Open")
    OPEN("Open"),
    @XmlEnumValue("Close")
    CLOSE("Close");
    private final String value;

    WebTabOperationTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WebTabOperationTypeEnum fromValue(String v) {
        for (WebTabOperationTypeEnum c: WebTabOperationTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
