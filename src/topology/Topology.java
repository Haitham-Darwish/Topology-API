package topology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;    
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Topology implements API{

	private ObjectMapper om = new ObjectMapper();
	public HashMap<String, Result> components_map=new HashMap();

	/**
	 * Read a topology from a given JSON file and store it in the memory
	 * 
	 *@param FileName: The name of the JSON file that we want to read
	 *
	 *@return {@link Result} of the read file
	 */
	@Override
	public Result readJSON(String FileName) {
		
		
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		// The result object that will store the JSON file
		Result result = new Result(); 
		
		// Read the file 
		 try (FileReader reader = new FileReader(FileName))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONObject topology = (JSONObject) obj;
	            
	            // Store the id in the memeory
	            result.setId(topology.get("id").toString());
	            
	            // Store the JSON file 
	            for (int index=0;index<((ArrayList) topology.get("components")).size();index++) {
	            	// Get the type from JSON
	            	String type = ((HashMap)((ArrayList) topology.get("components")).get(index)).get("type").toString();
	            	// Get the id 
	            	String id = ((HashMap)((ArrayList) topology.get("components")).get(index)).get("id").toString();
	            	
	            	if (type.equals("resistor")) {
	            		Resistance resistance=new Resistance(type, id);
	            		// Get the default value
	            		int default_ = Integer.parseInt(((HashMap)((HashMap)((HashMap)((ArrayList) topology.get("components")).get(index))).get("resistance")).get("default").toString()); 
	            		// Get min value
	            		int min = Integer.parseInt(((HashMap)((HashMap)((HashMap)((ArrayList) topology.get("components")).get(index))).get("resistance")).get("min").toString()); 
	            		// Get max value of the passive element
	            		int max = Integer.parseInt(((HashMap)((HashMap)((HashMap)((ArrayList) topology.get("components")).get(index))).get("resistance")).get("max").toString()); 
	                    
	            		// Get the node that the resistor connected with
	            		String t1 = ((HashMap)((HashMap)((HashMap)((ArrayList) topology.get("components")).get(index))).get("netlist")).get("t1").toString(); 
	            		String t2 = ((HashMap)((HashMap)((HashMap)((ArrayList) topology.get("components")).get(index))).get("netlist")).get("t2").toString(); 
	                    
	        			resistance.addResistance(default_, min=min, max=max, t1=t1, t2=t2);
	            		
	            		result.addComponents(resistance);

	            		
	            	}else {
	            		Transistor transistor=new Transistor(type, id);

	            		// Get the default value
	            		double default_ = Double.parseDouble(((HashMap)((HashMap)((HashMap)((ArrayList) topology.get("components")).get(index))).get("m(l)")).get("default").toString()); 
	            		// Get min value
	            		double min = Double.parseDouble(((HashMap)((HashMap)((HashMap)((ArrayList) topology.get("components")).get(index))).get("m(l)")).get("min").toString()); 
	            		// Get max value of the active element
	            		double max = Double.parseDouble(((HashMap)((HashMap)((HashMap)((ArrayList) topology.get("components")).get(index))).get("m(l)")).get("max").toString()); 

	            		// Get the node that the transistor connected with
	            		String drain = ((HashMap)((HashMap)((HashMap)((ArrayList) topology.get("components")).get(index))).get("netlist")).get("drain").toString(); 
	            		String gate = ((HashMap)((HashMap)((HashMap)((ArrayList) topology.get("components")).get(index))).get("netlist")).get("gate").toString(); 
	            		String source = ((HashMap)((HashMap)((HashMap)((ArrayList) topology.get("components")).get(index))).get("netlist")).get("source").toString(); 

	            		// Store transistor in memory
	            		transistor.addTransistor(default_, min, max, drain, gate, source);
	           	
	            		// Add it to other components
	            		result.addComponents(transistor);


	            	}
	            		

	            }
	            	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		 
		 	// Store result in memory
			components_map.put(result.getId(), result);

		return result;
	}

	/**
	 * Overloaded function for the writeJSON
	 */
	@Override
	public Result writeJSON(String TopologyID) {
		// TODO Auto-generated method stub
		return writeJSON(TopologyID, "topology.json");
	}

	/**
	 * Write a given topology from the memory to a JSON file.
	 * 
	 * @param TopologyID: The id of the given topology
	 * 		  FileName: The name of the JSON file that 
	 * 					we want to write in.
	 * 
	 * @return {@link Result}: The topology with TopologyID that we have written in the FileName
	 * */

	@Override
	public Result writeJSON(String TopologyID, String FileName) {
		// TODO Auto-generated method stub
		
		// The string that we want to write in JSON file
		String print = "{\"id\":\"top1\", \"components\": [\r\n";
		
		// Add the component in the print to add it in file 
		for (Component component:components_map.get(TopologyID).getComponents()) {
			// If resistance 
			if (component.getType().equals("resistor"))
				print+="{\"type\":\""+component.getType()+"\",\"id\":\""+component.getId()
						+"\",\"resistance\":"+((Resistance)component).getResistance()+
						",\"netlist\":"+component.getNetlist()+"}";
			else
				print+="{\"type\":"+component.getType()+",\"id\":\""+component.getId()
				+"\",\"m(l)\":"+((Transistor)component).getM()+
				",\"netlist\":"+component.getNetlist()+"}";	
			
			if(!components_map.get(TopologyID).getComponents().get(components_map.get(TopologyID).getComponents().size()-1).equals(component))
				print+=",";
			
		}
		print+="]}";
		//System.out.println("S"+print);
		
		// To make it printed in a pretty way
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement je = JsonParser.parseString(print);
		String prettyJsonString = gson.toJson(je);
		
		/*
		 //Creating a JSONObject object
	      JSONObject jsonObject = new JSONObject();
	      //Inserting key-value pairs into the json object
	      jsonObject.put("Id", TopologyID);
	      System.out.println(prettyJsonString);
	      jsonObject.put("components", List.of(
	    		  "type:resistance", 
	    		  prettyJsonString
	    		  
	    		  ));
	      */
	      try {
	         FileWriter file = new FileWriter(FileName);
	         file.write(prettyJsonString);
	         file.close();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
		
	      
	    // If we want to add the file in one line
		/*
		try {
			om.writerWithDefaultPrettyPrinter().writeValue(new File(FileName), components_map.get(TopologyID));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return components_map.get(TopologyID);
	}
	
	
	/**
	 * Query about which topologies are currently in the memory.
	 * 
	 * @return {@link ArrayList}<{@link Result}>: ArrayList of the topologies that are in memory
	 * 
	 * */

	@Override
	public ArrayList<Result> queryTopologies() {
		// TODO Auto-generated method stub
		
		// Make a list to store the topologies in it
		ArrayList<Result> result = new ArrayList<Result>();
		
		// Loop to add all topology
		for (String topology: components_map.keySet())
			result.add(components_map.get(topology));
		return result;
	}
	
	/**
	 * Delete a given topology from memory.
	 * 
	 * @param TopologyID: The id of the given topology
	 * 
	 * @return {@link Result}: The topology that was deleted
	 * */
	@Override
	public Result deleteTopology(String TopologyID) {
		// TODO Auto-generated method stub
		return components_map.remove(TopologyID);
	}

	/**
	 * Query about which devices are in a given topology.
	 * 
	 * @param TopologyID: The id of the given topology
	 * 
	 * @return ArrayList<String>: List of devices that are in the memory
	 * */
	@Override
	public ArrayList<String> queryDevices(String TopologyID) {
		// TODO Auto-generated method stub
		ArrayList<String> device = new ArrayList<String>();
		for (Component component:components_map.get(TopologyID).getComponents())
			device.add(component.getType());
			
		return device;
	}

	/**
	 * Query about which devices are connected to a given netlist node in a given topology.
	 * 
	 * @param TopologyID: The id of the given topology
	 * 		  NetlistNodeID: The id of the node
	 * 
	 * @return ArrayList<String>: List of devices that are connected with this node
	 * 
	 * */
	@Override
	public ArrayList<String> queryDevicesWithNetlistNode(String TopologyID, String NetlistNodeID) {
		// TODO Auto-generated method stub
		ArrayList<String> device = new ArrayList<String>();
		for (Component component:components_map.get(TopologyID).getComponents())
			if(component.getNetlist().containsValue(NetlistNodeID))
				device.add(component.getType());					
		return device;
	}
	
}
