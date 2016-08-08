package port;

import java.util.List;

import warehouse.Container;
import warehouse.Warehouse;

public class Berth {

	private int id;
	private Warehouse portWarehouse;

	public Berth(int id, Warehouse warehouse) {
		this.id = id;
		portWarehouse = warehouse;
	}

	public int getId() {
		return id;
	}

	public boolean add(Warehouse shipWarehouse, int numberOfConteiners) throws InterruptedException {
		boolean result = false;

		List<Conteiner> conteiners = shipWarehouse.getContainer(numberOfConteiners);
		synchronized (portWarehouse) {
			result = portWarehouse.addContainer(conteiners);
		}
		return result;
	}

	public boolean get(Warehouse shipWarehouse, int numberOfConteiners) throws InterruptedException {
		boolean result = false;

		List<Conteiner> conteiners = null;
		synchronized (portWarehouse) {
			conteiners = portWarehouse.getContainer(numberOfConteiners);
		}
		if(containers != null){
			shipWarehouse.addContainer(containers);
			result = true;
		}
		return result;
	}
	
	
}
