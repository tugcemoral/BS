package tr.ege.xmlschema;

import static xmlschema.XMLSchemaConstants.NAME;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.TestCase;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import domain.Dvd;
import domain.Musteri;
import domain.PO;
import domain.Personel;

import xmlschema.POSchemaManager;
import xmlschema.SchemaManagerFactory;

public class ReadInstanceTest extends TestCase {

	private static final String FILES_PERSONEL_XML = "./files/Personel.xml";

	private static final String FILES_DVD_XML = "./files/Dvd.xml";

	private static final String FILES_MUSTERI_XML = "./files/Musteri.xml";

	private static final String FILES_XML = "./files/XML.xml";

	private DocumentBuilder documentBuilder;

	@Override
	protected void setUp() throws Exception {
		// create document builder from builder factory.
		documentBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		super.setUp();
	}

	public void testReadDvdInstance() throws Exception {
		// read DVD.xml to a document instance.
		Document dvdDocument = documentBuilder.parse(new File(FILES_DVD_XML));

		// get dvdDocument element's document element
		Element dvdDocumentElement = (Element) dvdDocument.getDocumentElement();
		assertEquals("Dvd", dvdDocumentElement.getNodeName());

		// get dvdDocumentElement element's barkod element and its value
		Element barkodElement = (Element) dvdDocumentElement
				.getElementsByTagName("id").item(0);
		assertEquals("dvd124", barkodElement.getFirstChild().getNodeValue());
		// get dvdDocumentElement element's urunAdi element and its value
		Element urunAdiElement = (Element) dvdDocumentElement
				.getElementsByTagName("urunAdi").item(0);
		assertEquals("300 Bandirmali", urunAdiElement.getFirstChild()
				.getNodeValue());
		// get dvdDocumentElement element's yonetmen element and its value
		Element yonetmenElement = (Element) dvdDocumentElement
				.getElementsByTagName("yonetmen").item(0);
		assertEquals("Ovunc Cetin", yonetmenElement.getFirstChild()
				.getNodeValue());
		// get dvdDocumentElement element's fiyat element and its value
		Element fiyatElement = (Element) dvdDocumentElement
				.getElementsByTagName("fiyat").item(0);
		assertEquals("30", fiyatElement.getFirstChild().getNodeValue());
		// get dvdDocumentElement element's sirket element and its value
		Element sirketElement = (Element) dvdDocumentElement
				.getElementsByTagName("sirket").item(0);
		assertEquals("Seagent Ltd.Sti.Corp.As.", sirketElement
				.getFirstChild().getNodeValue());
		// get dvdDocumentElement element's sirket element and its value
		Element adetElement = (Element) dvdDocumentElement
				.getElementsByTagName("adet").item(0);
		assertEquals("5", adetElement.getFirstChild().getNodeValue());

	}

	public void testReadPersonelInstance() throws Exception {
		// read Musteri.xml file to a document instance.
		Document personelDocument = documentBuilder.parse(new File(
				FILES_PERSONEL_XML));

		// get document element...
		Element personelDocumentElement = personelDocument.getDocumentElement();
		assertEquals("Personel", personelDocumentElement.getNodeName());
		// get personelDocumentElement element's adSoyad element and its value
		Element adSoyadElement = (Element) personelDocumentElement
				.getElementsByTagName("adSoyad").item(0);
		assertEquals("ad soyad", adSoyadElement.getFirstChild().getNodeValue());
		// get personelDocumentElement element's kullaniciAdi element and its
		// value
		Element kullaniciAdiElement = (Element) personelDocumentElement
				.getElementsByTagName("kullaniciAdi").item(0);
		assertEquals("kullaniciAdi", kullaniciAdiElement.getFirstChild()
				.getNodeValue());
		// get personelDocumentElement element's parola element and its value
		Element parolaElement = (Element) personelDocumentElement
				.getElementsByTagName("parola").item(0);
		assertEquals("sifre", parolaElement.getFirstChild().getNodeValue());

	}

	public void testReadMusteriInstance() throws Exception {
		// read Musteri.xml file to a document instance.
		Document musteriDocument = documentBuilder.parse(new File(
				FILES_MUSTERI_XML));

		// get document element...
		Element musteriDocumentElement = musteriDocument.getDocumentElement();
		assertEquals("Musteri", musteriDocumentElement.getNodeName());

		// get adSoyad element and assert...
		Element adSoyadElement = (Element) musteriDocumentElement
				.getElementsByTagName("adSoyad").item(0);
		assertEquals("adim soyadim", adSoyadElement.getFirstChild()
				.getNodeValue());
		// get kullaniciAdi element and assert...
		Element kullaniciAdiElement = (Element) musteriDocumentElement
				.getElementsByTagName("kullaniciAdi").item(0);
		assertEquals("kullaniciAdim", kullaniciAdiElement.getFirstChild()
				.getNodeValue());
		// get parola element and assert...
		Element parolaElement = (Element) musteriDocumentElement
				.getElementsByTagName("sifre").item(0);
		assertEquals("sifrem", parolaElement.getFirstChild().getNodeValue());
		// get kartNo element and assert...
		Element kartNoElement = (Element) musteriDocumentElement
				.getElementsByTagName("kartNo").item(0);
		assertEquals("111111", kartNoElement.getFirstChild().getNodeValue());

	}

