package com.app.blog.Utils;

public class ModelConstants {

    // [USER]
    public static final String USER = "user";
    public static final String USER_TABLE = "USER";
    public static final String USER_ID = "user_id";
    public static final String USER_FIRST_NAME = "user_last_name";
    public static final String USER_LAST_NAME = "user_first_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_ROLE = "user_role";
    public static final String USER_SOURCE = "user_source";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_ABOUT = "user_about";

    // [POST]
    public static final String POST = "post";
    public static final String POST_TABLE = "POST";
    public static final String POST_ID = "post_id";
    public static final String POST_TITLE = "post_title";
    public static final String POST_CONTENT = "post_content";
    public static final String POST_IMG_URL = "post_imgUrl";
    public static final String POST_ADDED_DATE = "post_addedDate";

    // [CATEGORY]
    public static final String CATEGORY = "category";
    public static final String CATEGORY_TABLE = "CATEGORY";
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_TITLE = "category_title";
    public static final String CATEGORY_DESCRIPTION = "category_description";

    // [COMMENT]
    public static final String COMMENT_TABLE = "COMMENT";
    public static final String COMMENT_ID = "comment_id";
    public static final String COMMENT_CONTENT = "comment_content";
    public static final String COMMENT = "comment";

    // [VERIFICATION TOKEN]
    public static final String VERIFICATION_TOKEN_TABLE = "VERIFICATION_TOKEN";
    public static final int TOKEN_EXPIRATION_TIME_VALUE = 10;
    public static final String TOKEN_ID = "token_id";
    public static final String TOKEN_NAME = "token";
    public static final String TOKEN_EXPIRATION_TIME = "expiration_time";
    public static final String TOKEN_FOREIGN_KEY = "FK_USER_VERIFY_TOKEN";

}