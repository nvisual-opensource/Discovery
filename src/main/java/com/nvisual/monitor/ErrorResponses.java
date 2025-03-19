package com.nvisual.monitor;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by DanielTog on Jul, 2021
 */
@Component
public class ErrorResponses {

//现在不用这块区分多语言了，有了多语言方法
    //挨个替换message的引用


    public JSONObject ENTITY_NOT_FOUND() {
//        String message = ResourceBundleResolver.Language().get("entity_not_found");
        String message = ("entity not found");
        JSONObject response = new JSONObject();
        response.put("code", 800001);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ENTITY_HAVE_CHILD_CANT_DELETE() {
        String message = ("entity has instances");
        Map response = new LinkedHashMap();
        response.put("code", 800002);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map ENTITY_HAVE_CHILD() {

        String message = ("has sub nodes ,not allowed to convert scene type");
        Map response = new LinkedHashMap();
        response.put("code", 800003);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject ENTITY_NOT_FOUND_JSON() {
        String message = ("entity not found");
        JSONObject response = new JSONObject();
        response.put("code", 800004);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public JSONObject ENTITY_NOT_FOUND_JSON(String data) {
        String message = ("entity not found");
        JSONObject response = new JSONObject();
        response.put("code", 800004);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    public Map ENTITY_NOT_FOUND(String data) {
        String message = ("entity not found");
        Map response = new LinkedHashMap();
        response.put("code", 800005);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    public Map SAVE_ENTITY_ERROR() {

        String message = ("save entity error");
        Map response = new LinkedHashMap();
        response.put("code", 800006);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject SAVE_ENTITY_ERROR_JSON() {

        String message = ("save entity error");
        JSONObject response = new JSONObject();
        response.put("code", 800007);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map SAVE_ENTITY_ERROR(Object object) {


        String message = ("save entity error");
        Map response = new LinkedHashMap();
        response.put("code", 800008);
        response.put("message", message);
        response.put("data", object);
        return response;
    }

    public Map ENTITY_IS_NO_SLOT() {

        String message = ("entity is no slot");
        Map response = new LinkedHashMap();
        response.put("code", 800009);
        response.put("message", message);
        response.put("data", null);
        return response;
    }


    public Map INCORRECT_SLOT_MAPPING() {

        String message = ("incorrect slot mapping");
        Map response = new LinkedHashMap();
        response.put("code", 800010);
        response.put("message", message);
        response.put("data", null);
        return response;
    }


    public Map NO_SUBLINKS_FOUND_IN_LINK() {

        String message = ("no sublinks found in link");
        Map response = new LinkedHashMap();
        response.put("code", 800011);
        response.put("message", message);
        response.put("data", null);
        return response;

    }


    public Map NODE_IS_NOT_ALIAS() {

        String message = ("node is not alias");
        Map response = new LinkedHashMap();
        response.put("code", 800012);
        response.put("message", message);
        response.put("data", null);
        return response;

    }

    public Map CAN_NOT_CREATE_PORT_ALIAS() {

        String message = ("can not create port alias");
        Map response = new LinkedHashMap();
        response.put("code", 800013);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ENTITIES_ALREADY_EXIST() {

        String message = ("entities already exist");
        Map response = new LinkedHashMap();
        response.put("code", 800014);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANT_CREATE_BUNDLE_OUT_OF_ORDER() {

        String message = ("cant create bundle out of order");
        Map response = new LinkedHashMap();
        response.put("code", 800015);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANT_CREATE_INNER_DEVICE_CONNECTION() {

        String message = ("cant create inner device connection");
        Map response = new LinkedHashMap();
        response.put("code", 800016);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ALIAS_REPEATED_IN_THE_SAME_DIAGRAM() {

        String message = ("alias repeated in the same diagram");
        Map response = new LinkedHashMap();
        response.put("code", 800017);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map THERE_IS_IS_MORE_THAN_ONE_ALIAS() {

        String message = ("there is is more than one alias");
        Map response = new LinkedHashMap();
        response.put("code", 800018);
        response.put("message", message);
        response.put("data", null);
        return response;

    }

    public JSONObject CANNOT_HAVE_EMPTY_VALUE() {

        String message = ("cannot have empty value");
        JSONObject response = new JSONObject();
        response.put("code", 800019);
        response.put("message", message);
        response.put("data", null);
        return response;

    }

    public Map TARGET_DIAGRAM_IS_A_CHILD_NODE() {

        String message = ("target diagram is a child node");
        Map response = new LinkedHashMap();
        response.put("code", 800020);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map NODE_HAS_LINKS_ATTACHED() {

        String message = ("node has links attached");
        Map response = new LinkedHashMap();
        response.put("code", 800021);
        response.put("message", message);
        response.put("data", null);
        return response;
    }


    public Map CAN_NOT_CREATE_LINK_BETWEEN_NODES() {

        String message = ("can not create link between nodes");
        Map response = new LinkedHashMap();
        response.put("code", 800022);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map CANNOT_SET_NODE_PERMISSION_HIGHER() {

        String message = ("cannot set node permission higher");
        Map response = new LinkedHashMap();
        response.put("code", 800023);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map CAN_NOT_CREATE_LINK_BETWEEN_NODES_BY_PORTTYPE() {

        String message = ("can not create link between nodes by porttype");
        Map response = new LinkedHashMap();
        response.put("code", 800024);
        response.put("message", message);
        response.put("data", null);
        return response;
    }


    public Map CAN_NOT_CREATE_LINK_BETWEEN_NODES_BY_PORTTYPE2(String a,String b) {

        String message = ("can not create link between nodes by porttype");
        Map response = new LinkedHashMap();
        response.put("code", 800025);
        response.put("message", message+" [From:"+a+" To:"+b+"]");
        response.put("data", null);
        return response;
    }



    public Map EMPTY_ROUTES_ERROR() {

        String message = ("empty routes error");
        Map response = new LinkedHashMap();
        response.put("code", 800026);
        response.put("message", message);
        response.put("data", null);
        return response;

    }


    public Map NODE_HAS_CHILD_CONNECTIONS_TO_OTHER_DIAGRAMS() {
        String message = ("node has child connections to other diagrams");
        Map response = new LinkedHashMap();
        response.put("code", 800027);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map TARGET_DIAGRAM_AND_TARGET_NODE_CAN_NOT_BE_EQUAL() {
        String message = ("target diagram and target node can not be equal");
        Map response = new LinkedHashMap();
        response.put("code", 800028);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_BUNDLE_A_SUBLINK_PARENT_LINK_TO_THE_SUBLINK() {
        String message = ("cannot bundle a sublink parent link to the sublink");
        Map response = new LinkedHashMap();
        response.put("code", 800029);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_BUNDLE_A_LINK_TO_ITSELF() {
        String message = ("cannot bundle a link to itself");
        Map response = new LinkedHashMap();
        response.put("code", 800030);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ORIGINAL_NODE_TYPE_AND_TARGET_NODE_TYPE_ARE_EQUAL() {
        String message = ("original node type and target node type are equal");
        Map response = new LinkedHashMap();
        response.put("code", 800031);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map DIFFERENT_TYPE_GROUPS() {
        String message = ("different type groups");
        Map response = new LinkedHashMap();
        response.put("code", 800032);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map INCONSISTENT_NUMBER_OF_PORTS() {
        String message = ("inconsistent number of ports");
        Map response = new LinkedHashMap();
        response.put("code", 800033);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map USER_NOT_AUTHORIZED_TO_PERFORM_ACTION(Object data) {
        String message = ("user not authorized to perform action");
        Map response = new LinkedHashMap();
        response.put("code", 800034);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject USER_NOT_AUTHORIZED_TO_PERFORM_ACTION() {
        String message = ("user not authorized to perform action");
        JSONObject response = new JSONObject();
        response.put("code", 800034);
        response.put("message", message);
        response.put("data", null);
        return response;
    }


    public Map MULTI_STATUS(Object data) {
        String message = ("multi status");
        Map response = new LinkedHashMap();
        response.put("code", 800035);
        response.put("message", message);
        response.put("data", null);
        return response;
    }


    public Map DIAGRAM_IS_NOT_A_MAP() {
        String message = ("diagram is not a map");
        Map response = new LinkedHashMap();
        response.put("code", 800036);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map TYPE_GROUP_NODE() {
        String message = ("type group node");
        Map response = new LinkedHashMap();
        response.put("code", 800037);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map PROPERTY_VALUE_NOT_UNIQUE() {
        String message = ("property value not unique");
        Map response = new LinkedHashMap();
        response.put("code", 800038);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map TARGET_AND_DESTINATION_ARE_DIRECTLY_CONNECTED() {
        String message = ("target and destination are directly connected");
        Map response = new LinkedHashMap();
        response.put("code", 800039);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CAN_NOT_CUT_PASTE_A_NODE_WITH_LINKS_ON_A_MAP() {
        String message = ("can not cut paste a node with links on a map");
        Map response = new LinkedHashMap();
        response.put("code", 800040);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CAN_NOT_USE_THIS_API_TO_CREATE_A_CARD() {
        String message = ("can not use this api to create a card");
        Map response = new LinkedHashMap();
        response.put("code", 800041);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map MODEL_NOT_FOUND() {
        String message = ("model not found");
        Map response = new LinkedHashMap();
        response.put("code", 800042);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map MODEL_ALREADY_EXISTS() {
        String message = ("model already exists");
        Map response = new LinkedHashMap();
        response.put("code", 800043);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map DEVICE_VENDOR_NOT_FOUND() {
        String message = ("device vendor not found");
        Map response = new LinkedHashMap();
        response.put("code", 800044);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map USER_ALREADY_EXISTS() {
        String message = ("用户已存在");
        Map response = new LinkedHashMap();
        response.put("code", 800045);
        response.put("message", message);
        response.put("data", null);
        return response;
    }


    public JSONObject USER_ALREADY_EXISTS_JSON() {
        String message = ("user already exists in saas cloud");
        JSONObject response = new JSONObject();
        response.put("code", 800046);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ORGANIZATION_ALREADY_EXISTS() {
        String message = ("当前公司或组织已有注册账号");
        Map response = new LinkedHashMap();
        response.put("code", 800047);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ERROR_SENDING_SMS(Object data) {
        String message = ("发送验证码失败");
        Map response = new LinkedHashMap();
        response.put("code", 800048);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    public Map PHONE_NOT_FOUND() {
        String message = ("phone not found");
        Map response = new LinkedHashMap();
        response.put("code", 800049);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map PHONE_NOT_FOUND_OR_WRONG_CODE() {
        String message = ("找不到手机号或验证码错误");
        Map response = new LinkedHashMap();
        response.put("code", 800050);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ERROR_INSTALLING_NVISUAL() {
        String message = ("error installing nvisual");
        Map response = new LinkedHashMap();
        response.put("code", 800051);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map PHONE_ALREADY_EXISTS() {
        String message = ("当前电话已有注册账号");
        Map response = new LinkedHashMap();
        response.put("code", 800052);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map INVALID_CODE() {
        String message = ("invalid code");
        Map response = new LinkedHashMap();
        response.put("code", 800053);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject INVALID_CREDENTIALS() {
        String message = ("user name or password input error");
        JSONObject response = new JSONObject();
        response.put("code", 800054);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject INVALID_CREDENTIALS(int error) {
        String message = ("Wrong username or password, you can try "+(10-error)+" more times");
        JSONObject response = new JSONObject();
        response.put("code", 800054);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public JSONObject INVALID_CREDENTIALS(String error) {
        String message = ("Your account has been locked due to 5 consecutive incorrect password entries. Please contact customer service or an administrator");
        JSONObject response = new JSONObject();
        response.put("code", 800054);
        response.put("message", message);
        response.put("data", null);
        return response;
    }


    public Map EMAIL_ALREADY_EXISTS() {
        String message = ("email already exists");
        Map response = new LinkedHashMap();
        response.put("code", 800055);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject EMAIL_ALREADY_EXISTS_JSON() {
        String message = ("email already exists");
        JSONObject response = new JSONObject();
        response.put("code", 800056);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map USER_IS_MASTER_TENANT() {
        String message = ("user is master tenant");
        Map response = new LinkedHashMap();
        response.put("code", 800057);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ERROR_SAVING_FILE() {
        String message = ("error saving file");
        Map response = new LinkedHashMap();
        response.put("code", 800058);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ERROR(Object ob) {
        String message = ("error");
        Map response = new LinkedHashMap();
        response.put("code", 800059);
        response.put("message", message + " " + ob);
        response.put("data", null);
        return response;
    }

    public JSONObject ERROR_JSON(Object ob) {
        String message = ("error");
        JSONObject response = new JSONObject();
        response.put("code", 800060);
        response.put("message", message + " " + ob);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_HAVE_SPECIAL_CHARACTERS() {
        String message = ("cannot have special characters");
        JSONObject response = new JSONObject();
        response.put("code", 800061);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map USERNAME_CANNOT_CONTAIN_SPECIAL_CHARACTERS() {
        String message = ("用户名不能包含特殊字符");
        JSONObject response = new JSONObject();
        response.put("code", 800062);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ORGANIZATION_CANNOT_CONTAIN_SPECIAL_CHARACTERS() {
        String message = ("公司或组织名不能包含特殊字符");
        JSONObject response = new JSONObject();
        response.put("code", 800063);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map SLOT_ALREADY_OCCUPIED_BY_CARD() {
        String message = ("slot already occupied by card");
        Map response = new HashMap();
        response.put("code", 800064);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_DELETE_NODE_CLASS_IS_IN_USE() {
        String message = ("cannot delete node class is in use");
        Map response = new HashMap();
        response.put("code", 800065);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_DELETE_LINK_CATEGORY_IS_IN_USE() {
        String message = ("cannot delete link category is in use");
        Map response = new HashMap();
        response.put("code", 800066);
        response.put("message", message);
        response.put("data", null);
        return response;
    }


    public Map CODE_EXPIRED() {
        String message = ("验证码已过期，请重新获取验证码");
        Map response = new HashMap();
        response.put("code", 800067);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ERROR_CREATING_NEW_SAAS_USER() {
        String message = ("error creating new saas user");
        Map response = new HashMap();
        response.put("code", 800068);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map TOO_MANY_ATTEMPTS_TRY_LATER() {
        String message = ("操作太频繁，请稍后再试");
        Map response = new HashMap();
        response.put("code", 800069);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_DELETE_HAS_NODES_SET_FOR_NOT_DELETING(Object object) {
        String message = ("cannot delete has nodes set for not deleting");
        Map response = new HashMap();
        response.put("code", 800070);
        response.put("message", message + " " + object);
        response.put("data", object);
        return response;
    }

    public Map CANNOT_DELETE_IT_HAS_CHILD_NODES_OR_RELATION_TO_ANOTHER_OBJECT(Object object) {
        String message = ("cannot delete it has child nodes or relation to another object");
        Map response = new HashMap();
        response.put("code", 800071);
        response.put("message", message + " " + object);
        response.put("data", object);
        return response;
    }

    public Map CANNOT_DELETE_IT_HAS_RELATIONS(Object object) {
        String message = ("cannot delete it has relations");
        Map response = new HashMap();
        response.put("code", 800072);
        response.put("message", message + " " + object);
        response.put("data", object);
        return response;
    }

    public Map ERROR_IMPORTING_MODEL(Object object) {
        String message = ("error importing model");
        Map response = new HashMap();
        response.put("code", 800073);
        response.put("message", message + " " + object);
        response.put("data", object);
        return response;
    }

    public Map INVALID_FILE_FOR_LINK_MODEL(Object object) {
        String message = ("invalid file for link model");
        Map response = new HashMap();
        response.put("code", 800074);
        response.put("message", message + " " + object);
        response.put("data", object);
        return response;
    }

    public Map ERROR_EXECUTING_SCRIPT(Object object) {
        String message = ("error executing script");
        Map response = new HashMap();
        response.put("code", 800075);
        response.put("message", message + " " + object);
        response.put("data", object);
        return response;
    }

    public Map LANGUAGE_CODE_NOT_FOUND(){
        String message = ("language code not found");
        Map response = new HashMap();
        response.put("code", 800076);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANT_CLOSE_MVT() {
        String message = ("cant close mvt");
        Map response = new HashMap();
        response.put("code", 800077);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map NODEID_ENTITY_NOT_FOUND() {
        String message = ("nodeid entity not found");
        Map response = new HashMap();
        response.put("code", 800078);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map IP_ENTITY_NOT_FOUND() {
        String message = ("ip entity not found");
        Map response = new HashMap();
        response.put("code", 800079);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map NODEPROPERTY_NOT_FOUND() {
        String message = ("nodeproperty not found");
        Map response = new HashMap();
        response.put("code", 800080);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map EXPORT_MODEL_ERROR(String localizedMessage) {
        String message = ("export model error");
        Map response = new HashMap();
        response.put("code", 800081);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map INVALID_REFRESH_TOKEN() {
        String message = ("user name or password input error");
        Map response = new HashMap();
        response.put("code", 800082);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map REFRESH_TOKEN_NOT_FOUND() {
        String message = ("refresh token not found");
        Map response = new HashMap();
        response.put("code", 800083);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject REFRESH_TOKEN_EXPIRED() {
        String message = ("refresh token expired");
        JSONObject response = new JSONObject();
        response.put("code", 800084);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject USER_TEMPORARILY_BLOCKED() {
        String message = ("user temporarily blocked");
        JSONObject response = new JSONObject();
        response.put("code", 800085);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map USER_LOGGED_IN_FROM_ANOTHER_COMPUTER() {
        String message = ("user logged in from another computer");
        JSONObject response = new JSONObject();
        response.put("code", 800086);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject USER_CANNOT_LOG_IN() {
        String message = ("user cannot log in");
        JSONObject response = new JSONObject();
        response.put("code", 800087);
        response.put("message", message);
        response.put("data", null);
        return response;
    }


    public JSONObject USER_DISABLED() {
//        String message = ("user disabled");
        String message = ("Your account has been locked due to 5 consecutive incorrect password entries. Please contact customer service or an administrator");
        JSONObject response = new JSONObject();
        response.put("code", 800088);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject LICENSE_TIMELIMIT() {
        String message = ("Your account has expired. Please contact customer service or administrator");
        JSONObject response = new JSONObject();
        response.put("code", 800089);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject LICENSE_NOLICENSE() {
        String message = ("license nolicense");
        JSONObject response = new JSONObject();
        response.put("code", 800090);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject LICENSE_MACERROR() {
        String message = ("license macerror");
        JSONObject response = new JSONObject();
        response.put("code", 800091);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map SPACES_NOT_ALLOWED_IN_USERNAME() {
        String message = ("spaces not allowed in username");
        JSONObject response = new JSONObject();
        response.put("code", 800092);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map FOLDER_NAME_ALREADY_EXISTS() {
        String message = ("folder name already exists");
        JSONObject response = new JSONObject();
        response.put("code", 800093);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map INVALID_ENTITY() {
        String message = ("invalid entity");
        JSONObject response = new JSONObject();
        response.put("code", 800094);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ERROR_CREATING_FOLDER(String localizedMessage) {
        String message = ("error creating folder");
        JSONObject response = new JSONObject();
        response.put("code", 800095);
        response.put("message", message + " " + localizedMessage);
        response.put("data", null);
        return response;
    }

    public Map CANT_DELETE_TOP_FOLDER() {
        String message = ("cant delete top folder");
        JSONObject response = new JSONObject();
        response.put("code", 800096);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject UPLOAD_FILE_FAILED(Object e) {
        String message = ("upload file failed");
        JSONObject response = new JSONObject();
        response.put("code", 800097);
        response.put("message", message + " " + e.getClass());
        response.put("data", null);
        return response;
    }

    public JSONObject UPLOAD_FILE_OUT_OF_LINE() {
        String message = ("upload file out of line");
        JSONObject response = new JSONObject();
        response.put("code", 800098);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANT_EDIT_TOP_FOLDER() {
        String message = ("cant edit top folder");
        JSONObject response = new JSONObject();
        response.put("code", 800099);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map TEXT_TOPOLOGY_IS_NOT_SUPPORTED() {
        String message = ("text topology is not supported");
        JSONObject response = new JSONObject();
        response.put("code", 800100);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map TOPOLOGY_NOT_SUPPORTED() {
        String message = ("topology not supported");
        JSONObject response = new JSONObject();
        response.put("code", 800101);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ENCLOSURE_CREATION_NOT_ALLOWED() {
        String message = ("enclosure creation not allowed");
        JSONObject response = new JSONObject();
        response.put("code", 800102);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map NUMBER_OF_NODES_EXCEEDS_AUTHORIZATION_LIMIT() {
        String message = ("number of nodes exceeds authorization limit");
        JSONObject response = new JSONObject();
        response.put("code", 800103);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANT_BUNDLE_BECAUSE_LINK_TYPE_ERROR() {
        String message = ("cant bundle because link type error");
        JSONObject response = new JSONObject();
        response.put("code", 800104);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map HT_LINK_TYPE_CANT_BUNDLE() {
        String message = ("ht link type cant bundle");
        JSONObject response = new JSONObject();
        response.put("code", 800105);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject AlREADY_WORK_ORDER_TASK() {
        String message = ("already work order task");
        JSONObject response = new JSONObject();
        response.put("code", 800106);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map LINK_TYPE_NOT_EXISTS() {
        String message = ("link type not exists");
        JSONObject response = new JSONObject();
        response.put("code", 800107);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ERROR_MIGRATING_ENTITY(String error) {
        String message = ("error migrating entity");
        JSONObject response = new JSONObject();
        response.put("code", 800108);
        response.put("message", message + " / " + error);
        response.put("data", null);
        return response;
    }

    public String FILE_DELETION_FAILED() {
        String message = ("file deletion failed");
        return message;
    }

    public String THERE_WAS_AN_ERROR() {
        String message = ("there was an error");
        return message;
    }

    public Map FILE_EXTENSION_NOT_ALLOWED(String extension) {
        String message = ("file extension not allowed");
        JSONObject response = new JSONObject();
        response.put("code", 800109);
        response.put("message", message + " " + extension);
        response.put("data", null);
        return response;
    }

    public Map ERROR_DELETING_ALL_ENTITY_DATA(String error) {
        String message = ("error deleting all entity data");
        JSONObject response = new JSONObject();
        response.put("code", 800110);
        response.put("message", message + " / " + error);
        response.put("data", null);
        return response;
    }

    public Map NOT_ALLOWED_TO_MIGRATE() {
        String message = ("not allowed to migrate");
        JSONObject response = new JSONObject();
        response.put("code", 800111);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map SYNC_TARGET_NOT_FOUND() {
        String message = ("sync target not found");
        JSONObject response = new JSONObject();
        response.put("code", 800112);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map NOT_ALLOWED() {
        String message = ("not allowed");
        JSONObject response = new JSONObject();
        response.put("code", 800113);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CABLE_DOES_NOT_MATCH_INTERFACE(Object data) {
        String message = ("cable does not match interface");
        JSONObject response = new JSONObject();
        response.put("code", 800114);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    public Map EQUAL_RELATION_OBJECTS() {
        String message = ("equal relation objects");
        JSONObject response = new JSONObject();
        response.put("code", 800115);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map EMPTY_RELATION_TYPE() {
        String message = ("empty relation type");
        JSONObject response = new JSONObject();
        response.put("code", 800116);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map RELATION_ALREADY_EXISTS() {
        String message = ("relation already exists");
        JSONObject response = new JSONObject();
        response.put("code", 800117);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map REVERSE_RELATION_ALREADY_EXISTS() {
        String message = ("reverse relation already exists");
        JSONObject response = new JSONObject();
        response.put("code", 800118);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map IMPORT_NOT_ALLOWED_NOT_EMPTY_DIAGRAM() {
        String message = ("import not allowed not empty diagram");
        JSONObject response = new JSONObject();
        response.put("code", 800119);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map DISK_RETENTION_MODEL_IS_NOT_EXIST() {
        String message = ("disk retention model is not exist");
        JSONObject response = new JSONObject();
        response.put("code", 800120);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_OPERATIONS_CUTANDCONNECT() {
        String message = ("cannot operations cutandconnect");
        JSONObject response = new JSONObject();
        response.put("code", 800121);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CREATE_NODE_FAIL() {
        String message = ("create node fail");
        JSONObject response = new JSONObject();
        response.put("code", 800122);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map NO_CUTTING_EQUIPMENT_AVAILABLE() {
        String message = ("no cutting equipment available");
        JSONObject response = new JSONObject();
        response.put("code", 800123);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map NO_AVAILABLE_PATH() {
        String message = ("no available path");
        JSONObject response = new JSONObject();
        response.put("code", 800124);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map NO_NEAREST_HOLE() {
        String message = ("no nearest hole");
        JSONObject response = new JSONObject();
        response.put("code", 800125);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map FAILED_ADD_IMPORT_NODE() {
        String message = ("failed add import node");
        JSONObject response = new JSONObject();
        response.put("code", 800126);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map FAILED_ADD_IMPORT_LINK() {
        String message = ("failed add import link");
        JSONObject response = new JSONObject();
        response.put("code", 800127);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map READLINE_FILE_ERROR() {
        String message = ("readline file error");
        JSONObject response = new JSONObject();
        response.put("code", 800128);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_MIGRATE_AT_THIS_TYPE_AT_THIS_LEVEL() {
        String message = ("cannot migrate at this type at this level");
        JSONObject response = new JSONObject();
        response.put("code", 800129);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map DATA_INSERTION_FAILED() {
        String message = ("data insertion failed");
        JSONObject response = new JSONObject();
        response.put("code", 800130);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map USER_CAN_INPUT_ADMIN_PASSWORD_TO_DELETE() {
        String message = ("user can input admin password to delete");
        JSONObject response = new JSONObject();
        response.put("code", 800131);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map USER_CAN_INPUT_ADMIN_PASSWORD_TO_DELETE(String s) {
        String message = (s+",user can input admin password to delete");
        JSONObject response = new JSONObject();
        response.put("code", 800131);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map AUTHENTICATING_ADMIN_FAILED() {
        String message = ("authenticating admin failed");
        JSONObject response = new JSONObject();
        response.put("code", 800132);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map NO_FIBER_CABLE_ALARM_AVAILABLE() {
        String message = ("no fiber cable alarm available");
        JSONObject response = new JSONObject();
        response.put("code", 800133);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map PARAMETERS_NOT_SPECIFICATIONS(String endWithMessage) {
        String message = ("parameters not specifications");
        JSONObject response = new JSONObject();
        response.put("code", 800134);
        if (StringUtils.isNotBlank(endWithMessage)) {
            response.put("message", message + ": " + endWithMessage);
        } else {
            response.put("message", message);
        }
        response.put("data", null);
        return response;
    }

    public Map LINK_ROUTE_NOT_FOUND() {
        String message = ("link route not found");
        JSONObject response = new JSONObject();
        response.put("code", 800135);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map LINK_ROUTE_NOT_FOUND(int i) {
        String message = ("link route not found");
        JSONObject response = new JSONObject();
        response.put("code", 800135);
        response.put("message", message);
        response.put("data", i);
        return response;
    }

    public Map HAVE_CHILDREN_NODE_FORMAT_BRUSHES_CANNOT_BE_USED(String name, Integer num) {
        String message = name + ("have children node format brushes cannot be used");
        JSONObject response = new JSONObject();
        response.put("code", 800136);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_DELETE_IT_HAS_CHILD_NODES(String s) {
        String message = ("cannot delete it has child nodes");
        JSONObject response = new JSONObject();
        response.put("code", 800137);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ENTITY_NOT_FOUND_RESOURCE(String s) {
        String message = ("entity not found resource");
        JSONObject response = new JSONObject();
        response.put("code", 800138);
        response.put("message", message);
        response.put("data", null);
        return response;
    }


    public Map CANT_DELETE_SYSTEM_RESERVED_MODEL() {
        String message = ("cant delete system reserved model");
        JSONObject response = new JSONObject();
        response.put("code", 800139);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANT_DELETE_SYSTEM_RESERVED_PROPERTY() {
        String message = ("cant delete system reserved property");
        JSONObject response = new JSONObject();
        response.put("code", 800140);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map EXCEESDS_SYSTEM_ROUTING_LENGTH() {
        String message = ("exceesds system routing length");
        JSONObject response = new JSONObject();
        response.put("code", 800141);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject DONT_SEND_WORK_ORDER_TASK() {
        String message = ("dont send work order task");
        JSONObject response = new JSONObject();
        response.put("code", 800142);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map USER_NOT_ALLOWED() {
        String message = ("user not allowed");
        JSONObject response = new JSONObject();
        response.put("code", 800143);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map REQUEST_BODY_ERROR() {
        String message = ("request body error");
        JSONObject response = new JSONObject();
        response.put("code", 800144);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map RESOURCE_CODE_ALREADY() {
        String message = ("resource code already");
        JSONObject response = new JSONObject();
        response.put("code", 800145);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map SUBLINK_NEED_CONNECT() {
        String message = ("sublink need connect");
        Map response = new LinkedHashMap();
        response.put("code", 800146);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_REPEAT_SAME_DOCUMENT() {
        String message = ("cannot repeat same document");
        JSONObject response = new JSONObject();
        response.put("code", 800147);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_BE_ARCHIVED_WORK_ORDER_TASK() {
        String message = ("cannot be archived work order task");
        JSONObject response = new JSONObject();
        response.put("code", 800148);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map FILE_NOT_PRESENT() {
        String message = ("file not present");
        JSONObject response = new JSONObject();
        response.put("code", 800149);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map NO_BACKGROUND_ASSIGNED_TO_MODEL() {
        String message = ("no background assigned to model");
        JSONObject response = new JSONObject();
        response.put("code", 800150);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map DIST_PATH_ERROR() {
        String message = ("dist path error");
        JSONObject response = new JSONObject();
        response.put("code", 800151);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map EMPTY_REQUEST() {
        String message = ("empty request");
        JSONObject response = new JSONObject();
        response.put("code", 800152);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ERROR_CREATING_TEMP_NODE() {
        String message = ("error creating temp node");
        JSONObject response = new JSONObject();
        response.put("code", 800153);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map MODEL_LIBRARY_PATH_ERROR(Object o) {
        String message = ("model library path error");
        JSONObject response = new JSONObject();
        response.put("code", 800154);
        response.put("message", message + " " + o);
        response.put("data", null);
        return response;
    }

    public Map CLOUD_FILES_PATH_ERROR(Object o) {
        String message = ("cloud files path error");
        JSONObject response = new JSONObject();
        response.put("code", 800155);
        response.put("message", message + " " + o);
        response.put("data", null);
        return response;
    }

    public Map INCOMPLETE_PARAMETER_TRANSMISSION_DATA(String dataName) {//传参数据不完整
        String message = ("incomplete parameter transmission data");
        JSONObject response = new JSONObject();
        response.put("code", 800156);
        response.put("message", message + ":" + dataName);
        response.put("data", null);
        return response;
    }

    public Map SEARCH_SET_TOO_SMALL() {
        String message = ("search set too small");
        Map response = new LinkedHashMap();
        response.put("code", 800157);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_CREATE_SHORTCUTS() {
        String message = ("cannot create shortcuts");
        Map response = new LinkedHashMap();
        response.put("code", 800158);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map PASSING_PARAMETERS_ERROR() {
        String message = ("passing parameters error");
        Map response = new LinkedHashMap();
        response.put("code", 800159);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map WRONG_ID_PREFIX(String prefix) {
        String message = ("wrong id prefix");
        Map response = new LinkedHashMap();
        response.put("code", 800160);
        response.put("message", message + ":" + prefix);
        response.put("data", prefix);
        return response;
    }

    public Map LINK_IDENTIFIER_ALREADY_DEFINED_IN_NVISUAL(String linkIdentifier) {
        String message = ("link identifier already defined in nvisual");
        Map response = new LinkedHashMap();
        response.put("code", 800161);
        response.put("message", message + " " + linkIdentifier);
        response.put("data", null);
        return response;
    }

    public Map LINK_IDENTIFIER_ALREADY_DEFINED_IN_NVISUAL_BUT_NO_NVISUAL_LINK_WAS_FOUND(String linkIdentifier) {
        String message = ("link identifier already defined in nvisual but no nvisual link was found");
        Map response = new LinkedHashMap();
        response.put("code", 800162);
        response.put("message", message + " " + linkIdentifier);
        response.put("data", linkIdentifier);
        return response;
    }

    public Map CABLE_ID_WRONG_LENGTH(int length) {
        String message = ("cable id wrong length");
        Map response = new LinkedHashMap();
        response.put("code", 800163);
        response.put("message", message + ":" + length);
        response.put("data", length);
        return response;
    }

    public Map CABLE_ID_WRONG_NOMENCLATURE(char charAt) {
        String message = ("cable id wrong nomenclature");
        Map response = new LinkedHashMap();
        response.put("code", 800164);
        response.put("message", message + ":" + charAt);
        response.put("data", charAt);
        return response;
    }

    public Map PORT_ALREADY_REGISTERED(Object object) {
        String message = ("port already registered");
        Map response = new LinkedHashMap();
        response.put("code", 800165);
        response.put("message", message + ":" + object);
        response.put("data", object);
        return response;
    }

    public Map LINK_ALREADY_EXISTS(Object object, Object portA, Object portB, Object linkIdentifier) {
        String message = ("link already exists");
        Map response = new LinkedHashMap();
        response.put("code", 800166);
        response.put("message", message + ": " + "port_a:" + portA + ", " + "port_b:" + portB + ", " + "link identifier:" + linkIdentifier);
        response.put("data", object);
        return response;
    }

    public Map PORT_TAKEN_ALREADY(String port, Long portId) {
        String message = ("port taken already");
        Map response = new LinkedHashMap();
        response.put("code", 800167);
        response.put("message", message + ":" + port + "=" + portId);
        response.put("data", null);
        return response;
    }

    public Map PORT_ALREADY_CONNECTED(Object data) {
        String message = ("port already connected");
        Map response = new LinkedHashMap();
        response.put("code", 800168);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    public Map CANNOT_MODIFY_THIS_ENDPOINT(Object object) {
        String message = ("cannot modify this endpoint");
        Map response = new LinkedHashMap();
        response.put("code", 800169);
        response.put("message", message + ":" + object);
        response.put("data", object);
        return response;
    }



    public Map PORT_ALREADY_REGISTERED_TO_THE_SELECTED_CABLE(Object object) {
        String message = ("port already registered to the selected cable");
        Map response = new LinkedHashMap();
        response.put("code", 800170);
        response.put("message", message + ":" + object);
        response.put("data", object);
        return response;
    }

    public Map PLEASE_SELECT_A_PORT() {
        String message = ("please select a port");
        Map response = new LinkedHashMap();
        response.put("code", 800171);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map NO_CONNECTION_FOUND_FOR_THE_SELECTED_CABLE() {
        String message = ("no connection found for the selected cable");
        Map response = new LinkedHashMap();
        response.put("code", 800172);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map PLEASE_CHECK_THE_LAYER_SETTINGS() {
        String message = ("please check the layer settings");
        Map response = new LinkedHashMap();
        response.put("code", 800173);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map THERE_ARE_SUB_FILES() {
        String message = ("there are sub files");
        Map response = new LinkedHashMap();
        response.put("code", 800174);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map THIS_USER_DOES_NOT_HAVE_PERMISSION() {
        String message = "this user does not have permission";
        Map response = new LinkedHashMap();
        response.put("code", 800175);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CANNOT_PERFORM_THIS_OPERATION() {
        String message = ("cannot perform this operation");
        JSONObject response = new JSONObject();
        response.put("code", 800176);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CHECK_AND_MODIFY_DEVICE_CONNECTIONS() {
        String message = ("check and modify device connections");
        JSONObject response = new JSONObject();
        response.put("code", 800177);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map FIELD_VALUES_DIFFERENT() {
        String message = ("field values different");
        JSONObject response = new JSONObject();
        response.put("code", 800178);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CABLE_RELATION_MAPPING_VALUES_DIFFERENT() {
        String message = ("cable relation mapping values different");
        JSONObject response = new JSONObject();
        response.put("code", 800179);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map CABLE_CORE_RELATION_MAPPING_VALUES_DIFFERENT() {
        String message = ("cable core relation mapping values different");
        JSONObject response = new JSONObject();
        response.put("code", 800180);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map ONLY_SELECT_DEVICE() {
        String message = ("only select device");
        JSONObject response = new JSONObject();
        response.put("code", 800181);
        response.put("message", message);
        response.put("data", null);
        return response;
    }



    public Map SELECT_THE_STARTING_OR_ENDING_PORT() {
        String message = ("select the starting or ending port");
        JSONObject response = new JSONObject();
        response.put("code", 800182);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map model_is_a_required_field() {
        String message = ("model is a required field");
        JSONObject response = new JSONObject();
        response.put("code", 800183);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map device_not_on_shelf(Map data) {
        String message = ("device not on shelf");
        JSONObject response = new JSONObject();
        response.put("code", 800184);
        response.put("message", message);
        response.put("data", data);
        return response;
    }
    public Map the_current_slot_of_this_device_is_already_occupied() {
        String message = ("the current slot of this device is already occupied");
        JSONObject response = new JSONObject();
        response.put("code", 800185);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map application_to_instance_failed() {
        String message = ("application to instance failed");
        JSONObject response = new JSONObject();
        response.put("code", 800186);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map node_model_not_found() {
        String message = ("node model not found");
        JSONObject response = new JSONObject();
        response.put("code", 800187);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map node_model_not_found(String name) {
        String message = ("node model not found");
        JSONObject response = new JSONObject();
        response.put("code", 800187);
        response.put("message", message+":"+name);
        response.put("data", null);
        return response;
    }
    public Map only_edit_end_point_in_same_diagram() {
        String message = ("only edit end point in same diagram");
        JSONObject response = new JSONObject();
        response.put("code", 800188);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map this_template_does_not_exist() {
        String message = ("this template does not exist");
        JSONObject response = new JSONObject();
        response.put("code", 800189);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map the_current_object_has_no_displayable_routes() {
        String message = ("the current object has no displayable routes");
        JSONObject response = new JSONObject();
        response.put("code", 800190);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map before_creating_a_link_you_need_to_select_the_next_hop_device_or_port() {
        String message = ("before creating a link you need to select the next hop device or port");
        JSONObject response = new JSONObject();
        response.put("code", 800191);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map field_cannot_be_empty() {
        String message = ("field cannot be empty");
        JSONObject response = new JSONObject();
        response.put("code", 800192);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map creating_editing_tables_not_allowed() {
        String message = ("creating editing tables not allowed");
        JSONObject response = new JSONObject();
        response.put("code", 800193);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map synchronized_nodes_cannot_be_synced_again() {
        String message = ("synchronized nodes cannot be synced again");
        JSONObject response = new JSONObject();
        response.put("code", 800194);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map parent_node_cannot_be_empty() {
        String message = ("parent node cannot be empty");
        JSONObject response = new JSONObject();
        response.put("code", 800195);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map duplicate_parent_node_name() {
        String message = ("duplicate parent node name");
        JSONObject response = new JSONObject();
        response.put("code", 800196);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map device_model_name_duplicate_in_model_library(String type) {
        String message = ("device model name duplicate in model library:");
        JSONObject response = new JSONObject();
        response.put("code", 800197);
        response.put("message", message+type);
        response.put("data", null);
        return response;
    }
    public Map device_model_not_found(String type) {
        String message = ("device model not found:");
        JSONObject response = new JSONObject();
        response.put("code", 800198);
        response.put("message", message+type);
        response.put("data", null);
        return response;
    }
    public Map parent_node_not_found(String name) {
        String message = ("parent node not found:");
        JSONObject response = new JSONObject();
        response.put("code", 800199);
        response.put("message", message+name);
        response.put("data", null);
        return response;
    }
    public Map parent_node_rack_has_no_container() {
        String message = (" parent node rack has no container");
        JSONObject response = new JSONObject();
        response.put("code", 800200);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map no_rack_container(String name) {
        String message = (" no rack container ");
        JSONObject response = new JSONObject();
        response.put("code", 800201);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }

    public Map card_sync_failed() {
        String message = (" card sync failed");
        JSONObject response = new JSONObject();
        response.put("code", 800202);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map cannot_sync_synchronized_cables() {
        String message = ("cannot sync synchronized cables");
        JSONObject response = new JSONObject();
        response.put("code", 800203);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map no_such_cable_model(String type) {
        String message = ("no such cable model ");
        JSONObject response = new JSONObject();
        response.put("code", 800204);
        response.put("message",message+type);
        response.put("data", null);
        return response;
    }
    public Map starting_u_position_not_specified(String name) {
        String message = ("starting u position not specified ");
        JSONObject response = new JSONObject();
        response.put("code", 800205);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }

    public Map corresponding_u_position_in_this_rack_has_no_parent_node(String name) {
        String message = ("corresponding u position in this rack has no parent node ");
        JSONObject response = new JSONObject();
        response.put("code", 800206);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }
    public Map no_such_slot(String name) {
        String message = ("no such slot ");
        JSONObject response = new JSONObject();
        response.put("code", 800207);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }

    public Map slot_without_card(String name) {
        String message = ("slot without card ");
        JSONObject response = new JSONObject();
        response.put("code", 800208);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }
    public Map no_such_port(String name) {
        String message = ("no such port ");
        JSONObject response = new JSONObject();
        response.put("code", 800209);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }

    public Map duplicate_port_names_within_same_parent_node(String name) {
        String message = ("duplicate port names within same parent node ");
        JSONObject response = new JSONObject();
        response.put("code", 800210);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }
    public Map cannot_create_connection_between_these_two_nodes_because_port_types_do_not_match(String name) {
        String message = ("cannot create connection between these two nodes because port types do not match ");
        JSONObject response = new JSONObject();
        response.put("code", 800211);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }
    public JSONObject deletion_failed() {
        String message = ("deletion failed");
        JSONObject response = new JSONObject();
        response.put("code", 800212);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject deletion_failed(String message2) {
        String message = ("deletion failed"+message2);
        JSONObject response = new JSONObject();
        response.put("code", 800212);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map successful_deletion_quantity(String i) {
        String message = ("successful deletion quantity ");
        JSONObject response = new JSONObject();
        response.put("code", 200);
        response.put("message",message+i);
        response.put("data", null);
        return response;
    }

    public Map position_parameter_format_incorrect() {
        String message = ("position parameter format incorrect please enter coordinates in xxx,xxx format");
        JSONObject response = new JSONObject();
        response.put("code", 800213);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map longitude_value_range_between() {
        String message = ("longitude value range is between:-180~180");
        JSONObject response = new JSONObject();
        response.put("code", 800214);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map latitude_value_range_between() {
        String message = ("latitude value range is between:-90~90");
        JSONObject response = new JSONObject();
        response.put("code", 800215);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map devices_in_shelved_state_are_not_allowed_to_modify_position() {
        String message = ("devices in shelved state are not allowed to modify position");
        JSONObject response = new JSONObject();
        response.put("code", 800216);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map devices_in_shelved_state_are_not_allowed_to_modify_model() {
        String message = ("devices in shelved state are not allowed to modify model");
        JSONObject response = new JSONObject();
        response.put("code", 800217);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map parent_node_does_not_exist() {
        String message = ("parent node does not exist");
        JSONObject response = new JSONObject();
        response.put("code", 800218);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map devices_in_shelved_state_are_not_allowed_to_modify_parent_node() {
        String message = ("devices in shelved state are not allowed to modify parent node");
        JSONObject response = new JSONObject();
        response.put("code", 800219);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map this_value_does_not_match_the_data_validation_constraints_defined_for_this_cell() {
        String message = ("this value does not match the data validation constraints defined for this cell");
        JSONObject response = new JSONObject();
        response.put("code", 800220);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map import_not_allowed_if_submission_status_is_not_empty() {
        String message = ("import not allowed if submission status is not empty");
        JSONObject response = new JSONObject();
        response.put("code", 800221);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map no_such_attachment_node(String name) {
        String message = ("no such attachment node");
        JSONObject response = new JSONObject();
        response.put("code", 800222);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }
    public Map coordinates_or_attachment_node_are_required_for_import_on_the_map() {
        String message = ("coordinates or attachment node are required for import on the map");
        JSONObject response = new JSONObject();
        response.put("code", 800223);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map imported_cable_missing_diagram_id() {
        String message = ("imported cable missing diagram id");
        JSONObject response = new JSONObject();
        response.put("code", 800224);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map starting_port_and_ending_port_already_have_a_connection() {
        String message = ("starting port and ending port already have a connection");
        JSONObject response = new JSONObject();
        response.put("code", 800225);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map only_nodes_or_links_can_view_routes() {
        String message = ("only nodes or links can view routes");
        JSONObject response = new JSONObject();
        response.put("code", 800226);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map cannot_delete_this_group_as_it_contains_users() {
        String message = ("cannot delete this group as it contains users");
        JSONObject response = new JSONObject();
        response.put("code", 800227);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map group_name_cannot_be_duplicate() {
        String message = ("group name cannot be duplicate");
        JSONObject response = new JSONObject();
        response.put("code", 800228);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map modification_failed() {
        String message = ("modification failed");
        JSONObject response = new JSONObject();
        response.put("code", 800229);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map name_is_required_at_minimum() {
        String message = ("name is required at minimum");
        JSONObject response = new JSONObject();
        response.put("code", 800230);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map duplicate_name() {
        String message = ("duplicate name");
        JSONObject response = new JSONObject();
        response.put("code", 800231);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map duplicate_name(String name) {
        String message = ("duplicate name");
        JSONObject response = new JSONObject();
        response.put("code", 800231);
        response.put("message",message);
        response.put("data", name);
        return response;
    }
    public JSONObject duplicate_name_json() {
        String message = ("duplicate name");
        JSONObject response = new JSONObject();
        response.put("code", 800231);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public Map system_fields_cannot_be_deleted() {
        String message = ("system fields cannot be deleted");
        JSONObject response = new JSONObject();
        response.put("code", 800232);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map please_select_the_type_to_delete() {
        String message = ("please select the type to delete");
        JSONObject response = new JSONObject();
        response.put("code", 800233);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map field_default_value_cannot_be_empty() {
        String message = ("field default value cannot be empty");
        JSONObject response = new JSONObject();
        response.put("code", 800234);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject missing_from_field() {
        String message = ("missing from field");
        JSONObject response = new JSONObject();
        response.put("code", 800235);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject missing_to_field() {
        String message = ("missing to field");
        JSONObject response = new JSONObject();
        response.put("code", 800236);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject missing_ip_field_on_both_ends() {
        String message = ("missing ip field on both ends");
        JSONObject response = new JSONObject();
        response.put("code", 800237);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject incorrect_syntax() {
        String message = ("incorrect syntax correct syntax is XX&$01-1");
        JSONObject response = new JSONObject();
        response.put("code", 800238);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject cable_model_cannot_be_changed() {
        String message = ("cable model cannot be changed");
        JSONObject response = new JSONObject();
        response.put("code", 800239);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject fiber_cable_cannot_create_sub_links() {
        String message = ("fiber cable cannot create sub links");
        JSONObject response = new JSONObject();
        response.put("code", 800240);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject port_type_has_no_matching_available_cables() {
        String message = ("port type has no matching available cables");
        JSONObject response = new JSONObject();
        response.put("code", 800241);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject this_interface_is_only_for_creating_cables_with_one_end_or_no_nodes() {
        String message = ("this interface is only for creating cables with one end or no nodes");
        JSONObject response = new JSONObject();
        response.put("code", 800242);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject global_settings_do_not_have_auto_discovery_url_set() {
        String message = ("global settings do not have auto discovery url set");
        JSONObject response = new JSONObject();
        response.put("code", 800243);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject diagramId_cannot_be_empty() {
        String message = ("diagramId cannot be empty");
        JSONObject response = new JSONObject();
        response.put("code", 800244);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject incorrect_diagramId() {
        String message = ("incorrect diagramId");
        JSONObject response = new JSONObject();
        response.put("code", 800245);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject type_cannot_be_empty() {
        String message = ("type cannot be empty");
        JSONObject response = new JSONObject();
        response.put("code", 800246);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject nodeclass_null() {
        String message = ("nodeclass null");
        JSONObject response = new JSONObject();
        response.put("code", 800247);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject field_name_cannot_be_empty() {
        String message = ("field name cannot be empty");
        JSONObject response = new JSONObject();
        response.put("code", 800248);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject nodes_of_different_categories_cannot_change_models() {
        String message = ("nodes of different categories cannot change models");
        JSONObject response = new JSONObject();
        response.put("code", 800249);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject model_not_found_in_system_please_check_input() {
        String message = ("model not found in system please check input");
        JSONObject response = new JSONObject();
        response.put("code", 800250);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject the_number_of_nodes_exceeds_the_authorized_limit_and_adding_nodes_is_not_possible() {
        String message = ("the number of nodes exceeds the authorized limit and adding nodes is not possible");
        JSONObject response = new JSONObject();
        response.put("code", 800251);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject a_device_with_the_same_ip_already_exists_to_replace_it_you_must_first_delete_the_original_device(Long id) {
        String message = ("a device with the same ip already exists to replace it you must first delete the original device:");
        JSONObject response = new JSONObject();
        response.put("code", 800252);
        response.put("message",message+id);
        response.put("data", null);
        return response;
    }
    public JSONObject no_default_replacement_device_model_for_auto_discovery() {
        String message = ("no default replacement device model for auto discovery:monitor device");
        JSONObject response = new JSONObject();
        response.put("code", 800253);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject vendor_is_required() {
        String message = ("vendor is required");
        JSONObject response = new JSONObject();
        response.put("code", 800254);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject width_cannot_be_out_of_range() {
        String message = ("width cannot be out of range");
        JSONObject response = new JSONObject();
        response.put("code", 800255);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject height_cannot_be_out_of_range() {
        String message = ("height cannot be out of range");
        JSONObject response = new JSONObject();
        response.put("code", 800256);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject deep_cannot_be_out_of_range() {
        String message = ("deep cannot be out of range");
        JSONObject response = new JSONObject();
        response.put("code", 800257);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject inventory_cannot_be_out_of_range() {
        String message = ("inventory cannot be out of range");
        JSONObject response = new JSONObject();
        response.put("code", 800258);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject please_select_the_model_to_delete() {
        String message = ("please select the model to delete");
        JSONObject response = new JSONObject();
        response.put("code", 800259);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject message_id_cannot_be_empty() {
        String message = ("message id cannot be empty");
        JSONObject response = new JSONObject();
        response.put("code", 800260);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject please_confirm_the_modification_content() {
        String message = ("please confirm the modification content");
        JSONObject response = new JSONObject();
        response.put("code", 800261);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject bath_update_name_not_allow() {
        String message = ("bath update name not allow");
        JSONObject response = new JSONObject();
        response.put("code", 800262);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject the_selected_device_must_be_shelved_in_a_rack_to_be_set_as_a_template() {
        String message = ("the selected device must be shelved in a rack to be set as a template");
        JSONObject response = new JSONObject();
        response.put("code", 800263);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject the_number_of_applied_racks_is_not_a_multiple_of_the_number_of_template_racks_so_applying_the_template_failed() {
        String message = ("the number of applied racks is not a multiple of the number of template racks so applying the template failed");
        JSONObject response = new JSONObject();
        response.put("code", 800264);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject no_available_u_positions(String name) {
        String message = ("no available u position");
        JSONObject response = new JSONObject();
        response.put("code", 800265);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }
    public JSONObject rack_shelving_device_failed(String name) {
        String message = ("rack shelving device failed ");
        JSONObject response = new JSONObject();
        response.put("code", 800266);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }
    public JSONObject some_cross_object_cables_failed_to_connect() {
        String message = ("some cross object cables failed to connect");
        JSONObject response = new JSONObject();
        response.put("code", 800267);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject the_number_of_applied_devices_should_be_a_multiple_of_the_number_of_template_devices() {
        String message = ("the number of applied devices should be a multiple of the number of template devices");
        JSONObject response = new JSONObject();
        response.put("code", 800268);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject there_are_no_nodes_available_to_attach_the_spool_point_to_the_desired_location() {
        String message = ("there are no nodes available to attach the spool point to the desired location");
        JSONObject response = new JSONObject();
        response.put("code", 800269);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject forward_relationship_name_cannot_be_empty() {
        String message = ("forward relationship name cannot be empty");
        JSONObject response = new JSONObject();
        response.put("code", 800270);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject backward_relationship_name_cannot_be_empty() {
        String message = ("backward relationship name cannot be empty");
        JSONObject response = new JSONObject();
        response.put("code", 800271);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject forward_and_backward_relationship_names_cannot_be_the_same() {
        String message = ("forward and backward relationship names cannot be the same");
        JSONObject response = new JSONObject();
        response.put("code", 800272);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject forward_relationship_name_already_exists() {
        String message = ("forward relationship name already exists");
        JSONObject response = new JSONObject();
        response.put("code", 800273);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject backward_relationship_name_already_exists() {
        String message = ("backward relationship name already exists");
        JSONObject response = new JSONObject();
        response.put("code", 800274);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject this_relationship_type_has_relationship_instances_and_cannot_be_deleted() {
        String message = ("this relationship type has relationship instances and cannot be delete");
        JSONObject response = new JSONObject();
        response.put("code", 800275);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject report_group_names_cannot_be_duplicated() {
        String message = ("report group names cannot be duplicated");
        JSONObject response = new JSONObject();
        response.put("code", 800276);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject the_group_contains_reports_and_cannot_be_deleted() {
        String message = ("the group contains reports and cannot be deleted");
        JSONObject response = new JSONObject();
        response.put("code", 800277);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject file_upload_failed() {
        String message = ("file upload failed");
        JSONObject response = new JSONObject();
        response.put("code", 800278);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject file_corresponding_to_the_file_name_does_not_exist() {
        String message = ("file corresponding to the file name does not exist");
        JSONObject response = new JSONObject();
        response.put("code", 800279);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject modify_U_position_failed_please_check_if_U_position_is_already_occupied() {
        String message = ("modify U position failed please check if U position is already occupied");
        JSONObject response = new JSONObject();
        response.put("code", 800280);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject no_original_shelving_information_modifying_starting_U_position_not_allowed() {
        String message = ("no original shelving information modifying starting U position not allowed");
        JSONObject response = new JSONObject();
        response.put("code", 800281);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject the_cabinet_does_not_have_another_side_so_the_installation_direction_cannot_be_modified() {
        String message = ("the cabinet does not have another side so the installation direction cannot be modified");
        JSONObject response = new JSONObject();
        response.put("code", 800282);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject no_original_shelving_information_so_installation_direction_cannot_be_modified() {
        String message = ("no original shelving information so installation direction cannot be modified");
        JSONObject response = new JSONObject();
        response.put("code", 800283);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject set_as_globally_unique_field_value_cannot_be_repeated(String name) {
        String message = (" set as globally unique field value cannot be repeated ");
        JSONObject response = new JSONObject();
        response.put("code", 800284);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }
    public JSONObject the_device_does_not_have_an_IP_property_filled() {
        String message = (" the device does not have an IP property filled ");
        JSONObject response = new JSONObject();
        response.put("code", 800285);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject the_cable_does_not_have_some_property_value_filled(String name) {
        String message = (" the cable does not have some property value filled ");
        JSONObject response = new JSONObject();
        response.put("code", 800286);
        response.put("message",message+name);
        response.put("data", null);
        return response;
    }
    public JSONObject the_port_is_already_occupied() {
        String message = (" the port is already occupied ");
        JSONObject response = new JSONObject();
        response.put("code", 800287);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject this_fiber_core_has_already_been_spliced_If_you_need_to_splice_please_disconnect_the_original_splice_first(Long id) {
        String message = ("this fiber core has already been spliced If you need to splice please disconnect the original splice first");
        JSONObject response = new JSONObject();
        response.put("code", 800288);
        response.put("message",id+message);
        response.put("data", null);
        return response;
    }
    public JSONObject the_synchronization_address_does_not_exist_please_check_whether_the_synchronization_address_is_correctly_filled() {
        String message = ("the synchronization address does not exist please check whether the synchronization address is correctly filled");
        JSONObject response = new JSONObject();
        response.put("code", 800289);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject token_request_failed_please_check_whether_the_synchronization_address_username_and_password_are_correct() {
        String message = ("token request failed please check whether the synchronization address username and password are correct");
        JSONObject response = new JSONObject();
        response.put("code", 800290);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject old_password_input_error() {
        String message = (" old password input error ");
        JSONObject response = new JSONObject();
        response.put("code", 800291);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject new_password_input_error() {
        String message = (" new password input error ");
        JSONObject response = new JSONObject();
        response.put("code", 800292);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject duplicate_user_template() {
        String message = ("duplicate user template");
        JSONObject response = new JSONObject();
        response.put("code", 800293);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject dont_have_vendor() {
        String message = ("dont have vendor");
        JSONObject response = new JSONObject();
        response.put("code", 800294);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject vendor_repeat() {
        String message = ("vendor repeat");
        JSONObject response = new JSONObject();
        response.put("code", 800295);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject vendor_have_entity() {
        String message = ("vendor have entity");
        JSONObject response = new JSONObject();
        response.put("code", 800296);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject video_type_error() {
        String message = (" video type error ");
        JSONObject response = new JSONObject();
        response.put("code", 800297);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject video_picture_name_error() {
        String message = (" video picture name error ");
        JSONObject response = new JSONObject();
        response.put("code", 800298);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject video_file_namw_error() {
        String message = (" video file namw error ");
        JSONObject response = new JSONObject();
        response.put("code", 800299);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject passing_parameters_error() {
        String message = (" passing parameters error ");
        JSONObject response = new JSONObject();
        response.put("code", 800300);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject node_sorting_only_allowed() {
        String message = (" node sorting only allowed ");
        JSONObject response = new JSONObject();
        response.put("code", 800301);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject port_modification_not_allowed_vis_node() {
        String message = (" port modification not allowed vis node ");
        JSONObject response = new JSONObject();
        response.put("code", 800302);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject empty_tasks_error() {
        String message = (" empty tasks error ");
        JSONObject response = new JSONObject();
        response.put("code", 800303);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject token_error(Boolean b) {
        String message = (" token error ");
        JSONObject response = new JSONObject();
        response.put("code", 800304);
        response.put("message",message);
        response.put("data", b);
        return response;
    }
    public JSONObject token_malformed() {
        String message = ("token malformed");
        JSONObject response = new JSONObject();
        response.put("code", 800305);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject token_illegal_argument() {
        String message = ("token illegal argument");
        JSONObject response = new JSONObject();
        response.put("code", 800306);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject user_needs_login_again() {
        String message = ("user needs login again");
        JSONObject response = new JSONObject();
        response.put("code", 800307);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject check_the_IP_address_of_the_object() {
        String message = (" check the IP address of the object ");
        JSONObject response = new JSONObject();
        response.put("code", 800308);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject accessing_LLDP_exception() {
        String message = (" accessing LLDP exception ");
        JSONObject response = new JSONObject();
        response.put("code", 800309);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject node_is_not_a_device() {
        String message = ("please select a device");
        JSONObject response = new JSONObject();
        response.put("code", 800310);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject diagram_is_not_a_rack() {
        String message = ("diagram is not a rack");
        JSONObject response = new JSONObject();
        response.put("code", 800311);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject container_does_not_exist() {
        String message = (" container does not exist ");
        JSONObject response = new JSONObject();
        response.put("code", 800312);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject rack_has_no_container() {
        String message = (" rack has no container ");
        JSONObject response = new JSONObject();
        response.put("code", 800313);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject can_not_mount_device_in_this_position() {
        String message = (" can not mount device in this position ");
        JSONObject response = new JSONObject();
        response.put("code", 800314);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject search_error() {
        String message = (" search error ");
        JSONObject response = new JSONObject();
        response.put("code", 800315);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject license_user_limit() {
        String message = ("license user limit");
        JSONObject response = new JSONObject();
        response.put("code", 800316);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject duplicate_user() {
        String message = (" duplicate user ");
        JSONObject response = new JSONObject();
        response.put("code", 800317);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject Email_already_exists_do_not_reuse() {
        String message = (" Email already exists do not reuse ");
        JSONObject response = new JSONObject();
        response.put("code", 800318);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject delete_link_failed() {
        String message = ("delete link failed");
        JSONObject response = new JSONObject();
        response.put("code", 800319);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject access_denied() {
        String message = ("access denied");
        JSONObject response = new JSONObject();
        response.put("code", 800320);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject invalid_type_group() {
        String message = ("invalid type group");
        JSONObject response = new JSONObject();
        response.put("code", 800321);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject can_not_delete_devices_links() {
        String message = ("can not delete devices links");
        JSONObject response = new JSONObject();
        response.put("code", 800322);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject update_tasks_error() {
        String message = ("update tasks error");
        JSONObject response = new JSONObject();
        response.put("code", 800323);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject license_node_limit(Map data) {
        String message = ("license node limit");
        JSONObject response = new JSONObject();
        response.put("code", 800324);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    public JSONObject notice_title_empty() {
        String message = ("notice title empty");
        JSONObject response = new JSONObject();
        response.put("code", 800325);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject notice_content_empty() {
        String message = ("notice content empty");
        JSONObject response = new JSONObject();
        response.put("code", 800326);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject notice_user_empty() {
        String message = ("notice user empty");
        JSONObject response = new JSONObject();
        response.put("code", 800327);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject upload_file_failed_loading() {
        String message = ("upload file failed loading");
        JSONObject response = new JSONObject();
        response.put("code", 800328);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject system_user_cant_delete() {
        String message = ("system user cant delete");
        JSONObject response = new JSONObject();
        response.put("code", 800329);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject link_not_found(int i) {
        String message = ("link not found");
        JSONObject response = new JSONObject();
        response.put("code", 800330);
        response.put("message", message);
        response.put("data", i);
        return response;
    }

    public JSONObject link_route_rack_not_found(int i) {
        String message = ("link route rack not found");
        JSONObject response = new JSONObject();
        response.put("code", 800331);
        response.put("message", message);
        response.put("data", i);
        return response;
    }

    public JSONObject directory_not_exists() {
        String message = ("directory not exists");
        JSONObject response = new JSONObject();
        response.put("code", 800332);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject cannot_find_topology_data() {
        String message = ("cannot find topology data");
        JSONObject response = new JSONObject();
        response.put("code", 800333);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject lldp_error() {
        String message = ("lldp error");
        JSONObject response = new JSONObject();
        response.put("code", 800334);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject checkout_topology_ip() {
        String message = ("checkout topology ip");
        JSONObject response = new JSONObject();
        response.put("code", 800335);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject upload_fail_path() {
        String message = ("upload fail path");
        JSONObject response = new JSONObject();
        response.put("code", 800336);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject layers_already_exist() {
        String message = ("layers already exist");
        JSONObject response = new JSONObject();
        response.put("code", 800337);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject file_deletion_failed() {
        String message = ("file deletion failed");
        JSONObject response = new JSONObject();
        response.put("code", 800338);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject link_type_link_not_present() {
        String message = ("link type link not present");
        JSONObject response = new JSONObject();
        response.put("code", 800339);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public JSONObject USER_NAME_OR_PASSWORD_INPUT_ERROR() {
        String message = ("user name or password input error");
        JSONObject response = new JSONObject();
        response.put("code", 800340);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public JSONObject NOTICE_PRIORITY_NAME_REPLACE() {
        String message = ("notice priority name replace");
        JSONObject response = new JSONObject();
        response.put("code", 800341);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public JSONObject TASK_TYPE_EXISTS_TASK_NOT_DELETE() {
        String message = ("task type exists task not delete");
        JSONObject response = new JSONObject();
        response.put("code", 800342);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public JSONObject PORT_NOT_CONNECTION() {
        String message = ("port not connection");
        JSONObject response = new JSONObject();
        response.put("code", 800343);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public JSONObject EMPTY_TASKS_ERROR() {
        String message = ("empty tasks error");
        JSONObject response = new JSONObject();
        response.put("code", 800344);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public JSONObject UNABLE_TO_FIND_CORRESPIND_ALARM_INFORMATON() {
        String message = ("unable to find corresponding alarm information");
        JSONObject response = new JSONObject();
        response.put("code", 800345);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public JSONObject is_not_a_node() {
        String message = ("is not a node");
        JSONObject response = new JSONObject();
        response.put("code", 800346);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public JSONObject error_sending_email(String name) {
        String message = ("error sending email:"+name);
        JSONObject response = new JSONObject();
        response.put("code", 800347);
        response.put("message",message);
        response.put("data", null);
        return response;
    }

    public Map PHONE_NOT_FOUND_SMS() {
        String message = ("不存在此手机号，请联系客服");
        Map response = new LinkedHashMap();
        response.put("code", 800348);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

    public Map model_name_duplicate_in_model_library() {
        String message = ("model name duplicate in model library");
        JSONObject response = new JSONObject();
        response.put("code", 800349);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map USER_ALREADY_EXISTS2() {
        String message = ("user already exists");
        Map response = new LinkedHashMap();
        response.put("code", 800350);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map model_error() {
        String message = ("There is an error in setting the scale of the device model. Please contact customer service to modify the modeling image again");
        Map response = new LinkedHashMap();
        response.put("code", 800351);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map CANT_CREATE_INNER_DEVICE_NODE() {

        String message = ("Nodes cannot be created within the device");
        Map response = new LinkedHashMap();
        response.put("code", 800352);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map cant_modifying_cable_models() {
        String message = ("currently not supported for modifying cable models");
        JSONObject response = new JSONObject();
        response.put("code", 800353);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public JSONObject report_duplicate_name() {
        String message = ("report with duplicate name");
        JSONObject response = new JSONObject();
        response.put("code", 800354);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map cant_be_laid_on_a_fork_in_the_road() {

        String message = ("cant be laid on a fork in the road");
        Map response = new LinkedHashMap();
        response.put("code", 800355);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public JSONObject share_link_NOT_FOUND() {
        String message = ("share link not found");
        JSONObject response = new JSONObject();
        response.put("code", 800356);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject the_sharing_link_has_expired() {
        String message = ("the sharing link has expired");
        JSONObject response = new JSONObject();
        response.put("code", 800357);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject sharing_link_without_permission_access() {
        String message = ("Sharing link without permission access");
        JSONObject response = new JSONObject();
        response.put("code", 800358);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject inconsistent_number_of_containers() {
        String message = ("inconsistent number of containers");
        JSONObject response = new JSONObject();
        response.put("code", 800359);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map to_access_map_must_provide_range_parameter() {
        String message = ("to_access_map_must_provide_range_parameter");
        JSONObject response = new JSONObject();
        response.put("code", 800360);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public JSONObject only_node_can_combination() {
        String message = ("only node can combination");
        JSONObject response = new JSONObject();
        response.put("code", 800361);
        response.put("message",message);
        response.put("data", null);
        return response;
    }
    public Map INCONSISTENT_NUMBER_OF_SLOTS() {
        String message = ("inconsistent number of slots");
        Map response = new LinkedHashMap();
        response.put("code", 800362);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map token_request_timed_out() {
        String message = ("token request timed out");
        Map response = new LinkedHashMap();
        response.put("code", 800363);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public Map same_type_object_must_be_selected() {
        String message = ("same_type_object_must_be_selected");
        Map response = new LinkedHashMap();
        response.put("code", 800364);
        response.put("message", message);
        response.put("data", null);
        return response;
    }
    public JSONObject cant_lay_bent() {
//        String message = ResourceBundleResolver.Language().get("entity_not_found");
        String message = ("automatic laying cannot lay bent pipe");
        JSONObject response = new JSONObject();
        response.put("code", 800365);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

}
