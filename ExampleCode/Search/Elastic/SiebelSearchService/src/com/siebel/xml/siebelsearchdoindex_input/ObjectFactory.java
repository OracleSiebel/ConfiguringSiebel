
package com.siebel.xml.siebelsearchdoindex_input;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.siebel.xml.siebelsearchdoindex_input package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListOfDoIndexInput_QNAME =
        new QName("http://www.siebel.com/xml/SiebelSearchDoIndex_Input", "ListOfDoIndex_Input");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.siebel.xml.siebelsearchdoindex_input
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListOfDoIndexInput }
     *
     */
    public ListOfDoIndexInput createListOfDoIndexInput() {
        return new ListOfDoIndexInput();
    }

    /**
     * Create an instance of {@link ListOfDoIndexFieldAndValues }
     *
     */
    public ListOfDoIndexFieldAndValues createListOfDoIndexFieldAndValues() {
        return new ListOfDoIndexFieldAndValues();
    }

    /**
     * Create an instance of {@link DoIndexCustomHeaderProps }
     *
     */
    public DoIndexCustomHeaderProps createDoIndexCustomHeaderProps() {
        return new DoIndexCustomHeaderProps();
    }

    /**
     * Create an instance of {@link DoIndexFieldAndValues }
     *
     */
    public DoIndexFieldAndValues createDoIndexFieldAndValues() {
        return new DoIndexFieldAndValues();
    }

    /**
     * Create an instance of {@link DoIndexDataRecords }
     *
     */
    public DoIndexDataRecords createDoIndexDataRecords() {
        return new DoIndexDataRecords();
    }

    /**
     * Create an instance of {@link ListOfDoIndexDataRecords }
     *
     */
    public ListOfDoIndexDataRecords createListOfDoIndexDataRecords() {
        return new ListOfDoIndexDataRecords();
    }

    /**
     * Create an instance of {@link DoIndexInputHeader }
     *
     */
    public DoIndexInputHeader createDoIndexInputHeader() {
        return new DoIndexInputHeader();
    }

    /**
     * Create an instance of {@link ListOfDoIndexInputTopElmt }
     *
     */
    public ListOfDoIndexInputTopElmt createListOfDoIndexInputTopElmt() {
        return new ListOfDoIndexInputTopElmt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListOfDoIndexInput }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.siebel.com/xml/SiebelSearchDoIndex_Input", name = "ListOfDoIndex_Input")
    public JAXBElement<ListOfDoIndexInput> createListOfDoIndexInput(ListOfDoIndexInput value) {
        return new JAXBElement<ListOfDoIndexInput>(_ListOfDoIndexInput_QNAME, ListOfDoIndexInput.class, null, value);
    }

}
