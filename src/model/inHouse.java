package model;

/**
 * this class inherits from Part with the variable
 * machineId added as a special attribute.
 * includes methods for getting and setting machineId.
 */
public class inHouse extends Part {
    /**
     * the special attribute for inHouse
     */
    private int machineId;

    /**
     * constructor for inHouse
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     */
    public inHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        setMachineId(machineId);
    }

    /**
     * getter for machineId
     * @return
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * setter for machineId
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
