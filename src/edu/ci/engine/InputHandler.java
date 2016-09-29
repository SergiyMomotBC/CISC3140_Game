package edu.ci.engine;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class InputHandler implements KeyListener
{
    private static int NUMBER_OF_KEYS = 1000;

    public InputHandler(JFrame window)
    {
        keyStates = new KeyState[NUMBER_OF_KEYS];
        for(int i = 0; i < NUMBER_OF_KEYS; ++i)
            keyStates[i] = new KeyState();

        window.addKeyListener(this);
    }

    public boolean isKeyPressedOnce(int key)
    {
        return keyStates[key].event == 1;
    }

    public boolean isKeyPressed(int key, short framesCount)
    {
        if(isKeyPressedOnce(key) && keyStates[key].framesPressed == framesCount) {
            keyStates[key].framesPressed = 0;
            return true;
        } else
            return false;
    }

    public char getLetterKeyPressed()
    {
        for(int c = 48; c <= 57; c++)
            if(isKeyPressedOnce(c))
                return (char) c;

        for(int c = 65; c <= 90; c++)
            if(isKeyPressedOnce(c))
                return (char) c;

        if(isKeyPressedOnce(95))
            return (char) 95;

        return '\0';
    }

    public boolean isKeyReleased(int key)
    {
        return keyStates[key].event == 2;
    }

    @Override
    public synchronized void keyTyped(KeyEvent keyEvent) {}

    @Override
    public synchronized void keyPressed(KeyEvent keyEvent)
    {
        keyStates[keyEvent.getKeyCode()].current = true;
    }

    @Override
    public synchronized void keyReleased(KeyEvent keyEvent)
    {
        keyStates[keyEvent.getKeyCode()].current = false;
    }

    public void pollKeyboard()
    {
        for(int i = 0; i < NUMBER_OF_KEYS; ++i)
        {
            if(keyStates[i].current)
                keyStates[i].framesPressed++;
            else
                keyStates[i].framesPressed = 0;

            if(!keyStates[i].previous && keyStates[i].current)
                keyStates[i].event = 1;
            else if(keyStates[i].previous && !keyStates[i].current)
                keyStates[i].event = 2;
            else
                keyStates[i].event = 0;

            keyStates[i].previous = keyStates[i].current;
        }
    }

    private class KeyState
    {
        public KeyState()
        {
            current = false;
            previous = false;
            framesPressed = 0;
            event = 0;
        }

        boolean     current;
        boolean     previous;
        short       framesPressed;
        byte        event;
    }

    private KeyState[] keyStates;
}
