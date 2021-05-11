import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

//import statements
public class geneticSequence {
    static int[] parent1 = new int[20];//initializing int array for parent 2
    static int[] parent2 = new int[20];//initializing int array for parent 2
    static Map<String, int[]> arrays = new HashMap<>();//initializing a 2Dimensional Hashmap to store 20 genes

    public static void main(String[] args) {
        for (int i = 1; i < parent1.length + 1; i++) { //running a for loop to generate 20 genes and store it in a HashMap
            arrays.put("child" + i, new int[20]);
        }
        generateParents();//calling generate parents method to generate 2 parents
        System.out.println("Parent 1 is : " + Arrays.toString(parent1));
        System.out.println("Parent 2 is : " + Arrays.toString(parent2));
        crossOver();//calling cross over method to generate 2 crossover genes
        System.out.println("\n Generation: \n");
        generateChildren();//calling generate children method to generate 20 genes
        System.out.println("\n Mutation: \n");
        mutateChildren();//calling mutateChildren method to mutate the 20 genes
        System.out.println("\n Evaluation: \n");
        printBestChild();//calling printBestChild method to evaluate the best gene
    }

    public static void generateParents() {
        Random random = new Random();//using the random method to randomize the two parent arrays
        for (int i = 0; i < parent1.length; i++) {
            parent1[i] = random.nextInt(10);
            parent2[i] = random.nextInt(10);
        }
    }

    public static void crossOver() {
        Random random = new Random();//creating a random method to generate a random cross over point
        int crossoverPoint = random.nextInt(20);
        for (int i = crossoverPoint; i < parent1.length; i++) {//looping the crossOver
            int child1 = parent1[i];
            parent1[i] = parent2[i];
            parent2[i] = child1;
        }
        System.out.println("\n" + "cross over child 1: " + Arrays.toString(parent1) + ", Sum=> " + IntStream.of(parent1).sum());
        System.out.println("cross over child 2: " + Arrays.toString(parent2) + ", Sum=> " + IntStream.of(parent2).sum() + "\n");
        if (IntStream.of(parent1).sum() > IntStream.of(parent2).sum()) {//evaluating the best gene
            System.out.println("Crossover child 1 is the better gene: " + Arrays.toString(parent1) + ", Sum=> " + IntStream.of(parent1).sum() + "\n");
        } else {
            System.out.println("Crossover child 2 is the better gene: " + Arrays.toString(parent2) + ", Sum=> " + IntStream.of(parent2).sum() + "\n");
        }
    }

    public static void generateChildren() {
        Random random = new Random();
        for (int h = 1; h < parent1.length + 1; h++) {//applying crossOver for 20 arrays
            int crossoverPoint = random.nextInt(20);
            System.out.println("Cross over point is : " + crossoverPoint);
            for (int b = crossoverPoint; b < parent1.length; b++) {
                int childd = parent1[b];
                parent1[b] = parent2[b];
                parent2[b] = childd;
            }
            for (int a = 0; a < parent1.length; a++) { //storing the new crossOver genes in the hashmap
                arrays.get("child" + h)[a] = parent1[a];
            }
            System.out.println("Child" + h + ": " + Arrays.toString(arrays.get("child" + h)) + ", Sum= " + IntStream.of(arrays.get("child" + h)).sum());
        }
        System.out.println(arrays.size());
    }

    public static void mutateChildren() {
        Random random = new Random();
        int temp1;
        for (int i = 1; i < parent1.length + 1; i++) {//generating a random mutationPoint and a random Destination point to apply the mutation to.
            int mutationPoint = random.nextInt(20);
            int mutationDestination = random.nextInt(20);
            System.out.println(mutationPoint + " md= " + mutationDestination);
            temp1 = arrays.get("child" + i)[mutationPoint];
            if (arrays.get("child" + i)[mutationDestination] < arrays.get("child" + i)[mutationPoint]) {
                arrays.get("child" + i)[mutationDestination] = temp1;
            } else {
                mutationPoint = random.nextInt(20);
                mutationDestination = random.nextInt(20);
                temp1 = arrays.get("child" + i)[mutationPoint];
                if (arrays.get("child" + i)[mutationDestination] < arrays.get("child" + i)[mutationPoint]) {
                    arrays.get("child" + i)[mutationDestination] = temp1;
                }
            }
            System.out.println("Child " + i + ": " + Arrays.toString(arrays.get("child" + i)) + ", Sum= " + IntStream.of(arrays.get("child" + i)).sum());
        }
    }

    public static void printBestChild() {
        int sum = 0, childNo = 0, temp = 0;
        for (int f = 1; f < parent1.length + 1; f++) {//evaluating the best child out of the 20 genes
            temp = IntStream.of(arrays.get("child" + f)).sum();
            if (temp > sum) {
                sum = temp;
                childNo = f;
            }
        }
        System.out.println("\n" + "The best child is: Child " + childNo + "\n" + Arrays.toString(arrays.get("child" + childNo)) + " Sum => " + sum);
    }
}