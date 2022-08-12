package model;

import java.util.Random;

/**
 * Hold the methods for outsourced parts and is a subclass of "Part"
 */
public class Outsourced extends Part {
    Random rnd = new Random();

    private String companyName;

    /**
     * Constructor
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
        this.setId(rnd.nextInt(1000));
        this.companyName = companyName;
    }

    /**
     * Gets the company name
     * @return
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the string type company name value
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
