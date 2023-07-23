package a2_2101140030;


/**
 * @overview show a report of the PC
 */
public class PCReport {

    public String displayReport(PC[] pcs) {
        String report =
                "---------------------------------------------------------------------------------------------------" + '\n' +
                        "                                          PCPROG REPORT                                            " + '\n' +
                        "---------------------------------------------------------------------------------------------------" + '\n';
        int counter = 1;
        for (PC pc : pcs) {
            String model = pc.getModel();
            int year = pc.getYear();
            String manufacturer = pc.getManufacturer();
            String components = pc.getComps().getElements().toString();
            report += String.format("%-2d %-20s %-10d %-60s\n", counter++, model, year, manufacturer + components);
        }
        System.out.println(report);
        return report;
    }

}
