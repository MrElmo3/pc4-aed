package uni.edu.pe;

import java.util.Random;

public class App 
{
	public static void main( String[] args )
	{
		int[] treeHeights = {6, 14, 22, 25};
		int numIterations = 8;

		for (int height : treeHeights) {
			System.out.println("Tree Height: " + height);
			System.out.println(";----------------------------------------");
			Random random2 = new Random(2);
			BinarySearchTree tree = generateRandomTree(height);

			for (int i = 0; i < numIterations; i++) {

				if (i % 2 == 0) {
					System.out.println("Iteration " + (i + 1) + ": Deletion");
					performAlternatingOperations(tree, random2, true);
				}

				else {
					System.out.println("Iteration " + (i + 1) + ": Insertion");
					performAlternatingOperations(tree, random2, false);
				}
		
				int expectedIPL = calculateExpectedIPL(height);
				System.out.println("Expected IPL: " + expectedIPL);
				System.out.println("Final IPL: " + tree.getInternalPathLength());
				System.out.println("----------------------------------------");
			}
		}
	}

	private static BinarySearchTree generateRandomTree(int height) {
		Random random = new Random(1);
		BinarySearchTree tree = new BinarySearchTree();
		
		int iterations = (1 << height) -1;

		for (int i = 0; i < iterations; i++) {
			tree.insert(random.nextInt(1000000));
		}
		
		return tree;
	}

	private static int calculateExpectedIPL(int height) {
		return ((1 << height + 1) * (height - 1)) + 2;
	}

	private static void performAlternatingOperations(BinarySearchTree tree, Random random, boolean isDeletion) {

		boolean isSymmetric = random.nextBoolean();
		int key = random.nextInt(1000000);
		if (!isDeletion) {
			tree.insert(key);
		} 
		else {
			if (isSymmetric) {
				tree.deleteSymmetric(key);
			} else {
				tree.deleteAsymmetric(key);
			}
		}
	}
}
