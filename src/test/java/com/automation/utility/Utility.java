package com.automation.utility;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;

import com.automation.base.SerenityBase;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.restassured.config.EncoderConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Utility extends SerenityBase {
    public static JSONArray responseAsJsonArray;
    public static EncoderConfig encoderconfig = new EncoderConfig();

    /**
     * @author Mohit Bhatt
     * @description Method to alter the desired key values in the JSON and
     *              returning the JSON as string
     * @return alteredJson as string
     */
    public static synchronized String alterJson(String jsonName,
            Map<String, String> jsonValues) {
        String jsonString = null;
        try {
            URL file = Resources.getResource(
                    "configFiles/jsonFiles/RequestJson/" + jsonName + ".txt");
            jsonString = Resources.toString(file, Charsets.UTF_8);

            for (Map.Entry<String, String> keyVal : jsonValues.entrySet()) {
                jsonString = jsonString.replaceAll(keyVal.getKey(),
                        keyVal.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }


       /**
     * @author Mohit.Bhatt
     * 
     * @param headers
     * @param queryParam
     * @param jsonbody
     * @param URL
     * @param contentType
     * @return
     * @description post call with query parameter
     */


    public static void validateJsonSchema(Response response, String jsonName) {
        try {
            InputStream inputStream = response.asInputStream();
            JSONObject reponseSchema = new JSONObject(
                    new JSONTokener(inputStream));
            JSONObject expectedJsonSchema = new JSONObject(
                    new JSONTokener(Resources.getResource(
                            "configFiles/jsonFiles/ResponseJsonSchema/"
                                    + jsonName + ".txt")
                            .openStream()));
            Schema schema = SchemaLoader.load(expectedJsonSchema);
            schema.validate(reponseSchema);
        } catch (Exception e) {
            Assert.assertFalse(
                    "Error while validating response json schema: " + e, true);

        }
    }


    public static String readJson(String jsonName) {
        try {
            URL file = Resources.getResource(
                    "configFiles/jsonFiles/RequestJson/" + jsonName + ".txt");
            String jsonString = Resources.toString(file, Charsets.UTF_8);
            return jsonString;
        } catch (Exception e) {

            System.out.println("Error while altering json : " + e);
            return null;
        }
    }

    public static String createEndPoint(String base_url, String ApiEndpoint) {
        System.out.println(base_url + ApiEndpoint);
        return base_url + ApiEndpoint;
    }

    public static String readResponseJsonSchema(String jsonSchema) {
        try {
            URL file = Resources
                    .getResource("configFiles/jsonFiles/ResponseJsonSchema/"
                            + jsonSchema + ".json");
            String jsonString = Resources.toString(file, Charsets.UTF_8);
            return jsonString;
        } catch (Exception e) {

            System.out.println("Error while altering json : " + e);
            return null;
        }
    }

    /**
     * @description Method to get all the response headers
     * @param response
     * @return responseHeaders
     */
    public Headers getResponseHeaders(Response response) {
        Headers responseHeaders = response.getHeaders();
        return responseHeaders;
    }

   
    public static Object RemoveKeyFromJson(String JsonFileName, String JPath) {
        String jsonString = null;
        JSONObject ReqObj = null;
        try {
            Object document = Configuration.defaultConfiguration()
                    .jsonProvider().parse(JsonFileName);
            DocumentContext UpdateJson = com.jayway.jsonpath.JsonPath
                    .parse(document).delete(JPath);
            ReqObj = new JSONObject(UpdateJson.jsonString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReqObj;
    }

    public static Object RemoveKeyFromJsonFile(String JsonFileName,
            String JPath) {
        String jsonString = null;
        JSONObject ReqObj = null;
        try {
            URL file = Resources
                    .getResource("configFiles/jsonFiles/RequestJson/"
                            + JsonFileName + ".txt");
            jsonString = Resources.toString(file, Charsets.UTF_8);
            Object document = Configuration.defaultConfiguration()
                    .jsonProvider().parse(jsonString);
            DocumentContext UpdateJson = com.jayway.jsonpath.JsonPath
                    .parse(document).delete(JPath);
            ReqObj = new JSONObject(UpdateJson.jsonString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReqObj;
    }

    /**
     * @author mohit.bhatt
     * @param JsonFileName
     * @param JPath
     * @param KeyValue
     * @return
     */
    public static Object AddKeyInJson(String JsonFileName, String JPath) {
        String jsonString = null;
        JSONObject ReqObj = null;
        try {
            URL file = Resources
                    .getResource("configFiles/jsonFiles/RequestJson/"
                            + JsonFileName + ".txt");
            jsonString = Resources.toString(file, Charsets.UTF_8);
            Object document = Configuration.defaultConfiguration()
                    .jsonProvider().parse(jsonString);

            DocumentContext UpdateJson = com.jayway.jsonpath.JsonPath
                    .parse(document).put(JPath.split("-")[0],
                            JPath.split("-")[1], JPath.split("-")[2]);
            ReqObj = new JSONObject(UpdateJson.jsonString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReqObj;
    }

    /**
     * @param JsonFileName
     * @param JPath
     * @param KeyValue
     * @return
     */
    public static String GetKeyValueFromJson(String Json, String JPath) {
        Object object = null;
        try {
            Object document = Configuration.defaultConfiguration()
                    .jsonProvider().parse(Json);
            object = JsonPath.read(document, JPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public Map<String, String> getMapOfJsonPath(Class cls) {
        Map<String, String> jsonPathMap = new HashMap<String, String>();
        try {

            for (Field fields : cls.getFields()) {
                jsonPathMap.put(fields.getName(),
                        fields.get(fields.getName()).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonPathMap;
    }
    

    public static void putVariablesInMap(Class clz) throws Exception {
        Field[] fields = clz.getFields();
        for (Field field : fields) {
            PropertyHolder.setProperty(field.getName(),
                    (String) field.get(field));
        }
    }

}
