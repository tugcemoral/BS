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

import domain.Musteri;
import domain.PO;

public class MusteriSchemaManager extends POSchemaManager{

	private static MusteriSchemaManager musteriSchemaManagerInstance;
	
	private MusteriSchemaManager() {
		super();
	}
	
	public static MusteriSchemaManager getInstance() {
		if (musteriSchemaManagerInstance == null) {
			musteriSchemaManagerInstance = new MusteriSchemaManager();
		}
		return musteriSchemaManagerInstance;
	}
	
	@Override
	public Document createInstanceXML(PO po) {
		Musteri musteri = (Musteri) po;
		// create an empty document for musteri instance....
		Document musteriInstanceDoc = createSchemaDocument();
		// create musteri node...
		Element musteriElement = musteriInstanceDoc.createElement("Musteri");

		// set "ad soyad"...
		Element adSoyadElement = musteriInstanceDoc.createElement("adSoyad");
		// set the value...
		adSoyadElement.setTextContent(musteri.getAdSoyad());

		// create "kullanici adi" element...
		Element kullaniciAdiElement = musteriInstanceDoc.createElement("kullaniciAdi");
		// set the value...
		kullaniciAdiElement.setTextContent("" + musteri.getID());
		
		// create "parola" element...
		Element parolaElement = musteriInstanceDoc.createElement("parola");
		// set the value...
		parolaElement.setTextContent("" + musteri.getParola());

		//create "kartNo" element...
		Element kartNoElement = musteriInstanceDoc.createElement("kartNo");
		//set the value...
		kartNoElement.setTextContent("" + musteri.getKrediKartNo());
		
		// add  "adSoyad" into Musteri...
		musteriElement.appendChild(adSoyadElement);
		// add "kullaniciAdi" into Musteri...
		musteriElement.appendChild(kullaniciAdiElement);
		// add "parola" into Musteri...
		musteriElement.appendChild(parolaElement);
		// add  "kartNo" into Musteri...
		musteriElement.appendChild(kartNoElement);
		
		// set root node...
		musteriInstanceDoc.appendChild(musteriElement);

		return musteriInstanceDoc;
	}

	@Override
	protected Element createComplexTypeElement() {
		//create complex type element...
		Element musteriComplexTypeElement = getSchemaModel().createElementNS(XMLSCHEMA_NS, COMPLEX_TYPE);
		//set prefix...
		musteriComplexTypeElement.setPrefix(XSD_PREFIX);
		//set name attribute...
		musteriComplexTypeElement.setAttribute(NAME, "Musteri");
		
		//create sequence element...
		Element musteriSequenceElement = createSequence();
		//add sequence element into complex type... 
		musteriComplexTypeElement.appendChild(musteriSequenceElement);
		
		return musteriComplexTypeElement;
		
	}

	@Override
	protected Element createSequence() {
		//create a musteri sequence element..
		Element musteriSequenceElement = getSchemaModel().createElementNS(XMLSCHEMA_NS, SEQUENCE);
		//setting its attributes..
		musteriSequenceElement.setPrefix(XSD_PREFIX);

		//create the fields..
		Element adSoyadElement = createFieldElement("adSoyad", STRING);
		Element kullaniciAdiElement = createFieldElement("kullaniciAdi", STRING);
		Element sifreElement = createFieldElement("sifre", STRING);
		Element kartNoElement = createFieldElement("kartNo", STRING);
		
		//add fields into sequence element..
		musteriSequenceElement.appendChild(adSoyadElement);
		musteriSequenceElement.appendChild(kullaniciAdiElement);
		musteriSequenceElement.appendChild(sifreElement);
		musteriSequenceElement.appendChild(kartNoElement);
		
		return musteriSequenceElement;
		
	}

	@Override
	protected PO setFields(Document xmlDocument) {
		Musteri createdCostumer = new Musteri();
		
		NodeList schemaFieldsNodeList = getSchemaFieldsNodeList();
		Vector<String> names = findSchemaFieldNames(schemaFieldsNodeList);
		
		Iterator iterate = names.iterator();
		while(iterate.hasNext()){
			String fieldName = (String) iterate.next();
			Element element = (Element) xmlDocument.getElementsByTagName(fieldName).item(0);
			String nodeValue = element.getFirstChild().getNodeValue();
			setMusteriInstanceFieldValues(createdCostumer, fieldName, nodeValue);
		}
		
		return createdCostumer;
	}

	private void setMusteriInstanceFieldValues(Musteri createdCostumer, String fieldName, String nodeValue) {
		if(fieldName.equals("adSoyad")){
			createdCostumer.setAdSoyad(nodeValue);
		}else if (fieldName.equals("kullaniciAdi")) {
			createdCostumer.setKullaniciAdi(nodeValue);
			createdCostumer.setID(nodeValue);
		}else if (fieldName.equals("sifre")) {
			createdCostumer.setParola(nodeValue);
		}else if (fieldName.equals("kartNo")) {
			createdCostumer.setKrediKartNo(nodeValue);
		}
	}

}
