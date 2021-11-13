import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.Random;

public class FormTrolleybus {
    private Trolleybus trolleybus = new Trolleybus();

    private JPanel pictureBox;
    private Graphics g;

    private void Draw() {
        trolleybus.DrawTransport(g);
    }

    public FormTrolleybus() {
        JFrame frame = new JFrame("Троллейбус");
        frame.setLayout(null);
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание окна, где будет рисоваться изображение
        pictureBox = new JPanel();
        pictureBox.setSize(750, 500);
        pictureBox.setBackground(Color.WHITE);
        pictureBox.setLocation(0, 0);
        pictureBox.setVisible(true);

        //Кнопки на форме
        JButton buttonCreate = new JButton("Создать");
        buttonCreate.setBackground(Color.WHITE);
        buttonCreate.setLocation(775, 10);
        buttonCreate.setSize(100, 20);
        buttonCreate.setVisible(true);

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
        buttonCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                g = pictureBox.getGraphics();
                pictureBox.update(g);
                Random rn = new Random();
                trolleybus.Init(rn.nextInt(100) + 200, rn.nextInt(1000) + 1000, new Color(255, 165, 0),
                        new Color(0,0,0), true, true, true,  rn.nextInt(3) +1);
                trolleybus.SetPosition(rn.nextInt(90) + 10, rn.nextInt(90) + 10, pictureBox.getWidth(), pictureBox.getHeight());
                Draw();
            }
        });

        buttonUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pictureBox.update(g);
                trolleybus.MoveTransport(Direction.Up);
                Draw();
            }
        });

        buttonDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pictureBox.update(g);
                trolleybus.MoveTransport(Direction.Down);
                Draw();
            }
        });

        buttonRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pictureBox.update(g);
                trolleybus.MoveTransport(Direction.Right);
                Draw();
            }
        });

        buttonLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pictureBox.update(g);
                trolleybus.MoveTransport(Direction.Left);
                Draw();
            }
        });

        frame.add(buttonUp);
        frame.add(buttonDown);
        frame.add(buttonRight);
        frame.add(buttonLeft);
        frame.add(buttonCreate);
        frame.add(pictureBox);
        frame.setVisible(true);
    }
}
