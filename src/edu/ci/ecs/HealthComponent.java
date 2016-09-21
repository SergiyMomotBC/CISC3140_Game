package edu.ci.ecs;

public class HealthComponent implements Component
{
    public HealthComponent(int initialHP)
    {
        this.hp = initialHP;
    }

    public void applyDamage(int amount)
    {
        hp -= amount;
    }

    public int getHp() { return hp; }

    private int hp;
}
