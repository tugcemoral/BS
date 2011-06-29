package xmlschema;

import static xmlschema.XMLSchemaConstants.COMPLEX_TYPE;
import static xmlschema.XMLSchemaConstants.ELEMENT;
import static xmlschema.XMLSchemaConstants.NAME;
import static xmlschema.XMLSchemaConstants.SCHEMA;
import static xmlschema.XMLSchemaConstants.TYPE;
import static xmlschema.XMLSchemaConstants.XMLSCHEMA_NS;
import static xmlschema.XMLSchemaConstants.XSD_PREFIX;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import domain.PO;
import domain.POFabrikasi;

abstract public class POSchemaManager {

	protected Document schemaModel;

	public POSchemaManager() {
		super();
	}

	public abstract Document createInstanceXML(PO po);
	
	public Document getSchemaModel() {
		if (schemaModel == null) {
			schemaModel = createSchemaDocument();
			// create schema element...
			Element schemaElement = schemaModel.createElementNS(XMLSCHEMA_NS,
					SCHEMA);
			schemaElement.setAttribute("xmlns:" + XSD_PREFIX, XMLSCHEMA_NS);
			// set xsd prefix...
			schemaElement.setPrefix(XSD_PREFIX);
	
			// create complex type Element...
			Element complexTypeElement = createComplexTypeElement();
			// add dvd complex type element into the schema element...
			schemaElement.appendChild(complexTypeElement);
			// set schema element into schemaModel document as root...
			schemaModel.appendChild(schemaElement);
	
		}
		return schemaModel;
	}

	public void writeInstanceTo(Document instanceDocument, OutputStream out) throws IOException {
		// create a xml serializer...
		XMLSerializer xmlSerializer = new XMLSerializer();
		// set output stream...
		xmlSerializer.setOutputByteStream(out);
	
		// create an output format...
		OutputFormat outputFormat = new OutputFormat(instanceDocument);
		// set intending to true...
		outputFormat.setIndenting(true);
		// set output format... 
		xmlSerializer.setOutputFormat(outputFormat);
	
		xmlSerializer.serialize(instanceDocument);
	}

	public void writeSchemaModel(OutputStream out) throws IOException {
		System.out.println();
		writeInstanceTo(getSchemaModel(), out);
	}

	protected abstract Element createComplexTypeElement();

	protected  abstract Element createSequence();
	
	protected Element createFieldElement(String fieldName, String fieldType) {
		// create field element...
		Element fieldElement = getSchemaModel().createElementNS(XMLSCHEMA_NS, ELEMENT);
		// set prefix...
		fieldElement.setPrefix(XMLSchemaConstants.XSD_PREFIX);
		// set name attribute of field element..
		fieldElement.setAttribute(NAME, fieldName);
		// set type attribute of field element...
		fieldElement.setAttribute(TYPE, XSD_PREFIX + ":" + fieldType);
		return fieldElement;
	}

	protected Document createSchemaDocument() {
		Document document = null;
		try {
			// create a document builder factory...
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			// create a document builder...
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			// create a document...
			document = documentBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return document;
	}

	public boolean validate(Document xmlDocument) {
		
		NodeList schemaFields = getSchemaFieldsNodeList();
	
		NodeList instanceFields = getInstanceFieldsNodeList(xmlDocument);
	
		// compare element lists...
		return matchFields(schemaFields, instanceFields);
	}

	protected NodeList getInstanceFieldsNodeList(Document xmlDocument) {
		// get documentElement...
		Element documentElement = xmlDocument.getDocumentElement();
		// get elementList of document element.
		NodeList instanceFields = documentElement.getChildNodes();
		return instanceFields;
	}

	protected NodeList getSchemaFieldsNodeList() {
		// get sequence element...
		Document schemaModel = getSchemaModel();
		Element complexTypeElement = (Element) schemaModel.getElementsByTagName(
				XSD_PREFIX + ":" + COMPLEX_TYPE).item(0);
		Element sequenceElement = (Element) complexTypeElement.getFirstChild();
		
		// get element's of sequence element...
		NodeList schemaFields = sequenceElement.getChildNodes();
		return schemaFields;
	}

	private boolean matchFields(NodeList schemaFields, NodeList instanceFields) {
		
		boolean result = true;
		
		Vector<String> schemaFieldNames = findSchemaFieldNames(schemaFields);
	
		//search instance field names in schema field names...
		for (int i = 1; i < instanceFields.getLength(); i+=2) {
			String nodeName = instanceFields.item(i).getNodeName();
			result &= schemaFieldNames.contains(nodeName);
		}
	
		return result;
	}

	protected Vector<String> findSchemaFieldNames(NodeList schemaFields) {
		Vector<String> schemaFieldNames = new Vector<String>();
		// get schemaFields' names...
		for (int i = 0; i < schemaFields.getLength(); i++) {
			Element element = (Element) schemaFields.item(i);
			schemaFieldNames.add(element.getAttribute(NAME));
		}
		return schemaFieldNames;
	}

	public PO createInstanceFromXMLDocument(Document xmlDocument) {
		PO poInstance = null;
		
		//if xml document is validated
		if (validate(xmlDocument)) {
			//set atributes of instance...
			poInstance = POFabrikasi.getInstance(poInstance);
			poInstance = setFields(xmlDocument);
		}
		
		return poInstance;
	}

	protected abstract PO setFields(Document xmlDocument);
	
}