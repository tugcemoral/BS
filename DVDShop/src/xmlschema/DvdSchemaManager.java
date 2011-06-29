package xmlschema;

import static xmlschema.XMLSchemaConstants.COMPLEX_TYPE;
import static xmlschema.XMLSchemaConstants.NAME;
import static xmlschema.XMLSchemaConstants.POSITIVE_INTEGER;
import static xmlschema.XMLSchemaConstants.SEQUENCE;
import static xmlschema.XMLSchemaConstants.STRING;
import static xmlschema.XMLSchemaConstants.XMLSCHEMA_NS;
import static xmlschema.XMLSchemaConstants.XSD_PREFIX;

import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import domain.Dvd;
import domain.PO;

public class DvdSchemaManager extends POSchemaManager {

	private static POSchemaManager dvdSchemaManagerInstance;

	private DvdSchemaManager() {
		super();
	}

	public static POSchemaManager getInstance() {
		if (dvdSchemaManagerInstance == null) {
			dvdSchemaManagerInstance = new DvdSchemaManager();
		}
		return dvdSchemaManagerInstance;
	}

	@Override
	public Document createInstanceXML(PO po) {
		Dvd dvd = (Dvd) po;
		// create an empty document for dvd instance....
		Document dvdInstanceDoc = createSchemaDocument();
		// create Dvd node...
		Element dvdElement = dvdInstanceDoc.createElement("Dvd");

		// set "film adi"...
		Element urunAdiElement = dvdInstanceDoc.createElement("urunAdi");
		// set the value...
		urunAdiElement.setTextContent(dvd.getUrunAdi());

		// create "fiyat" element...
		Element fiyatElement = dvdInstanceDoc.createElement("fiyat");
		// set the value...
		fiyatElement.setTextContent("" + dvd.getFiyat());

		// create "id" element:..
		Element idElement = dvdInstanceDoc.createElement("barkod");
		// set the value...
		idElement.setTextContent("" + dvd.getID());

		// create "sirket" element:..
		Element sirketElement = dvdInstanceDoc.createElement("sirket");
		// set the value...
		sirketElement.setTextContent("" + dvd.getSirket());

		// create "adet" element:..
		Element adetElement = dvdInstanceDoc.createElement("adet");
		// set the value...
		adetElement.setTextContent("" + dvd.getAdet());

		// create "yonetmen" element:..
		Element yonetmenElement = dvdInstanceDoc.createElement("yonetmen");
		// set the value...
		yonetmenElement.setTextContent("" + dvd.getYonetmen());

		// add "barkod" into Dvd...
		dvdElement.appendChild(idElement);
		// add "urunAdi" into Dvd...
		dvdElement.appendChild(urunAdiElement);
		// add "yonetmen" into Dvd...
		dvdElement.appendChild(yonetmenElement);
		// add "sirket" into Dvd...
		dvdElement.appendChild(sirketElement);
		// add "fiyat" into Dvd...
		dvdElement.appendChild(fiyatElement);
		// add "adet" into Dvd...
		dvdElement.appendChild(adetElement);

		// set root node...
		dvdInstanceDoc.appendChild(dvdElement);

		return dvdInstanceDoc;
	}

	@Override
	protected Element createComplexTypeElement() {
		// create complex type element...
		Element dvdComplexTypeElement = schemaModel.createElementNS(
				XMLSCHEMA_NS, COMPLEX_TYPE);
		// set prefix...
		dvdComplexTypeElement.setPrefix(XSD_PREFIX);
		// set complexType name attribute...
		dvdComplexTypeElement.setAttribute(NAME, "Dvd");
		// create "sequence" element to complexType element...
		Element sequenceElement = createSequence();

		// add "sequence" element into the dvd complex type...
		dvdComplexTypeElement.appendChild(sequenceElement);
		return dvdComplexTypeElement;
	}

	@Override
	protected Element createSequence() {
		Element sequenceElement = schemaModel.createElementNS(XMLSCHEMA_NS,
				SEQUENCE);
		// set prefix...
		sequenceElement.setPrefix(XSD_PREFIX);
		// create "yonetmen" field...
		Element yonetmenElement = createFieldElement("yonetmen", STRING);
		// create "fiyat" field...
		Element fiyatElement = createFieldElement("fiyat", POSITIVE_INTEGER);
		// create "adet" field..
		Element adetElement = createFieldElement("adet", POSITIVE_INTEGER);
		// create "urunAdi" element...
		Element urunAdiElement = createFieldElement("urunAdi", STRING);
		// create "sirket" element...
		Element sirketElement = createFieldElement("sirket", STRING);
		// create "ID" element...
		Element IDElement = createFieldElement("id", STRING);
		// add "id" element to sequence..
		sequenceElement.appendChild(IDElement);
		// add "sirket" element to sequence..
		sequenceElement.appendChild(sirketElement);
		// add "urunAdi" element to sequence..
		sequenceElement.appendChild(urunAdiElement);
		// add "adet" element to sequence...
		sequenceElement.appendChild(adetElement);
		// add "yönetmen element..."
		sequenceElement.appendChild(yonetmenElement);
		// add "fiyat" element into sequence node...
		sequenceElement.appendChild(fiyatElement);
		return sequenceElement;
	}
	
	@Override
	protected PO setFields(Document xmlDocument) {
		Dvd createdPO = new Dvd();
		
		//get schema field names...
		NodeList schemaFields = getSchemaFieldsNodeList();
		Vector<String> schemaFieldNames = findSchemaFieldNames(schemaFields);
		
		String tagName = null;
		String value = null;
		Iterator isimler = schemaFieldNames.iterator();
		while (isimler.hasNext()) {
			tagName = (String) isimler.next();
			Element docElement = xmlDocument.getDocumentElement();
			Element element = (Element) docElement
					.getElementsByTagName(tagName).item(0);
			value = element.getFirstChild().getNodeValue();
			setDvdInstanceField(createdPO, tagName, value);
		}
		
		return createdPO;
	}

	/**
	 * set one of the createdPO's fields
	 * @param createdPO
	 * @param tagName
	 * @param value
	 */
	private void setDvdInstanceField(Dvd createdPO, String tagName, String value) {
		if(tagName.equals("id")){
			createdPO.setID(value);
		}else if (tagName.equals("urunAdi")) {
			createdPO.setUrunAdi(value);
		} else if(tagName.equals("yonetmen")){
			createdPO.setYonetmen(value);
		}else if (tagName.equals("sirket")) {
			createdPO.setSirket(value);
		}else if (tagName.equals("fiyat")) {
			createdPO.setFiyat(Integer.parseInt(value));
		}else if (tagName.equals("adet")) {
			createdPO.setAdet((Integer.parseInt(value)));
		}
	}
	
}
