import javax.swing.*;
import java.awt.*;

public class Trolleybus extends Bus {

    IBarbell barbells;

    /// Дополнительный цвет
    private Color DopColor;
    public Color getDopColor() {return DopColor;}
    public void setDopColor(Color newDopColor){DopColor = newDopColor;}

    /// Признак наличия фар
    private boolean Headlights;
    public boolean getHeadlights() {return Headlights;}
    public void setHeadlights(boolean newHeadlights){Headlights = newHeadlights;}

    /// Признак наличия боковой линии
    private boolean SideLine;
    public boolean getSideLine() {return SideLine;}
    public void setSideLine(boolean newSideLine){SideLine = newSideLine;}

    /// Признак наличия штанги линии
    private boolean Barbell;
    public boolean getBarbell() {return Barbell;}
    public void setBarbell(boolean newBarbell){Barbell = newBarbell;}

    /// Инициализация свойств
    /// <param name="maxSpeed">Максимальная скорость</param>
    /// <param name="weight">Вес автомобиля</param>
    /// <param name="mainColor">Основной цвет кузова</param>
    /// <param name="dopColor">Дополнительный цвет</param>
    /// <param name="frontSpoiler">Признак наличия переднего спойлера</param>
    /// <param name="sideSpoiler">Признак наличия боковых спойлеров</param>
    public Trolleybus(int maxSpeed, float weight, Color mainColor, Color dopColor,
                      boolean headlights, boolean sideLine, boolean barbell, int valueBarbell, int valueTypeBarbell)
    {
        super(maxSpeed, weight, mainColor, 140, 103);
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        DopColor = dopColor;
        Headlights = headlights;
        SideLine = sideLine;
        Barbell = barbell;

        typeBarbell(valueBarbell, valueTypeBarbell);
    }

    public void typeBarbell(int valueBarbell,  int valueTypeBarbell){
        if(valueTypeBarbell == 1){
            barbells = new BarbellAdd();
        }
        else if(valueTypeBarbell == 2){
            barbells = new BarbellAddSecondType();
        }
        else if(valueTypeBarbell == 3){
            barbells = new BarbellAddThirdType();
        } else{
            barbells = new BarbellAdd();
        }
        barbells.setBarbell(valueBarbell);
    }

    /// Отрисовка автомобиля
    public void DrawTransport(Graphics g)
    {
        Color black_pen = new Color(0, 0, 0);
        int y = -48;

        int startPosX = Math.round(_startPosX);
        int startPosY = Math.round(_startPosY);

        //Штанга троллейбуса
        if (Barbell)
        {
            g.setColor(black_pen);
            barbells.DrawBarbell(g, DopColor, startPosX, startPosY);
        }

        super.DrawTransport(g);

        // Боковая линия
        if (SideLine)
        {
            g.setColor(black_pen);
            g.drawRect(startPosX, startPosY + 30 - y, 45, 10);
            g.drawRect(startPosX + 65, startPosY + 30 - y, 95, 10);
            g.setColor(DopColor);
            g.fillRect(startPosX + 1, startPosY + 31 - y, 44, 9);
            g.fillRect(startPosX + 66, startPosY + 31 - y, 94, 9);
        }
        // Фары
        if (Headlights)
        {
            g.setColor(black_pen);
            g.drawOval(startPosX + 150, startPosY + 40 - y, 8, 8);
            g.setColor(DopColor);
            g.fillOval(startPosX + 150, startPosY + 40 - y, 8, 8);
        }

    }
}
