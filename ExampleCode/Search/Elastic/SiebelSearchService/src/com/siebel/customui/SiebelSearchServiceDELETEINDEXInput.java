
package com.siebel.customui;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.siebel.xml.siebelsearchdeleteindex_input.DeleteIndexInputParam;


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
 *         &lt;element ref="{http://www.siebel.com/xml/SiebelSearchDeleteIndex_Input}DeleteIndex_InputParam"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "deleteIndexInputParam" })
@XmlRootElement(name = "SiebelSearchService_DELETEINDEX_Input")
public class SiebelSearchServiceDELETEINDEXInput {

    @XmlElement(name = "DeleteIndex_InputParam", namespace = "http://www.siebel.com/xml/SiebelSearchDeleteIndex_Input",
                required = true)
    protected DeleteIndexInputParam deleteIndexInputParam;

    /**
     * Gets the value of the deleteIndexInputParam property.
     *
     * @return
     *     possible object is
     *     {@link DeleteIndexInputParam }
     *
     */
    public DeleteIndexInputParam getDeleteIndexInputParam() {
        return deleteIndexInputParam;
    }

    /**
     * Sets the value of the deleteIndexInputParam property.
     *
     * @param value
     *     allowed object is
     *     {@link DeleteIndexInputParam }
     *
     */
    public void setDeleteIndexInputParam(DeleteIndexInputParam value) {
        this.deleteIndexInputParam = value;
    }

}
