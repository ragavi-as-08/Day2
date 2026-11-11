import java.util.*;

public class ECommerceAnalysis {

    // ---------------- TASK 1: BINARY SEARCH TREE ----------------
    static class TreeNode {
        String category;
        TreeNode left, right;

        TreeNode(String category) {
            this.category = category;
        }
    }

    static class CategoryBST {
        TreeNode root;

        void insert(String category) {
            root = insertNode(root, category);
        }

        private TreeNode insertNode(TreeNode node, String category) {
            if (node == null) return new TreeNode(category);

            int result = category.compareToIgnoreCase(node.category);
            if (result < 0) node.left = insertNode(node.left, category);
            else if (result > 0) node.right = insertNode(node.right, category);
            return node; // Ignore duplicate categories.
        }

        boolean search(String category) {
            TreeNode current = root;
            while (current != null) {
                int result = category.compareToIgnoreCase(current.category);
                if (result == 0) return true;
                current = (result < 0) ? current.left : current.right;
            }
            return false;
        }

        void inorder(TreeNode node) {
            if (node == null) return;
            inorder(node.left);
            System.out.print(node.category + " ");
            inorder(node.right);
        }

        void preorder(TreeNode node) {
            if (node == null) return;
            System.out.print(node.category + " ");
            preorder(node.left);
            preorder(node.right);
        }

