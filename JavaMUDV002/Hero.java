package JavaMUDV002;

import java.io.File;
import java.util.Objects;

public class Hero extends Character implements java.io.Serializable{
    private int numAtkUpgrades, numDefUpgrades, pots;

    public Hero(String name) {
        super(name, 1100, 300, 200, 0, 0,1);
        numAtkUpgrades = 0;
        numDefUpgrades = 0;
        pots = 0;
        setPlace(0); // 0: Palace; 1: Woods(Lv1/Goblin); 2: Mountain(Lv2/Ogre); 3: Dragon Valley
    }

    public int chooseTrait(){
        Util.printHeading("Choose a trait");
        String[] choices = {"Upgrade attack", "Upgrade defense"};
        int upTrait = Util.makeAChoice(choices);
        return upTrait;
    }

    public void heroInfo(){
        Util.clearConsole();
        Util.printHeading(getName() + "'s INFO");
        Util.pln("Level: " + getLevel());
        Util.pln("Golds: " + getGold());
        Util.pln("XP: " + getXp());
        Util.pln("HP: " + getHp() + "/" + getMaxHp());
        Util.pln("Attack: " + getAttack());
        Util.pln("Defense: " + getDefense());
        Util.pln("Potions: " + getPots());
        Util.pln("Place:" + getPlace());
        Util.printSeperator(20);
        Util.anythingToContinue();
    }

    public void upLevel(){
        if(shouldUpLevel()){
            Util.clearConsole();
            Util.pln("Congratulations! Level UP!");
            int upTrait = chooseTrait();
            setXp(getXp()-nextLevel());
            setLevel(getLevel()+1);
            setMaxHp(getMaxHp()+100);
            setHp(getMaxHp());
            if(upTrait==1){
                numAtkUpgrades++;
                setAttack(getAttack()+100);
                Util.printHeading(getName() + " chose Upgrade Dttack");
            }else if(upTrait==2){
                numAtkUpgrades++;
                setDefense(getDefense()+100);
                Util.printHeading(getName() + " chose Upgrade Defense");
            }
            heroInfo();
        }
    }

    public boolean shouldUpLevel(){
        if(getXp()>=nextLevel()){
            return true;
        }
        return false;
    }

    public int nextLevel(){
        int ret = 0;
        int currentLevel = getLevel()+1;
        ret = (int)(5 * (Math.pow(currentLevel, 2)) - (5*currentLevel));
        return ret;
    }

    public int getNumAtkUpgrades() {
        return numAtkUpgrades;
    }

    public void setNumAtkUpgrades(int numAtkUpgrades) {
        this.numAtkUpgrades = numAtkUpgrades;
    }

    public int getNumDefUpgrades() {
        return numDefUpgrades;
    }

    public void setNumDefUpgrades(int numDefUpgrades) {
        this.numDefUpgrades = numDefUpgrades;
    }

    public int getPots() {
        return pots;
    }

    public void setPots(int pots) {
        this.pots = pots;
    }

    public static Hero getHero(){
        Hero player = Util.deserializeHero();
        if(Objects.isNull(player)){
            String name = "DALABENGBA";
            player = new Hero(name);
        }
        return player;
    }

    public static void clearDb(){
        File file = new File("gamedb");
        if(file.exists()){
            file.delete();
        }
    }
}
