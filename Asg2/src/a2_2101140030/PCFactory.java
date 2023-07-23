package a2_2101140030;

/**
 * @overview A PC class that create full information of a PC
 * */

public class PCFactory {

     private static PCFactory instance;

    private PCFactory() {
    }
    // Get the singleton instance of PCFactory
    public static PCFactory getInstance() {
        if (instance == null) {
            instance = new PCFactory();
        }
        return instance;
    }

    /**
     * @effects crate and return a PC
     * */
    public PC createPC(String model, int year, String manufacturer, Set<String> comps) {
        PC pc = new PC(model, year, manufacturer, comps);
        return pc;
    }
}    