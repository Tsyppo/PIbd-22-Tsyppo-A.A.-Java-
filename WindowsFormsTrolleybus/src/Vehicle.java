import java.awt.*;

public abstract class Vehicle implements ITransport {

    /// Левая координата отрисовки автомобиля
    protected float _startPosX;

    /// Правая кооридната отрисовки автомобиля
    protected float _startPosY;

    /// Ширина окна отрисовки
    protected int _pictureWidth;

    /// Высота окна отрисовки
    protected int _pictureHeight;

    /// Максимальная скорость
    public int MaxSpeed;
    public int getMaxSpeed() {return MaxSpeed;}
    public void setMaxSpeed(int newMaxSpeed){MaxSpeed = newMaxSpeed;}

    /// Вес автомобиля
    public float Weight;
    public float getWeight() {return Weight;}
    public void setWeight(int newWeight){Weight = newWeight;}

    /// Основной цвет кузова
    public Color MainColor;
    public Color getMainColor() {return MainColor;}
    public void setMainColor(Color newMainColor){MainColor = newMainColor;}

    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }
    public abstract void DrawTransport(Graphics g);
    public abstract void MoveTransport(Direction direction);
}
