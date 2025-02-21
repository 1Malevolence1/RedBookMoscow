package com.example.admin_panel_service.config.uri;



public final class PathUriController {

    // Запросы для Entry
    public static String GET_LIST_RED_BOK_ENTRY_DATA_BASE = "/api/server-part/entry/all";
    public static String GET_RED_BOOL_ENTRY_DATA_BASE = "/api/server-part/entry/%d";
    public static String DEL_RED_BOOL_ENTRY_DATA_BASE = "/api/server-part/entry/%d";
    public static String POST_RED_BOOL_ENTRY_DATA_BASE = "/api/server-part/entry";
    public static String PUT_RED_BOOL_ENTRY_DATA_BASE = "/api/server-part/entry/%d";

    // Запросы для View

    public static String GET_LIST_VIEW_DATA_BASE = "/api/server-part/view";
    public static String DEL_VIEW_DATA_BASE = "/api/server-part/view/delete/%d";
    public static String POST_VIEW_DATA_BASE = "/api/server-part/view";
}
