import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Stack;

public class FormBusStation {

    private BusStation<ITransport, IBarbell> busStation;

    private BusStationCollection busStationCollection;

    BufferedImage bufferedImage;
    FormImage busStationImage;
    private Graphics g;

    //ListBox
    private JList<BusStation<ITransport, IBarbell>> listBoxBusStation;

    //Для листБокса
    private DefaultListModel<String> busStationList;

    Stack<ITransport> stack;

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
        busStationImage.setSize(674, 470);
        busStationImage.setLocation(0, 0);

        busStationCollection = new BusStationCollection(busStationImage.getWidth(), busStationImage.getHeight());
        bufferedImage = new BufferedImage(busStationImage.getWidth(), busStationImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        JLabel labelBusStation = new JLabel("Автовокзалы");
        labelBusStation.setBounds(730, 22, 100, 17);
        labelBusStation.setVisible(true);

        JTextField textFieldNewLevelName = new JTextField();
        textFieldNewLevelName.setLocation(680, 40);
        textFieldNewLevelName.setSize(170, 22);
        textFieldNewLevelName.setVisible(true);

        JButton buttonAddBusStation = new JButton("Добавить автовокзал");
        buttonAddBusStation.setBackground(Color.WHITE);
        buttonAddBusStation.setLocation(680, 70);
        buttonAddBusStation.setSize(170, 50);
        buttonAddBusStation.setVisible(true);


        //ListBox "JList"
        busStationList = new DefaultListModel<>();

        listBoxBusStation = new JList<>();
        listBoxBusStation.setBounds(680, 130,170,110);
        listBoxBusStation.setVisible(true);
        listBoxBusStation.setModel(busStationCollection.modelList);

        //Кнопка "Удалить депо"
        JButton buttonDelBusStation = new JButton("Удалить автовокзал");
        buttonDelBusStation.setBackground(Color.WHITE);
        buttonDelBusStation.setBounds(680, 250,170,35);
        buttonDelBusStation.setVisible(true);

        JButton buttonSetBus = new JButton("Добавить автобус");
        buttonSetBus.setBackground(Color.WHITE);
        buttonSetBus.setLocation(680, 290);
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

        stack = new Stack<>();

        //Кнопка "Взять из стека"
        JButton buttonFromStack = new JButton("Взять из стека");
        buttonFromStack.setBackground(Color.WHITE);
        buttonFromStack.setBounds(680,475,170,20);

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

        busStation = new BusStation<ITransport,IBarbell>(busStationImage.getWidth(), busStationImage.getHeight());

        buttonSetBus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color mColor = JColorChooser.showDialog(frame, "Color Chooser", Color.green);
                if (mColor != null) {
                    Bus bus = new Bus(100, 1000, mColor);
                    int num = busStation.add(bus);
                    if (num != -1) {
                        Draw();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Автовокзал заполнен!");
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
                            JOptionPane.showMessageDialog(frame, "Автовокзал заполнен!");
                        }
                    }
                }
            }
        });

        buttonTake.addActionListener(e ->
        {
            if (textFieldPlace.getText() != "")
            {
                int place;
                try
                {
                    place = Integer.parseInt(textFieldPlace.getText());
                } catch (NumberFormatException ex)
                {
                    return;
                }

                ITransport bus = busStation.del(place);
                if (bus != null)
                {
                    stack.add(bus);
                }
                Draw();
            }
        });

        listBoxBusStation.getSelectionModel().addListSelectionListener(e ->
        {
            busStation = listBoxBusStation.getSelectedValue();
            if (busStation == null) frame.getGraphics().clearRect(0, 0, busStationImage.getWidth(), busStationImage.getHeight());
            else Draw();
        });

        buttonFromStack.addActionListener(e ->
        {
            ITransport bus = null;
            if (!stack.isEmpty())
            {
                bus = stack.pop();
            }
            if(bus != null){
                FormTrolleybus removedBus = new FormTrolleybus();
                removedBus.SetBus(bus);
            }
            else{
                JOptionPane.showMessageDialog(null, "Стэк пустой");
            }
            Draw();
        });

        buttonAddBusStation.addActionListener(e ->
        {
            if (textFieldNewLevelName.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Сначала введите имя автовокзала");
            } else
            {

                if (busStationCollection.AddBusStation(textFieldNewLevelName.getText()) != null)
                {
                    Draw();
                } else
                {
                    JOptionPane.showMessageDialog(frame, "Автовокзал с таким именем был создан");
                }
                textFieldNewLevelName.setText("");
            }
        });


        buttonDelBusStation.addActionListener((e) ->
        {
            if(listBoxBusStation.getSelectedIndex() > -1)
            {
                int a = JOptionPane.showConfirmDialog(frame, "Удалить депо <" +
                        listBoxBusStation.getSelectedValue() + ">", "Удаление", JOptionPane.YES_NO_OPTION);
                if(a == 0)
                {
                    busStationCollection.DelBusStation(listBoxBusStation.getSelectedValue().toString());
                    ReloadLevels();
                }
            }
        });




        frame.add(buttonDelBusStation);
        frame.add(buttonFromStack);
        frame.add(listBoxBusStation);
        frame.add(buttonAddBusStation);
        frame.add(textFieldNewLevelName);
        frame.add(labelBusStation);
        frame.add(buttonSetBus);
        frame.add(buttonSetTrolleybus);
        frame.add(labelTakeBus);
        frame.add(labelPlace);
        frame.add(textFieldPlace);
        frame.add(buttonTake);
        frame.add(busStationImage);

        frame.setVisible(true);

        Draw();
    }

    private void ReloadLevels()
    {
        int index = listBoxBusStation.getSelectedIndex();
        busStationList.removeAllElements();

        int i = 0;
        for(String name: busStationCollection.Keys())
        {
            busStationList.add(i, name);
            i++;
        }

        int count = busStationList.size();
        if(count > 0 && (index < 0 || index >= count))
        {
            listBoxBusStation.setSelectedIndex(0);
        }
        else if(index > -1 && index < count)
        {
            listBoxBusStation.setSelectedIndex(index);
        }
        Draw();
    }
}
