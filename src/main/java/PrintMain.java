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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Kireev on 23.01.2020
 * @project PrintTestPageHelp
 */
public class PrintMain {
    private static Data data = new Data();
    private static MyFrame myFrame;

    private static final String PATH_TO_TEST_PAGE = "C:\\testPage.pdf";

    public static void main(String[] args) {
        List<String> printers = data.getPrinters();
        myFrame = new MyFrame("Простая Печать для Паши", printers);
        myFrame.setOnClickPrint(new OnClickPrint() {
            public void onClickPrint(String printerName) {
                try {
                    printTestPage(printerName);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(myFrame, ex.getMessage());
                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(myFrame, ex.getMessage());
                }
            }
        });
        myFrame.setVisible(true);
    }

        private static void printTestPage (String nameOfPrinter) throws IOException, PrinterException {
            System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
            PDDocument document = PDDocument.load(new File(PATH_TO_TEST_PAGE));
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(document));
            PrintService printService = PrintUtility.findPrintService(nameOfPrinter);
            job.setPrintService(printService);
            job.setCopies(myFrame.getNumberOfCopies());
            job.print();
            document.close();
        }


        public static void savePrinters(List<String> printers) {
            data.savePrinters(printers);
        }

}
