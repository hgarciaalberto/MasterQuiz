package com.reablace.masterquiz.common

import com.reablace.masterquiz.BuildConfig

// region User sesion SharedPref
////////////////////////////////
const val USER_SESION_SHARED_PREFERENCES: String = "user_sesion_data"
const val LOGIN_USER_NAME: String = "user_name"
const val USER_SESION_AUTH_ID: String = "user_aut_id"
const val USER_SESION_TENANT_ID: String = "user_tenant_id"
// endregion

// region Data SharedPrefs
//////////////////////////
const val DATA_SHARED_PREFERENCES: String = "data"
// endregion

// region Firebase
//////////////////

// Roots
const val ROOT: String = "root/${BuildConfig.FIRESTORE_FLAVOR}"
const val PLAYERS_ROOT: String = "${ROOT}/players"
const val QUESTIONS_ROOT: String = "${ROOT}/questions"
const val QUIZZES_ROOT: String = "${ROOT}/quizzes"

// Tenancies node
const val TENANCIES_ROOT: String = "${ROOT}/tenancies"

// Events node
const val EVENTS_ROOT: String = "${ROOT}/events"
const val EVENTS_FIELD_DATE: String = "date"
const val EVENTS_FIELD_DIFFICULTY: String = "difficulty"
const val EVENTS_FIELD_LOCATION: String = "location"
const val EVENTS_FIELD_NAME: String = "name"
const val EVENTS_FIELD_PLAYERS_JOINED: String = "playersJoined"
const val EVENTS_FIELD_PLAYERS_SCORE: String = "playersScore"
const val EVENTS_FIELD_QUIZZES: String = "quizzes"
const val EVENTS_FIELD_STATE: String = "state"
const val EVENTS_FIELD_TENANCY_ID: String = "tenancyId"

// Users Tenancies node
const val USERS_TENANCIES_ROOT: String = "${ROOT}/usersTenancies"
const val USERS_TENANCIES_FIELD_ID: String = "tenancyId"

// Players node
const val PLAYERS_FIELD_NAME: String = "name"


const val FIREBASE_TIMEOUT: Long = 100_000
// endregion

//region Permissions
////////////////////
const val MY_PERMISSIONS_REQUEST_GOOGLE_MAP = 100
//endregion

// region Events
const val EVENT = "event"
const val EVENT_ID = "event_id"
const val EVENT_TYPE = "event_type"
const val FUTURE_EVENTS = "future_events"
const val PAST_EVENTS = "past_events"
// endregion