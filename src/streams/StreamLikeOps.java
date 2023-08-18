package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class StreamLikeOps {

    private StreamLikeOps() {
    }

    public static <E> List<E> generate(Supplier<E> generator,
                                       Supplier<List<E>> listFactory,
                                       int count) {

        List<E> resultList = listFactory.get();

        for (int i = 0; i < count; i++) {
            resultList.add(generator.get());
        }

        return resultList;
    }

    public static <E> List<E> filter(List<E> elements, Predicate<E> filter) {
        List<E> returnList = null;
        try {
            returnList = elements.getClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for (E element: elements) {
            if(filter.test(element)){
                returnList.add(element);
            }
        }
        return returnList;
    }

    public static <E> boolean anyMatch(List<E> elements, Predicate<E> predicate) {
        for (E element:elements) {
            if (predicate.test(element))
                return true;
        }
        return false;
    }

    public static <E> boolean allMatch(List<E> elements, Predicate<E> predicate) {
        for (E element:elements) {
            if(!predicate.test(element))
                return false;
        }
        return true;
    }

    public static <E> boolean noneMatch(List<E> elements, Predicate<E> predicate) {
        for (E element:elements) {
            if(predicate.test(element))
                return false;
        }
        return true;
    }

    public static <T, R> List<R> map(List<T> elements,
                                     Function<T, R> mappingFunction,
                                     Supplier<List<R>> listFactory) {
        List<R> resultList = listFactory.get();
        for (T element:elements) {
            resultList.add(mappingFunction.apply(element));
        }
        return resultList;
    }

    public static <E> Optional<E> max(List<E> elements, Comparator<E> comparator) {
        if (elements.isEmpty())
            return Optional.empty();
        E max = elements.get(0);
        for (E element:elements) {
            if (comparator.compare(element,max)>0)
                max=element;
        }
        return Optional.of(max);
    }

    public static <E> Optional<E> min(List<E> elements, Comparator<E> comparator) {
        if (elements.isEmpty())
            return Optional.empty();
        E min = elements.get(0);
        for (E element:elements) {
            if (comparator.compare(element,min)<0)
                min=element;
        }
        return Optional.of(min);
    }

    public static <E> List<E> distinct(List<E> elements, Supplier<List<E>> listFactory) {

        List<E> resultList = listFactory.get();

        for (E element:elements) {
            if(!resultList.contains(element))
                resultList.add(element);
        }
        return resultList;
    }

    public static <E> void forEach(List<E> elements, Consumer<E> consumer) {
        for (E element:elements) {
            consumer.accept(element);
        }
    }

    public static <E> Optional<E> reduce(List<E> elements, BinaryOperator<E> accumulator) {
        if (elements.isEmpty())
            return Optional.empty();

        E result = elements.get(0);

        for (int i = 1; i < elements.size(); i++) {
            result = accumulator.apply(result,elements.get(i));
        }
        return Optional.of(result);
    }

    public static <E> E reduce(E seed, List<E> elements, BinaryOperator<E> accumulator) {
        if (elements.isEmpty())
            return seed;

        E result = seed;

        for (int i = 0; i < elements.size(); i++) {
            result = accumulator.apply(result,elements.get(i));
        }
        return result;
    }

    public static <E> Map<Boolean, List<E>> partitionBy(List<E> elements,
                                                        Predicate<E> predicate,
                                                        Supplier<Map<Boolean, List<E>>> mapFactory,
                                                        Supplier<List<E>> listFactory) {
        Map<Boolean,List<E>> resultMap = mapFactory.get();

        List<E> isPartition = listFactory.get();
        List<E> isntPartition = listFactory.get();

        for (E element: elements) {
            if(predicate.test(element))
                isPartition.add(element);
            else
                isntPartition.add(element);
        }
        resultMap.put(true,isPartition);
        resultMap.put(false,isntPartition);
        return resultMap;
    }

    public static <T, K> Map<K, List<T>> groupBy(List<T> elements,
                                                 Function<T, K> classifier,
                                                 Supplier<Map<K, List<T>>> mapFactory,
                                                 Supplier<List<T>> listFactory) {
        //TODO Implement me
        return null;
    }

    public static <T, K, U> Map<K, U> toMap(List<T> elements,
                                            Function<T, K> keyFunction,
                                            Function<T, U> valueFunction,
                                            BinaryOperator<U> mergeFunction,
                                            Supplier<Map<K, U>> mapFactory) {

        Map<K,U> resultMap = mapFactory.get();

        for (T element:elements) {
            K key = keyFunction.apply(element);
            U value = valueFunction.apply(element);
            resultMap.put(key,value);
        }

        return resultMap;
    }


    public static <E, T> Map<Boolean, List<T>> partitionByAndMapElement(List<E> elements,
                                                                        Predicate<E> predicate,
                                                                        Supplier<Map<Boolean, List<T>>> mapFactory,
                                                                        Supplier<List<T>> listFactory,
                                                                        Function<E, T> elementMapper) {
        //TODO Implement me
        return null;
    }

    public static <T, U, K> Map<K, List<U>> groupByAndMapElement(List<T> elements,
                                                                 Function<T, K> classifier,
                                                                 Supplier<Map<K, List<U>>> mapFactory,
                                                                 Supplier<List<U>> listFactory,
                                                                 Function<T, U> elementMapper) {
        //TODO Implement me
        return null;
    }
}
