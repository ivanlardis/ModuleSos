package com.example.i_larin.pixabayreader.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lardis.i_larin.module.repository.TestModel
import rx.Observable
import rx.subjects.BehaviorSubject
import timber.log.Timber


class IncidentsRepository : IIncidentsRepository {


    private var behaviorSubject: BehaviorSubject<MutableList<TestModel>>
            = BehaviorSubject.create<MutableList<TestModel>>()


    override fun subcRep(): Observable<MutableList<TestModel>> {

        return behaviorSubject.asObservable()

    }
    var mFirebaseDatabase: FirebaseDatabase


    constructor(firebaseDatabase: FirebaseDatabase) {
        mFirebaseDatabase = firebaseDatabase

        showElement()
    }



    var i = 1;



    override fun add() {
        addElement(TestModel("ann" + (i++)))

    }

    fun addElement(myObject: TestModel) {
        val reference = mFirebaseDatabase.getReference("Incidents")
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Timber.e("" + p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot?) {
                reference.push().setValue(myObject)
            }
        })
    }

    override fun rm() {
        val reference = mFirebaseDatabase.getReference("Incidents")
        reference.removeValue({ databaseError, databaseReference -> {} })
    }

    override fun loadData() {


        Timber.e("loadData")


    }


    fun showElement() {
        val reference = mFirebaseDatabase.getReference("Incidents")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Timber.e("" + p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot?) {
                val list = mutableListOf<TestModel>()
                p0?.children?.
                        forEach {
                    element ->
                    list.add(element.getValue(TestModel::class.java))
                }
                behaviorSubject.onNext(list)
            }
        })
    }

}