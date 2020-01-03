package com.xmd.firstBoot.JavaEightAction.chapterSix.sixPointOne;

import com.xmd.firstBoot.entity.Trader;
import com.xmd.firstBoot.entity.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @Author: xx
 * @Description:
 * @Date: Created in 14:16 2020/1/2
 */
public class UsingTheCollector {


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

    private static Stream<Transaction> transactionStream = transactions.stream();

    public static void main(String[] args) {



        //下面是一些查询的例子，看看你用collect和收集器能够做什么。

        // 对一个交易列表按货币分组，获得该货币的所有交易额总和（返回一个Map<Currency,Integer>）。
        // 将交易列表分成两组：贵的和不贵的（返回一个Map<Boolean, List<Transaction>>）。
        // 创建多级分组，比如按城市对交易分组，然后进一步按照贵或不贵分组（返回一个Map<Boolean, List<Transaction>>）。

        //代码清单6-1 用指令式风格对交易按照货币分组
        Map<Trader, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            Trader trader = transaction.getTrader();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(trader);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(trader, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

        //同上边的功能相同java8实现
        Map<Trader, List<Transaction>> transactionsByCurrenciesSimple = transactions.stream().collect(groupingBy(Transaction::getTrader));

        List<Transaction> transactionList = transactionStream.collect(Collectors.toList());


    }
}
