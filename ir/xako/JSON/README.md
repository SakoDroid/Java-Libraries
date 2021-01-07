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
   
Examples :</br>
```
JSONBuilder bld = JSONBuilder.newInstance();
JSONDocument doc = bld.parse(new File("example.json"));
ArrayList data = (ArrayList) doc.toJava();
```

```
Socket s = new Socket("host",1234);
JSONBuilder bld = JSONBuilder.newInstance();
JSONDocument doc = bld.parse(s.getInputStream());
ArrayList data = (ArrayList) doc.toJava();
```