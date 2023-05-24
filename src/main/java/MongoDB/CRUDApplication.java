package MongoDB;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class CRUDApplication {
    private static final String COLLECTION_NAME = "Orders";

    public static void create(Document document) {
        MongoDatabase database = MongoDBConnection.getConnection();
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        collection.insertOne(document);
    }

    public static Document read(String id) {
        MongoDatabase database = MongoDBConnection.getConnection();
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        return collection.find(new Document("_id", id)).first();
    }

    public static void update(String id, Document updatedDocument) {
        MongoDatabase database = MongoDBConnection.getConnection();
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        collection.replaceOne(new Document("_id", id), updatedDocument);
    }

    public static void delete(String id) {
        MongoDatabase database = MongoDBConnection.getConnection();
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        collection.deleteOne(new Document("_id", id));
    }

    public static void main(String[] args) {
        Document document = new Document("_id", "1")
                .append("name", "John Doe")
                .append("age", 25)
                .append("email", "johndoe@example.com");

        // Create
        CRUDApplication.create(document);

        // Read
        Document retrievedDocument = CRUDApplication.read("1");
        System.out.println(retrievedDocument);

        // Update
        Document updatedDocument = new Document("_id", "1")
                .append("name", "John Doe")
                .append("age", 26)
                .append("email", "johndoe@example.com");
        CRUDApplication.update("1", updatedDocument);

        // Read after update
        retrievedDocument = CRUDApplication.read("1");
        System.out.println(retrievedDocument);

        // Delete
        CRUDApplication.delete("1");

        // Read after delete
        retrievedDocument = CRUDApplication.read("1");
        System.out.println(retrievedDocument);
    }

}
