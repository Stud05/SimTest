import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

public class Units3Stars {
    public static void main(String[] args) {


        //input lvl
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the lvl :");
        int lvl = sc.nextInt();

        //Probabilities table base
        double[][] tierProbs = {
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0.75, 0.25, 0, 0, 0},
                {0.55, 0.30, 0.15, 0, 0},
                {0.45, 0.33, 0.20, 0.02, 0},
                {0.25, 0.40, 0.30, 0.05, 0},
                {0.19, 0.30, 0.35, 0.15, 0.01},
                {0.16, 0.20, 0.35, 0.25, 0.04},
                {0.09, 0.15, 0.30, 0.30, 0.16}
        };

        //probabilities base
        double tier1Prob = tierProbs[lvl - 1][0] / 13.0;
        double tier2Prob = tierProbs[lvl - 1][1] / 13.0;
        double tier3Prob = tierProbs[lvl - 1][2] / 13.0;
        double tier4Prob = tierProbs[lvl - 1][3] / 12.0;
        double tier5Prob = tierProbs[lvl - 1][4] / 8;

        // create an array of unit objects
        Unit[] units = {
                new Unit("Ashe", tier1Prob, 1, randomPool1()),
                new Unit("Blitzcrank", tier1Prob, 1, randomPool1()),
                new Unit("Galio", tier1Prob, 1, randomPool1()),
                new Unit("Gankplank", tier1Prob, 1, randomPool1()),
                new Unit("Kayle", tier1Prob, 1, randomPool1()),
                new Unit("Lulu", tier1Prob, 1, randomPool1()),
                new Unit("Lux", tier1Prob, 1, randomPool1()),
                new Unit("Poppy", tier1Prob, 1, randomPool1()),
                new Unit("Nasus", tier1Prob, 1, randomPool1()),
                new Unit("Renekton", tier1Prob, 1, randomPool1()),
                new Unit("Sylas", tier1Prob, 1, randomPool1()),
                new Unit("Talon", tier1Prob, 1, randomPool1()),
                new Unit("Wukong", tier1Prob, 1, randomPool1()),

                new Unit("Annie", tier2Prob, 2, randomPool2()),
                new Unit("Camille", tier2Prob, 2, randomPool2()),
                new Unit("Draven", tier2Prob, 2, randomPool2()),
                new Unit("Ezreal", tier2Prob, 2, randomPool2()),
                new Unit("Fiora", tier2Prob, 2, randomPool2()),
                new Unit("Jinx", tier2Prob, 2, randomPool2()),
                new Unit("Lee Sin", tier2Prob, 2, randomPool2()),
                new Unit("Malphite", tier2Prob, 2, randomPool2()),
                new Unit("Rell", tier2Prob, 2, randomPool2()),
                new Unit("Sivir", tier2Prob, 2, randomPool2()),
                new Unit("Vi", tier2Prob, 2, randomPool2()),
                new Unit("Yasuo", tier2Prob, 2, randomPool2()),
                new Unit("Yuumi", tier2Prob, 2, randomPool2()),

                new Unit("Alistar", tier3Prob, 3, randomPool3()),
                new Unit("Cho'Gath", tier3Prob, 3, randomPool3()),
                new Unit("Jax", tier3Prob, 3, randomPool3()),
                new Unit("Kai'sa", tier3Prob, 3, randomPool3()),
                new Unit("LeBlanc", tier3Prob, 3, randomPool3()),
                new Unit("Nilah", tier3Prob, 3, randomPool3()),
                new Unit("Rammus", tier3Prob, 3, randomPool3()),
                new Unit("Riven", tier3Prob, 3, randomPool3()),
                new Unit("Senna", tier3Prob, 3, randomPool3()),
                new Unit("Sona", tier3Prob, 3, randomPool3()),
                new Unit("Vayne", tier3Prob, 3, randomPool3()),
                new Unit("Vel'koz", tier3Prob, 3, randomPool3()),
                new Unit("Zoe", tier3Prob, 3, randomPool3()),

                new Unit("Aurelion Sol", tier4Prob, 4, randomPool4()),
                new Unit("Bel'Veth", tier4Prob, 4, randomPool4()),
                new Unit("Ekko", tier4Prob, 4, randomPool4()),
                new Unit("Miss Fortune", tier4Prob, 4, randomPool4()),
                new Unit("Samira", tier4Prob, 4, randomPool4()),
                new Unit("Sejuani", tier4Prob, 4, randomPool4()),
                new Unit("Sett", tier4Prob, 4, randomPool4()),
                new Unit("Soraka", tier4Prob, 4, randomPool4()),
                new Unit("Taliyah", tier4Prob, 4, randomPool4()),
                new Unit("Viego", tier4Prob, 4, randomPool4()),
                new Unit("Zac", tier4Prob, 4, randomPool4()),
                new Unit("Zed", tier4Prob, 4, randomPool4()),

                new Unit("Aphelios", tier5Prob, 5, randomPool5()),
                new Unit("Fiddlesticks", tier5Prob, 5, randomPool5()),
                new Unit("Janna", tier5Prob, 5, randomPool5()),
                new Unit("Leona", tier5Prob, 5, randomPool5()),
                new Unit("Mordekaiser", tier5Prob, 5, randomPool5()),
                new Unit("Nunu", tier5Prob, 5, randomPool5()),
                new Unit("Syndra", tier5Prob, 5, randomPool5()),
                new Unit("Urgot", tier5Prob, 5, randomPool5())
        };

