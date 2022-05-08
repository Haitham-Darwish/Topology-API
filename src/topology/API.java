package topology;

import java.util.ArrayList;

public interface API {

	/**Read a topology from a given JSON file and store it in the memory*/
	public Result readJSON(String FileName);
	/**Write a given topology from the memory to a JSON file.*/
	public Result writeJSON(String TopologyID);
	public Result writeJSON(String TopologyID, String FileName);
	/**Query about which topologies are currently in the memory.*/
	public ArrayList<Result> queryTopologies();
	/**Delete a given topology from memory.*/
	public Result deleteTopology(String TopologyID);
	/**Query about which devices are in a given topology.*/
	public ArrayList<String> queryDevices(String TopologyID);
	/**Query about which devices are connected to a given netlist node in a given topology.*/
	public ArrayList<String> queryDevicesWithNetlistNode(String TopologyID, String NetlistNodeID);
	
}
