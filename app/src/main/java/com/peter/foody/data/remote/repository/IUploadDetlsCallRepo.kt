package com.hend.calldetailsrecorder.data.remote.repository


interface IUploadDetlsCallRepo {
    fun sentRingingAction()

    fun sentAnswerAction()

    fun sentCloseAction()

    fun sentNoAnswerAction()
}