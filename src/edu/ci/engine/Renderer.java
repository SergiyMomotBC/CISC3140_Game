package edu.ci.engine;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public final class Renderer
{
    public Renderer(JFrame windowTarget, Dimension resolution)
    {
        this.resolution = resolution;
        frame = new Canvas();
        frame.setSize(resolution);
        frame.setIgnoreRepaint(true);
        windowTarget.add(frame);
        frame.createBufferStrategy(2);
        buffer = frame.getBufferStrategy();
        windowTarget.pack();
    }

    public Graphics2D getGraphic2D()
    {
        return (Graphics2D) buffer.getDrawGraphics();
    }

    public void prepareFrame()
    {
        Graphics g = buffer.getDrawGraphics();
        g.setColor(Color.black);
        g.clearRect(0, 0, resolution.width, resolution.height);
        g.fillRect(0, 0, resolution.width, resolution.height);
        g.dispose();
    }

    public void renderSprite(Image sprite, Point position, Dimension size)
    {
        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(sprite, position.x, position.y, size.width, size.height, null);
        g.dispose();
    }

    public void renderSprite(Image sprite, Point position)
    {
        renderSprite(sprite, position, new Dimension(sprite.getWidth(null), sprite.getHeight(null)));
    }

    public void renderText(String text, Point position, int fontSize, Color color)
    {
        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(new Font(null, Font.PLAIN, fontSize));
        g.setColor(color);
        g.drawString(text, position.x, position.y);
        g.dispose();
    }

    public void presentFrame()
    {
        buffer.show();
    }

    private Canvas              frame;
    private BufferStrategy      buffer;
    private Dimension           resolution;
}
