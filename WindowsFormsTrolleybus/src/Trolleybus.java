import java.awt.*;
import java.util.Random;

public class Trolleybus{

    BarbellAdd barbellAdd = new BarbellAdd();

    /// Левая координата отрисовки троллейбуса
    private float _startPosX;

    /// Правая кооридната отрисовки троллейбуса
    private float _startPosY;

    /// Ширина окна отрисовки
    private int _pictureWidth;

    /// Высота окна отрисовки
    private int _pictureHeight;

    /// Ширина отрисовки троллейбуса
    private int trolleybusWidth = 150;

    /// Высота отрисовки троллейбуса
    private int trolleybusHeight = 105;

    /// Максимальная скорость
    private int MaxSpeed;
    public int getMaxSpeed() {return MaxSpeed;}
    public void setMaxSpeed(int newMaxSpeed){MaxSpeed = newMaxSpeed;}

    /// Вес троллейбуса
    private float Weight;
    public float getWeight() {return Weight;}
    public void setWeight(int newWeight){Weight = newWeight;}

    /// Основной цвет кузова
    private Color MainColor;
    public Color getMainColor() {return MainColor;}
    public void setMainColor(Color newMainColor){MainColor = newMainColor;}

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
    public void Init(int maxSpeed, float weight, Color mainColor, Color dopColor,
                     boolean headlights, boolean sideLine, boolean barbell, int valueBarbell)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        DopColor = dopColor;
        Headlights = headlights;
        SideLine = sideLine;
        Barbell = barbell;
        barbellAdd.setBarbell(valueBarbell);
    }

    /// Установка позиции троллейбуса
    /// <param name="x">Координата X</param>
    /// <param name="y">Координата Y</param>
    /// <param name="width">Ширина картинки</param>
    /// <param name="height">Высота картинки</param>
    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    /// Изменение направления пермещения
    /// <param name="direction">Направление</param>
    public void MoveTransport(Direction direction)
    {
        float step = MaxSpeed * 100 / Weight;
        switch (direction)
        {
            //Вправо
            case Right:
                if (_startPosX + step < _pictureWidth - trolleybusWidth)
                {
                    _startPosX += step;
                }
                break;
            //Влево
            case Left:
                if (_startPosX + step > step)
                {
                    _startPosX -= step;
                }
                break;
            //Вверх
            case Up:
                if (_startPosY + step > 0)
                {
                    _startPosY -= step;
                }
                break;
            //Вниз
            case Down:
                if (_startPosY + step < _pictureHeight - trolleybusHeight)
                {
                    _startPosY += step;
                }
                break;
        }
    }

    /// <summary>
    /// Отрисовка автомобиля
    /// </summary>
    /// <param name="g"></param>
    public void DrawTransport(Graphics g)
    {
        Color black_pen = new Color(0, 0, 0);
        int y = -48;

        int startPosX = Math.round(_startPosX);
        int startPosY = Math.round(_startPosY);

        //Кузов троллейбуса
        g.setColor(MainColor);
        g.drawRect(startPosX, startPosY - y, 160, 50);
        g.fillRect(startPosX + 1, startPosY + 1 - y, 159, 49);

        //Штанга троллейбуса
        if (Barbell)
        {
            g.setColor(black_pen);
            barbellAdd.DrawBarbell(g, DopColor, startPosX, startPosY);
        }

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

        //Дверь
        g.setColor(black_pen);
        g.drawRect(startPosX + 45, startPosY + 20 - y, 20, 30);
        g.setColor(MainColor);
        g.fillRect(startPosX + 46, startPosY + 21 - y, 19, 29);

        //Колёса троллейбуса
        g.setColor(black_pen);
        g.drawOval(startPosX + 20, startPosY + 45 - y, 20, 20);
        g.drawOval(startPosX + 120, startPosY + 45 - y, 20, 20);
        g.setColor(Color.WHITE);
        g.fillOval(startPosX + 21, startPosY + 46 - y, 18, 18);
        g.fillOval(startPosX + 121, startPosY + 46 - y, 18, 18);

        //Окна
        g.setColor(Color.BLUE);
        g.drawOval(startPosX + 3, startPosY + 5 - y, 18, 25);
        g.drawOval(startPosX + 24, startPosY + 5 - y, 18, 25);
        g.drawOval(startPosX + 68, startPosY + 5 - y, 18, 25);
        g.drawOval(startPosX + 92, startPosY + 5 - y, 18, 25);
        g.drawOval(startPosX + 116, startPosY + 5 - y, 18, 25);
        g.drawOval(startPosX + 140, startPosY + 5 - y, 18, 25);
        g.setColor(Color.WHITE);
        g.fillOval(startPosX + 4, startPosY + 6 - y, 17, 23);
        g.fillOval(startPosX + 25, startPosY + 6 - y, 17, 23);
        g.fillOval(startPosX + 69, startPosY + 6 - y, 17, 23);
        g.fillOval(startPosX + 93, startPosY + 6 - y, 17, 23);
        g.fillOval(startPosX + 117, startPosY + 6 - y, 17, 23);
        g.fillOval(startPosX + 141, startPosY + 6 - y, 17, 23);

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
