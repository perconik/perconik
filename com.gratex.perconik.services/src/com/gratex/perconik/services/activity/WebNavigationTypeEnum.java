
package com.gratex.perconik.services.activity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WebNavigationTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WebNavigationTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Link"/>
 *     &lt;enumeration value="Bookmark"/>
 *     &lt;enumeration value="UrlBar"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WebNavigationTypeEnum")
@XmlEnum
public enum WebNavigationTypeEnum {

    @XmlEnumValue("Link")
    LINK("Link"),
    @XmlEnumValue("Bookmark")
    BOOKMARK("Bookmark"),
    @XmlEnumValue("UrlBar")
    URL_BAR("UrlBar");
    private final String value;

    WebNavigationTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WebNavigationTypeEnum fromValue(String v) {
        for (WebNavigationTypeEnum c: WebNavigationTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
