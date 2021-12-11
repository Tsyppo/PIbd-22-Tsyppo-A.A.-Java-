import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.util.Random;

public class FormBusStation {

    private BusStation<Bus, IBarbell> busStation;

    BufferedImage bufferedImage;
    FormImage busStationImage;
    private Graphics g;

    private void Draw() {
        g = bufferedImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, busStationImage.getWidth(), busStationImage.getHeight());
        busStation.Draw(g);
        busStationImage.image = bufferedImage;
        busStationImage.repaint();
    }

    public FormBusStation() {
        JFrame frame = new JFrame("Автовокзал");
        frame.setLayout(null);
        frame.setSize(900, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        busStationImage = new FormImage();
        busStationImage.setSize(674, 500);
        busStationImage.setLocation(0, 0);

        bufferedImage = new BufferedImage(busStationImage.getWidth(), busStationImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        JButton buttonSetBus = new JButton("Добавить автобус");
        buttonSetBus.setBackground(Color.WHITE);
        buttonSetBus.setLocation(680, 292);
        buttonSetBus.setSize(170, 24);
        buttonSetBus.setVisible(true);

        JButton buttonSetTrolleybus = new JButton("Добавить троллейбус");
        buttonSetTrolleybus.setBackground(Color.WHITE);
        buttonSetTrolleybus.setLocation(680, 322);
        buttonSetTrolleybus.setSize(170, 50);
        buttonSetTrolleybus.setVisible(true);

        JButton buttonTake = new JButton("Забрать");
        buttonTake.setBackground(Color.WHITE);
        buttonTake.setLocation(680, 425);
        buttonTake.setSize(170, 45);
        buttonTake.setVisible(true);

        JLabel labelTakeBus = new JLabel("Забрать автобус");
        labelTakeBus.setBounds(720, 381, 100, 17);
        labelTakeBus.setVisible(true);

        JLabel labelPlace = new JLabel("Место:");
        labelPlace.setBounds(680, 401, 53, 17);
        labelPlace.setVisible(true);

        JTextField textFieldPlace = new JTextField();
        textFieldPlace.setLocation(726, 401);
        textFieldPlace.setSize(124, 22);
        textFieldPlace.setVisible(true);

        buttonSetBus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color mColor = JColorChooser.showDialog(frame, "Color Chooser", Color.green);
                if (mColor != null) {
                    Bus bus = new Bus(100, 1000, mColor);
                    int num = busStation.add(bus);
                    if (num != -1) {
                        Draw();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Bus Station is Full!");
                    }
                }
            }
        });

        buttonSetTrolleybus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color mainColor = JColorChooser.showDialog(frame, "Color Chooser", Color.green);
                Color secondaryColor = JColorChooser.showDialog(frame, "Color Chooser", Color.red);
                Random rnd = new Random();
                if (mainColor != null) {
                    if (secondaryColor != null) {
                        Trolleybus trolleybus = new Trolleybus(1000, rnd.nextInt(1000) + 1000,
                                mainColor, secondaryColor, true, true, true, rnd.nextInt(2) + 1, rnd.nextInt(2) + 1);
                        if (busStation.add(trolleybus) != -1) {
                            Draw();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Bus Station is Full!");
                        }
                    }
                }
            }
        });

        buttonTake.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textFieldPlace.getText() != "") {
                    int place;
                    try {
                        place = Integer.parseInt(textFieldPlace.getText());
                    } catch (NumberFormatException ex) {
                        return;
                    }

                    ITransport bus = busStation.del(place);
                    if (bus != null) {
                        FormTrolleybus form = new FormTrolleybus();
                        form.SetBus(bus);
                    }
                    Draw();
                }
            }
        });

        frame.add(buttonSetBus);
        frame.add(buttonSetTrolleybus);
        frame.add(labelTakeBus);
        frame.add(labelPlace);
        frame.add(textFieldPlace);
        frame.add(buttonTake);
        frame.add(busStationImage);

        frame.setVisible(true);

        busStation = new BusStation<>(busStationImage.getWidth(), busStationImage.getHeight());
        Draw();
    }
}
