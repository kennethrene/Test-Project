package collections;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ListMeasure {
    ArrayList<Integer> arrayList = new ArrayList<>();
    LinkedList<Integer> linkedList = new LinkedList<>();
    CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    public static void main(String... args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(".*" + ListMeasure.class.getSimpleName() + ".*")
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
                .build();

        new Runner(options).run();
    }

    @Setup(Level.Trial)
    public void linkedHashMap() {
        IntStream.range(0, 10000).forEach(v -> linkedList.add(v * 10));
        IntStream.range(0, 10000).forEach(v -> copyOnWriteArrayList.add(v * 10));
        IntStream.range(0, 10000).forEach(v -> arrayList.add(v * 10));
    }

    @Benchmark
    public void testAddArrayList() {
        arrayList.add(2000);
    }

    @Benchmark
    public void testAddAtArrayList() {
        arrayList.add(2000, 2000);
    }

    @Benchmark
    public void testIndexOfArrayList() {
        arrayList.indexOf(20);
    }

    @Benchmark
    public Integer testGetArrayList() {
        return arrayList.get(20);
    }

    @Benchmark
    public boolean testContainsArrayList() {
        return arrayList.contains(100);
    }

    @Benchmark
    public Integer testRemoveArrayList() {
        return arrayList.remove(200);
    }
    // ***********************************************
    //
    @Benchmark
    public void testAddLinkedList() {
        linkedList.add(200);
    }

    @Benchmark
    public void testAddAtLinkedList() {
        linkedList.add(2000, 2000);
    }

    @Benchmark
    public void testIndexOfLinkedList() {
        linkedList.indexOf(200);
    }

    @Benchmark
    public Integer testGetLinkedList() {
        return linkedList.get(200);
    }

    @Benchmark
    public boolean testContainsLinkedList() {
        return linkedList.contains(1000);
    }

    @Benchmark
    public Integer testRemoveLinkedList() {
        return linkedList.remove(100);
    }
// ***********************************************

    @Benchmark
    public void testAddCopyOnWriteArrayList() {
        copyOnWriteArrayList.add(200);
    }

    @Benchmark
    public void testAddAtCopyOnWriteArrayList() {
        copyOnWriteArrayList.add(2000, 2000);
    }

    @Benchmark
    public void testIndexOfCopyOnWriteArrayList() {
        copyOnWriteArrayList.indexOf(200);
    }

    @Benchmark
    public Integer testGetCopyOnWriteArrayList() {
        return copyOnWriteArrayList.get(200);
    }

    @Benchmark
    public boolean testContainsCopyOnWriteArrayList() {
        return copyOnWriteArrayList.contains(100);
    }

    @Benchmark
    public Integer testRemoveCopyOnWriteArrayList() {
        return copyOnWriteArrayList.remove(10);
    }
}
