import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class BusStation<T extends ITransport, U extends IBarbell> {
    // Массив объектов, которые храним
    private final List<T> _places;

    //Максимальное кол-во мест в автовокзале
    private final int _maxCount;

    // Ширина окна отрисовки
    private final int pictureWidth;

    // Высота окна отрисовки
    private final int pictureHeight;

    // Ширина места автовокзала
    private final int _placeSizeWidth = 210;

    // Высота места автовокзала
    private final int _placeSizeHeight = 110;

    private String name;

    private int width;
    private int height;

    // Конструктор
    public BusStation(int picWidth, int picHeight)
    {
        width = picWidth / _placeSizeWidth;
        height = picHeight / _placeSizeHeight;
        _maxCount = width * height;
        _places =  new LinkedList<>();
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    // Перегрузка оператора сложения
    public int add(T bus)
    {
        if(_places.size() >= _maxCount){
            return -1;
        }
        for (int i = 0; i < _maxCount; i++)
        {
            if (!_places.contains(bus))
            {
                _places.add(bus);
                return i;
            }
        }
        return -1;
    }

    // Перегрузка оператора вычитания
    public T del(int index)
    {
        if (index < -1 || index > _places.size())
        {
            return null;
        }
        if (_places.size() <= _maxCount)
        {
            T obj = _places.get(index);
            _places.remove(index);
            return obj;
        }
        return null;
    }

    public boolean less(Trolleybus bus)
    {
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < _places.size(); i++)
        {
            if (_places.get(i) != null)
            {
                if (_places.get(i).hashCode() < minNum) minNum = _places.get(i).hashCode();
            }
        }
        if (bus.hashCode() < minNum) return true;
        return false;
    }

    public boolean more(Trolleybus bus)
    {
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < _places.size(); i++)
        {
            if (_places.get(i) != null)
            {
                if (_places.get(i).hashCode() > maxNum) maxNum = _places.get(i).hashCode();
            }
        }
        if (bus.hashCode() > maxNum) return true;
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < _places.size(); i++)
        {
            _places.get(i).SetPosition(35 + i % 3 * _placeSizeWidth, i / 3 * _placeSizeHeight + 7, pictureWidth, pictureHeight);;
            _places.get(i).DrawTransport(g);

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

    //Индексатор для получения элемента из списка
    public T indexer(int ind)
    {
        if (ind > -1 && ind < _places.size()) return _places.get(ind);
        else return null;
    }
}
