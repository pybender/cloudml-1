
package net.flexiant.extility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for modifyDisk complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modifyDisk">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updatedResource" type="{http://extility.flexiant.net}disk" minOccurs="0"/>
 *         &lt;element name="when" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyDisk", propOrder = {
    "updatedResource",
    "when"
})
public class ModifyDisk {

    protected Disk updatedResource;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar when;

    /**
     * Gets the value of the updatedResource property.
     * 
     * @return
     *     possible object is
     *     {@link Disk }
     *     
     */
    public Disk getUpdatedResource() {
        return updatedResource;
    }

    /**
     * Sets the value of the updatedResource property.
     * 
     * @param value
     *     allowed object is
     *     {@link Disk }
     *     
     */
    public void setUpdatedResource(Disk value) {
        this.updatedResource = value;
    }

    /**
     * Gets the value of the when property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWhen() {
        return when;
    }

    /**
     * Sets the value of the when property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWhen(XMLGregorianCalendar value) {
        this.when = value;
    }

}