package topology;

import java.util.HashMap;

public class Component{
	
	// To add the type of the component
	String type;
	// To add the id of component
	String id;
	
	// The HashMap that we will need to add the netlist to memory
	protected HashMap<String, String> netlist=new HashMap<String, String>();
	// The HashMap that we will need to add the resistance to memory
	protected HashMap<String, Integer> resistance =  new HashMap<String, Integer>();;
	// The HashMap that we will need to add the transistor to memory
	protected HashMap<String, Double> m = new HashMap<String, Double>();;

	/**
	 * Get the netlist 
	 * @return HashMap<String, String> of the netlist
	 * 
	 */
	public HashMap<String, String> getNetlist() {
		return netlist;
	}
	

	/**
	 * Set the netlist
	 * @param netlist the netlist that we want to set
	 */
	public void setNetlist(HashMap<String, String> netlist) {
		this.netlist = netlist;
	}
	
	/**
	 * Constructor
	 */
	public Component() {
    }
	
	public Component(String type, String id) {
		this.type = type;
		this.id = id;        
    }
	
	/**
	 * Get the id 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the id
	 * @param id that we want to set
	 */
	public void setId(String id) {
		this.id = id;
	}


    /**
     * Get the type of the component
     * @return type of the component
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of the component
     * @param type that we want to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