	public void testReadInstance() throws Exception {
		// read from an xml File...
		Document xmlDocument = documentBuilder.parse(new File(FILES_XML));
		// get document element...
		Element documentElement = xmlDocument.getDocumentElement();
		// create a schemaManager...
		String xmlDocumentElementName = documentElement.getNodeName();
		POSchemaManager schemaManager = SchemaManagerFactory
				.getInstance(xmlDocumentElementName);
		// get schema model...
		Document schemaModel = schemaManager.getSchemaModel();
		// get schema element...
		Element schemaElement = schemaModel.getDocumentElement();
		//
		Element complexTypeElement = (Element) schemaElement.getFirstChild();
		String complexTypeElementName = complexTypeElement.getAttribute(NAME);
		//asserting that complex type's element name is Dvd...
		assertEquals("Dvd", complexTypeElementName);
	}

	public void testValidationAnXmlDocument() throws Exception {

		// read from an xml File...
		Document xmlDocument = documentBuilder.parse(new File(FILES_XML));
		// get document element...
		Element documentElement = xmlDocument.getDocumentElement();
		// create a schemaManager...
		String xmlDocumentElementName = documentElement.getNodeName();
		POSchemaManager schemaManager = SchemaManagerFactory
				.getInstance(xmlDocumentElementName);

		assertTrue(schemaManager.validate(xmlDocument));
	}

	public void testCreateDvdInstanceFromXML() throws Exception {

		// read from an xml file...
		Document xmlDocument = documentBuilder.parse(new File(FILES_DVD_XML));
		// get Document Element...
		Element documentElement = xmlDocument.getDocumentElement();
		// create a schema manager...
		POSchemaManager schemaManager = SchemaManagerFactory
				.getInstance(documentElement.getNodeName());
		
		//creating an po instance from Xml document...
		PO po = schemaManager.createInstanceFromXMLDocument(xmlDocument);
		
		// asserting that po's fields are equal to xml file's...
		Dvd dvd = (Dvd) po;
		assertEquals("dvd124", dvd.getID());
		assertEquals("300 Bandirmali", dvd.getUrunAdi());
		assertEquals("Ovunc Cetin", dvd.getYonetmen());
		assertEquals("Seagent Ltd.Sti.Corp.As.", dvd.getSirket());
		assertEquals((new Integer(30)), dvd.getFiyat());
		assertEquals((new Integer(5)), dvd.getAdet());

	}

	public void testCreatePersonelInstanceFromXml() throws Exception {
		
		//reading xml from a file...
		Document xmlDocument = documentBuilder.parse(new File(FILES_PERSONEL_XML));
		//getting document element of xml document...
		Element documentElement = xmlDocument.getDocumentElement();
		//creating a schema manager and getting a instance of personel by document element's node name...
		POSchemaManager schemaManager = SchemaManagerFactory.getInstance(documentElement.getNodeName());
		
		PO po = schemaManager.createInstanceFromXMLDocument(xmlDocument);
		//casting po to personel...
		Personel personel = (Personel) po;
		
		//asserting that po's fields are equal to xml file's...
		assertEquals("ad soyad", personel.getAdSoyad());
		assertEquals("sifre", personel.getParola());
		assertEquals("kullaniciAdi", personel.getKullaniciAdi());
		assertEquals("kullaniciAdi", personel.getID());
	
	}
	
	public void testCreateMusteriInstancefromXml() throws Exception {
		
		//reading xml from a file...
		Document xmlDocument = documentBuilder.parse(new File(FILES_MUSTERI_XML));
		//getting doc element of xml document to understand xml document's type...
		Element documentElement = xmlDocument.getDocumentElement();
		//creating a schema manager and getting a instance of musteri by document element's node name...
		POSchemaManager schemaManager = SchemaManagerFactory.getInstance(documentElement.getNodeName());
		PO po = schemaManager.createInstanceFromXMLDocument(xmlDocument);
		
		//casting po to musteri...
		Musteri musteri = (Musteri) po;
		
		//asserting that po's fields are equal to xml file's...
		assertEquals("adim soyadim", musteri.getAdSoyad());
		assertEquals("kullaniciAdim", musteri.getKullaniciAdi());
		assertEquals("sifrem", musteri.getParola());
		assertEquals("111111", musteri.getKrediKartNo());
		
	}
}
