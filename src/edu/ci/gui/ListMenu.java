package edu.ci.gui;

import edu.ci.engine.Engine;
import edu.ci.engine.InputHandler;
import edu.ci.engine.Renderer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class ListMenu
{
    public static class OptionInfo
    {
        public OptionInfo(String text, Consumer<Void> action)
        {
            this.text = text;
            this.action = action;
        }

        private String text;
        private Consumer<Void> action;
    }

    public ListMenu(Point position, OptionInfo[] options)
    {
        this.position = position;
        currentOptionIndex = 0;

        normalImage = Engine.getResourceManager().loadImage("button_normal.png");
        selectedImage = Engine.getResourceManager().loadImage("button_selected.png");

        inputHandler = Engine.getInputHandler();

        this.options = new ListOption[options.length];

        for(int i = 0; i < options.length; i++) {
            this.options[i] = new ListOption(options[i].text, false, this);
            this.options[i].setAction(options[i].action);
        }

        this.options[0].select();
    }

    public void update()
    {
        if(inputHandler.isKeyPressedOnce(KeyEvent.VK_DOWN)) {
            options[currentOptionIndex].unselect();
            currentOptionIndex++;
            if(currentOptionIndex == options.length)
                currentOptionIndex = 0;
            options[currentOptionIndex].select();
        }

        if(inputHandler.isKeyPressedOnce(KeyEvent.VK_UP)) {
            options[currentOptionIndex].unselect();
            currentOptionIndex--;
            if(currentOptionIndex < 0)
                currentOptionIndex = options.length - 1;
            options[currentOptionIndex].select();
        }

        if(inputHandler.isKeyPressedOnce(KeyEvent.VK_ENTER)) {
            options[currentOptionIndex].executeAction();
        }
    }

    public void render(Renderer r)
    {
        int y = position.y;

        for(int i = 0; i < options.length; i++) {
            options[i].render(r, new Point(position.x, y));
            y += normalImage.getHeight(null) * 1.2;
        }
    }

    public Image getNormalImage() { return normalImage; }
    public Image getSelectedImage() { return selectedImage; }

    private ListOption[]    options;
    private int             currentOptionIndex;
    private Image           normalImage;
    private Image           selectedImage;
    private Point           position;
    private InputHandler    inputHandler;
}
