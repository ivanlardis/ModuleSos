package com.example.i_larin.pixabayreader.repository

import com.example.i_larin.pixabayreader.network.VKApi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lardis.i_larin.module.model.FBModel
import com.lardis.i_larin.module.model.IncidentsModel
import com.lardis.i_larin.module.model.UserModel
import com.vk.sdk.VKAccessToken
import rx.Observable
import rx.schedulers.Schedulers
import rx.subjects.BehaviorSubject
import timber.log.Timber
import java.util.*


class IncidentsRepository : IIncidentsRepository {
    override fun loadData() {
    }


    private var behaviorSubject: BehaviorSubject<IncidentsModel>
            = BehaviorSubject.create<IncidentsModel>()


    override fun subcRep(): Observable<IncidentsModel> {

        return behaviorSubject.asObservable()

    }

    var mFirebaseDatabase: FirebaseDatabase
    var mVKApi: VKApi


    constructor(api: VKApi, firebaseDatabase: FirebaseDatabase) {
        mFirebaseDatabase = firebaseDatabase
        mVKApi = api
        showElement()
    }


    var i = 1;


    override fun add() {
        addElement(FBModel("ann" + (i++), "" + VKAccessToken.currentToken()?.userId))

    }

    fun addElement(myObject: FBModel) {
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

    fun loadData(ids: Set<String>, list: MutableList<FBModel>) {

        Observable.zip(Observable.just(list), getUsers(ids),
                { fbModel, id -> zipModels(fbModel, id) })
                .subscribeOn(Schedulers.io())
                .subscribe({behaviorSubject.onNext(it)},{},{})


    }

    private fun zipModels(fbModel: MutableList<FBModel>, id: MutableList<UserModel>?): IncidentsModel {
        var usersModels = HashMap<String, UserModel>()
        id?.forEach {
            usersModels.put("" + it.uid, it)
        }

        return IncidentsModel(usersModels, fbModel)
    }

    fun getUsers(ids: Set<String>) =
            mVKApi.getUsers(ids.toString().replace("[", "").replace("]", ""), "photo_50", "5.64")
                    .map { it.response }


    fun showElement() {
        val reference = mFirebaseDatabase.getReference("Incidents")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Timber.e("" + p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot?) {
                val list = mutableListOf<FBModel>()
                val set = HashSet<String>()
                p0?.children?.
                        forEach {

                            var value = it.getValue(FBModel::class.java)
                            set.add(value.idUser)

                            list.add(value)
                        }
                loadData(set, list)


            }
        })
    }

}