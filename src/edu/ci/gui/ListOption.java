package edu.ci.gui;

import edu.ci.engine.Renderer;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.function.Consumer;

public class ListOption
{
    public ListOption(String optionName, boolean isSelected, ListMenu manager)
    {
        this.name = optionName;
        this.isSelected = isSelected;
        this.manager = manager;
    }

    public void select() { isSelected = true; }
    public void unselect() { isSelected = false; }

    public void render(Renderer r, Point position)
    {
        Image image;

        if(isSelected)
            image = manager.getSelectedImage();
        else
            image = manager.getNormalImage();

        r.renderSprite(image, position, new Dimension(image.getWidth(null), image.getHeight(null)));

        Rectangle2D rect = r.getGraphic2D()
                .getFontMetrics(new Font(null, Font.PLAIN, 48))
                .getStringBounds(name, r.getGraphic2D());

        int x = (int) (position.x + (image.getWidth(null) / 2 - rect.getWidth() / 2));
        int y = (int) (position.y + (image.getHeight(null) / 2 + rect.getHeight() / 2) - 15);

        r.renderText(name, new Point(x, y), 48, isSelected ? Color.white : Color.black);
    }

    public void setAction(Consumer<Void> action)
    {
        this.action = action;
    }

    public void executeAction()
    {
        action.accept(null);
    }

    private ListMenu manager;
    private String                  name;
    private boolean                 isSelected;
    private Consumer<Void>          action;
}
