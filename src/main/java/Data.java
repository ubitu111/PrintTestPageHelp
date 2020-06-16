import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Kireev on 28.01.2020
 * @project PrintTestPageHelp
 */
public class Data {
    private List<String> printers;

    public Data() {
        printers = new ArrayList<String>();
        File file = new File("printers.out");
        if (file.exists()) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                printers = (ArrayList) objectInputStream.readObject();
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            printers.add("Xerox Phaser 3117");
            printers.add("Xerox Phaser 3140");
            printers.add("Xerox Phaser 3250");
            printers.add("HP P1102");
            printers.add("HP LaserJet 1018 (KEK)");
            printers.add("HP LaserJet Pro MFP M125-M126 PCLmS");
            printers.add("HP P2050");
            printers.add("Xerox Phaser 3300");
            printers.add("Samsung M332x");
            printers.add("мехгбеярмн");
            printers.add("NPI175D12 (HP LaserJet 400 MFP M425dn)");
            printers.add("HP LaserJet Professional P1102");
            printers.add("мехгбеярмн");
            printers.add("мехгбеярмн");
            printers.add("мехгбеярмн");
            printers.add("мехгбеярмн");
            printers.add("мехгбеярмн");
            printers.add("мехгбеярмн");
        }
    }

    public List<String> getPrinters() {
        return printers;
    }

    public void savePrinters(List<String> printers) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("printers.out"));
            objectOutputStream.writeObject(printers);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
