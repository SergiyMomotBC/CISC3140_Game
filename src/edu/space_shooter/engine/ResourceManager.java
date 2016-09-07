package edu.space_shooter.engine;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class ResourceManager
{
    public ResourceManager(String resourcesFolderPath)
    {
        this.resourcesFolder = resourcesFolderPath;
        this.resources = new HashMap<>();
    }

    public Image loadImage(String fileName)
    {
        if(!resources.containsKey(fileName))
            try{
                resources.put(fileName, new Resource(ImageIO.read(
                        new File(resourcesFolder + "/images/" + fileName)
                )));
            } catch (IOException e) {
                return null;
            }

        resources.get(fileName).refCount++;
        return (Image) resources.get(fileName).file;
    }

    public Clip loadSound(String fileName)
    {
        if (!resources.containsKey(fileName)) {
            try {
                AudioInputStream ai = AudioSystem.getAudioInputStream(
                        new File(resourcesFolder + "/sounds/" + fileName)
                );
                Clip sound = AudioSystem.getClip();
                sound.open(ai);
                resources.put(fileName, new Resource(sound));
            } catch (UnsupportedAudioFileException e) {
                System.out.println("File is not supported...");
                return null;
            } catch (IOException e) {
                System.out.println("Cannot access a file...");
                return null;
            } catch (LineUnavailableException e) {
                System.out.println("Cannot read from a file...");
                return null;
            }
        }

        resources.get(fileName).refCount++;
        return (Clip) resources.get(fileName).file;
    }

    public void freeResource(String fileName)
    {
        if(resources.containsKey(fileName)) {
            resources.get(fileName).refCount--;
            if(resources.get(fileName).refCount == 0)
                resources.remove(fileName);
        }
    }

    private Map<String, Resource>   resources;
    private String                  resourcesFolder;

    private class Resource
    {
        public Resource(Object file)
        {
            this.file = file;
            refCount = 0;
        }

        public Object   file;
        public int      refCount;
    }
}
