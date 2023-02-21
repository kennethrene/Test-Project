package collections;

import org.openjdk.jmh.runner.RunnerException;

import java.util.IdentityHashMap;
import java.util.LinkedHashMap;

public class MapTest {
    LinkedHashMap<Student, Integer> mapLinked = new LinkedHashMap();
    IdentityHashMap<Student, Integer> mapIdentity = new IdentityHashMap<>();

    public static void main(String... args) throws RunnerException {
        MapTest maps = new MapTest();
        maps.checkIdentityMutable();
    }

    private void checkIdentityMutable() {
        mapIdentity = new IdentityHashMap<>();

        Student student1 = new Student(1, "Kenneth");
        Student student2 = new Student(2, "Rene");

        mapLinked.put(student1, 10);
        mapLinked.put(student2, 20);
        mapIdentity.put(student1, 10);
        mapIdentity.put(student2, 20);

        System.out.println(mapLinked.get(student1));
        System.out.println(mapIdentity.get(student1));
        System.out.println("===============");

        student1.setName("David");
        System.out.println(mapLinked.get(student1));
        System.out.println(mapIdentity.get(student1));
        System.out.println("===============");
    }
}
