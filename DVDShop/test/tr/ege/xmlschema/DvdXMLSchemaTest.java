package tr.ege.xmlschema;

import static xmlschema.XMLSchemaConstants.COMPLEX_TYPE;
import static xmlschema.XMLSchemaConstants.ELEMENT;
import static xmlschema.XMLSchemaConstants.NAME;
import static xmlschema.XMLSchemaConstants.TYPE;
import junit.framework.TestCase;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xmlschema.DvdSchemaManager;
import xmlschema.POSchemaManager;
import domain.Dvd;

public class DvdXMLSchemaTest extends TestCase {

	public void testCreateDvdSchemaModel() throws Exception {
		// create a DVD Schema Builder...
		POSchemaManager dvdSchemaManager = DvdSchemaManager.getInstance();
		// create DVD XML Schema model...
		Document dvdModel = dvdSchemaManager.getSchemaModel();
		// assert that dvdModel is not null...
		assertNotNull(dvdModel);
	
		// get schema element...
		Element schemaElement = dvdModel.getDocumentElement();
		// assert that schema element is not null...
		assertNotNull(schemaElement);
	
		// get complex type element...
		Element complexTypeElement = (Element) schemaElement
				.getElementsByTagName("xsd:" + COMPLEX_TYPE).item(0);
		// assert that there is a complex type element in schema...
		assertNotNull(complexTypeElement);
		// check the name of complex type...
		assertEquals("Dvd", complexTypeElement.getAttribute(NAME));
	
		// get sequence element...
		Element sequenceElement = (Element) complexTypeElement.getFirstChild();
		// assert that there is a sequence element in complex type...
		assertNotNull(sequenceElement);
	
		NodeList elementList = sequenceElement.getElementsByTagName("xsd:"
				+ ELEMENT);
		// check size...
		assertEquals(6, elementList.getLength());
	
		// get yonetmen element...
		Element yonetmenElement = (Element) sequenceElement.getFirstChild();
		// check the name of element....
		assertEquals("id", yonetmenElement.getAttribute(NAME));
		// check type...
		assertEquals("xsd:string", yonetmenElement.getAttribute(TYPE));
	}

	public void testWriteInstanceTo() throws Exception {
		// create dvd schema builder.
		POSchemaManager dvdSchemaManager = DvdSchemaManager.getInstance();
		// create an example dvd...
		Dvd dvd = createExampleDvd();
		// create xml instance of example dvd...
		Document dvdInstance = dvdSchemaManager.createInstanceXML(dvd);
	
		// write dvd schema model to screen;
		dvdSchemaManager.writeInstanceTo(dvdInstance, System.out);
	}

	public void testWriteSchemaModel() throws Exception {
		// create dvd schema builder.
		POSchemaManager dvdSchemaBuilder = DvdSchemaManager.getInstance();
		// write dvd schema model to screen;
		dvdSchemaBuilder.writeSchemaModel(System.out);
		
		System.out.println("");
	}

	public void testCreateInstance() throws Exception {
		// create dvd schema builder.
		POSchemaManager dvdSchemaManager = DvdSchemaManager.getInstance();
	
		// create an example dvd...
		Dvd dvd = createExampleDvd();
		// create xml instance of example dvd...
		Document dvdInstance = dvdSchemaManager.createInstanceXML(dvd);
		// assert that dvdInstance is not null...
		assertNotNull(dvdInstance);
	
		// get Dvd element...
		Element dvdElement = dvdInstance.getDocumentElement();
		// get urunAdi element from Dvd element...
		Element urunAdiElement = (Element) dvdElement.getElementsByTagName(
				"urunAdi").item(0);
		// check "urunAdi"...
		assertEquals("300 Ispartalı", urunAdiElement.getFirstChild()
				.getNodeValue());
	
		// get fiyat element from Dvd element...
		Element fiyatElement = (Element) dvdElement.getElementsByTagName(
				"fiyat").item(0);
		// check "fiyat"...
		assertEquals("30", fiyatElement.getFirstChild().getNodeValue());
		
		//check the number of elements of dvd...
		NodeList nodeList = dvdElement.getChildNodes();
		assertEquals(6, nodeList.getLength());
		
	}

	private Dvd createExampleDvd() {
		Dvd dvd = new Dvd("dvd124", "300 Ispartalı", 30, 5, "Onder Gurcan",
				"Gurcan Production");
		return dvd;
	}
}
