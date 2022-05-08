package topology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;    
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainActivity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Topology topology=new Topology();
		Result result = topology.readJSON("topology.json");
		result = topology.readJSON("top2.json");
		System.out.println(result);


		result = topology.writeJSON("top1", "output.json");
		result = topology.readJSON("output.json");
		System.out.println("R"+result);
		
		
		System.out.println(topology.components_map);
		System.out.println(result);
		
		ArrayList<Result> result_arr= topology.queryTopologies();
		System.out.println(result_arr);
		
		result = topology.deleteTopology("top1");
		result_arr= topology.queryTopologies();
		System.out.println(result_arr.get(0));
		
		ArrayList<String> device=topology.queryDevices("top2");
		System.out.println(device.contains("resistor"));
		
		device=topology.queryDevicesWithNetlistNode("top2", "n1");
		System.out.println(device);

/*
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader("topology.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONObject employeeList = (JSONObject) obj;
             System.out.println(((HashMap)((HashMap)((ArrayList) employeeList.get("components")).get(0)).get("netlist")).get("t1"));
            //Iterate over employee array
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    
		   //Object obj=JSONValue.parse(s);  
		ObjectMapper om = new ObjectMapper();
		
		
		Car c1=new Car(2019, "Fork");
		Car c2=new Car(2017, "Probe");
		Car c3=new Car(1925, "Mustage");
		Car c4=new Car(1935, "Chevy");
		System.out.println(c1);
		
		
		ArrayList<Car> someList = new ArrayList<Car>();
		someList.add(c1);
		someList.add(c2);
		someList.add(c3);
		someList.add(c4);
		CarList cl = new CarList(someList);
		
		HashMap<String, String> m=new HashMap();
		m.put("type", "resistor");
		m.put("id", "res1");
		
		ArrayList<HashMap<String, String>> someList2 = new ArrayList<HashMap<String, String>>();
		someList2.add(m);
		Transistor transistor=new Transistor("nmos", "m1");
		
		try {
			transistor.addTransistor(1.5, 1, 2, "n1", "vin", "vss");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Result api=new Result("top1");

		api.addComponents(transistor);
		
		Resistance resistance=new Resistance("resistor", "res1");
		
		try {
			resistance.addResistance(100, 10, 1000, "vdd", "n1");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		api.addComponents(resistance);
		
		Result a=new Result();
		try {
			a=om.readerFor(Result.class).readValue(new File("car.json"));
			om.writerWithDefaultPrettyPrinter().writeValue(new File("car.json"), api);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a);
		*/
		
		
		
		
		
		
	}
	
	

}
