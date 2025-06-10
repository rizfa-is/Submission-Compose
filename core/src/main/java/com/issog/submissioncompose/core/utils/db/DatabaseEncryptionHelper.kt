package com.issog.submissioncompose.core.utils.db

import android.content.Context
import net.sqlcipher.database.SQLiteDatabase
import java.io.File

object DatabaseEncryptionHelper {
    fun migrateToEncrypted(
        context: Context,
        databaseName: String,
        passphrase: String
    ): Boolean {
        val databaseFile = context.getDatabasePath(databaseName)
        val tempDatabaseFile = File(context.filesDir, "$databaseName-temp")

        if (databaseFile.exists()) {
            try {
                // Try to open unencrypted database
                val tempDb = SQLiteDatabase.openOrCreateDatabase(databaseFile, "", null)
                tempDb.version // This will throw if database is already encrypted

                // Create encrypted database
                SQLiteDatabase.openOrCreateDatabase(databaseFile, passphrase, null).use { encryptedDb ->
                    // Copy data from unencrypted to encrypted database
                    tempDb.rawExecSQL("ATTACH DATABASE '${tempDatabaseFile.absolutePath}' AS encrypted KEY '$passphrase'")
                    tempDb.rawExecSQL("SELECT sqlcipher_export('encrypted')")
                    tempDb.rawExecSQL("DETACH DATABASE encrypted")
                }

                // Close and delete old database
                tempDb.close()
                databaseFile.delete()

                // Rename temp file to original name
                tempDatabaseFile.renameTo(databaseFile)
                return true
            } catch (e: Exception) {
                // Database is already encrypted or other error occurred
                tempDatabaseFile.delete()
                return false
            }
        }
        return false
    }
}