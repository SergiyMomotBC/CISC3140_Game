package edu.ci.ecs.components;

import java.awt.*;

public class SpriteComponent implements Component
{
    public SpriteComponent(Image sprite)
    {
        this.spriteImage = sprite;
    }

    public Image getSpriteImage() { return spriteImage; }
    public void changeSprite(Image newSpriteImage) { spriteImage = newSpriteImage; }
    public Dimension getImageSize() { return new Dimension(spriteImage.getWidth(null), spriteImage.getHeight(null)); }

    private Image   spriteImage;
}
