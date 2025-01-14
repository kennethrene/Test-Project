package wtest;

public class Father extends Live {
    public int age=18;

    Father() {
        this.age = 35;
    }

    @Override
    public void breathe() {
        System.out.println(" I am a father and I am "+ age + " years old");
    }
}
