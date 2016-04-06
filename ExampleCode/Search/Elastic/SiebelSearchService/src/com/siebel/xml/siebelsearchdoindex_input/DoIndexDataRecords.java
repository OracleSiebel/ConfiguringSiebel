
package com.siebel.xml.siebelsearchdoindex_input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DoIndex_DataRecords complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DoIndex_DataRecords">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ListOfDoIndex_FieldAndValues" type="{http://www.siebel.com/xml/SiebelSearchDoIndex_Input}ListOfDoIndex_FieldAndValues" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoIndex_DataRecords", propOrder = { "rowID", "listOfDoIndexFieldAndValues" })
public class DoIndexDataRecords {

    @XmlElement(name = "RowID")
    protected String rowID;
    @XmlElement(name = "ListOfDoIndex_FieldAndValues")
    protected ListOfDoIndexFieldAndValues listOfDoIndexFieldAndValues;

    /**
     * Gets the value of the rowID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRowID() {
        return rowID;
    }

    /**
     * Sets the value of the rowID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRowID(String value) {
        this.rowID = value;
    }

    /**
     * Gets the value of the listOfDoIndexFieldAndValues property.
     *
     * @return
     *     possible object is
     *     {@link ListOfDoIndexFieldAndValues }
     *
     */
    public ListOfDoIndexFieldAndValues getListOfDoIndexFieldAndValues() {
        return listOfDoIndexFieldAndValues;
    }

    /**
     * Sets the value of the listOfDoIndexFieldAndValues property.
     *
     * @param value
     *     allowed object is
     *     {@link ListOfDoIndexFieldAndValues }
     *
     */
    public void setListOfDoIndexFieldAndValues(ListOfDoIndexFieldAndValues value) {
        this.listOfDoIndexFieldAndValues = value;
    }

}
