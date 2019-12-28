package com.reablace.masterquiz.firebase.firestore

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.reablace.masterquiz.common.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val TAG: String = "FirebaseDatabaseRep"

class FirestoreRepository @Inject constructor() : FirestoreRepositoryContract {

    private val db = FirebaseFirestore.getInstance()

    //TODO: Change String result to Result Object
    override suspend fun getUserTenancy(userAuthId: String): String {
        val snapshot = db.collection(USERS_TENANCIES_ROOT).document(userAuthId).get().await()

        return if (snapshot.exists()) {
            snapshot.getString(USERS_TENANCIES_FIELD_ID) ?: ""
        } else {
            ""
        }
    }

    override suspend fun getEventList(): QuerySnapshot = db.collection(EVENTS_ROOT).get().await()

    override suspend fun getFilterEventList(eventType: String): QuerySnapshot {

        val currentDate = Timestamp.now()

        return if (eventType == FUTURE_EVENTS)
            db.collection(EVENTS_ROOT).whereGreaterThanOrEqualTo(EVENTS_FIELD_DATE, currentDate.toDate()).get().await()
        else
            db.collection(EVENTS_ROOT).whereLessThan(EVENTS_FIELD_DATE, currentDate.toDate()).get().await()
    }

    override fun getFilterEventList(eventType: String, listener: EventListener<QuerySnapshot>) {

        val currentDate = Timestamp.now()

        if (eventType == FUTURE_EVENTS)
            db.collection(EVENTS_ROOT).whereGreaterThanOrEqualTo(EVENTS_FIELD_DATE, currentDate.toDate())
                .addSnapshotListener(listener)
        else
            db.collection(EVENTS_ROOT).whereLessThan(EVENTS_FIELD_DATE, currentDate.toDate())
                .addSnapshotListener(listener)
    }

    override fun getEventCollection(): CollectionReference? = db.collection(EVENTS_ROOT)
    override fun getPlayersCollection(): CollectionReference? = db.collection(PLAYERS_ROOT)
    override fun getQuestionsCollection(): CollectionReference? = db.collection(QUESTIONS_ROOT)
    override fun getQuizzesCollection(): CollectionReference? = db.collection(QUIZZES_ROOT)
    override fun getTenanciesCollection(): CollectionReference? = db.collection(TENANCIES_ROOT)
    override fun getUsersTenanciesCollection(): CollectionReference? = db.collection(USERS_TENANCIES_ROOT)

}
