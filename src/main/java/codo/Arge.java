package codo;

class Arge {

    public static int nbYear(int p0, double percent, int aug, int p) {
        int population = p0;
        int counter = 0;

        while(population <= p) {
            int increase = (int) ((double) population * percent / 100);
            population += increase + aug;
            counter++;
        }
        return counter;
    }
}
