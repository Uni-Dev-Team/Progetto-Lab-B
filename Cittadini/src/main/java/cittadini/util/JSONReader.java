/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package cittadini.util;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileNotFoundException;

/**
 * Classe per leggere e lavorare da file JSON
 * Usato per il file italia.json e per italia_comuni.json
 * Questi file ci permettono di ottenere informazioni sui comuni e province italiane 
 */
public class JSONReader {
    JSONParser parser;

    public JSONReader() {
        parser = new JSONParser();
    }

    /**
     * 
     * @return Una lista di tutte le province italiane già ordinate
     */
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
                JSONObject jsonObject = (JSONObject)object;
                
                JSONArray regioni = (JSONArray) jsonObject.get("regioni");

                for (Object regione : regioni) {
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

    /**
     * Ottiene comuni tramite la sigla della provincia
     * @param siglaProvincia sigla della provincia 
     * @return una lista di comuni sotto forma di stringa
     */
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
                // conversione da oggetto a oggetto JSON
                JSONObject jsonObject = (JSONObject)object;
                
                JSONArray regioni = (JSONArray) jsonObject.get("regioni");

                for (Object regione : regioni) {
                    // iterazione su tutte le regioni
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

    /**
     * @param nomeComune nome del comune
     * @return il CAP sotto forma di stringa
     */
    public String getCAPfromComune(String nomeComune) {
        try {
            Object object;
            try{
                object = parser.parse(new FileReader("Centro vaccinale/src/main/resources/italia_comuni.json"));
            } catch (FileNotFoundException e) {
                object = parser.parse(new FileReader("src/main/resources/italia_comuni.json"));
            }
            // conversione da oggetto a oggetto JSON
            JSONObject jsonObject = (JSONObject)object;
            
            JSONArray regioni = (JSONArray) jsonObject.get("regioni");

            for (Object regione : regioni) {
                // iterazione su tutte le regioni
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

    /**
     * @return una lista di comuni ordinati
     */
    public List<String> getAllComuni() {
        List<String> comuni = new ArrayList<String>();

        try {
                Object object;
                try{
                    object = parser.parse(new FileReader("Cittadini/src/main/resources/italia_comuni.json"));
                } catch (FileNotFoundException e) {
                    object = parser.parse(new FileReader("src/main/resources/italia_comuni.json"));
                }
                // conversione da oggetto a oggetto JSON
                JSONObject jsonObject = (JSONObject)object;
                
                JSONArray regioni = (JSONArray) jsonObject.get("regioni");

                for (Object regione : regioni) {
                    // iterazione su tutte le regioni
                    JSONObject regioneObject = (JSONObject)regione;
                    JSONArray provinceJSONArr = (JSONArray)regioneObject.get("province");

                    for(Object provincia : provinceJSONArr) {
                        JSONObject provinciaJSONobj = (JSONObject)provincia;
                        JSONArray comuniJSONArr = (JSONArray)provinciaJSONobj.get("comuni");

                        for(Object comune : comuniJSONArr) {
                            JSONObject comuneJSONobj = (JSONObject)comune;
                            comuni.add(comuneJSONobj.get("nome").toString());
                        }
                    }
                }

                java.util.Collections.sort(comuni);
                
            } catch(Exception e) {
                e.printStackTrace();
            }

        return comuni;
    }
}