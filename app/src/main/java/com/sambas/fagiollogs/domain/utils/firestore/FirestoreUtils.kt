package com.sambas.fagiollogs.domain.utils.firestore

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirestoreUtils @Inject constructor(
    private val firestore: FirebaseFirestore,
) {
    companion object {
        private val TAG = FirestoreUtils::class.java.simpleName
    }

    suspend fun <T> addDocumentToCollection(
        collectionPath: String,
        data: T
    ): Result<Unit> =
        try {
            firestore.document(collectionPath)
                .set(data!!, SetOptions.merge())
                .await()

            Log.d(TAG, "Document added to collection: $collectionPath")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error adding document to collection", e)
            Result.failure(e)
        }


    suspend fun <T> updateDocument(
        path: String,
        data: T
    ): Result<Unit> = try {
        firestore.document(path)
            .set(data!!, SetOptions.merge())
            .await()

        Log.d(TAG, "Document updated successfully at path: $path")
        Result.success(Unit)
    } catch (e: Exception) {
        Log.e(TAG, "Error updating document", e)
        Result.failure(e)
    }

    suspend fun <T> getDocument(
        path: String,
        classType: Class<T>
    ): Result<T?> = try {
        val snapshot = firestore.document(path)
            .get()
            .await()

        val data = snapshot.toObject(classType)
        Log.d(TAG, "Document retrieved successfully from path: $path")
        Result.success(data)
    } catch (e: Exception) {
        Log.e(TAG, "Error getting document", e)
        Result.failure(e)
    }

    suspend fun <T> getCollectionDocuments(
        path: String,
        classType: Class<T>
    ): Result<List<T>> = try {
        val snapshot = firestore.collection(path)
            .get()
            .await()

        val documents = snapshot.documents.mapNotNull { it.toObject(classType) }
        Log.d(TAG, "Collection documents retrieved successfully from path: $path")
        Result.success(documents)
    } catch (e: Exception) {
        Log.e(TAG, "Error getting collection documents", e)
        Result.failure(e)
    }

    suspend fun deleteDocument(path: String): Result<Unit> = try {
        firestore.document(path)
            .delete()
            .await()

        Log.d(TAG, "Document deleted successfully at path: $path")
        Result.success(Unit)
    } catch (e: Exception) {
        Log.e(TAG, "Error deleting document", e)
        Result.failure(e)
    }

    suspend fun <T> queryCollection(
        path: String,
        field: String,
        value: Any,
        classType: Class<T>
    ): Result<List<T>> = try {
        val snapshot = firestore.collection(path)
            .whereEqualTo(field, value)
            .get()
            .await()
        Result.success(snapshot.documents.mapNotNull { it.toObject(classType) })
    } catch (e: Exception) {
        Result.failure(e)
    }
}