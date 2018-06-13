package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Main {

    public static int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static String TEST_XML_STRING =
        "<xml>C:\\Users\\shama.ugale\\Documents\\Export script and output xmls\\output\\projects.xml</xml>";

    public static void main(String[] args) {
        try {
        	
        	String[] s= new String[2];
        	s[0]="hi";
        	s[1]="world";
        	
        	
        	
        	List l= new ArrayList<>();
        	l.add("hello");
        	l=Arrays.asList(s);
        	int array[]={3,5,7,8};
        	List lArray=Arrays.asList(array);
        	System.out.println(lArray);
        	l.add(45);
        	System.out.println(l);
        	System.out.println(l);
            JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            System.out.println(jsonPrettyPrintString);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
    }
}