package com.jsrf.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Desc:请输入类描述信息
 * ----------------------------------
 *
 * @author:jishirifang@meituan.com Time:2020/1/8 7:26 下午
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Mock
    MyClass test;
    //    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private List mockList;

    @Test
    public void shorthand() {
        mockList.add(1);
        verify(mockList).add(1);
    }

    @Test
    public void answerTest() {
        when(mockList.get(anyInt())).thenAnswer(new CustomAnswer());
        assertEquals("hello world:0", mockList.get(0));
        assertEquals("hello world:999", mockList.get(999));
    }

    private class CustomAnswer implements Answer<String> {
        @Override
        public String answer(InvocationOnMock invocation) throws Throwable {
            Object[] args = invocation.getArguments();
            return "hello world:" + args[0];
        }
    }

    @Test
    public void answer_with_callback() {
        //使用Answer来生成我们我们期望的返回
        when(mockList.get(anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                return "hello world:" + arguments[0];
            }
        });
        assertEquals("hello world:0", mockList.get(0));
        assertEquals("hello world:999", mockList.get(999));
    }

    @Test
    public void unstubbed_invocations() {
        //mock对象使用Answer来对未预设的调用返回默认期望值
        List mock = mock(List.class, new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return 999;
            }
        });
        //下面的get(1)没有预设，通常情况下会返回NULL，但是使用了Answer改变了默认期望值
        assertEquals(999, mock.get(1));
        //下面的size()没有预设，通常情况下会返回0，但是使用了Answer改变了默认期望值
        assertEquals(999, mock.size());
    }

    @Test
    public void test1() {
        when(test.getUniqueId()).thenReturn(43);
        // use mock in test....
        assertEquals(test.getUniqueId(), 43);
    }


    // demonstrates the return of multiple values
    @Test
    public void testMoreThanOneReturnValue() {
        Iterator<String> i = mock(Iterator.class);
        when(i.next()).thenReturn("Mockito").thenReturn("rocks");
        String result = i.next() + " " + i.next();
        //assert
        assertEquals("Mockito rocks", result);
    }

    // this test demonstrates how to return values based on the input
    @Test
    public void testReturnValueDependentOnMethodParameter() {
        Comparable<String> c = mock(Comparable.class);
        when(c.compareTo("Mockito")).thenReturn(1);
        when(c.compareTo("Eclipse")).thenReturn(2);
        //assert
        assertEquals(1, c.compareTo("Mockito"));
        assertEquals(2, c.compareTo("Eclipse"));
    }

// this test demonstrates how to return values independent of the input value

    @Test
    public void testReturnValueInDependentOnMethodParameter() {
        Comparable<Integer> c = mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        //assert
        assertEquals(-1, c.compareTo(9));
    }

    @Test(expected = IOException.class)
    public void when_thenThrow() throws IOException {
        OutputStream outputStream = mock(OutputStream.class);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        //预设当流关闭时抛出异常
        doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }

    @Test
    public void returnsSmartNullsTest() {
        List mock = mock(List.class, RETURNS_SMART_NULLS);
        System.out.println(mock.get(0));

        //使用RETURNS_SMART_NULLS参数创建的mock对象，不会抛出NullPointerException异常。另外控制台窗口会提示信息“SmartNull returned by unstubbed get() method on mock”
        System.out.println(mock.toArray().length);
    }

// return a value based on the type of the provide parameter

