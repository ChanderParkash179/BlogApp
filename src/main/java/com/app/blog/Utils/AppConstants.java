package com.app.blog.Utils;

public class AppConstants {

    // APP VARIABLES
    public static final String ID = "ID";
    public static final String EMAIL = "EMAIL";
    public static final String NAME = "NAME";
    public static final String USER = "USER";
    public static final String CATEGORY = "CATEGORY";

    // APP VALIDATIONS
    public static final String MINMAX_PASSWORD = "Password should contains minimum 6 & maximum 12 Characters!";
    public static final String MINMAX_NAME = "Name should contains minimum 3 & maximum 20 Characters!";
    public static final String MINMAX_ABOUT = "About should contains minimum 10 & maximum 30 Characters!";
    public static final String FORMAT_EMAIL = "Email should be in proper Format!";
    public static final String MINMAX_TITLE = "Title should contains minimum 4 & maximum 12 Characters!";
    public static final String MINMAX_DESCRIPTION = "Description should contains minimum 6 & maximum 60 Characters!";


    // APP CODES
    public static final String OK = "SUCCESS_200";
    public static final String CREATED = "SUCCESS_201";
    public static final String ACCEPTED = "SUCCESS_202";
    public static final String NO_CONTENT = "SUCCESS_204";
    public static final String FOUND = "SUCCESS_302";
    public static final String BAD_REQUEST = "ERROR_400";
    public static final String FORBIDDEN = "ERROR_403";
    public static final String NOT_FOUND = "ERROR_404";
    public static final String INTERNAL_SERVER_ERROR = "ERROR_500";

    // APP MESSAGES

    // [USER MESSAGES]
    public static final String MSG_NO_ID_AVAILABLE = "There's no id available against the provided request!";
    public static final String MSG_NO_ID_PROVIDED = "There's no id provided in the request!";
    public static final String MSG_EMAIL_AVAILABLE = "Requested email is available already!";
    public static final String MSG_NO_EMAIL_AVAILABLE = "There's no email available against the provided request!";
    public static final String MSG_NO_EMAIL_PROVIDED = "There's no email provided in the request!";
    public static final String MSG_NO_NAME_PROVIDED = "There's no name in the request!";
    public static final String MSG_NO_NAME_AVAILABLE = "There's no user available against the provided requested name!";
    public static final String MSG_NO_PASSWORD_PROVIDED = "There's no password provided in the request!";
    public static final String MSG_NO_ABOUT_PROVIDED = "There's no about provided in the request!";
    public static final String MSG_USER_NOT_AVAILABLE = "Requested User not available!";
    public static final String MSG_USER_FOUND_SUCCESSFULLY = "Requested User found Successfully!";
    public static final String MSG_USER_DELETED_SUCCESSFULLY = "Requested User deleted Successfully!";
    public static final String MSG_USER_SAVED_SUCCESSFULLY = "Requested User saved Successfully!";
    public static final String MSG_USER_UPDATED_SUCCESSFULLY = "Requested User updated Successfully!";

    // [CATEGORY MESSAGES]

    public static final String MSG_NO_TITLE_PROVIDED = "There's no title in the request!";
    public static final String MSG_NO_DESCRIPTION_PROVIDED = "There's no description in the request!";
    public static final String MSG_NO_TITLE_AVAILABLE = "There's no Category available against the requested title!";
    public static final String MSG_CATEGORY_DELETED_SUCCESSFULLY = "Requested Category deleted Successfully!";
    public static final String MSG_CATEGORY_FOUND_SUCCESSFULLY = "Requested Category found Successfully!";
    public static final String MSG_CATEGORY_NOT_AVAILABLE = "Requested Category not available!";
    public static final String MSG_CATEGORY_UPDATED_SUCCESSFULLY = "Requested Category updated Successfully!";
    public static final String MSG_CATEGORY_SAVED_SUCCESSFULLY = "Requested Category saved Successfully!";
    public static final String MSG_CATEGORY_AVAILABLE = "Requested Category is available already!";

}
