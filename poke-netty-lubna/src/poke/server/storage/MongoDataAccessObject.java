package poke.server.storage;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.*;


public class MongoDataAccessObject {
	
	private String host;
	private int port;
	private static DB mydb;
	private String dbName;
	private static DBCollection collections;
	private MongoClient mongoc;

	  public MongoDataAccessObject()
	  {
		  
		  mongosetup();
	  }
	   
	   private void mongosetup()
	{
		host="localhost";
		port=27107;
		
	}
	
	public MongoClient getMongoclient()
	{
		try{
		mongosetup();
		mongoc= new MongoClient(host,port);
		 
		}
		catch(Exception e)
		{
			System.out.println("Unable to connect to database");
		}
		return mongoc;
		
	}
	


	public DB getDB (String dataBase)	{
	mydb = mongoc.getDB(dataBase);
	return mydb;
	}

	public DBCollection getCollection(String collection) {
	collections = mydb.getCollection(collection);
	return collections;
	}

	public void insertDocument(BasicDBObject document)	{
	collections.insert(document);
	}

	public void deleteDocument(BasicDBObject document)	{
	collections.findAndRemove(document);
	}

	public void updateDocument(BasicDBObject query, BasicDBObject update )	{
	collections.findAndModify(query, update);
	}


	public void closeConnection() {
	mongoc.close();

	}

	public DBCursor findDocument(BasicDBObject query1) {
	return collections.find(query1);
	}



	public String getDbName() {
	return dbName;
	}

	public void setDbName(String dbName) {
	this.dbName = dbName;
	}

	


	}


