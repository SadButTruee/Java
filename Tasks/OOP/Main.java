package Lesson2;

class Humen implements RubAndJump{
    final int maxRun = 100;
    final int maxJump = 20;
    String name;

    public Humen(String name) {
        this.name = name;
    }

    @Override
    public boolean run(int lenght) {
        if (maxRun >= lenght){
            System.out.println("Humen " + name + " run " + lenght + " meters");
            return true;
        }else{
            System.out.println("Humen " + name + " can't run");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (maxJump >= height){
            System.out.println("Humen " + name + " jump " + height + " meters");
            return true;
        }else{
            System.out.println("Humen can't jump");
            return false;
        }
    }
}

class Cat implements  RubAndJump{
    final int maxRun = 200;
    final int maxJump = 40;
    String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public boolean run(int lenght) {
        if (maxRun >= lenght){
            System.out.println("Cat " + name + " run " + lenght + " meters");
            return true;
        }else{
            System.out.println("Cat " + name + " can't run");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (maxJump >= height){
            System.out.println("Cat " + name + " jump " + height + " meters");
            return true;
        }else{
            System.out.println("Cat can't jump");
            return false;
        }
    }
}

class Robot implements RubAndJump{
    final int maxRun = 1000;
    final int maxJump = 100;
    String name;

    public Robot(String name) {
        this.name = name;
    }

    @Override
    public boolean run(int lenght) {
        if (maxRun >= lenght){
            System.out.println("Robot " + name + " run " + lenght + " meters");
            return true;
        }else{
            System.out.println("Robot " + name + " can't run");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (maxJump >= height){
            System.out.println("Robot " + name + " jump " + height + " meters");
            return true;
        }else{
            System.out.println("Robot can't jump");
            return false;
        }
    }

}

abstract class Obatacles{
    int  parameter;

    public Obatacles(int parameter) {
        this.parameter = parameter;
    }

    abstract boolean canDoIt(RubAndJump rubAndJump);
}

class runBoard extends Obatacles{
    public runBoard(int parameter) {
        super(parameter);
    }

    @Override
    boolean canDoIt(RubAndJump rubAndJump) {
        if (rubAndJump.run(parameter))
            return true;
        else
            return false;
    }
}

class Wall extends Obatacles{
    public Wall(int parameter) {
        super(parameter);
    }

    @Override
    boolean canDoIt(RubAndJump rubAndJump) {
        if (rubAndJump.jump(parameter))
            return true;
        else
            return false;
    }
}

interface RubAndJump{
    boolean run(int lenght);
    boolean jump(int height);
}

public class Main {
    public static void main(String[] args) {
        RubAndJump[] array = new RubAndJump[]{
                new Humen("Sar"),
                new Cat("Barsik"),
                new Robot("Ben")};

        Obatacles[] obatacles = new Obatacles[]{
                new runBoard(150),
                new runBoard(10),
                new runBoard(30),
                new Wall(3),
                new Wall(28),
                new Wall(90),};

        for (Obatacles o : obatacles){
            for (int i = 0; i < array.length; i++){
                if(array[i] != null){
                    if(!(o.canDoIt(array[i])))
                        array[i] = null;
                }
            }
        }
    }
}
