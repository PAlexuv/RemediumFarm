package Utils;

import TestsRF.BaseForTestingRF;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataProviderMethod extends BaseForTestingRF {

    @DataProvider
    public Object[] dpRegisterValidInput(){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;

        Object obj = null;
        try{
            obj = parser.parse(new FileReader("src/main/resources/validRegInputs.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jsonObject = (JSONObject) obj;
        assert jsonObject!=null;

        JSONArray registerInfo = (JSONArray) jsonObject.get("registerValidDataRF");
        String[] dataArray = new String[registerInfo.size()];

        JSONObject registerInfoData;
        String prenume, nume, telefon, email, parola, confirmParola;

        for(int i=0; i<registerInfo.size(); i++){
            registerInfoData = (JSONObject) registerInfo.get(i);

            prenume = (String) registerInfoData.get("prenumeField");
            nume = (String) registerInfoData.get("numeField");
            telefon = (String) registerInfoData.get("telefonField");
            email = (String) registerInfoData.get("emailField");
            parola = (String) registerInfoData.get("parolaField");
            confirmParola = (String) registerInfoData.get("confParolaField");
            dataArray[i] = prenume + "," + nume + "," + telefon + "," + email + "," + parola + "," + confirmParola;
        }
        return dataArray;
    }

    @DataProvider
    public Object[] dpLoginValidInput(){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;

        Object obj = null;
        try{
            obj = parser.parse(new FileReader("src/main/resources/validLoginInputs.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jsonObject = (JSONObject) obj;
        assert jsonObject!=null;

        JSONArray loginInfo = (JSONArray) jsonObject.get("loginValidDataRF");
        String[] dataArray = new String[loginInfo.size()];

        JSONObject loginInfoData;
        String username, parola;

        for(int i=0; i<loginInfo.size(); i++){
            loginInfoData = (JSONObject) loginInfo.get(i);

            username = (String) loginInfoData.get("emailField");
            parola = (String) loginInfoData.get("parolaField");

            dataArray[i] = username + "," + parola;
        }
        return dataArray;
    }

}
