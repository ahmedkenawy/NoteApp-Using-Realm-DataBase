package com.a7medkenawy.noteapp.realmmananger

import com.a7medkenawy.noteapp.model.Note
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults

class NoteManager {
    private val config: RealmConfiguration =
        RealmConfiguration.Builder()
            .name("Note.realm")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(1).build()
    private val realm: Realm = Realm.getInstance(config)

    fun addNoteOrUpdate(note: Note) {
        realm.beginTransaction()
        note.let { note ->
            realm.insertOrUpdate(note)
            realm.commitTransaction()
        }
    }

    val getAllNotes: RealmResults<Note>
        get() = realm.where(Note::class.java).findAll()


    fun deleteFromRealm(id: String) {
        val itemRealmObject = realm.where(Note::class.java).equalTo("id", id).findFirst()
        if (itemRealmObject != null) {
            realm.beginTransaction()
            itemRealmObject.deleteFromRealm()
            realm.commitTransaction()
        }
    }

    fun deleteAllFromRealm(){
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
    }
}