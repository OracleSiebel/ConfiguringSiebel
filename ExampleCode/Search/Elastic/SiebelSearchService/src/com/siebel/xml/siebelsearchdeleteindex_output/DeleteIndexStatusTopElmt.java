
package com.siebel.xml.siebelsearchdeleteindex_output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeleteIndex_StatusTopElmt complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DeleteIndex_StatusTopElmt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeleteIndex_Status" type="{http://www.siebel.com/xml/SiebelSearchDeleteIndex_Output}DeleteIndex_Status"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteIndex_StatusTopElmt", propOrder = { "deleteIndexStatus" })
public class DeleteIndexStatusTopElmt {

    @XmlElement(name = "DeleteIndex_Status", required = true)
    protected DeleteIndexStatus deleteIndexStatus;

    /**
     * Gets the value of the deleteIndexStatus property.
     *
     * @return
     *     possible object is
     *     {@link DeleteIndexStatus }
     *
     */
    public DeleteIndexStatus getDeleteIndexStatus() {
        return deleteIndexStatus;
    }

    /**
     * Sets the value of the deleteIndexStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link DeleteIndexStatus }
     *
     */
    public void setDeleteIndexStatus(DeleteIndexStatus value) {
        this.deleteIndexStatus = value;
    }

}
