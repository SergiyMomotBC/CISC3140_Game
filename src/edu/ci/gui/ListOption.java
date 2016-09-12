package edu.ci.gui;

import edu.ci.engine.Renderer;
import java.awt.*;

public class ListOption
{
    public ListOption(String optionName, boolean isActive, boolean isSelected)
    {
        this.name = optionName;
        this.isActive = isActive;
        this.isSelected = isSelected;
    }

    public void select() { isSelected = true; }
    public void unselect() { isSelected = false; }
    public void disable() { isActive = false; }
    public void enable() { isActive = true; }

    public void render(Renderer r, Point position)
    {
        if(!isActive) {

        } else if(isSelected) {

        } else {

        }
    }

    private String      name;
    private boolean     isActive;
    private boolean     isSelected;
}
