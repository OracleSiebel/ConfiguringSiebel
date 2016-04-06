
package com.siebel.xml.siebelsearchdoindex_input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListOfDoIndex_InputTopElmt complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ListOfDoIndex_InputTopElmt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ListOfDoIndex_Input" type="{http://www.siebel.com/xml/SiebelSearchDoIndex_Input}ListOfDoIndex_Input"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOfDoIndex_InputTopElmt", propOrder = { "listOfDoIndexInput" })
public class ListOfDoIndexInputTopElmt {

    @XmlElement(name = "ListOfDoIndex_Input", required = true)
    protected ListOfDoIndexInput listOfDoIndexInput;

    /**
     * Gets the value of the listOfDoIndexInput property.
     *
     * @return
     *     possible object is
     *     {@link ListOfDoIndexInput }
     *
     */
    public ListOfDoIndexInput getListOfDoIndexInput() {
        return listOfDoIndexInput;
    }

    /**
     * Sets the value of the listOfDoIndexInput property.
     *
     * @param value
     *     allowed object is
     *     {@link ListOfDoIndexInput }
     *
     */
    public void setListOfDoIndexInput(ListOfDoIndexInput value) {
        this.listOfDoIndexInput = value;
    }

}
