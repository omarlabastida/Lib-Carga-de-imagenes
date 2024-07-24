package com.mx.profuturo.android_file_upload_lib.core.interfaces
interface BasePresenter<V> {

    fun subscribe(view: V)
    fun unSubscribe()
    var view: V?

}