package com.xmd.firstBoot.JavaEightActualCombat.chapterFive.fivePointFive;

import com.xmd.firstBoot.entity.Trader;
import com.xmd.firstBoot.entity.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

/**
 * @Author: xx
 * @Description:
 * @Date: Created in 14:23 2019/12/31
 */
public class TraderTrading {

    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario","Milan");
    private static Trader alan = new Trader("Alan","Cambridge");
    private static Trader brian = new Trader("Brian","Cambridge");



    private static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String args[]) {

        //allTransaction(transactions);
        //allCity(transactions);
        //traderFromCambridge(transactions);
        //allTraderName(transactions);
        //isWorkCityInMilan(transactions);
        //transactionValueInCambridge(transactions);
        //transactionValueMAX(transactions);
        transactionValueMIN(transactions);
    }


    //(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
    public static List<Transaction> allTransaction(List<Transaction> list){
        List<Transaction> res = list.stream()
                .filter(x->x.getYear()==2011)
                .sorted(comparing(Transaction::getValue))
                .collect(Collectors.toList());
        return res;
    }

    //(2)交易员都在哪些不同的城市工作过？
    public static Set<String> allCity(List<Transaction> list){

        //办法一
        /*List<String> res = list.stream()
                .map(x->x.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());*/

        //办法二
        Set<String> res = list.stream()
                .map(x->x.getTrader().getCity())
                .collect(toSet());

        return res;
    }

    //(3)查找所有来自于剑桥的交易员，并按姓名排序。
    public static List<Trader> traderFromCambridge(List<Transaction> list){
        List<Trader> res = list.stream()
                //从交易中提取所有交易员
                .map(Transaction::getTrader)
                //仅选择位于剑桥的交易员
                .filter(x->x.getCity().equals("Cambridge"))
                //确保没有任何重复
                .distinct()
                //对生成的交易员流按照姓名进行排序
                .sorted(comparing(Trader::getName))
                .collect(Collectors.toList());
        return res;
    }

    //(4)返回所有交易员的姓名字符串，按字母顺序排序。
    public static String allTraderName(List<Transaction> list){
        //此解决方案效率不高
        /*String res = list.stream()
                .map(x->x.getTrader().getName())
                //只选择不相同的姓名
                .distinct()
                //对姓名按字母顺序排序
                .sorted()
                //逐个拼接每个名字，得到一个将所有名字连接起来的String
                .reduce("",(n1,n2)->n1+n2);*/
        //更为高效的解决方案
        String res =transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());
        return res;
    }

    //(5)有没有交易员是在米兰工作的？
    public static boolean isWorkCityInMilan(List<Transaction> list){
        //把一个谓词传递给anyMatch，检查是否有交易员在米兰工作
        boolean bool = list.stream().anyMatch(x->x.getTrader().getCity().equals("Milan"));
        return bool;
    }

    //(6)打印生活在剑桥的交易员的所有交易额。
    public static int transactionValueInCambridge(List<Transaction> list){
        int transactionValue = list.stream()
                .filter(x->x.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                //.reduce(0,(a,b)->a+b);
                //这段代码的问题是，它有一个暗含的装箱成本
                .reduce(0,Integer::sum);
        return transactionValue;
    }

    //(7)所有交易中，最高的交易额是多少？
    public static Integer transactionValueMAX(List<Transaction> list){
        Optional<Integer> max = list.stream()
                //提取每项交易的交易额
                .map(Transaction::getValue)
                //计算生成的流中的最大值
                .reduce(Integer::max);
        return max.get();
    }

    //(8)找到交易额最小的交易。
    public static Integer transactionValueMIN(List<Transaction> list){
        //方式一
        /*Optional<Integer> min = list.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);*/

        //方式二
        /*Optional<Transaction> min = list.stream()
                .reduce((n1,n2)->n1.getValue()>n2.getValue()?n2:n1);*/

        //方式三
        Optional<Transaction> min = list.stream()
                .min(comparing(Transaction::getValue));

        return min.get().getValue();
    }

}
