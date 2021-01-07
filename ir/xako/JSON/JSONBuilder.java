package ir.xako.JSON;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONBuilder {


    public JSONDocument parse (String data){
        JSONDocument temp = null;
        if (data != null && !data.isEmpty()){
            temp = this.parseString(data);
        }else throw new IllegalArgumentException("data cannot be null or empty");
        return temp;
    }

    public JSONDocument parse (File fl){
        JSONDocument temp = null;
        String data = "";
        if (fl != null){
            try (FileReader fr = new FileReader(fl)){
                int c;
                while((c = fr.read()) != -1) data += (char)c;
                temp = this.parseString(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else throw new IllegalArgumentException("File cannot be null");
        return temp;
    }

    public JSONDocument parse (InputStream is){
        JSONDocument temp = null;
        String data = "";
        if (is != null){
            try{
                int c;
                while((c = is.read()) != -1) data += (char)c;
                temp = this.parseString(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else throw new IllegalArgumentException("InputStream cannot be null");
        return temp;
    }

    private JSONDocument parseString(String data){
        JSONDocument temp = null;
        int pointer = 0;
        boolean firstValidCharReached = false;
        do{
            char c = data.charAt(pointer ++);
            switch (c) {
                case '{':
                    HashMap thm = this.parseMap(this.getFull(data.substring(pointer),0));
                    temp = new JSONDocument(thm);
                    firstValidCharReached = true;
                    break;
                case '[':
                    ArrayList tal = this.parseList(this.getFull(data.substring(pointer),1));
                    temp = new JSONDocument(tal);
                    firstValidCharReached = true;
                    break;
            }
        }while (!firstValidCharReached);
        return temp;
    }

    private ArrayList parseList(String localData){
        int localPointer = 0;
        ArrayList temp = new ArrayList();
        char c = localData.charAt(localPointer++);
        while (c != ']'){
            String item = "";
            Object itemObj = null;
            item_loop :
            while (c != ']'){
                switch (c){
                    case ',' :
                        c = localData.charAt(localPointer++);
                        break item_loop;
                    case '{' :
                        String tempItem = this.getFull(localData.substring(localPointer),0);
                        localPointer += tempItem.length();
                        item += tempItem;
                        break ;
                    case '[' :
                        String tempItem2 = this.getFull(localData.substring(localPointer),1);
                        localPointer += tempItem2.length();
                        item += tempItem2;
                        break ;
                    default :
                        item += c;
                        break ;
                }
                c = localData.charAt(localPointer++);
            }
            item = item.trim();
            switch (this.getDataType(item.trim())){
                case String:
                    itemObj = item.trim().replace("\"","");
                    break ;
                case Integer:
                    itemObj = Integer.parseInt(item.trim());
                    break ;
                case Float:
                    itemObj = Float.parseFloat(item.trim());
                    break ;
                case Boolean:
                    itemObj = Boolean.parseBoolean(item.trim());
                    break ;
                case Map:
                    itemObj = this.parseMap(item.trim());
                    break ;
                case List:
                    itemObj = this.parseList(item.trim());
            }
            temp.add(itemObj);
        }
        return temp;
    }

    private HashMap parseMap(String localData){
        int localPointer = 0;
        HashMap temp = new HashMap();
        char c = localData.charAt(localPointer++);
        while (c != '}'){
            String entry = "";
            entry_loop :
            while (c != '}'){
                switch (c){
                    case ',' :
                        c = localData.charAt(localPointer++);
                        break entry_loop;
                    case '{' :
                        String tempEntry = this.getFull(localData.substring(localPointer),0);
                        entry += tempEntry;
                        localPointer += tempEntry.length();
                        break ;
                    case '[' :
                        String tempEntry2 = this.getFull(localData.substring(localPointer),1);
                        entry += tempEntry2;
                        localPointer += tempEntry2.length();
                        break ;
                    default :
                        entry += c;
                        break ;
                }
                c = localData.charAt(localPointer++);
            }
            entry = entry.trim();
            String[] entryArray = entry.split(":",2);
            String key = entryArray[0].trim();
            String value = entryArray[1].trim();
            Object keyObj = null;
            Object valueObj = null;

            switch (this.getDataType(key.trim())){
                case String:
                    keyObj = key.trim().replace("\"","");
                    break ;
                case Integer:
                    keyObj = Integer.parseInt(key.trim());
                    break ;
                case Float:
                    keyObj = Float.parseFloat(key.trim());
                    break ;
                case Boolean:
                    keyObj = Boolean.parseBoolean(key.trim());
                    break ;
                case Map:
                    keyObj = this.parseMap(key.trim());
                    break ;
                case List:
                    keyObj = this.parseList(key.trim());
            }

            switch (this.getDataType(value.trim())){
                case String:
                    valueObj = value.trim().replace("\"","");
                    break ;
                case Integer:
                    valueObj = Integer.parseInt(value.trim());
                    break ;
                case Float:
                    valueObj = Float.parseFloat(value.trim());
                    break ;
                case Boolean:
                    valueObj = Boolean.parseBoolean(value.trim());
                    break ;
                case Map:
                    valueObj = this.parseMap(value.trim());
                    break ;
                case List:
                    valueObj = this.parseList(value.trim());
            }
            temp.put(keyObj,valueObj);
        }
        return temp;
    }

    public void write(JSONDocument doc,File fl){
        try(FileWriter fw  = new FileWriter(fl)){
            fw.write(this.parseJSONDocument(doc));
            fw.flush();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void write(JSONDocument doc, OutputStream os){
        try{
            os.write(this.parseJSONDocument(doc).getBytes());
            os.flush();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    //Converts a JSONDocument to string (to be written in JSON file).
    private String parseJSONDocument(JSONDocument json){

    }

    //Returns the type of a token is JSON.
    private DataTypes getDataType(String token){
        token = token.trim();
        if (token.endsWith("]"))
            return DataTypes.List;
        else if (token.endsWith("}"))
            return DataTypes.Map;
        else if (token.startsWith("\""))
            return DataTypes.String;
        else if (token.toLowerCase().equals("null"))
            return DataTypes.Null;
        else {
            if (token.toLowerCase().equals("true") || token.toLowerCase().equals("false"))
                return DataTypes.Boolean;
            else {
                if (token.contains("."))
                    return DataTypes.Float;
                else
                    return DataTypes.Integer;
            }
        }
    }

    //Returns the full body of a Map (Object) or a List.
    private String getFull(String localData,int type){
        String temp = "";
        int localPointer = 0;
        int cases = 0;
        boolean found = false;
        char opening = (type == 0) ? '{' : '[' ;
        char closing = (type == 0) ? '}' : ']' ;
        while (!found){
            char c = localData.charAt(localPointer++);
            if (c == opening){
                ++ cases;
                temp += c;
            }
            else if (c == closing){
                if (cases == 0) {
                    temp += c;
                    found = true;
                }else{
                    -- cases;
                    temp += c;
                }
            }else temp += c;
        }
        return temp;
    }
}