import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

public class DrawCards {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the lvl :");
        int lvl = sc.nextInt();

        double[][] tierProbs = {
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0.75, 0.25, 0, 0, 0},
                {0.55, 0.30, 0.15, 0, 0},
                {0.45, 0.33, 0.20, 0.02, 0},
                {0.25, 0.40, 0.30, 0.05, 0},
                {0.19, 0.30, 0.35, 0.15, 0.01},
                {0.16, 0.20, 0.35, 0.25, 0.04},
                {0.9, 0.15, 0.30, 0.30, 0.16}};

        //probabilities
        double tier1Prob = tierProbs[lvl - 1][0] / 13.0;
        double tier2Prob = tierProbs[lvl - 1][1] / 13.0;
        double tier3Prob = tierProbs[lvl - 1][2] / 13.0;
        double tier4Prob = tierProbs[lvl - 1][3] / 12.0;
        double tier5Prob = tierProbs[lvl - 1][4] / 8;
        System.out.println(tier1Prob);
        Tier[] tiers = {new Tier(tier1Prob, "Legendary"), new Tier(tier2Prob, "Rare"), new Tier(tier3Prob, "Common")};

        // create an array of card objects
        Card[] cards = {
                new Card("Ashe", tiers[0], 1),
                new Card("Blitzcrank", tiers[0], 1),
                new Card("Galio", tiers[0], 1),
                new Card("Gankplank", tiers[0], 1),
                new Card("Kayle", tiers[0], 1),
                new Card("Lulu", tiers[0], 1),
                new Card("Lux", tiers[0], 1),
                new Card("Poppy", tiers[0], 1),
                new Card("Nasus", tiers[0], 1),
                new Card("Renekton", tiers[0], 1),
                new Card("Sylas", tiers[0], 1),
                new Card("Talon", tiers[0], 1),
                new Card("Wukong", tiers[0], 1),

                new Card("Annie", tiers[1], 2),
                new Card("Camille", tiers[1], 2),
                new Card("Draven", tiers[1], 2),
                new Card("Ezreal", tiers[1], 2),
                new Card("Fiora", tiers[1], 2),
                new Card("Jinx", tiers[1], 2),
                new Card("Lee Sin", tiers[1], 2),
                new Card("Malphite", tiers[1], 2),
                new Card("Rell", tiers[1], 2),
                new Card("Sivir", tiers[1], 2),
                new Card("Vi", tiers[1], 2),
                new Card("Yasuo", tiers[1], 2),
                new Card("Yuumi", tiers[1], 2),

                new Card("Alistar", tiers[2], 3),
                new Card("Cho'Gath", tiers[2], 3),
                new Card("Jax", tiers[2], 3),
                new Card("Kai'sa", tiers[2], 3),
                new Card("LeBlanc", tiers[2], 3),
                new Card("Nilah", tiers[2], 3),
                new Card("Rammus", tiers[2], 3),
                new Card("Riven", tiers[2], 3),
                new Card("Senna", tiers[2], 3),
                new Card("Sona", tiers[2], 3),
                new Card("Vayne", tiers[2], 3),
                new Card("Vel'koz", tiers[2], 3),
                new Card("Zoe", tiers[2], 3)
        };

        Random rng = new Random();

        // initialize counters for each card
        HashMap<Card, Integer> counters = new HashMap<>();
        for (Card card : cards) {
            counters.put(card, 0);
        }

        //user input for target card
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter the name of the card you want to draw multiple of:");
        String targetCardName = sc2.nextLine();
        Card targetCard = null;

        for (Card card : cards) {
            if (card.name.equals(targetCardName)) {
                targetCard = card;
                break;
            }
        }
        if (targetCard == null) {
            System.out.println("Invalid card name, defaulting to Ashe");
            targetCard = cards[0];
        }

        //set total draws and cost per 5 draws
        int totalDraws = 1000;
        int cost = 2;
        int goldSpent = 0;
        int actualDraws = 0;

        //simulation var
        int simulations = 1;
        int currentSimulation = 1;
        int totalGoldSpent = 0;

        while (currentSimulation <= simulations) {
            System.out.println("Simulation " + currentSimulation + ": ");

            // draw cards
            outerloop:
            for (int i = 0; i < totalDraws; i++) {
                Card drawnCard = drawCard(cards, rng);
                //System.out.println("Drew the " + drawnCard.name + "!");
                //System.out.println("Rarity: " + drawnCard.rarity);
                if (actualDraws % 5 == 0) {
                    goldSpent += cost;
                }
                //System.out.println(actualDraws);
                if (drawnCard.name.equals(targetCard.name)) {
                    //System.out.println("Good Card");
                    goldSpent += drawnCard.goldCost;
                    int count = counters.get(drawnCard);
                    count++;
                    //System.out.println(count);
                    counters.put(drawnCard, count);
                    actualDraws++; //increment actualDraws here
                    if (count == 9) {
                        //System.out.println("Drew 9 " + drawnCard.name + "'s , Exiting the program. " + actualDraws + " cards drew");
                        //System.out.println("Gold Spent: " + goldSpent);
                        counters.put(drawnCard, 0);
                        actualDraws = 0;
                        break outerloop;
                    }
                } else {
                    // continue drawing until we get the target card
                    actualDraws++;
                }
            }
            System.out.println("Gold Spent: " + goldSpent);
            totalGoldSpent += goldSpent;
            goldSpent = 0;
            currentSimulation++;
        }
        double averageGoldSpent = (double) totalGoldSpent / simulations;
        System.out.println("Average gold spent over " + simulations + " simulations: " + averageGoldSpent);
    }

    public static Card drawCard(Card[] cards, Random rng) {
        double rand = rng.nextDouble();
        double cumulativeProb = 0.0;
        for (int j = 0; j < cards.length; j++) {
            cumulativeProb += cards[j].probability;
            if (rand <= cumulativeProb) {
                return cards[j];
            }
        }
        return null;
    }
}

class Card {
    String name;
    Tier rarity;
    double probability;
    int goldCost;

    public Card(String name, Tier rarity, int goldCost) {
        this.name = name;
        this.rarity = rarity;
        this.probability = rarity.probability;
        this.goldCost = goldCost;
    }
}

class Tier {
    double probability;
    String name;

    public Tier(double probability, String name) {
        this.probability = probability;
        this.name = name;
    }
}

