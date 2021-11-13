import java.awt.*;

public class Bus extends Vehicle{

    /// Ширина отрисовки троллейбуса
    protected  int trolleybusWidth = 150;

    /// Высота отрисовки троллейбуса
    protected  int trolleybusHeight = 105;

    /// Конструктор
    public Bus(int maxSpeed, float weight, Color mainColor)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }

    /// Конструкторс изменением размеров машины
    protected Bus(int maxSpeed, float weight, Color mainColor, int TrolleybusWidth,
                  int TrolleybusHeight)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        this.trolleybusWidth = TrolleybusWidth;
        this.trolleybusHeight = TrolleybusHeight;
    }

    @Override
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

    @Override
    public void DrawTransport(Graphics g) {
        Color black_pen = new Color(0, 0, 0);
        int y = -48;

        int startPosX = Math.round(_startPosX);
        int startPosY = Math.round(_startPosY);

        //Кузов троллейбуса
        g.setColor(MainColor);
        g.drawRect(startPosX, startPosY - y, 160, 50);
        g.fillRect(startPosX + 1, startPosY + 1 - y, 159, 49);

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
    }
}
