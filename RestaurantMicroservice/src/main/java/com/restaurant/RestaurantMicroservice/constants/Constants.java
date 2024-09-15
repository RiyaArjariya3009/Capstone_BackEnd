package com.restaurant.RestaurantMicroservice.constants;

/**
 * This class holds constant messages used across the application.
 * These constants are primarily used for exception handling and
 * successful operation messages.
 */
public class Constants {

    /** Error message when a category is not found in the system. */
    public static final String CATEGORY_NOT_FOUND = "Category not found";

    /** Error message when a menu item is not found in the system. */
    public static final String MENUITEM_NOT_FOUND = "MenuItem not found";

    /** Error message when a restaurant is not found in the system. */
    public static final String RESTAURANT_NOT_FOUND = "Restaurant not found";

    /** Error message when a category already exists with the same name. */
    public static final String CATEGORY_ALREADY_EXIST = "Category already exists with this name";

    /** Message when a menu item is available. */
    public static final String MENU_ITEM_AVAILABLE = "Menu Item is Available";

    /** Message when a menu item is not available. */
    public static final String MENU_ITEM_NOTAVAILABLE = "Menu Item is not Available";

    /** Message indicating that the restaurant is currently open. */
    public static final String RESTAURANT_IS_NOW_OPEN = "Restaurant is now open";

    /** Message indicating that the restaurant is currently closed. */
    public static final String RESTAURANT_IS_NOW_CLOSED = "Restaurant is now closed";

    /** Error message when unable to fetch all restaurants from the system. */
    public static final String UNABLE_TO_FETCH_ALL_RESTAURANT = "Unable to fetch all restaurants";

    /** Message indicating that an entity has been successfully deleted. */
    public static final String DELETED_SUCCESSFULLY = "Deleted Successfully";

    /** Message indicating that an entity has been successfully added. */
    public static final String ADDED_SUCCESSFULLY = "Added Successfully";

    /** Message indicating that an entity has been successfully updated. */
    public static final String UPDATED_SUCCESSFULLY = "Updated Successfully";

    /** Error message when a menu item with a specific ID is not found. */
    public static final String MENU_ITEM_NOT_FOUND = "Menu Item with this ID was not found";

    /** Message indicating that a menu item has been successfully updated. */
    public static final String MENU_ITEM_UPDATED = "Menu Item Updated Successfully";

    /** Message indicating that a restaurant has been successfully created. */
    public static final String RESTAURANT_CREATED_SUCCESSFULLY = "Restaurant created successfully";

    /** Error message when a user with the provided ID is not found. */
    public static final String USER_NOT_FOUND = "User with this ID was not found";

    /** Error message indicating that a user is not authorized for an operation. */
    public static final String NOT_AUTHORIZED = "User with this ID is not Authorized";

    /** Error message when an invalid file type is uploaded. */
    public static final String INVALID_FILE = "Invalid file type: Only JPEG, JPG, and PNG files are allowed.";

    /** Error message when file processing fails, such as during image handling. */
    public static final String PROCESSING_FAILED = "Error occurred while processing the image file";

    /** Error message when a menu item with the same name already exists for the given restaurant. */
    public static final String MENU_ITEM_ALREADY_EXIST = "Menu item with this name already exists for this restaurant.";

    /** Message indicating that a menu item has been successfully added. */
    public static final String MENU_ITEM_ADDED = "Menu Item has been added successfully";

    /** Error message when a restaurant with the same name already exists. */
    public static final String RESTAURANT_NAME_ALREADY_EXISTS = "A restaurant with the given name already exists.";

    /** Error message when an uploaded file exceeds the 10 MB size limit. */
    public static final String FILE_TOO_LARGE = "File size exceeds the 10 MB limit";
}
