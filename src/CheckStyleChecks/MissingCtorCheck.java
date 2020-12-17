package CheckStyleChecks;

import com.puppycrawl.tools.checkstyle.StatelessCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * <p>
 * Checks that classes (except abstract ones) define a constructor and don't rely
 * on the default one.
 * </p>
 * <p>
 * To configure the check:
 * </p>
 * <pre>
 * &lt;module name=&quot;MissingCtor&quot;/&gt;
 * </pre>
 * <p>Example:</p>
 * <pre>
 * class ExampleOk { // OK
 *   private int a;
 *   ExampleOk(int a) {
 *     this.a = a;
 *   }
 * }
 * class ExampleDefaultCtor { // OK
 *   private String s;
 *   ExampleDefaultCtor() {
 *     s = "foobar";
 *   }
 * }
 * class InvalidExample { // violation, class must have a constructor.
 *   public void test() {}
 * }
 * abstract class AbstractExample { // OK
 *   public abstract void test() {}
 * }
 * </pre>
 * <p>
 * Parent is {@code com.puppycrawl.tools.checkstyle.TreeWalker}
 * </p>
 * <p>
 * Violation Message Keys:
 * </p>
 * <ul>
 * <li>
 * {@code missing.ctor}
 * </li>
 * </ul>
 *
 * @since 3.4
 */
@StatelessCheck
public class MissingCtorCheck extends AbstractCheck {

    /**
     * A key is pointing to the warning message text in "messages.properties"
     * file.
     */
    public static final String MSG_KEY = "missing.ctor";

    @Override
    public int[] getDefaultTokens() {
        return getRequiredTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return getRequiredTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[] {TokenTypes.CLASS_DEF};
    }

    @Override
    public void visitToken(DetailAST ast) {
        final DetailAST modifiers = ast.findFirstToken(TokenTypes.MODIFIERS);
        if (modifiers.findFirstToken(TokenTypes.ABSTRACT) == null
                && ast.findFirstToken(TokenTypes.OBJBLOCK).findFirstToken(TokenTypes.CTOR_DEF) == null) {
            log(ast, MSG_KEY);
        }
    }

}