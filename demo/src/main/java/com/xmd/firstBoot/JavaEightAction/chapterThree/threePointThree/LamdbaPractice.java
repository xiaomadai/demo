package com.xmd.firstBoot.JavaEightAction.chapterThree.threePointThree;

import com.xmd.firstBoot.JavaEightAction.interfance.BufferedReaderProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author: xx
 * @Description: Lambda 付诸实践
 * @Date: Created in 10:48 2020/1/3
 */
public class LamdbaPractice {


    public static void main(String[] args) {

        //栗子

        //资源处理（例如处理文件或数据库）时一个常见的模式就是打开一个资源，做一些处理，
        //然后关闭资源。这个设置和清理阶段总是很类似，并且会围绕着执行处理的那些重要代码。这就
        //是所谓的环绕执行


        //这就是做有用工作的那行代码
        //BufferedReader br = new  BufferedReader(new FileReader("data.txt"));

        //3.3.1 第 1 步：记得行为参数化

        //现在这段代码是有局限的。你只能读文件的第一行。如果你想要返回头两行，甚至是返回使
        //用最频繁的词，该怎么办呢？

        //在理想的情况下，你要重用执行设置和清理的代码，并告诉
        //processFile方法对文件执行不同的操作。这听起来是不是很耳熟？是的，你需要把
        //processFile的行为参数化。你需要一种方法把行为传递给processFile，以便它可以利用
        //BufferedReader执行不同的行为。

        //传递行为正是Lambda的拿手好戏。
        //那要是想一次读两行，这个新的processFile方法看起
        //来又该是什么样的呢？基本上，你需要一个接收BufferedReader并返回String的Lambda



        //3.3.2 第 2 步：使用函数式接口来传递行为

        //BufferedReaderProcessor

        //3.3.3 第 3 步：执行一个行为

        //任何BufferedReader -> String形式的Lambda都可以作为参数来传递，因为它们符合
        //BufferedReaderProcessor接口中定义的process方法的签名。现在你只需要一种方法在
        //processFile主体内执行Lambda所代表的代码。请记住，Lambda表达式允许你直接内联，为
        //函数式接口的抽象方法提供实现，并且将整个表达式作为函数式接口的一个实例。因此，你可
        //以在processFile主体内，对得到的BufferedReaderProcessor对象调用process方法执行
        //处理：

        //3.3.4 第 4 步：传递 Lambda
        try {
            String oneLine = processFile((BufferedReader br) -> br.readLine());
            String result = processFile((BufferedReader br) ->br.readLine() + br.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }



    }


    public static String processFile() throws IOException {
        //这就是做有用工作的那行代码
        try(BufferedReader br = new  BufferedReader(new FileReader("data.txt"))){
            return br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            //执行一个行为
            return p.process(br);
        }
    }

}
