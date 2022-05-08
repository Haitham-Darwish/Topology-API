package topology;

import java.util.*;
public class Result {

	  String id;
	  ArrayList<Component> components=new ArrayList<Component>();

	  @Override
		public String toString() {
		        return "{"+
		        		"id=" + id	+
		                ", components=" + components +
		                '}';
		                
		    }
	  /**
	   *  Constructor
	   * **/
	  public Result(String id, Component  components) {
	        this.id = id;
	        this.components.add(components);
	    }

	    public Result(String id) {
	    	this.id = id;
	    	//this(id, new ArrayList());	    	
	    }
	    
	    public Result() {

	    }
	    
	    /**
	     * Get the id
	     * 
	     * @return id of the given topology
	     */
	public String getId() {
		return id;
	}

	/**
	 * Set the id of the given topology
	 * 
	 * @param id that we want to change to
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * Set the component list that stores our topologies in memory
	 * @param components
	 */
	public void setComponents(ArrayList<Component> components) {
		this.components = components;
	}
	
	/**
	 * Get the component
	 * @return the topology
	 */
	public ArrayList<Component> getComponents() {
	        return components;
	    }

	/**
	 * Add new topology
	 * @param components the component of the topology(resistance or transistor, netlist, type, id...etc)
	 */
	public void addComponents(Component components) {
	        this.components.add(components);
	    }
	    
	   

	    
	
}
