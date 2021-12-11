import javax.swing.*;
import java.awt.*;

public class BusStation<T extends ITransport, U extends IBarbell> {
    // Массив объектов, которые храним
    private final T[] _places;

    // Ширина окна отрисовки
    private final int pictureWidth;

    // Высота окна отрисовки
    private final int pictureHeight;

    // Ширина места автовокзала
    private final int _placeSizeWidth = 210;

    // Высота места автовокзала
    private final int _placeSizeHeight = 110;

    private int width;
    private int height;

    // Конструктор
    public BusStation(int picWidth, int picHeight)
    {
        width = picWidth / _placeSizeWidth;
        height = picHeight / _placeSizeHeight;
        _places = (T[]) new ITransport[width * height];
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    // Перегрузка оператора сложения
    public int add(T bus)
    {
        int i = 0;
        int j = 0;
        while (i < height)
        {
            j = 0;
            while (j < width)
            {
                if (_places[i * width + j] == null)
                {
                    _places[i * width + j] = bus;
                    bus.SetPosition(35 + j * _placeSizeWidth, 7 + i * _placeSizeHeight, pictureWidth, pictureHeight);
                    return i * width + j;
                }
                j++;
            }
            i++;
        }
        return -1;
    }

    // Перегрузка оператора вычитания
    public T del(int index)
    {
        if ((index >= width * height) || (_places[index] == null))
        {
            return null;
        }
        if (_places[index] != null)
        {
            T obj = _places[index];
            _places[index] = null;
            return obj;
        } else
        {
            return null;
        }
    }

    public boolean less(BusStation<T, U> p, Trolleybus bus)
    {
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < p._places.length; i++)
        {
            if (_places[i] != null)
            {
                if (_places[i].hashCode() < minNum) minNum = _places[i].hashCode();
            }
        }
        if (bus.hashCode() < minNum) return true;
        return false;
    }

    public boolean more(BusStation<T, U> p, Trolleybus bus)
    {
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < p._places.length; i++)
        {
            if (_places[i] != null)
            {
                if (_places[i].hashCode() > maxNum) maxNum = _places[i].hashCode();
            }
        }
        if (bus.hashCode() > maxNum) return true;
        return false;
    }


    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < _places.length; i++)
        {
            if (_places[i] != null)
            {
                _places[i].DrawTransport(g);
            }
        }
    }

    // Метод отрисовки границ мест автовокзала
    private void DrawMarking(Graphics g)
    {
        g.setColor(Color.BLACK);

        for (int i = 0; i < pictureWidth / _placeSizeWidth; i++)
        {
            for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; ++j)
            {//линия рамзетки места
                g.drawLine(i * _placeSizeWidth + 5, j * _placeSizeHeight + 10,
                        i * _placeSizeWidth + _placeSizeWidth / 2, j * _placeSizeHeight + 10);
            }
            g.drawLine(i * _placeSizeWidth + 5, 10, i * _placeSizeWidth + 5,
                    (pictureHeight / _placeSizeHeight) * _placeSizeHeight + 10);
        }
    }
}
