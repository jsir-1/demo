package com.jsrf;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import org.assertj.core.util.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class DailyTest {

    public static void main(String[] args) {

//        Long id  = 123456712345678909L;
//        Long idValue = 3000L;
//        Map<Long, Object> data = new HashMap<>(2);
//        data.put(id, idValue);
//
//        String jsonString = JSON.toJSONString(data);
//
//        // 反序列化时Long被转为了Integer
//        Map map = JSON.parseObject(jsonString, Map.class);
//        Object idObj = map.get(id);
//        Assert.assertTrue(idObj instanceof Integer);
//        Assert.assertEquals(idValue, (Long) idObj);
    }

    @Test
    public void stringDemo() {
        Assert.assertThat(Strings.repeat("h", 5), equalTo("hhhhh"));

        String join = Joiner.on("#").join(Lists.list("hi", "jfifj"));
        Assert.assertEquals(join, "hi#jfifj");
    }

    @Test
    public void test() {
        Function<String, Integer> function = new Function<String, Integer>() {

            @Nullable
            @Override
            public Integer apply(@Nullable String input) {
                return input.length();
            }
        };

        System.out.println(function.apply("haha"));



        java.util.function.Function<String, Integer> function1 = String::length;
        System.out.println(function1.apply("aaa"));
    }

}