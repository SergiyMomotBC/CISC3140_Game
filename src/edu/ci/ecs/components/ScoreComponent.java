package edu.ci.ecs.components;

public class ScoreComponent implements Component
{
    public ScoreComponent()
    {
        this.score = 0;
    }

    public long getScore()
    {
        return score;
    }

    public void increaseScoreBy(long scoreReceived)
    {
        score += scoreReceived;
    }

    private long score;
}
