#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_issog_submissioncompose_core_utils_security_ComposeNativeLibs_beritainApiKey(JNIEnv *env, jobject thiz) {
    std::string value = "d305ba82931246e7816341ecc21fbcb1";
    return env ->NewStringUTF(value.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_issog_submissioncompose_core_utils_security_ComposeNativeLibs_beritainBaseUrl(JNIEnv *env, jobject thiz) {
    std::string value = "https://newsapi.org/v2/";
    return env ->NewStringUTF(value.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_issog_submissioncompose_core_utils_security_ComposeNativeLibs_getNewsSourceUrl(JNIEnv *env, jobject thiz) {
    std::string value = "top-headlines/sources";
    return env ->NewStringUTF(value.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_issog_submissioncompose_core_utils_security_ComposeNativeLibs_getTopHeadlineByCategoryUrl(JNIEnv *env, jobject thiz) {
    std::string value = "top-headlines";
    return env ->NewStringUTF(value.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_issog_submissioncompose_core_utils_security_ComposeNativeLibs_beritainDb(JNIEnv *env, jobject thiz) {
    std::string value = "beritain.db";
    return env ->NewStringUTF(value.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_issog_submissioncompose_core_utils_security_ComposeNativeLibs_beritainPassphrase(JNIEnv *env,jobject thiz) {
    std::string value = "DlyQ93bfKmYZ8Zy7BGt6tKELWtGmifc4";
    return env ->NewStringUTF(value.c_str());
}