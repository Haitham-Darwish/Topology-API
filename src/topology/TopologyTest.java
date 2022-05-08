package topology;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TopologyTest {
	
	Topology topology=new Topology();

	@Test
	void testRead() {
		
		Result result = topology.readJSON("topology.json");
		
		// Check for correct Id
		assertEquals("top1", result.getId());

		// Check for resistance Id
		assertEquals("res1", result.getComponents().get(0).getId());
		
		// Check for transistor Id
		assertEquals("m1", result.getComponents().get(1).getId());
		
		// Check for resistance type
		assertEquals("resistor", result.getComponents().get(0).getType());
		
		// Check for transistor type
		assertEquals("nmos", result.getComponents().get(1).getType());
		
		// Check for resistance netlist
		assertEquals("vdd", result.getComponents().get(0).getNetlist().get("t1"));
		
		// Check for transistor netlist
		assertEquals("vin", result.getComponents().get(1).getNetlist().get("gate"));
		
		// Check for resistance
		assertEquals(10, ((Resistance)result.getComponents().get(0)).getResistance().get("min"));
		
		// Check for transistor
		assertEquals(2, ((Transistor)result.getComponents().get(1)).getM().get("max"));


		// Check for size
		assertEquals(1, topology.components_map.size());
		result = topology.readJSON("top2.json");
		// Check for size
		assertEquals(2, topology.components_map.size());
		
		
		//fail("Not yet implemented");
	}

	@Test
	void testWrite() {
		 topology.readJSON("topology.json");
		
		 Result result = topology.writeJSON("top1", "output.json");
		
		// Check for correct Id
		assertEquals("top1", result.getId());

		// Check for resistance Id
		assertEquals("res1", result.getComponents().get(0).getId());
				
		// Check for transistor Id
		assertEquals("m1", result.getComponents().get(1).getId());
				
		// Check for resistance type
		assertEquals("resistor", result.getComponents().get(0).getType());
				
		// Check for transistor type
		assertEquals("nmos", result.getComponents().get(1).getType());
				
		// Check for resistance netlist
		assertEquals("vdd", result.getComponents().get(0).getNetlist().get("t1"));
				
		// Check for transistor netlist
		assertEquals("vin", result.getComponents().get(1).getNetlist().get("gate"));
				
		// Check for resistance
		assertEquals(10, ((Resistance)result.getComponents().get(0)).getResistance().get("min"));
				
		// Check for transistor
		assertEquals(2, ((Transistor)result.getComponents().get(1)).getM().get("max"));

	}
	
	@Test
	void testQueryTopologies() {
		// Read first topology
		Result result = topology.readJSON("topology.json");
		// Read second topology
		result = topology.readJSON("top2.json");
		
		// ArrayList to store the topologies
		ArrayList<Result> result_arr= topology.queryTopologies();
		
		// Check the size 
		assertEquals(2, result_arr.size());
		
		// Check the first id to be top1
		assertEquals("top1", result_arr.get(0).getId());
		// Check the second id to be top2
		assertEquals("top2", result_arr.get(1).getId());
	}
	
	@Test
	void testDeleteTopology() {
		// Read first topology
		Result result = topology.readJSON("topology.json");
		// Read second topology
		result = topology.readJSON("top2.json");
		
		// ArrayList to store the topologies
		ArrayList<Result> result_arr= topology.queryTopologies();
				
		// Check the size 
		assertEquals(2, result_arr.size());
				
		result = topology.deleteTopology("top1");
		// ArrayList to store the topologies
		result_arr= topology.queryTopologies();
		// Check the size 
		assertEquals(1, result_arr.size());
	}
	
	@Test
	void testQueryDevices() {
		// Read first topology
		topology.readJSON("top2.json");
		
		ArrayList<String> device=topology.queryDevices("top2");

		// Check for the device
		assertEquals(true, device.contains("resistor"));
		assertEquals(true, device.contains("pmos"));
		assertEquals(true, device.contains("nmos"));
		assertEquals(false, device.contains("cap"));		
	}
	
	@Test
	void testQueryDevicesWithNetlistNode() {
		// Read first topology
		topology.readJSON("topology.json");
		topology.readJSON("top2.json");
		
		ArrayList<String> device=topology.queryDevicesWithNetlistNode("top2", "n1");
		// Check for the device
		assertEquals(true, device.contains("resistor"));
		assertEquals(true, device.contains("pmos"));
		assertEquals(true, device.contains("nmos"));
		assertEquals(false, device.contains("cap"));
		
		device=topology.queryDevicesWithNetlistNode("top1", "vin");
		// Check for the device
		assertEquals(false, device.contains("resistor"));
		assertEquals(false, device.contains("pmos"));
		assertEquals(true, device.contains("nmos"));
		assertEquals(false, device.contains("cap"));
					
	}
	
	
}
