#<b>Ako JSON Parser for java</br>

Author :
-----------
Sako

Documentation : 
------------
In this library we have to classes :
1. JSONBuilder : </br> This is the class that is used for parsing a JSON file into a JSONDocument and a JSONDocument into a JSON file.</br></br>
2. JSONDocument : </br>This class (as you can tell by the name) is the JSONDocument which is
created by the JSONBuilder class.
   
Note :
------------
JSONDocument's toString method has been overridden and returns the JSON format of the document.</br>
Also every method has Java doc, so i won't write every method documentation.

Examples :
------------
```
JSONBuilder bld = JSONBuilder.newInstance();
JSONDocument doc = bld.parse(new File("example.json"));
ArrayList data = (ArrayList) doc.toJava();
```

```
Socket s = new Socket("host",1234);
JSONBuilder bld = JSONBuilder.newInstance();
JSONDocument doc = bld.parse(s.getInputStream());
HashMap data = (HashMap) doc.toJava();
```

```
String json = "some json data";
JSONBuilder bld = JSONBuilder.newInstance();
JSONDocument doc = bld.parse(json);
HashMap data = (HashMap) doc.toJava();
```

```
ArrayList data = new ArrayList(); //Can be used raw or parameterized;

//Some processing on the list.

JSONBuilder bld = JSONBuilder.newInstance();
JSONDocument doc = new JSONDocument(data);
bld.write(doc,new File("example.json"));
```
```
HashMap data = new HashMap(); //Can be used raw or parameterized;

//Some processing on the map.

Socket s = new Socket("host",1234);
JSONBuilder bld = JSONBuilder.newInstance();
JSONDocument doc = new JSONDocument(data);
bld.write(doc,s.getOutputStream());
```