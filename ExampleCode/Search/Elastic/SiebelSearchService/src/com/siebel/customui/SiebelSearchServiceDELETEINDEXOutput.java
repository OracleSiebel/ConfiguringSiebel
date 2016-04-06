
package com.siebel.customui;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.siebel.xml.siebelsearchdeleteindex_output.DeleteIndexStatus;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.siebel.com/xml/SiebelSearchDeleteIndex_Output}DeleteIndex_Status"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "deleteIndexStatus" })
@XmlRootElement(name = "SiebelSearchService_DELETEINDEX_Output")
public class SiebelSearchServiceDELETEINDEXOutput {

    @XmlElement(name = "DeleteIndex_Status", namespace = "http://www.siebel.com/xml/SiebelSearchDeleteIndex_Output",
                required = true)
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
