package xmlschema;

import static xmlschema.XMLSchemaConstants.COMPLEX_TYPE;
import static xmlschema.XMLSchemaConstants.NAME;
import static xmlschema.XMLSchemaConstants.SEQUENCE;
import static xmlschema.XMLSchemaConstants.STRING;
import static xmlschema.XMLSchemaConstants.XMLSCHEMA_NS;
import static xmlschema.XMLSchemaConstants.XSD_PREFIX;

import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import domain.PO;
import domain.Personel;

public class PersonelSchemaManager extends POSchemaManager {

	private static PersonelSchemaManager personelSchemaManagerInstance;
	

	private PersonelSchemaManager() {
		super();
	}

	public static PersonelSchemaManager getInstance() {
		if (personelSchemaManagerInstance == null) {
			personelSchemaManagerInstance = new PersonelSchemaManager();
		}
		return personelSchemaManagerInstance;
	}

	@Override
	public Document createInstanceXML(PO po) {
		Personel personel = (Personel) po;
		// create an empty document for personel instance....
		Document personelInstanceDoc = createSchemaDocument();
		// create Personel node...
		Element personelElement = personelInstanceDoc.createElement("Personel");

		// set "ad soyad"...
		Element adSoyadElement = personelInstanceDoc.createElement("adSoyad");
		// set the value...
		adSoyadElement.setTextContent(personel.getAdSoyad());

		// create "kullanici adi" element...
		Element kullaniciAdiElement = personelInstanceDoc.createElement("kullaniciAdi");
		// set the value...
		kullaniciAdiElement.setTextContent("" + personel.getID());
		
		// create "parola" element...
		Element parolaElement = personelInstanceDoc.createElement("parola");
		// set the value...
		parolaElement.setTextContent("" + personel.getParola());

		// add "kullaniciAdi" into Personel...
		personelElement.appendChild(kullaniciAdiElement);
		// add "parola" into Personel...
		personelElement.appendChild(parolaElement);
		// add  "adSoyad" into Personel...
		personelElement.appendChild(adSoyadElement);
		
		// set root node...
		personelInstanceDoc.appendChild(personelElement);

		return personelInstanceDoc;
	}

	protected Element createComplexTypeElement() {

		// create complex type...
		Element personelComplexType = getSchemaModel().createElementNS(XMLSCHEMA_NS,
				COMPLEX_TYPE);
		// set prefix..
		personelComplexType.setPrefix(XSD_PREFIX);
		// set complex type name attribute...
		personelComplexType.setAttribute(NAME, "Personel");

		// create sequence element
		Element personelSequenceElement = createSequence();
		// add sequence element to complex type element..
		personelComplexType.appendChild(personelSequenceElement);

		return personelComplexType;
	}

	protected Element createSequence() {

		// create the sequence type..
		Element personelSequenceType = getSchemaModel().createElementNS(
				XMLSCHEMA_NS, SEQUENCE);
		// set the prefix..
		personelSequenceType.setPrefix(XSD_PREFIX);
		
		// creating element ad soyad...
		Element adSoyadElement = createFieldElement("adSoyad", STRING);
		// creating element kullanici adi...
		Element kullaniciAdiElement = createFieldElement("kullaniciAdi", STRING);
		// creating element parola...
		Element parolaElement = createFieldElement("parola", STRING);

		// add ad soyad element to sequence element
		personelSequenceType.appendChild(adSoyadElement);
		// add kullanici adi element to sequence element
		personelSequenceType.appendChild(kullaniciAdiElement);
		// add parola element to sequence element
		personelSequenceType.appendChild(parolaElement);

		return personelSequenceType;
	}

	@Override
	protected PO setFields(Document xmlDocument) {
		//creating an instance object of personel...
		Personel createdPO = new Personel();
		//getting schema fields' node list... 
		NodeList schemaFields = getSchemaFieldsNodeList();
		//finding schema fields' names...
		Vector<String> schemaFieldNames = findSchemaFieldNames(schemaFields);
		//iterating...
		Iterator names = schemaFieldNames.iterator();
		String nodeValue = null;
		while (names.hasNext()) {
			String fieldName = (String) names.next();
			Element documentElement = xmlDocument.getDocumentElement();
			Element element = (Element) documentElement.getElementsByTagName(fieldName).item(0);
			nodeValue = element.getFirstChild().getNodeValue();
			setPersonelInstanceFieldValues(createdPO, nodeValue, fieldName);
		}
		
		return createdPO;
	}

	private void setPersonelInstanceFieldValues(Personel createdPO, String nodeValue, String fieldName) {
		if(fieldName.equals("kullaniciAdi")){
			createdPO.setKullaniciAdi(nodeValue);
			createdPO.setID(nodeValue);
		}else if (fieldName.equals("parola")) {
			createdPO.setParola(nodeValue);
		}else if (fieldName.equals("adSoyad")) {
			createdPO.setAdSoyad(nodeValue);
		}
	}
}
