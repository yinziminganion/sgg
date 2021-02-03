package com.yinziming.part16.java8;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Optional类：为了在程序中避免出现空指针异常而创建的
 * <p>
 * Optional.of(T t)创建一个Optional实例，t必须非空
 * Optional.empty()创建一个空的Optional实例
 * Optional.ofNullable(T t)创建一个Optional实例，t可以为null
 */
public class OptionalTest {
    public static void main(String[] args) {
        Optional<Girl> girl = Optional.of(new Girl());
        System.out.println(girl);
    }

    public String getGirlName(Boy boy) {
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("第一个女孩")));
        Girl girl1 = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl1);
        Girl girl2 = girlOptional.orElse(new Girl("第二个女孩"));
        return girl2.getName();
    }

    @Test
    public void test1() {
        Boy boy = null;//第一个女孩
//        Boy boy = new Boy();//第二个女孩
        String girlName = getGirlName(boy);
        System.out.println(girlName);
    }
}

class Boy {
    private Girl girl;

    public Boy() {
    }

    public Boy(Girl girl) {
        this.girl = girl;
    }

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "girl=" + girl +
                '}';
    }
}

class Girl {
    private String name;

    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Girl(String name) {
        this.name = name;
    }

    public Girl() {
    }
}