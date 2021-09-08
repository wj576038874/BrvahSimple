//
//  CLog.cpp
//  Joyrun_oc_alg
//
//  Created by Wiki on 15/10/20.
//  Copyright © 2015年 Wiki. All rights reserved.
//

#include "CLog.hpp"
//#include <android/log.h>
//#include <jni.h>
//#define  LOG_TAG  "libjoyrun.so"
//#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
//#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

int CLog::setLogLevel(int l){
    level = l;
    return level;
}
void CLog::printInfo()
{
//    LOGE(myLogger.c_str());
    myLogger = "";
}
void CLog::printDebug()
{
//    LOGE(myLogger.c_str());
    myLogger = "";
}
void CLog::printError(){
//    LOGE(myLogger.c_str());
    myLogger = "";
}
        
        
