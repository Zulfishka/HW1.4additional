import java.sql.SQLOutput;
import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static int medicHealth = 150;
    public static int medicSave = 100;
    public static String bossDefence;
    public static int[] heroesHealth = {260, 270, 250};
    public static int[] heroesDamage = {20, 10, 15};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic"};
    public static int roundNumber = 0;


    public static void main(String[] args) {
        printStatistic();

        while (!isGameFinished()) {
            playRound();
        }
    }
    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        medicine();
        iAmThor ();
        printStatistic();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType [randomIndex];
    }
    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType [i] == bossDefence){
                    Random random = new Random();
                    int coeff = random.nextInt(9);
                    damage = heroesDamage [i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }
    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth [i] - bossDamage<0) {
                    heroesHealth [i] = 0;
                } else {
                    heroesHealth [i] = heroesHealth [i]-bossDamage;
                }
            }
        }
    }

    public static boolean medicine() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (medicHealth > 0) {
                if (heroesHealth[i] > 0 && heroesHealth[i] < 100) {
                    heroesHealth[i] = medicSave + heroesHealth[i];
                } else {
                    heroesHealth[i]= medicSave + heroesHealth[i];
                }
            }
        }
        return true;
    }
    public static void iAmThor (){
        Random random = new Random();
        boolean Thor = random.nextBoolean();
        if (Thor) {
            bossDamage = 0;
            System.out.println("Boss is stuned");}
        else {
            bossDamage = 50;
        }
    }


    public static void printStatistic() {
        System.out.println("ROUND " + roundNumber + "-------------");
        /* String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
            } */
        System.out.println("Boss health:" + bossHealth + " damage:" + bossDamage + " defence: " + (bossDefence == null ? "No defence " : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health:" + heroesHealth[i] + " damage:" + heroesDamage[i]);
        }
    }
    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        }
        /* if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!");
            return true;
         }
        return false; */

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth [i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!");
        }
        return allHeroesDead;
    }

}

