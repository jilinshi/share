package com.share.mongodb;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class MongoDBManager {
	private MongoClient client;

	private String user;
	private String password;
	private String hoststr;
	private String port;

	public MongoDBManager(String database) {

		try {
			Properties prop = new Properties();

			URL u1 = MongoDBManager.class.getClassLoader().getResource(
					"db.properties");
			System.out.println(u1.toString());
			FileInputStream fis = new FileInputStream(u1.getFile());
			InputStream in = fis;
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

			ServerAddress host = new ServerAddress(hoststr, new Integer(port));

			client = new MongoClient(host, mcs,
					new MongoClientOptions.Builder().socketTimeout(300000)
							.connectionsPerHost(500)
							.threadsAllowedToBlockForConnectionMultiplier(500)
							.socketKeepAlive(true).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MongoDBManager(String[] database) {

		try {
			Properties prop = new Properties();
			InputStream in = Object.class.getResourceAsStream("/db.properties");
			prop.load(in);
			ArrayList<MongoCredential> mcs = new ArrayList<MongoCredential>();
			ArrayList<ServerAddress> hosts = new ArrayList<ServerAddress>();
			for (String e : database) {
				System.out.println(e);
				user = prop.getProperty(e + ".user").trim();
				password = prop.getProperty(e + ".password").trim();
				hoststr = prop.getProperty(e + ".host").trim();
				port = prop.getProperty(e + ".port").trim();

				MongoCredential credential = MongoCredential
						.createScramSha1Credential(user, e,
								password.toCharArray());
				ServerAddress host = new ServerAddress(hoststr, new Integer(
						port));
				mcs.add(credential);
				hosts.add(host);
			}

			client = new MongoClient(hosts, mcs,
					new MongoClientOptions.Builder().socketTimeout(300000)
							.connectionsPerHost(500)
							.threadsAllowedToBlockForConnectionMultiplier(500)
							.socketKeepAlive(true).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入数据
	 * 
	 * @param document
	 * @param databaseName
	 * @param collectionname
	 */
	public void inster(Document document, String databaseName,
			String collectionname) {
		this.client.getDatabase(databaseName).getCollection(collectionname)
				.insertOne(document);

	}

	/**
	 * 批量插入
	 * 
	 * @param documents
	 * @param databaseName
	 * @param collectionname
	 */
	public void insterMany(List<? extends Document> documents,
			String databaseName, String collectionname) {
		this.client.getDatabase(databaseName).getCollection(collectionname)
				.insertMany(documents);
	}

	/**
	 * 根据调教更新单条记录
	 * 
	 * @param update
	 *            更新
	 * @param filter
	 *            条件
	 * @param databaseName
	 *            数据库
	 * @param collectionname
	 *            集合
	 * @return $set 设置字段或者添加字段
	 */
	public String update(Bson update, Bson filter, String databaseName,
			String collectionname) {
		UpdateResult r = this.client.getDatabase(databaseName)
				.getCollection(collectionname)
				.updateOne(filter, new BasicDBObject("$set", update));
		return r.toString();
	}

	/**
	 * 根据调教更新多条记录
	 * 
	 * @param update
	 *            更新
	 * @param filter
	 *            条件
	 * @param databaseName
	 *            数据库
	 * @param collectionname
	 *            集合
	 * @return $set 设置字段或者添加字段
	 */
	public String updateMany(Bson update, Bson filter, String databaseName,
			String collectionname) {
		UpdateResult r = this.client.getDatabase(databaseName)
				.getCollection(collectionname)
				.updateMany(filter, new BasicDBObject("$set", update));
		return r.toString();
	}

	/**
	 * 用操作符更新
	 * 
	 * @param update
	 * @param filter
	 * @param databaseName
	 * @param collectionname
	 * @param operator
	 *            $set $inc $mul .........
	 * @return
	 */
	public String updateOperator(Bson update, Bson filter, String databaseName,
			String collectionname, String operator) {
		UpdateResult r = this.client.getDatabase(databaseName)
				.getCollection(collectionname)
				.updateOne(filter, new BasicDBObject(operator, update));
		return r.toString();
	}

	/**
	 * 查询所有
	 * 
	 * @param filter
	 * @param sort
	 * @param databaseName
	 * @param collectionname
	 * @return
	 */
	public FindIterable<Document> queryAll(Bson filter, Bson sort,
			String databaseName, String collectionname) {
		return client.getDatabase(databaseName).getCollection(collectionname)
				.find(filter).sort(sort);

	}

	/**
	 * 按条件查询
	 * 
	 * @param filter
	 * @param sort
	 * @param skip
	 * @param limit
	 * @param databaseName
	 * @param collectionname
	 * @return
	 */
	public FindIterable<Document> queryRows(Bson filter, Bson sort, int skip,
			int limit, String databaseName, String collectionname) {
		return client.getDatabase(databaseName).getCollection(collectionname)
				.find(filter).sort(sort).skip(skip).limit(limit);

	}

	/**
	 * 
	 * @param databaseName
	 * @param collectionname
	 * @param filename
	 *            文件名称
	 * @param in
	 *            文件输入流
	 * @param metadata
	 * @param id
	 * @param contentType
	 */
	@SuppressWarnings("deprecation")
	public String insertFile(String databaseName, String collectionname,
			Object id, String filename, String contentType, DBObject metadata,
			InputStream in) {
		GridFS gridFS = new GridFS(client.getDB(databaseName), collectionname);
		GridFSInputFile gridFSInputFile = gridFS.createFile(in);
		gridFSInputFile.setFilename(filename);
		gridFSInputFile.setMetaData(metadata);
		gridFSInputFile.setId(id);
		gridFSInputFile.setContentType(contentType);
		gridFSInputFile.save();
		System.out.println(gridFSInputFile.toString());
		return filename;
	}

	/**
	 * 按文件名删除文件
	 * 
	 * @param databaseName
	 * @param collectionname
	 * @param filename
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public void removeFile(String databaseName, String collectionname,
			String filename) {
		GridFS gridFS = new GridFS(client.getDB(databaseName), collectionname);
		gridFS.remove(filename);
	}

	@SuppressWarnings("deprecation")
	public void removeFile(String databaseName, String collectionname,
			ObjectId objectId) {
		GridFS gridFS = new GridFS(client.getDB(databaseName), collectionname);
		gridFS.remove(objectId);
	}

	/**
	 * 返回输出流
	 * 
	 * @param databaseName
	 * @param collectionname
	 * @param filename
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public InputStream readFile(String databaseName, String collectionname,
			String filename) {
		GridFS gridFS = new GridFS(client.getDB(databaseName), collectionname);
		GridFSDBFile gridFSDbFile = gridFS.findOne(filename);
		return gridFSDbFile.getInputStream();
	}

	@SuppressWarnings("deprecation")
	public InputStream readFile(String databaseName, String collectionname,
			ObjectId objectId) {
		GridFS gridFS = new GridFS(client.getDB(databaseName), collectionname);
		GridFSDBFile gridFSDbFile = gridFS.findOne(objectId);
		return gridFSDbFile.getInputStream();
	}

	@SuppressWarnings("deprecation")
	public InputStream readFile(String databaseName, String collectionname,
			DBObject query) {
		GridFS gridFS = new GridFS(client.getDB(databaseName), collectionname);
		GridFSDBFile gridFSDbFile = gridFS.findOne(query);
		return gridFSDbFile.getInputStream();
	}

	@SuppressWarnings("deprecation")
	public List<GridFSDBFile> readFiles(String databaseName,
			String collectionname, DBObject query) {
		GridFS gridFS = new GridFS(client.getDB(databaseName), collectionname);
		return gridFS.find(query);
	}

	@SuppressWarnings("deprecation")
	public List<GridFSDBFile> readFiles(String databaseName,
			String collectionname, DBObject query, DBObject sort) {
		GridFS gridFS = new GridFS(client.getDB(databaseName), collectionname);
		return gridFS.find(query, sort);
	}

	public void close() {
		if (null != client)
			client.close();
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

		String[] aa = new String[1];
		aa[0] = "sharefile";
		MongoDBManager mm1 = new MongoDBManager(aa);
		mm1.getClient();
		System.out.println(mm1.getClient().getAddress());
		mm1.close();
	}
}
