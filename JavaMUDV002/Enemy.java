package JavaMUDV002;

public class Enemy extends Character {
    public static String[] enemies = {"OgreTank", "OgreWarrior", "GoblinTank", "GoblinWarrior", "GoblinAssassin"};

    public Enemy(String name, int maxHp, int attack, int defense, int xp, int gold, int level) {

        super(name, maxHp, attack, defense, xp, gold, level);

    }

    public static Enemy goblinAssassin(){
        return new Enemy("GoblinAssassin", 500, 700, 50, 10, 5,1);
    }

    // todo: many other kinds of enemies, such as "OgreTank", "OgreWarrior" etc.

}
