package javamudlab;

public class Character{
    private int maxHp, hp, attack, defense, level;
    private int place, xp, gold;
    private String name;

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp>maxHp){
            this.hp = maxHp;
        } else {
            if(hp<0){
                this.hp = 0;
            } else {
                this.hp = hp;
            }
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Character(String name, int maxHp, int attack, int defense){
        this(name, maxHp, attack, defense, 0, 0, 1);
    }

    public Character(String name, int maxHp, int attack, int defense, int xp, int gold, int level) {
//        super(); // Is it necessary to call super() here?
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.xp = xp;
        this.gold = gold;
        this.level = level;
    }
    
    public String toString(){
    	return name + "'s maxHP: " + maxHp + " Attack: " + attack + " Defense: " + defense;
    }
}
