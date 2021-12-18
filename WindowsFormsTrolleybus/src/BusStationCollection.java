import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BusStationCollection {
    //Словарь (хранилище) с автовокзалами
    final HashMap<String, BusStation<ITransport, IBarbell>> busStationStages;

    //Возвращение списка названий автовокзала
    public Set<String> Keys()
    {
        return busStationStages.keySet();
    }

    // Возвращение списка названий мест автовокзала
    public DefaultListModel<BusStation<ITransport, IBarbell>> modelList;

    //Ширина окна отрисовки
    private final int pictureWidth;

    //Высота окна отрисовки
    private final int pictureHeight;

    //Конструктор
    public BusStationCollection(int pictureWidth, int pictureHeight)
    {
        busStationStages = new HashMap<>();
        modelList =  new DefaultListModel<>();
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }

    //Добавление автовокзала
    public BusStation<ITransport, IBarbell> AddBusStation(String name)
    {
        if (!busStationStages.containsKey(name))
        {
            BusStation<ITransport, IBarbell> busStation = new BusStation<>(pictureWidth, pictureHeight);
            busStation.setName(name);
            busStationStages.put(name, busStation);
            modelList.addElement(busStation);
            return busStation;
        }
        return null;
    }

    //Удаление автовокзала
    public void DelBusStation(String name)
    {
        if(busStationStages.containsKey(name)){
            BusStation busStation = busStationStages.get(name);
            busStationStages.remove(name);
            modelList.removeElement(busStation);
        }
    }

    //Доступ к автовокзалу
    public BusStation<ITransport, IBarbell> index(String ind)
    {
        return busStationStages.getOrDefault(ind, null);
    }

    //Индексатор с 2 параметрами
    public ITransport index(String key, int index)
    {
        if(busStationStages.containsKey(key)){
            return busStationStages.get(key).indexer(index);
        }
        return null;
    }

}
