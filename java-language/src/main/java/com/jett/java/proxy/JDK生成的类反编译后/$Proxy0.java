package com.jett.java.proxy.JDK生成的类反编译后;

import com.jett.java.proxy.Human;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 本类是JDK自动生成，反编译后。
 * 本类生成的位置：Java-Tutorials\com\jett\java\proxy
 */
final class $Proxy0 extends Proxy implements Human {
  
  // 这里多个方法由下面的 static 方法块初始化。
  private static Method m1;

  private static Method m2;

  private static Method m3;

  private static Method m4;

  private static Method m0;
  
  /**
   * 本类的构建方法，传入 InvocationHandler 实现类（即 ProxyInvocationHandler），然后直接调用父类构造方法，
   * 点进去，就是 this.h = paramInvocationHandler
   * @param paramInvocationHandler
   */
  public $Proxy0(InvocationHandler paramInvocationHandler) {
    super(paramInvocationHandler);
  }

  public final boolean equals(Object paramObject) {
    try {
      return ((Boolean)this.h.invoke(this, m1, new Object[] { paramObject })).booleanValue();
    } catch (Error|RuntimeException error) {
      throw null;
    } catch (Throwable throwable) {
      throw new UndeclaredThrowableException(throwable);
    }
  }

  public final String toString() {
    try {
      return (String)this.h.invoke(this, m2, null);
    } catch (Error|RuntimeException error) {
      throw null;
    } catch (Throwable throwable) {
      throw new UndeclaredThrowableException(throwable);
    }
  }

  public final void test(String paramString) {
    try {
      this.h.invoke(this, m3, new Object[] { paramString });
      return;
    } catch (Error|RuntimeException error) {
      throw null;
    } catch (Throwable throwable) {
      throw new UndeclaredThrowableException(throwable);
    }
  }

  public final String sayHi(String paramString) {
    try {
      return (String)this.h.invoke(this, m4, new Object[] { paramString });
    } catch (Error|RuntimeException error) {
      throw null;
    } catch (Throwable throwable) {
      throw new UndeclaredThrowableException(throwable);
    }
  }

  public final int hashCode() {
    try {
      return ((Integer)this.h.invoke(this, m0, null)).intValue();
    } catch (Error|RuntimeException error) {
      throw null;
    } catch (Throwable throwable) {
      throw new UndeclaredThrowableException(throwable);
    }
  }

  static {
    try {
      m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
      m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
      m3 = Class.forName("com.jett.java.proxy.Human").getMethod("test", new Class[] { Class.forName("java.lang.String") });
      m4 = Class.forName("com.jett.java.proxy.Human").getMethod("sayHi", new Class[] { Class.forName("java.lang.String") });
      m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
      //return; // 生成的代码有个return，这里语法错误，不知为何。
    } catch (NoSuchMethodException noSuchMethodException) {
      throw new NoSuchMethodError(noSuchMethodException.getMessage());
    } catch (ClassNotFoundException classNotFoundException) {
      throw new NoClassDefFoundError(classNotFoundException.getMessage());
    }
  }
}
