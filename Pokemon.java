import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        ArrayList<Pokemon> team1 = new ArrayList<>();
        ArrayList<Pokemon> team2 = new ArrayList<>();

        // Создаем покемонов для первой команды
        team1.add(new Pikachu("Пикачу"));
        team1.add(new Charmander("Чармандер"));
        team1.add(new Squirtle("Сквиртл"));

        // Создаем покемонов для второй команды
        team2.add(new Bulbasaur("Бульбазавр"));
        team2.add(new Jigglypuff("Джигглипафф"));
        team2.add(new Meowth("Мяут"));

        battleTeams(team1, team2);
    }

    public static void battleTeams(ArrayList<Pokemon> team1, ArrayList<Pokemon> team2) {
        for (int i = 0; i < team1.size(); i++) {
            Pokemon pokemon1 = team1.get(i);
            Pokemon pokemon2 = team2.get(i);

            System.out.println("---- Начинает бой " + pokemon1.getName() + " против " + pokemon2.getName() + " ----");
            pokemon1.fight(pokemon2);
            pokemon2.fight(pokemon1);
            pokemon1.sleep();
            pokemon2.sleep();
        }
    }
}

class Pikachu extends Pokemon {
    public Pikachu(String name) {
        super(name, 100, 55, 40, 50, 50, 90);
    }
}

class Charmander extends Pokemon {
    public Charmander(String name) {
        super(name, 90, 52, 43, 60, 50, 65);
    }
}

class Squirtle extends Pokemon {
    public Squirtle(String name) {
        super(name, 80, 48, 65, 50, 64, 43);
    }
}

class Bulbasaur extends Pokemon {
    public Bulbasaur(String name) {
        super(name, 85, 49, 49, 65, 65, 45);
    }
}

class Jigglypuff extends Pokemon {
    public Jigglypuff(String name) {
        super(name, 115, 45, 20, 45, 25, 20);
    }
}

class Meowth extends Pokemon {
    public Meowth(String name) {
        super(name, 40, 45, 35, 40, 40, 90);
    }
}

class Pokemon {
    String name;
    int healthPoints;
    int attack;
    int defense;
    int specialAttack;
    int specialDefense;
    int speed;

    private final static double additionalHP;

    static {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дополнительное количество HP для всех покемонов:");
        additionalHP = scanner.nextDouble();
    }

    public Pokemon(String name, int healthPoints, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void fight(Pokemon opponent) {
        System.out.println(name + " начинает бой против " + opponent.getName() + "!");
        int damage = calculateDamage(opponent);
        opponent.reduceHealthPoints(damage);
        System.out.println(name + " нанес " + damage + " урона " + opponent.getName() + ".");
        System.out.println(opponent.getName() + " осталось здоровья: " + opponent.getHealthPoints());
        if (opponent.getHealthPoints() <= 0) {
            System.out.println(opponent.getName() + " был побежден!");
            return;
        }
        damage = opponent.calculateDamage(this);
        this.reduceHealthPoints(damage);
        System.out.println(opponent.getName() + " нанес " + damage + " урона " + name + ".");
        System.out.println(name + " осталось здоровья: " + getHealthPoints());
        if (getHealthPoints() <= 0) {
            System.out.println(name + " был побежден!");
        }
    }

    public void sleep() {
        System.out.println(name + " засыпает и восстанавливает здоровье.");
        int healAmount = (int) (healthPoints * 0.2);
        healthPoints += healAmount;
        System.out.println("Здоровье " + name + " восстановлено на " + healAmount + " единиц.");
    }

    private int calculateDamage(Pokemon opponent) {
        return attack;
    }

    public void reduceHealthPoints(int amount) {
        healthPoints -= amount;
        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }
}
