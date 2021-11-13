import java.awt.*;
import javax.swing.*;


public class BarbellAddSecondType implements IBarbell{
    private Barbell barbell;

    @Override
    public void setBarbell(int volue)
    {
        if (volue == 1)
        {
            barbell = Barbell.One;
        } else if (volue == 2)
        {
            barbell = Barbell.Two;
        } else if (volue == 3)
        {
            barbell = Barbell.Three;
        } else{
            barbell = Barbell.One;
        }
    }

    @Override
    public void DrawBarbell(Graphics g, Color color,  int x, int y) {
        switch (barbell) {
            case One:
                g.setColor(color);
                g.drawLine(x + 40, y + 48 , x - 10, y + 10);
                g.drawLine(x - 10, y + 10, x - 30, y + 20);
                g.drawLine(x + 45, y + 48, x - 5, y + 10);
                g.drawLine(x - 5, y + 10, x - 25, y + 20);
            case Two:
                g.drawLine(x + 60, y + 48 , x + 10, y + 10);
                g.drawLine(x + 10, y + 10, x - 10, y + 20);
                g.drawLine(x + 65, y + 48, x + 15, y + 10);
                g.drawLine(x + 15, y + 10, x - 5, y + 20);
            case Three:
                g.drawLine(x + 80, y + 48 , x +30, y + 10);
                g.drawLine(x + 30, y + 10, x + 10, y + 20);
                g.drawLine(x + 85, y + 48, x + 35, y + 10);
                g.drawLine(x + 35, y + 10, x + 15, y + 20);
                break;
        }
    }
}
