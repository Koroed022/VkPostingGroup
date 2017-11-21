import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormClass extends JFrame {
    public JTextArea additionallyINF;
    private JPanel contentPane;
    private JButton DegrodBut;
    private JTextArea FirstPhotoTime;
    private JButton PlusHalf;
    private JButton MinusHalf;
    private JTextField ThisPage;
    private JTextArea PhotoCount;
    private JTextArea EveryTime;
    private JTextArea AlbumArea;
    Boolean plus = false;

    public FormClass() {
        setContentPane(contentPane);
        //setModal(true);
        getRootPane().setDefaultButton(DegrodBut);
        getRootPane().setDefaultButton(PlusHalf);
        getRootPane().setDefaultButton(MinusHalf);

        PlusHalf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plus = true;
            }
        });
        MinusHalf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plus = false;
            }
        });

        DegrodBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ThisPage.setText(OurTurns.Turn(FirstPhotoTime.getText(), additionallyINF.getText(),plus,PhotoCount.getText(),EveryTime.getText(),AlbumArea.getText()));
                }  catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }

    public static void CteateForm() {
        FormClass dialog = new FormClass();

        dialog.pack();
        dialog.setTitle("Через сколько часов || photos");
        dialog.setLocation(500,300);
        dialog.setSize(700,300);
        dialog.setVisible(true);

    }



}
