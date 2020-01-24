import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

/**
 * @author Vladimir Kireev on 23.01.2020
 * @project PrintTestPageHelp
 */
public class PrintTest {
    private static final String PATH_TO_TEST_PAGE = "C:\\testPage.pdf";

    private static final String PRINTER_ONE = "Xerox Phaser 3117";
    private static final String PRINTER_TWO = "Xerox Phaser 3140";
    private static final String PRINTER_THREE = "Xerox Phaser 3250";
    private static final String PRINTER_FOUR = "HP P1102";
    private static final String PRINTER_FIVE = "HP LaserJet 1018 (KEK)";
    private static final String PRINTER_SIX = "HP M125";
    private static final String PRINTER_SEVEN = "HP P2050";
    private static final String PRINTER_EIGHT = "Xerox Phaser 3300";
    private static final String PRINTER_NINE = "Samsung M332x";
    private static final String PRINTER_TEN = "";
    private static final String PRINTER_ELEVEN = "NPI175D12 (HP LaserJet 400 MFP M425dn)";
    private static final String PRINTER_TWELVE = "HP LaserJet Professional P1102";
    private static String[] printers;

    static {
        printers = new String[12];
        printers[0] = PRINTER_ONE;
        printers[1] = PRINTER_TWO;
        printers[2] = PRINTER_THREE;
        printers[3] = PRINTER_FOUR;
        printers[4] = PRINTER_FIVE;
        printers[5] = PRINTER_SIX;
        printers[6] = PRINTER_SEVEN;
        printers[7] = PRINTER_EIGHT;
        printers[8] = PRINTER_NINE;
        printers[9] = PRINTER_TEN;
        printers[10] = PRINTER_ELEVEN;
        printers[11] = PRINTER_TWELVE;
    }

    public static void main(String[] args) {
        final JFrame main = new JFrame();
        main.setVisible(true);
        main.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        main.setTitle("Печать тестовой страницы");
        main.setLayout(new GridLayout(4, 3, 10 , 10));

        for (int i = 0; i < printers.length; i++) {
            final int j = i;
            Button button = new Button(printers[i]);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        printTestPage(printers[j]);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(main, ex.getMessage());
                    } catch (PrinterException ex) {
                        JOptionPane.showMessageDialog(main, ex.getMessage());
                    }
                }
            });
            main.add(button);
        }
        main.setSize(600, 300);
    }

        private static void printTestPage (String nameOfPrinter) throws IOException, PrinterException {
            System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
            PDDocument document = PDDocument.load(new File(PATH_TO_TEST_PAGE));
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(document));
            PrintService printService = PrintUtility.findPrintService(nameOfPrinter);
            job.setPrintService(printService);
            job.print();
            document.close();
        }


}
