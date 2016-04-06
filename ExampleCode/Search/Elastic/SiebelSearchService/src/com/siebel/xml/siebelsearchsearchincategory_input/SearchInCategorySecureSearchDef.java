
package com.siebel.xml.siebelsearchsearchincategory_input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchInCategory_SecureSearchDef complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SearchInCategory_SecureSearchDef">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CategoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchInCategory_VisibilityProps" type="{http://www.siebel.com/xml/SiebelSearchSearchInCategory_Input}SearchInCategory_VisibilityProps" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchInCategory_SecureSearchDef", propOrder = { "categoryName", "searchInCategoryVisibilityProps" })
public class SearchInCategorySecureSearchDef {

    @XmlElement(name = "CategoryName")
    protected String categoryName;
    @XmlElement(name = "SearchInCategory_VisibilityProps")
    protected List<SearchInCategoryVisibilityProps> searchInCategoryVisibilityProps;

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
     * Gets the value of the searchInCategoryVisibilityProps property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchInCategoryVisibilityProps property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchInCategoryVisibilityProps().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchInCategoryVisibilityProps }
     *
     *
     */
    public List<SearchInCategoryVisibilityProps> getSearchInCategoryVisibilityProps() {
        if (searchInCategoryVisibilityProps == null) {
            searchInCategoryVisibilityProps = new ArrayList<SearchInCategoryVisibilityProps>();
        }
        return this.searchInCategoryVisibilityProps;
    }

}
