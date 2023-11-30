package com.descritas.notesappmvvm.utils

import com.descritas.notesappmvvm.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository
lateinit var LOGIN: String
lateinit var PASSWORD: String


object Constants {
    object Keys {
        const val NOTE_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "Add new note"
        const val NOTE_TITLE = "Note Title"
        const val NOTE_SUBTITLE = "Note Subtitle"
        const val ADD_NOTE = "Add note"
        const val TITLE = "Add note"
        const val SUBTITLE = "Add note"
        const val CHOSE_BASE = "What base will we use"
        const val ROOM_DATABASE = "Room database"
        const val FIREBASE_DATABASE = "Firebase database"
        const val ID = "Id"
        const val NONE = "None"
        const val UPDATE = "Update"
        const val DELETE = "Delete"
        const val NAV_BACK = "Back"
        const val EDIT_NOTE = "Edit Note"
        const val EMPTY = ""
        const val UPDATE_NOTE = "Update note"
        const val SIGN_IN = "Sign in"
        const val LOG_IN = "Log in"
        const val INPUT_EMAIL = "Input e-mail"
        const val ENTER_PASSWORD = "Enter password"




    }

    object Screens {
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"

    }
}
