#include <jni.h>
JNIEXPORT jstring JNICALL
Java_com_jamessc94_weather_network_ServiceInterceptor_getAPIKey(JNIEnv *env, jobject instance) {
 return (*env)->  NewStringUTF(env, "8d863944cd6fbb68560f6492507d0ceabe192033");
}