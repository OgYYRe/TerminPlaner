package ch.informatik.m223.TerminPlaner.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TimeRangeValidator.class)
public @interface ValidTimeRange {
  String message() default "{reservation.timerange.invalid}";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}