package builder;

// product class: Meal
class Meal {
    private String mainCourse;
    private String sideDish;
    private String drink;
    private String dessert;

    public Meal(String mainCourse, String sideDish, String drink, String dessert) {
        this.mainCourse = mainCourse;
        this.sideDish = sideDish;
        this.drink = drink;
        this.dessert = dessert;
    }

    @Override
    public String toString() {
        return "Meal [Main Course=" + mainCourse + ", Side Dish=" + sideDish
                + ", Drink=" + drink + ", Dessert=" + dessert + "]";
    }
}

// builder interface
interface MealBuilder {
    void prepareMainCourse();
    void prepareSideDish();
    void prepareDrink();
    void prepareDessert();
    Meal serveMeal();
}

// concrete builder: turkish cuisine meal builder
class TurkishMealBuilder implements MealBuilder {
    private String mainCourse;
    private String sideDish;
    private String drink;
    private String dessert;

    @Override
    public void prepareMainCourse() {
        this.mainCourse = "Adana Kebab";
    }

    @Override
    public void prepareSideDish() {
        this.sideDish = "Çoban Salad";
    }

    @Override
    public void prepareDrink() {
        this.drink = "Ayran";
    }

    @Override
    public void prepareDessert() {
        this.dessert = "Baklava";
    }

    @Override
    public Meal serveMeal() {
        return new Meal(mainCourse, sideDish, drink, dessert);
    }
}

// concrete builder: american cuisine meal builder
class AmericanMealBuilder implements MealBuilder {
    private String mainCourse;
    private String sideDish;
    private String drink;
    private String dessert;

    @Override
    public void prepareMainCourse() {
        this.mainCourse = "Cheeseburger";
    }

    @Override
    public void prepareSideDish() {
        this.sideDish = "Fries";
    }

    @Override
    public void prepareDrink() {
        this.drink = "Coke";
    }

    @Override
    public void prepareDessert() {
        this.dessert = "Apple Pie";
    }

    @Override
    public Meal serveMeal() {
        return new Meal(mainCourse, sideDish, drink, dessert);
    }
}

// director class
class MealDirector {
    // builds a complete meal (main course, side dish, drink, dessert)
    public void constructCompleteMeal(MealBuilder builder) {
        builder.prepareMainCourse();
        builder.prepareSideDish();
        builder.prepareDrink();
        builder.prepareDessert();
    }

    // builds a simplified meal without dessert
    public void constructBasicMeal(MealBuilder builder) {
        builder.prepareMainCourse();
        builder.prepareSideDish();
        builder.prepareDrink();
    }
}

public class BuilderAndDirector {
    public static void main(String[] args) {
        MealDirector director = new MealDirector();

        // building a Turkish meal
        MealBuilder turkishMealBuilder = new TurkishMealBuilder();
        director.constructCompleteMeal(turkishMealBuilder);
        Meal turkishMeal = turkishMealBuilder.serveMeal();
        System.out.println("Turkish Meal: " + turkishMeal);

        // building an American meal
        MealBuilder americanMealBuilder = new AmericanMealBuilder();
        director.constructCompleteMeal(americanMealBuilder);
        Meal americanMeal = americanMealBuilder.serveMeal();
        System.out.println("American Meal: " + americanMeal);

        // building a basic Turkish meal (without dessert)
        MealBuilder basicTurkishMealBuilder = new TurkishMealBuilder();
        director.constructBasicMeal(basicTurkishMealBuilder);
        Meal basicTurkishMeal = basicTurkishMealBuilder.serveMeal();
        System.out.println("Basic Turkish Meal: " + basicTurkishMeal);
    }
}
