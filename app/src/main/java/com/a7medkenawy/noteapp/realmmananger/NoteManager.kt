package com.a7medkenawy.noteapp.realmmananger

import com.a7medkenawy.noteapp.model.Note
import io.realm.Realm
import io.realm.RealmConfiguration

class NoteManager {
    private val config: RealmConfiguration =
        RealmConfiguration.Builder()
            .name("Note.realm")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(1).build()

    fun addNote(note: Note) {
        val realm = Realm.getInstance(config)
        realm.beginTransaction()
        note.let {
            realm.insertOrUpdate(it)
            realm.commitTransaction()
            realm.close()
        }
    }
}