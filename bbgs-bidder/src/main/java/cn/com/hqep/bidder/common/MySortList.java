package cn.com.hqep.bidder.common;

import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by sssJL on 2017-10-26.
 * list 排序 工具类
 */
public class MySortList<E> {
    /**
     * 对列表中的数据按指定字段进行排序。要求类必须有相关的方法返回字符串、整型、日期等值以进行比较。
     * @param list 排序集合
     * @param method 排序字段get方法
     * @param type 是否转特定的比较类型
     * @param reverseFlag 是否倒序排列
     */
    public void sortByMethod(List<E> list, final String method, final String type,
                             final boolean reverseFlag) {
        Collections.sort(list, new Comparator<Object>() {
            @SuppressWarnings("unchecked")
            public int compare(Object arg1, Object arg2) {
                int result = 0;
                try {
                    Method m1 = ((E) arg1).getClass().getMethod(method, null);
                    Method m2 = ((E) arg2).getClass().getMethod(method, null);
                    Object obj1 = m1.invoke(((E)arg1), null);
                    Object obj2 = m2.invoke(((E)arg2), null);
                    if (type !=null ){
                        if(type.equals("String")) {
                            // 字符串
                            result = obj1.toString().compareTo(obj2.toString());
                        }else if(type.equals("Data")) {
                            // 日期
                            long l = ((Date)obj1).getTime() - ((Date)obj2).getTime();
                            if(l > 0) {
                                result = 1;
                            }else if(l < 0) {
                                result = -2;
                            }else {
                                result = 0;
                            }
                        }else if(type.equals("int")) {
                            // 整型（Method的返回参数可以是int的，因为JDK1.5之后，Integer与int可以自动转换了）
                            result = (Integer)obj1 - (Integer)obj2;
                        }else if(type.equals("double")){
                            if (Double.parseDouble(obj1.toString()) > Double.parseDouble(obj2.toString())){
                                result = 1;
                            }
                            if (Double.parseDouble(obj1.toString()) < Double.parseDouble(obj2.toString())){
                                result = -1;
                            }
                            if (Double.parseDouble(obj1.toString()) == Double.parseDouble(obj2.toString())){
                                tblBbgsPackageSupplierModel model1 = (tblBbgsPackageSupplierModel)arg1;
                                tblBbgsPackageSupplierModel model2 = (tblBbgsPackageSupplierModel)arg2;
                                if (Double.parseDouble(model1.getTechnologyScore()) > Double.parseDouble(model2.getTechnologyScore())){
                                    result = 1;
                                }
                                if (Double.parseDouble(model1.getTechnologyScore()) < Double.parseDouble(model2.getTechnologyScore())){
                                    result = -1;
                                }
                                if (Double.parseDouble(model1.getTechnologyScore()) == Double.parseDouble(model2.getTechnologyScore())){
                                    if (Double.parseDouble(model1.getPriceScore()) > Double.parseDouble(model2.getPriceScore())){
                                        result = 1;
                                    }
                                    if (Double.parseDouble(model1.getPriceScore()) < Double.parseDouble(model2.getPriceScore())){
                                        result = -1;
                                    }
                                    if (Double.parseDouble(model1.getPriceScore()) == Double.parseDouble(model2.getPriceScore())){
                                        result = 0;
                                    }
                                }
                            }
                        }
                    }else {
                        if(obj1 instanceof String) {
                            // 字符串
                            result = obj1.toString().compareTo(obj2.toString());
                        }else if(obj1 instanceof Date) {
                            // 日期
                            long l = ((Date)obj1).getTime() - ((Date)obj2).getTime();
                            if(l > 0) {
                                result = 1;
                            }else if(l < 0) {
                                result = -1;
                            }else {
                                result = 0;
                            }
                        }else if(obj1 instanceof Integer) {
                            // 整型（Method的返回参数可以是int的，因为JDK1.5之后，Integer与int可以自动转换了）
                            result = (Integer)obj1 - (Integer)obj2;
                        }else {
                            // 目前尚不支持的对象，直接转换为String，然后比较，后果未知
                            result = obj1.toString().compareTo(obj2.toString());
                            System.err.println("MySortList.sortByMethod方法接受到不可识别的对象类型，转换为字符串后比较返回...");
                        }
                    }

                    if (reverseFlag) {
                        // 倒序
                        result = -result;
                    }
                } catch (NoSuchMethodException nsme) {
                    nsme.printStackTrace();
                } catch (IllegalAccessException iae) {
                    iae.printStackTrace();
                } catch (InvocationTargetException ite) {
                    ite.printStackTrace();
                }

                return result;
            }
        });
    }
}