        Random rng = new Random();

        // initialize counters for each unit
        HashMap<Unit, Integer> counters = new HashMap<>();
        for (Unit unit : units) {
            counters.put(unit, 0);
        }

        //all units print
        for (Unit unit : units) {
            unit.printInfo();
        }

        //user input for target unit
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter the name of the unit you want to 3 stars:");
        String targetUnitName = sc2.nextLine();
        Unit targetUnit = null;

        int poolTier1 = 0;
        int poolTier2 = 0;
        int poolTier3 = 0;
        int poolTier4 = 0;
        int poolTier5 = 0;

        for (Unit unit : units) {
            switch (unit.goldCost) {
                case 1 -> poolTier1 += unit.pool;
                case 2 -> poolTier2 += unit.pool;
                case 3 -> poolTier3 += unit.pool;
                case 4 -> poolTier4 += unit.pool;
                case 5 -> poolTier5 += unit.pool;
                default -> {
                }
            }
            if (unit.name.equals(targetUnitName)) {
                targetUnit = unit;
            }
        }
        if (targetUnit == null) {
            System.out.println("Invalid unit name, defaulting to Ashe");
            targetUnit = units[0];
        }

        //Test pool size
        System.out.println(poolTier1 + " " + poolTier2 + " " + poolTier3 + " " + poolTier4 + " " + poolTier5);

        //set total draws and cost per 5 draws
        int totalDraws = 10000;
        int cost = 2;
        int goldSpent = 0;
        int actualDraws = 0;

        //simulation var
        int simulations = 100000;
        int currentSimulation = 1;
        int totalGoldSpent = 0;

