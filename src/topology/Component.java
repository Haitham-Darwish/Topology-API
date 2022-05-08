package topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Component{
	
	String type;
	String id;
	
	  protected HashMap<String, String> netlist=new HashMap();
	  protected HashMap<String, Integer> resistance =  new HashMap<String, Integer>();;
	  protected HashMap<String, Double> m = new HashMap<String, Double>();;

	 
	
	public HashMap<String, String> getNetlist() {
		return netlist;
	}
	

	public void setNetlist(HashMap<String, String> netlist) {
		this.netlist = netlist;
	}
	
	public Component() {
    }
	
	public Component(String type, String id) {
		this.type = type;
		this.id = id;        
    }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    /*
	public void addComponent(String name, String default_, String min, String max,
			String drain, String gate, String source) throws Exception {
		if (type.equals("resistor"))
			throw new Exception("The type is resistor not transitor");
		
		addTransistor(default_, min, max,
				 drain, gate, source);
		
		component.put(name, m);
	}
	
	public void addComponent(String name, String default_, String min, String max,
			String t1, String t2) throws Exception {
		if (!type.equals("resistor"))
			throw new Exception("The type is transitor not resistor");
		
		addResistance(default_, min, max,
				 t1, t2);
		component.put(name, resistance);
		
	}
	
	public void addTransistor(String d, String min, String max,
			String drain, String gate, String source) {

			
			m.put("default", d);
			m.put("min", min);
			m.put("max", max);
			
			netlist.put("drain", drain);
			netlist.put("gate", gate);
			netlist.put("source", source);
	}
	
	public void addResistance(String default_, String min, String max,
			String t1, String t2) {

		resistance.put("default", default_);
		resistance.put("min", min);
		resistance.put("max", max);
			
			netlist.put("t1", t1);
			netlist.put("t2", t2);
			
			
	}
	/*/
    
	

	
	
	  

}
