package com.itcodes.myhub.onlytest.demo;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @ClassName Demo05
 * @Author sussen
 * @Version 1.0
 * @Date 2020/4/4
 */
public class Demo05 {
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<Object>(o1,referenceQueue);
        o1 = null;
        System.gc();
        //null
        Object o = weakReference.get();
        //o1引用地址
        Reference<?> obj = referenceQueue.poll();
    }
}
