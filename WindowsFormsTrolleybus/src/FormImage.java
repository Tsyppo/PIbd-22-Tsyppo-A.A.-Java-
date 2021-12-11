import java.awt.*;
import javax.swing.*;

// Класс для отрисовки изображения на форме
public class FormImage extends JComponent
{
    Image image;
    public FormImage() { super(); }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(image, 0, 0, this);
    }
}
