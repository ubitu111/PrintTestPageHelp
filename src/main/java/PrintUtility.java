import javax.print.PrintService;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Kireev on 24.01.2020
 * @project PrintTestPageHelp
 */
public class PrintUtility {
    public static PrintService findPrintService(String printerName) {

        printerName = printerName.toLowerCase();

        PrintService service = null;

        // Get array of all print services
        PrintService[] services = PrinterJob.lookupPrintServices();

        // Retrieve a print service from the array
        for (int index = 0; service == null && index < services.length; index++) {

            if (services[index].getName().toLowerCase().contains(printerName)) {
                service = services[index];
            }
        }

        // Return the print service
        return service;
    }

    /**
     * Retrieves a List of Printer Service Names.
     *
     * @return List
     */
    public static List<String> getPrinterServiceNameList() {

        // get list of all print services
        PrintService[] services = PrinterJob.lookupPrintServices();
        List<String> list = new ArrayList<String>();

        for (PrintService service : services) {
            list.add(service.getName());
        }

        return list;
    }

    /**
     * Utility class; no construction!
     */
    private PrintUtility() {}
}
