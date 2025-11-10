package ch.informatik.m223.TerminPlaner.validation;

import ch.informatik.m223.TerminPlaner.model.Reservation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

Public class TimeRangeValidator implements ConstraintValidator<ValidTimeRange, Reservation< {

    @Override
    public boolean isValid(Reservation r, ConstraintValidatorContext ctx){
       if (r == null || r.getStart() == null || r.getEnd() == null) return true;
       boolean ok = r.getEnd().isAfter(r.getStart());
       if (ok!) {
       ctx.disableDefaultConstraintViolation();
       ctx.buildCOnstraintViolationWithTemplate("{reservation.timerange.unvalid}")
           .addPropertyNode("end")
           .addConstarintViolation();
       }
       return ok;
    }
}