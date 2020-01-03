package com.xmd.firstBoot.JavaEightAction.chapterFive.fivePointSeven;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: xx
 * @Description:
 * @Date: Created in 11:06 2020/1/2
 */
public class BuildStream {

    public static void main(String[] args) {

        //5.7.1 由值创建流

        //使用静态方法Stream.of，通过显式值创建一个流

        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        //使用empty得到一个空流

        Stream<String> empty = Stream.empty();


        //5.7.2 由数组创建流

        int[] numbers = {2, 3, 5, 7, 11, 13};
        Arrays.stream(numbers).sum();

        //5.7.3 由文件生成流

        //Java中用于处理文件等I/O操作的NIO API（非阻塞 I/O）已更新，以便利用Stream API。
        //java.nio.file.Files中的很多静态方法都会返回一个流。例如，一个很有用的方法是
        //Files.lines，它会返回一个由指定文件中的各行构成的字符串流。使用你迄今所学的内容，
        //你可以用这个方法看看一个文件中有多少各不相同的词


        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line-> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }catch(IOException e){

        }

        //使用Files.lines得到一个流，其中的每个元素都是给定文件中的一行。然后，你
        //可以对line调用split方法将行拆分成单词。应该注意的是，你该如何使用flatMap产生一个扁
        //平的单词流，而不是给每一行生成一个单词流

        //5.7.4 由函数生成流：创建无限流

        //Stream API提供了两个静态方法来从函数生成流：Stream.iterate和Stream.generate。
        //这两个操作可以创建所谓的无限流：不像从固定集合创建的流那样有固定大小的流。由iterate
        //和generate产生的流会用给定的函数按需创建值，因此可以无穷无尽地计算下去！一般来说，
        //应该使用limit(n)来对这种流加以限制，以避免打印无穷多个值。

        //1. 迭代

        Stream.iterate(0,n->n+2)
                .limit(5)
                .forEach(System.out::println);

        //一般来说，在需要依次生成一系列值的时候应该使用iterate，比如一系列日期：1月31日，
        //2月1日，依此类推

        //测验5.4：斐波纳契元组序列

        //斐波纳契数列是著名的经典编程练习。下面这个数列就是斐波纳契数列的一部分：0, 1, 1,
        //2, 3, 5, 8, 13, 21, 34, 55…数列中开始的两个数字是0和1，后续的每个数字都是前两个数字之和。

        //斐波纳契元组序列与此类似，是数列中数字和其后续数字组成的元组构成的序列：(0, 1),
        //(1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21) …

        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));

        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]})
                .limit(10)
                .map(t->t[0])
                .forEach(System.out::println);


        //2. 生成

        //与iterate方法类似，generate方法也可让你按需生成一个无限流。但generate不是依次
        //对每个新生成的值应用函数的。它接受一个Supplier<T>类型的Lambda提供新的值

        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        //generate方法还有什么用途。我们使用的供应源（指向Math.random的方
        //法引用）是无状态的：它不会在任何地方记录任何值，以备以后计算使用。但供应源不一定是无
        //状态的。你可以创建存储状态的供应源，它可以修改状态，并在为流生成下一个值时使用。举个
        //例子，我们将展示如何利用generate创建测验5.4中的斐波纳契数列，这样你就可以和用
        //iterate方法的办法比较一下。但很重要的一点是，在并行代码中使用有状态的供应源是不安全
        //的。因此下面的代码仅仅是为了内容完整，应尽量避免使用


        IntStream.generate(fib).
                limit(10).
                forEach(System.out::println);



    }


    private static IntSupplier fib = new IntSupplier(){
        private int previous = 0;
        private int current = 1;

        //getAsInt在调用时会改变对象的状态，由此在每次调用时产生新的值
        @Override
        public int getAsInt() {
            int oldPrevious = this.previous;
            int nextValue = this.previous + this.current;
            this.previous = this.current;
            this.current = nextValue;
            return oldPrevious;
        }
    };



}
