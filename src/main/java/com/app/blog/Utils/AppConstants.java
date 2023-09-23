package com.app.blog.Utils;

public class AppConstants {

    // APP VARIABLES
    public static final String ID = "ID";
    public static final String EMAIL = "EMAIL";
    public static final String NAME = "NAME";
    public static final String USER = "USER";
    public static final String CATEGORY = "CATEGORY";
    public static final String POST = "POST";
    public static final String COMMENT = "COMMENT";

    // EXTERNAL VARIABLES
    public static final String PAGE_NO = "pageNo";
    public static final String PAGE_SIZE = "pageSize";
    public static final String SORT_BY = "sortBy";
    public static final String ORDER_BY = "orderBy";
    public static final String FIELD_ID = "id";
    public static final String ORDER_BY_ASC = "asc";

    // SIZES
    public static final String PAGE_NO_DEFAULT_VALUE = "1";
    public static final String PAGE_SIZE_DEFAULT_VALUE = "5";

    // APP VALIDATIONS
    public static final String MINMAX_PASSWORD = "Password should contains minimum 6 & maximum 100 Characters!";
    public static final String MINMAX_NAME = "Name should contains minimum 3 & maximum 20 Characters!";
    public static final String MINMAX_ABOUT = "About should contains minimum 10 & maximum 60 Characters!";
    public static final String FORMAT_EMAIL = "Email should be in proper Format!";
    public static final String MINMAX_TITLE = "Title should contains minimum 4 & maximum 12 Characters!";
    public static final String MINMAX_CONTENT = "Content should contains minimum 6 & maximum 60 Characters!";
    public static final String MINMAX_DESCRIPTION = "Description should contains minimum 6 & maximum 60 Characters!";
    public static final String CONTENT_MSG = "Content should contains minimum 6 & maximum 60 Characters!";

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
    public static final String MSG_USER_EMAIL_ALREADY_AVAILABLE = "Requested Email is already assigned to other User!";

    // [CATEGORY MESSAGES]
    public static final String MSG_NO_TITLE_PROVIDED = "There's no title in the request!";
    public static final String MSG_USER_TITLE_ALREADY_AVAILABLE = "Requested Title is already assigned to other Category!";
    public static final String MSG_NO_DESCRIPTION_PROVIDED = "There's no description in the request!";
    public static final String MSG_NO_TITLE_AVAILABLE = "There's no Category available against the requested title!";
    public static final String MSG_CATEGORY_DELETED_SUCCESSFULLY = "Requested Category deleted Successfully!";
    public static final String MSG_CATEGORY_FOUND_SUCCESSFULLY = "Requested Category found Successfully!";
    public static final String MSG_CATEGORY_NOT_AVAILABLE = "Requested Category not available!";
    public static final String MSG_CATEGORY_UPDATED_SUCCESSFULLY = "Requested Category updated Successfully!";
    public static final String MSG_CATEGORY_SAVED_SUCCESSFULLY = "Requested Category saved Successfully!";
    public static final String MSG_CATEGORY_AVAILABLE = "Requested Category is available already!";

    // [POST MESSAGES]
    public static final String MSG_NO_CONTENT_PROVIDED = "There's no content in the request!";
    public static final String MSG_NO_IMAGE_URL_PROVIDED = "There's no image url in the request!";
    public static final String MSG_NO_CATEGORY_ID_PROVIDED = "There's no Category Id in the request!";
    public static final String MSG_NO_USER_ID_PROVIDED = "There's no User id in the request!";
    public static final String MSG_POST_DELETED_SUCCESSFULLY = "Requested Post deleted Successfully!";
    public static final String MSG_POST_FOUND_SUCCESSFULLY = "Requested Post found Successfully!";
    public static final String MSG_POST_NOT_AVAILABLE = "Requested Post not available!";
    public static final String MSG_POST_UPDATED_SUCCESSFULLY = "Requested Post updated Successfully!";
    public static final String MSG_POST_SAVED_SUCCESSFULLY = "Requested Post saved Successfully!";
    public static final String MSG_POST_AVAILABLE = "Requested Post is available already!";
    public static final String MSG_NO_CATEGORY_AVAILABLE = "Requested Post's Category is not available!";
    public static final String MSG_NO_USER_AVAILABLE = "Requested Post's User is not available!";
    public static final String MSG_POST_FOUND_SUCCESSFULLY_AGAINST_CATEGORY = "Requested Posts found Successfully against the provided Category!";
    public static final String MSG_POST_FOUND_SUCCESSFULLY_AGAINST_USER = "Requested Posts found Successfully against the provided User!";

    // [COMMENT MESSAGES]
    public static final String MSG_COMMENT_FOUND_SUCCESSFULLY = "Requested Comment found Successfully!";
    public static final String MSG_COMMENT_NOT_AVAILABLE = "Requested Comment not available!";
    public static final String MSG_COMMENT_FOUND_SUCCESSFULLY_AGAINST_POST = "Requested Comments found Successfully against the provided Post!";
    public static final String MSG_COMMENT_FOUND_SUCCESSFULLY_AGAINST_USER = "Requested Comments found Successfully against the provided User!";
    public static final String MSG_NO_POST_ID_PROVIDED = "There's no Post Id in the request!";
    public static final String MSG_COMMENT_SAVED_SUCCESSFULLY = "Requested Commented saved Successfully!";
    public static final String MSG_COMMENT_DELETED_SUCCESSFULLY = "Requested Comment deleted Successfully!";
    public static final String MSG_COMMENT_UPDATED_SUCCESSFULLY = "Requested Comment updated Successfully!";

}