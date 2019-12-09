package ca.jrvs.apps.practice;

import static java.util.Arrays.stream;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc {
  @Override
  public Stream<String> createStrStream(String... strings) {
    return stream(strings);
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    return createStrStream(strings).map(String::toUpperCase);
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter((String str) -> str.matches(pattern));
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    return stream(arr);
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.range(start, end);
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    return stream.collect(Collectors.toList());
  }

  @Override
  public List<Integer> toList(IntStream intStream) {
    return intStream.boxed().collect(Collectors.toList());
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.mapToDouble(Math::sqrt);
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(i -> i % 2 != 0);
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    return (String s) -> System.out.print(prefix + s + suffix);
  }

  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    stream(messages).forEach(printer::accept);
  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    this.getOdd(intStream).forEach(num -> printer.accept("" + num));
  }

  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
    return ints.flatMap(Collection::stream);
  }

}
