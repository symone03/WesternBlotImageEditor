import org.im4java.core.Operation;
import org.im4java.script.AbstractScriptGenerator;

import java.io.File;
import java.util.Calendar;

public class BashScriptGenerator extends AbstractScriptGenerator {

    //////////////////////////////////////////////////////////////////////////////

    /**
     * The default constructor.
     */

    public BashScriptGenerator() {
        ESC_EOL = '\\';
        ESC_SPECIAL = '\\';
    }

    //////////////////////////////////////////////////////////////////////////////

    /**
     * Write the header of the script.
     */

    protected void writeHeader() {
        getWriter().println(
                "#!/bin/bash\n" +
                        "#-------------------------------------------------------\n" +
                        "# Bash-script autogenerated by im4java\n" +
                        "# at " + Calendar.getInstance().getTime() + "\n" +
                        "#-------------------------------------------------------\n"
        );

        // add search-path
        String path = getSearchPath();
        if (path != null) {
            getWriter().println(
                    "export PATH=" + path + File.pathSeparator + "$PATH\n"
            );
        }
    }

    //////////////////////////////////////////////////////////////////////////////

    /**
     * Return the token as a script-argument. Normally, the argument token is
     * only part of the script-argument if it contains a [wxh+x+y]-read-spec.
     */

    protected String getScriptArg(String pToken) {
        iArgIndex++;
        if (pToken.length() > Operation.IMG_PLACEHOLDER.length()) {
            return "\"${" + iArgIndex + "}\"" +
                    pToken.substring(Operation.IMG_PLACEHOLDER.length());
        } else {
            return "\"${" + iArgIndex + "}\"";
        }
    }

    //////////////////////////////////////////////////////////////////////////////

    /**
     * Quote the given string.
     *
     * <p>
     * Note that this implementation is not 100% correct, since it does not
     * handle the case with pre-escaped strings correctly.
     * </p>
     */

    protected String quote(String pString) {
        if (pString.indexOf('"') == -1) {
            return '"' + pString + '"';
        } else {
            return '"' + pString.replace("\"", ESC_SPECIAL + "\"") + '"';
        }
    }
}