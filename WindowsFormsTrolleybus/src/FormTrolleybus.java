import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.Random;

public class FormTrolleybus {
    private ITransport bus;

    private JPanel pictureBox;
    private Graphics g;

    private void Draw() {
        bus.DrawTransport(g);
    }

    public FormTrolleybus() {
        JFrame frame = new JFrame("Троллейбус");
        frame.setLayout(null);
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание окна, где будет рисоваться изображение
        pictureBox = new JPanel();
        pictureBox.setSize(750, 500);
        pictureBox.setBackground(Color.WHITE);
        pictureBox.setLocation(0, 0);
        pictureBox.setVisible(true);

        //Кнопки на форме
        JButton buttonCreateTrolleybus = new JButton("Создать тролейбус");
        buttonCreateTrolleybus.setBackground(Color.WHITE);
        buttonCreateTrolleybus.setLocation(755, 10);
        buttonCreateTrolleybus.setSize(150, 50);
        buttonCreateTrolleybus.setVisible(true);

        JButton buttonCreateBus = new JButton("Создать Автобус");
        buttonCreateBus.setBackground(Color.WHITE);
        buttonCreateBus.setLocation(910, 10);
        buttonCreateBus.setSize(150, 50);
        buttonCreateBus.setVisible(true);

        JButton buttonUp = new JButton();
        buttonUp.setLocation(800, 360);
        buttonUp.setSize(30, 30);
        buttonUp.setVisible(true);
        buttonUp.setIcon(new ImageIcon(new ImageIcon("src/img/Up.png")
                .getImage().getScaledInstance(30, 30, 16)));


        JButton buttonDown = new JButton();
        buttonDown.setLocation(800, 400);
        buttonDown.setSize(30, 30);
        buttonDown.setVisible(true);
        buttonDown.setIcon(new ImageIcon(new ImageIcon("src/img/Down.png").
                getImage().getScaledInstance(30, 30, 16)));


        JButton buttonRight = new JButton();
        buttonRight.setLocation(840, 400);
        buttonRight.setSize(30, 30);
        buttonRight.setVisible(true);
        buttonRight.setIcon(new ImageIcon(new ImageIcon("src/img/Right.png").
                getImage().getScaledInstance(30, 30, 16)));


        JButton buttonLeft = new JButton();
        buttonLeft.setLocation(760, 400);
        buttonLeft.setSize(30, 30);
        buttonLeft.setVisible(true);
        buttonLeft.setIcon(new ImageIcon(new ImageIcon("src/img/Left.png").
                getImage().getScaledInstance(30, 30, 16)));


        // Логика кнопок
        buttonCreateTrolleybus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                g = pictureBox.getGraphics();
                pictureBox.update(g);
                Random rn = new Random();
                bus = new Trolleybus(rn.nextInt(100) + 200, rn.nextInt(1000) + 1000, new Color(255, 165, 0),
                        new Color(0,0,0), true, true, true, rn.nextInt(3) +1, rn.nextInt(3) +1);
                bus.SetPosition(rn.nextInt(90) + 10, rn.nextInt(90) + 10, pictureBox.getWidth(), pictureBox.getHeight());
                Draw();
            }
        });

        buttonCreateBus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                g = pictureBox.getGraphics();
                pictureBox.update(g);
                Random rnd = new Random();
                bus = new Bus(rnd.nextInt(100) + 200, rnd.nextInt(1000) + 1000, new Color(255, 165, 0));
                bus.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10, pictureBox.getWidth(), pictureBox.getHeight());
                Draw();
            }
        });

        buttonUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pictureBox.update(g);
                bus.MoveTransport(Direction.Up);
                Draw();
            }
        });

        buttonDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pictureBox.update(g);
                bus.MoveTransport(Direction.Down);
                Draw();
            }
        });

        buttonRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pictureBox.update(g);
                bus.MoveTransport(Direction.Right);
                Draw();
            }
        });

        buttonLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pictureBox.update(g);
                bus.MoveTransport(Direction.Left);
                Draw();
            }
        });

        frame.add(buttonUp);
        frame.add(buttonDown);
        frame.add(buttonRight);
        frame.add(buttonLeft);
        frame.add(buttonCreateTrolleybus);
        frame.add(buttonCreateBus);
        frame.add(pictureBox);
        frame.setVisible(true);
    }
}
