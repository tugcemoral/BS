package tr.ege.xmlschema;

import static xmlschema.XMLSchemaConstants.COMPLEX_TYPE;
import static xmlschema.XMLSchemaConstants.ELEMENT;
import static xmlschema.XMLSchemaConstants.NAME;
import static xmlschema.XMLSchemaConstants.SEQUENCE;
import static xmlschema.XMLSchemaConstants.TYPE;
import static xmlschema.XMLSchemaConstants.XSD_PREFIX;
import junit.framework.TestCase;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xmlschema.MusteriSchemaManager;
import xmlschema.POSchemaManager;
import domain.Musteri;

public class MusteriXMLSChemaTest extends TestCase {
	
	public void testCreateMusteriSchemaModel() throws Exception {
		//create an instance of Musteri schema model
		POSchemaManager musteriSchemaManager = MusteriSchemaManager.getInstance();
		//create a musteri model...
	 	Document musteriModel = musteriSchemaManager.getSchemaModel();
		
	 	//asserting musteri model isnot null
		assertNotNull(musteriModel);
		
		//get the schema element..
		Element schemaElement = musteriModel.getDocumentElement();
		//assert that schema element is not null
		assertNotNull(schemaElement);
		
		//get the complex type..
		Element musteriComplexType = (Element) musteriModel.getElementsByTagName(XSD_PREFIX + ":" + COMPLEX_TYPE).item(0);
		//assert personel complex type element is not null...
		assertNotNull(musteriComplexType);
		
		//get sequence element..
		Element sequenceElement = (Element) musteriModel.getElementsByTagName(XSD_PREFIX + ":" + SEQUENCE).item(0);
		//assert sequence element is not null...
		assertNotNull(sequenceElement);
		
		//get adSoyad element from sequence element...
		Element firstElementOfSequence = (Element) sequenceElement.getFirstChild();
		//assert name and the type are equal..
		assertEquals("adSoyad",firstElementOfSequence.getAttribute(NAME));
		assertEquals("xsd:" + "string", firstElementOfSequence.getAttribute(TYPE));
		
		//assert number of the elements are equal to 4.
		assertEquals(4, sequenceElement.getElementsByTagName("xsd:" + ELEMENT).getLength());
	}
	
	public void testWriteSchemaModel() throws Exception {
		// create musteri schema builder.
		MusteriSchemaManager musteriSchemaManager = MusteriSchemaManager.getInstance();
		// write musteri schema model to screen;
		musteriSchemaManager.writeSchemaModel(System.out);
	}
	
	public void testWriteInstanceTo() throws Exception {
		// create musteri schema builder.
		POSchemaManager musteriSchemaManager = MusteriSchemaManager.getInstance();
		// create an example musteri...
		Musteri musteri = createExampleMusteri();
		// create xml instance of example musteri...
		Document dvdInstance = musteriSchemaManager.createInstanceXML(musteri);

		// write dvd schema model to screen;
		musteriSchemaManager.writeInstanceTo(dvdInstance, System.out);
	}
	
	public void testCreateInstance() throws Exception {
		// create musteri schema manager...
		POSchemaManager musteriSchemaManager = MusteriSchemaManager.getInstance();

		// create an example musteri...
		Musteri musteri= createExampleMusteri();
		// create xml instance of example musteri...
		Document musteriInstance = musteriSchemaManager.createInstanceXML(musteri);
		// assert that musteriInstance is not null...
		assertNotNull(musteriInstance);

		// get Musteri element...
		Element musteriElement = musteriInstance.getDocumentElement();
		// get adSoyad element from Musteri element...
		Element adSoyadElement = (Element) musteriElement.getElementsByTagName(
				"adSoyad").item(0);
		// check "adSoyad"...
		assertEquals("adim soyadim", adSoyadElement.getFirstChild()
				.getNodeValue());

		// get kullaniciAdi element from Musteri element... 
		Element kullaniciElement = (Element) musteriElement.getElementsByTagName(
				"kullaniciAdi").item(0);
		// check "kullaniciAdi"...
		assertEquals("kullaniciAdim", kullaniciElement.getFirstChild().getNodeValue());
		
		//check the number of elements of musteri...
		NodeList nodeList = musteriElement.getChildNodes();
		assertEquals(4, nodeList.getLength());
		
	}

	private Musteri createExampleMusteri() {
		Musteri musteri = new Musteri("adim soyadim", "kullaniciAdim", "sifrem", "255144");
		return musteri;
	}
}
