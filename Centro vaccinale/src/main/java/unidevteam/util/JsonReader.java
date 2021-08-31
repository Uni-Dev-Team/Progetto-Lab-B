package unidevteam.util;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileNotFoundException;


public class JsonReader {
    JSONParser parser;

    public JsonReader() {
        parser = new JSONParser();
    }

    public List<String> getProvince() {
        List<String> province = new ArrayList<String>();

        try
            {
                Object object;
                try{
                    object = parser.parse(new FileReader("Centro vaccinale/src/main/resources/italia.json"));
                } catch (FileNotFoundException e) {
                    object = parser.parse(new FileReader("src/main/resources/italia.json"));
                }
                //convert Object to JSONObject
                JSONObject jsonObject = (JSONObject)object;
                
                JSONArray regioni = (JSONArray) jsonObject.get("regioni");

                for (Object regione : regioni) {
                    //Printing all the values
                    JSONObject regioneObject = (JSONObject)regione;
                    JSONArray provinceJSONArr = (JSONArray) regioneObject.get("province");
                    JSONArray capoluoghiJSONArr = (JSONArray) regioneObject.get("capoluoghi");
                    
                    for(int i = 0; i < provinceJSONArr.size(); i++) {
                        String record = String.format("%s (%s)", capoluoghiJSONArr.get(i), provinceJSONArr.get(i));
                        province.add(record);
                    }
                }
                
                java.util.Collections.sort(province);
                
            } catch(Exception e) {
                e.printStackTrace();
            }

            return province;
    }

    public List<String> getComuniByProvincia(String siglaProvincia) {
        List<String> comuni = new ArrayList<String>();

        try
            {
                Object object;
                try{
                    object = parser.parse(new FileReader("Centro vaccinale/src/main/resources/italia_comuni.json"));
                } catch (FileNotFoundException e) {
                    object = parser.parse(new FileReader("src/main/resources/italia_comuni.json"));
                }
                //convert Object to JSONObject
                JSONObject jsonObject = (JSONObject)object;
                
                JSONArray regioni = (JSONArray) jsonObject.get("regioni");

                for (Object regione : regioni) {
                    //Printing all the values
                    JSONObject regioneObject = (JSONObject)regione;
                    JSONArray provinceJSONArr = (JSONArray) regioneObject.get("province");

                    for(Object provincia : provinceJSONArr) {
                        JSONObject provinciaJSONobj = (JSONObject)provincia;
                        if(provinciaJSONobj.get("code").toString().equals(siglaProvincia)) {
                            JSONArray comuniJSONArr = (JSONArray)provinciaJSONobj.get("comuni");

                            for(Object comune : comuniJSONArr) {
                                JSONObject comuneJSONobj = (JSONObject)comune;
                                comuni.add(comuneJSONobj.get("nome").toString());
                            }

                            java.util.Collections.sort(comuni);
                            return comuni;
                        }
                    }
                }
                
            } catch(Exception e) {
                e.printStackTrace();
            }

        return null;
    }

    public String getCAPfromComune(String nomeComune) {
        try {
            Object object;
            try{
                object = parser.parse(new FileReader("Centro vaccinale/src/main/resources/italia_comuni.json"));
            } catch (FileNotFoundException e) {
                object = parser.parse(new FileReader("src/main/resources/italia_comuni.json"));
            }
            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject)object;
            
            JSONArray regioni = (JSONArray) jsonObject.get("regioni");

            for (Object regione : regioni) {
                //Printing all the values
                JSONObject regioneObject = (JSONObject)regione;
                JSONArray provinceJSONArr = (JSONArray) regioneObject.get("province");

                for(Object provincia : provinceJSONArr) {
                    JSONObject provinciaJSONobj = (JSONObject)provincia;
                    JSONArray comuniJSONArr = (JSONArray)provinciaJSONobj.get("comuni");

                    for(Object comune : comuniJSONArr) {
                        JSONObject comuneJSONobj = (JSONObject)comune;
                        if(comuneJSONobj.get("nome").toString().equals(nomeComune)) {
                            return comuneJSONobj.get("cap").toString();
                        }
                    }
                }
            }
                
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}