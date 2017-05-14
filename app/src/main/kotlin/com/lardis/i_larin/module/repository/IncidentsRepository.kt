package com.example.i_larin.pixabayreader.repository

import com.example.i_larin.pixabayreader.network.VKApi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lardis.i_larin.module.model.FBModel
import com.lardis.i_larin.module.prefs.Prefs
import com.lardis.i_larin.module.ui.activity.intident.ModelComment
import com.lardis.i_larin.module.ui.fragment.incidents.IncidentAddModel
import com.vk.sdk.VKAccessToken
import rx.Observable
import rx.subjects.BehaviorSubject
import timber.log.Timber


class IncidentsRepository : IIncidentsRepository {


    override fun loadData() {
    }


    private var behaviorSubject: BehaviorSubject<List<FBModel>>
            = BehaviorSubject.create<List<FBModel>>()


    override fun subcRep(): Observable<List<FBModel>> {

        return behaviorSubject.asObservable()

    }

    private var behaviorSubjectSelected: BehaviorSubject<FBModel>
            = BehaviorSubject.create<FBModel>()


    override fun subcRepSelected(): Observable<FBModel> {

        return behaviorSubjectSelected.asObservable()

    }


    var mFirebaseDatabase: FirebaseDatabase
    var mVKApi: VKApi


    constructor(api: VKApi, firebaseDatabase: FirebaseDatabase) {
        mFirebaseDatabase = firebaseDatabase
        mVKApi = api
        showElement()
    }


    var i = 1;


    override fun add(incidentAddModel: IncidentAddModel) {
        VKAccessToken.currentToken()?.let {

            var fBModel = FBModel()
            fBModel.idUser = it.userId

            fBModel.name = Prefs.FIRST_NAME.string
            fBModel.family = Prefs.LAST_NAME.string
            fBModel.photoUrl = Prefs.PHOTO_URL.string
            fBModel.idUser = it.userId



            fBModel.car = incidentAddModel.car
            fBModel.phoneNumber = incidentAddModel.phoneNumber
            fBModel.mapInfo = incidentAddModel.mapInfo
            fBModel.latitude = incidentAddModel.latitude
            fBModel.longitude = incidentAddModel.longitude
            fBModel.intident = incidentAddModel.intident


            addElement(fBModel)
        }
    }

    fun addElement(myObject: FBModel) {
        val reference = mFirebaseDatabase.getReference("Incidents")
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Timber.e("" + p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var push = reference.push()
                myObject.key = push.key
                push.setValue(myObject)
            }
        })
    }

    var selectedModel: FBModel? = null
    override fun selected(fBModel: FBModel) {
        selectedModel = fBModel
        behaviorSubjectSelected.onNext(fBModel)


    }

    override fun completed(fBModel: FBModel) {
        Timber.e("completed")
        mFirebaseDatabase.getReference("Incidents")
                .child(fBModel.key)
                .child("compeleteTime")
                .setValue(System.currentTimeMillis())


    }

    override fun addComments(comments: String) {

        mFirebaseDatabase.getReference("Incidents")
                .child(selectedModel?.key)
                .child("comments")
                .push()
                .setValue(ModelComment(Prefs.FIRST_NAME.string, comments, System.currentTimeMillis(),Prefs.PHOTO_URL.string))


    }

    override fun rm() {
        val reference = mFirebaseDatabase.getReference("Incidents")
        reference.removeValue({ databaseError, databaseReference -> {} })
    }


    fun showElement() {
        val reference = mFirebaseDatabase.getReference("Incidents")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Timber.e("" + p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot?) {
                val list = mutableListOf<FBModel>()

                p0?.children?.
                        forEach {
                            var value = it.getValue(FBModel::class.java)
                            list.add(value)
                            if (it.key.equals(selectedModel?.key)) {
                                behaviorSubjectSelected.onNext(value)
                            }

                        }
                behaviorSubject.onNext(list)


            }
        })
    }

}