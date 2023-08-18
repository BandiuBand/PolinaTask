package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

public class test {
    private static int count = 0;
    public static void main(String[] args) {

        List<Integer> list = generate();

        System.out.println("generate numbers from 0 to 5");
        System.out.println("____________________________");

        printLn(list);
        System.out.println("____________________________");
        System.out.println("filter <3");
        System.out.println("____________________________");

        filter(list);

        System.out.println("____________________________");
        System.out.println("map(x10) and reduce by concatenation");
        System.out.println("____________________________");

        reduceAndMap(list);
    }

    private static void printLn(Optional<Integer> reduce) {
        System.out.println(reduce.get());
    }

    private static <T> void printLn(List<T> list)
    {
        StreamLikeOps.forEach(list, new Consumer<T>() {
            @Override
            public void accept(T t) {
                System.out.println(t);
            }
        });

    }
    private static List<Integer> generate() {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                int returnValue = count;
                count++;
                return returnValue;
            }
        };
        List<Integer> list = StreamLikeOps.generate(supplier, new Supplier<List<Integer>>() {
            @Override
            public List<Integer> get() {
                return new ArrayList<>();
            }
        }, 6);
        return list;
    }

    private static void filter(List<Integer> list){
        printLn(StreamLikeOps.filter(list, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer<3;
            }
        }));
    }

    private static void reduceAndMap(List<Integer> list){
        printLn(StreamLikeOps.reduce(StreamLikeOps.map(list, new Function<Integer, Integer>() {

                    @Override
                    public Integer apply(Integer integer) {
                        return integer*10;
                    }
                },
                new Supplier<List<Integer>>() {
                    @Override
                    public List<Integer> get() {
                        return new ArrayList<>();
                    }
                }),new BinaryOperator<Integer>(){



            @Override
            public Integer apply(Integer integer, Integer integer2) {

                return Integer.parseInt((String)( String.valueOf(integer)+String.valueOf(integer2)));
            }
        }));
    }

}
