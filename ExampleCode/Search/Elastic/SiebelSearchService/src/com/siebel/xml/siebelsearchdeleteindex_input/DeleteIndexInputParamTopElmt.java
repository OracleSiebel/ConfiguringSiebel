
package com.siebel.xml.siebelsearchdeleteindex_input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeleteIndex_InputParamTopElmt complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DeleteIndex_InputParamTopElmt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeleteIndex_InputParam" type="{http://www.siebel.com/xml/SiebelSearchDeleteIndex_Input}DeleteIndex_InputParam"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteIndex_InputParamTopElmt", propOrder = { "deleteIndexInputParam" })
public class DeleteIndexInputParamTopElmt {

    @XmlElement(name = "DeleteIndex_InputParam", required = true)
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
