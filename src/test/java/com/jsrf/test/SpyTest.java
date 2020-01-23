package com.jsrf.test;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Desc:请输入类描述信息
 * ----------------------------------
 *
 * @author:jishirifang@meituan.com Time:2020/1/8 7:43 下午
 */
public class SpyTest {
    @Test
    public void testLinkedListSpyWrong() {
        // Lets mock a LinkedList
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        // this does not work
        // real method is called so spy.get(0)
        // throws IndexOutOfBoundsException (list is still empty)
        when(spy.get(0)).thenReturn("foo");

        assertEquals("foo", spy.get(0));
    }

    @Test
    public void testLinkedListSpyCorrect() {
        // Lets mock a LinkedList
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        // You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);

        assertEquals("foo", spy.get(0));
    }
}
