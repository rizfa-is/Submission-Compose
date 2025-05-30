package com.issog.submissioncompose.core.utils.security

object ComposeNativeLibs {
    init {
        System.loadLibrary("compose-native-libs")
    }

    external fun beritainApiKey(): String
    external fun beritainBaseUrl(): String
    external fun getNewsSourceUrl(): String
    external fun getTopHeadlineByCategoryUrl(): String
    external fun beritainDb(): String
    external fun beritainPassphrase(): String
}