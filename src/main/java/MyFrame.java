import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Kireev on 28.01.2020
 * @project PrintTestPageHelp
 */
public class MyFrame extends JFrame {

    private OnClickPrint onClickPrint;
    private String title;
    private int numberOfCopies = 1;
    private List<JButton> buttons;
    private List<String> printers;

    public MyFrame(String title, List<String> printers){
        this.title = title;
        this.printers = printers;
        buttons = new ArrayList<JButton>();
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            init();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setOnClickPrint(OnClickPrint onClickPrint) {
        this.onClickPrint = onClickPrint;
    }

    private void init(){
        this.setSize(new Dimension(500, 300));
        this.setTitle(title);
        JMenuBar menuBar = new JMenuBar();

        JMenu menuPrinters = new JMenu("Принтеры");
        JMenu menuPrinterUi = new JMenu("Изменить принтеры");

        for (int i = 0; i < printers.size(); i++) {
            final int j = i;
            final JMenuItem item = new JMenuItem(printers.get(i));
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String oldVersion = printers.get(j);
                    createDialog(printers.get(j), j).setVisible(true);
                    if (!oldVersion.equals(printers.get(j))) {
                        item.setText(printers.get(j));
                    }
                }
            });
            menuPrinterUi.add(item);
        }

        menuPrinters.add(menuPrinterUi);

        JMenu menuPrint = new JMenu("Печать");
        JMenuItem itemPrint1 = new JMenuItem("Изменить кол-во копий");
        itemPrint1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result =  JOptionPane.showInputDialog("Изменить количество копий, сейчас - " + numberOfCopies);
                try {
                    numberOfCopies = Integer.parseInt(result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(MyFrame.this, "Требуется ввести число");
                }
            }
        });
        menuPrint.add(itemPrint1);

        menuBar.add(menuPrinters);
        menuBar.add(menuPrint);
        this.setJMenuBar(menuBar);

        this.setLayout(new GridLayout(4, 3, 10, 10));
        for (int i = 0; i < printers.size(); i++) {
            final int j = i;
            JButton button = new JButton(formatTextOfButton(printers.get(i)));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onClickPrint.onClickPrint(printers.get(j));
                }
            });
            buttons.add(button);
            this.add(button);
        }
    }

    private JDialog createDialog(String printer, final int position) {
        final JDialog dialog = new JDialog(this, printer, true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLabel lblName = new JLabel(printer);
        final JTextField textField = new JTextField(20);
        JButton button = new JButton("Сохранить");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String newPrinter = textField.getText();
                printers.set(position, newPrinter);
                buttons.get(position).setText(formatTextOfButton(newPrinter));
                dialog.dispose();
            }
        });
        dialog.setSize(new Dimension(400, 200));
        dialog.setLayout(new FlowLayout());
        dialog.add(lblName);
        dialog.add(textField);
        dialog.add(button);
        return dialog;
    }

    protected void processWindowEvent(WindowEvent e)
    {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING)
        {
            PrintMain.savePrinters(printers);
            System.exit(0);
        }
    }

    private String formatTextOfButton(String nameOfPrinter) {
        return String.format("<html><div WIDTH=%d><center>%s</center></div></html>", 120, nameOfPrinter);
    }
}
