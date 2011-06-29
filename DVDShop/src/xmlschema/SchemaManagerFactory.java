package xmlschema;

public class SchemaManagerFactory {
	
	public static POSchemaManager getInstance(String documentElementName){
		POSchemaManager schemaManager = null;
		
		if (documentElementName.equals("Dvd")){
			schemaManager = DvdSchemaManager.getInstance();
		}else if (documentElementName.equals("Musteri")){
			schemaManager = MusteriSchemaManager.getInstance();
		}else if (documentElementName.equals("Personel")){
			schemaManager = PersonelSchemaManager.getInstance();
		}
		
		return schemaManager;
	} 

}
