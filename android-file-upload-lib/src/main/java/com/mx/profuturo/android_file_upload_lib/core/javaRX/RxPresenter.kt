package com.mx.profuturo.android_file_upload_lib.core.javaRX
import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.mx.profuturo.android_file_upload_lib.core.interfaces.BasePresenter

abstract class RxPresenter<V>: BasePresenter<V> {

    private val disposables = CompositeDisposable()

    fun launch(job: () -> Disposable){
        disposables.add(job())
    }

    override fun subscribe(view: V) {
        this.view = view
    }

    @CallSuper
    override fun unSubscribe() {
        disposables.clear()
        view = null
    }

}