
 package test.Module7_Phase2.src;
import java.util.Hashtable;
import java.util.List;


public class Client2 {

	public static void main(String[] args) {
		
		XMLReader xml = new XMLReader("C:\\Users\\shama.ugale\\Documents\\Export script and output xmls\\output\\projects.xml");
	//	xml.getDataAsList("LoginTest");
		System.out.println("************************************************************-------------------------------------");
		List<Hashtable<String,String>> data = xml.getDataAsList("Project");
		System.out.println(data);

		System.out.println("************************************************************8");

		Object[][] data1 = xml.getDataAsArray("LoginTest");
		System.out.println(data1);
	}

}
