
package com.siebel.xml.siebelsearchcreateschema_input;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.siebel.xml.siebelsearchcreateschema_input package.
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

    private final static QName _CreateSchemaInputHeader_QNAME =
        new QName("http://www.siebel.com/xml/SiebelSearchCreateSchema_Input", "CreateSchema_InputHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.siebel.xml.siebelsearchcreateschema_input
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateSchemaInputHeader }
     *
     */
    public CreateSchemaInputHeader createCreateSchemaInputHeader() {
        return new CreateSchemaInputHeader();
    }

    /**
     * Create an instance of {@link CreateSchemaInputHeaderTopElmt }
     *
     */
    public CreateSchemaInputHeaderTopElmt createCreateSchemaInputHeaderTopElmt() {
        return new CreateSchemaInputHeaderTopElmt();
    }

    /**
     * Create an instance of {@link CreateSchemaFieldDef }
     *
     */
    public CreateSchemaFieldDef createCreateSchemaFieldDef() {
        return new CreateSchemaFieldDef();
    }

    /**
     * Create an instance of {@link CreateSchemaCustomProp }
     *
     */
    public CreateSchemaCustomProp createCreateSchemaCustomProp() {
        return new CreateSchemaCustomProp();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSchemaInputHeader }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.siebel.com/xml/SiebelSearchCreateSchema_Input",
                    name = "CreateSchema_InputHeader")
    public JAXBElement<CreateSchemaInputHeader> createCreateSchemaInputHeader(CreateSchemaInputHeader value) {
        return new JAXBElement<CreateSchemaInputHeader>(_CreateSchemaInputHeader_QNAME, CreateSchemaInputHeader.class,
                                                        null, value);
    }

}
