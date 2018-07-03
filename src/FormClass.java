import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormClass extends JFrame {
    public JTextArea additionallyINF;
    private JPanel contentPane;
    private JButton DegrodBut;
    private JTextArea FirstPhotoTime;
    private JButton PlusHalf;
    private JButton MinusHalf;
    private JTextField hentaiPage;
    private JTextArea PhotoCount;
    private JTextArea EveryTime;
    private JTextArea AlbumArea;
    private JButton hentaiButton;
    private JSlider slider1;
    private JLabel labelSlider;
    private JButton sortButton;
    private JButton inviteButton;
    Boolean plus = false;

    public FormClass() {
        setContentPane(contentPane);
        //setModal(true);
        getRootPane().setDefaultButton(DegrodBut);
        getRootPane().setDefaultButton(PlusHalf);
        getRootPane().setDefaultButton(MinusHalf);

        slider1.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(slider1.getValue() == 4)
                    labelSlider.setText("Danbooru");

                //if(slider1.getValue() == 3)
                //    labelSlider.setText("Danbooru");

                if(slider1.getValue() == 2)
                    labelSlider.setText("Arts Pixiv");

                if(slider1.getValue() == 1)
                    labelSlider.setText("Manga AllHentai");

                if(slider1.getValue() == 0)
                    labelSlider.setText("Video Hentai-X");
            }
        });

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
                    hentaiPage.setText(OurTurns.Turn(FirstPhotoTime.getText(), additionallyINF.getText(),plus,PhotoCount.getText(),EveryTime.getText(),AlbumArea.getText()));
                }  catch (Exception e1) {
                    System.out.println(e1.toString());
                }
            }
        });

        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                   OurTurns.Sort(hentaiPage.getText());
                }  catch (Exception e1) {
                    System.out.println(e1.toString());
                }
            }
        });


        hentaiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(slider1.getValue() == 4)
                        hentaiPage.setText(OurTurns.downloadDanbooru(hentaiPage.getText()));
                    //if(slider1.getValue() == 3)
                    //    hentaiPage.setText(OurTurns.downloadDanbooru(hentaiPage.getText()));
                    if(slider1.getValue() == 2)
                        hentaiPage.setText(OurTurns.downloadPixiv(hentaiPage.getText()));
                    if(slider1.getValue() == 1)
                        OurTurns.downloadHentai(hentaiPage.getText());
                    if(slider1.getValue() == 0)
                        OurTurns.downloadVideo(hentaiPage.getText());
                    }  catch (Exception e1) {
                    System.out.println(e1.toString());
                }
            }
        });

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }

    public static void CteateForm() {
        FormClass dialog = new FormClass();

        dialog.pack();
        dialog.setTitle("HentaiYER");
        dialog.setLocation(500,300);
        dialog.setSize(700,300);
        dialog.setVisible(true);

    }



}
