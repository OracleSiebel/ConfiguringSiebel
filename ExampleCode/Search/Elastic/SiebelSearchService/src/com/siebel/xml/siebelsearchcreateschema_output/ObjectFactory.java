
package com.siebel.xml.siebelsearchcreateschema_output;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.siebel.xml.siebelsearchcreateschema_output package.
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

    private final static QName _CreateSchemaStatus_QNAME =
        new QName("http://www.siebel.com/xml/SiebelSearchCreateSchema_Output", "CreateSchema_Status");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.siebel.xml.siebelsearchcreateschema_output
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateSchemaStatus }
     *
     */
    public CreateSchemaStatus createCreateSchemaStatus() {
        return new CreateSchemaStatus();
    }

    /**
     * Create an instance of {@link CreateSchemaStatusTopElmt }
     *
     */
    public CreateSchemaStatusTopElmt createCreateSchemaStatusTopElmt() {
        return new CreateSchemaStatusTopElmt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSchemaStatus }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.siebel.com/xml/SiebelSearchCreateSchema_Output",
                    name = "CreateSchema_Status")
    public JAXBElement<CreateSchemaStatus> createCreateSchemaStatus(CreateSchemaStatus value) {
        return new JAXBElement<CreateSchemaStatus>(_CreateSchemaStatus_QNAME, CreateSchemaStatus.class, null, value);
    }

}
