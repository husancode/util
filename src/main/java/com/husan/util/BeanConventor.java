package com.husan.util;


import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/6/25
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class BeanConventor {

    private static final Logger logger = LoggerFactory.getLogger(BeanConventor.class);

    public static <F,T>   List<T> transListFrom(List<F> fromList, Class<T> targetClass){

        if(CollectionUtils.isEmpty(fromList) || targetClass == null){
            return Collections.emptyList();
        }
        return Lists.transform(fromList, new Function<F, T>() {
            @Override
            public T apply(F input) {
                try {
                    T targetInstance = targetClass.newInstance();
                    BeanUtils.copyProperties(targetInstance, input);
                    return targetInstance;
                } catch (Exception e) {
                    logger.warn("", e);
                }
                return null;
            }
        });

    }

    public static <F,T> T transBeanFrom(F source, Class<T> targetClass){
        try {
            if(source!=null && targetClass != null){
                T target = targetClass.newInstance();
                BeanUtils.copyProperties(target, source);
                return target;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;

    }

}
