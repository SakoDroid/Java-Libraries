package ir.xako.JSON;

import java.util.List;
import java.util.Map;

public class JSONDocument {

    private List content1 = null;
    private Map content2 = null;

    /**
     * Creates a JSONDocument from given ArrayList.
     *
     * @param data List that contains the data.
     */
    public JSONDocument(List data){
        this.content1 = data;
    }

    /**
     * Creates a JSONDocument from given Map.
     *
     * @param data Map that contains the data.
     */
    public JSONDocument(Map data){
        this.content2 = data;
    }

    /**
     * This method converts the JSONDocument into java usable.
     * According to the JSON data, you can cast the output of this method into Map or List.
     *
     * @<code> ArrayList al = (ArrayList) jd.toJava();
     * HashMap hm = (HashMap) jd.toJava();</code>
     *
     * @return An Object that is either a Map or List.
     */
    public Object toJava(){
        if (content1 != null)
            return this.content1;
        else
            return this.content2;
    }

}
