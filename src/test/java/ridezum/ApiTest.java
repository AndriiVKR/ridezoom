package ridezum;

import org.apache.http.util.Asserts;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ApiTest {

    @Test
    public void testApi() {

        try {

            URL url = new URL("http://api.zippopotam.us/us/98208");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("Accept", "application/json");

//            int responseCode = connection.getResponseCode();

//            System.out.println(responseCode);

            if (connection.getResponseCode() != 200) {

//                System.out.println(connection.getResponseCode());

                throw new RuntimeException("ERROR response code: " + connection.getResponseCode());

            }

            System.out.println(connection.getResponseCode());

            Scanner scanner = new Scanner(url.openStream());

            String response = new String();

            while (scanner.hasNext()) {
                response = scanner.nextLine();
            }

            System.out.println(response);

            scanner.close();

             JSONObject obj = new JSONObject(response);

             String postCode = obj.get("post code").toString();

             Assert.assertEquals("98208", postCode);

            System.out.println(postCode);

            String country = obj.get("country").toString();

            Assert.assertEquals("United States", country);

            System.out.println(country);

            String countryAbbreviation = obj.get("country abbreviation").toString();

            Assert.assertEquals("US", countryAbbreviation);

            System.out.println(countryAbbreviation);

            JSONArray array = obj.getJSONArray("places");

            String city = array.getJSONObject(0).getString("place name");

            System.out.println(city);

            Assert.assertEquals("Everett", city);

            String longitude = array.getJSONObject(0).getString("longitude");

            System.out.println(longitude);

            Assert.assertEquals("-122.1987", longitude);

            String state = array.getJSONObject(0).getString("state");

            System.out.println(state);

            Assert.assertEquals("Washington", state);

            String stateAbbreviation = array.getJSONObject(0).getString("state abbreviation");

            System.out.println(stateAbbreviation);

            Assert.assertEquals("WA", stateAbbreviation);

            String latitude = array.getJSONObject(0).getString("latitude");

            System.out.println(latitude);

            Assert.assertEquals("47.8948", latitude);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

