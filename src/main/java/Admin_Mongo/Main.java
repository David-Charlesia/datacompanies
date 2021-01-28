package Admin_Mongo;

import java.io.*;
import java.util.*;

import org.bson.Document;
//import org.junit.Assert;
import org.lpld.datacompanies.backend.mongodb.DatabaseMongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("Atol les opticiens !");
        String cluster_address = "";
		DatabaseMongoDB db = new DatabaseMongoDB(cluster_address);
        MongoDatabase database = db.getDataBase();
        MongoCollection<Document> collection = database.getCollection("Comptes_annuels");

        String chemin = "C:\\Users\\Riber\\Documents\\Debut4";
        File dossier = new File(chemin);
        File[] Foret = dossier.listFiles();
        int taille = dossier.listFiles().length;
        System.out.println(taille);
        for(int i = 148731; i < taille;i++)
        {	String name = Foret[i].getName();
        	System.out.println(name +"  "+ (double)i/(taille) * 100 +" %");
        	//Import_JSON(collection,chemin+"\\"+name);
        }
	}
	public static void Import_JSON(MongoCollection<Document> collection, String fichier) throws FileNotFoundException, IOException
	{
		int count = 0;
        int batch = 100;

        List<InsertOneModel<Document>> docs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
              String line;
              while ((line = br.readLine()) != null) {
                 docs.add(new InsertOneModel<>(Document.parse(line)));
                 count++;
                 if (count == batch) {
                   collection.bulkWrite(docs, new BulkWriteOptions().ordered(false));
                   docs.clear();
                   count = 0;
                }
            }
        }
        if (count > 0) {
           collection.bulkWrite(docs, new BulkWriteOptions().ordered(false));
           }
	}
}