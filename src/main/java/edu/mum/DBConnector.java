package edu.mum;

import java.net.MalformedURLException;

import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

public class DBConnector {
	
	private DBConnector() {}
	
	private static CouchDbInstance dbInstance = null;
	
	public static CouchDbInstance getInstance() {
		try {
			HttpClient httpClient = new StdHttpClient.Builder().url("http://localhost:5984")
					.username("admin")
					.password("rupenman")
					.build();
			dbInstance = new StdCouchDbInstance(httpClient);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return dbInstance;
	}
}
