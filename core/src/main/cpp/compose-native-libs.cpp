#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_issog_submissioncompose_core_utils_security_ComposeNativeLibs_beritainApiKey(JNIEnv *env, jobject thiz) {
    std::string value = "46d324d6f5ef44c38627e29bc47740d8";
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