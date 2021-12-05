public class food {
    int foodId;
    String name;
    int price;

    public food(int foodId, String name, int price) {
        this.foodId = foodId;
        this.name = name;
        this.price = price;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getInfor() {
        return this.foodId + " " + this.name + "  vnd" + this.price;
    }
}
