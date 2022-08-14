package model;
import java.util.Random;

/**
 * Hold the methods for inhouse parts and is a subclass of "Part"
 */
public class InHouse extends Part {


    private int machineId;

    /**
     * In house constructor
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Gets machine id
     * @return
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Sets integer type machine id value
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
