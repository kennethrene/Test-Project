package collections;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MapMeasure {
    HashMap<Integer, Integer> mapHash = new HashMap();
    LinkedHashMap<Integer, Integer> mapLinked = new LinkedHashMap();
    IdentityHashMap<Integer, Integer> mapIdentity = new IdentityHashMap<>();
    ConcurrentHashMap<Integer, Integer> mapConcurrent = new ConcurrentHashMap<>();

    WeakHashMap<Integer, Integer> mapWeak = new WeakHashMap<>();

    public static void main(String... args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(".*" + MapMeasure.class.getSimpleName() + ".*")
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
                .build();

        new Runner(options).run();
    }

    @Setup(Level.Trial)
    public void linkedHashMap() {
        IntStream.range(0, 1000000).forEach(v -> mapLinked.put(v, v * 10));
        IntStream.range(0, 1000000).forEach(v -> mapIdentity.put(v, v * 10));
        IntStream.range(0, 1000000).forEach(v -> mapWeak.put(v, v * 10));
        IntStream.range(0, 1000000).forEach(v -> mapHash.put(v, v * 10));
        IntStream.range(0, 1000000).forEach(v -> mapConcurrent.put(v, v * 10));
    }

    @Benchmark
    public void testPutHashMap() {
        mapHash.put(2000000, 2000);
    }

    @Benchmark
    public Integer testGetHashMap() {
        return mapHash.get(2000);
    }

    @Benchmark
    public boolean testContainsHashMap() {
        return mapHash.containsKey(1000);
    }

    @Benchmark
    public Integer testRemoveHashMap() {
        return mapHash.remove(2000);
    }
    // ***********************************************
    //
    @Benchmark
    public void testPutLinkedHashMap() {
        mapLinked.put(2000000, 2000);
    }

    @Benchmark
    public Integer testGetLinkedHashMap() {
        return mapLinked.get(2000);
    }

    @Benchmark
    public boolean testContainsLinkedHashMap() {
        return mapLinked.containsKey(1000);
    }

    @Benchmark
    public Integer testRemoveLinkedHashMap() {
        return mapLinked.remove(2000);
    }
// ***********************************************
    @Benchmark
    public void testPutIdentityHashMap() {
        mapIdentity.put(2000000, 2000);
    }

    @Benchmark
    public Integer testGetIdentityHashMap() {
        return mapIdentity.get(2000);
    }

    @Benchmark
    public boolean testContainsIdentityHashMap() {
        return mapIdentity.containsKey(1000);
    }

    @Benchmark
    public Integer testRemoveIdentityHashMap() {
        return mapIdentity.remove(2000);
    }
// ***********************************************
    @Benchmark
    public void testPutWeakHashMap() {
        mapWeak.put(2000000, 2000);
    }

    @Benchmark
    public Integer testGetWeakHashMap() {
        return mapWeak.get(2000);
    }

    @Benchmark
    public boolean testContainsWeakHashMap() {
        return mapWeak.containsKey(1000);
    }

    @Benchmark
    public Integer testRemoveWeakHashMap() {
        return mapWeak.remove(2000);
    }
// ***********************************************
    @Benchmark
    public void testPutConcurrentHashMap() {
        mapConcurrent.put(2000000, 2000);
    }

    @Benchmark
    public Integer testGetConcurrentHashMap() {
        return mapConcurrent.get(2000);
    }

    @Benchmark
    public boolean testContainsConcurrentHashMap() {
        return mapConcurrent.containsKey(1000);
    }

    @Benchmark
    public Integer testRemoveConcurrentHashMap() {
        return mapConcurrent.remove(2000);
    }
}
