package tr.ege.xmlschema;

import static xmlschema.XMLSchemaConstants.COMPLEX_TYPE;
import static xmlschema.XMLSchemaConstants.ELEMENT;
import static xmlschema.XMLSchemaConstants.NAME;
import static xmlschema.XMLSchemaConstants.SEQUENCE;
import static xmlschema.XMLSchemaConstants.TYPE;
import junit.framework.TestCase;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import domain.Personel;

import xmlschema.POSchemaManager;
import xmlschema.PersonelSchemaManager;

public class PersonelXMLSchemaTest extends TestCase {
	
	public void testCreatePersonelXmlSchema() {
		//create a personel schema builder...
		PersonelSchemaManager personelSchemaBuilder = PersonelSchemaManager.getInstance();
		//create a personel schema model...
		Document personelModel = personelSchemaBuilder.getSchemaModel();
		
		//assert that personel model is not null
		assertNotNull(personelModel);
		
		//get the schema element..
		Element schemaElement = personelModel.getDocumentElement();
		//assert that schema element is not null
		assertNotNull(schemaElement);
		
		//get the complex type..
		Element personelComplexType = (Element) personelModel.getElementsByTagName("xsd:" + COMPLEX_TYPE).item(0);
		//asserting personel complex type element is not null...
		assertNotNull(personelComplexType);
		
		//get sequence element..
		Element sequenceElement = (Element) personelModel.getElementsByTagName("xsd:" + SEQUENCE).item(0);
		//asserting..
		assertNotNull(sequenceElement);
		
		//get adSoyad element from sequence element...
		Element firstElementOfSequence = (Element) sequenceElement.getFirstChild();
		//asserting that name and the type are equal..
		assertEquals("adSoyad",firstElementOfSequence.getAttribute(NAME));
		assertEquals("xsd:" + "string", firstElementOfSequence.getAttribute(TYPE));
		//assert that number of the elements are equal to 3.
		assertEquals(3, sequenceElement.getElementsByTagName("xsd:" + ELEMENT).getLength());
		
	}
	
	public void testWriteSchemaModel() throws Exception {
		// create personel schema builder.
		PersonelSchemaManager personelSchemaBuilder = PersonelSchemaManager.getInstance();
		// write personel schema model to screen;
		personelSchemaBuilder.writeSchemaModel(System.out);
	}
	
	public void testWriteInstanceTo() throws Exception {
		System.out.println("");
		// create personel schema manager...
		POSchemaManager personelSchemaManager = PersonelSchemaManager.getInstance();
		// create an example dvd...
		Personel personel = createExamplePersonel();
		// create xml instance of example dvd...
		Document personelInstance = personelSchemaManager.createInstanceXML(personel);
	
		// write dvd schema model to screen;
		personelSchemaManager.writeInstanceTo(personelInstance, System.out);
	}

	public void testCreateInstance() throws Exception {
		// create personel schema builder.
		POSchemaManager personelSchemaManager = PersonelSchemaManager.getInstance();
	
		// create an example personel...
		Personel personel= createExamplePersonel();
		// create xml instance of example personel...
		Document personelInstance = personelSchemaManager.createInstanceXML(personel);
		// assert that personelInstance is not null...
		assertNotNull(personelInstance);
	
		// get Personel element...
		Element personelElement = personelInstance.getDocumentElement();
		// get kullanici adi element from Personel element...
		Element kullaniciAdiElement = (Element) personelElement.getElementsByTagName(
				"kullaniciAdi").item(0);
		// check "kullaniciAdi"...
		assertEquals("kullaniciAdi", kullaniciAdiElement.getFirstChild()
				.getNodeValue());
	
		// get parola element from Personel element...
		Element parolaElement = (Element) personelElement.getElementsByTagName(
				"parola").item(0);
		// check "parola"...
		assertEquals("sifre", parolaElement.getFirstChild().getNodeValue());
		
		//check the number of elements of personel...
		NodeList nodeList = personelElement.getChildNodes();
		assertEquals(3, nodeList.getLength());
		
	}

	private Personel createExamplePersonel() throws Exception{
		//create an example personel instance...
		Personel personel = new Personel("ad soyad", "kullaniciAdi", "sifre");
		return personel;
	}

}
