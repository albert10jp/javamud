package javamudlab;

class LabMain {
	final static int MINHP = 1;
	final static int MAXHP = 100;
	final static int MINATTACK = 1;
	final static int MINDEFENSE = 0;

	public static boolean isBattleFinished(Character player, Character opponent,
			int dmgDealt, int dmgTook) {

		// the player will never win
		// as they cannot deal any damage to their opponent
		if (dmgDealt <= 0) {
			player.setHp(0);
			return true;
		}

		if (dmgTook < 0)
			dmgTook = 0;

		opponent.setHp(opponent.getHp() - dmgDealt);
		player.setHp(player.getHp() - dmgTook);

		if (player.getHp() <= 0) {
			return true;
		} else if (opponent.getHp() <= 0) {
			return true;
		}

		return false;
	}

	public static int didTheyWin(Character player, Character opponent){
		int dmgDealt = player.getAttack() - opponent.getDefense();
		int dmgTook = opponent.getAttack() - player.getDefense();
		int round = 0;
		boolean isFinished = true;

		do {
			round++;
			isFinished = isBattleFinished(player, opponent, dmgDealt, dmgTook);
		} while (round<MAXHP && !isFinished);

		if (player.getHp() > 0) {
			return round;
		}

		return 0;
	}

	static void lab() {
		for(int assassinMaxHP = MINHP; assassinMaxHP < MAXHP; assassinMaxHP+=MINHP){
			Util.pln("assassinMaxHP: " + assassinMaxHP);
			for(int assassinAttack = MINATTACK; assassinAttack < MAXHP; assassinAttack+=MINHP){
				for(int assassinDefense = 1; assassinDefense < MAXHP; assassinDefense+=MINHP){
					Util.pln("assassinDefense: " + assassinDefense);
					for(int warriorMaxHP = 5; warriorMaxHP < MAXHP; warriorMaxHP+=MINHP){
						Util.pln("warriorMaxHP: " + warriorMaxHP);
						for(int warriorAttack = MINATTACK; warriorAttack < MAXHP; warriorAttack+=MINHP){
							Util.pln( "assassinAttack: " + assassinAttack +
									" assassinDefense:" + assassinDefense +
									" warriorMaxHP: " + warriorMaxHP +
									" warriorAttack: " + warriorAttack);
							for(int warriorDefense = MINDEFENSE; warriorDefense < MAXHP; warriorDefense+=MINHP){
								for(int tankMaxHP = MINHP; tankMaxHP < MAXHP; tankMaxHP+=MINHP){
									for(int tankAttack = MINATTACK; tankAttack < MAXHP; tankAttack+=MINHP){
										for(int tankDefense = MINDEFENSE; tankDefense < MAXHP; tankDefense+=MINHP){
											Character assassin1 = new Character("A", assassinMaxHP, assassinAttack, assassinDefense);
											Character warrior1 = new Character("W", warriorMaxHP, warriorAttack, warriorDefense);
											if(didTheyWin(assassin1, warrior1)>0){
												Character warrior2 = new Character("W", warriorMaxHP, warriorAttack, warriorDefense);
												Character tank2 = new Character("T", tankMaxHP, tankAttack, tankDefense);
												if(didTheyWin(warrior2, tank2)>0){
													Character tank3 = new Character("T", tankMaxHP, tankAttack, tankDefense);
													Character assassin3 = new Character("A", assassinMaxHP, assassinAttack, assassinDefense);
													if(didTheyWin(tank3, assassin3)>0){
														Util.pln("- - -");
														Util.pln(assassin1);
														Util.pln(warrior1);
														Util.pln(tank2);
														Util.pln("- - -");
														System.exit(0);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		lab();
	}
}