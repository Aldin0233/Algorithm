import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static Map<String, Long> minimumPrice = new HashMap<>();
//    static Map<String, List<Recipe>> potionRecipes = new HashMap<>();
    static Map<String, List<Recipe>> ingredientUsingResultList = new HashMap<>();
    static final long MAX = 1_000_000_001;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        PriorityQueue<State> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String item = st.nextToken();
            long price = Long.parseLong(st.nextToken());
            pq.add(new State(item, price));
        }
        for(int i = 0; i < M; i++) {
            Recipe recipe = turnRecipe(br.readLine());
//            potionRecipes.computeIfAbsent(recipe.potionName, k -> new ArrayList<>()).add(recipe);
            //단방향 전파로 충분함
            for(int j = 0; j < recipe.ingredients.length; j++) {
                ingredientUsingResultList.computeIfAbsent(recipe.ingredients[j].inName, k -> new ArrayList<>()).add(recipe);
            }
        }
        while(!pq.isEmpty()) {
            State cur = pq.poll();
            if(minimumPrice.containsKey(cur.name)) continue;
            //PQ에서 나온 순간 더 낮은 가격의 물약은 나올 수 없음 //무한 사이클 불가
            minimumPrice.put(cur.name, cur.cost);
            List<Recipe> recipeList = ingredientUsingResultList.get(cur.name);
            if(recipeList == null) continue;
            for(Recipe recipe : recipeList) {
                //모르는 코스트 종류 --;
                recipe.unknownCost--;
                if(recipe.unknownCost == 0) {
                    long recipePrice = checkRecipePrice(recipe);
                    pq.offer(new State(recipe.potionName, recipePrice));
                }
            }
        }

        System.out.println(minimumPrice.getOrDefault("LOVE", -1L));
    }

    static long checkRecipePrice(Recipe recipe) {
        long price = 0;
        for(int i = 0; i < recipe.ingredients.length; i++) {
            long curItemPrice = minimumPrice.get(recipe.ingredients[i].inName);
            long curItemDemand = recipe.ingredients[i].demand;
            price += (curItemPrice * curItemDemand);
            if(price >= MAX) {
                price = MAX;
                return price;
            }
        }
        return price;
    }

    static Recipe turnRecipe(String input) {
        String[] arrTmp = input.split("=");
        String potionName = arrTmp[0];
        Ingredient[] ingredients = getIngredients(arrTmp[1]);
        return new Recipe(potionName, ingredients);
    }

    static Ingredient[] getIngredients(String input) {
        String[] arr = input.split("\\+");
        Ingredient[] ingredients = new Ingredient[arr.length];
        for(int i = 0; i < arr.length; i++) {
            int demand = arr[i].charAt(0) - '0';
            String ingredientName = arr[i].substring(1);
            ingredients[i] = new Ingredient(ingredientName, demand);
        }
        return ingredients;
    }

    static class State implements Comparable<State> {
        String name;
        long cost;
        State(String name, long cost) {
            this.name = name;
            this.cost = cost;
        }

        public int compareTo(State o) {
            return Long.compare(cost, o.cost);
        }

    }

    static class Recipe {
        String potionName;
        Ingredient[] ingredients;
        int unknownCost;
        public Recipe(String potionName, Ingredient[] ingredients) {
            this.potionName = potionName;
            this.ingredients = ingredients;
            this.unknownCost = ingredients.length;
        }
    }

    static class Ingredient {
        String inName;
        int demand;
        public Ingredient(String inName, int demand) {
            this.inName = inName;
            this.demand = demand;
        }
    }



}




