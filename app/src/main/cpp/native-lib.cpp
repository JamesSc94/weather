#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

Java_com_jamessc94_weather_network_ServiceInterceptor_getAPIKey(JNIEnv *env, jobject object) {
    std::string api_key = "8d863944cd6fbb68560f6492507d0ceabe192033";
    return env->NewStringUTF(api_key.c_str());
}