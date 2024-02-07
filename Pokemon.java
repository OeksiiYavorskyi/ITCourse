class Main {
    public static void main(String[] args) {
        Pikachu pikachu = new Pikachu("Пикачу");
        Charmander charmander = new Charmander("Чармандер");

        pikachu.fight(charmander);
        charmander.fight(pikachu);
        pikachu.sleep();
        charmander.sleep();
    }
}
class Pikachu extends Pokemon {
    // Конструктор класса Pikachu
    public Pikachu(String name) {
        super(name, 100, 55, 40, 50, 50, 90);
    }
}

// Определение класса Charmander
class Charmander extends Pokemon {
    // Конструктор класса Charmander
    public Charmander(String name) {
        super(name, 90, 52, 43, 60, 50, 65);
    }
}
class Pokemon {
    // Переменные экземпляра
    String name;
    int healthPoints;
    int attack;
    int defense;
    int specialAttack;
    int specialDefense;
    int speed;

    // Конструктор класса Pokemon
    public Pokemon(String name, int healthPoints, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    // Метод для боя
    public void fight(Pokemon opponent) {
        System.out.println(name + " начинает бой против " + opponent.getName() + "!");

        // Определение урона, который нанесет текущий покемон
        int damage = calculateDamage(opponent);

        // Уменьшение здоровья оппонента на величину урона
        opponent.reduceHealthPoints(damage);

        System.out.println(name + " нанес " + damage + " урона " + opponent.getName() + ".");
        System.out.println(opponent.getName() + " осталось здоровья: " + opponent.getHealthPoints());

        // Проверяем здоровье оппонента после удара
        if (opponent.getHealthPoints() <= 0) {
            System.out.println(opponent.getName() + " был побежден!");
            return;
        }

        // Оппонент атакует в ответ
        damage = opponent.calculateDamage(this);
        this.reduceHealthPoints(damage);

        System.out.println(opponent.getName() + " нанес " + damage + " урона " + name + ".");
        System.out.println(name + " осталось здоровья: " + getHealthPoints());

        // Проверяем здоровье текущего покемона после удара оппонента
        if (getHealthPoints() <= 0) {
            System.out.println(name + " был побежден!");
        }
    }

    // Метод для сна
    public void sleep() {
        System.out.println(name + " засыпает и восстанавливает здоровье.");
        // Восстанавливаем здоровье на 20% от максимального значения
        int healAmount = (int) (healthPoints * 0.2);
        healthPoints += healAmount;
        System.out.println("Здоровье " + name + " восстановлено на " + healAmount + " единиц.");
    }

    // Метод для расчета урона, наносимого текущим покемоном
    private int calculateDamage(Pokemon opponent) {
        //возвращаем значение атаки текущего покемона
        return attack;
    }

    // Метод для уменьшения здоровья покемона на определенную величину
    public void reduceHealthPoints(int amount) {
        healthPoints -= amount;
        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }
    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;

    }
}