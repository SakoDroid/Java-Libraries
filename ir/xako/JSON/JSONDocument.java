package ir.xako.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONDocument {

    private ArrayList content1 = null;
    private HashMap content2 = null;

    public JSONDocument(ArrayList data){
        this.content1 = data;
    }

    public JSONDocument(HashMap data){
        this.content2 = data;
    }

    public Serializable toJava(){
        if (content1 != null)
            return this.content1;
        else
            return this.content2;
    }

}
