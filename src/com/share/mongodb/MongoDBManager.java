package com.share.mongodb;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;

public class MongoDBManager {

	private static Logger log = LoggerFactory.getLogger(MongoDBManager.class);

	private MongoClient client;

	private String user;
	private String password;
	private String hoststr;
	private String port;
	private static ThreadLocal<MongoClient> connectionHolder1 = new ThreadLocal<MongoClient>();
	private static ThreadLocal<MongoClient> connectionHolder2 = new ThreadLocal<MongoClient>();

	public MongoDBManager(String database) {

		try {
			client = connectionHolder1.get();
			if (null == client) {

				Properties prop = new Properties();
				InputStream in = Object.class
						.getResourceAsStream("/db.properties");
				prop.load(in);
				user = prop.getProperty(database + ".user").trim();
				password = prop.getProperty(database + ".password").trim();
				hoststr = prop.getProperty(database + ".host").trim();
				port = prop.getProperty(database + ".port").trim();

				MongoCredential credential = MongoCredential
						.createScramSha1Credential(user, database,
								password.toCharArray());

				ArrayList<MongoCredential> mcs = new ArrayList<MongoCredential>();
				mcs.add(credential);

				ServerAddress host = new ServerAddress(hoststr, new Integer(
						port));

				client = new MongoClient(host, mcs,
						new MongoClientOptions.Builder()
								.socketTimeout(300000)
								.connectionsPerHost(500)
								.threadsAllowedToBlockForConnectionMultiplier(
										500).socketKeepAlive(true).build());
				connectionHolder1.set(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MongoDBManager(String[] database) {

		try {
			client = connectionHolder2.get();
			if (null == client) {
				Properties prop = new Properties();
				InputStream in = Object.class
						.getResourceAsStream("/db.properties");
				prop.load(in);
				ArrayList<MongoCredential> mcs = new ArrayList<MongoCredential>();
				ArrayList<ServerAddress> hosts = new ArrayList<ServerAddress>();
				for (String e : database) {
					user = prop.getProperty(database + ".user").trim();
					password = prop.getProperty(database + ".password").trim();
					hoststr = prop.getProperty(database + ".host").trim();
					port = prop.getProperty(database + ".port").trim();

					MongoCredential credential = MongoCredential
							.createScramSha1Credential(user, e,
									password.toCharArray());
					ServerAddress host = new ServerAddress(hoststr,
							new Integer(port));
					mcs.add(credential);
					hosts.add(host);
				}

				client = new MongoClient(hosts, mcs,
						new MongoClientOptions.Builder()
								.socketTimeout(300000)
								.connectionsPerHost(500)
								.threadsAllowedToBlockForConnectionMultiplier(
										500).socketKeepAlive(true).build());
				connectionHolder2.set(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MongoCollection<Document> getCollection(String databaseName,
			String collectionname) {
		return client.getDatabase(databaseName).getCollection(collectionname);
	}

	public void insert(String databaseName, String collectionname,
			Document document) {
		try {
			this.getCollection(databaseName, collectionname)
					.insertOne(document);
		} catch (Exception e) {
			log.info(e.toString());
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * @param databaseName
	 *            数据库名称
	 * @param collectionname
	 *            集合名称
	 * @param document
	 *            更新对象
	 * @param updater
	 *            更新内容
	 */
	public void upate(String databaseName, String collectionname, Bson filter,
			Bson update) {
		this.getCollection(databaseName, collectionname).updateOne(filter,
				update);
	}

	public void close() {
		if (null != client)
			client.close();
		connectionHolder1.remove();
		connectionHolder2.remove();
	}

	public MongoClient getClient() {
		return client;
	}

	public void setClient(MongoClient client) {
		this.client = client;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHoststr() {
		return hoststr;
	}

	public void setHoststr(String hoststr) {
		this.hoststr = hoststr;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public static void main(String[] a) {
		MongoDBManager mm = new MongoDBManager("sharefile");
		mm.getClient();
		System.out.println(mm.getClient().getAddress());
		mm.close();
	}

}
