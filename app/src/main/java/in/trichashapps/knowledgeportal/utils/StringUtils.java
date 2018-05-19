package in.trichashapps.knowledgeportal.utils;

import in.trichashapps.knowledgeportal.common.App;

public class StringUtils {
    public static String getString(int stringResourceId) {
        return App.getContext().getString(stringResourceId);
    }
}
