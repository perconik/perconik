
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EntityType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EntityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="File"/>
 *     &lt;enumeration value="Namespace"/>
 *     &lt;enumeration value="Class"/>
 *     &lt;enumeration value="Structure"/>
 *     &lt;enumeration value="Enum"/>
 *     &lt;enumeration value="Interface"/>
 *     &lt;enumeration value="Constructor"/>
 *     &lt;enumeration value="Destructor"/>
 *     &lt;enumeration value="Method"/>
 *     &lt;enumeration value="Property"/>
 *     &lt;enumeration value="Indexer"/>
 *     &lt;enumeration value="Annotation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EntityType")
@XmlEnum
public enum EntityType {

    @XmlEnumValue("File")
    FILE("File"),
    @XmlEnumValue("Namespace")
    NAMESPACE("Namespace"),
    @XmlEnumValue("Class")
    CLASS("Class"),
    @XmlEnumValue("Structure")
    STRUCTURE("Structure"),
    @XmlEnumValue("Enum")
    ENUM("Enum"),
    @XmlEnumValue("Interface")
    INTERFACE("Interface"),
    @XmlEnumValue("Constructor")
    CONSTRUCTOR("Constructor"),
    @XmlEnumValue("Destructor")
    DESTRUCTOR("Destructor"),
    @XmlEnumValue("Method")
    METHOD("Method"),
    @XmlEnumValue("Property")
    PROPERTY("Property"),
    @XmlEnumValue("Indexer")
    INDEXER("Indexer"),
    @XmlEnumValue("Annotation")
    ANNOTATION("Annotation");
    private final String value;

    EntityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EntityType fromValue(String v) {
        for (EntityType c: EntityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
