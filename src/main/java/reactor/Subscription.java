package reactor;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Subscription {
    private static void oneThread() {
        System.out.println("------------------ Running in the parent thread");

        Flux<String> cities = Flux.just("New York", "London", "Paris", "Amsterdam")
                .map(String::toUpperCase)
                .filter(cityName -> cityName.length() <= 8)
                .map(cityName -> cityName.concat(" City"))
                .log();

        cities.subscribe();
    }

    private static void subscribeOn() {
        System.out.println("------------------ Running in the subscribeOn thread without blocking but with a delay in the parent thread");
        Flux<String> cities = Flux.just("New York", "London", "Paris", "Amsterdam")
                .subscribeOn(Schedulers.boundedElastic())
                .map(String::toUpperCase)
                .filter(cityName -> cityName.length() <= 8)
                .map(cityName -> cityName.concat(" City"))
                .log();

        cities.subscribe();
    }

    private static void subscribeOnBlocking() {
        System.out.println("------------------ Blocking the thread until the last element is processed");

        Flux.just("New York", "London", "Paris", "Amsterdam")
                .subscribeOn(Schedulers.boundedElastic())
                .map(String::toUpperCase)
                .filter(cityName -> cityName.length() <= 8)
                .map(cityName -> cityName.concat(" City"))
                .log()
                .blockLast();

        System.out.println("------------------ Blocking the thread until the first element is processed");

        Flux.just("New York", "London", "Paris", "Amsterdam")
                .subscribeOn(Schedulers.boundedElastic())
                .map(String::toUpperCase)
                .filter(cityName -> cityName.length() <= 8)
                .map(cityName -> cityName.concat(" City"))
                .log()
                .blockFirst();
    }

    private static void hotStream() {
        System.out.println("------------------ Creating hot stream to run undefinedly");

        ConnectableFlux<Object> publish = Flux.create(sink -> {
                while(true) {
                    sink.next(Math.round(Math.random() * 1000));
                }
            })
            .publish();

        publish.subscribe(System.out::println);
        publish.subscribe(v -> System.out.println((long)v*2));
        publish.connect();
    }

    private static void hotStreamWithSample() {
        System.out.println("------------------ Creating hot stream to run undefinedly pushing elements with the interval of two seconds");

        ConnectableFlux<Object> publish = Flux.create(sink -> {
                    while(true) {
                        sink.next(Math.round(Math.random() * 1000));
                    }
                })
                .sample(Duration.ofSeconds(2))
                .publish();

        publish.subscribe(System.out::println);
        publish.subscribe(v -> System.out.println((long)v*2));
        publish.connect();
    }

    private static void publishOn() {
        System.out.println("------------------ Running in the publishOn thread without blocking but with a delay in the parent thread");
        Flux<String> cities = Flux.just("New York", "London", "Paris", "Amsterdam")
                .map(String::toUpperCase)
                .publishOn(Schedulers.boundedElastic())
                .log()
                .filter(cityName -> cityName.length() <= 8)
                .publishOn(Schedulers.parallel())
                .map(cityName -> cityName.concat(" City"))
                .log();

        cities.subscribe();
    }

    private static void runningParallel() {
        System.out.println("------------------ Running in the parent thread parallel");
        Flux.just("New York", "London", "Paris", "Amsterdam", "Bogota")
                .parallel(6)
                .runOn(Schedulers.parallel())
                .map(String::toUpperCase)
                .filter(cityName -> cityName.length() <= 8)
                .map(cityName -> cityName.concat(" City"))
                .log()
                .subscribe();
    }

    private static void runningAsync() {
        System.out.println("------------------ Running in the parent thread asynchronous");
        Mono<Long> callEvent = Mono.fromCallable(() -> {
            Long value = Math.round(Math.random() * 1000);
            System.out.println("Returning value: " + value);
            return value;
        });

        callEvent = callEvent.subscribeOn(Schedulers.boundedElastic());

        callEvent.subscribe(result -> {
            System.out.println("subscribed thread : " + Thread.currentThread().getName());
            System.out.println("------------------ Finishing the asynchronous execution");
        });
    }

    public static void main(String... args) throws InterruptedException {
        Subscription.oneThread();
        Subscription.subscribeOn();
        Thread.sleep(100); //Delay 100 in the parent thread because could finish before the subscribeOn (that is running in another thread)

        Subscription.subscribeOnBlocking();

        //Subscription.hotStreamWithSample();
        Subscription.publishOn();
        Thread.sleep(100);

        Subscription.runningAsync();

        Subscription.runningParallel();
        Thread.sleep(100);
    }
}
