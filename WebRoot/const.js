
// GetNextItem() ItemCode
var ITEM_CODE={
	SELECTED:10,
	CHILD:11,
    FIRSTVISIBLE : 12,
    NEXT:13,
    NEXTVISIBLE:14,
    PARENT:15,
    PREVIOUS:16,
    PREVIOUSVISIBLE:17,
    ROOT:18
};

var VK_ENTER=13;

var WorldPointType = {
	WPT_TERRAIN                             : 0,
    WPT_MODEL                               : 1,
    WPT_LABEL                               : 2,
    WPT_PRIMITIVE                           : 4,
    WPT_ANIM                                : 8,
    WPT_BUILDING                            : 16,
    WPT_SKY                                 : 32,
    WPT_ACCURATE_CPT                        : 64,
    WPT_BBOX_CPT                            : 128,
    WPT_VIDEO                               : 256,
    WPT_UNDERGROUND                         : 512,
    WPT_SCREEN_OVERLAY                      : 1024,
    WPT_SCREEN_CONTROL                      : 2048,
    WPT_SCREEN_COVERED                      : 4096,
    WPT_3DML                                : 8192,
    WPT_DEFAULT                             : -1
};

var ObjectType={
	OT_UNDEFINED : 0,
    OT_POLYLINE : 1,
    OT_POLYGON : 2,
    OT_RECTANGLE : 3,
    OT_REGULAR_POLYGON : 4,
    OT_CIRCLE : 5,
    OT_3D_POLYGON : 6,
    OT_BUILDING : 7,
    OT_BOX : 8,
    OT_PYRAMID : 9,
    OT_CYLINDER : 10,
    OT_CONE : 11,
    OT_ELLIPSE : 12,
    OT_ARC : 13,
    OT_ARROW : 14,
    OT_3D_ARROW : 15,
    OT_SPHERE : 16,
    OT_MODEL : 17,
    OT_LABEL : 18,
    OT_LOCATION : 19,
    OT_TREE_HOTLINK : 20,
    OT_ROUTE : 21,
    OT_MESSAGE : 22,
    OT_DYNAMIC : 23,
    OT_IMAGE_LABEL : 24,
    OT_THREAT_DOME : 25,
    OT_IMAGERY_LAYER : 26,
    OT_TERRAIN_VIDEO : 27,
    OT_POINT_CLOUD : 28,
    OT_ELEVATION_LAYER : 29,
    OT_TERRAIN_MODIFIER : 30,
    OT_TERRAIN_HOLE : 31,
    OT_POPUP_MESSAGE : 32,
    OT_FEATURE : 33,
    OT_PRESENTATION : 34,
    OT_ANALYSIS_LOS : 35,
    OT_FEATURE_LAYER : 36,
    OT_FEATURE_GROUP : 37,
    OT_3D_MESH_LAYER : 38,
    OT_3D_MESH_FEATURE_LAYER : 39,
    OT_KML_LAYER : 40,
    OT_3D_VIEWSHED : 41,
    OT_CONTOUR_MAP : 42,
    OT_SLOPE_MAP : 43
};


// search type
var SearchType={
	SEARCH:1,
	ARMY:2,
	CHILDBEARING_WOMAN:3,
	RETIRED:4,
	SPECIAL_GROUP:5
};