        void postorder(TreeNode node) {
            if (node == null) return;
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.category + " ");
        }
    }

    // ---------------- TASK 2: HASH TABLE WITH CHAINING ----------------
    static class ChainedHashTable {
        private final LinkedList<String>[] buckets;

        @SuppressWarnings("unchecked")
        ChainedHashTable(int capacity) {
            buckets = new LinkedList[capacity];
            for (int i = 0; i < capacity; i++) buckets[i] = new LinkedList<>();
        }

        private int getIndex(String key) {
            return Math.floorMod(key.hashCode(), buckets.length);
        }

        void insert(String sku) {
            int index = getIndex(sku);
            if (!buckets[index].contains(sku)) buckets[index].add(sku);
        }

        boolean search(String sku) {
            return buckets[getIndex(sku)].contains(sku);
        }

        int bucketIndex(String sku) {
            return getIndex(sku);
        }
    }

    // ---------------- TASK 3: GRAPH, BFS, DFS AND CYCLE DETECTION ----------------
    static class Graph {
        private final Map<String, List<String>> adjacency = new LinkedHashMap<>();

        void addNode(String node) {
            adjacency.putIfAbsent(node, new ArrayList<>());
        }

        void addEdge(String from, String to) {
            addNode(from);
            addNode(to);
            adjacency.get(from).add(to);
        }

        void bfs(String start) {
            Set<String> visited = new LinkedHashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.add(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                String current = queue.remove();
                System.out.print(current + " ");
                for (String neighbour : adjacency.get(current)) {
                    if (!visited.contains(neighbour)) {
                        visited.add(neighbour);
                        queue.add(neighbour);
                    }
                }
            }
        }

        void dfs(String start) {
            Set<String> visited = new LinkedHashSet<>();
            dfsVisit(start, visited);
        }

        private void dfsVisit(String node, Set<String> visited) {
            visited.add(node);
            System.out.print(node + " ");
            for (String neighbour : adjacency.get(node)) {
                if (!visited.contains(neighbour)) dfsVisit(neighbour, visited);
            }
        }

        // Kahn's algorithm: an edge A -> B means A must be deployed before B.
        List<String> topologicalOrder() {
            Map<String, Integer> indegree = new LinkedHashMap<>();
            for (String node : adjacency.keySet()) indegree.put(node, 0);
            for (String node : adjacency.keySet()) {
                for (String neighbour : adjacency.get(node)) {
                    indegree.put(neighbour, indegree.get(neighbour) + 1);
                }
            }

            Queue<String> queue = new LinkedList<>();
            for (String node : indegree.keySet()) {
                if (indegree.get(node) == 0) queue.add(node);
            }

            List<String> order = new ArrayList<>();
            while (!queue.isEmpty()) {
                String current = queue.remove();
                order.add(current);
                for (String neighbour : adjacency.get(current)) {
                    indegree.put(neighbour, indegree.get(neighbour) - 1);
                    if (indegree.get(neighbour) == 0) queue.add(neighbour);
                }
            }
            return order;
        }

        boolean hasCycle() {
            Set<String> visited = new HashSet<>();
            Set<String> activePath = new HashSet<>();
            for (String node : adjacency.keySet()) {
                if (cycleVisit(node, visited, activePath)) return true;
            }
            return false;
        }

        private boolean cycleVisit(String node, Set<String> visited, Set<String> activePath) {
            if (activePath.contains(node)) return true;
            if (visited.contains(node)) return false;

            visited.add(node);
            activePath.add(node);
            for (String neighbour : adjacency.get(node)) {
                if (cycleVisit(neighbour, visited, activePath)) return true;
            }
            activePath.remove(node);
            return false;
        }
    }

    // ---------------- TASK 4: SLIDING WINDOW ----------------
    static void findBusiestThreeHours(int[] requests) {
        int windowSize = 3;
        int currentTotal = 0;
        for (int i = 0; i < windowSize; i++) currentTotal += requests[i];

        int maximumTotal = currentTotal;
        int bestStart = 0;

        for (int i = windowSize; i < requests.length; i++) {
            currentTotal = currentTotal + requests[i] - requests[i - windowSize];
            if (currentTotal > maximumTotal) {
                maximumTotal = currentTotal;
                bestStart = i - windowSize + 1;
            }
        }

        System.out.println("Busiest window: " + bestStart + ":00 to " + (bestStart + 3) + ":00");
        System.out.println("Total requests in 3 hours: " + maximumTotal);
    }

    // ---------------- TASK 5: TWO POINTERS ----------------
    static void findGiftCardPairs(int[] values, int target) {
        int left = 0;
        int right = values.length - 1;
        boolean found = false;

        while (left < right) {
            int sum = values[left] + values[right];
            if (sum == target) {
                System.out.println(values[left] + " + " + values[right] + " = " + target);
                found = true;
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        if (!found) System.out.println("No matching pairs found.");
    }

    public static void main(String[] args) {
        System.out.println("========== E-COMMERCE CATALOGUE AND TRAFFIC ANALYSIS ==========");

        // TASK 1
        System.out.println("\nTASK 1: BINARY SEARCH TREE");
        String[] categories = {"Electronics", "Books", "Clothing", "Home", "Beauty",
                "Sports", "Toys", "Furniture", "Groceries", "Footwear",
                "Jewellery", "Gaming", "Kitchen", "Stationery", "Automotive"};
        CategoryBST bst = new CategoryBST();
        for (String category : categories) bst.insert(category);
        System.out.print("Inorder: "); bst.inorder(bst.root); System.out.println();
        System.out.print("Preorder: "); bst.preorder(bst.root); System.out.println();
        System.out.print("Postorder: "); bst.postorder(bst.root); System.out.println();
        System.out.println("Search 'Gaming': " + (bst.search("Gaming") ? "Found" : "Not found"));
        System.out.println("Search 'Books': " + (bst.search("Books") ? "Found" : "Not found"));

        // TASK 2
        System.out.println("\nTASK 2: HASHING AND HASHMAP");
        ChainedHashTable smallTable = new ChainedHashTable(10);
        smallTable.insert("SKU-A");
        smallTable.insert("SKU-K");
        System.out.println("SKU-A bucket: " + smallTable.bucketIndex("SKU-A"));
        System.out.println("SKU-K bucket: " + smallTable.bucketIndex("SKU-K"));
        System.out.println("Collision demonstrated: " +
                (smallTable.bucketIndex("SKU-A") == smallTable.bucketIndex("SKU-K")));
        System.out.println("Search SKU-K in chained table: " + smallTable.search("SKU-K"));

        final int skuCount = 100_000;
        ChainedHashTable largeTable = new ChainedHashTable(200_003);
        Map<String, String> skuMap = new HashMap<>(skuCount * 2);
        for (int i = 0; i < skuCount; i++) {
            String sku = String.format("SKU%06d", i);
            largeTable.insert(sku);
            skuMap.put(sku, "Product-" + i);
        }

        // Warm-up lookups reduce (but do not eliminate) JVM warm-up effects.
        for (int i = 0; i < 5_000; i++) {
            String sku = String.format("SKU%06d", i);
            largeTable.search(sku);
            skuMap.get(sku);
        }

        int lookupCount = 20_000;
        long start = System.nanoTime();
        int customFound = 0;
        for (int i = 0; i < lookupCount; i++) {
            String sku = String.format("SKU%06d", (i * 37) % skuCount);
            if (largeTable.search(sku)) customFound++;
        }
        long customTime = System.nanoTime() - start;

        start = System.nanoTime();
        int mapFound = 0;
        for (int i = 0; i < lookupCount; i++) {
            String sku = String.format("SKU%06d", (i * 37) % skuCount);
            if (skuMap.get(sku) != null) mapFound++;
        }
        long mapTime = System.nanoTime() - start;

        System.out.println("Total SKUs stored: " + skuCount);
        System.out.println("Lookups performed per implementation: " + lookupCount);
        System.out.println("Chained hash table successful lookups: " + customFound);
        System.out.println("Built-in HashMap successful lookups: " + mapFound);
        System.out.printf(Locale.US, "Chained hash table lookup time: %.3f ms%n", customTime / 1_000_000.0);
        System.out.printf(Locale.US, "Built-in HashMap lookup time: %.3f ms%n", mapTime / 1_000_000.0);
        System.out.println("Note: timings vary by machine and JVM; this is a simple demonstration, not a formal benchmark.");

        // TASK 3
        System.out.println("\nTASK 3: MICROSERVICE DEPENDENCY GRAPH");
        Graph deploymentGraph = new Graph();
        deploymentGraph.addEdge("Database", "Inventory");
        deploymentGraph.addEdge("Inventory", "Pricing");
        deploymentGraph.addEdge("Pricing", "Cart");
        deploymentGraph.addEdge("Authentication", "Cart");
        deploymentGraph.addEdge("Cart", "Notification");
        System.out.print("BFS from Database: "); deploymentGraph.bfs("Database"); System.out.println();
        System.out.print("DFS from Database: "); deploymentGraph.dfs("Database"); System.out.println();
        System.out.println("Valid deployment order: " + deploymentGraph.topologicalOrder());
        System.out.println("Circular dependency in original graph: " + deploymentGraph.hasCycle());

        Graph circularGraph = new Graph();
        circularGraph.addEdge("Cart", "Pricing");
        circularGraph.addEdge("Pricing", "Inventory");
        circularGraph.addEdge("Inventory", "Cart");
        System.out.println("Circular dependency in test graph: " + circularGraph.hasCycle());

        // TASK 4
        System.out.println("\nTASK 4: SLIDING WINDOW - BUSIEST 3 HOURS");
        int[] requestsPerHour = {120, 150, 180, 220, 300, 450, 600, 700,
                650, 550, 400, 350, 300, 280, 320, 400,
                500, 650, 800, 900, 850, 600, 350, 200};
        System.out.println("Hourly requests: " + Arrays.toString(requestsPerHour));
        findBusiestThreeHours(requestsPerHour);

        // TASK 5
        System.out.println("\nTASK 5: TWO POINTERS - GIFT CARD PAIRS");
        int[] orderValues = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        int giftCardValue = 100;
        System.out.println("Sorted order values: " + Arrays.toString(orderValues));
        System.out.println("Gift-card value: " + giftCardValue);
        System.out.println("Matching pairs:");
        findGiftCardPairs(orderValues, giftCardValue);

    }
}