        //simulation loop
        while (currentSimulation <= simulations) {
            System.out.println("\nSimulation " + currentSimulation + ": ");

            // draw units
            for (int i = 0; i < totalDraws; i++) {
                Unit drawnUnit1 = drawUnit(units, rng);
                Unit drawnUnit2 = drawUnit(units, rng);
                Unit drawnUnit3 = drawUnit(units, rng);
                Unit drawnUnit4 = drawUnit(units, rng);
                Unit drawnUnit5 = drawUnit(units, rng);
                System.out.println("Shop " + i + " drew: " + drawnUnit1.name + ", " + drawnUnit2.name + ", " + drawnUnit3.name + ", " + drawnUnit4.name + ", " + drawnUnit5.name);
                goldSpent += cost;

                //System.out.println(actualDraws);
                if (drawnUnit1.name.equals(targetUnit.name)) {
                    //System.out.println("Good Unit");
                    goldSpent += drawnUnit1.goldCost;
                    int count = counters.get(drawnUnit1);
                    count++;
                    //System.out.println(count);
                    counters.put(drawnUnit1, count);
                    actualDraws++; //increment actualDraws here
                    if (count == 9) {
                        //System.out.println("Drew 9 " + drawnUnit.name + "'s , Exiting the program. " + actualDraws + " units drew");
                        //System.out.println("Gold Spent: " + goldSpent);
                        counters.put(drawnUnit1, 0);
                        actualDraws = 0;
                        break;
                    }
                } else {
                    // continue drawing until we get the target unit
                    actualDraws++;
                }
                if (drawnUnit2.name.equals(targetUnit.name)) {
                    //System.out.println("Good Unit");
                    goldSpent += drawnUnit2.goldCost;
                    int count = counters.get(drawnUnit2);
                    count++;
                    //System.out.println(count);
                    counters.put(drawnUnit2, count);
                    actualDraws++; //increment actualDraws here
                    if (count == 9) {
                        //System.out.println("Drew 9 " + drawnUnit.name + "'s , Exiting the program. " + actualDraws + " units drew");
                        //System.out.println("Gold Spent: " + goldSpent);
                        counters.put(drawnUnit2, 0);
                        actualDraws = 0;
                        break;
                    }
                } else {
                    // continue drawing until we get the target unit
                    actualDraws++;
                }
                if (drawnUnit3.name.equals(targetUnit.name)) {
                    //System.out.println("Good Unit");
                    goldSpent += drawnUnit3.goldCost;
                    int count = counters.get(drawnUnit3);
                    count++;
                    //System.out.println(count);
                    counters.put(drawnUnit3, count);
                    actualDraws++; //increment actualDraws here
                    if (count == 9) {
                        //System.out.println("Drew 9 " + drawnUnit.name + "'s , Exiting the program. " + actualDraws + " units drew");
                        //System.out.println("Gold Spent: " + goldSpent);
                        counters.put(drawnUnit3, 0);
                        actualDraws = 0;
                        break;
                    }
                } else {
                    // continue drawing until we get the target unit
                    actualDraws++;
                }
                if (drawnUnit4.name.equals(targetUnit.name)) {
                    //System.out.println("Good Unit");
                    goldSpent += drawnUnit4.goldCost;
                    int count = counters.get(drawnUnit4);
                    count++;
                    //System.out.println(count);
                    counters.put(drawnUnit4, count);
                    actualDraws++; //increment actualDraws here
                    if (count == 9) {
                        //System.out.println("Drew 9 " + drawnUnit.name + "'s , Exiting the program. " + actualDraws + " units drew");
                        //System.out.println("Gold Spent: " + goldSpent);
                        counters.put(drawnUnit4, 0);
                        actualDraws = 0;
                        break;
                    }
                } else {
                    // continue drawing until we get the target unit
                    actualDraws++;
                }
                if (drawnUnit5.name.equals(targetUnit.name)) {
                    //System.out.println("Good Unit");
                    goldSpent += drawnUnit5.goldCost;
                    int count = counters.get(drawnUnit5);
                    count++;
                    //System.out.println(count);
                    counters.put(drawnUnit5, count);
                    actualDraws++; //increment actualDraws here
                    if (count == 9) {
                        //System.out.println("Drew 9 " + drawnUnit.name + "'s , Exiting the program. " + actualDraws + " units drew");
                        //System.out.println("Gold Spent: " + goldSpent);
                        counters.put(drawnUnit5, 0);
                        actualDraws = 0;
                        break;
                    }
                } else {
                    // continue drawing until we get the target unit
                    actualDraws++;
                }
            }
            System.out.println("Gold Spent: " + goldSpent);
            totalGoldSpent += goldSpent;
            goldSpent = 0;
            currentSimulation++;
        }
        double averageGoldSpent = (double) totalGoldSpent / simulations;
        System.out.println("\n" + "Average gold spent over " + simulations + " simulations: " + averageGoldSpent);
    }

    //draw units method
    public static Unit drawUnit(Unit[] units, Random rng) {
        double rand = rng.nextDouble();
        double cumulativeProb = 0.0;
        for (Unit unit : units) {
            cumulativeProb += unit.probability * unit.pool;
            if (rand <= cumulativeProb) {
                return unit;
            }
        }
        return null;
    }

    public static int randomPool1() {
        Random rng = new Random();
        return 29 - rng.nextInt(12);
    }

    public static int randomPool2() {
        Random rng = new Random();
        return 22 - rng.nextInt(8);
    }

    public static int randomPool3() {
        Random rng = new Random();
        return 18 - rng.nextInt(5);
    }

    public static int randomPool4() {
        Random rng = new Random();
        return 12 - rng.nextInt(4);
    }

    public static int randomPool5() {
        Random rng = new Random();
        return 10 - rng.nextInt(3);
    }
}