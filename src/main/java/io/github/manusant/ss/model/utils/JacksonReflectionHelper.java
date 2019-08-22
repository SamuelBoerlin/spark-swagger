package io.github.manusant.ss.model.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO: write JavaDoc
 *
 * @author loris.sauter
 */
public class JacksonReflectionHelper {

  private JacksonReflectionHelper() {
    // not needed
  }

  public static boolean hasClassAnnotation(final Class cls,
      final Class<? extends Annotation> annotation) {
    return Arrays.stream(cls.getAnnotations())
        .anyMatch(an -> an.annotationType().equals(annotation));
  }

  public static boolean hasFieldAnnotation(final Class cls,
      final Class<? extends Annotation> annoation) {
    return Arrays.stream(cls.getFields()).flatMap(f -> Arrays.stream(f.getAnnotations()))
        .anyMatch(an -> an.annotationType().equals(annoation));
  }

  public static List<Field> getFieldsWithAnnotation(final Class cls,
      final Class<? extends Annotation> annotation) {
    return Arrays.stream(cls.getFields()).filter(f -> hasAnnotation(f, annotation)).collect(
        Collectors.toList());
  }

  public static boolean hasAnnotation(final AccessibleObject obj,
      final Class<? extends Annotation> annotation) {
    return Arrays.stream(obj.getAnnotations())
        .anyMatch(an -> an.annotationType().equals(annotation));
  }

  public static boolean hasConstructorParameterAnnotation(final Class cls,
      final Class<? extends Annotation> annotation) {
    return Arrays.stream(cls.getConstructors())
        .flatMap(c -> Arrays.stream(c.getParameterAnnotations()))
        .flatMap(Arrays::stream).anyMatch(an -> an.annotationType().equals(annotation));
  }

  public static List<Parameter> getConstructorParametersWithAnnotation(final Class cls,
      final Class<? extends Annotation> annotation) {
    return Arrays.stream(cls.getConstructors()).flatMap(c -> Arrays.stream(c.getParameters()))
        .filter(p -> p.getAnnotation(
            JsonProperty.class) != null).collect(Collectors.toList());
  }

}
