package model;

/**
 * this class inherits from Part with the variable
 * companyName added as a special attribute.
 * includes methods for getting and setting companyName.
 */
public class Outsourced extends Part {
    /**
     * the special attribute for Outsourced
     */
    private String companyName;

    /**
     * constructor for Outsourced
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        setCompanyName(companyName);
    }

    /**
     * getter for companyName
     * @return
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * setter for companyName
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
