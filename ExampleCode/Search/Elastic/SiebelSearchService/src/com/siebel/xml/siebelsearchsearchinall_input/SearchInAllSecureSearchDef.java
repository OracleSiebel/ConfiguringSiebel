
package com.siebel.xml.siebelsearchsearchinall_input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchInAll_SecureSearchDef complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SearchInAll_SecureSearchDef">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CategoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchInAll_VisibilityProps" type="{http://www.siebel.com/xml/SiebelSearchSearchInAll_Input}SearchInAll_VisibilityProps" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchInAll_SecureSearchDef", propOrder = { "categoryName", "searchInAllVisibilityProps" })
public class SearchInAllSecureSearchDef {

    @XmlElement(name = "CategoryName")
    protected String categoryName;
    @XmlElement(name = "SearchInAll_VisibilityProps")
    protected List<SearchInAllVisibilityProps> searchInAllVisibilityProps;

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
     * Gets the value of the searchInAllVisibilityProps property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchInAllVisibilityProps property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchInAllVisibilityProps().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchInAllVisibilityProps }
     *
     *
     */
    public List<SearchInAllVisibilityProps> getSearchInAllVisibilityProps() {
        if (searchInAllVisibilityProps == null) {
            searchInAllVisibilityProps = new ArrayList<SearchInAllVisibilityProps>();
        }
        return this.searchInAllVisibilityProps;
    }

}
