package topology;

import java.util.HashMap;

public class Resistance extends Component{

	/**
	 * The constructor 
	 */
	
	public Resistance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Resistance(String type, String id) {
		super(type, id);
		// TODO Auto-generated constructor stub
	}

	/*
	 * Get the resistance hashmap
	 */
	public HashMap<String, Integer> getResistance() {
		return resistance;
	}

	/**
	 * Set the resistance HashMap
	 * 
	 * @param resistance the HashMap that we want to set our map to
	 */
	public void setResistance(HashMap<String, Integer> resistance) {
		this.resistance = resistance;
	}

	/**
	 * 
	 * Add new resistance to the memory
	 * 
	 * @param default_ default value of the resistance if we didn't specify
	 * @param min minimum value of the resistance that can't be put less than it
	 * @param max maximum value of the passive element that can't be put more than it
	 * @param t1 the first node 
	 * @param t2 the second node that our two terminal device will be connected to it.
	 */
	public void addResistance(int default_, int min, int max,
			String t1, String t2) {

		resistance.put("default", default_);
		resistance.put("min", min);
		resistance.put("max", max);
			
			netlist.put("t1", t1);
			netlist.put("t2", t2);
			
	}
	
	@Override
	public String toString() {
	        return "{"+
	        		"resistance=" + resistance	+
	                ", netlist=" + netlist +
	                '}';
	                
	    }
	
}
