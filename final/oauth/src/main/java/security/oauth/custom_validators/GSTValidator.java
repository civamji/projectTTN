package security.oauth.custom_validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GSTValidator
        implements ConstraintValidator<ValidGST, String> {

    private Pattern pattern;
    private Matcher matcher;

    private static final String GST_PATTERN = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}";

    @Override
    public void initialize(ValidGST constraintAnnotation) {
    }

    @Override
    public boolean isValid(String GST, ConstraintValidatorContext context){
        return (validateGST(GST));
    }

    private boolean validateGST(String GST) {
        pattern = Pattern.compile(GST_PATTERN);
        matcher = pattern.matcher(GST);
        return matcher.matches();
    }
}
