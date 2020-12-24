package com.itcodes.myhub.javaagent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @ClassName AgentMain
 * @Author sussenn
 * @Version 1.0.0
 * @Date 2020/11/20
 */
public class AgentMain {
    public static void premain(String args, Instrumentation instrumentation) {
        instrumentation.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className,
                                    Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                                    byte[] classfileBuffer) throws IllegalClassFormatException {
                //如果不是针对的类则放行
                if (!"com/itcodes/myhub/javaagent/UserService".equals(className)) {
                    return null;
                }

                try {
                    //获取类
                    ClassPool pool = new ClassPool();
                    pool.appendSystemPath();
                    CtClass ctClass = pool.get("com.itcodes.myhub.javaagent.UserService");
                    //获取方法
                    CtMethod method = ctClass.getDeclaredMethod("sayHello");
                    //插入代码
                    method.insertBefore("System.out.println(\"当前时间:\"+System.currentTimeMillis());");
                    //转换字节码
                    return ctClass.toBytecode();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        });
    }
}
