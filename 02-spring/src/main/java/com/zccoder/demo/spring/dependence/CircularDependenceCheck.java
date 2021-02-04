package com.zccoder.demo.spring.dependence;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 循环依赖检查示例
 *
 * @author zc
 * @date 2021/01/08
 */
public class CircularDependenceCheck {

    private static Map<String, Set<String>> dependenceMap;

    static {
        dependenceMap = new HashMap<>(16);

        dependenceMap.put("A", buildSet("B", "C"));
        dependenceMap.put("B", buildSet("C", "F"));
        dependenceMap.put("C", buildSet());
        dependenceMap.put("D", buildSet());
        dependenceMap.put("E", buildSet("A"));
        dependenceMap.put("F", buildSet("N"));
        dependenceMap.put("N", buildSet());
    }

    private static Set<String> buildSet(String... args) {
        return Arrays.stream(args).collect(Collectors.toSet());
    }

    private static Set<String> waitExecute = new HashSet<>(16);
    private static Set<String> finishExecute = new HashSet<>(16);

    public static void main(String[] args) {
        for (String name : dependenceMap.keySet()) {
            doExecute(name);
        }

        System.out.println("执行完毕后waitExecute：" + Arrays.toString(waitExecute.toArray()));
        System.out.println("执行完毕后finishExecute：" + Arrays.toString(finishExecute.toArray()));
    }

    private static void doExecute(String name) {
        if (finishExecute.contains(name)) {
            return;
        }

        Set<String> dependenceNames = dependenceMap.get(name);
        if (allDependenceExecute(dependenceNames)) {
            // 全部依赖名称已执行，则执行当前名称
            System.out.println("执行完毕：" + name);
            finishExecute.add(name);
            waitExecute.remove(name);
            return;
        }

        // 判断当前名称是否已经在待执行集合中
        if (waitExecute.contains(name)) {
            throw new IllegalArgumentException(String.format("执行【%s】失败，出现循环依赖，当前等待执行的有：%s", name, Arrays.toString(waitExecute.toArray())));
        }

        // 将当前名称加入待执行集合中
        waitExecute.add(name);

        // 执行依赖名称
        for (String dependenceName : dependenceNames) {
            doExecute(dependenceName);
        }

        // 全部依赖名称执行完后，再次执行当前名称
        doExecute(name);
    }

    private static boolean allDependenceExecute(Set<String> dependenceNames) {
        if (CollectionUtils.isEmpty(dependenceNames)) {
            return true;
        }

        for (String dependenceName : dependenceNames) {
            if (!finishExecute.contains(dependenceName)) {
                return false;
            }
        }

        return true;
    }

}
