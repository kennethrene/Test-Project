package wtest;

abstract class Live {
    protected abstract void breathe();

    public void living() {
        this.breathe();
    }
}