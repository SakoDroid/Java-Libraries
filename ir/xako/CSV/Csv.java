package ir.xako.CSV;

import java.io.*;
import java.util.*;

/*
** Written By : Sako
** Nov 17 2020 15:46:25 IRST
 */

public class Csv {

    private String rawData = "";
    private String[] rows;
    private String fileName;

    public Csv(String filename){
        this.fileName = filename;
        try{
            File fl = new File(fileName);
            if (!fl.exists()) fl.createNewFile();
            else{
                FileReader rd = new FileReader(filename);
                int i;
                while((i = rd.read()) != -1){
                    this.rawData += (char)i;
                }
            }
            rows = rawData.split("\n");
        }catch(IOException ex){
            System.out.println(ex.toString());
        }
    }

    public Csv(String filename,int limit){
        this.fileName = filename;
        try{
            File fl = new File(fileName);
            if (!fl.exists()) fl.createNewFile();
            else{
                FileReader rd = new FileReader(filename);
                int i;
                while((i = rd.read()) != -1){
                    this.rawData += (char)i;
                }
            }
            rows = rawData.split("\n",limit);
        }catch(IOException ex){
            System.out.println(ex.toString());
        }
    }

    public static String toCsvStructure(String[] row){
        String out = "";
        for (String s : row) out += s + ",";
        return out.substring(0,out.length()-1);
    }

    public String[] getTitles(){
        return rawData.split("\n")[0].split(",");
    }

    public int getRowsLen(){
        return rows.length;
    }

    public String[] getRow(int row){
        if (row < 0) row = rows.length + row;
        return rows[row].split(",");
    }

    public String[][] getRowsArray(){
        String []p = rawData.split("\n");
        String[][] out = new String[p.length][p[0].split(",").length];
        for(int i = 0 ; i < p.length ; i++){
            out[i] = p[i].split(",");
        }
        return out;
    }

    public ArrayList getRowsArrayList(){
        ArrayList out = new ArrayList();
        String[] temp = rawData.split("\n");
        for (String s : temp){
            ArrayList tmp = new ArrayList();
            String[] h = s.split(",");
            Collections.addAll(tmp, h);
            out.add(tmp);
        }
        return out;
    }

    public HashMap getRowMap(int row){
        if (row < 0) row = rows.length + row;
        if (row == 0) row = 1;
        HashMap out = new HashMap();
        String[] rw = rows[row].split(",");
        String[] heads = getTitles();
        for (int i = 0 ; i < rw.length ; i++){
            out.put(heads[i],rw[i]);
        }
        return out;
    }

    public HashMap[] getRowsMap(){
        HashMap[] out = new HashMap[rows.length];
        String[] heads = getTitles();
        for (int j = 1 ; j < rows.length ; j++){
            String[] temp = rows[j].split(",");
            for (int i = 0 ; i < temp.length ; i++){
                HashMap mn = new HashMap();
                mn.put(heads[i],temp[i]);
                out[j] = mn;
            }
        }
        return out;
    }

    public void writeCsv(String[][] rows,boolean append){
        try(FileWriter fw = new FileWriter(this.fileName,append)){
            String prt = "";
            for (String[] row : rows){
                prt += toCsvStructure(row) + "\n";
            }
            fw.write(prt.substring(0,prt.length()-1));
        }catch (IOException ex){
            System.out.println(ex.toString());
        }
    }

    public void writeCsvMap(Map[] rows,boolean append){
        Set titles = rows[0].keySet();
        String prt = "";
        for (Object tt : titles){
            prt += tt + ",";
        }
        prt = prt.substring(0,prt.length()-1);
        for(Map row : rows){
            prt += "\n";
            for (Object ttl : titles){
                prt += row.get(ttl) + ",";
            }
            prt = prt.substring(0,prt.length()-1);
        }
        try(FileWriter fw = new FileWriter(fileName,append)){
            fw.write(prt);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

}