package JavaMUDV002;

public class GameLogic {
    static Hero player;
    public static boolean isRunning;
    // acts: see checkAct() method
    public static int act=1;

    public static void startGame() throws Exception{
        player = Hero.getHero();
        Util.clearConsole();
        System.out.println("\t\t\t-- JavaMUD --");
        if(player.getPlace()==0){
            Story.printIntro();
            player.setPlace(1);
        }

        // uncomment the following block to enable the player set hero's name
//        boolean nameSet=false;
//        String name;
//        do {
//            Util.clearConsole();
//            Util.printHeading("Your name: ");
//            name = Util.scanner.next();
//            Util.clearConsole();
//            Util.printHeading("Your name is "+name+".\nIs that correct?");
//            System.out.println("(1) Yes!");
//            System.out.println("(2) No, I want to change my name!");
//            int input = Util.readInt("-> ", 2);
//            if (input == 1){
//                nameSet = true;
//                player.setName(name);
//            }
//        }while (!nameSet);

        isRunning = true;
        gameLoop();
    }

    public static void checkAct(){ //printMenu
        Util.clearConsole();
        System.out.println("Choose an action");
        String[] choices = {"Fight Here", "Move On", "Hero Info", "Shop & Merchant (Cost 15 Golds)", "Hotel & Hospital (Cost 5 Golds)", "Start Over"};
        int act = Util.makeAChoice(choices);
        if(act==1){
            randomBattle();
        } else if(act==2){
            // todo: places
            // 0: village
            // 1: lvl 1 enemies
            // 2: lvl 2 enemies
//            place++;
        } else if(act==3){
            player.heroInfo();
        } else if(act==4){
            // Shop & Merchant
            if(player.getGold()<15){
                Util.pln(player.getName() + " doesn't have enough golds");
            }else {
                player.setPots(player.getPots() + 1);
                player.setGold(player.getGold() - 15);
                Util.pln(player.getName() + " got 1 potion");
            }
        } else if(act==5){
            // hotel + hospital
            if(player.getGold()<5){
                Util.pln(player.getName() + " doesn't have enough golds");
            }else {
                player.setHp(player.getMaxHp());
                player.setGold(player.getGold() - 5);
                Util.pln(player.getName() + "got a rest");
            }
        }  else if(act==6){
            Util.pln("Do you really want the hero back to level 1 and 0 XP?");
            String[] opts = {"Yes", "No"};
            int opt = Util.makeAChoice(opts);
            if(opt==1){
                Hero.clearDb();
            }else {

            }
        } else {
            Util.pln("error");
        }
    }

    public static void randomBattle(){
        Util.clearConsole();
        Util.printHeading("You encountered an evil minded creature. You'll have to fight it!");
//        Util.anythingToContinue();
        // todo: generate the enemies at random
        battle(Enemy.goblinAssassin());
    }

    public static boolean isBattleFinished(Hero player, Enemy enemy, int dmgDealt, int dmgTook){

        if(dmgTook < 0)
            dmgTook=10;

        if(dmgDealt < 0)
            dmgDealt = 10;

        enemy.setHp(enemy.getHp()-dmgDealt);
        player.setHp(player.getHp()-dmgTook);

        Util.clearConsole();
        Util.printHeading("Battle");
        Util.pln("You dealt " + dmgDealt + " damage to the " + enemy.getName() + ".");
        Util.printSeperator(15);
        Util.pln("The " + enemy.getName() + " dealt " + dmgTook + " damage to you.");
        if(player.getHp() <= 0){
            playerDied();
            return true;
        }else if(enemy.getHp() <= 0){
            Util.printHeading(player.getName() + " defeated the " + enemy.getName() + "!");
            player.setXp(player.getXp() + enemy.getXp());
            player.setGold(player.getGold() + enemy.getGold());
            Util.pln(player.getName() + " got " + enemy.getXp() + " XP!");
            Util.printSeperator(15);
            Util.pln(player.getName() + " got " + enemy.getGold() + " Gold!");
            Util.anythingToContinue();
            if(player.shouldUpLevel()){
                player.upLevel();
            }
            return true;
        }
        return false;
    }

    public static void battle(Enemy enemy){
        while (true){
            Util.printHeading(enemy.getName() +
                    "\nHP: " + enemy.getHp() + "/" + enemy.getMaxHp());
            Util.printHeading(player.getName() + "\nHP: " + player.getHp() + "/" + player.getMaxHp());
            System.out.println("Choose an action");
            Util.printSeperator(20);
            String[] choices = {"Attack", "Defend", "Run Away", "Auto", "Potion"};
            int act = Util.makeAChoice(choices);
            int dmgDealt = 0;
            int dmgTook = 0;

            if(act == 1){
                dmgDealt = player.getAttack() - enemy.getDefense()/2;
                dmgTook = enemy.getAttack() - player.getDefense()/2;
            } else if(act == 2){
                dmgDealt = player.getAttack() - enemy.getDefense()/2;
                dmgTook = enemy.getAttack() - player.getDefense();
            } else if(act == 3){
                Util.pln("run away");
                // todo: run away
            } else if(act == 4){
                Util.pln("auto");
                // todo: auto
            } else if(act == 5){
                if(player.getPots()>0){
                    player.setPots(player.getPots()-1);
                    player.setHp(player.getMaxHp());
                    Util.pln(player.getName() + " drank a potion that restored health to " + player.getMaxHp());
                } else {
                    Util.pln(player.getName() + " doesn't have any potions");
                }
            } else {
                Util.pln("Exceptions");
            }

            boolean isFinished = isBattleFinished(player, enemy, dmgDealt, dmgTook);
            if (isFinished){
                Util.serializeHero(player);
                break;
            }
        }
    }

    public static void playerDied(){
//        Util.clearConsole();
        Util.printHeading(player.getName() + " died...");
//        Util.printHeading("You earned " + player.getXp() + " XP on your journey. Try to earn more next time");
        Util.pln("Use Potion and Rest wisely next time!");
        isRunning = false;
    }

    public static void gameLoop(){
        while (isRunning) {
            checkAct();
        }
    }

}
