class Unit {
    String name;
    double probability;
    int goldCost;
    int pool;

    public Unit(String name, double probability, int goldCost, int pool) {
        this.name = name;
        this.probability = probability;
        this.goldCost = goldCost;
        this.pool = pool;
        this.setRealProb();
    }

    public void poolMinus1() {
        this.pool -= 1;
        this.setRealProb();
    }

    private void setRealProb() {
        this.probability = this.probability / pool;
    }

    public void printInfo() {
        System.out.println("Name: " + this.name + ", pool size: " + this.pool + ", probability: " + this.probability + ", cost: " + this.goldCost);
    }
}