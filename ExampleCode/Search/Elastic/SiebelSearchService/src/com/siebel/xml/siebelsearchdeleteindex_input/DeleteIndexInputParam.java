
package com.siebel.xml.siebelsearchdeleteindex_input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeleteIndex_InputParam complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DeleteIndex_InputParam">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BCName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CategoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeleteIndex_CustomProps" type="{http://www.siebel.com/xml/SiebelSearchDeleteIndex_Input}DeleteIndex_CustomProps" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteIndex_InputParam", propOrder = { "bcName", "categoryName", "deleteIndexCustomProps" })
public class DeleteIndexInputParam {

    @XmlElement(name = "BCName", required = true)
    protected String bcName;
    @XmlElement(name = "CategoryName")
    protected String categoryName;
    @XmlElement(name = "DeleteIndex_CustomProps")
    protected List<DeleteIndexCustomProps> deleteIndexCustomProps;

    /**
     * Gets the value of the bcName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBCName() {
        return bcName;
    }

    /**
     * Sets the value of the bcName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBCName(String value) {
        this.bcName = value;
    }

    /**
     * Gets the value of the categoryName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * Gets the value of the deleteIndexCustomProps property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deleteIndexCustomProps property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeleteIndexCustomProps().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeleteIndexCustomProps }
     *
     *
     */
    public List<DeleteIndexCustomProps> getDeleteIndexCustomProps() {
        if (deleteIndexCustomProps == null) {
            deleteIndexCustomProps = new ArrayList<DeleteIndexCustomProps>();
        }
        return this.deleteIndexCustomProps;
    }

}
