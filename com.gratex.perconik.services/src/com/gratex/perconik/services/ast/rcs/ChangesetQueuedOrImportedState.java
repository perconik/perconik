
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChangesetQueuedOrImportedState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChangesetQueuedOrImportedState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NotQueuedOrImported"/>
 *     &lt;enumeration value="QueuedOrImported"/>
 *     &lt;enumeration value="QueuedOrImportedForBranch"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChangesetQueuedOrImportedState")
@XmlEnum
public enum ChangesetQueuedOrImportedState {

    @XmlEnumValue("NotQueuedOrImported")
    NOT_QUEUED_OR_IMPORTED("NotQueuedOrImported"),
    @XmlEnumValue("QueuedOrImported")
    QUEUED_OR_IMPORTED("QueuedOrImported"),
    @XmlEnumValue("QueuedOrImportedForBranch")
    QUEUED_OR_IMPORTED_FOR_BRANCH("QueuedOrImportedForBranch");
    private final String value;

    ChangesetQueuedOrImportedState(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ChangesetQueuedOrImportedState fromValue(String v) {
        for (ChangesetQueuedOrImportedState c: ChangesetQueuedOrImportedState.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
