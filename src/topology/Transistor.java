package topology;

import java.util.HashMap;

public class Transistor extends Component{
	

	/**
	 * Constructor
	 */
	public Transistor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transistor(String type, String id) {
		super(type, id);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get the transistor values
	 * @return transistor values
	 */
	public HashMap<String, Double> getM() {
		return m;
	}

	/**
	 * Set the transistor values
	 * @param m transistor values
	 */
	public void setM(HashMap<String, Double> m) {
		this.m = m;
	}

	/**
	 * 
	 * Add new transistor to the memory
	 * 
	 * @param d default value of the transistor if we didn't specify
	 * @param min minimum value of the transistor that can't be put less than it
	 * @param max maximum value of the transistor that can't be put more than it
	 * @param drain the drain of the MOS transistor
	 * @param gate the gate node of the MOS transistor
	 * @param source the source node of the MOS transistor
	 */
	
	public void addTransistor(double d, double min, double max,
			String drain, String gate, String source) {

			
			m.put("default", d);
			m.put("min", min);
			m.put("max", max);
			
			netlist.put("drain", drain);
			netlist.put("gate", gate);
			netlist.put("source", source);
	}
	
	
	@Override
	public String toString() {
	        return "{"+
	        		"m(l)=" + m	+
	                ", netlist=" + netlist +
	                '}';
	                
	    }
	
	
}
