package com.itontheway.manage.util;


import com.itontheway.manage.common.Result;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * @author: 公众号 itontheway
 * @description: 校验实体类是否合法
 * @date 2020/3/12 10:46
 */
public class ValidatorUtil {

   private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

   public static <T> Result validatedEntity(T obj, Class<T>... groups){
       Set<ConstraintViolation<T>> validate = validator.validate(obj, groups);
       if(CollectionUtils.isNotEmpty(validate)){
           StringBuilder stringBuilder = new StringBuilder();
           for (ConstraintViolation<T> tConstraintViolation : validate) {
               String message = tConstraintViolation.getMessage();
               stringBuilder.append(message);
           }
           Result.fail(stringBuilder.toString());
       }
       return null;
   }


}
