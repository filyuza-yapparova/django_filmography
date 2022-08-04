package constants;

import com.google.errorprone.annotations.Var;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Constant {
    public static class TimeoutVariables {
        public static final int IMPLICIT_WAIT = 5;
        public static final int EXPLICIT_WAIT = 10;
    }

    public static class Urls {
        public static final String MAIN_URL = "http://localhost:8000/admin/";
        public static final String ACTORS_URL = "http://localhost:8000/admin/core/actor/";
        public static final String MOVIES_URL = "http://localhost:8000/admin/core/movie/";
        public static final String DIRECTORS_URL = "http://localhost:8000/admin/core/director/";
        public static final String CHARACTERS_URL = "http://localhost:8000/admin/core/character/";
    }

    public static class Variables {
        //вытаскивать из проперти?
        public static final String USER_LOGIN = "ryapparov";
        public static final String USER_PASSWORD = "12345678";
        public static final String ACTORS = "Actors";
        public static final String CHARACTERS = "Characters";
        public static final String MOVIES = "Movies";
        public static final String DIRECTORS = "Directors";
        public static final String ADDED_SUCCESSFULLY_NOTIFICATION_TEXT = "The %s “%s” was added successfully.";
        public static final String EDITED_SUCCESSFULLY_NOTIFICATION_TEXT = "The %s “%s” was changed successfully.";
        //public static final String DELETED_SUCCESSFULLY_NOTIFICATION_TEXT = "";
    }

    @RequiredArgsConstructor
    @Getter
    public enum NotificationType {
        ADD(Variables.ADDED_SUCCESSFULLY_NOTIFICATION_TEXT),
        EDIT(Variables.EDITED_SUCCESSFULLY_NOTIFICATION_TEXT);
        //DELETE(Variables.DELETED_SUCCESSFULLY_NOTIFICATION_TEXT);
        private final String text;
    }
}