//    @Test
//    public void testReturnValueInDependentOnMethodParameter2()  {
//        Comparable<Todo> c= mock(Comparable.class);
//        when(c.compareTo(isA(Todo.class))).thenReturn(0);
//        //assert
//        assertEquals(0, c.compareTo(new Todo(1)));
//    }

    @Test
    public void testVerify() {
        // create and configure mock
        MyClass test = Mockito.mock(MyClass.class);
        when(test.getUniqueId()).thenReturn(43);


        // call method testing on the mock with parameter 12
        test.testing(12);
        test.getUniqueId();
        test.getUniqueId();


        // now check if method testing was called with the parameter 12
        verify(test).testing(ArgumentMatchers.eq(12));

        // was the method called twice?
        verify(test, times(2)).getUniqueId();

        // other alternatives for verifiying the number of method calls for a method
        verify(test, never()).someMethod("never called");
//        verify(test, atLeastOnce()).someMethod("called at least once");
//        verify(test, atLeast(2)).someMethod("called at least twice");
//        verify(test, times(5)).someMethod("called five times");
//        verify(test, atMost(3)).someMethod("called at most 3 times");
        // This let's you check that no other methods where called on this object.
        // You call it after you have verified the expected method calls.
        verifyNoMoreInteractions(test);
    }

    class MyClass {
        public int multiply(int i, int j) {
            return i * j;
        }

        public int getUniqueId() {
            return 0;
        }

        public void testing(int i) {
        }

        public void someMethod(String never_called) {

        }
    }


    @Test
    public void deepstubsTest() {
        Account account = mock(Account.class, RETURNS_DEEP_STUBS);
        when(account.getRailwayTicket().getDestination()).thenReturn("Beijing");
        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing", account.getRailwayTicket().getDestination());
    }

    @Test
    public void deepstubsTest2() {
        Account account = mock(Account.class);
        RailwayTicket railwayTicket = mock(RailwayTicket.class);
        when(account.getRailwayTicket()).thenReturn(railwayTicket);
        when(railwayTicket.getDestination()).thenReturn("Beijing");

        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing", account.getRailwayTicket().getDestination());
    }

    public class RailwayTicket {
        private String destination;

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }
    }

    public class Account {
        private RailwayTicket railwayTicket;

        public RailwayTicket getRailwayTicket() {
            return railwayTicket;
        }

        public void setRailwayTicket(RailwayTicket railwayTicket) {
            this.railwayTicket = railwayTicket;
        }
    }

    @Test(expected = RuntimeException.class)
    public void doThrow_when() {
        List list = mock(List.class);
        doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }

    //SPY
//    @Test(expected = IndexOutOfBoundsException.class)
    @Test
    public void spy_on_real_objects() {
        List list = new LinkedList();
        List spy = spy(list);
        //下面预设的spy.get(0)会报错，因为会调用真实对象的get(0)，所以会抛出越界异常
//        when(spy.get(0)).thenReturn(3);
//        assertEquals(3, spy.get(0));

        //使用doReturn-when可以避免when-thenReturn调用真实对象api
        doReturn(999).when(spy).get(999);
        //预设size()期望值
        when(spy.size()).thenReturn(100);
        //调用真实对象的api
        spy.add(1);
        spy.add(2);
        assertEquals(100, spy.size());
        assertEquals(1, spy.get(0));
        assertEquals(2, spy.get(1));
        verify(spy).add(1);
        verify(spy).add(2);
        assertEquals(999, spy.get(999));
        spy.get(2);
    }

    @Test
    public void real_partial_mock() {
        //通过spy来调用真实的api
        List list = spy(new ArrayList());
        assertEquals(0, list.size());
        A a = mock(A.class);
        //通过thenCallRealMethod来调用真实的api
        when(a.doSomething(anyInt())).thenCallRealMethod();
        assertEquals(999, a.doSomething(999));
    }


    class A {
        public int doSomething(int i) {
            return i;
        }
    }


    @Test
    public void verifying_number_of_invocations() {
        List list = mock(List.class);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(3);
        //验证是否被调用一次，等效于下面的times(1)
        verify(list).add(1);
        verify(list, times(1)).add(1);
        //验证是否被调用2次
        verify(list, times(2)).add(2);
        //验证是否被调用3次
        verify(list, times(3)).add(3);
        //验证是否从未被调用过
        verify(list, never()).add(4);
        //验证至少调用一次
        verify(list, atLeastOnce()).add(1);
        //验证至少调用2次
        verify(list, atLeast(2)).add(2);
        //验证至多调用3次
        verify(list, atMost(3)).add(3);
    }

    @Test(expected = RuntimeException.class)
    public void consecutive_calls() {
        //模拟连续调用返回期望值，如果分开，则只有最后一个有效
        when(mockList.get(0)).thenReturn(0);
        when(mockList.get(0)).thenReturn(1);
        when(mockList.get(0)).thenReturn(2);
        when(mockList.get(1)).thenReturn(0).thenReturn(1).thenThrow(new RuntimeException());
        assertEquals(2, mockList.get(0));
        assertEquals(2, mockList.get(0));
        assertEquals(0, mockList.get(1));
        assertEquals(1, mockList.get(1));
        //第三次或更多调用都会抛出异常
        mockList.get(1);
    }


    @Test
    public void verification_in_order() {
        List list = mock(List.class);
        List list2 = mock(List.class);
        list.add(1);
        list2.add("hello");
        list.add(2);
        list2.add("world");
        //将需要排序的mock对象放入InOrder
        InOrder inOrder = inOrder(list, list2);
        //下面的代码不能颠倒顺序，验证执行顺序
        inOrder.verify(list).add(1);
        inOrder.verify(list2).add("hello");
        inOrder.verify(list).add(2);
        inOrder.verify(list2).add("world");
    }
}
