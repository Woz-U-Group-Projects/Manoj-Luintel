package com.example.groupproject.utils;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LocalResponse {
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";
    private static final String RESULT = "data";

    public LocalResponse() {
    }

    public static ResponseEntity<?> configure(Object object){
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put(MESSAGE, "Success");
        addObjectToResponse(object,objectMap);

        return ResponseEntity.ok().body(objectMap);
    }

    public static ResponseEntity<?> configure(){
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put(ERROR,false);
        objectMap.put(MESSAGE, "Success");

        return ResponseEntity.ok().body(objectMap);
    }

    private static void addObjectToResponse(Object object, Map<String, Object> objectMap) {
        objectMap.put(ERROR,false);
        if (object instanceof ArrayList && !((ArrayList) object).isEmpty()){
            List<Object> objectList = new ArrayList<>((ArrayList<?>) object);
            objectMap.put(RESULT,objectList);
            objectMap.put("count",objectList.size());
        }else if (object instanceof ArrayList && ((ArrayList<?>) object).isEmpty()){
            objectMap.put(MESSAGE,"no data found");
            objectMap.put(RESULT,new ArrayList<>());
        }else if (object instanceof String){
            objectMap.put(MESSAGE,object);
        }else {
            objectMap.put(RESULT,object);
            objectMap.put("count",object == null?0:1);
        }
    }
}
